package employee;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import employee.dto.EmployeeWithLevelDto;
import employee.entity.Employee;
import employee.entity.Manager;
import employee.entity.Secretary;
import employee.entity.Staff;

import lib.ObjectDBIO;

public abstract class EmployeeDBIO extends ObjectDBIO implements EmployeeIO {

    /**
     * 직원 정보를 추가하는 메소드
     * secno는 매니저와 OnetoOne 관계이므로 여기서는 임시로 null
     * <p>
     * TODO : secno에 null 대신 넣을 값 생각하기
     *
     * @param emp
     * @return boolean -> 정보가 등록되면 true
     */
    public boolean insertStaff(Staff emp) {
        String insertSql = "insert into EMPLOYEE values (null, ?, ?, ?, ?, ?, ?, null)";

        Connection conn = super.open();
        try {
            PreparedStatement pstm = conn.prepareStatement(insertSql);
            pstm.setString(1, emp.getENo());    // eno
            pstm.setString(2, emp.getName());   // name
            pstm.setInt(3, emp.getYear());      // year
            pstm.setInt(4, emp.getMonth());     // month
            pstm.setInt(5, emp.getDate());      // date
            pstm.setString(6, emp.getRole());   // role
            pstm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn);
        }
        return true;
    }

    /**
     * Secretary 정보를 추가하는 메소드
     * Staff 클래스가 Secretary 클래스의 부모 클래스이므로 호환 가능
     *
     * @param emp
     * @return boolean -> 정보가 등록되면 true
     */
    public boolean insertSecretary(Secretary emp) {
        return insertStaff(emp);
    }

    /**
     * 매니저를 추가하는 메소드
     * 최소 한명 이상의 Staff 또는 Secretary가 있어야 동작
     *
     * @param emp
     * @return boolean -> 정보가 등록되면 true
     */
    public boolean insertManager(Manager emp) {
        ArrayList<Employee> resArray = searchEmployee(emp.getSecNo());
        if (resArray.isEmpty()) return false;

        String insertSql = "insert into EMPLOYEE values(null, ?,?,?,?,?,?,?)";

        Connection conn = super.open();
        try {
            PreparedStatement pstm = conn.prepareStatement(insertSql);
            pstm.setString(1, emp.getENo());    // eno
            pstm.setString(2, emp.getName());   // name
            pstm.setInt(3, emp.getYear());      // year
            pstm.setInt(4, emp.getMonth());     // month
            pstm.setInt(5, emp.getDate());      // date
            pstm.setString(6, emp.getRole());   // role
            pstm.setString(7, emp.getSecNo());  // secNo

            pstm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            super.close();
        }
        return true;
    }

    /**
     * 해당 직원의 eno를 통해 접근 권한을 확인하고
     * 접근 가능한 데이터까지 찾아서 반환 메소드
     *
     * @param eno -> 검색을 사용하고 있는 직원의 ID
     * @return EmployeeList
     * @throws SQLException
     */
    public ArrayList<Employee> getEmployeeList(String eno) {
        Connection conn = super.open();
        ResultSet rs = null;

        Optional<EmployeeWithLevelDto> optional = findById(eno, conn);
        if (optional.isEmpty()) { // 없는 직원이면 데이터에 접근하면 안되기 때문에 빈 리스트 반환
            return new ArrayList<>();
        }

        EmployeeWithLevelDto dto = optional.get();
        if (dto.getExpiredAt().isBefore(LocalDateTime.now())) {
            System.out.println("접근권한이 만료되었습니다.");
            return new ArrayList<>();
        }

        String selectSql = "select e.*, r.* " +
                "from EMPLOYEE " +
                "as e " +
                "left join RESTRICTION_LEVEL as r " +
                "on e.ID = r.EMPLOYEE_ID " +
                "where r.ACCESS_ROLE <= ? || " +
                "r.ACCESS_ROLE is NULL && " +
                "e.ROLE != 'Manager'" +
                "order by e.eno";

        ArrayList<Employee> resArray = new ArrayList<>();
        try {
            PreparedStatement pstm = conn.prepareStatement(selectSql);
            pstm.setLong(1, dto.getRole()); // role

            rs = pstm.executeQuery();
            while (rs.next()) {
                Employee emp = getEmployee(rs);

                resArray.add(emp);
            }

            rs.close();
            super.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return resArray;
    }

    /**
     * 직원의 ENO를 통해 직원의 권한을 찾는 메소드
     *
     * @param eno
     * @param conn
     * @return
     */
    private static Optional<EmployeeWithLevelDto> findById(String eno, Connection conn) {
        String selectSql = "select e.eno, e.name, r.access_role, r.expired_at " +
                "from EMPLOYEE as e " +
                "join RESTRICTION_LEVEL as r " +
                "on e.id = r.employee_id " +
                "where eno = ? ";

        ResultSet rs;
        Optional<EmployeeWithLevelDto> optional = null;

        try {
            PreparedStatement pstm = conn.prepareStatement(selectSql);
            pstm.setString(1, eno);

            rs = pstm.executeQuery();

            if (!rs.next()) {
                optional = Optional.empty();
                System.out.println("존재하지 않는 회원입니다.");
            } else {
                optional = Optional.of(
                        EmployeeWithLevelDto.builder()
                                .eno(rs.getString("eno"))
                                .name(rs.getString("name"))
                                .role(rs.getLong("access_role"))
                                .expiredAt(rs.getTimestamp("expired_at").toLocalDateTime())
                                .build()
                );
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return optional;
    }

    /**
     * 특정 직원을 찾는 메소드
     * TODO : Role과 다른 데이터 접근 권한에 관한 테이블을 새로 만들어 추후에 권한을 학인 후 메소드 사용 가능하도록 변경
     *
     * @param strENo
     * @return EmployeeList
     * @throws SQLException
     */
    public ArrayList<Employee> searchEmployee(String strENo) {
        ArrayList<Employee> resArray = new ArrayList<>();
        String selectSql = "select * from EMPLOYEE where eno = ?";

        Connection conn = super.open();
        ResultSet rs = null;
        try {
            PreparedStatement pstm = conn.prepareStatement(selectSql);
            pstm.setString(1, strENo);    // secno

            rs = pstm.executeQuery();
            while (rs.next()) {
                Employee emp = getEmployee(rs);

                resArray.add(emp);
            }
            rs.close();
            super.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resArray;
    }

    /**
     * SQL을 통해서 데이터베이스에서 가져온 ResultSet을
     * 각각의 Role에 맞게 Builder를 사용하여 Staff, Secretary, Manager로 변환.
     * 생성자 패턴 Body에서 값을 넣던 Role, Firstpay, EnteringDay, NowPay를
     * Builder에서 넣을 방법이 없어 따로 메소드를 사용하여 데이터를 넣음.
     *
     * @param rs -> SQL 실행 결과값들이 담긴 Employee
     * @return Employee
     * @throws SQLException
     */
    private static Employee getEmployee(ResultSet rs) throws SQLException {
        if (rs.getString("role").equals("Staff")) {
            Staff staff = Staff.builder()
                    .m_strENo(rs.getString("eno"))
                    .m_strName(rs.getString("name"))
                    .m_nYear(rs.getInt("enteryear"))
                    .m_nMonth(rs.getInt("entermonth"))
                    .m_nDate(rs.getInt("enterday"))
                    .m_strRole("Staff")
                    .m_nFirstPay(2000L)
                    .build();
            staff.setEnteringDay();
            staff.setNowPay(staff.getPay());

            return staff;
        } else if (rs.getString("role").equals("Secretary")) {
            Secretary secretary = Secretary.builder()
                    .m_strENo(rs.getString("eno"))
                    .m_strName(rs.getString("name"))
                    .m_nYear(rs.getInt("enteryear"))
                    .m_nMonth(rs.getInt("entermonth"))
                    .m_nDate(rs.getInt("enterday"))
                    .m_strRole("Secretary")
                    .m_nFirstPay(2000L)
                    .build();
            secretary.setEnteringDay();
            secretary.setNowPay(secretary.getPay());

            return secretary;
        } else if (rs.getString("role").equals("Manager")) {
            Manager manager = Manager.builder()
                    .m_strENo(rs.getString("eno"))
                    .m_strName(rs.getString("name"))
                    .m_nYear(rs.getInt("enteryear"))
                    .m_nMonth(rs.getInt("entermonth"))
                    .m_nDate(rs.getInt("enterday"))
                    .m_strSecNo(rs.getString("secno"))
                    .m_strRole("Manager")
                    .m_nFirstPay(2000L)
                    .build();
            manager.setEnteringDay();
            manager.setNowPay(manager.getPay());

            return manager;
        }
        return null;
    }
}
