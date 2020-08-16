package solid.domains.security;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import lombok.Data;
import solid.domains.employee.Employee;

@Entity
@NamedQuery(name="Access.disable", query="UPDATE Access a SET a.authorized = false WHERE a.employee.id = :employeeId")
@Data
public class Access {

	@Id
	@GeneratedValue
	private Integer id;

	private Boolean authorized;
	
	private LocalDateTime date;
	
	@OneToOne
	private Employee employee;

}
