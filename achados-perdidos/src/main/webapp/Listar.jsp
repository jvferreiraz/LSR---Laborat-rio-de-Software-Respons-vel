<%@ page import="java.util.List" %>
<%@ page import="model.Item" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Achados e Perdidos</title>

<style>

body{
    font-family: Arial;
    background: #f4f4f4;
    padding: 20px;
}

.card{
    background: white;
    padding: 15px;
    margin: 10px 0;
    border-radius: 10px;
    box-shadow: 0 0 10px #ccc;
}

.botao{
    background: #0066ff;
    color: white;
    padding: 10px;
    text-decoration: none;
    border-radius: 5px;
}

</style>

</head>
<body>

<h1>Achados e Perdidos</h1>

<a class="botao" href="cadastro.jsp">
    Novo Item
</a>

<hr>

<%

List<Item> lista = (List<Item>) request.getAttribute("lista");

if(lista != null){

    for(Item item : lista){

%>

<div class="card">

    <h2><%= item.getDescricao() %></h2>

    <p>
        Categoria: <%= item.getCategoria() %>
    </p>

    <p>
        Status: <%= item.getStatus() %>
    </p>

</div>

<%
    }
}
%>

</body>
</html>