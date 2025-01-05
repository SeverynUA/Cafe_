package brainacad.org.Dao.DB_Dao.CustomerDAO;

import brainacad.org.Dao.DB_Dao.QueryExecutor;
import brainacad.org.Models.Customer.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

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
               && customer.getMiddleName() == null && customer.getBirthDate() == null &&
               customer.getPhoneNumber() == null && customer.getSale() == null);
    }

    @Override
    public void ShowCustomer(int id)
    {
        String sql = "SELECT * FROM CUSTOMER WHERE id=?";
        try (ResultSet resultSet = queryExecutor.executeQuery(sql,id))
        {
            while (resultSet.next())
            {
                System.out.println("\nCUSTOMER id: " + resultSet.getString("id") +
                        "\nFirstName: " + resultSet.getString("firstName") +
                        "\nLastName : " + resultSet.getString("lastName") +
                        "\nMiddleName : " + resultSet.getString("middleName") +
                        "\nBirthDate : " + resultSet.getString("birthDate") +
                        "\nEmail : " + resultSet.getString("email") +
                        "\nPhone number : " + resultSet.getString("phoneNumber") +
                        "\nSale : " + resultSet.getString("sale"));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void ShowMinSale()
    {
        String sql = "SELECT * FROM CUSTOMER WHERE Sale = (SELECT MIN(Sale) FROM CUSTOMER)";

        try (ResultSet resultSet = queryExecutor.executeQuery(sql))
        {
            if (resultSet.next())
            {
                System.out.println("Customer with Minimum Sale:");
                System.out.println("ID: " + resultSet.getLong("id"));
                System.out.println("First Name: " + resultSet.getString("firstName"));
                System.out.println("Last Name: " + resultSet.getString("lastName"));
                System.out.println("Sale: " + resultSet.getDouble("Sale"));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void ShowMaxSale()
    {
        String sql = "SELECT * FROM CUSTOMER WHERE Sale = (SELECT MAX(Sale) FROM CUSTOMER)";

        try (ResultSet resultSet = queryExecutor.executeQuery(sql))
        {
            if (resultSet.next())
            {
                System.out.println("\nCustomer with Maximum Sale:");
                System.out.println("ID: " + resultSet.getLong("id"));
                System.out.println("First Name: " + resultSet.getString("firstName"));
                System.out.println("Last Name: " + resultSet.getString("lastName"));
                System.out.println("Sale: " + resultSet.getDouble("Sale"));
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void ShowAverageSale()
    {
        String sql = "SELECT AVG(Sale) AS AverageValue FROM CUSTOMER";

        try (ResultSet resultSet = queryExecutor.executeQuery(sql))
        {
            if (resultSet.next())
            {
                System.out.println("Average Sale: " + resultSet.getDouble("AverageValue"));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void ShowTodayBirthday() {
        LocalDate today = LocalDate.now();
        int day = today.getDayOfMonth();
        int month = today.getMonthValue();

        String sql = "SELECT * FROM CUSTOMER WHERE EXTRACT(DAY FROM birthDate) = ? AND EXTRACT(MONTH FROM birthDate) = ?";

        try (ResultSet resultSet = queryExecutor.executeQuery(sql, day, month)) {
            boolean hasResults = false;
            while (resultSet.next()) {
                hasResults = true;
                System.out.println("\nCUSTOMER id: " + resultSet.getString("id") +
                        "\nFirstName: " + resultSet.getString("firstName") +
                        "\nLastName : " + resultSet.getString("lastName") +
                        "\nMiddleName : " + resultSet.getString("middleName") +
                        "\nBirthDate : " + resultSet.getString("birthDate") +
                        "\nEmail : " + resultSet.getString("email") +
                        "\nPhone number : " + resultSet.getString("phoneNumber") +
                        "\nSale : " + resultSet.getString("sale"));
            }
            if (!hasResults) {
                System.out.println("No customers with birthdays today.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void ShowCustomer_Email_IsNUll()
    {
        String sql = "SELECT * FROM CUSTOMER WHERE email IS NULL";
        try (ResultSet resultSet = queryExecutor.executeQuery(sql))
        {
            while (resultSet.next())
            {
                System.out.println("Customer ID: " + resultSet.getLong("id"));
                System.out.println("First Name: " + resultSet.getString("firstName"));
                System.out.println("Last Name: " + resultSet.getString("lastName"));
                System.out.println("Email: " + resultSet.getString("email"));
                System.out.println("-----------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
