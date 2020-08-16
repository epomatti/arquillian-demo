package solid.domains.employee;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import solid.domains.security.SecurityService;

@Stateless
public class EmployeeService {
	
	@Inject
	private EntityManager em;
	
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
		return em.find(Employee.class, id).getSalary();
	}
	
	public void layoffEmployee(Integer id) {
		repository.disable(id);
	}

}
