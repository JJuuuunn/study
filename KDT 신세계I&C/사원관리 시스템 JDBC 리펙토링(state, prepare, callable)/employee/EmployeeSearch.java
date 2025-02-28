package employee;

import employee.domain.Employee;

import java.util.ArrayList;

public interface EmployeeSearch {
	ArrayList<Employee> searchEmployee(String eno, String strENo);
}
