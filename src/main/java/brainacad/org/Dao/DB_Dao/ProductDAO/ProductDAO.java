package brainacad.org.Dao.DB_Dao.ProductDAO;

import brainacad.org.Dao.CRUD_Interface;
import brainacad.org.Models.Categories.Category;
import brainacad.org.Models.Categories.SubCategory;
import brainacad.org.Models.Language.Language;
import brainacad.org.Models.Product.Product;

public interface ProductDAO extends CRUD_Interface<Product>
{
    //ex2.1
    int addDessert(Product product , Category category , SubCategory subCategory);

    //ex5.2
    void showAllDessertsWithNames(Category category , Language language);

    //ex3.1
    int updatePriceDrink_FilterType(SubCategory subCategory , double newPrice);

    //ex5.1
    void showAllDrinksWithNames(Category category , Language language);
}
