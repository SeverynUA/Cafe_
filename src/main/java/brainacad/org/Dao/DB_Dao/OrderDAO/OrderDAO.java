package brainacad.org.Dao.DB_Dao.OrderDAO;

import brainacad.org.Dao.CRUD_Interface;
import brainacad.org.Models.Categories.Category;
import brainacad.org.Models.Customer.Customer;
import brainacad.org.Models.Employee.Employee;
import brainacad.org.Models.Order.Order;
import brainacad.org.Models.Product.Product;

import java.time.LocalDate;

public interface OrderDAO  extends CRUD_Interface<Order>
{
    void showAllOrders_filterEmployee(Employee employee);
    void showAllOrders_filterCustomer(Customer customer);

    //ex3.2
    void ShowAllOrders_filterDate(LocalDate localDate);

    //4.1
    void ShowAllCustomerAndEmployee_filterToday();

    //4.2
    void ShowAverageAmountOrders_filterDate(LocalDate localDate);

    //4.3 , 4.4
    void ShowMaxAmountOrders_filterDate(LocalDate localDate , boolean withCustomer);
}
