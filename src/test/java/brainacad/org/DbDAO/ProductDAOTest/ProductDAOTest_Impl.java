package brainacad.org.DbDAO.ProductDAOTest;
import brainacad.org.Dao.DB_Dao.ProductDAO.ProductDAO_impl;
import brainacad.org.Models.Categories.Category;
import brainacad.org.Models.Categories.SubCategory;
import brainacad.org.Models.Language.Language;
import brainacad.org.Models.Product.Product;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

public class ProductDAOTest_Impl implements ProductDAO_Test {
    private final ProductDAO_impl productDAO = new ProductDAO_impl();

    @Test
    @Override
    public void addTest() {
        Product product = Product.builder()
                .name("Test Products")
                .categoryId(1L)
                .subCategoryId(2L)
                .price(BigDecimal.valueOf(99.99))
                .build();

        int productId = productDAO.add(product);
        assert productId > 0 : "Failed to add Products. ID is not greater than 0.";
        System.out.println("addTest passed.");
    }

    @Test
    @Override
    public void updateTest() {
        Product product = Product.builder()
                .id(1L)
                .name("Updated Products")
                .categoryId(1L)
                .subCategoryId(2L)
                .price(BigDecimal.valueOf(149.99))
                .build();

        int rowsAffected = productDAO.update(product);
        assert rowsAffected == 1 : "Failed to update Products. Rows affected: " + rowsAffected;
        System.out.println("updateTest passed.");
    }

    @Test
    @Override
    public void deleteTest() {
        Long productIdToDelete = 1L;
        int rowsDeleted = productDAO.delete(productIdToDelete.intValue());
        assert rowsDeleted == 1 : "Failed to delete product. Rows deleted: " + rowsDeleted;
        System.out.println("deleteTest passed.");
    }

    @Test
    @Override
    public void showAllTest() {
        System.out.println("Products:");
        productDAO.showAll(); // Перевірка візуально через консоль
        System.out.println("showAllTest completed.");
    }

    @Test
    @Override
    public void checkOFNullTest() {
        Product product = null;
        boolean isNull = productDAO.checkOFNull(product);
        assert isNull : "Failed to identify null product.";
        System.out.println("checkOFNullTest passed.");
    }

    @Test
    @Override
    public void addDessertTest() {
        Product product = Product.builder()
                .name("Chocolate Cake")
                .categoryId(1L) // ID категорії "Desserts"
                .subCategoryId(2L) // ID підкатегорії "Cakes"
                .price(BigDecimal.valueOf(25.99))
                .build();

        int productId = productDAO.addDessert(product, new Category("Desserts"), new SubCategory("Cakes"));
        assert productId > 0 : "Failed to add dessert. ID is not greater than 0.";
        System.out.println("addDessertTest passed.");
    }

    @Test
    @Override
    public void showAllDessertsWithNamesTest() {
        Category category = new Category("Desserts");
        Language language = new Language("en", "English");

        System.out.println("Desserts:");
        productDAO.showAllDessertsWithNames(category, language);
        System.out.println("showAllDessertsWithNamesTest completed.");
    }

    @Test
    @Override
    public void updatePriceDrink_FilterTypeTest() {
        SubCategory subCategory = new SubCategory("Coffee");
        BigDecimal newPrice = BigDecimal.valueOf(3.99);

        int rowsUpdated = productDAO.updatePriceDrink_FilterType(subCategory, newPrice.doubleValue());
        assert rowsUpdated > 0 : "Failed to update drink prices. Rows updated: " + rowsUpdated;
        System.out.println("updatePriceDrink_FilterTypeTest passed.");
    }

    @Test
    @Override
    public void showAllDrinksWithNamesTest() {
        Category category = new Category("Drinks");
        Language language = new Language("en", "English");

        System.out.println("Drinks:");
        productDAO.showAllDrinksWithNames(category, language);
        System.out.println("showAllDrinksWithNamesTest completed.");
    }
}
