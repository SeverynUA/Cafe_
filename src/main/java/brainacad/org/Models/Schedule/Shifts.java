package brainacad.org.Models.Schedule;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import brainacad.org.Models.Employee.Employee;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Shifts
{
    private Long id;
    private String name;
    private String description;
    private LocalDate start_date;
    private LocalDate   end_Date;
    private LocalTime start_Time;
    private LocalTime  end_Time;
    private Employee employee;
}
