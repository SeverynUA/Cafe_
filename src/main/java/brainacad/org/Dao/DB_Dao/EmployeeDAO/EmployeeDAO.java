package brainacad.org.Dao.DB_Dao.EmployeeDAO;

import brainacad.org.Dao.CRUD_Interface;
import brainacad.org.Models.Employee.Employee;

public interface EmployeeDAO extends CRUD_Interface<Employee>
{
    void ShowEmployee(int id);

    //2.1
    void ShowTheMostYoung();

    //2.2
    void ShowTheMostOld();

}
