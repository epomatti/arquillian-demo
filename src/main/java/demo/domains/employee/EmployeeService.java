package demo.domains.employee;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class EmployeeService {

	@Inject
	private EmployeeRepository repository;

	public Integer createEmployee(CreateEmployeeRequest request) {
		return repository.creteEmployee(request);
	}

	public Integer getEmployeeSalary(Integer employeeId) {
		return repository.getEmployeeSalary(employeeId);
	}

	public void layoffEmployee(Integer employeeId) {
		repository.disableEmployee(employeeId);
	}

	public List<Employee> getAllEmployees() {
		return repository.findAllEmployees();
	}

}
