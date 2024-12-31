package brainacad.org.Dao.DB_Dao.ProductDAO;

import brainacad.org.Dao.DB_Dao.QueryExecutor;
import brainacad.org.Models.Categories.Category;
import brainacad.org.Models.Categories.SubCategory;
import brainacad.org.Models.Language.Language;
import brainacad.org.Models.Product.Product;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDAO_impl implements ProductDAO
{
    private final QueryExecutor queryExecutor = new QueryExecutor();

    @Override
    public int add(Product product)
    {
        String sql = "INSERT INTO Products (name,category_id,subCategory_id,price) VALUES (?,?,?,?)";
        if (checkOFNull(product))
        {
            throw new IllegalArgumentException("Products or price cannot be null");
        }
        return queryExecutor.executeInsertAndReturnId(sql, product.getName(),product.getCategoryId(),product.getSubCategoryId(),product.getPrice());
    }

    @Override
    public int update(Product product)
    {
        String sql = "UPDATE Products SET price = ? WHERE id = ?";
        if (product == null || product.getPrice() == null || product.getId() == null)
        {
            throw new IllegalArgumentException("Product, price or ID cannot be null");
        }
        return queryExecutor.executeUpdate(sql, product.getPrice(), product.getId());
    }

    @Override
    public int delete(int id)
    {
        String sql = "DELETE FROM Products WHERE id = ?";
        return queryExecutor.executeUpdate(sql, id);
    }

    @Override
    public void showAll()
    {
        String sql = "SELECT id, price FROM Products";
        try (var resultSet = queryExecutor.executeQuery(sql))
        {
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                BigDecimal price = resultSet.getBigDecimal("price");
                long category_id = resultSet.getLong("category_id");
                System.out.println("Product ID: " + id + ", Price: " + price + ", Name: " + name + ", Category ID: " + category_id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkOFNull(Product product)
    {
        return product == null || (product.getPrice() == null && product.getName() == null && product.getCategoryId() == null && product.getSubCategoryId() == null);
    }

    @Override
    public int addDessert(Product product, Category category , SubCategory subCategory)
    {
        product.setCategoryId(getCategoryIdOrCreate("Desserts"));

        // Отримання ID підкатегорії, якщо вона існує, або створення нової
        product.setSubCategoryId(getSubCategoryIdOrCreate(subCategory.getName(), product.getCategoryId()));

        // Додавання продукту
        String addProductSQL = "INSERT INTO Products (name, category_id, subCategory_id, price) VALUES (?, ?, ?, ?)";
        return queryExecutor.executeInsertAndReturnId(addProductSQL,
                product.getName(),
                product.getCategoryId(),
                product.getSubCategoryId(),
                product.getPrice());
    }

    // Отримання ID категорії за назвою або створення нової
    private long getCategoryIdOrCreate(String categoryName) {
        String checkCategorySQL = "SELECT id FROM Category WHERE name = ?";
        try (ResultSet resultSet = queryExecutor.executeQuery(checkCategorySQL, categoryName)) {
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            logError("Error while checking category: " + categoryName, e);
        }

        // Створення нової категорії
        String createCategorySQL = "INSERT INTO Category (name) VALUES (?)";
        return queryExecutor.executeInsertAndReturnId(createCategorySQL, categoryName);
    }

    // Отримання ID підкатегорії за назвою або створення нової
    private long getSubCategoryIdOrCreate(String subCategoryName, long categoryId) {
        String checkSubCategorySQL = "SELECT id FROM SubCategory WHERE name = ? AND category_id = ?";
        try (ResultSet resultSet = queryExecutor.executeQuery(checkSubCategorySQL, subCategoryName, categoryId)) {
            if (resultSet.next()) {
                return resultSet.getLong("id");
            }
        } catch (SQLException e) {
            logError("Error while checking sub-category: " + subCategoryName, e);
        }

        // Створення нової підкатегорії
        String createSubCategorySQL = "INSERT INTO SubCategory (name, category_id) VALUES (?, ?)";
        return queryExecutor.executeInsertAndReturnId(createSubCategorySQL, subCategoryName, categoryId);
    }

    private void logError(String message, Exception e) {
        // Використовуйте відповідну логіку для логування
        System.err.println(message);
        e.printStackTrace();
    }

    @Override
    public void showAllDessertsWithNames(Category category, Language language)
    {
        try (ResultSet dessertsResultSet = queryExecutor.executeQuery(getDessertsSQL())) {
            while (dessertsResultSet.next()) {
                int productId = dessertsResultSet.getInt("product_id");
                String productName = dessertsResultSet.getString("productName");

                System.out.println("Product ID: " + productId + ", Name: " + productName);

                // Отримуємо переклади для продукту
                try (ResultSet translationsResultSet = queryExecutor.executeQuery(getTranslationsSQL(), productId)) {
                    System.out.println("Translations:");
                    while (translationsResultSet.next()) {
                        String translatedName = translationsResultSet.getString("translatedName");
                        String languageName = translationsResultSet.getString("languageName");
                        System.out.println("  - " + translatedName + " (" + languageName + ")");
                    }
                }
            }
        } catch (SQLException e) {
            logError("Error fetching desserts and translations", e);
        }
    }

    @Override
    public void showAllDrinksWithNames(Category category, Language language)
    {
        try (ResultSet dessertsResultSet = queryExecutor.executeQuery(getDrinksSQL())) {
            while (dessertsResultSet.next()) {
                int productId = dessertsResultSet.getInt("product_id");
                String productName = dessertsResultSet.getString("productName");

                System.out.println("Product ID: " + productId + ", Name: " + productName);

                // Отримуємо переклади для продукту
                try (ResultSet translationsResultSet = queryExecutor.executeQuery(getTranslationsSQL(), productId)) {
                    System.out.println("Translations:");
                    while (translationsResultSet.next()) {
                        String translatedName = translationsResultSet.getString("translatedName");
                        String languageName = translationsResultSet.getString("languageName");
                        System.out.println("  - " + translatedName + " (" + languageName + ")");
                    }
                }
            }
        } catch (SQLException e) {
            logError("Error fetching desserts and translations", e);
        }
    }

    private String getTranslationsSQL()
    {
        return """
        SELECT 
            t.name AS translatedName,
            l.name AS languageName
        FROM 
            Translations t
        INNER JOIN 
            Languages l ON t.language_code = l.code
        WHERE 
            t.product_id = ?
    """;}

    private String getDessertsSQL()
    {
        return """
         SELECT 
            p.id AS productId,
            p.name AS productName
        FROM 
            Products p
        INNER JOIN 
            Category c ON p.category_id = c.id
        WHERE 
            c.name = 'Desserts'
        ORDER BY 
            p.name
    """;
    }

    private String getDrinksSQL() {
        return """
        SELECT 
            p.id AS productId,
            p.name AS productName
        FROM 
            Products p
        INNER JOIN 
            Category c ON p.category_id = c.id
        WHERE 
            c.name = 'Drinks'
        ORDER BY 
            p.name
    """;
    }

    @Override
    public int updatePriceDrink_FilterType(SubCategory subCategory, double newPrice) {
        if (subCategory == null || subCategory.getName() == null || subCategory.getName().isEmpty()) {
            throw new IllegalArgumentException("SubCategory or SubCategory name cannot be null or empty");
        }

        if (newPrice <= 0) {
            throw new IllegalArgumentException("New price must be greater than 0");
        }

        // SQL для перевірки існування підкатегорії
        String checkSubCategorySQL = """
        SELECT COUNT(*)
        FROM SubCategory
        WHERE name = ?
    """;

        int subCategoryCount = 0;

        try (ResultSet resultSet = queryExecutor.executeQuery(checkSubCategorySQL, subCategory.getName())) {
            if (resultSet.next()) {
                subCategoryCount = resultSet.getInt(1); // Отримуємо кількість записів
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error checking subcategory existence: " + e.getMessage(), e);
        }

        if (subCategoryCount == 0) {
            throw new IllegalStateException("SubCategory with name " + subCategory.getName() + " does not exist.");
        }

        // SQL для оновлення ціни
        String updatePriceSQL = """
        UPDATE Products
        SET price = ?
        WHERE subCategory_id = (SELECT id FROM SubCategory WHERE name = ?)
    """;

        int rowsUpdated = queryExecutor.executeUpdate(updatePriceSQL, newPrice, subCategory.getName());
        if (rowsUpdated == 0) {
            throw new IllegalStateException("No products were updated. Check if the subcategory has related products.");
        }
        return rowsUpdated;
    }


}
