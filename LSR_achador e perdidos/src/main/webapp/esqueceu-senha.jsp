<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String erro = (String) request.getAttribute("erro");
    String sucesso = (String) request.getAttribute("sucesso");
%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Esqueceu a Senha - FindGo</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <link rel="stylesheet" href="css/login.css">
</head>
<body>
    <div class="login-container">
        <div class="login-card">
            <div class="login-header">
                <div class="logo"><i class="fas fa-location-arrow"></i></div>
                <h1>Recuperar Senha</h1>
                <p>Digite seu email para receber um link de recuperação</p>
            </div>

            <% if (erro != null) { %>
                <div class="alert alert-error">
                    <i class="fas fa-exclamation-circle"></i> <%= erro %>
                </div>
            <% } %>

            <% if (sucesso != null) { %>
                <div class="alert alert-success">
                    <i class="fas fa-check-circle"></i> <%= sucesso %>
                </div>
            <% } %>

            <form action="${pageContext.request.contextPath}/esqueceu-senha" method="POST">
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" id="email" name="email" placeholder="seu@email.com" required>
                </div>

                <button type="submit" class="login-btn">
                    <i class="fas fa-paper-plane"></i> Enviar Link de Recuperação
                </button>
            </form>

            <div class="info-text">
                <i class="fas fa-info-circle"></i> Um link de recuperação será enviado para seu email. O link é válido por 1 hora.
            </div>

            <p class="signup-link">
                <a href="${pageContext.request.contextPath}/login.jsp">
                    <i class="fas fa-arrow-left"></i> Voltar para login
                </a>
            </p>
        </div>
    </div>
</body>
</html>
