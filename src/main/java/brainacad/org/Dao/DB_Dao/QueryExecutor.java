package brainacad.org.Dao.DB_Dao;

import brainacad.org.Busines.Proper.DatabaseUtil;

import java.sql.*;

import java.util.logging.Logger;

public class QueryExecutor {
    private static final Logger logger = Logger.getLogger(QueryExecutor.class.getName());

    // Виконує запити типу INSERT, UPDATE, DELETE (зміна даних)
    public int executeUpdate(String sql, Object... params)
    {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql))
        {

            logQuery(sql, params);

            setParameters(statement, params);
            return statement.executeUpdate();
        } catch (SQLException e) {
            logger.severe("SQL Execution Error: " + e.getMessage());
            throw new RuntimeException("Error executing update query.", e);
        }
    }

    // Виконує INSERT-запити і повертає згенерований ID
    public int executeInsertAndReturnId(String sql, Object... params)
    {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
        {

            setParameters(statement, params);
            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0)
            {
                try (ResultSet generatedKeys = statement.getGeneratedKeys())
                {
                    if (generatedKeys.next())
                    {
                        return generatedKeys.getInt(1);
                    }
                }
            }
            return -1;
        } catch (SQLException e)
        {
            logger.severe("SQL Execution Error: " + e.getMessage());
            throw new RuntimeException("Error executing insert query.", e);
        }
    }

    // Виконує INSERT-запити і повертає згенерований ключ у вигляді рядка
    public String executeInsertAndReturnGeneratedKey(String sql, Object... params)
    {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            setParameters(statement, params);
            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0)
            {
                try (ResultSet generatedKeys = statement.getGeneratedKeys())
                {
                    if (generatedKeys.next())
                    {
                        return generatedKeys.getString(1);
                    }
                }
            }
            return null;
        } catch (SQLException e) {
            logger.severe("SQL Execution Error: " + e.getMessage());
            throw new RuntimeException("Error executing insert query.", e);
        }
    }

    // Виконує SELECT-запити і повертає ResultSet
    public ResultSet executeQuery(String sql, Object... params)
    {
        try {
            Connection connection = DatabaseUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            setParameters(statement, params);
            logQuery(sql, params);

            return statement.executeQuery(); // Важливо: Закриття ресурсів має виконувати користувач
        } catch (SQLException e)
        {
            logger.severe("SQL Execution Error: " + e.getMessage());
            throw new RuntimeException("Error executing select query.", e);
        }
    }

    // Виконує SELECT-запити і повертає ІД запису
    public int executeQueryForId(String sql, Object... params)
    {
        try {
            Connection connection = DatabaseUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            setParameters(statement, params); // Установка параметрів запиту
            logQuery(sql, params); // Логування запиту

            try (ResultSet resultSet = statement.executeQuery())
            {
                if (resultSet.next())
                {
                    return resultSet.getInt(1);
                } else
                {
                    throw new RuntimeException("No record found for the query.");
                }
            }
        } catch (SQLException e)
        {
            logger.severe("SQL Execution Error: " + e.getMessage());
            throw new RuntimeException("Error executing select query for ID.", e);
        }
    }

    // Допоміжний метод для установки параметрів у PreparedStatement
    private void setParameters(PreparedStatement statement, Object... params) throws SQLException
    {
        for (int i = 0; i < params.length; i++)
        {
            statement.setObject(i + 1, params[i]);
        }
    }

    // Логування SQL-запитів і параметрів
    private void logQuery(String sql, Object... params)
    {
        logger.info("Executing SQL: " + sql);
        for (int i = 0; i < params.length; i++)
        {
            logger.info("Param[" + (i + 1) + "]: " + params[i]);
        }
    }
}