package com.restaurantweb.dao;

import com.restaurantweb.models.Guest;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GuestDAO {
    private Connection connection;

    public GuestDAO(Connection connection) throws SQLException {
        this.connection = connection;
        // Проверка подключения
        if (this.connection == null) {
            throw new SQLException("Не удалось установить соединение с базой данных.");
        }
    }

    // Получение всех гостей
    public List<Guest> getAllGuests() throws SQLException {
        List<Guest> guests = new ArrayList<>();
        String query = "SELECT id, name, phone_number, delivery_adr FROM guests";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Guest guest = new Guest();
                guest.setId(rs.getInt("id"));
                guest.setName(rs.getString("name"));
                guest.setPhoneNumber(rs.getString("phone_number"));
                guest.setDeliveryAdr(rs.getString("delivery_adr"));
                // Логируем данные
                System.out.println("Loaded guest: ID=" + guest.getId() + ", Name=" + guest.getName() +
                        ", Phone=" + guest.getPhoneNumber() + ", Delivery Address=" + guest.getDeliveryAdr());
                guests.add(guest);
            }
        }
        return guests;
    }

    // Метод для добавления нового гостя в базу данных
    public void addGuest(String name, String phoneNumber, String deliveryAdr) throws SQLException {
        String query = "INSERT INTO guests (name, phone_number, delivery_adr) VALUES (?, ?, ?)";

        // Используем PreparedStatement для предотвращения SQL-инъекций
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);         // Устанавливаем имя гостя
            stmt.setString(2, phoneNumber);  // Устанавливаем номер телефона
            stmt.setString(3, deliveryAdr);  // Устанавливаем адрес доставки
            stmt.executeUpdate();            // Выполняем запрос
        }
    }
    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

}
