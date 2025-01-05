package brainacad.org.Dao.DB_Dao.EmployeeDAO;

import brainacad.org.Dao.DB_Dao.QueryExecutor;
import brainacad.org.Models.Employee.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class EmployeeDAO_Impl implements EmployeeDAO
{
    QueryExecutor queryExecutor = new QueryExecutor();

    @Override
    public int add(Employee employee)
    {
        String sql = "INSERT INTO Employees (first_name,last_name,middle_name,job_position_id,hire_date,birthday) VALUES (?,?,?,?,?,?)";

        if (checkOFNull(employee))
        {
            throw new IllegalArgumentException("Employees cannot be null");
        }

        return queryExecutor.executeInsertAndReturnId(sql, employee.getFirstName(),employee.getLastName(),employee.getMiddleName(),employee.getJobPosition().getId(),employee.getHireDate(),employee.getDateOfBirth());
    }

    @Override
    public int update(Employee employee)
    {
        String sql = "UPDATE Employees SET first_name = ?, last_name = ?, middle_name = ?, job_position_id = ?, hire_date = ?, birthday = ? WHERE id = ?";

        if (checkOFNull(employee))
        {
            throw new IllegalArgumentException("Employees cannot be null");
        }

        return queryExecutor.executeUpdate(sql, employee.getFirstName(),employee.getLastName(),employee.getMiddleName(),employee.getJobPosition().getId(),employee.getHireDate(),employee.getDateOfBirth(),employee.getId());
    }

    @Override
    public int delete(int id)
    {
        String sql = "DELETE FROM Employees WHERE id=?";

        if(id <= 0)
        {
            return 0;
        }

        return queryExecutor.executeUpdate(sql,id);
    }

    @Override
    public void showAll()
    {
        String sql = "SELECT * FROM Employees";

        try(ResultSet resultSet = queryExecutor.executeQuery(sql))
        {
            while(resultSet.next())
            {
                System.out.println(("\nEmployee id : " + resultSet.getLong("id")) +
                                    "\nFirstname : " + resultSet.getString("first_name") +
                                    "\nLastname : " + resultSet.getString("last_name")+
                                    "\nMiddle name : " + resultSet.getString("middle_name") +
                                    "\nJob position id : " + resultSet.getInt("job_position_id") +
                                    "\nHire date : " + resultSet.getDate("hire_date") +
                                    "\nBirthday :" + resultSet.getDate("birthday"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean checkOFNull(Employee employee)
    {
        return employee == null || (employee.getFirstName() == null  || employee.getLastName() == null || employee.getMiddleName() == null || employee.getHireDate() == null || employee.getDateOfBirth() == null);
    }

    @Override
    public void ShowEmployee(int id)
    {
        String sql = "SELECT * FROM Employees WHERE id=?";

        try(ResultSet resultSet = queryExecutor.executeQuery(sql,id))
        {
            while(resultSet.next())
            {
                System.out.println(("\nEmployee id : " + resultSet.getInt("id")) +
                        "\nFirstname : " + resultSet.getString("first_name") +
                        "\nLastname : " + resultSet.getString("last_name")+
                        "\nMiddle name : " + resultSet.getString("middle_name") +
                        "\nJob position id : " + resultSet.getInt("job_position_id") +
                        "\nHire date : " + resultSet.getDate("hire_date") +
                        "\nBirthday :" + resultSet.getDate("birthday"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void ShowTheMostYoung()
    {
        LocalDate today = LocalDate.now();
        java.sql.Date sqlDate = java.sql.Date.valueOf(today);

        String sql = "SELECT * FROM Employees WHERE birthday <= ? ORDER BY birthday DESC LIMIT 1";

        try (ResultSet resultSet = queryExecutor.executeQuery(sql, sqlDate)) {
            if (resultSet.next())
            {
                System.out.println("\nEmployee id : " + resultSet.getInt("id") +
                        "\nFirstname : " + resultSet.getString("first_name") +
                        "\nLastname : " + resultSet.getString("last_name") +
                        "\nMiddle name : " + resultSet.getString("middle_name") +
                        "\nJob position id : " + resultSet.getInt("job_position_id") +
                        "\nHire date : " + resultSet.getDate("hire_date") +
                        "\nBirthday :" + resultSet.getDate("birthday"));
            } else
            {
                System.out.println("No employees found.");
            }
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void ShowTheMostOld()
    {
        LocalDate today = LocalDate.now();
        java.sql.Date sqlDate = java.sql.Date.valueOf(today);

        String sql = "SELECT * FROM Employees WHERE birthday <= ? ORDER BY birthday ASC LIMIT 1";

        try (ResultSet resultSet = queryExecutor.executeQuery(sql, sqlDate))
        {
            if (resultSet.next())
            {
                System.out.println("\nEmployee id : " + resultSet.getInt("id") +
                        "\nFirstname : " + resultSet.getString("first_name") +
                        "\nLastname : " + resultSet.getString("last_name") +
                        "\nMiddle name : " + resultSet.getString("middle_name") +
                        "\nBirthday :" + resultSet.getDate("birthday"));
            } else
            {
                System.out.println("No employees found.");
            }
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

}
