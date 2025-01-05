package brainacad.org.Dao.DB_Dao.OrderDetailsDAO;

import brainacad.org.Dao.CRUD_Interface;
import brainacad.org.Dao.DB_Dao.OrderDAO.OrderDAO;
import brainacad.org.Models.Order.Order;
import brainacad.org.Models.OrderInfo.OrderDetails;
import brainacad.org.Models.Product.Product;

import java.time.LocalDate;

public interface OrderDetailsDAO extends CRUD_Interface<OrderDetails>
{
    int DeleteOrder_filterProductOfDessert(Product product , Order order);

    //ex3.3
    int AmountOrdersOfDesserts_filterDay(LocalDate localDate);

    //ex3.4
    int AmountOrdersOfDrinks_filterDay(LocalDate localDate);
}
