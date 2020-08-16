package solid.domains.employee;

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

	public Integer disable(Integer employeeId) {
		Query query = em.createNamedQuery("Employee.disable");
		query.setParameter("id", employeeId);
		return query.executeUpdate();
	}

}