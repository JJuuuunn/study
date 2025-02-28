package employee;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

import employee.domain.Employee;
import employee.domain.Manager;
import employee.domain.Secretary;
import employee.domain.Staff;

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
        CallableStatement cstmt = null;
        Connection conn = null;
        String ackMessage = null;

        try {
            String callSql = "{call InsertManger(?, ?, ?, ?, ?, ?, ?, ?)}";

            conn = super.open();
            cstmt = conn.prepareCall(callSql);

            cstmt.setString(1, emp.getENo());    // eno
            cstmt.setString(2, emp.getName());   // name
            cstmt.setInt(3, emp.getYear());      // year
            cstmt.setInt(4, emp.getMonth());     // month
            cstmt.setInt(5, emp.getDate());      // date
            cstmt.setString(6, emp.getRole());   // role
            cstmt.setString(7, emp.getSecNo());  // secNo

            cstmt.registerOutParameter(8, Types.VARCHAR); // ackMessage

            cstmt.execute();

            ackMessage = cstmt.getString(8);
            System.out.println(ackMessage);
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (EmployeeException e) {
            e.printStackTrace();
        } finally {
            super.close(conn);
            close(cstmt);
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
        CallableStatement cstmt = null;
        ArrayList<Employee> resArray = new ArrayList<>();

        try {
            conn = super.open();
            String callSql = "{call GetEmployeeList(?, ?)}";

            cstmt = conn.prepareCall(callSql);
            cstmt.setString(1, eno);
            cstmt.registerOutParameter(2, Types.VARCHAR);

            cstmt.execute();

            String ackMessage = cstmt.getString(2);

            if (ackMessage != null) {
                System.out.println(ackMessage);
            } else {
                rs = cstmt.getResultSet();
                while (rs.next()) {
                    Employee emp = getEmployee(rs);

                    resArray.add(emp);
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (EmployeeException e) {
            e.printStackTrace();
        } finally {
            super.close(conn);
            close(cstmt);
            return resArray;
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
        CallableStatement cstmt = null;
        ArrayList<Employee> resArray = new ArrayList<>();

        try {
            conn = super.open();

            String callSql = "{call SearchEmployee(?, ?, ?)}";

            cstmt = conn.prepareCall(callSql);
            cstmt.setString(1, eno);
            cstmt.setString(2, strENo);
            cstmt.registerOutParameter(3, Types.VARCHAR);

            cstmt.execute();

            String ackMessage = cstmt.getString(3);

            if (ackMessage != null) {
                System.out.println(ackMessage);
            } else {
                rs = cstmt.getResultSet();
                while (rs.next()) {
                    Employee emp = getEmployee(rs);

                    resArray.add(emp);
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (EmployeeException e) {
            e.printStackTrace();
        } finally {
            close(conn);
            close(cstmt);
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
            Staff staff = Staff.from(rs);

            return staff;
        } else if (rs.getString("role").equals("Secretary")) {
            Secretary secretary = Secretary.from(rs);

            return secretary;
        } else if (rs.getString("role").equals("Manager")) {
            Manager manager = Manager.from(rs);

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
