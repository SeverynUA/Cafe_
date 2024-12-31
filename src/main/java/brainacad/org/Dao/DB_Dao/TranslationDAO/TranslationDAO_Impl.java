package brainacad.org.Dao.DB_Dao.TranslationDAO;

import brainacad.org.Dao.DB_Dao.QueryExecutor;
import brainacad.org.Models.Translation.Translation;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TranslationDAO_Impl implements TranslationDAO
{
    QueryExecutor queryExecutor = new QueryExecutor();

    @Override
    public int add(Translation translation) {
        String sql = "INSERT INTO Translations (product_id, language_code, name) VALUES (?, ?, ?)";

        if (checkOFNull(translation)) {
            return 0;
        }

        return queryExecutor.executeInsertAndReturnId(sql,
                translation.getProduct().getId(),
                translation.getLanguage().getCode(),
                translation.getName());
    }

    @Override
    public int update(Translation translation) {
        String sql = "UPDATE Translations SET product_id=?, language_code=?, name=? WHERE id=?";

        if (checkOFNull(translation)) {
            return 0;
        }

        return queryExecutor.executeUpdate(sql,
                translation.getProduct().getId(),
                translation.getLanguage().getCode(),
                translation.getName(),
                translation.getId());
    }

    @Override
    public int delete(int id) {
        String sql = "DELETE FROM Translations WHERE id=?";

        if (id <= 0) {
            return 0;
        }

        return queryExecutor.executeUpdate(sql, id);
    }

    @Override
    public void showAll() {
        String sql = "SELECT * FROM Translations";
        try (ResultSet resultSet = queryExecutor.executeQuery(sql)) {
            while (resultSet.next()) {
                System.out.println("Translation id: " + resultSet.getLong("id") +
                        ", Product ID: " + resultSet.getLong("product_id") +
                        ", Language Code: " + resultSet.getString("language_code") +
                        ", Name: " + resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkOFNull(Translation translation) {
        return translation == null ||
                translation.getProduct().getId() <= 0 ||
                translation.getLanguage().getCode() == null ||
                translation.getName() == null;
    }
}
