<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  <!-- Подключаем JSTL -->

<!DOCTYPE html>
<html>
<head>
    <title>Гости</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css"> <!-- Если у вас есть стили -->
</head>
<body>

<!-- Навигационное меню -->
<nav>
    <a href="menu">Меню</a> |
    <a href="guests">Гости</a> |
    <a href="orders">Заказы</a> |
    <a href="feedbacks">Отзывы</a>
</nav>

<h1>Список гостей</h1>

<!-- Таблица с гостями -->
<table border="1">
    <tr>
        <th>ID</th>
        <th>Имя</th>
        <th>Номер телефона</th>
        <th>Адрес доставки</th>
    </tr>

    <!-- Цикл для отображения списка гостей -->
    <c:forEach var="guest" items="${guests}">
        <tr>
            <td><c:out value="${guest.id}" /></td>
            <td><c:out value="${guest.name}" /></td>
            <td><c:out value="${guest.phoneNumber}" /></td>
            <td><c:out value="${guest.deliveryAdr}" /></td>
        </tr>
    </c:forEach>
</table>

<form action="addGuest" method="post">
    <table>
        <tr>
            <td><label for="guestName">Имя гостя:</label></td>
            <td><input type="text" id="guestName" name="guestName" required></td>
        </tr>
        <tr>
            <td><label for="guestPhone">Номер телефона:</label></td>
            <td><input type="text" id="guestPhone" name="guestPhone" required></td>
        </tr>
        <tr>
            <td><label for="deliveryAdr">Адрес доставки:</label></td>
            <td><input type="text" id="deliveryAdr" name="deliveryAdr"></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Добавить гостя"></td>
        </tr>
    </table>
</form>


</body>
</html>
