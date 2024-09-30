package com.restaurantweb.servlets;

import com.restaurantweb.dao.OrderDAO;
import com.restaurantweb.models.Order;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

//@WebServlet("/orders")
public class OrderServlet extends HttpServlet {
    private OrderDAO orderDAO;

    @Override
    public void init() throws ServletException {
        // Получаем соединение из контекста приложения
        try {
            Connection connection = (Connection) getServletContext().getAttribute("DBConnection");
            orderDAO = new OrderDAO(connection); // Передаем соединение в DAO
        } catch (SQLException e) {
            throw new ServletException("Ошибка при подключении к базе данных", e); // Обрабатываем исключение
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Получаем все элементы меню через DAO
            List<Order> orders = orderDAO.getAllOrders();
            request.setAttribute("orders", orders);

            // Перенаправляем на JSP
            request.getRequestDispatcher("/jsp/order.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Ошибка при получении списка меню", e);
        }
    }
    @Override
    public void destroy() {
        try {
            // Закрываем соединение через MenuDAO
            if (orderDAO != null) {
                orderDAO.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
