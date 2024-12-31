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
public class EmployeeContact
{
    private Long id;
    private Employee employee;;
    private String phoneNumber;
    private String email;
}
