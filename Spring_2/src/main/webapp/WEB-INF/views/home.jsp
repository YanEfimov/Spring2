<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<header>
	<p> ${message} </p>
</header>
<form:form action="dateProcess" method="post">
	<p>Введите дату рождения<input type="text" name="date"/></p>
	<p><input type="submit" value="submit"/>
</form:form>
<footer>
	<p>Maven version ...</p>
</footer>
</body>
</html>
