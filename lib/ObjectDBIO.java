package lib;

import java.sql.*;

public abstract class ObjectDBIO {

	private Connection conn = null;
	private String jdbc_url = "jdbc:oracle:thin:@localhost:1521";
	private String db_id = "hr";
	private String db_pwd = "hr";
	
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
	private boolean open() {
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
			return true;
		} 
		catch(ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} 
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
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
