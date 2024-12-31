package brainacad.org.Models.EmployeeInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobPosition
{
    private Long id;
    private String name;
    private String description;
}
