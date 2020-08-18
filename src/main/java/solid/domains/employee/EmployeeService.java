package solid.domains.employee;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import solid.domains.security.SecurityService;

@Stateless
public class EmployeeService {

	@Inject
	private EmployeeRepository repository;

	@Inject
	private SecurityService security;

	public Integer createEmployee(String name, Integer salary) {
		Integer id = repository.creteEmployee(name, salary);
		security.enableAccess(id);
		return id;
	}

	public Integer getEmployeeSalary(Integer id) {
		return repository.getSalary(id);
	}

	public void layoffEmployee(Integer id) {
		repository.disable(id);
	}

	public List<Employee> getAllEmployees() {
		return repository.findAll();
	}

}
