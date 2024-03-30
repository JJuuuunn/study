package employee;

import employee.domain.Manager;
import employee.domain.Secretary;
import employee.domain.Staff;

public interface EmployeeInput {
	boolean insertStaff(Staff emp);
	boolean insertManager(Manager emp);
	boolean insertSecretary(Secretary emp);
}
