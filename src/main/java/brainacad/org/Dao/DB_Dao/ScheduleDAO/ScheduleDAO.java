package brainacad.org.Dao.DB_Dao.ScheduleDAO;

import brainacad.org.Dao.CRUD_Interface;
import brainacad.org.Models.Employee.Employee;
import brainacad.org.Models.EmployeeInfo.JobPosition;
import brainacad.org.Models.Schedule.WorkSchedules;

import java.time.LocalDate;
import java.time.LocalTime;

public interface ScheduleDAO extends CRUD_Interface<WorkSchedules>
{
    int addToCloseMonday(Employee employee, LocalTime start_time , LocalTime end_time ,boolean isHoliday);
    int updateToCloseTuesday(Employee employee,WorkSchedules schedule ,boolean isHoliday);
    int deleteSchedules_filterDay(LocalDate date);
    int deleteSchedules_filterDay(LocalDate start_date, LocalDate end_date);
    void showAll(LocalDate date);

    //ex5.1
    void ShowAllByWeek(Employee employee);

    //ex5.2
    void ShowAllByWeek(JobPosition jobPosition);

    //ex5.3
    void ShowAllByWeek();
}
