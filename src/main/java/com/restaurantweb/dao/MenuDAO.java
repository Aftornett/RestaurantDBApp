package com.restaurantweb.dao;

import com.restaurantweb.models.Menu;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuDAO {
    private Connection connection;

    public MenuDAO(Connection connection) throws SQLException {
        this.connection = connection;
        // Проверка подключения
        if (this.connection == null) {
            throw new SQLException("Не удалось установить соединение с базой данных.");
        }
    }

    // Получение всех блюд
    public List<Menu> getAllMenuItems() throws SQLException {
        List<Menu> menuItems = new ArrayList<>();
        String query = "SELECT * FROM Menu";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Menu menuItem = new Menu();
                menuItem.setId(rs.getInt("id"));
                menuItem.setDishName(rs.getString("dish_name"));
                menuItem.setCost(rs.getDouble("cost"));
                menuItem.setPreview(rs.getString("preview"));
                menuItem.setDishType(rs.getString("dish_type"));
                menuItems.add(menuItem);
            }
        }
        return menuItems;
    }

    public void addDish(String dishName, float cost, String preview, String dish_type) throws SQLException {
        String query = "INSERT INTO menu (dish_name, cost, preview, dish_type) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, dishName);
            statement.setFloat(2, cost);
            statement.setString(3, preview);
            statement.setString(4, dish_type);
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
