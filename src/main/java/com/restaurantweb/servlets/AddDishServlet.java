package com.restaurantweb.servlets;

import com.restaurantweb.dao.MenuDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class AddDishServlet extends HttpServlet {

    private MenuDAO menuDAO;

    @Override
    public void init() throws ServletException {
        // Получаем соединение из контекста приложения
        try {
            Connection connection = (Connection) getServletContext().getAttribute("DBConnection");
            menuDAO = new MenuDAO(connection); // Передаем соединение в DAO
        } catch (SQLException e) {
            throw new ServletException("Ошибка при подключении к базе данных", e); // Обрабатываем исключение
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Получаем данные из формы
        String dishName = request.getParameter("dishName");
        String cost = request.getParameter("cost");
        String preview = request.getParameter("preview");
        String dish_type = request.getParameter("dish_type");

        System.out.println("Add dish: " + dishName + ", Cost: " + cost + ", Preview: " + preview + ", Dish type: " + dish_type); // Логирование

        // Добавляем новое блюдо в базу данных
        try {
            menuDAO.addDish(dishName, cost, preview, dish_type);
            // Перенаправляем обратно на страницу меню
            response.sendRedirect("menu");
        } catch (SQLException e) {
            throw new ServletException("Ошибка при добавлении блюда", e);
        }
    }
}
