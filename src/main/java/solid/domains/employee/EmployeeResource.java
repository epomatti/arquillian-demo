package solid.domains.employee;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import solid.shared.BaseResource;

@Path("/employee")
public class EmployeeResource extends BaseResource {

	@Inject
	private EmployeeService service;

	@POST
	public Integer createEmployee(CreateEmployeeRequest request) {
		return service.createEmployee(request.getName(), request.getSalary());
	}

	@GET
	@Path("/all")
	public List<Employee> getEmployees() {
		return service.getAllEmployees();
	}

}