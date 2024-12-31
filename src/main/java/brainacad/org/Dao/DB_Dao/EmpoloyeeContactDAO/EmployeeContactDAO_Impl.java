package brainacad.org.Dao.DB_Dao.EmpoloyeeContactDAO;

import brainacad.org.Dao.DB_Dao.QueryExecutor;
import brainacad.org.Models.EmployeeInfo.EmployeeContact;
import brainacad.org.Models.EmployeeInfo.JobPosition;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeContactDAO_Impl implements EmployeeContactDAO
{
    QueryExecutor queryExecutor = new QueryExecutor();

    @Override
    public int add(EmployeeContact employeeContact)
    {
        String sql = "INSERT INTO Employee_contacts (employee_id,phone_number,email) VALUES (?,?,?)";
        if(checkOFNull(employeeContact))
        {
            throw new IllegalArgumentException("Employee_contacts cannot be null");
        }
        return queryExecutor.executeInsertAndReturnId(sql, employeeContact.getEmployee().getId(),employeeContact.getPhoneNumber(),employeeContact.getEmail());
    }

    @Override
    public int update(EmployeeContact employeeContact)
    {
        String sql = "UPDATE Employee_contacts SET employee_id = ?, phone_number = ?, email = ? WHERE id = ?";
        if(checkOFNull(employeeContact))
        {
            throw new IllegalArgumentException("Employee_contacts cannot be null");
        }
        return queryExecutor.executeUpdate(sql, employeeContact.getEmployee().getId(), employeeContact.getPhoneNumber(), employeeContact.getEmail(),employeeContact.getId());
    }

    @Override
    public int delete(int id)
    {
        String sql = "DELETE FROM Employee_contacts WHERE ID = ?";
        if(id <= 0)
        {
            return 0;
        }
        return queryExecutor.executeUpdate(sql,id);
    }

    @Override
    public void showAll()
    {
        String sql = "SELECT * FROM Employee_contacts";
        try(ResultSet resultSet = queryExecutor.executeQuery(sql))
        {
            while(resultSet.next())
            {
                System.out.println("\nEmployee_contacts id : " + resultSet.getInt("id") +
                                    "\nEmployee id : " + resultSet.getString("employee_id") +
                                    "\nPhone number : " + resultSet.getString("phone_number") +
                                    "\nEmail : " + resultSet.getString("email"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean checkOFNull(EmployeeContact employeeContact)
    {
        return employeeContact == null || (employeeContact.getEmployee().getId() == 0 && employeeContact.getId() == 0 && employeeContact.getPhoneNumber() == null && employeeContact.getEmail() == null);
    }

    @Override
    public int updatePhoneAndPostalCode_filterConfectioner(String phone, String postalCode, JobPosition jobPosition) {
        String sql = """
        UPDATE Employee_addresses
        SET postal_code = ?, address_line1 = ?
        WHERE employee_id = (
            SELECT e.id
            FROM Employees e
            JOIN Job_positions jp ON e.job_position_id = jp.id
            WHERE jp.name = ?
        )
    """;
        return queryExecutor.executeUpdate(sql, postalCode, phone, jobPosition.getName());
    }


    @Override
    public int updatePhone_filterBarista(String phone, JobPosition jobPosition) {
        String sql = """
        UPDATE Employee_contacts
        SET phone_number = ?
        WHERE employee_id = (
            SELECT e.id
            FROM Employees e
            JOIN Job_positions jp ON e.job_position_id = jp.id
            WHERE jp.name = ?
        )
    """;
        return queryExecutor.executeUpdate(sql, phone, jobPosition.getName());
    }


}
