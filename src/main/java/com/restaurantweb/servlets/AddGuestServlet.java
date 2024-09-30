package com.restaurantweb.servlets;

import com.restaurantweb.dao.GuestDAO;
import com.restaurantweb.dao.MenuDAO;
import com.restaurantweb.utils.DatabaseConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class AddGuestServlet extends HttpServlet {

    private GuestDAO guestDAO;

    @Override
    public void init() throws ServletException {
        // Получаем соединение из контекста приложения
        try {
            Connection connection = (Connection) getServletContext().getAttribute("DBConnection");
            guestDAO = new GuestDAO(connection); // Передаем соединение в DAO
        } catch (SQLException e) {
            throw new ServletException("Ошибка при подключении к базе данных", e); // Обрабатываем исключение
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Получаем данные из формы
        String guestName = request.getParameter("guestName");
        String guestPhone = request.getParameter("guestPhone");
        String deliveryAdr = request.getParameter("deliveryAdr");

        System.out.println("Add гостя: " + guestName + ", Phone: " + guestPhone + ", Address: " + deliveryAdr); // Логирование

        // Добавляем нового гостя в базу данных
        try {
            guestDAO.addGuest(guestName, guestPhone, deliveryAdr);
            // Перенаправляем обратно на страницу гостей
            response.sendRedirect("guests");
        } catch (SQLException e) {
            throw new ServletException("Ошибка при добавлении гостя", e);
        }
    }
}
