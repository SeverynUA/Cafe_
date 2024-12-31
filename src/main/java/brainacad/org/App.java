package brainacad.org;

import brainacad.org.Busines.Proper.PropertyReader;

import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String environment = System.getProperty("env", "main");

        System.out.println("Середовище: " + environment);

        try {
            // Завантажуємо конфігурації
            PropertyReader reader = new PropertyReader();
            Properties properties = reader.readProperties(environment);

            // Виводимо властивості
            System.out.println("Database URL: " + properties.getProperty("db.url"));
            System.out.println("Database Username: " + properties.getProperty("db.username"));
            System.out.println("Database Password: " + properties.getProperty("db.password"));
            System.out.println("SQL File Path: " + properties.getProperty("db.sql_file_path"));
        } catch (Exception e) {
            System.err.println("Помилка завантаження конфігурації для середовища '" + environment + "': " + e.getMessage());
        }
    }
}
