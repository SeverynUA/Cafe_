package brainacad.org.Dao.DB_Dao.JobPositionDAO;

import brainacad.org.Dao.DB_Dao.QueryExecutor;
import brainacad.org.Models.EmployeeInfo.JobPosition;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JobPositionDAO_Impl implements  JobPositionDAO {

    QueryExecutor queryExecutor = new QueryExecutor();

    @Override
    public int add(JobPosition jobPosition) {
        String sql = "INSERT INTO Job_positions (name, description) VALUES (?, ?)";

        if (checkOFNull(jobPosition)) {
            return 0;
        }

        return queryExecutor.executeInsertAndReturnId(sql,
                jobPosition.getName(),
                jobPosition.getDescription());
    }

    @Override
    public int update(JobPosition jobPosition) {
        String sql = "UPDATE Job_positions SET name=?, description=? WHERE id=?";

        if (checkOFNull(jobPosition)) {
            return 0;
        }

        return queryExecutor.executeUpdate(sql,
                jobPosition.getName(),
                jobPosition.getDescription(),
                jobPosition.getId());
    }

    @Override
    public int delete(int id) {
        String sql = "DELETE FROM Job_positions WHERE id=?";

        if (id <= 0) {
            return 0;
        }

        return queryExecutor.executeUpdate(sql, id);
    }

    @Override
    public void showAll() {
        String sql = "SELECT * FROM Job_positions";
        try (ResultSet resultSet = queryExecutor.executeQuery(sql)) {
            while (resultSet.next()) {
                System.out.println("Job Position id: " + resultSet.getLong("id") +
                        ", Name: " + resultSet.getString("name") +
                        ", Description: " + resultSet.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkOFNull(JobPosition jobPosition) {
        return jobPosition == null ||
                jobPosition.getName() == null ||
                jobPosition.getDescription() == null;
    }
}