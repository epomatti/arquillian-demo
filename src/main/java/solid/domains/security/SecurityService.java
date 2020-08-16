package solid.domains.security;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class SecurityService {
	
	@Inject
	private SecurityRepository repository;
	
	public void enableAccess(Integer employeeId) {
		repository.createEmployeeAccess(employeeId);
	}
	
	public void disableAccess(Integer employeeId) {
		repository.disableAccess(employeeId);
	}

}
