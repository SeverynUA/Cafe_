package brainacad.org.Dao.DB_Dao.CategoryDAO;

import brainacad.org.Dao.CRUD_Interface;
import brainacad.org.Dao.DB_Dao.QueryExecutor;
import brainacad.org.Models.Categories.Category;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryDAO_impl implements CRUD_Interface<Category>
{
    QueryExecutor queryExecutor = new QueryExecutor();

    @Override
    public int add(Category сategory)
    {
        String sql = "INSERT INTO Category (Name) VALUES (?)";

        if(checkOFNull(сategory))
        {
            return 0;
        }
        return queryExecutor.executeInsertAndReturnId(sql,сategory.getName());
    }

    @Override
    public int update(Category сategory)
    {
        String sql = "UPDATE Category SET Name=? WHERE id=?";

        if(checkOFNull(сategory))
        {
            return 0;
        }

        return queryExecutor.executeInsertAndReturnId(sql,сategory.getName(),сategory.getName());
    }

    @Override
    public int delete(int id)
    {
        String sql = "DELETE FROM CUSTOMER WHERE id=?";
        if(id <= 0)
        {
            return 0;
        }

        return queryExecutor.executeUpdate(sql,id);
    }

    @Override
    public void showAll()
    {
        String sql = "SELECT * FROM Category";
        try (ResultSet resultSet = queryExecutor.executeQuery(sql))
        {
            while (resultSet.next())
            {
                System.out.println("Category id: " + resultSet.getString("id") +
                        ", Name: " + resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkOFNull(Category category)
    {
        return category != null && category.getName() == null;
    }
}
