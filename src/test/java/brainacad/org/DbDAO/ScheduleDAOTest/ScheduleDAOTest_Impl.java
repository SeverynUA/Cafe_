package brainacad.org.DbDAO.ScheduleDAOTest;

import brainacad.org.Dao.DB_Dao.ScheduleDAO.ScheduleDAO_Impl;
import brainacad.org.Models.Employee.Employee;
import brainacad.org.Models.EmployeeInfo.JobPosition;
import brainacad.org.Models.Schedule.WorkSchedules;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

public class ScheduleDAOTest_Impl implements ScheduleDAOTest
{
    ScheduleDAO_Impl scheduleDAO_Impl = new ScheduleDAO_Impl();

    @Test
    @Override
    public void addToCloseMondayTest()
    {
        Employee employee = new Employee(1L, "John", "Doe", "M.",
                new JobPosition(1L, "Barista", "Prepares coffee"), LocalDate.of(2022, 5, 15),LocalDate.of(2022, 1, 15));
        LocalTime startTime = LocalTime.of(9, 0);
        LocalTime endTime = LocalTime.of(17, 0);

        int scheduleId = scheduleDAO_Impl.addToCloseMonday(employee, startTime, endTime, false);

        assert scheduleId > 0 : "Failed to add schedule for Monday. ID is not greater than 0.";
        System.out.println("addToCloseMondayTest passed.");
    }


    @Test
    @Override
    public void updateToCloseTuesdayTest()
    {
        Employee employee = new Employee(1L, "John", "Doe", "M.",
                new JobPosition(1L, "Barista", "Prepares coffee"), LocalDate.of(2022, 5, 15),LocalDate.of(2022, 1, 15));
        WorkSchedules schedule = new WorkSchedules(1L, employee, LocalDate.of(2025, 1, 10), LocalDate.of(2025, 1, 10),
                LocalTime.of(8, 0), LocalTime.of(16, 0), false);

        int rowsAffected = scheduleDAO_Impl.updateToCloseTuesday(employee, schedule, true);

        assert rowsAffected == 1 : "Failed to update schedule for Tuesday. Rows affected: " + rowsAffected;
        System.out.println("updateToCloseTuesdayTest passed.");
    }


    @Test
    @Override
    public void deleteSchedules_filterDayTest()
    {
        LocalDate date = LocalDate.of(2025, 1, 15);

        scheduleDAO_Impl.add(new WorkSchedules(
                1L,
                new Employee(1L, "John", "Doe", "M.", new JobPosition(1L, "Barista", "Prepares coffee"), LocalDate.of(2022, 5, 15),LocalDate.of(2022, 1, 15)),
                LocalDate.of(2025, 1, 15),
                LocalDate.of(2025, 1, 15),
                LocalTime.of(9, 0),
                LocalTime.of(17, 0),
                false
        ));

        int rowsDeleted = scheduleDAO_Impl.deleteSchedules_filterDay(date);

        assert rowsDeleted > 0 : "No rows were deleted for the given date.";
        System.out.println("deleteSchedules_filterDayTest passed. Rows deleted: " + rowsDeleted);
    }



    @Test
    @Override
    public void deleteSchedules_filterDayTest_From_ToTest()
    {
        // Додання тестових даних
        scheduleDAO_Impl.add(new WorkSchedules(
                1L,
                new Employee(1L, "John", "Doe", "M.", new JobPosition(1L, "Barista", "Prepares coffee"), LocalDate.of(2022, 5, 15),LocalDate.of(2022, 1, 15)),
                LocalDate.of(2025, 1, 10),
                LocalDate.of(2025, 1, 15),
                LocalTime.of(9, 0),
                LocalTime.of(17, 0),
                false
        ));
        scheduleDAO_Impl.add(new WorkSchedules(
                2L,
                new Employee(2L, "Alice", "Smith", "L.", new JobPosition(2L, "Cashier", "Handles payments"), LocalDate.of(2023, 3, 1),LocalDate.of(2022, 1, 15)),
                LocalDate.of(2025, 1, 12),
                LocalDate.of(2025, 1, 14),
                LocalTime.of(10, 0),
                LocalTime.of(18, 0),
                true
        ));

        LocalDate startDate = LocalDate.of(2025, 1, 10);
        LocalDate endDate = LocalDate.of(2025, 1, 15);

        int rowsDeleted = scheduleDAO_Impl.deleteSchedules_filterDay(startDate, endDate);

        assert rowsDeleted > 0 : "No rows were deleted for the given date range.";
        System.out.println("deleteSchedules_filterDayTest_From_ToTest passed. Rows deleted: " + rowsDeleted);
    }




    @Test
    @Override
    public void showAll_filterDayTest()
    {
        LocalDate date = LocalDate.of(2025, 1, 15);

        System.out.println("Schedules for the day:");
        scheduleDAO_Impl.showAll(date);
        System.out.println("showAll_filterDayTest completed.");
    }


    @Test
    @Override
    public void addTest()
    {
        Employee employee = new Employee(2L, "Alice", "Smith", "L.",
                new JobPosition(2L, "Cashier", "Handles payments"), LocalDate.of(2023, 3, 1),LocalDate.of(2022, 1, 15));
        WorkSchedules schedule = new WorkSchedules(0L, employee, LocalDate.of(2025, 2, 1), LocalDate.of(2025, 2, 1),
                LocalTime.of(10, 0), LocalTime.of(18, 0), false);

        int scheduleId = scheduleDAO_Impl.add(schedule);
        assert scheduleId > 0 : "Failed to add schedule. ID is not greater than 0.";
        System.out.println("addTest passed.");
    }

    @Test
    @Override
    public void updateTest()
    {
        Employee employee = new Employee(2L, "Alice", "Smith", "L.",
                new JobPosition(2L, "Cashier", "Handles payments"), LocalDate.of(2023, 3, 1),LocalDate.of(2022, 1, 15));
        WorkSchedules schedule = new WorkSchedules(1L, employee, LocalDate.of(2025, 2, 2), LocalDate.of(2025, 2, 2),
                LocalTime.of(9, 0), LocalTime.of(17, 0), true);

        int rowsAffected = scheduleDAO_Impl.update(schedule);
        assert rowsAffected == 1 : "Failed to update schedule. Rows affected: " + rowsAffected;
        System.out.println("updateTest passed.");
    }

    @Test
    @Override
    public void deleteTest()
    {
        int scheduleId = 1;

        int rowsDeleted = scheduleDAO_Impl.delete(scheduleId);
        assert rowsDeleted == 1 : "Failed to delete schedule. Rows deleted: " + rowsDeleted;
        System.out.println("deleteTest passed.");
    }

    @Test
    @Override
    public void showAllTest()
    {
        System.out.println("All schedules:");
        scheduleDAO_Impl.showAll();
        System.out.println("showAllTest completed.");
    }

    @Test
    @Override
    public void checkOFNullTest()
    {
        WorkSchedules schedule = new WorkSchedules();
        schedule.setEmployee(null);
        schedule.setStart_workDay(null);
        schedule.setEnd_workDay(null);
        schedule.setStartTime(null);
        schedule.setEndTime(null);

        boolean isNull = scheduleDAO_Impl.checkOFNull(schedule);

        assert isNull : "Failed to identify invalid WorkSchedules.";
        System.out.println("checkOFNullTest passed for WorkSchedules.");
    }


}
