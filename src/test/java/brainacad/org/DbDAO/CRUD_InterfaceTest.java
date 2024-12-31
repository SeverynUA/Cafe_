package brainacad.org.DbDAO;

import brainacad.org.Busines.Proper.DatabaseUtil;
import brainacad.org.Busines.Proper.PropertyReader;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.Properties;

public interface CRUD_InterfaceTest<T>
{
    static final Properties properties = new Properties();

    @BeforeAll
    static void setUp() throws IOException
    {
        PropertyReader reader = new PropertyReader();
        Properties loadedProperties = reader.readProperties("test");
        properties.putAll(loadedProperties);
    }

    @AfterEach
    default void resetTest() throws IOException
    {
        DatabaseUtil.resetDatabase();
    }

    @BeforeEach
    default void InitDbTest() throws IOException
    {
        String createTablesFilePath = properties.getProperty("db.create_tables_file_path");
        String initFilePath = properties.getProperty("db.init_file_path");

        if (createTablesFilePath == null || initFilePath == null)
        {
            throw new IOException("Database file paths are not defined in the properties file.");
        }

        DatabaseUtil.executeSQLFiles(createTablesFilePath, initFilePath);
    }

    @Test
    void addTest();

    @Test
    void updateTest();

    @Test
    void deleteTest();

    @Test
    void showAllTest();

    @Test
    void checkOFNullTest();
}
