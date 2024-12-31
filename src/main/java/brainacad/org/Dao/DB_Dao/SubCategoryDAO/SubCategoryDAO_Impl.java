package brainacad.org.Dao.DB_Dao.SubCategoryDAO;

import brainacad.org.Dao.DB_Dao.QueryExecutor;
import brainacad.org.Models.Categories.SubCategory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubCategoryDAO_Impl implements SubCategoryDAO
{
    QueryExecutor queryExecutor = new QueryExecutor();

    @Override
    public int add(SubCategory subCategory) {
        String sql = "INSERT INTO SubCategory (name, categoryId) VALUES (?, ?)";

        if (checkOFNull(subCategory)) {
            return 0;
        }

        return queryExecutor.executeInsertAndReturnId(sql,
                subCategory.getName(),
                subCategory.getCategoryId());
    }

    @Override
    public int update(SubCategory subCategory) {
        String sql = "UPDATE SubCategory SET name=?, categoryId=? WHERE id=?";

        if (checkOFNull(subCategory)) {
            return 0;
        }

        return queryExecutor.executeUpdate(sql,
                subCategory.getName(),
                subCategory.getCategoryId(),
                subCategory.getId());
    }

    @Override
    public int delete(int id) {
        String sql = "DELETE FROM SubCategory WHERE id=?";

        if (id <= 0) {
            return 0;
        }

        return queryExecutor.executeUpdate(sql, id);
    }

    @Override
    public void showAll() {
        String sql = "SELECT * FROM SubCategory";
        try (ResultSet resultSet = queryExecutor.executeQuery(sql)) {
            while (resultSet.next()) {
                System.out.println("SubCategory id: " + resultSet.getLong("id") +
                        ", Name: " + resultSet.getString("name") +
                        ", Category ID: " + resultSet.getLong("categoryId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkOFNull(SubCategory subCategory) {
        return subCategory == null ||
                subCategory.getName() == null ||
                subCategory.getCategoryId() <= 0;
    }
}
