<%@ page import="java.util.List" %>
<%@ page import="model.Categoria" %>
<%@ page import="model.LocalEncontro" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastrar Item - Achados e Perdidos</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background: #f4f4f4;
        padding: 20px;
    }
    
    .container {
        max-width: 600px;
        margin: 0 auto;
        background: white;
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0 0 10px #ccc;
    }
    
    h1 {
        color: #333;
        text-align: center;
    }
    
    .form-group {
        margin-bottom: 15px;
    }
    
    label {
        display: block;
        margin-bottom: 5px;
        font-weight: bold;
        color: #555;
    }
    
    input[type="text"],
    input[type="date"],
    textarea,
    select {
        width: 100%;
        padding: 10px;
        border: 1px solid #ddd;
        border-radius: 5px;
        box-sizing: border-box;
        font-family: Arial, sans-serif;
    }
    
    textarea {
        resize: vertical;
        min-height: 100px;
    }
    
    button {
        background: #0066ff;
        color: white;
        padding: 12px 30px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        font-size: 16px;
        width: 100%;
    }
    
    button:hover {
        background: #0052cc;
    }
    
    .link {
        text-align: center;
        margin-top: 15px;
    }
    
    .link a {
        color: #0066ff;
        text-decoration: none;
    }
    
    .link a:hover {
        text-decoration: underline;
    }
    
    .error {
        background: #ffdddd;
        color: #cc0000;
        padding: 10px;
        border-radius: 5px;
        margin-bottom: 15px;
        display: none;
    }
</style>
</head>
<body>

<div class="container">
    <h1>Cadastrar Item - Achados e Perdidos</h1>
    
    <%
        String erro = request.getParameter("erro");
        if("true".equals(erro)) {
    %>
    <div class="error" style="display: block;">
        Erro ao cadastrar item. Tente novamente.
    </div>
    <%
        }
    %>
    
    <form action="cadastrar" method="post">

        <div class="form-group">
            <label for="descricao">Descrição do Item:</label>
            <input type="text" id="descricao" name="descricao" required>
        </div>

        <div class="form-group">
            <label for="categoria">Categoria:</label>
            <select id="categoria" name="categoria" required>
                <option value="">-- Selecione uma categoria --</option>
                <%
                    List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
                    if(categorias != null) {
                        for(Categoria cat : categorias) {
                %>
                <option value="<%= cat.getId() %>"><%= cat.getNome() %></option>
                <%
                        }
                    }
                %>
            </select>
        </div>

        <div class="form-group">
            <label for="local">Local do Encontro:</label>
            <select id="local" name="local" required>
                <option value="">-- Selecione um local --</option>
                <%
                    List<LocalEncontro> locais = (List<LocalEncontro>) request.getAttribute("locais");
                    if(locais != null) {
                        for(LocalEncontro local : locais) {
                %>
                <option value="<%= local.getId() %>"><%= local.getNome() %></option>
                <%
                        }
                    }
                %>
            </select>
        </div>

        <div class="form-group">
            <label for="data">Data do Encontro:</label>
            <input type="date" id="data" name="data" required>
        </div>

        <div class="form-group">
            <label for="observacao">Observação:</label>
            <textarea id="observacao" name="observacao"></textarea>
        </div>

        <button type="submit">Cadastrar Item</button>

    </form>
    
    <div class="link">
        <a href="itens">Voltar para Lista</a>
    </div>
</div>

</body>
</html>
