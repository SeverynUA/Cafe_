package brainacad.org.Busines.Proper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static java.lang.System.getProperty;

public class PropertyReader {
    public Properties readProperties(String environment) throws IOException {
        Properties properties = new Properties();
        String filePath;

        // Вибір файлу залежно від середовища
        if ("test".equalsIgnoreCase(environment)) {
            filePath = "src/test/resources/config_test.properties";
        } else {
            filePath = "src/main/resources/config.properties";
        }

        File file = new File(filePath);

        if (!file.exists()) {
            throw new IOException("Файл конфігурації не знайдено: " + filePath);
        }

        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new IOException("Помилка читання файлу конфігурації: " + filePath, e);
        }

        return properties;
    }
}
