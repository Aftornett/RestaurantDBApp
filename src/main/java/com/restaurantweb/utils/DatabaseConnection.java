package com.restaurantweb.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // Метод для получения подключения к базе данных PostgreSQL
    public static Connection getConnection() throws SQLException {
        // Явная загрузка драйвера PostgreSQL
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("PostgreSQL драйвер не найден", e);
        }

        // URL для подключения к базе данных
        String url = "jdbc:postgresql://localhost:5432/Restaurant_DB";
        String username = "postgres";  // Имя пользователя для подключения
        String password = "hfylfn123"; // Пароль для подключения

        //Connection connection = DriverManager.getConnection(url, username, password);

        // Логирование состояния соединения
        /*if (connection != null) {
            System.out.println("Соединение успешно установлено");
        } else {
            System.out.println("Не удалось установить соединение");
        }

        //return connection;*/
        // Устанавливаем и возвращаем соединение с базой данных
        return DriverManager.getConnection(url, username, password);
    }
}
