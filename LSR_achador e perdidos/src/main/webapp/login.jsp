<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Achados e Perdidos</title>
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

        /* Elementos decorativos de fundo */
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

        .login-container {
            position: relative;
            z-index: 10;
            width: 100%;
            max-width: 420px;
            padding: 20px;
        }

        .login-card {
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

        .login-header {
            text-align: center;
            margin-bottom: 40px;
        }

        .logo {
            font-size: 3rem;
            margin-bottom: 15px;
        }

        .login-header h1 {
            font-size: 28px;
            color: #5d4037;
            font-weight: 700;
            margin-bottom: 8px;
        }

        .login-header p {
            color: #8b6f47;
            font-size: 14px;
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

        input[type="email"],
        input[type="password"] {
            width: 100%;
            padding: 12px 16px;
            border: 2px solid #e0d5c7;
            border-radius: 12px;
            font-size: 14px;
            font-family: inherit;
            transition: all 0.3s ease;
            background: #fafaf8;
        }

        input[type="email"]:focus,
        input[type="password"]:focus {
            outline: none;
            border-color: #d4a574;
            background: #fff;
            box-shadow: 0 0 0 3px rgba(212, 165, 116, 0.1);
        }

        .remember-forgot {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 25px;
            font-size: 14px;
        }

        .remember-forgot label {
            margin: 0;
            display: flex;
            align-items: center;
            cursor: pointer;
            font-weight: 500;
        }

        .remember-forgot input[type="checkbox"] {
            margin-right: 6px;
            cursor: pointer;
            accent-color: #8b6f47;
        }

        .remember-forgot a {
            color: #8b6f47;
            text-decoration: none;
            transition: color 0.3s ease;
            font-weight: 500;
        }

        .remember-forgot a:hover {
            color: #d4a574;
        }

        .login-btn {
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

        .login-btn:hover {
            transform: translateY(-2px);
            box-shadow: 0 12px 30px rgba(139, 111, 71, 0.4);
        }

        .login-btn:active {
            transform: translateY(0);
        }

        .divider {
            text-align: center;
            margin: 30px 0;
            position: relative;
            color: #c0b5a8;
            font-size: 13px;
            font-weight: 600;
        }

        .divider::before {
            content: '';
            position: absolute;
            left: 0;
            top: 50%;
            width: 40%;
            height: 1px;
            background: #e0d5c7;
        }

        .divider::after {
            content: '';
            position: absolute;
            right: 0;
            top: 50%;
            width: 40%;
            height: 1px;
            background: #e0d5c7;
        }

        .social-login {
            display: flex;
            gap: 12px;
            margin-bottom: 25px;
        }

        .social-btn {
            flex: 1;
            padding: 12px;
            border: 2px solid #e0d5c7;
            background: #fafaf8;
            border-radius: 12px;
            cursor: pointer;
            transition: all 0.3s ease;
            font-size: 18px;
            color: #5d4037;
        }

        .social-btn:hover {
            border-color: #d4a574;
            background: #fff;
            transform: translateY(-2px);
        }

        .signup-link {
            text-align: center;
            color: #8b6f47;
            font-size: 14px;
        }

        .signup-link a {
            color: #d4a574;
            text-decoration: none;
            font-weight: 700;
            transition: color 0.3s ease;
        }

        .signup-link a:hover {
            color: #8b6f47;
        }

        @media (max-width: 480px) {
            .login-card {
                padding: 40px 25px;
            }

            .login-header h1 {
                font-size: 24px;
            }

            .logo {
                font-size: 2.5rem;
            }
        }
    </style>
</head>
<body>
    <div class="login-container">
        <div class="login-card">
            <div class="login-header">
                <div class="logo">📖</div>
                <h1>Achados e Perdidos</h1>
                <p>Encontre e compartilhe seus itens</p>
            </div>

            <form action="${pageContext.request.contextPath}/index.jsp" method="POST">
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" id="email" name="email" placeholder="seu@email.com" required>
                </div>

                <div class="form-group">
                    <label for="password">Senha</label>
                    <input type="password" id="password" name="password" placeholder="Digite sua senha" required>
                </div>

                <div class="remember-forgot">
                    <label>
                        <input type="checkbox" name="remember">
                        Lembrar de mim
                    </label>
                    <a href="#">Esqueceu a senha?</a>
                </div>

                <button type="submit" class="login-btn">
                    <i class="fas fa-sign-in-alt"></i> Entrar
                </button>
            </form>

            <div class="divider">OU</div>

            <div class="social-login">
                <button class="social-btn" title="Login com Google">
                    <i class="fab fa-google"></i>
                </button>
                <button class="social-btn" title="Login com Facebook">
                    <i class="fab fa-facebook"></i>
                </button>
                <button class="social-btn" title="Login com GitHub">
                    <i class="fab fa-github"></i>
                </button>
            </div>

            <p class="signup-link">
                Não tem conta? <a href="#">Criar conta</a>
            </p>
        </div>
    </div>

    <script>
        // Lembrar email
        const emailInput = document.getElementById('email');
        const rememberCheckbox = document.querySelector('input[name="remember"]');

        const savedEmail = localStorage.getItem('userEmail');
        if (savedEmail) {
            emailInput.value = savedEmail;
            rememberCheckbox.checked = true;
        }

        rememberCheckbox.addEventListener('change', function() {
            if (this.checked) {
                localStorage.setItem('userEmail', emailInput.value);
            } else {
                localStorage.removeItem('userEmail');
            }
        });

        emailInput.addEventListener('change', function() {
            if (rememberCheckbox.checked) {
                localStorage.setItem('userEmail', this.value);
            }
        });
    </script>
</body>
</html>
