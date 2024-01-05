package employee;

import employee.entity.Employee;

import java.util.ArrayList;

public interface EmployeeOutput {
	public ArrayList<Employee> getEmployeeList(String strUserID);
}
