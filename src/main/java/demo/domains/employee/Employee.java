package demo.domains.employee;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import lombok.Data;

@Entity
@NamedQueries(value = {
		@NamedQuery(name = "Employee.disable", query = "UPDATE Employee e SET e.enabled = false WHERE e.id = :id"),
		@NamedQuery(name = "Employee.delete", query = "DELETE FROM Employee e WHERE e.id = :id"),
		@NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e") })

@Data
public class Employee {

	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	private Integer salary;

	private Boolean enabled;

}
