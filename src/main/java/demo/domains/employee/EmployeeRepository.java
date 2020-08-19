package demo.domains.employee;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@Stateless
public class EmployeeRepository {

	@Inject
	private EntityManager em;

	public Integer creteEmployee(CreateEmployeeRequest request) {
		Employee employee = new Employee();
		employee.setName(request.getName());
		employee.setSalary(request.getSalary());
		employee.setEnabled(true);
		em.persist(employee);
		return employee.getId();
	}

	public Integer getEmployeeSalary(Integer employeeId) {
		return em.find(Employee.class, employeeId).getSalary();
	}

	public Integer disableEmployee(Integer employeeId) {
		Query query = em.createNamedQuery("Employee.disable");
		query.setParameter("id", employeeId);
		return query.executeUpdate();
	}

	public Integer deleteEmployee(Integer employeeId) {
		Query query = em.createNamedQuery("Employee.delete");
		query.setParameter("id", employeeId);
		return query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Employee> findAllEmployees() {
		Query query = em.createNamedQuery("Employee.findAll");
		return query.getResultList();
	}

}
