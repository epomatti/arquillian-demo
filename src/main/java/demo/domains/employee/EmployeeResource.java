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
		Integer employeeId = service.createEmployee(request);
		return Response.ok().entity(employeeId).build();
	}

	@GET
	@Path("all")
	public Response getEmployees() {
		List<Employee> employees = service.getAllEmployees();
		return Response.ok().entity(employees).build();
	}

}