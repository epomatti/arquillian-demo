package solid.domains.employee;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@Stateless
public class EmployeeRepository {

	@Inject
	private EntityManager em;

	public Integer creteEmployee(String name, Integer salary) {
		Employee employee = new Employee();
		employee.setName(name);
		employee.setSalary(salary);
		employee.setEnabled(true);
		em.persist(employee);
		return employee.getId();
	}

	public Integer getSalary(Integer id) {
		return em.find(Employee.class, id).getSalary();
	}

	public Integer disable(Integer employeeId) {
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
	public List<Employee> findAll() {
		Query query = em.createNamedQuery("Employee.findAll");
		return query.getResultList();
	}

}
