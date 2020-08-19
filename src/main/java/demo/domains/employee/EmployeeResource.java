package demo.domains.employee;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import demo.shared.BaseResource;

@Path("/employee")
@Stateless
public class EmployeeResource extends BaseResource {

	@Inject
	private EmployeeService service;

	@POST
	public Response createEmployee(CreateEmployeeRequest request) {
		Integer id = service.createEmployee(request);
		return Response.ok().entity(id).build();
	}

	@GET
	@Path("all")
	public List<Employee> getEmployees() {
		return service.getAllEmployees();
	}

}