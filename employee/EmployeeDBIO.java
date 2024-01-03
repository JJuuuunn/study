package employee;

import java.sql.*;
import java.util.ArrayList;

import student.Student;

import lib.ObjectDBIO;

public abstract class EmployeeDBIO extends ObjectDBIO implements EmployeeIO {

    /**
     * insertStaff 메소드
     *
     * @param emp
     * @return
     */
    public boolean insertStaff(Staff emp) {
        String insertSql = "insert into EMPLOYEE values (?, ?, ?, ?, ?, ?, null)";

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

    public boolean insertSecretary(Secretary emp) {
        return insertStaff(emp);
    }

    /**
     * insertManager 메소드
     *
     * @param emp
     * @return
     * @throws SQLException
     */
    public boolean insertManager(Manager emp) {
        ArrayList<Employee> resArray = searchEmployee(emp.getSecNo());
        if (resArray.isEmpty()) return false;

        String insertSql = "insert into EmPLOYEE values(?,?,?,?,?,?,?)";

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
     * getEmployeeList 메소드
     *
     * @param strUserID
     * @return
     * @throws SQLException
     */
    public ArrayList<Employee> getEmployeeList(String strUserID) {
        ArrayList<Employee> resArray = new ArrayList<Employee>();

        String selectSql = "select * from EMPLOYEE where (role != 'Manager') or (secno = ?)";

        Connection conn = super.open();
		ResultSet rs = null;
		try {
            PreparedStatement pstm = conn.prepareStatement(selectSql);
            pstm.setString(1, strUserID);	// secno

			rs = pstm.executeQuery();
			while(rs.next()) {
				Employee emp = null;
				if(rs.getString("role").equals("Staff")) {
					emp = new Staff(rs.getString("eno"), rs.getString("name"), rs.getInt("enteryear"),
							rs.getInt("entermonth"), rs.getInt("enterday"));
				}
				else if(rs.getString("role").equals("Secretary")) {
					emp = new Secretary(rs.getString("eno"), rs.getString("name"), rs.getInt("enteryear"),
							rs.getInt("entermonth"), rs.getInt("enterday"));
				}
				else if(rs.getString("role").equals("Manager")) {
					emp = new Manager(rs.getString("eno"), rs.getString("secno"), rs.getString("name"),
							rs.getInt("enteryear"), rs.getInt("entermonth"), rs.getInt("enterday"));
				}
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
     * searchEmployee 메소드
     *
     * @param strENo
     * @return
     * @throws SQLException
     */
    public ArrayList<Employee> searchEmployee(String strENo) {
        ArrayList<Employee> resArray = new ArrayList<Employee>();
        String selectSql = "select * from EMPLOYEE where eno = ?";

		Connection conn = super.open();
		ResultSet rs = null;
		try {
		    PreparedStatement pstm = conn.prepareStatement(selectSql);
		    pstm.setString(1, strENo);	// secno

            rs = pstm.executeQuery();
			while(rs.next()) {
				Employee emp = null;
				if(rs.getString("role").equals("Staff")) {
					emp = new Staff(rs.getString("eno"), rs.getString("name"), rs.getInt("enteryear"),
							rs.getInt("entermonth"), rs.getInt("enterday"));
				}
				else if(rs.getString("role").equals("Secretary")) {
					emp = new Secretary(rs.getString("eno"), rs.getString("name"), rs.getInt("enteryear"),
							rs.getInt("entermonth"), rs.getInt("enterday"));
				}
				else if(rs.getString("role").equals("Manager")) {
					emp = new Manager(rs.getString("eno"), rs.getString("secno"), rs.getString("name"),
							rs.getInt("enteryear"), rs.getInt("entermonth"), rs.getInt("enterday"));
				}
				resArray.add(emp);
			}
			rs.close();
			super.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resArray;
	}
}
