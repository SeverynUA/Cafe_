package brainacad.org.Dao.DB_Dao.ScheduleDAO;

import brainacad.org.Dao.DB_Dao.QueryExecutor;
import brainacad.org.Models.Schedule.WorkSchedules;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ScheduleDAO_Impl implements ScheduleDAO
{
    QueryExecutor queryExecutor = new QueryExecutor();

    @Override
    public int add(WorkSchedules schedule) {
        String sql = "INSERT INTO Work_schedules (employee_id, start_work_day, end_work_day, start_time, end_time, is_holiday) VALUES (?, ?, ?, ?, ?, ?)";

        if (checkOFNull(schedule)) {
            return 0;
        }

        return queryExecutor.executeInsertAndReturnId(sql,
                schedule.getEmployee().getId(),
                schedule.getStart_workDay(),
                schedule.getEnd_workDay(),
                schedule.getStartTime(),
                schedule.getEndTime(),
                schedule.isHoliday());
    }

    @Override
    public int update(WorkSchedules schedule) {
        String sql = "UPDATE Work_schedules SET employee_id=?, start_work_day=?, end_work_day=?, start_time=?, end_time=?, is_holiday=? WHERE id=?";

        if (checkOFNull(schedule)) {
            return 0;
        }

        return queryExecutor.executeUpdate(sql,
                schedule.getEmployee().getId(),
                schedule.getStart_workDay(),
                schedule.getEnd_workDay(),
                schedule.getStartTime(),
                schedule.getEndTime(),
                schedule.isHoliday(),
                schedule.getId());
    }

    @Override
    public int delete(int id) {
        String sql = "DELETE FROM Work_schedules WHERE id=?";

        if (id <= 0) {
            return 0;
        }

        return queryExecutor.executeUpdate(sql, id);
    }

    @Override
    public void showAll() {
        String sql = "SELECT * FROM Work_schedules";
        try (ResultSet resultSet = queryExecutor.executeQuery(sql)) {
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
    public boolean checkOFNull(WorkSchedules schedule) {
        return schedule == null ||
                schedule.getEmployee().getId() <= 0 ||
                schedule.getStart_workDay() == null ||
                schedule.getEnd_workDay() == null ||
                schedule.getStartTime() == null ||
                schedule.getEndTime() == null;
    }
}
