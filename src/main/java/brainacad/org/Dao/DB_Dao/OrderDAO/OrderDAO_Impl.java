package brainacad.org.Dao.DB_Dao.OrderDAO;

import brainacad.org.Dao.DB_Dao.QueryExecutor;
import brainacad.org.Models.Order.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDAO_Impl implements OrderDAO
{
    QueryExecutor queryExecutor = new QueryExecutor();

    @Override
    public int add(Order order) {
        String sql = "INSERT INTO Orders (order_date, order_amount, status_id) VALUES (?, ?, ?)";

        if (checkOFNull(order)) {
            return 0;
        }

        return queryExecutor.executeInsertAndReturnId(sql,
                order.getOrderDate(),
                order.getOrderAmount(),
                order.getStatus().getId());
    }

    @Override
    public int update(Order order) {
        String sql = "UPDATE Orders SET order_date=?, order_amount=?, status_id=? WHERE id=?";

        if (checkOFNull(order)) {
            return 0;
        }

        return queryExecutor.executeUpdate(sql,
                order.getOrderDate(),
                order.getOrderAmount(),
                order.getStatus().getId(),
                order.getId());
    }

    @Override
    public int delete(int id) {
        String sql = "DELETE FROM Orders WHERE id=?";

        if (id <= 0) {
            return 0;
        }

        return queryExecutor.executeUpdate(sql, id);
    }

    @Override
    public void showAll() {
        String sql = "SELECT * FROM Orders";
        try (ResultSet resultSet = queryExecutor.executeQuery(sql)) {
            while (resultSet.next()) {
                System.out.println("Order id: " + resultSet.getLong("id") +
                        ", Order Date: " + resultSet.getTimestamp("order_date") +
                        ", Order Amount: " + resultSet.getBigDecimal("order_amount") +
                        ", Status ID: " + resultSet.getLong("status_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkOFNull(Order order) {
        return order == null ||
                order.getOrderDate() == null ||
                order.getOrderAmount() == null ||
                order.getStatus().getId() <= 0;
    }
}
