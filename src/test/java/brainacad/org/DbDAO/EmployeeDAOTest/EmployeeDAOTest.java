package brainacad.org.DbDAO.EmployeeDAOTest;

import brainacad.org.Dao.DB_Dao.EmployeeDAO.EmployeeDAO;
import brainacad.org.DbDAO.CRUD_InterfaceTest;
import org.junit.jupiter.api.Test;

public interface EmployeeDAOTest extends CRUD_InterfaceTest<EmployeeDAO>
{
    @Test
    void ShowEmployee_Id_returnEmployee();

    @Test
    void ShowTheMostYoung_ReturnEmployee();

    @Test
    void ShowTheMostOld_ReturnEmployee();
}
