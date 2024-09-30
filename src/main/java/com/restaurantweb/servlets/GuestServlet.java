package com.restaurantweb.servlets;

import com.restaurantweb.dao.GuestDAO;
import com.restaurantweb.models.Guest;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

//@WebServlet("/guests")
public class GuestServlet extends HttpServlet {
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Запрашиваем список гостей из базы данных
            List<Guest> guests = guestDAO.getAllGuests();
            System.out.println("Count of guests: " + guests.size());  // Для отладки
            // Передаем список гостей в JSP
            request.setAttribute("guests", guests);
            // Перенаправляем на страницу guests.jsp
            request.getRequestDispatcher("/jsp/guest.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Ошибка при получении списка гостей", e);
        }
    }

    @Override
    public void destroy() {
        try {
            // Закрываем соединение через MenuDAO
            if (guestDAO != null) {
                guestDAO.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
