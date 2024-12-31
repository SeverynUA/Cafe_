package brainacad.org.Dao.DB_Dao.EmpoloyeeContactDAO;

import brainacad.org.Dao.CRUD_Interface;
import brainacad.org.Models.EmployeeInfo.EmployeeContact;
import brainacad.org.Models.EmployeeInfo.JobPosition;

public interface EmployeeContactDAO extends CRUD_Interface<EmployeeContact>
{
    //ex3.2
    int updatePhoneAndPostalCode_filterConfectioner(String phone, String postalCode, JobPosition jobPosition);

    //ex3.3
    int updatePhone_filterBarista(String phone , JobPosition jobPosition);
}
