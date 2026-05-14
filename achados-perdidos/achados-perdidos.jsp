<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>


<%@ page import="java.util.*" %>
<%@ page import="Model.Item" %>

<h1>Lista de Itens</h1>

<%
List<Item> lista = (List<Item>) request.getAttribute("lista");

for (Item item : lista) {
%>
    <p><%= item.getDescricao() %></p>
<%
}
%>
