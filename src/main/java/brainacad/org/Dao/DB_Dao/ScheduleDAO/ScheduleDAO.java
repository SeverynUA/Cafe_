package brainacad.org.Dao.DB_Dao.ScheduleDAO;

import brainacad.org.Dao.CRUD_Interface;
import brainacad.org.Models.Employee.Employee;
import brainacad.org.Models.Schedule.WorkSchedules;

import java.time.LocalDate;
import java.time.LocalTime;

public interface ScheduleDAO extends CRUD_Interface<WorkSchedules>
{
    //ex1.3
    int addToCloseMonday(Employee employee, LocalTime start_time , LocalTime end_time ,boolean isHoliday);

    //ex2.1
    int updateToCloseTuesday(Employee employee,WorkSchedules schedule ,boolean isHoliday);

    //3.3
    int deleteSchedules_filterDay(LocalDate date);

    //3.4
    int deleteSchedules_filterDay(LocalDate start_date, LocalDate end_date);

    //4.3
    void showAll(LocalDate date);
}
