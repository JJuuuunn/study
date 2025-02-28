package employee;

import employee.domain.Employee;

import java.util.ArrayList;

public interface EmployeeOutput {
	ArrayList<Employee> getEmployeeList(String strUserID);
}
