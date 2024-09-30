<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.restaurantweb.models.Feedback" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
  <title>Отзывы</title>
  <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>

<!-- Навигационное меню -->
<nav>
  <a href="menu">Меню</a> |
  <a href="guests">Гости</a> |
  <a href="orders">Заказы</a> |
  <a href="feedbacks">Отзывы</a>
</nav>

<h1>Отзывы о блюдах</h1>
<table>
  <tr>
    <th>ID отзыва</th>
    <th>ID блюда</th>
    <th>ID гостя</th>
    <th>Отзыв</th>
    <th>Оценка</th>
  </tr>
  <%
    List<Feedback> feedbacks = (List<Feedback>) request.getAttribute("feedbacks");
    for (Feedback feedback : feedbacks) {
  %>
  <tr>
    <td><%= feedback.getId() %></td>
    <td><%= feedback.getMealId() %></td>
    <td><%= feedback.getGuestId() %></td>
    <td><%= feedback.getFeedbackText() %></td>
    <td><%= feedback.getRating() %></td>
  </tr>
  <% } %>
</table>
</body>
</html>
