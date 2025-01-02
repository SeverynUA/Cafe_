package brainacad.org.Dao.DB_Dao.ProductDAO;

import brainacad.org.Dao.CRUD_Interface;
import brainacad.org.Models.Categories.Category;
import brainacad.org.Models.Categories.SubCategory;
import brainacad.org.Models.Language.Language;
import brainacad.org.Models.Product.Product;

public interface ProductDAO extends CRUD_Interface<Product>
{
    int addDessert(Product product , Category category , SubCategory subCategory);

    void showAllDessertsWithNames(Category category , Language language);

    int updatePriceDrink_FilterType(SubCategory subCategory , double newPrice);

    void showAllDrinksWithNames(Category category , Language language);
}
