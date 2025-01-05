package brainacad.org.Dao.DB_Dao.OrderDAO;

import brainacad.org.Dao.DB_Dao.CustomerDAO.CustomerDAO_impl;
import brainacad.org.Dao.DB_Dao.EmployeeDAO.EmployeeDAO;
import brainacad.org.Dao.DB_Dao.EmployeeDAO.EmployeeDAO_Impl;
import brainacad.org.Dao.DB_Dao.QueryExecutor;
import brainacad.org.Models.Customer.Customer;
import brainacad.org.Models.Employee.Employee;
import brainacad.org.Models.Order.Order;
import brainacad.org.Models.Product.Product;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class OrderDAO_Impl implements OrderDAO
{
    QueryExecutor queryExecutor = new QueryExecutor();

    @Override
    public int add(Order order)
    {
        String sql = "INSERT INTO Orders (order_date, order_amount,status_id, employee_id, customer_id) VALUES (?,?,?,?,?)";

        if (checkOFNull(order))
        {
            throw new IllegalArgumentException("Orders cannot be null");
        }

        return queryExecutor.executeInsertAndReturnId(sql, order.getOrderDate(), order.getOrderAmount(), order.getStatus().getId(), order.getEmployee().getId(), order.getCustomer().getId());
    }

    @Override
    public int update(Order order)
    {
        String sql = "UPDATE Orders SET order_date=?, order_amount=?, status_id=? , employee_id=? , customer_id = ? WHERE id=?";

        if (checkOFNull(order))
        {
            throw new IllegalArgumentException("Orders cannot be null");
        }

        return queryExecutor.executeUpdate(sql, order.getOrderDate(), order.getOrderAmount(), order.getStatus().getId(), order.getEmployee().getId(), order.getCustomer().getId(), order.getId());
    }

    @Override
    public int delete(int id) {
        String sql = "DELETE FROM Orders WHERE id=?";

        if (id <= 0) {
            return 0;
        }

        return queryExecutor.executeUpdate(sql, id);
    }

    @Override
    public void showAll()
    {
        String sql = "SELECT * FROM Orders";
        try (ResultSet resultSet = queryExecutor.executeQuery(sql))
        {
            while (resultSet.next())
            {
                System.out.println("Order id: " + resultSet.getLong("id") +
                        ", Order Date: " + resultSet.getTimestamp("order_date") +
                        ", Order Amount: " + resultSet.getBigDecimal("order_amount") +
                        ", Status ID: " + resultSet.getLong("status_id") +
                        ", Employee id : " + resultSet.getBigDecimal("employee_id") +
                        ", Customer ID: " + resultSet.getLong("customer_id"));;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkOFNull(Order order)
    {
        return order == null || order.getOrderDate() == null || order.getOrderAmount() == null || order.getOrderAmount().compareTo(BigDecimal.ZERO) <= 0 || order.getStatus() == null || order.getStatus().getId() <= 0 || order.getEmployee() == null || order.getEmployee().getId() <= 0 || order.getCustomer() == null || order.getCustomer().getId() <= 0;

    }

    @Override
    public void showAllOrders_filterEmployee(Employee employee)
    {
        String sql = "SELECT * FROM Orders WHERE employee_id=?";

        try (ResultSet resultSet = queryExecutor.executeQuery(sql , employee.getId()))
        {
            while (resultSet.next())
            {
                System.out.println("Order id: " + resultSet.getLong("id") +
                        ", Order Date: " + resultSet.getTimestamp("order_date") +
                        ", Order Amount: " + resultSet.getBigDecimal("order_amount") +
                        ", Status ID: " + resultSet.getLong("status_id") +
                        ", Employee ID: " + resultSet.getLong("employee_id")+
                        ", Employee ID: " + resultSet.getString("customer_id"));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void showAllOrders_filterCustomer(Customer customer)
    {
        String sql = "SELECT * FROM Orders WHERE customer_id=?";

        try (ResultSet resultSet = queryExecutor.executeQuery(sql , customer.getId()))
        {
            while (resultSet.next())
            {
                System.out.println("Order id: " + resultSet.getLong("id") +
                        ", Order Date: " + resultSet.getTimestamp("order_date") +
                        ", Order Amount: " + resultSet.getBigDecimal("order_amount") +
                        ", Status ID: " + resultSet.getLong("status_id") +
                        ", Employee ID: " + resultSet.getLong("employee_id")+
                        ", Employee ID: " + resultSet.getString("customer_id"));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void ShowAllOrders_filterDate(LocalDate localDate)
    {
        String sql = "SELECT * FROM Orders WHERE order_date=?";

        try (ResultSet resultSet = queryExecutor.executeQuery(sql , localDate))
        {
            while (resultSet.next())
            {
                System.out.println("Order id: " + resultSet.getLong("id") +
                        ", Order Date: " + resultSet.getDate("order_date") +
                        ", Order Amount: " + resultSet.getBigDecimal("order_amount") +
                        ", Status ID: " + resultSet.getLong("status_id") +
                        ", Employee ID: " + resultSet.getLong("employee_id")+
                        ", Employee ID: " + resultSet.getString("customer_id"));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void ShowAllCustomerAndEmployee_filterToday()
    {
        LocalDate localDate = LocalDate.now();

        String sql = "SELECT * FROM Orders WHERE order_date=?";

        String sqlEmployee = "SELECT employee_id FROM Employee  WHERE order_date=?";
        String sqlCustomer = "SELECT customer_id FROM Employee  WHERE order_date=?";

        int idEmployee = queryExecutor.executeQueryForId(sqlEmployee, localDate.toString());
        int idCustomer = queryExecutor.executeQueryForId(sqlCustomer, localDate.toString());

        if(idEmployee <= 0 && idCustomer <= 0)
        {
            ShowOrdersBySQL_FilterDate(sql,localDate);

            EmployeeDAO_Impl employeeDAO_Impl = new EmployeeDAO_Impl();
            employeeDAO_Impl.ShowEmployee(idEmployee);

            CustomerDAO_impl customerDAO_impl = new CustomerDAO_impl();
            customerDAO_impl.ShowCustomer(idCustomer);

            System.out.println("///////////////////////////////////////");
        }
        else
        {
            throw new IllegalArgumentException("idEmployee cannot be null or idCustomer cannot be null");
        }
    }



    @Override
    public void ShowAverageAmountOrders_filterDate(LocalDate localDate) {
        String sql = "SELECT AVG(order_amount) AS AverageValue FROM Orders WHERE order_date = ?";

        try (ResultSet resultSet = queryExecutor.executeQuery(sql, localDate)) {
            if (resultSet.next()) {
                double averageAmount = resultSet.getDouble("AverageValue");
                System.out.println("Average Order amount for date " + localDate + ": " + averageAmount);
            } else {
                System.out.println("No orders found for date: " + localDate);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error executing select query.", e);
        }
    }


    @Override
    public void ShowMaxAmountOrders_filterDate(LocalDate localDate , boolean withCustomer)
    {
        String sql = "SELECT * FROM Orders WHERE order_amount = " +
                "(SELECT MAX(order_amount) FROM Orders WHERE DATE(order_date) = ?)";

        ShowOrdersBySQL_FilterDate(sql,localDate);

        if (withCustomer)
        {
            CustomerDAO_impl customerDAO_impl = new CustomerDAO_impl();
            customerDAO_impl.ShowCustomer(queryExecutor.executeQueryForId(sql, localDate));
        }

    }


    private void ShowOrdersBySQL_FilterDate(String sql , LocalDate localDate)
    {
        try (ResultSet resultSet = queryExecutor.executeQuery(sql,localDate))
        {
            while (resultSet.next())
            {
                System.out.println("Order id: " + resultSet.getLong("id") +
                        ", Order Date: " + resultSet.getTimestamp("order_date") +
                        ", Order Amount: " + resultSet.getBigDecimal("order_amount") +
                        ", Status ID: " + resultSet.getLong("status_id") +
                        ", Employee ID: " + resultSet.getLong("employee_id")+
                        ", Customer ID: " + resultSet.getString("customer_id"));
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
