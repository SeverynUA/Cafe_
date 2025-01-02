package brainacad.org.DbDAO.OrderDAOTest;

import brainacad.org.DbDAO.CRUD_InterfaceTest;
import brainacad.org.Models.Customer.Customer;
import brainacad.org.Models.Employee.Employee;
import brainacad.org.Models.Order.Order;
import org.junit.jupiter.api.Test;

public interface OrderDAOTest extends CRUD_InterfaceTest<Order>
{
    @Test
    void showAllOrders_filterEmployeeTest();

    @Test
    void showAllOrders_filterCustomerTest();
}
