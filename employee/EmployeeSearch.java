package employee;

import employee.domain.Employee;

import java.util.ArrayList;

public interface EmployeeSearch {
	public ArrayList<Employee> searchEmployee(String eno, String strENo);
}
