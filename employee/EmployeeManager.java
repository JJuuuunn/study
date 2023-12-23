package employee;

import java.util.ArrayList;

public class EmployeeManager extends EmployeeDBIO{
	private static final EmployeeManager instance = new EmployeeManager();
	private EmployeeManager() {}
	public static EmployeeManager getInstance() {
		return instance;
	}
	
	public boolean insertStaff(Staff emp) {
		return super.insertStaff(emp);
	}
	
	public boolean insertSecretary(Secretary emp) {
		return super.insertSecretary(emp);
	}
	
	public boolean insertManager(Manager emp) {
		return super.insertManager(emp);
	}
	
	public ArrayList<Employee> getEmployeeList(String strUserID) {
		ArrayList<Employee> resArray = super.getEmployeeList(strUserID);
		return resArray;
	}
	
	public ArrayList<Employee> searchEmployee(String strENo) {
		ArrayList<Employee> resArray = super.searchEmployee(strENo);
		return resArray;
	}
}
