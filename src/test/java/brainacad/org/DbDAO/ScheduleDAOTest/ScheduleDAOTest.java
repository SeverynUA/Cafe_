package brainacad.org.DbDAO.ScheduleDAOTest;

import brainacad.org.DbDAO.CRUD_InterfaceTest;
import brainacad.org.Models.Employee.Employee;
import brainacad.org.Models.Schedule.WorkSchedules;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

public interface ScheduleDAOTest extends CRUD_InterfaceTest<WorkSchedules>
{
    @Test
    void addToCloseMondayTest();

    @Test
    void updateToCloseTuesdayTest();

    @Test
    void deleteSchedules_filterDayTest();

    @Test
    void deleteSchedules_filterDayTest_From_ToTest();

    @Test
    void showAll_filterDayTest();
}
