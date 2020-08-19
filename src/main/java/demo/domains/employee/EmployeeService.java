package demo.domains.employee;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class EmployeeService {

	@Inject
	private EmployeeRepository repository;

	public Integer createEmployee(CreateEmployeeRequest request) {
		Integer id = repository.creteEmployee(request);
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
