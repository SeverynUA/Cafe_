package brainacad.org.DbDAO.OrderDAOTest;

import brainacad.org.Dao.DB_Dao.OrderDAO.OrderDAO_Impl;
import brainacad.org.Models.Customer.Customer;
import brainacad.org.Models.Employee.Employee;
import brainacad.org.Models.EmployeeInfo.JobPosition;
import brainacad.org.Models.Order.Order;
import brainacad.org.Models.OrderInfo.Statuses;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderDAOTest_Impl implements OrderDAOTest
{
    private final OrderDAO_Impl orderDAO_Impl = new OrderDAO_Impl();

    @Test
    @Override
    public void addTest()
    {
        JobPosition jobPosition = new JobPosition(1L, "Barista", "Prepares coffee and other beverages");
        Employee employee = new Employee(1L, "Alice", "Johnson", "C.", jobPosition, LocalDate.of(2022, 1, 15),LocalDate.of(2022, 1, 15));
        Customer customer = Customer.builder().id(2L).firstName("Alice").lastName("Smith").build();

        Order order = Order.builder()
                .orderDate(LocalDateTime.of(LocalDate.now(), LocalTime.now()))
                .orderAmount(BigDecimal.valueOf(50.00))
                .status(new Statuses(1L, "Pending", "Order is pending confirmation"))
                .employee(employee)
                .customer(customer)
                .build();

        int orderId = orderDAO_Impl.add(order);
        assertTrue(orderId > 0, "Failed to add Order. ID is not greater than 0.");
        System.out.println("addTest passed for Order.");
    }

    @Test
    @Override
    public void updateTest()
    {
        JobPosition jobPosition = new JobPosition(1L, "Barista", "Prepares coffee and other beverages");
        Employee employee = new Employee(1L, "Alice", "Johnson", "C.", jobPosition, LocalDate.of(2022, 1, 15),LocalDate.of(2022, 1, 15));
        Customer customer = Customer.builder().id(2L).firstName("Alice").lastName("Smith").build();

        Order order = Order.builder()
                .id(1L)
                .orderDate(LocalDateTime.of(2025, 1, 1, 14, 30))
                .orderAmount(BigDecimal.valueOf(75.00))
                .status(new Statuses(2L, "Completed", "Order has been completed"))
                .employee(employee)
                .customer(customer)
                .build();

        int rowsAffected = orderDAO_Impl.update(order);
        assertEquals(1, rowsAffected, "Failed to update Order. Rows affected: " + rowsAffected);
        System.out.println("updateTest passed for Order.");
    }


    @Test
    @Override
    public void deleteTest()
    {
        Long orderIdToDelete = 1L;
        int rowsDeleted = orderDAO_Impl.delete(orderIdToDelete.intValue());
        assert rowsDeleted == 1 : "Failed to delete Order. Rows deleted: " + rowsDeleted;
        System.out.println("deleteTest passed for Order.");
    }

    @Test
    @Override
    public void showAllTest()
    {
        System.out.println("Orders:");
        orderDAO_Impl.showAll();
        System.out.println("showAllTest completed for Orders.");
    }

    @Test
    @Override
    public void checkOFNullTest()
    {
        Order order = null;
        boolean isNull = orderDAO_Impl.checkOFNull(order);
        assert isNull : "Failed to identify null order as valid.";
        System.out.println("checkOFNullTest passed for null Order.");
    }

    @Test
    @Override
    public void showAllOrders_filterEmployeeTest()
    {
        JobPosition jobPosition = new JobPosition(1L, "Barista", "Prepares coffee and other beverages");
        Employee employee = new Employee(1L, "Alice", "Johnson", "C.", jobPosition, LocalDate.of(2022, 1, 15),LocalDate.of(2022, 1, 15));

        System.out.println("Orders filtered by Employee:");
        orderDAO_Impl.showAllOrders_filterEmployee(employee);
        System.out.println("showAllOrders_filterEmployeeTest completed for Employee.");
    }

    @Test
    @Override
    public void showAllOrders_filterCustomerTest()
    {
        Customer customer = Customer.builder().id(2L).firstName("Alice").lastName("Smith").build();

        System.out.println("Orders filtered by Customer:");
        orderDAO_Impl.showAllOrders_filterCustomer(customer);
        System.out.println("showAllOrders_filterCustomerTest completed for Order.");
    }

    @Test
    @Override
    public void ShowAllOrdersFilterDate_sortByDay_returnListOfOrders()
    {
        LocalDate localDate = LocalDate.of(2024,12,30);
        orderDAO_Impl. ShowAllOrders_filterDate(localDate);
        System.out.println("ShowAllOrdersFilterDate_sortByDay_returnListOfOrders completed for Order.");
    }

    @Test
    @Override
    public void ShowAverageAmountOrdersFilterDate_sortByDay_returnAverageAmountOfOrders()
    {
        LocalDate localDate = LocalDate.of(2024,12,30);
        orderDAO_Impl. ShowAverageAmountOrders_filterDate(localDate);
        System.out.println("ShowAverageAmountOrdersFilterDate_sortByDay_returnAverageAmountOfOrders completed for Order.");
    }

    @Test
    @Override
    public void ShowMaxAmountOrdersFilterDate_sortByDayAndFalse_returnAverageAmountOfOrdersWithoutCustomer()
    {
        LocalDate localDate = LocalDate.of(2024,12,30);
        orderDAO_Impl. ShowMaxAmountOrders_filterDate(localDate,false);
        System.out.println("ShowMaxAmountOrdersFilterDate_sortByDayAndFalse_returnAverageAmountOfOrdersWithoutCustomer completed for Order.");
    }

    @Test
    @Override
    public void ShowMaxAmountOrdersFilterDate_sortByDayAndTrue_returnAverageAmountOfOrdersWithCustomer()
    {
        LocalDate localDate = LocalDate.of(2024,12,30);
        orderDAO_Impl. ShowMaxAmountOrders_filterDate(localDate,true);
        System.out.println("ShowMaxAmountOrdersFilterDate_sortByDayAndFalse_returnAverageAmountOfOrdersWithoutCustomer completed for Order.");
    }
}
