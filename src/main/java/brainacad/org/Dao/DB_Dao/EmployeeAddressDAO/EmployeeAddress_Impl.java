package brainacad.org.Dao.DB_Dao.EmployeeAddressDAO;

import brainacad.org.Dao.DB_Dao.QueryExecutor;
import brainacad.org.Models.EmployeeInfo.EmployeeAddress;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeAddress_Impl implements EmployeeAddressDAO
{
    QueryExecutor queryExecutor = new QueryExecutor();

    @Override
    public int add(EmployeeAddress employeeAddress)
    {
        String sql = "INSERT INTO Employee_addresses(employee_id,address_line1,address_line2,city,country,postal_code) VALUES(?,?,?,?,?,?)";
        if(checkOFNull(employeeAddress))
        {
            throw new IllegalArgumentException("EmployeeAddress cannot be null");
        }
        return queryExecutor.executeInsertAndReturnId(sql, employeeAddress.getEmployee().getId(),employeeAddress.getAddressLine1(),employeeAddress.getAddressLine2(),employeeAddress.getCity(),employeeAddress.getCountry(),employeeAddress.getPostalCode());
    }

    @Override
    public int update(EmployeeAddress employeeAddress)
    {
        String sql = "UPDATE EmployeeAddress SET (employee_id,address_line1,address_line2,city,country,postal_code) VALUES(?,?,?,?,?,?) WHERE id=?";
        if(checkOFNull(employeeAddress))
        {
            throw new IllegalArgumentException("EmployeeAddress cannot be null");
        }
        return queryExecutor.executeUpdate(sql,employeeAddress.getEmployee().getId(),employeeAddress.getAddressLine1(),employeeAddress.getAddressLine2(),employeeAddress.getCity(),employeeAddress.getCountry(),employeeAddress.getPostalCode(),employeeAddress.getId());
    }

    @Override
    public int delete(int id)
    {
        String sql = "DELETE FROM EmployeeAddress WHERE id=?";

        if(id<=0)
        {
            return 0;
        }
        return queryExecutor.executeUpdate(sql,id);
    }

    @Override
    public void showAll()
    {
        String sql = "SELECT * FROM EmployeeAddress";
        try(ResultSet resultSet = queryExecutor.executeQuery(sql))
        {
            while(resultSet.next())
            {
                System.out.println("\nEmployeeAddress id : " + resultSet.getInt("id") +
                                    "\nEmployee id : " + resultSet.getInt("employee_id") +
                                    "\nAddress line : " + resultSet.getString("address_line1") +
                                    "\naddress_line2 : " + resultSet.getString("address_line2") +
                                    "\ncity : " + resultSet.getString("city") +
                                    "\ncountry : " + resultSet.getString("country") +
                                    "\npostal_code : " + resultSet.getString("postal_code"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean checkOFNull(EmployeeAddress employeeAddress)
    {
        return employeeAddress != null && employeeAddress.getId() != null && employeeAddress.getEmployee().getId() != null && employeeAddress.getAddressLine1() != null && employeeAddress.getAddressLine2() != null && employeeAddress.getCity() != null && employeeAddress.getCountry() != null && employeeAddress.getPostalCode() != null;
    }
}
