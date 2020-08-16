package solid.domains.employee;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

import lombok.Data;

@Entity
@NamedQuery(name="Employee.disable", query="UPDATE Employee e SET e.enabled = false WHERE e.id = :id")
@Data
public class Employee {

	@Id
	@GeneratedValue
	private Integer id;

	private String name;
	
	private Integer salary;
	
	private Boolean enabled;

}
