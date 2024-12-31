package brainacad.org.DbDAO.ProductDAOTest;

import brainacad.org.Dao.CRUD_Interface;
import brainacad.org.DbDAO.CRUD_InterfaceTest;
import brainacad.org.Models.Categories.Category;
import brainacad.org.Models.Categories.SubCategory;
import brainacad.org.Models.Language.Language;
import brainacad.org.Models.Product.Product;
import org.junit.jupiter.api.Test;

public interface ProductDAO_Test extends CRUD_InterfaceTest<Product>
{
    //ex2.1
    @Test
    void addDessertTest();

    //ex5.2
    @Test
    void showAllDessertsWithNamesTest();

    //ex3.1
    @Test
    void updatePriceDrink_FilterTypeTest();

    //ex5.1
    @Test
    void showAllDrinksWithNamesTest();
}
