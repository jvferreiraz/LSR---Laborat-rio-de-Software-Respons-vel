<%@ page import="java.util.List" %>
<%@ page import="model.Item" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Achados e Perdidos</title>

<style>

    body {
        font-family: Arial;
        background: #f4f4f4;
        padding: 20px;
    }
    
    .container {
        max-width: 1000px;
        margin: 0 auto;
    }
    
    h1 {
        color: #333;
        text-align: center;
        margin-bottom: 30px;
    }
    
    .button-group {
        margin-bottom: 20px;
        text-align: center;
    }
    
    .botao {
        background: #0066ff;
        color: white;
        padding: 12px 25px;
        text-decoration: none;
        border-radius: 5px;
        display: inline-block;
        margin: 5px;
        cursor: pointer;
        border: none;
        font-size: 14px;
    }
    
    .botao:hover {
        background: #0052cc;
    }
    
    .botao-danger {
        background: #ff3333;
    }
    
    .botao-danger:hover {
        background: #cc0000;
    }
    
    .card {
        background: white;
        padding: 15px;
        margin: 15px 0;
        border-radius: 10px;
        box-shadow: 0 0 10px #ccc;
        border-left: 5px solid #0066ff;
    }
    
    .card h2 {
        margin-top: 0;
        color: #333;
        font-size: 18px;
    }
    
    .card p {
        margin: 8px 0;
        color: #666;
    }
    
    .card-info {
        display: grid;
        grid-template-columns: 1fr 1fr;
        gap: 15px;
        margin-bottom: 15px;
    }
    
    .info-item {
        font-size: 14px;
    }
    
    .label {
        font-weight: bold;
        color: #333;
    }
    
    .status-badge {
        display: inline-block;
        padding: 5px 10px;
        border-radius: 3px;
        font-size: 12px;
        font-weight: bold;
        color: white;
    }
    
    .status-encontrado {
        background: #28a745;
    }
    
    .status-perdido {
        background: #dc3545;
    }
    
    .status-devolvido {
        background: #007bff;
    }
    
    .status-em-analise {
        background: #ffc107;
        color: black;
    }
    
    .status-arquivado {
        background: #6c757d;
    }
    
    .observacao {
        background: #f9f9f9;
        padding: 10px;
        border-left: 3px solid #0066ff;
        margin: 10px 0;
        font-style: italic;
    }
    
    .card-actions {
        text-align: right;
        margin-top: 15px;
        border-top: 1px solid #eee;
        padding-top: 15px;
    }
    
    .empty {
        text-align: center;
        padding: 40px;
        color: #999;
    }
    
    @media (max-width: 600px) {
        .card-info {
            grid-template-columns: 1fr;
        }
    }

</style>

</head>
<body>

<div class="container">
    <h1>📋 Achados e Perdidos</h1>

    <div class="button-group">
        <a class="botao" href="cadastro">+ Novo Item</a>
    </div>

    <hr>

    <%
        List<Item> lista = (List<Item>) request.getAttribute("lista");

        if(lista != null && !lista.isEmpty()) {
            for(Item item : lista) {
                String statusClass = "status-" + (item.getStatus() != null ? item.getStatus().toLowerCase() : "");
    %>

    <div class="card">

        <h2><%= item.getDescricao() %></h2>
        
        <div class="card-info">
            <div class="info-item">
                <span class="label">Categoria:</span> 
                <%= item.getCategoriaNome() != null ? item.getCategoriaNome() : "N/A" %>
            </div>
            
            <div class="info-item">
                <span class="label">Local:</span> 
                <%= item.getLocalEncontroNome() != null ? item.getLocalEncontroNome() : "N/A" %>
            </div>
            
            <div class="info-item">
                <span class="label">Data:</span> 
                <%= item.getDataEncontro() != null ? item.getDataEncontro() : "N/A" %>
            </div>
            
            <div class="info-item">
                <span class="label">Status:</span> 
                <span class="status-badge <%= statusClass %>">
                    <%= item.getStatusItemNome() != null ? item.getStatusItemNome() : item.getStatus() %>
                </span>
            </div>
        </div>

        <%
            if(item.getObservacao() != null && !item.getObservacao().isEmpty()) {
        %>
        <div class="observacao">
            <strong>Observação:</strong> <%= item.getObservacao() %>
        </div>
        <%
            }
        %>

        <div class="card-actions">
            <a class="botao" href="#">Editar</a>
            <form action="#" method="post" style="display:inline;">
                <button type="submit" class="botao botao-danger">Deletar</button>
            </form>
        </div>

    </div>

    <%
            }
        } else {
    %>

    <div class="empty">
        <p>Nenhum item cadastrado ainda.</p>
        <a class="botao" href="cadastro">Cadastre o primeiro item</a>
    </div>

    <%
        }
    %>

</div>

</body>
</html>
