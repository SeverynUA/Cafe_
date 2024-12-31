package brainacad.org.Busines;

import brainacad.org.Busines.Proper.PropertyReader;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class PropertyReaderTest
{
    @Test
    void testMainEnvironmentConfig() throws Exception {
        PropertyReader reader = new PropertyReader();
        Properties properties = reader.readProperties("main");

        assertEquals("jdbc:postgresql://localhost:5432/Cafe", properties.getProperty("db.url"));
        assertEquals("postgres", properties.getProperty("db.username"));
        assertEquals("1234567890", properties.getProperty("db.password"));
        assertEquals("src/main/resources/Database/CreateTables-db_Cafe.sql", properties.getProperty("db.create_tables_file_path"));
        assertEquals("src/main/resources/Database/Init-db_Cafe.sql", properties.getProperty("db.init_file_path"));
    }

    @Test
    void testTestEnvironmentConfig() throws Exception {
        PropertyReader reader = new PropertyReader();
        Properties properties = reader.readProperties("test");

        assertEquals("jdbc:postgresql://localhost:5432/Cafe_Test", properties.getProperty("db.url"));
        assertEquals("postgres", properties.getProperty("db.username"));
        assertEquals("1234567890", properties.getProperty("db.password"));
        assertEquals("src/main/resources/Database/CreateTables-db_Cafe.sql", properties.getProperty("db.create_tables_file_path"));
        assertEquals("src/main/resources/Database/Init-db_Cafe.sql", properties.getProperty("db.init_file_path"));
    }
}
