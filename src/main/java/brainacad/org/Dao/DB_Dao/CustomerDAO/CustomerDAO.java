package brainacad.org.Dao.DB_Dao.CustomerDAO;

import brainacad.org.Dao.CRUD_Interface;
import brainacad.org.Models.Customer.Customer;

public interface CustomerDAO extends CRUD_Interface<Customer>
{
    void ShowCustomer(int id);

    //1.3
    void ShowMinSale();

    //1.4
    void ShowMaxSale();

    //1.5
    void ShowAverageSale();

    //2.3
    void ShowTodayBirthday();

    //2.4
    void ShowCustomer_Email_IsNUll();
}
