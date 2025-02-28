package lib;

import java.sql.*;

public abstract class ObjectDBIO {

	private Connection conn = null;
	private String jdbc_url = "jdbc:mysql://localhost:3306/HRInfoSystem";
	// TODO: env 파일로 변경할것
	private String db_id = "root";
	private String db_pwd = "0";

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


	/**
	 * DB Connect
	 * ObjectDBIO에서 실행되던 쿼리를 자식클래스에서 실행하게 하기위해
	 * 접근제어자를 Protected로 변경하여 DB를 연결 후 반환
	 * 기존에 private 였던걸 protected로 변경
	 *
	 * @return Connection
	 */
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

	/**
	 * DB Disconnect
	 * 하위 클래스로 보낸 DB Connection을 닫기 위해서 파라미터로 닫아야 할 Connection을 가져옴
	 *
	 * @param connection
	 * @return
	 */
	protected boolean close(Connection connection) {
		try {
			connection.close();
			return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Employee 패키지에서는 삭제완료
	 * Student 패키지에서는 삭제 예정
	 *
	 * @param strSql
	 * @param rs
	 * @return
	 */
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

	/**
	 * Employee 패키지에서는 삭제완료
	 * Student 패키지에서는 삭제 예정
	 *
	 * @param strSql
	 */
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
