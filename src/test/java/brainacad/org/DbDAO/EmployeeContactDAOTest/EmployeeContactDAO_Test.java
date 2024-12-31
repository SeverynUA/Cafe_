package brainacad.org.DbDAO.EmployeeContactDAOTest;
import brainacad.org.DbDAO.CRUD_InterfaceTest;
import brainacad.org.Models.EmployeeInfo.EmployeeContact;
import org.junit.jupiter.api.Test;

public interface EmployeeContactDAO_Test extends CRUD_InterfaceTest<EmployeeContact>
{
    @Test
    void updatePhoneAndPostalCode_filterConfectioner();

    @Test
    void updatePhone_filterBarista();
}
