package employee;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import employee.dto.EmployeeWithLevelDto;
import employee.entity.Employee;
import employee.entity.Manager;
import employee.entity.Secretary;
import employee.entity.Staff;

import employee.error.code.EmployeeErrorCode;
import employee.error.exception.EmployeeException;
import lib.ObjectDBIO;

public abstract class EmployeeDBIO extends ObjectDBIO implements EmployeeIO {

    /**
     * 직원 정보를 추가하는 메소드
     * 추가하기전에 이미 존재하는 사원번호인지 확인
     * secno는 매니저와 OnetoOne 관계이므로 여기서는 임시로 null
     *
     * @param emp
     * @return boolean -> 정보가 등록되면 true
     */
    public boolean insertStaff(Staff emp) {
        boolean result = false;
        Connection conn = null;
        CallableStatement cstmt = null;
        String ackMessage = null;

        try {
            existsByEno(emp.getENo()); // 이미 존재하는 사원번호인지 확인

            String callSql = "{call InsertEmployee(?, ?, ?, ?, ?, ?, ?)}";

            conn = super.open();
            cstmt = conn.prepareCall(callSql);

            cstmt.setString(1, emp.getENo());
            cstmt.setString(2, emp.getName());
            cstmt.setInt(3, emp.getYear());
            cstmt.setInt(4, emp.getMonth());
            cstmt.setInt(5, emp.getDate());
            cstmt.setString(6, emp.getRole());

            cstmt.registerOutParameter(7, Types.VARCHAR);

            cstmt.execute();

            ackMessage = cstmt.getString(7);
            System.out.println(ackMessage);
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(cstmt);
            close(conn);
            return result;
        }
    }

    private static void close(CallableStatement cstmt) {
        try {
            if (cstmt != null) {
                cstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 사원번호를 통해 직원이 존재하는지 확인하는 메소드
     *
     * @param Eno
     * @return
     */
    private boolean existsByEno(String Eno) {
        Connection conn = null;
        CallableStatement cstmt = null;
        boolean exists = false;

        try {
            String callSql = "{call CheckEmployeeExistence(?, ?)}";

            conn = super.open();
            cstmt = conn.prepareCall(callSql);

            cstmt.setString(1, Eno);

            cstmt.registerOutParameter(2, Types.BOOLEAN);

            cstmt.execute();

            exists = cstmt.getBoolean(2);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(cstmt);
            super.close(conn);
            return exists;
        }
    }


    /**
     * Secretary 정보를 추가하는 메소드
     * Staff 클래스가 Secretary 클래스의 부모 클래스이므로 호환 가능
     * 추가하기전에 이미 존재하는 사원번호인지 확인
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
     * 추가하기전에 이미 존재하는 사원번호인지 확인
     * <p>
     * TODO : 예외처리 리펙토링하기
     *
     * @param emp
     * @return boolean -> 정보가 등록되면 true
     */
    public boolean insertManager(Manager emp) {
        boolean result = false;
        Connection conn = null;

        try {
            if (existsByEno(emp.getENo())) { // 이미 존재하는 사원번호인지 확인
                throw new EmployeeException(EmployeeErrorCode.EMPLOYEE_ALREADY_EXIST);
            }

            ArrayList<Employee> resArray = searchEmployee(null, emp.getSecNo()); // 비서 사원번호가 존재하는지 확인
            if (resArray.isEmpty()) { // 비서 사원번호가 존재하지 않으면 false TODO : 예외처리하기
                throw new EmployeeException(EmployeeErrorCode.EMPLOYEE_NOT_FOUND);
            }

            String insertSql = "insert into EMPLOYEE values(null, ?,?,?,?,?,?,?)";
            conn = super.open();

            PreparedStatement pstm = conn.prepareStatement(insertSql);
            pstm.setString(1, emp.getENo());    // eno
            pstm.setString(2, emp.getName());   // name
            pstm.setInt(3, emp.getYear());      // year
            pstm.setInt(4, emp.getMonth());     // month
            pstm.setInt(5, emp.getDate());      // date
            pstm.setString(6, emp.getRole());   // role
            pstm.setString(7, emp.getSecNo());  // secNo

            pstm.execute();

            result = true;
            System.out.println("등록되었습니다.");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (EmployeeException e) {
            e.printStackTrace();
        } finally {
            super.close(conn);
            return result;
        }
    }

    /**
     * 해당 직원의 eno를 통해 접근 권한을 확인하고
     * 접근 가능한 데이터까지 찾아서 반환 메소드
     *
     * @param eno -> 검색을 사용하고 있는 직원의 ID
     * @return EmployeeList
     */
    public ArrayList<Employee> getEmployeeList(String eno) {
        Connection conn = null;
        ResultSet rs = null;
        ArrayList<Employee> resArray = new ArrayList<>();

        try {
            conn = super.open();

            EmployeeWithLevelDto dto = findById(eno).orElseThrow(() -> new EmployeeException(EmployeeErrorCode.EMPLOYEE_NOT_FOUND));
            checkRole(dto); // 직원의 권한이 존재하는지 확인하는 메소드

            String selectSql = "select e.*, r.* " +
                    "from EMPLOYEE as e " +
                    "left join RESTRICTION_LEVEL as r " +
                    "on e.ID = r.EMPLOYEE_ID " +
                    "where r.ACCESS_ROLE <= ? || " +
                    "r.ACCESS_ROLE is NULL && " +
                    "e.ROLE != 'Manager' " +
                    "order by e.eno";

            PreparedStatement pstm = conn.prepareStatement(selectSql);
            pstm.setLong(1, dto.getRole()); // role

            rs = pstm.executeQuery();
            while (rs.next()) {
                Employee emp = getEmployee(rs);

                resArray.add(emp);
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (EmployeeException e) {
            e.printStackTrace();
        } finally {
            super.close(conn);
            return resArray;
        }
    }

    private static void checkRole(EmployeeWithLevelDto dto) {
        if (dto.getRole() == 0) {
            throw new EmployeeException(EmployeeErrorCode.EMPLOYEE_NOT_AUTHORIZED);
        } else if (dto.getExpiredAt().isBefore(LocalDateTime.now())) {
            throw new EmployeeException(EmployeeErrorCode.EMPLOYEE_EXPIRED_AUTHORITY);
        }
    }

    /**
     * eno를 통해 직원을 찾는 메소드
     * 접근 권한을 확인하고 접근 가능한 데이터까지 찾아서 반환
     *
     * @param eno
     * @return
     */
    private Optional<EmployeeWithLevelDto> findById(String eno) {
        Connection conn = null;
        String selectSql = "select e.eno, e.name, r.access_role, r.expired_at " +
                "from EMPLOYEE as e " +
                "left join RESTRICTION_LEVEL as r " +
                "on e.id = r.employee_id " +
                "where eno = ? ";

        ResultSet rs;
        Optional<EmployeeWithLevelDto> optional = null;

        try {
            conn = super.open();
            PreparedStatement pstm = conn.prepareStatement(selectSql);
            pstm.setString(1, eno);

            rs = pstm.executeQuery();

            if (!rs.next()) {
                throw new EmployeeException(EmployeeErrorCode.EMPLOYEE_NOT_FOUND);
            } else {
                optional = Optional.of(
                        EmployeeWithLevelDto.builder()
                                .eno(rs.getString("eno"))
                                .name(rs.getString("name"))
                                .role(rs.getLong("access_role"))
                                .expiredAt(checkDateTimeNull(rs.getTimestamp("expired_at")))
                                .build()
                );
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (EmployeeException e) {
            e.printStackTrace();
        } finally {
            super.close(conn);
            return optional;
        }
    }

    /**
     * 특정 직원을 찾는 메소드
     * 접근 권한을 확인하고 접근 가능한 데이터까지 찾아서 반환
     *
     * @param strENo
     * @return EmployeeList
     * @throws SQLException
     */
    public ArrayList<Employee> searchEmployee(String eno, String strENo) {
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<Employee> resArray = new ArrayList<>();

        try {
            conn = super.open();

            EmployeeWithLevelDto dto = findById(eno).orElseThrow(() -> new EmployeeException(EmployeeErrorCode.EMPLOYEE_NOT_FOUND));
            checkRole(dto); // 직원의 권한이 존재하는지 확인하는 메소드

            String selectSql = "select e.*, r.* " +
                    "from EMPLOYEE as e " +
                    "left join RESTRICTION_LEVEL as r " +
                    "on e.ID = r.EMPLOYEE_ID " +
                    "where eno = ? && " +
                    "(r.ACCESS_ROLE <= ? || " +
                    "r.ACCESS_ROLE is NULL) " +
                    "order by e.eno";

            PreparedStatement pstm = conn.prepareStatement(selectSql);
            pstm.setString(1, strENo);    // secno
            pstm.setLong(2, dto.getRole()); // role

            rs = pstm.executeQuery();
            while (rs.next()) {
                Employee emp = getEmployee(rs);

                resArray.add(emp);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (EmployeeException e) {
            e.printStackTrace();
        } finally {
            close(conn);
            return resArray;
        }
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
    private Employee getEmployee(ResultSet rs) throws SQLException {
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

    /**
     * Timestamp를 LocalDateTime으로 변환하고 NULL 체크하는 메소드
     *
     * @param str
     * @return
     */
    private LocalDateTime checkDateTimeNull(Timestamp str) {
        if (str == null) {
            return null;
        }
        return str.toLocalDateTime();
    }
}
