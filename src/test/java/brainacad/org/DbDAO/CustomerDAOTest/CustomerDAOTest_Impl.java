package brainacad.org.DbDAO.CustomerDAOTest;
import brainacad.org.Dao.DB_Dao.CustomerDAO.CustomerDAO_impl;
import brainacad.org.Models.Customer.Customer;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CustomerDAOTest_Impl implements CustomerDAOTest
{
    private final CustomerDAO_impl customerDAO_Impl = new CustomerDAO_impl();

    @Test
    @Override
    public void addTest()
    {
        Customer customer = Customer.builder()
                .firstName("Alice")
                .lastName("Brown")
                .middleName("C.")
                .birthDate(LocalDate.of(1992, 7, 10))
                .email("alice.brown@example.com")
                .phoneNumber("555-9876")
                .sale(BigDecimal.valueOf(15.00))
                .build();

        int customerId = customerDAO_Impl.add(customer);
        assert customerId > 0 : "Failed to add Customer. ID is not greater than 0.";
        System.out.println("addTest passed for Customer.");
    }

    @Test
    @Override
    public void updateTest()
    {
        Customer customer = Customer.builder()
                .id(1L)
                .firstName("Updated John")
                .lastName("Updated Doe")
                .middleName("U.")
                .birthDate(LocalDate.of(1990, 5, 15))
                .email("updated.john.doe@example.com")
                .phoneNumber("555-4321")
                .sale(BigDecimal.valueOf(20.00))
                .build();

        int rowsAffected = customerDAO_Impl.update(customer);
        assert rowsAffected == 1 : "Failed to update Customer. Rows affected: " + rowsAffected;
        System.out.println("updateTest passed for Customer.");
    }

    @Test
    @Override
    public void deleteTest()
    {
        Long customerIdToDelete = 2L;
        int rowsDeleted = customerDAO_Impl.delete(customerIdToDelete.intValue());
        assert rowsDeleted == 1 : "Failed to delete Customer. Rows deleted: " + rowsDeleted;
        System.out.println("deleteTest passed for Customer.");
    }

    @Test
    @Override
    public void showAllTest()
    {
        System.out.println("Customers:");
        customerDAO_Impl.showAll();
        System.out.println("showAllTest completed for Customer.");
    }

    @Test
    @Override
    public void checkOFNullTest()
    {
        Customer customer = null;
        boolean isNull = customerDAO_Impl.checkOFNull(customer);
        assert isNull : "Failed to identify null customer as valid.";
        System.out.println("checkOFNullTest passed for null Customer.");
    }

}
