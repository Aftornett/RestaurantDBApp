package com.restaurantweb.utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.SQLException;

//@WebListener
public class DatabaseContextListener implements ServletContextListener {
    private Connection connection;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Initializing database connection...");
        try {
            connection = DatabaseConnection.getConnection(); // Инициализация соединения
            sce.getServletContext().setAttribute("DBConnection", connection); // Сохранение в контексте
            System.out.println("Database connection established.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to database.", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close(); // Закрытие соединения
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
