package com.restaurantweb.dao;

import com.restaurantweb.models.Order;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class OrderDAO {
    private Connection connection;

    public OrderDAO(Connection connection) throws SQLException {
        this.connection = connection;
        // Проверка подключения
        if (this.connection == null) {
            throw new SQLException("Не удалось установить соединение с базой данных.");
        }
    }

    // Получение всех заказов
    public List<Order> getAllOrders() throws SQLException {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM Orders";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setGuestId(rs.getInt("guest_id"));
                order.setOrderDatetime(rs.getTimestamp("order_datetime").toLocalDateTime());
                order.setOrderStatus(rs.getString("order_status"));
                orders.add(order);
            }
        }
        return orders;
    }

    // Метод для добавления нового заказа
    public void addOrder(int guestId, Timestamp orderDatetime, String orderStatus) throws SQLException {
        String query = "INSERT INTO orders (guest_id, order_datetime, order_status) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, guestId);
            statement.setTimestamp(2, orderDatetime);
            statement.setString(3, orderStatus);
            statement.executeUpdate();
        }
    }


    // Закрытие подключения
    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
