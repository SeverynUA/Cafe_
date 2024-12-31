package brainacad.org.Busines;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

import java.sql.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseUtil {
    private static final Logger logger = Logger.getLogger(DatabaseUtil.class.getName());

    private static final String URL;
    private static final String USER;
    private static final String PASSWORD;
    private static final String SQL_FILE_PATH;

    static {
        Properties properties = PropertyFactory.getProperties();
        if (properties == null) {
            throw new IllegalStateException("Конфігурація не завантажена.");
        }

        URL = properties.getProperty("db.url");
        USER = properties.getProperty("db.username");
        PASSWORD = properties.getProperty("db.password");
        SQL_FILE_PATH = properties.getProperty("db.sql_file_path");

        if (URL == null || USER == null || PASSWORD == null || SQL_FILE_PATH == null) {
            throw new IllegalStateException("Не всі необхідні властивості присутні у файлі конфігурації.");
        }
    }

    public static String getSqlFilePath() {
        return SQL_FILE_PATH;
    }

    /**
     * Отримання з'єднання з базою даних.
     */
    public static Connection getConnection() throws SQLException {
        if (URL == null || USER == null || PASSWORD == null) {
            throw new IllegalStateException("Database configuration is missing.");
        }
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        logger.info("Підключення до бази даних встановлено.");
        return connection;
    }

    /**
     * Перевіряє доступність бази даних.
     */
    public static boolean isDatabaseReachable() {
        try (Connection connection = getConnection()) {
            return connection.isValid(2); // Перевірка протягом 2 секунд
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "База даних недоступна.", e);
            return false;
        }
    }

    /**
     * Виконує SQL-запит без повернення результату (INSERT, UPDATE, DELETE).
     */
    public static void executeUpdate(String query, Object... params) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            setParameters(statement, params);
            int rowsAffected = statement.executeUpdate();
            logger.info("Запит виконано успішно. Змінено рядків: " + rowsAffected);

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Помилка під час виконання запиту.", e);
            throw new RuntimeException("Помилка під час виконання SQL-запиту.", e);
        }
    }

    /**
     * Виконує SELECT-запити і повертає `ResultSet`.
     */
    public static ResultSet executeQuery(String query, Object... params) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            setParameters(statement, params);

            logger.info("Виконується SELECT-запит.");
            return statement.executeQuery(); // Повернення ResultSet, користувач має закрити його самостійно.

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Помилка під час виконання SELECT-запиту.", e);
            throw new RuntimeException("Помилка під час виконання SELECT-запиту.", e);
        }
    }

    /**
     * Виконує SQL-файл для ініціалізації бази даних.
     */
    public static void executeSQLFile() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             BufferedReader reader = new BufferedReader(new FileReader(SQL_FILE_PATH))) {

            StringBuilder sql = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sql.append(line).append("\n");
            }

            statement.execute(sql.toString());
            logger.info("SQL-файл успішно виконаний.");

        } catch (IOException | SQLException e) {
            logger.log(Level.SEVERE, "Помилка під час виконання SQL-файлу.", e);
            throw new RuntimeException("Помилка під час виконання SQL-файлу.", e);
        }
    }

    /**
     * Скидання бази даних: видалення всіх таблиць, ENUM-типів і повторна ініціалізація.
     */
    public static void resetDatabase() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            // Видалення всіх таблиць
            String dropTablesQuery = """
            DO $$ DECLARE
            r RECORD;
            BEGIN
                FOR r IN (SELECT tablename FROM pg_tables WHERE schemaname = 'public') LOOP
                    EXECUTE 'DROP TABLE IF EXISTS ' || quote_ident(r.tablename) || ' CASCADE';
                END LOOP;
            END $$;
            """;
            statement.execute(dropTablesQuery);
            logger.info("Всі таблиці успішно видалені.");

            // Видалення всіх ENUM-типів
            String dropEnumsQuery = """
            DO $$ DECLARE
            r RECORD;
            BEGIN
                FOR r IN (SELECT typname FROM pg_type WHERE typtype = 'e' AND typnamespace = 'public'::regnamespace) LOOP
                    EXECUTE 'DROP TYPE IF EXISTS ' || quote_ident(r.typname) || ' CASCADE';
                END LOOP;
            END $$;
            """;
            statement.execute(dropEnumsQuery);
            logger.info("Всі ENUM-типи успішно видалені.");

            // Виконання SQL-файлу
            executeSQLFile();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Помилка під час скидання бази даних.", e);
            throw new RuntimeException("Помилка під час скидання бази даних.", e);
        }
    }

    /**
     * Встановлює параметри для PreparedStatement.
     */
    private static void setParameters(PreparedStatement statement, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            statement.setObject(i + 1, params[i]);
        }
    }
}
