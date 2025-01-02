package brainacad.org.DbDAO.OrderDetailsDAOTest;

import brainacad.org.DbDAO.CRUD_InterfaceTest;
import brainacad.org.Models.Order.Order;
import brainacad.org.Models.OrderInfo.OrderDetails;
import brainacad.org.Models.Product.Product;
import org.junit.jupiter.api.Test;

public interface OrderDetailsDEOTest extends CRUD_InterfaceTest<OrderDetails>
{
    @Test
    void DeleteOrder_filterProductOfDessert();
}
