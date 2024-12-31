package brainacad.org.Models.Schedule;

import brainacad.org.Models.Employee.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkSchedules
{
    private Long id;
    private Employee employee;
    private LocalDate start_workDay;
    private LocalDate end_workDay;
    private LocalTime startTime;
    private LocalTime  endTime;
    private boolean isHoliday;
}
