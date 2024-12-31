package brainacad.org.Dao.DB_Dao.EmployeeDAO;

import brainacad.org.Dao.DB_Dao.QueryExecutor;
import brainacad.org.Models.Employee.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDAO_Impl implements EmployeeDAO
{
    QueryExecutor queryExecutor = new QueryExecutor();

    @Override
    public int add(Employee employee) {
        String sql = "INSERT INTO Employee (first_name,last_name,middle_name,job_position_id,hire_date) VALUES (?,?,?,?,?)";

        if (!checkOFNull(employee)) {
            return 0;
        }

        return queryExecutor.executeInsertAndReturnId(sql, employee.getFirstName(),employee.getLastName(),employee.getMiddleName(),employee.getJobPosition().getId());
    }

    @Override
    public int update(Employee employee)
    {
        String sql = "UPDATE Employee SET (first_name,last_name,middle_name,job_position_id,hire_date) VALUES (?,?,?,?,?) WHERE id=?";

        if(!checkOFNull(employee))
        {
            return 0;
        }

        return queryExecutor.executeUpdate(sql, employee.getFirstName(),employee.getLastName(),employee.getMiddleName(),employee.getJobPosition().getId(),employee.getHireDate(),employee.getId());
    }

    @Override
    public int delete(int id)
    {
        String sql = "DELETE FROM Employee WHERE id=?";

        if(id <= 0)
        {
            return 0;
        }

        return queryExecutor.executeUpdate(sql,id);
    }

    @Override
    public void showAll()
    {
        String sql = "select * from Employee";

        try(ResultSet resultSet = queryExecutor.executeQuery(sql))
        {
            while(resultSet.next())
            {
                System.out.println(("\nEmployee id : " + resultSet.getInt("id")) +
                                    "\nFirstname : " + resultSet.getString("first_name") +
                                    "\nLastname : " + resultSet.getString("last_name")+
                                    "\nMiddle name : " + resultSet.getString("middle_name") +
                                    "\nJob position id : " + resultSet.getInt("job_position_id") +
                                    "\nHire date : " + resultSet.getInt("hire_date"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean checkOFNull(Employee employee)
    {
        return employee != null && employee.getFirstName() != null && employee.getLastName() != null && employee.getMiddleName() != null && employee.getHireDate() != null;
    }
}
