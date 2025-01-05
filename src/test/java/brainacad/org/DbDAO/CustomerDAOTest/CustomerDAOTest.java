package brainacad.org.DbDAO.CustomerDAOTest;

import brainacad.org.DbDAO.CRUD_InterfaceTest;
import brainacad.org.Models.Customer.Customer;
import org.junit.jupiter.api.Test;

public interface CustomerDAOTest extends CRUD_InterfaceTest<Customer>
{
    @Test
    void ShowCustomer_Id_returnCustomer();

    @Test
    void ShowMinSale_showMinFromList_returnCustomer();

    @Test
    void ShowMinSale_showMaxFromList_returnCustomer();

    @Test
    void ShowMinSale_showAvgFromList_returnAverage();

    @Test
    void ShowTodayBirthday_showTodayBirthday_returnListOfCustomer();

    @Test
    void ShowCustomerEmailIsNUll_showEmailNull_returnListOfCustomer();
}
