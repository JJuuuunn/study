package employee;

import employee.domain.Employee;

import java.util.ArrayList;

public interface EmployeeOutput {
	public ArrayList<Employee> getEmployeeList(String strUserID);
}
