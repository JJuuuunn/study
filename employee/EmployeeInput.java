package employee;

import employee.domain.Manager;
import employee.domain.Secretary;
import employee.domain.Staff;

public interface EmployeeInput {
	public boolean insertStaff(Staff emp);
	public boolean insertManager(Manager emp);
	public boolean insertSecretary(Secretary emp);
}
