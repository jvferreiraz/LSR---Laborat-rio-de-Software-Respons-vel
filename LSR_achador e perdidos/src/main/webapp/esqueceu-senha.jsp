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
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        html {
            scroll-behavior: smooth;
        }

        body {
            min-height: 100vh;
            font-family: 'Inter', 'Segoe UI', sans-serif;
            background: linear-gradient(135deg, #8b6f47 0%, #5d4037 50%, #3e2723 100%);
            display: flex;
            align-items: center;
            justify-content: center;
            overflow-x: hidden;
            position: relative;
        }

        body::before {
            content: '';
            position: absolute;
            width: 400px;
            height: 400px;
            background: rgba(212, 165, 116, 0.1);
            border-radius: 50%;
            top: -100px;
            left: -100px;
            animation: float 6s ease-in-out infinite;
        }

        body::after {
            content: '';
            position: absolute;
            width: 300px;
            height: 300px;
            background: rgba(139, 111, 71, 0.1);
            border-radius: 50%;
            bottom: -50px;
            right: -50px;
            animation: float 8s ease-in-out infinite reverse;
        }

        @keyframes float {
            0%, 100% { transform: translateY(0px); }
            50% { transform: translateY(20px); }
        }

        .container {
            position: relative;
            z-index: 10;
            width: 100%;
            max-width: 420px;
            padding: 20px;
        }

        .card {
            background: rgba(255, 255, 255, 0.95);
            backdrop-filter: blur(20px);
            -webkit-backdrop-filter: blur(20px);
            border: 1px solid rgba(255, 255, 255, 0.2);
            border-radius: 20px;
            padding: 50px 40px;
            box-shadow: 0 20px 60px rgba(0, 0, 0, 0.3);
            animation: slideUp 0.6s ease-out;
        }

        @keyframes slideUp {
            from {
                opacity: 0;
                transform: translateY(30px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        .header {
            text-align: center;
            margin-bottom: 40px;
        }

        .logo {
            font-size: 3rem;
            margin-bottom: 15px;
        }

        .header h1 {
            font-size: 28px;
            color: #5d4037;
            font-weight: 700;
            margin-bottom: 8px;
        }

        .header p {
            color: #8b6f47;
            font-size: 14px;
        }

        .alert {
            padding: 12px 16px;
            border-radius: 12px;
            margin-bottom: 20px;
            font-size: 14px;
            animation: slideDown 0.3s ease-out;
            display: flex;
            align-items: center;
            gap: 10px;
        }

        .alert-error {
            background: #fee2e2;
            color: #dc2626;
            border: 1px solid #fca5a5;
        }

        .alert-success {
            background: #dcfce7;
            color: #059669;
            border: 1px solid #86efac;
        }

        @keyframes slideDown {
            from {
                opacity: 0;
                transform: translateY(-10px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-bottom: 8px;
            color: #5d4037;
            font-weight: 600;
            font-size: 14px;
        }

        input[type="email"] {
            width: 100%;
            padding: 12px 16px;
            border: 2px solid #e0d5c7;
            border-radius: 12px;
            font-size: 14px;
            font-family: inherit;
            transition: all 0.3s ease;
            background: #fafaf8;
        }

        input[type="email"]:focus {
            outline: none;
            border-color: #d4a574;
            background: #fff;
            box-shadow: 0 0 0 3px rgba(212, 165, 116, 0.1);
        }

        .submit-btn {
            width: 100%;
            padding: 13px;
            background: linear-gradient(135deg, #8b6f47 0%, #d4a574 100%);
            color: white;
            border: none;
            border-radius: 12px;
            font-size: 15px;
            font-weight: 700;
            cursor: pointer;
            transition: all 0.3s ease;
            box-shadow: 0 8px 20px rgba(139, 111, 71, 0.3);
        }

        .submit-btn:hover {
            transform: translateY(-2px);
            box-shadow: 0 12px 30px rgba(139, 111, 71, 0.4);
        }

        .back-link {
            text-align: center;
            color: #8b6f47;
            font-size: 14px;
            margin-top: 25px;
        }

        .back-link a {
            color: #d4a574;
            text-decoration: none;
            font-weight: 700;
            transition: color 0.3s ease;
        }

        .back-link a:hover {
            color: #8b6f47;
        }

        .info-text {
            text-align: center;
            color: #8b6f47;
            font-size: 13px;
            margin-top: 20px;
            padding: 15px;
            background: rgba(212, 165, 116, 0.05);
            border-radius: 12px;
            line-height: 1.6;
        }

        @media (max-width: 480px) {
            .card {
                padding: 40px 25px;
            }

            .header h1 {
                font-size: 24px;
            }

            .logo {
                font-size: 2.5rem;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="card">
            <div class="header">
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

                <button type="submit" class="submit-btn">
                    <i class="fas fa-paper-plane"></i> Enviar Link de Recuperação
                </button>
            </form>

            <div class="info-text">
                <i class="fas fa-info-circle"></i> Um link de recuperação será enviado para seu email. O link é válido por 1 hora.
            </div>

            <p class="back-link">
                <a href="${pageContext.request.contextPath}/login.jsp">
                    <i class="fas fa-arrow-left"></i> Voltar para login
                </a>
            </p>
        </div>
    </div>
</body>
</html>