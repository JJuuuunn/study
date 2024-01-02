package employee;

import java.sql.*;
import java.util.ArrayList;

import student.Student;

import lib.ObjectDBIO;

public abstract class EmployeeDBIO extends ObjectDBIO implements EmployeeIO {

    /**
     * insertEmp 메소드
     *
     * @param emp
     * @return
     */
    public boolean insertStaff1(Staff emp) {
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
            super.close();
        }
        return true;
    }

	public boolean insertStaff(Staff emp) {
		String strSql = "insert into EMPLOYEE values('" + emp.getENo() + "','" + emp.getName() + "'," + emp.getYear() + "," +
		emp.getMonth() + "," + emp.getDate() + ",'" + emp.getRole() + "', null )";

		super.execute(strSql);
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
    public boolean insertManager1(Manager emp) throws SQLException {
        ArrayList<Employee> resArray = selectEmp(emp.getSecNo());
        if (resArray.isEmpty())
            return false;

        String insertSql = "insert into EmPLOYEE values(?,?,?,?,?,?,?)";

        try {
            Connection conn = super.open();
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
        } finally {
            super.close();
        }
        return true;
    }

    public boolean insertManager(Manager emp) {
        ArrayList<Employee> resArray = searchEmployee(emp.getSecNo());
        if (resArray.isEmpty())
            return false;

        String strSql = "insert into EMPLOYEE values('" + emp.getENo() + "','" + emp.getName() + "'," + emp.getYear() + "," +
                emp.getMonth() + "," + emp.getDate() + ",'" + emp.getRole() + "','" + emp.getSecNo() + "')";

        super.execute(strSql);
        return true;
    }

    /**
     * getEmployeeList 메소드
     *
     * @param strUserID
     * @return
     * @throws SQLException
     */
    public ArrayList<Employee> selectEmpList(String strUserID) throws SQLException {
        ArrayList<Employee> resArray = new ArrayList<Employee>();
        String selectSql = "select * from EMPLOYEE where (role != 'Manager') or (secno = ?)";

		Connection conn = super.open();
		PreparedStatement pstm = conn.prepareStatement(selectSql);
		pstm.setString(1, strUserID);	// secno

		ResultSet rs = null;
		try {
			rs = pstm.executeQuery(selectSql);
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

	public ArrayList<Employee> getEmployeeList(String strUserID) {
		ArrayList<Employee> resArray = new ArrayList<Employee>();
		String strSql = "select * from EMPLOYEE where (role != 'Manager') or (secno = '" + strUserID + "')";
		ResultSet rs = null;

		try {
			rs = super.execute(strSql, rs);
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
    public ArrayList<Employee> selectEmp(String strENo) throws SQLException {
        ArrayList<Employee> resArray = new ArrayList<Employee>();
        String selectSql = "select * from EMPLOYEE where eno = ?";

		Connection conn = super.open();
		PreparedStatement pstm = conn.prepareStatement(selectSql);
		pstm.setString(1, strENo);	// secno


		ResultSet rs = null;
		try {
			rs = super.execute(selectSql, rs);
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
	
	public ArrayList<Employee> searchEmployee(String strENo) {
		ArrayList<Employee> resArray = new ArrayList<Employee>();
		String strSql = "select * from EMPLOYEE where eno = '" + strENo + "'";
		ResultSet rs = null;
		
		try {
			rs = super.execute(strSql, rs);
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
