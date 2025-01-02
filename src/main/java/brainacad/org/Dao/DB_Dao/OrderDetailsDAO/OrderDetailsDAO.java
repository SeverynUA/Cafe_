package brainacad.org.Dao.DB_Dao.OrderDetailsDAO;

import brainacad.org.Dao.CRUD_Interface;
import brainacad.org.Dao.DB_Dao.OrderDAO.OrderDAO;
import brainacad.org.Models.Order.Order;
import brainacad.org.Models.OrderInfo.OrderDetails;
import brainacad.org.Models.Product.Product;

public interface OrderDetailsDAO extends CRUD_Interface<OrderDetails>
{
    //ex3.2
    int DeleteOrder_filterProductOfDessert(Product product , Order order);
}
