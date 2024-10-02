<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.restaurantweb.models.Menu" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Меню</title>
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

<h1>Меню</h1>
<table>
    <tr>
        <th>ID</th>
        <th>Название блюда</th>
        <th>Стоимость</th>
        <th>Описание</th>
        <th>Тип блюда</th>
    </tr>
    <%
        List<Menu> menuItems = (List<Menu>) request.getAttribute("menuItems");
        for (Menu item : menuItems) {
    %>
    <tr>
        <td><%= item.getId() %></td>
        <td><%= item.getDishName() %></td>
        <td><%= item.getCost() %></td>
        <td><%= item.getPreview() %></td>
        <td><%= item.getDishType() %></td>
    </tr>
    <% } %>
</table>

<h2>Добавить новое блюдо</h2>
<form action="addDish" method="post">
    <label for="dishName">Название:</label>
    <input type="text" id="dishName" name="dishName" required>
    <br><br>
    <label for="cost">Цена:</label>
    <input type="number" step="0.01" id="cost" name="cost" required>
    <br><br>
    <label for="preview">Описание блюда:</label>
    <input id="preview" name="preview" required></input>
    <br><br>
    <label for="dish_type">Тип блюда:</label>
    <input type="text" id="dish_type" name="dish_type" required>
    <br><br>
    <input type="submit" value="Добавить блюдо">
</form>

</body>
</html>
