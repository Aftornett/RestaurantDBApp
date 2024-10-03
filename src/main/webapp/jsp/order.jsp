<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.restaurantweb.models.Order" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Заказы</title>
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

<h1>Список заказов</h1>
<table>
    <tr>
        <th>ID</th>
        <th>ID гостя</th>
        <th>Дата заказа</th>
        <th>Статус</th>
    </tr>
    <%
        List<Order> orders = (List<Order>) request.getAttribute("orders");
        for (Order order : orders) {
    %>
    <tr>
        <td><%= order.getId() %></td>
        <td><%= order.getGuestId() %></td>
        <td><%= order.getOrderDatetime() %></td>
        <td><%= order.getOrderStatus() %></td>
    </tr>
    <% } %>
</table>

<!-- Форма для добавления нового заказа -->
<h2>Добавить новый заказ</h2>
<form action="addOrder" method="post">
    <label for="guestId">ID гостя:</label>
    <input type="number" id="guestId" name="guestId" required><br><br>

    <label for="orderStatus">Статус заказа:</label>
    <input type="text" id="orderStatus" name="orderStatus" required><br><br>

    <input type="submit" value="Добавить заказ">
</form>

</body>
</html>
