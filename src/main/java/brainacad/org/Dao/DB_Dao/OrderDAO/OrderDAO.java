package brainacad.org.Dao.DB_Dao.OrderDAO;

import brainacad.org.Dao.CRUD_Interface;
import brainacad.org.Models.Categories.Category;
import brainacad.org.Models.Customer.Customer;
import brainacad.org.Models.Employee.Employee;
import brainacad.org.Models.Order.Order;
import brainacad.org.Models.Product.Product;

public interface OrderDAO  extends CRUD_Interface<Order>
{
    //ex4.3
    void showAllOrders_filterEmployee(Employee employee);
    //ex4.4
    void showAllOrders_filterCustomer(Customer customer);
}
