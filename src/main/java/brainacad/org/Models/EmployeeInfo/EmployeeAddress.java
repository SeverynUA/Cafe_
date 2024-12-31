package brainacad.org.Models.EmployeeInfo;

import brainacad.org.Models.Employee.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeAddress
{
    private Long id;
    private Employee employee;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String country;
    private String postalCode;
}
