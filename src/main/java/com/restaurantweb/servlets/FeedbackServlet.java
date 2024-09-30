package com.restaurantweb.servlets;

import com.restaurantweb.dao.FeedbackDAO;
import com.restaurantweb.models.Feedback;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

//@WebServlet("/feedbacks")
public class FeedbackServlet extends HttpServlet {
    private FeedbackDAO feedbackDAO;

    @Override
    public void init() throws ServletException {
        // Получаем соединение из контекста приложения
        try {
            Connection connection = (Connection) getServletContext().getAttribute("DBConnection");
            feedbackDAO = new FeedbackDAO(connection); // Передаем соединение в DAO
        } catch (SQLException e) {
            throw new ServletException("Error connecting to the database", e); // Обрабатываем исключение
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Получаем все элементы меню через DAO
            List<Feedback> feedbacks = feedbackDAO.getAllFeedbacks();
            request.setAttribute("feedbacks", feedbacks);

            // Перенаправляем на JSP
            request.getRequestDispatcher("/jsp/feedback.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Ошибка при получении списка меню", e);
        }
    }
    @Override
    public void destroy() {
        try {
            // Закрываем соединение через MenuDAO
            if (feedbackDAO != null) {
                feedbackDAO.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
