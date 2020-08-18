package solid.domains.employee;

import lombok.Data;

@Data
public class CreateEmployeeRequest {

    private String name;
    private Integer salary;

}