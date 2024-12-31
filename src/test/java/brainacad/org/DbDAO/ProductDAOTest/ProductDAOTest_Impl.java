package brainacad.org.DbDAO.ProductDAOTest;
import brainacad.org.Busines.Proper.DatabaseUtil;
import brainacad.org.Busines.Proper.PropertyReader;
import brainacad.org.Dao.DB_Dao.ProductDAO.ProductDAO_impl;
import brainacad.org.Models.Categories.Category;
import brainacad.org.Models.Categories.SubCategory;
import brainacad.org.Models.Language.Language;
import brainacad.org.Models.Product.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Properties;;

import static org.junit.jupiter.api.Assertions.*;

class ProductDAOTest {

    private final ProductDAO_impl productDAO = new ProductDAO_impl();

    @Test
    void testAddProduct() {
        Product product = new Product();
        product.setPrice(BigDecimal.valueOf(10.99));

        int productId = productDAO.add(product);

        assertTrue(productId > 0, "Product ID should be greater than 0");
    }

    @Test
    void testUpdateProduct() {
        Product product = new Product();
        product.setId(1L); // Припустимо, у базі є продукт із ID 1
        product.setPrice(BigDecimal.valueOf(15.99));

        int rowsUpdated = productDAO.update(product);

        assertEquals(1, rowsUpdated, "Exactly 1 row should be updated");
    }

    @Test
    void testDeleteProduct() {
        int productId = 1; // Припустимо, у базі є продукт із ID 1

        int rowsDeleted = productDAO.delete(productId);

        assertEquals(1, rowsDeleted, "Exactly 1 row should be deleted");
    }

    @Test
    void testShowAllProducts() {
        productDAO.showAll();

        // Перевірте результати виводу вручну у консолі або інтегруйте логіку для автоматичної перевірки.
        assertTrue(true, "ShowAll executed successfully.");
    }

    @Test
    void testAddDessert() {
        Category category = new Category();
        category.setName("Desserts");

        SubCategory subCategory = new SubCategory();
        subCategory.setName("Cakes");

        Product product = new Product();
        product.setName("Test Cake");
        product.setPrice(BigDecimal.valueOf(20.99));

        int productId = productDAO.addDessert(product, category, subCategory);

        assertTrue(productId > 0, "Product ID should be greater than 0");
    }

    @Test
    void testUpdatePriceDrink_FilterType() {
        SubCategory subCategory = new SubCategory();
        subCategory.setId(2L); // Припустимо, у базі є підкатегорія з ID 2

        int rowsUpdated = productDAO.updatePriceDrink_FilterType(subCategory, 12.99);

        assertTrue(rowsUpdated > 0, "At least 1 row should be updated");
    }

    @Test
    void testShowAllDessertsWithNames() {
        Category category = new Category();
        category.setName("Desserts");

        Language language = new Language();
        language.setCode("en");

        productDAO.showAllDessertsWithNames(category, language);

        // Перевірте результати виводу вручну у консолі.
        assertTrue(true, "ShowAllDessertsWithNames executed successfully.");
    }

    @Test
    void testShowAllDrinksWithNames() {
        Category category = new Category();
        category.setName("Drinks");

        Language language = new Language();
        language.setCode("en");

        productDAO.showAllDrinksWithNames(category, language);

        // Перевірте результати виводу вручну у консолі.
        assertTrue(true, "ShowAllDrinksWithNames executed successfully.");
    }
}
