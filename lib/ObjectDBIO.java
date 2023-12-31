package lib;

import employee.Employee;

import java.sql.*;

public abstract class ObjectDBIO {

	private Connection conn = null;
	private String jdbc_url = "jdbc:mysql://localhost:3306/HRInfoSystem";
	// TODO: env 파일로 변경할것
	private String db_id = "yang";
	private String db_pwd = "1234";

	// Setter
	public void setDb_id(String db_id) {
		this.db_id = db_id;
	}

	public void setDb_pwd(String db_pwd) {
		this.db_pwd = db_pwd;
	}

	public void setJdbc_url(String jdbc_url) {
		this.jdbc_url = jdbc_url;
	}
	
	// DB Connect
	protected Connection open() {
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
		} catch(ClassNotFoundException e) {
			System.err.println(" !! JDBC Driver load Error : " + e.getMessage());
			e.printStackTrace();
			return null;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	// DB Disconnect
	protected boolean close() {
		try {
			conn.close();
			return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	protected ResultSet execute(String strSql, ResultSet rs)
	{		
		try {
			open();
			Statement stObj = conn.createStatement();
			rs = stObj.executeQuery(strSql);
			//close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	protected void execute(String strSql)
	{		
		try {
			open();
			Statement stObj = conn.createStatement();
			stObj.executeQuery(strSql);
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
