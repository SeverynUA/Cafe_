package brainacad.org.Dao.DB_Dao.ScheduleDAO;

import brainacad.org.Dao.DB_Dao.QueryExecutor;
import brainacad.org.Models.Employee.Employee;
import brainacad.org.Models.EmployeeInfo.JobPosition;
import brainacad.org.Models.Schedule.WorkSchedules;
import brainacad.org.Service.LocalDate.LocalDate_Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class ScheduleDAO_Impl implements ScheduleDAO
{
    QueryExecutor queryExecutor = new QueryExecutor();

    @Override
    public int add(WorkSchedules schedule)
    {
        String sql = "INSERT INTO Work_schedules (employee_id, start_work_day, end_work_day, start_time, end_time, is_holiday) VALUES (?, ?, ?, ?, ?, ?)";

        if (checkOFNull(schedule))
        {
            throw new IllegalArgumentException("Work_schedules cannot be null");
        }

        return queryExecutor.executeInsertAndReturnId(sql, schedule.getEmployee().getId(), schedule.getStart_workDay(), schedule.getEnd_workDay(), schedule.getStartTime(), schedule.getEndTime(), schedule.isHoliday());
    }

    @Override
    public int update(WorkSchedules schedule)
    {
        String sql = "UPDATE Work_schedules SET employee_id=?, start_work_day=?, end_work_day=?, start_time=?, end_time=?, is_holiday=? WHERE id=?";

        if (checkOFNull(schedule))
        {
            throw new IllegalArgumentException("Work_schedules cannot be null");
        }

        return queryExecutor.executeUpdate(sql, schedule.getEmployee().getId(), schedule.getStart_workDay(), schedule.getEnd_workDay(), schedule.getStartTime(), schedule.getEndTime(), schedule.isHoliday(), schedule.getId());
    }

    @Override
    public int delete(int id)
    {
        String sql = "DELETE FROM Work_schedules WHERE id=?";

        if (id <= 0) {
            throw new IllegalArgumentException("id cannot be id=<0");
        }

        return queryExecutor.executeUpdate(sql, id);
    }

    @Override
    public void showAll()
    {
        String sql = "SELECT * FROM Work_schedules";
        try (ResultSet resultSet = queryExecutor.executeQuery(sql))
        {
            while (resultSet.next()) {
                System.out.println("Schedule id: " + resultSet.getLong("id") +
                        ", Employee ID: " + resultSet.getLong("employee_id") +
                        ", Start Work Day: " + resultSet.getDate("start_work_day") +
                        ", End Work Day: " + resultSet.getDate("end_work_day") +
                        ", Start Time: " + resultSet.getTime("start_time") +
                        ", End Time: " + resultSet.getTime("end_time") +
                        ", Is Holiday: " + resultSet.getBoolean("is_holiday"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showAll(LocalDate date)
    {
        String sql = "SELECT * FROM Work_schedules WHERE start_work_day = ? AND end_work_day = ?";

        if (date == null)
        {
            throw new IllegalArgumentException("date cannot be null");
        }

        try (ResultSet resultSet = queryExecutor.executeQuery(sql, date, date))
        {
            while (resultSet.next())
            {
                System.out.println("Schedule id: " + resultSet.getLong("id") +
                        ", Employee ID: " + resultSet.getLong("employee_id") +
                        ", Start Work Day: " + resultSet.getDate("start_work_day") +
                        ", End Work Day: " + resultSet.getDate("end_work_day") +
                        ", Start Time: " + resultSet.getTime("start_time") +
                        ", End Time: " + resultSet.getTime("end_time") +
                        ", Is Holiday: " + resultSet.getBoolean("is_holiday"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void ShowAllByWeek(Employee employee) {

    }

    @Override
    public void ShowAllByWeek(JobPosition jobPosition) {

    }

    @Override
    public void ShowAllByWeek() {

    }

    @Override
    public boolean checkOFNull(WorkSchedules schedule)
    {
        return schedule == null || schedule.getEmployee() == null || schedule.getEmployee().getId() == null || schedule.getStart_workDay() == null || schedule.getEnd_workDay() == null || schedule.getStartTime() == null || schedule.getEndTime() == null;
    }


    @Override
    public int addToCloseMonday(Employee employee, LocalTime start_time, LocalTime end_time,boolean isHoliday)
    {
        String sql = "INSERT INTO Work_schedules (employee_id, start_work_day, end_work_day, start_time, end_time, is_holiday) VALUES (?, ?, ?, ?, ?, ?)";


        WorkSchedules schedule = new WorkSchedules();

        LocalDate_Service localDate_service = new LocalDate_Service();
        LocalDate monday = localDate_service.ReturnCloseDateByName("Monday");

        schedule.setStart_workDay(monday);  schedule.setEnd_workDay(monday);
        schedule.setStartTime(start_time);  schedule.setEndTime(end_time);
        schedule.setEmployee(employee);     schedule.setHoliday(isHoliday);

        if (checkOFNull(schedule))
        {
            throw new IllegalArgumentException("Work_schedules cannot be null");
        }

        return queryExecutor.executeInsertAndReturnId(sql, schedule.getEmployee().getId(), schedule.getStart_workDay(), schedule.getEnd_workDay(), schedule.getStartTime(), schedule.getEndTime(), schedule.isHoliday());
    }

    @Override
    public int updateToCloseTuesday(Employee employee, WorkSchedules schedule , boolean isHoliday)
    {
        String sql = "UPDATE Work_schedules SET employee_id=?, start_work_day=?, end_work_day=?, start_time=?, end_time=?, is_holiday=? WHERE id=?";

        LocalDate_Service localDate_service = new LocalDate_Service();
        LocalDate monday = localDate_service.ReturnCloseDateByName("Tuesday");

        schedule.setStart_workDay(monday);  schedule.setEnd_workDay(monday);    schedule.setHoliday(isHoliday);

        if (checkOFNull(schedule))
        {
            throw new IllegalArgumentException("Work_schedules cannot be null");
        }

        return queryExecutor.executeUpdate(sql, schedule.getEmployee().getId(), schedule.getStart_workDay(), schedule.getEnd_workDay(), schedule.getStartTime(), schedule.getEndTime(), schedule.isHoliday(), schedule.getId());
    }

    @Override
    public int deleteSchedules_filterDay(LocalDate date)
    {
        String sql = "DELETE FROM Work_schedules WHERE start_work_day = ? OR end_work_day = ?";

        if (date == null) {
            throw new IllegalArgumentException("date cannot be null");
        }

        return queryExecutor.executeUpdate(sql, date, date);
    }


    @Override
    public int deleteSchedules_filterDay(LocalDate start_date, LocalDate end_date)
    {
        if (start_date == null || end_date == null) {
            throw new IllegalArgumentException("Start date and end date cannot be null");
        }

        String sql = """
        DELETE FROM Work_schedules
        WHERE (start_work_day BETWEEN ? AND ?)
           OR (end_work_day BETWEEN ? AND ?)
           OR (start_work_day <= ? AND end_work_day >= ?)
    """;

        return queryExecutor.executeUpdate(sql, start_date, end_date, start_date, end_date, start_date, end_date);
    }
}
