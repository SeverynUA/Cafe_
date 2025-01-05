package brainacad.org.DbDAO.OrderDAOTest;

import brainacad.org.DbDAO.CRUD_InterfaceTest;
import brainacad.org.Models.Order.Order;
import org.junit.jupiter.api.Test;

public interface OrderDAOTest extends CRUD_InterfaceTest<Order>
{
    @Test
    void showAllOrders_filterEmployeeTest();

    @Test
    void showAllOrders_filterCustomerTest();

    @Test
    void ShowAllOrdersFilterDate_sortByDay_returnListOfOrders();

    @Test
    void ShowAverageAmountOrdersFilterDate_sortByDay_returnAverageAmountOfOrders();

    @Test
    void ShowMaxAmountOrdersFilterDate_sortByDayAndFalse_returnAverageAmountOfOrdersWithoutCustomer();

    @Test
    void ShowMaxAmountOrdersFilterDate_sortByDayAndTrue_returnAverageAmountOfOrdersWithCustomer();
}
