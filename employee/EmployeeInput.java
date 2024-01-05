package employee;

import employee.entity.Manager;
import employee.entity.Secretary;
import employee.entity.Staff;

public interface EmployeeInput {
	public boolean insertStaff(Staff emp);
	public boolean insertManager(Manager emp);
	public boolean insertSecretary(Secretary emp);
}
