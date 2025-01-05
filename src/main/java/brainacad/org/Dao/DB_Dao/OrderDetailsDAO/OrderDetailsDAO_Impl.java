package brainacad.org.Dao.DB_Dao.OrderDetailsDAO;

import brainacad.org.Dao.DB_Dao.OrderDAO.OrderDAO_Impl;
import brainacad.org.Dao.DB_Dao.ProductDAO.ProductDAO_impl;
import brainacad.org.Dao.DB_Dao.QueryExecutor;
import brainacad.org.Models.Order.Order;
import brainacad.org.Models.OrderInfo.OrderDetails;
import brainacad.org.Models.Product.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class OrderDetailsDAO_Impl implements OrderDetailsDAO
{
    QueryExecutor queryExecutor = new QueryExecutor();

    @Override
    public int add(OrderDetails orderDetails)
    {
        String sql = "INSERT INTO Order_Details (order_id, product_id, quantity) VALUES (?, ?, ?)";

        if (checkOFNull(orderDetails))
        {
            throw new IllegalArgumentException("OrderDetails cannot be null");
        }

        return queryExecutor.executeInsertAndReturnId(sql, orderDetails.getOrder().getId(), orderDetails.getProduct().getId(), orderDetails.getQuantity());
    }

    @Override
    public int update(OrderDetails orderDetails)
    {
        String sql = "UPDATE Order_Details SET order_id=?, product_id=?, quantity=? WHERE id=?";

        if (checkOFNull(orderDetails))
        {
            throw new IllegalArgumentException("OrderDetails cannot be null");
        }

        return queryExecutor.executeUpdate(sql,orderDetails.getOrder().getId(), orderDetails.getProduct().getId(), orderDetails.getQuantity(),orderDetails.getId());
    }

    @Override
    public int delete(int id)
    {
        String sql = "DELETE FROM Order_Details WHERE id=?";

        if (id <= 0) {
            throw new IllegalArgumentException("Id cannot be id<=0");
        }

        return queryExecutor.executeUpdate(sql, id);
    }

    @Override
    public void showAll()
    {
        String sql = "SELECT * FROM Order_Details";
        try (ResultSet resultSet = queryExecutor.executeQuery(sql))
        {
            while (resultSet.next())
            {

                System.out.println("Order_Details id: " + resultSet.getLong("id") +
                        ", Order id: " + resultSet.getTimestamp("order_id") +
                        ", Product id: " + resultSet.getBigDecimal("product_id") +
                        ", Quantity iD: " + resultSet.getLong("quantity"));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkOFNull(OrderDetails orderDetails)
    {
        return orderDetails == null || orderDetails.getId() == null || orderDetails.getOrder() == null || orderDetails.getOrder().getId() == null || orderDetails.getProduct() == null || orderDetails.getProduct().getId() == null || orderDetails.getQuantity() <= 0;
    }
    @Override
    public int DeleteOrder_filterProductOfDessert(Product product, Order order)
    {
        ProductDAO_impl productDAO_Impl = new ProductDAO_impl();
        OrderDAO_Impl orderDAO_Impl = new OrderDAO_Impl();

        if(productDAO_Impl.checkOFNull(product))
        {
            throw new IllegalArgumentException("Product cannot be null");
        }

        if(!ReturnCategoryByProduct(product).equals("Desserts"))
        {
            throw new IllegalArgumentException("Product is not a Dessert");
        }

        if(orderDAO_Impl.checkOFNull(order))
        {
            throw new IllegalArgumentException("Order cannot be null");
        }

        return orderDAO_Impl.delete(Math.toIntExact((order.getId())));
    }

    @Override
    public int AmountOrdersOfDesserts_filterDay(LocalDate localDate) {
        return 0;
    }

    @Override
    public int AmountOrdersOfDrinks_filterDay(LocalDate localDate) {
        return 0;
    }

    private String ReturnCategoryByProduct(Product product)
    {
        if (product == null || product.getCategoryId() == null)
        {
            throw new IllegalArgumentException("Product or its category ID cannot be null");
        }

        String sql = "SELECT name FROM Category WHERE id = ?";

        try (ResultSet resultSet = queryExecutor.executeQuery(sql, product.getCategoryId()))
        {
            if (resultSet.next())
            {
                return resultSet.getString("name");
            } else
            {
                throw new IllegalArgumentException("No category found for the given product");
            }
        } catch (SQLException e)
        {
            throw new RuntimeException("Error fetching category name for product", e);
        }
    }
}
