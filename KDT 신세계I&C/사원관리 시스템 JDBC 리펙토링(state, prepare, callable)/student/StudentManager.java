package student;

import java.util.ArrayList;

public class StudentManager extends StudentDBIO {
	private static final StudentManager instance = new StudentManager();
	
	private StudentManager() {}
	public static StudentManager getInstance() {
		return instance;
	}
	
	public boolean insertStudent(Student stu) {
		return super.insertStudent(stu);
	}
	
	public ArrayList<Student> getStudentList() {
		return super.getStudentList();
	}
	
	public ArrayList<Student> searchStudent(String strSNo) {
		return super.searchStudent(strSNo);
	}
	
	public ArrayList<Student> getSortedStudent() {
		ArrayList<Student> stArray = super.getSortedStudent();
		Student st = null;
		for(int nRank = 1, nIndex = 0, nbforTotal = 0, nSize = stArray.size(); nIndex < nSize; nIndex++ ) {
			st = stArray.get(nIndex);
			if(st.getTotal() != nbforTotal) {
				nRank = nIndex + 1;
			}
			st.setRank(nRank);
			nbforTotal = st.getTotal();
		}
		return stArray;
	}
}
