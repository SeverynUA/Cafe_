package brainacad.org.DbDAO.OrderDetailsDAOTest;

import brainacad.org.Dao.DB_Dao.OrderDetailsDAO.OrderDetailsDAO_Impl;
import brainacad.org.Models.Customer.Customer;
import brainacad.org.Models.Employee.Employee;
import brainacad.org.Models.EmployeeInfo.JobPosition;
import brainacad.org.Models.Order.Order;
import brainacad.org.Models.OrderInfo.OrderDetails;
import brainacad.org.Models.OrderInfo.Statuses;
import brainacad.org.Models.Product.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class OrderDetailsDAOTest_Impl implements OrderDetailsDEOTest
{
    OrderDetailsDAO_Impl orderDetailsDAO_Impl = new OrderDetailsDAO_Impl();

    @Test
    @Override
    public void addTest()
    {
        Product product = new Product();
        product.setId(4L);
        product.setName("Chocolate Cookies");
        product.setCategoryId(3L);
        product.setSubCategoryId(4L);
        product.setPrice(BigDecimal.valueOf(3.99));
        Order order = new Order(1L, LocalDateTime.now(), BigDecimal.valueOf(25.00), new Statuses(1L, "Pending", ""), new Employee(), new Customer());
        OrderDetails orderDetails = new OrderDetails(1L, order, product, 2);

        int orderDetailsId = orderDetailsDAO_Impl.add(orderDetails);
        assert orderDetailsId > 0 : "Failed to add OrderDetails. ID is not greater than 0.";
        System.out.println("addTest passed for OrderDetails.");
    }

    @Test
    @Override
    public void updateTest()
    {
        Product product = new Product();
        product.setId(4L);
        product.setName("Chocolate Cookies");
        product.setCategoryId(3L);
        product.setSubCategoryId(4L);
        product.setPrice(BigDecimal.valueOf(3.99));
        Order order = new Order(1L, LocalDateTime.of(2025, 1, 1, 14, 30), BigDecimal.valueOf(30.00), new Statuses(2L, "Completed", ""), new Employee(), new Customer());
        OrderDetails orderDetails = new OrderDetails(1L, order, product, 3);

        int rowsAffected = orderDetailsDAO_Impl.update(orderDetails);
        assert rowsAffected == 1 : "Failed to update OrderDetails. Rows affected: " + rowsAffected;
        System.out.println("updateTest passed for OrderDetails.");
    }

    @Test
    @Override
    public void deleteTest()
    {
        int orderDetailsId = 1;
        int rowsDeleted = orderDetailsDAO_Impl.delete(orderDetailsId);
        assert rowsDeleted == 1 : "Failed to delete OrderDetails. Rows deleted: " + rowsDeleted;
        System.out.println("deleteTest passed for OrderDetails.");
    }

    @Test
    @Override
    public void showAllTest()
    {
        System.out.println("Order Details:");
        orderDetailsDAO_Impl.showAll();
        System.out.println("showAllTest completed for OrderDetails.");
    }

    @Test
    @Override
    public void checkOFNullTest()
    {
        OrderDetails orderDetails = new OrderDetails(0L, null, null, 0);

        boolean isNull = orderDetailsDAO_Impl.checkOFNull(orderDetails);

        assert isNull : "Failed to identify invalid OrderDetails.";
        System.out.println("checkOFNullTest passed for OrderDetails.");
    }

    @Test
    @Override
    public void DeleteOrder_filterProductOfDessert()
    {
        Product product = new Product();
        product.setId(4L);
        product.setName("Chocolate Cookies");
        product.setCategoryId(3L);
        product.setSubCategoryId(4L);
        product.setPrice(BigDecimal.valueOf(3.99));

        Order order = new Order();
        order.setId(1L);
        order.setOrderDate(LocalDateTime.now());
        order.setOrderAmount(BigDecimal.valueOf(12.00));
        order.setStatus(new Statuses(1L, "Pending", "Order is pending"));
        order.setEmployee(new Employee(1L, "Alice", "Johnson", "C.",
                new JobPosition(1L, "Barista", "Prepares coffee"), LocalDate.of(2022, 1, 15),LocalDate.of(2022, 1, 15)));
        order.setCustomer(new Customer(1L, "John", "Doe", "A.", LocalDate.of(1990, 5, 15), "john.doe@example.com", "555-1234", BigDecimal.valueOf(10.00)));

        int rowsDeleted = orderDetailsDAO_Impl.DeleteOrder_filterProductOfDessert(product, order);

        assert rowsDeleted == 1 : "Failed to delete Order associated with Dessert. Rows deleted: " + rowsDeleted;
        System.out.println("DeleteOrder_filterProductOfDessert test passed.");
    }

}
