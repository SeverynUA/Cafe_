package brainacad.org.DbDAO.EmployeeDAOTest;

import brainacad.org.Dao.DB_Dao.CustomerDAO.CustomerDAO_impl;
import brainacad.org.Dao.DB_Dao.EmployeeDAO.EmployeeDAO_Impl;
import brainacad.org.Models.Employee.Employee;
import brainacad.org.Models.EmployeeInfo.JobPosition;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmployeeDAOTest_Impl implements EmployeeDAOTest
{
    private final EmployeeDAO_Impl employeeDAO_Impl = new EmployeeDAO_Impl();

    @Test
    @Override
    public void ShowEmployee_Id_returnEmployee()
    {
        int testId = 1;
        employeeDAO_Impl.ShowEmployee(testId);
        System.out.println("ShowEmployee_Id_returnEmployee completed for Customer.");
    }

    @Test
    @Override
    public void ShowTheMostYoung_ReturnEmployee()
    {
        employeeDAO_Impl.ShowTheMostYoung();
        System.out.println("ShowTheMostYoung_ReturnEmployee completed for Customer.");
    }

    @Test
    @Override
    public void ShowTheMostOld_ReturnEmployee()
    {
        employeeDAO_Impl.ShowTheMostOld();
        System.out.println("ShowEmployee_Id_returnEmployee completed for Customer.");
    }

    @Test
    @Override
    public void addTest() {
        JobPosition jobPosition = new JobPosition(1L, "Barista", "Prepares coffee and other beverages");
        Employee employee = new Employee(null, "Alice", "Johnson", "C.", jobPosition, LocalDate.of(2022, 1, 15), LocalDate.of(2004, 3, 5));

        int employeeId = employeeDAO_Impl.add(employee);
        assertTrue(employeeId > 0, "Failed to add Employee. ID is not greater than 0.");
        System.out.println("addTest passed for Employee.");
    }

    @Test
    @Override
    public void updateTest() {
        JobPosition jobPosition = new JobPosition(1L, "Barista", "Prepares coffee and other beverages");
        Employee employee = new Employee(1L, "Updated Alice", "Updated Johnson", "U.", jobPosition, LocalDate.of(2023, 1, 1), LocalDate.of(2004, 3, 5));

        int rowsAffected = employeeDAO_Impl.update(employee);
        assertEquals(1, rowsAffected, "Failed to update Employee. Rows affected: " + rowsAffected);
        System.out.println("updateTest passed for Employee.");
    }

    @Test
    @Override
    public void deleteTest() {
        Long employeeIdToDelete = 1L;
        int rowsDeleted = employeeDAO_Impl.delete(employeeIdToDelete.intValue());
        assertEquals(1, rowsDeleted, "Failed to delete Employee. Rows deleted: " + rowsDeleted);
        System.out.println("deleteTest passed for Employee.");
    }

    @Test
    @Override
    public void showAllTest() {
        System.out.println("Employees:");
        employeeDAO_Impl.showAll();
        System.out.println("showAllTest completed for Employees.");
    }

    @Test
    @Override
    public void checkOFNullTest() {
        Employee employee = null;
        boolean isNull = employeeDAO_Impl.checkOFNull(employee);
        assertTrue(isNull, "Failed to identify null employee as valid.");
        System.out.println("checkOFNullTest passed for null Employee.");
    }
}
