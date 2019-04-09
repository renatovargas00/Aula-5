<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="model.Pais" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Chame de Pais</title>
</head>
<body>
<%Pais pais = (Pais)request.getAttribute("pais"); %>
	Nome: <%=pais.getNome() %><br>
	populacao: <%=pais.getPopulacao() %><br>
	area: <%=pais.getArea() %><br>

</body>
</html>