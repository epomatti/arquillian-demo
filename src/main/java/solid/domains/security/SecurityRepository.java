package solid.domains.security;

import java.time.LocalDateTime;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import solid.domains.employee.Employee;

@Stateless
public class SecurityRepository {
	
	@Inject
	private EntityManager em;
	
	public Access createEmployeeAccess(Integer employeeId) {		
		Access access = new Access();
		access.setAuthorized(true);
		access.setDate(LocalDateTime.now());
		
		Employee employee = em.find(Employee.class, employeeId);
		access.setEmployee(employee);				
		
		em.persist(access);
		return access;
	}
	
	public Integer disableAccess(Integer employeeId) {
		Query query = em.createNamedQuery("Access.disable");
		query.setParameter("employeeId", employeeId);
		return query.executeUpdate();
	}	
	

}
