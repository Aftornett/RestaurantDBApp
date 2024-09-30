package com.restaurantweb.servlets;

import com.restaurantweb.dao.MenuDAO;
import com.restaurantweb.models.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


//@WebServlet("/menu")
public class MenuServlet extends HttpServlet {
    private MenuDAO menuDAO;

    @Override
    public void init() throws ServletException {
        System.out.println("MenuServlet init called");
        // Получаем соединение из контекста приложения
        try {
            Connection connection = (Connection) getServletContext().getAttribute("DBConnection");
            if (connection == null) {
                System.out.println("Connection is null");
                throw new ServletException("Не удалось получить соединение из контекста.");
            } else {
                System.out.println("Connection is not null, proceeding...");
                menuDAO = new MenuDAO(connection); // Передаем соединение в DAO
            }
            //menuDAO = new MenuDAO(connection); // Передаем соединение в DAO
        } catch (SQLException e) {
            throw new ServletException("Ошибка при подключении к базе данных", e); // Обрабатываем исключение
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Получаем все элементы меню через DAO
            List<Menu> menuItems = menuDAO.getAllMenuItems();
            request.setAttribute("menuItems", menuItems);

            // Перенаправляем на JSP
            request.getRequestDispatcher("/jsp/menu.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Ошибка при получении списка меню", e);
        }
    }

    @Override
    public void destroy() {
        try {
            // Закрываем соединение через MenuDAO
            if (menuDAO != null) {
                menuDAO.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
