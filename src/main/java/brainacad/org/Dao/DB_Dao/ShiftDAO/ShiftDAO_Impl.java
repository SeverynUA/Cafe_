package brainacad.org.Dao.DB_Dao.ShiftDAO;

import brainacad.org.Dao.DB_Dao.QueryExecutor;
import brainacad.org.Models.Schedule.Shifts;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShiftDAO_Impl implements ShiftDAO
{
    QueryExecutor queryExecutor = new QueryExecutor();

    @Override
    public int add(Shifts shift) {
        String sql = "INSERT INTO Shifts (name, description, start_date, end_date, start_time, end_time, employee_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        if (checkOFNull(shift)) {
            return 0;
        }

        return queryExecutor.executeInsertAndReturnId(sql,
                shift.getName(),
                shift.getDescription(),
                shift.getStart_date(),
                shift.getEnd_Date(),
                shift.getStart_Time(),
                shift.getEnd_Time(),
                shift.getEmployee().getId());
    }

    @Override
    public int update(Shifts shift) {
        String sql = "UPDATE Shifts SET name=?, description=?, start_date=?, end_date=?, start_time=?, end_time=?, employee_id=? WHERE id=?";

        if (checkOFNull(shift)) {
            return 0;
        }

        return queryExecutor.executeUpdate(sql,
                shift.getName(),
                shift.getDescription(),
                shift.getStart_date(),
                shift.getEnd_Date(),
                shift.getStart_Time(),
                shift.getEnd_Time(),
                shift.getEmployee().getId(),
                shift.getId());
    }

    @Override
    public int delete(int id) {
        String sql = "DELETE FROM Shifts WHERE id=?";

        if (id <= 0) {
            return 0;
        }

        return queryExecutor.executeUpdate(sql, id);
    }

    @Override
    public void showAll() {
        String sql = "SELECT * FROM Shifts";
        try (ResultSet resultSet = queryExecutor.executeQuery(sql)) {
            while (resultSet.next()) {
                System.out.println("Shift id: " + resultSet.getLong("id") +
                        ", Name: " + resultSet.getString("name") +
                        ", Description: " + resultSet.getString("description") +
                        ", Start Date: " + resultSet.getDate("start_date") +
                        ", End Date: " + resultSet.getDate("end_date") +
                        ", Start Time: " + resultSet.getTime("start_time") +
                        ", End Time: " + resultSet.getTime("end_time") +
                        ", Employee ID: " + resultSet.getLong("employee_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkOFNull(Shifts shift) {
        return shift == null ||
                shift.getName() == null ||
                shift.getStart_date() == null ||
                shift.getEnd_Date() == null ||
                shift.getStart_Time() == null ||
                shift.getEnd_Time() == null ||
                shift.getEmployee().getId() <= 0;
    }
}
