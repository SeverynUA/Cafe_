package brainacad.org.Busines;

import brainacad.org.Busines.Proper.DatabaseUtil;
import brainacad.org.Busines.Proper.PropertyReader;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DatabaseUtilTest {

    @BeforeAll
    void setUp() throws IOException
    {
        PropertyReader reader = new PropertyReader();
        Properties properties = reader.readProperties("test");

        System.out.println("URL: " + properties.getProperty("db.url"));
        System.out.println("USER: " + properties.getProperty("db.username"));
        System.out.println("PASSWORD: " + properties.getProperty("db.password"));

        System.out.println("Скидання бази даних перед тестами...");
        DatabaseUtil.resetDatabase();
    }

    @Test
    void testGetConnection()
    {
        try (Connection connection = DatabaseUtil.getConnection())
        {
            assertNotNull(connection, "З'єднання з базою даних не повинно бути null");
            assertTrue(connection.isValid(2), "З'єднання з базою даних недійсне");
        } catch (SQLException e)
        {
            fail("Не вдалося встановити з'єднання: " + e.getMessage());
        }
    }

    @Test
    void testExecuteUpdate()
    {
        String createTableQuery = "CREATE TABLE test_table (id SERIAL PRIMARY KEY, price NUMERIC)";
        DatabaseUtil.executeUpdate(createTableQuery);

        String insertQuery = "INSERT INTO test_table (price) VALUES (?)";
        DatabaseUtil.executeUpdate(insertQuery, BigDecimal.valueOf(99.99));

        String updateQuery = "UPDATE test_table SET price = ? WHERE id = ?";
        DatabaseUtil.executeUpdate(updateQuery, BigDecimal.valueOf(109.99), 1);
    }

    @Test
    void testExecuteQuery()
    {
        String selectQuery = "SELECT price FROM test_table WHERE id = ?";
        try (ResultSet resultSet = DatabaseUtil.executeQuery(selectQuery, 1))
        {
            assertTrue(resultSet.next(), "Результат SELECT-запиту має містити дані");
            BigDecimal price = resultSet.getBigDecimal("price");
            assertEquals(BigDecimal.valueOf(109.99), price, "Ціна не співпадає із очікуваною");
        } catch (SQLException e)
        {
            fail("Помилка під час виконання SELECT-запиту: " + e.getMessage());
        }
    }

    @Test
    void testResetDatabase()
    {
        DatabaseUtil.resetDatabase();
        // Перевірка, чи таблиці були видалені
        String checkQuery = "SELECT table_name FROM information_schema.tables WHERE table_schema = 'public'";
        try (ResultSet resultSet = DatabaseUtil.executeQuery(checkQuery))
        {
            assertFalse(resultSet.next(), "Після скидання бази даних жодна таблиця не повинна існувати");
        } catch (SQLException e)
        {
            fail("Помилка під час перевірки скидання бази даних: " + e.getMessage());
        }
    }

    @Test
    void testExecuteSQLFiles()
    {
        // Виконання SQL-файлів
        DatabaseUtil.executeSQLFiles(
                "src/main/resources/Database/CreateTables-db_Cafe.sql",
                "src/main/resources/Database/Init-db_Cafe.sql"
        );

        // Перевірка існування таблиці Languages
        String checkTableQuery = "SELECT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = 'languages')";
        try (ResultSet resultSet = DatabaseUtil.executeQuery(checkTableQuery))
        {
            if (resultSet.next())
            {
                boolean tableExists = resultSet.getBoolean(1);
                assertTrue(tableExists, "Таблиця 'languages' повинна існувати після виконання SQL-файлу.");
            } else {
                fail("Не вдалося перевірити наявність таблиці 'languages'.");
            }
        } catch (SQLException e)
        {
            fail("SQL-помилка під час перевірки таблиці 'languages': " + e.getMessage());
        }

        // Перевірка записів у таблиці Languages
        String checkDataQuery = "SELECT COUNT(*) FROM languages";
        try (ResultSet resultSet = DatabaseUtil.executeQuery(checkDataQuery)) {
            if (resultSet.next()) {
                int recordCount = resultSet.getInt(1);
                assertTrue(recordCount > 0, "Таблиця 'languages' повинна містити записи після ініціалізації.");
            } else {
                fail("Не вдалося перевірити записи у таблиці 'languages'.");
            }
        } catch (SQLException e) {
            fail("SQL-помилка під час перевірки записів у таблиці 'languages': " + e.getMessage());
        }
    }
}

