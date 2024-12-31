package brainacad.org.DbDAO.EmployeeContactDAOTest;

import brainacad.org.Dao.DB_Dao.EmpoloyeeContactDAO.EmployeeContactDAO_Impl;
import brainacad.org.Models.Employee.Employee;
import brainacad.org.Models.EmployeeInfo.EmployeeContact;
import brainacad.org.Models.EmployeeInfo.JobPosition;
import org.junit.jupiter.api.Test;

public class EmployeeContactDAOTest_Impl implements EmployeeContactDAO_Test
{
    private final EmployeeContactDAO_Impl employeeContactDAO = new EmployeeContactDAO_Impl();

    @Test
    @Override
    public void updatePhoneAndPostalCode_filterConfectioner() {
        String testPhone = "555-1234";
        String testPostalCode = "98765";
        JobPosition jobPosition = JobPosition.builder()
                .name("Confectioner")
                .build();

        int rowsAffected = employeeContactDAO.updatePhoneAndPostalCode_filterConfectioner(testPhone, testPostalCode, jobPosition);
        assert rowsAffected > 0 : "Failed to update phone and postal code for Confectioner. Rows affected: " + rowsAffected;
        System.out.println("updatePhoneAndPostalCode_filterConfectionerTest passed.");
    }

    @Test
    @Override
    public void updatePhone_filterBarista() {
        String testPhone = "555-5678";
        JobPosition jobPosition = JobPosition.builder()
                .name("Barista")
                .build();

        int rowsAffected = employeeContactDAO.updatePhone_filterBarista(testPhone, jobPosition);
        assert rowsAffected > 0 : "Failed to update phone number for Barista. Rows affected: " + rowsAffected;
        System.out.println("updatePhone_filterBaristaTest passed.");
    }


    @Test
    @Override
    public void addTest() {
        Employee employee = Employee.builder()
                .id(1L)
                .build();

        EmployeeContact contact = EmployeeContact.builder()
                .employee(employee)
                .phoneNumber("555-9999")
                .email("new.barista@example.com")
                .build();

        int contactId = employeeContactDAO.add(contact);
        assert contactId > 0 : "Failed to add EmployeeContact. ID is not greater than 0.";
        System.out.println("addTest passed for EmployeeContact.");
    }

    @Test
    @Override
    public void updateTest() {
        Employee employee = Employee.builder()
                .id(1L)
                .build();

        EmployeeContact contact = EmployeeContact.builder()
                .id(1L)
                .employee(employee)
                .phoneNumber("555-8888")
                .email("updated.alice@example.com")
                .build();

        int rowsAffected = employeeContactDAO.update(contact);
        assert rowsAffected == 1 : "Failed to update EmployeeContact. Rows affected: " + rowsAffected;
        System.out.println("updateTest passed for EmployeeContact.");
    }

    @Test
    @Override
    public void deleteTest() {
        Long contactIdToDelete = 1L;
        int rowsDeleted = employeeContactDAO.delete(contactIdToDelete.intValue());
        assert rowsDeleted == 1 : "Failed to delete EmployeeContact. Rows deleted: " + rowsDeleted;
        System.out.println("deleteTest passed for EmployeeContact.");
    }

    @Test
    @Override
    public void showAllTest() {
        System.out.println("EmployeeContacts:");
        employeeContactDAO.showAll(); // Перевірка візуально через консоль
        System.out.println("showAllTest completed for EmployeeContact.");
    }

    @Test
    @Override
    public void checkOFNullTest()
    {
        EmployeeContact employeeContact = null;
        boolean isNull = employeeContactDAO.checkOFNull(employeeContact);
        assert isNull : "Failed to identify null EmployeeContact.";
        System.out.println("checkOFNullTest passed.");
    }

}
