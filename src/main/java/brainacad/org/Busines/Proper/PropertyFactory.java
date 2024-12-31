package brainacad.org.Busines;

import java.io.IOException;
import java.util.Properties;

import java.io.IOException;
import java.util.Properties;

public class PropertyFactory {
    private static volatile PropertyFactory instance;
    private static Properties properties;

    private PropertyFactory() {
        try {
            PropertyReader reader = new PropertyReader();
            // Отримуємо середовище з системної властивості або за замовчуванням "main"
            String environment = System.getProperty("env", "main");
            properties = reader.readProperties(environment);
        } catch (IOException e) {
            throw new RuntimeException("Не вдалося завантажити властивості: " + e.getMessage(), e);
        }
    }

    public static PropertyFactory getInstance() {
        if (instance == null) {
            synchronized (PropertyFactory.class) {
                if (instance == null) {
                    instance = new PropertyFactory();
                }
            }
        }
        return instance;
    }

    public static Properties getProperties() {
        return properties;
    }
}

