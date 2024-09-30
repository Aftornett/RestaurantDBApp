package com.restaurantweb.dao;

import com.restaurantweb.models.Feedback;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDAO {
    private Connection connection;

    public FeedbackDAO(Connection connection)  throws SQLException {
        this.connection = connection;
        // Проверка подключения
        if (this.connection == null) {
            throw new SQLException("Не удалось установить соединение с базой данных.");
        }
    }

    // Получение всех отзывов
    public List<Feedback> getAllFeedbacks() throws SQLException {
        List<Feedback> feedbacks = new ArrayList<>();
        String query = "SELECT * FROM Feedbacks";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Feedback feedback = new Feedback();
                feedback.setId(rs.getInt("id"));
                feedback.setMealId(rs.getInt("meal_id"));
                feedback.setGuestId(rs.getInt("guest_id"));
                feedback.setFeedbackText(rs.getString("fb_txt"));
                feedback.setRating(rs.getInt("rating"));
                feedbacks.add(feedback);
            }
        }
        return feedbacks; // Возвращаем List<Feedback>
    }
    // Закрытие подключения
    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
