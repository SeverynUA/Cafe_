package brainacad.org.Models.Employee;

import java.time.LocalDate;

import brainacad.org.Models.EmployeeInfo.JobPosition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private JobPosition jobPosition;
    private LocalDate hireDate;
    private LocalDate dateOfBirth;

    public Employee(long l, String updatedAlice, String updatedJohnson, String s, JobPosition jobPosition, LocalDate of, LocalDate of1)
    {
        this.id = l;
        this.firstName = updatedAlice;
        this.lastName = updatedJohnson;
        this.middleName = s;
        this.jobPosition = jobPosition;
        this.hireDate = of;
        this.dateOfBirth = of1;
    }
}
