package brainacad.org.Dao.DB_Dao.CustomerDAO;

import brainacad.org.Dao.DB_Dao.QueryExecutor;
import brainacad.org.Models.Customer.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO_impl  implements  CustomerDAO
{
    QueryExecutor queryExecutor = new QueryExecutor();

    @Override
    public int add(Customer customer)
    {
        String sql = "INSERT INTO CUSTOMER (firstName,lastName,middleName,birthDate,email,phoneNumber,sale)VALUES (?,?,?,?,?,?,?)";

        if(checkOFNull(customer))
        {
            throw new IllegalArgumentException("Customer cannot be null");
        }

        return queryExecutor.executeInsertAndReturnId(sql, customer.getFirstName(),customer.getLastName(),customer.getMiddleName(),customer.getBirthDate(),customer.getEmail(),customer.getPhoneNumber(),customer.getSale());

    }

    @Override
    public int update(Customer customer)
    {
        String sql = "UPDATE CUSTOMER SET firstName = ?, lastName = ?, middleName = ?, birthDate = ?, email = ?, phoneNumber = ?, sale = ? WHERE id = ?";

        if(checkOFNull(customer))
        {
            throw new IllegalArgumentException("Customer cannot be null");
        }

        return queryExecutor.executeUpdate(sql, customer.getFirstName(), customer.getLastName(), customer.getMiddleName(), customer.getBirthDate(), customer.getEmail(), customer.getPhoneNumber(), customer.getSale(), customer.getId());
    }

    @Override
    public int delete(int id)
    {
        String sql = "DELETE FROM CUSTOMER WHERE id=?";
        return queryExecutor.executeUpdate(sql,id);
    }

    @Override
    public void showAll()
    {
        String sql = "SELECT * FROM CUSTOMER";
        try (ResultSet resultSet = queryExecutor.executeQuery(sql))
        {
            while (resultSet.next())
            {
                System.out.println("\nCUSTOMER id: " + resultSet.getString("id") +
                                    "\nFirstName: " + resultSet.getString("firstName") +
                                    "\nLastName : " + resultSet.getString("lastName") +
                                    "\nMiddleName : " + resultSet.getString("middleName") +
                                    "\nBirthDate : " + resultSet.getString("birthDate") +
                                    "\nEmail : " + resultSet.getString("email") +
                                    "\n Phone number : " + resultSet.getString("phoneNumber") +
                                    "\nSale : " + resultSet.getString("sale"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkOFNull(Customer customer)
    {
       return customer == null || ( customer.getId() == null && customer.getFirstName() == null && customer.getLastName() == null
               && customer.getMiddleName() == null && customer.getBirthDate() == null && customer.getEmail() == null &&
               customer.getPhoneNumber() == null && customer.getSale() == null);
    }
}
