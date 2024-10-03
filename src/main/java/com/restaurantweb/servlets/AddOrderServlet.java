package com.restaurantweb.servlets;

import com.restaurantweb.dao.OrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class AddOrderServlet extends HttpServlet {

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Получаем данные из формы
        int guestId = Integer.parseInt(request.getParameter("guestId"));
        String orderStatus = request.getParameter("orderStatus");

        // Добавляем новый заказ в базу данных
        try {
            Timestamp orderDatetime = Timestamp.valueOf(LocalDateTime.now());
            //LocalDateTime orderDatetime = LocalDateTime.now(); // Устанавливаем текущую дату и время
            orderDAO.addOrder(guestId, orderDatetime, orderStatus);
            // Перенаправляем обратно на страницу заказов
            response.sendRedirect("orders");
        } catch (SQLException e) {
            throw new ServletException("Ошибка при добавлении заказа", e);
        }
    }
}
