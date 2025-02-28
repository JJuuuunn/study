package student;

import java.sql.*;
import java.util.ArrayList;
import lib.ObjectDBIO;

public abstract class StudentDBIO extends ObjectDBIO implements StudentIO {	
	
	public ArrayList<Student> getStudentList() {
		ArrayList<Student> resArray = new ArrayList<Student>();
		String strSql = "select * from STUDENT";
		ResultSet rs = null;
		
		try {
			rs = super.execute(strSql, rs);
			while(rs.next()) {
				Student stu = new Student(rs.getString("sno"), rs.getString("name"), rs.getInt("korean"),
						rs.getInt("english"), rs.getInt("math"), rs.getInt("science"), 
						rs.getInt("total"), rs.getFloat("average"), rs.getString("grade"));
				resArray.add(stu);
			}
			rs.close();
			super.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resArray;
	}
	
	public ArrayList<Student> searchStudent(String strSNo) {
		ArrayList<Student> resArray = new ArrayList<Student>();
		String strSql = "select * from STUDENT where sno = '" + strSNo + "'";
		ResultSet rs = null;
		
		try {
			rs = super.execute(strSql, rs);
			while(rs.next()) {
				Student stu = new Student(rs.getString("sno"), rs.getString("name"), rs.getInt("korean"),
						rs.getInt("english"), rs.getInt("math"), rs.getInt("science"), 
						rs.getInt("total"), rs.getFloat("average"), rs.getString("grade"));
				resArray.add(stu);
			}
			rs.close();
			super.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resArray;
	}
	
	public ArrayList<Student> getSortedStudent() {
		ArrayList<Student> resArray = new ArrayList<Student>();
		String strSql = "select * from STUDENT ORDER BY total DESC";
		ResultSet rs = null;
		
		try {
			rs = super.execute(strSql, rs);
			while(rs.next()) {
				Student stu = new Student(rs.getString("sno"), rs.getString("name"), rs.getInt("korean"),
						rs.getInt("english"), rs.getInt("math"), rs.getInt("science"), 
						rs.getInt("total"), rs.getFloat("average"), rs.getString("grade"));
				resArray.add(stu);
			}
			rs.close();
			super.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resArray;
	}
	
	public boolean insertStudent(Student stu) {
		String strSql = "insert into STUDENT values('" + stu.getSNo() + "','" + stu.getName() + "'," + stu.getRecord()[0] + "," + 
		stu.getRecord()[1] + "," + stu.getRecord()[2] + "," + stu.getRecord()[3] + "," + stu.getTotal() + "," + stu.getAverage() + ",'" +
		stu.getGrade() + "')";
		
		super.execute(strSql);
		return true;
	}
}
