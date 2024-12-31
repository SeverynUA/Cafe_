package brainacad.org.Dao.DB_Dao.LanguageDAO;

import brainacad.org.Dao.CRUD_Interface;
import brainacad.org.Dao.DB_Dao.QueryExecutor;
import brainacad.org.Models.Language.Language;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LanguageDAO_impl implements LanguageDAO
{
    QueryExecutor queryExecutor = new QueryExecutor();

    @Override
    public int add(Language language) {return 0;}

    @Override
    public String addCode(Language language)
    {
        String sql = "INSERT INTO Languages (code, name) VALUES (?, ?) RETURNING code";
        if(!checkOFNull(language))
        {
            throw new IllegalArgumentException("Language code or name is null");
        }

        return queryExecutor.executeInsertAndReturnGeneratedKey(sql, language.getCode(), language.getName());
    }

    @Override
    public int update(Language language)
    {
        String sql = "UPDATE Languages SET code=?, name=? WHERE code=?";
        if(!checkOFNull(language))
        {
            throw new IllegalArgumentException("Language code or name is null");
        }
        return queryExecutor.executeUpdate(sql, language.getCode(), language.getName(), language.getCode());
    }

    @Override
    public int delete(int id) {return 0;}

    @Override
    public int deleteCode(Language language)
    {
        String sql = "DELETE FROM Languages WHERE code=?";
        if(!checkOFNull(language))
        {
            throw new IllegalArgumentException("Language code or name is null");
        }
        return queryExecutor.executeUpdate(sql,language.getCode());
    }

    @Override
    public void showAll()
    {
        String sql = "SELECT * FROM Languages";
        try (ResultSet resultSet = queryExecutor.executeQuery(sql))
        {
            while (resultSet.next())
            {
                System.out.println("Language Code: " + resultSet.getString("code") +
                        ", Name: " + resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkOFNull(Language language)
    {
        return language != null && language.getCode() != null && language.getName() != null;
    }
}
