<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dao.ItemDAO,model.Item,model.Usuario,java.util.List" %>
<%
    Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
    if (usuarioLogado == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
    List<Item> lista = new ItemDAO().listarTodos();
%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>FindGo - Achados e Perdidos</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        :root {
            --bg: #f5f3f0;
            --surface: rgba(255, 255, 255, 0.78);
            --surface-solid: #ffffff;
            --primary: #8b6f47;
            --primary-light: #d4a574;
            --secondary: #5d4037;
            --text: #3e2723;
            --text-soft: #795548;
            --border: #d4a574;
            --danger: #ef4444;

            --gradient: linear-gradient(135deg, #8b6f47 0%, #d4a574 45%, #5d4037 100%);

            --shadow-sm: 0 4px 12px rgba(62, 39, 35, 0.08);
            --shadow-md: 0 12px 30px rgba(139, 111, 71, 0.15);
            --shadow-lg: 0 22px 60px rgba(62, 39, 35, 0.18);

            --radius: 20px;
            --transition: 0.28s cubic-bezier(0.4, 0, 0.2, 1);
        }

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
            background: linear-gradient(135deg, #faf8f6 0%, #f5f3f0 50%, #ede7e1 100%);
            color: var(--text);
            overflow-x: hidden;
        }

        /* ================= NAVBAR ================= */

        .container {
            width: min(1200px, 92%);
            margin: auto;
        }

        .modern-navbar {
            position: fixed;
            top: 18px;
            left: 50%;
            transform: translateX(-50%);
            width: min(1180px, 94%);
            z-index: 9999;

            backdrop-filter: blur(18px);
            -webkit-backdrop-filter: blur(18px);

            background: rgba(255, 255, 255, 0.85);
            border: 1px solid rgba(255, 255, 255, 0.5);
            border-radius: 24px;
            box-shadow: var(--shadow-sm);
            transition: var(--transition);
        }

        .nav-wrapper {
            height: 74px;
            display: flex;
            align-items: center;
            justify-content: space-between;
        }

        .brand {
            display: flex;
            align-items: center;
            gap: 12px;
            font-size: 1.45rem;
            font-weight: 900;
            letter-spacing: 0.5px;
            color: var(--primary);
            text-decoration: none;
        }

        .brand i {
            width: 42px;
            height: 42px;
            display: grid;
            place-items: center;
            border-radius: 14px;
            background: var(--gradient);
            color: #fff;
            box-shadow: var(--shadow-md);
        }

        .modern-navbar ul {
            display: flex;
            align-items: center;
            gap: 10px;
            list-style: none;
        }

        .modern-navbar a {
            position: relative;
            text-decoration: none;
            color: var(--text-soft);
            font-weight: 600;
            padding: 11px 18px;
            border-radius: 14px;
            transition: var(--transition);
        }

        .modern-navbar a::after {
            content: '';
            position: absolute;
            left: 16px;
            right: 16px;
            bottom: 6px;
            height: 2px;
            border-radius: 20px;
            background: var(--gradient);
            transform: scaleX(0);
            transition: var(--transition);
        }

        .modern-navbar a:hover {
            color: var(--primary);
            background: rgba(139, 111, 71, 0.08);
        }

        .modern-navbar a:hover::after {
            transform: scaleX(1);
        }

        .logout-btn {
            border: none;
            outline: none;
            padding: 12px 22px;
            border-radius: 16px;
            background: var(--gradient);
            color: #fff;
            font-weight: 700;
            cursor: pointer;
            box-shadow: var(--shadow-md);
            transition: var(--transition);
        }

        .logout-btn:hover {
            transform: translateY(-2px);
            box-shadow: 0 18px 35px rgba(139, 111, 71, 0.25);
        }

        /* ================= HERO ================= */

        .modern-hero {
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            position: relative;
            overflow: hidden;
            padding: 120px 20px 80px;
        }

        .modern-hero::before {
            content: '';
            position: absolute;
            width: 650px;
            height: 650px;
            background: radial-gradient(circle, #d4a57440 0%, transparent 70%);
            top: -250px;
            right: -180px;
            z-index: 0;
        }

        .modern-hero::after {
            content: '';
            position: absolute;
            width: 500px;
            height: 500px;
            background: radial-gradient(circle, #8b6f4735 0%, transparent 70%);
            bottom: -220px;
            left: -160px;
            z-index: 0;
        }

        .hero-content {
            position: relative;
            z-index: 2;
            max-width: 760px;
            text-align: center;
            animation: fadeUp 0.9s ease;
        }

        @keyframes fadeUp {
            from {
                opacity: 0;
                transform: translateY(30px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        .hero-badge {
            display: inline-flex;
            align-items: center;
            gap: 8px;
            padding: 10px 18px;
            border-radius: 999px;
            background: rgba(255, 255, 255, 0.8);
            backdrop-filter: blur(12px);
            border: 1px solid rgba(255, 255, 255, 0.4);
            margin-bottom: 24px;
            color: var(--primary);
            font-weight: 700;
        }

        .hero-content h1 {
            font-size: clamp(2.8rem, 7vw, 5rem);
            line-height: 1.05;
            font-weight: 900;
            margin-bottom: 24px;
        }

        .hero-content h1 span {
            background: var(--gradient);
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
        }

        .hero-content p {
            font-size: 1.15rem;
            line-height: 1.8;
            color: var(--text-soft);
            max-width: 650px;
            margin: auto auto 38px;
        }

        /* ================= SEARCH ================= */

        .search-box-glass {
            position: relative;
            width: min(620px, 100%);
            margin: auto;
            display: flex;
            align-items: center;
            padding: 10px 18px;
            border-radius: 24px;
            background: rgba(255, 255, 255, 0.85);
            backdrop-filter: blur(16px);
            border: 1px solid rgba(255, 255, 255, 0.4);
            box-shadow: var(--shadow-lg);
        }

        .search-box-glass input {
            width: 100%;
            border: none;
            outline: none;
            background: transparent;
            padding: 14px 10px;
            font-size: 1rem;
            color: var(--text);
        }

        .search-box-glass i {
            font-size: 1.1rem;
            color: var(--primary);
        }

        /* ================= BUTTON ================= */

        .cta {
            margin-top: 26px;
            border: none;
            outline: none;
            background: var(--gradient);
            color: #fff;
            font-size: 1rem;
            font-weight: 700;
            padding: 16px 30px;
            border-radius: 18px;
            cursor: pointer;
            box-shadow: 0 14px 35px rgba(139, 111, 71, 0.24);
            transition: var(--transition);
        }

        .cta:hover {
            transform: translateY(-3px) scale(1.02);
        }

        /* ================= SECTION ================= */

        .modern-itens {
            width: min(1200px, 92%);
            margin: 0 auto;
            padding: 90px 0;
        }

        .section-title {
            display: flex;
            justify-content: space-between;
            align-items: center;
            gap: 20px;
            margin-bottom: 42px;
        }

        .section-title h2 {
            font-size: 2rem;
            font-weight: 900;
        }

        .section-title h2 span {
            color: var(--primary);
        }

        /* ================= FILTERS ================= */

        .filters {
            display: flex;
            gap: 12px;
            flex-wrap: wrap;
        }

        .filters button {
            border: none;
            outline: none;
            padding: 10px 20px;
            border-radius: 14px;
            background: #fff;
            color: var(--text-soft);
            font-weight: 700;
            cursor: pointer;
            transition: var(--transition);
            box-shadow: var(--shadow-sm);
        }

        .filters button:hover,
        .filters button.active {
            background: var(--gradient);
            color: #fff;
        }

        /* ================= CARDS ================= */

        .modern-cards {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
            gap: 28px;
        }

        .modern-card {
            position: relative;
            overflow: hidden;
            padding: 28px;
            border-radius: 28px;
            background: var(--surface);
            backdrop-filter: blur(18px);
            border: 1px solid rgba(255, 255, 255, 0.4);
            box-shadow: var(--shadow-sm);
            transition: var(--transition);
        }

        .modern-card::before {
            content: '';
            position: absolute;
            inset: 0;
            background: linear-gradient(140deg, rgba(255, 255, 255, 0.3), transparent 45%);
            pointer-events: none;
        }

        .modern-card:hover {
            transform: translateY(-8px);
            box-shadow: var(--shadow-lg);
        }

        .modern-card .icon {
            width: 70px;
            height: 70px;
            display: grid;
            place-items: center;
            border-radius: 22px;
            background: var(--gradient);
            color: #fff;
            font-size: 1.8rem;
            margin-bottom: 22px;
        }

        .modern-card h3 {
            font-size: 1.25rem;
            margin-bottom: 12px;
        }

        .details {
            display: flex;
            flex-direction: column;
            gap: 12px;
        }

        .local {
            color: var(--primary);
            font-weight: 600;
        }

        small {
            color: var(--text-soft);
        }

        .badge {
            width: max-content;
            padding: 8px 14px;
            border-radius: 999px;
            font-size: 0.85rem;
            font-weight: 800;
        }

        .perdido {
            background: #fee2e2;
            color: #dc2626;
        }

        .encontrado {
            background: #dcfce7;
            color: #059669;
        }

        /* ================= CARD ACTIONS ================= */

        .card-actions {
            display: flex;
            gap: 8px;
            margin-top: 14px;
            padding-top: 12px;
            border-top: 1px solid rgba(255, 255, 255, 0.2);
        }

        .btn-edit, .btn-delete {
            flex: 1;
            padding: 9px 12px;
            border: none;
            border-radius: 10px;
            font-size: 0.85rem;
            font-weight: 700;
            cursor: pointer;
            transition: var(--transition);
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 6px;
        }

        .btn-edit {
            background: rgba(139, 111, 71, 0.1);
            color: var(--primary);
        }

        .btn-edit:hover {
            background: rgba(139, 111, 71, 0.2);
            transform: translateY(-2px);
        }

        .btn-delete {
            background: rgba(239, 68, 68, 0.1);
            color: #dc2626;
        }

        .btn-delete:hover {
            background: rgba(239, 68, 68, 0.2);
            transform: translateY(-2px);
        }

        /* ================= FOOTER ================= */

        .modern-footer {
            margin-top: 100px;
            padding: 70px 8% 30px;
            background: #3e2723;
            color: #fff;
            border-radius: 40px 40px 0 0;
        }

        .footer-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(260px, 1fr));
            gap: 40px;
        }

        .footer-col {
            display: flex;
            flex-direction: column;
            gap: 15px;
        }

        .footer-col h3 {
            margin-bottom: 5px;
            font-size: 1.2rem;
        }

        .footer-col p {
            color: #cbd5e1;
            line-height: 1.8;
        }

        .footer-copy {
            margin-top: 50px;
            padding-top: 24px;
            text-align: center;
            border-top: 1px solid rgba(255, 255, 255, 0.08);
            color: #94a3b8;
        }

        /* ================= FORM ================= */

        .contactform {
            display: flex;
            flex-direction: column;
            gap: 14px;
        }

        .contactform input,
        .contactform textarea {
            border: none;
            outline: none;
            padding: 14px 16px;
            border-radius: 16px;
            background: rgba(255, 255, 255, 0.08);
            color: #fff;
            transition: var(--transition);
            width: 100%;
        }

        .contactform input:focus,
        .contactform textarea:focus {
            background: rgba(255, 255, 255, 0.14);
        }

        .contactform textarea {
            min-height: 120px;
            resize: none;
        }

        /* ================= MODAL ================= */

        .modal2 {
            position: fixed;
            inset: 0;
            display: none;
            align-items: center;
            justify-content: center;
            background: rgba(15, 23, 42, 0.55);
            backdrop-filter: blur(8px);
            z-index: 99999;
        }

        .modal2-content {
            position: relative;
            width: min(580px, 94vw);
            padding: 30px;
            border-radius: 30px;
            background: #fff;
            box-shadow: var(--shadow-lg);
            animation: fadeUp 0.35s ease;
        }

        .modal2-content form {
            display: flex;
            flex-direction: column;
            gap: 14px;
            margin-top: 20px;
        }

        .modal2-content input,
        .modal2-content select,
        .modal2-content textarea {
            border: 1px solid var(--border);
            padding: 14px 16px;
            border-radius: 16px;
            font-size: 1rem;
            outline: none;
            transition: var(--transition);
            background-color: #fff;
            color: var(--text);
        }

        .modal2-content input:focus,
        .modal2-content textarea:focus,
        .modal2-content select:focus {
            border-color: var(--primary);
            box-shadow: 0 0 0 4px rgba(139, 111, 71, 0.1);
        }

        .close2 {
            position: absolute;
            top: 18px;
            right: 22px;
            cursor: pointer;
            font-size: 1.4rem;
            color: var(--text);
            transition: var(--transition);
        }

        .close2:hover {
            color: var(--primary);
        }

        /* ================= SCROLL ================= */

        ::-webkit-scrollbar {
            width: 10px;
        }

        ::-webkit-scrollbar-thumb {
            background: linear-gradient(to bottom, #8b6f47, #d4a574);
            border-radius: 20px;
        }

        /* ================= RESPONSIVO ================= */

        @media (max-width: 900px) {
            .modern-navbar ul {
                display: none;
            }

            .hero-content h1 {
                font-size: 3rem;
            }

            .section-title {
                flex-direction: column;
                align-items: flex-start;
            }
        }

        @media (max-width: 600px) {
            .modern-hero {
                padding-top: 140px;
            }

            .hero-content h1 {
                font-size: 2.4rem;
            }

            .search-box-glass {
                border-radius: 18px;
            }

            .modern-card {
                padding: 24px;
            }
        }
    </style>
</head>
<body>
<nav class="modern-navbar" id="mainNavbar" aria-label="Navegação Principal">
    <div class="container nav-wrapper">
        <a href="#inicio" class="brand" aria-label="FindGo - Ir para o início">
            <i class="fas fa-location-arrow" aria-hidden="true"></i> FindGo
        </a>

        <div class="nav-content">
            <ul class="nav-links">
                <li><a href="#inicio">Início</a></li>
                <li><a href="#itens">Itens</a></li>
                <li><a href="#contato">Contato</a></li>
                <li>
                    <button class="logout-btn" onclick="logout()" type="button">
                        <i class="fas fa-sign-out-alt"></i> <%= usuarioLogado.getNome() %> - Sair
                    </button>
                </li>
            </ul>
        </div>
    </div>
</nav>

<section class="modern-hero" id="inicio">
    <div class="hero-content">
        <h1>O ponto de encontro para<br><span>achados e perdidos</span><br>em sua cidade.</h1>
        <p>Encontre e divulgue objetos com facilidade, segurança e rapidez.<br>Somos a ponte entre pessoas honestas!</p>
        <div class="search-box-glass">
            <input type="text" id="searchInput" placeholder="Busque por nome, cor ou local..." />
            <i class="fas fa-search"></i>
        </div>
        <button class="cta" onclick="document.getElementById('modalAnuncio').style.display='flex'">
            <i class="fas fa-plus"></i> Anunciar item agora
        </button>
    </div>
</section>

<section class="modern-itens" id="itens">
    <div class="section-title">
        <h2><i class="fas fa-box-open"></i> Itens Recentes</h2>
        <div class="filters">
            <button class="active" data-filter="todos">Todos</button>
            <button data-filter="perdido">Perdidos</button>
            <button data-filter="achado">Achados</button>
        </div>
    </div>

    <div class="modern-cards">
        <% for (Item i : lista) { %>
        <div class="modern-card" data-type="<%= i.getTipo() %>">
            <div class="icon"><i class="fas fa-box"></i></div>
            <div class="details">
                <h3><%= i.getTitulo() %></h3>
                <span class="badge <%= "achado".equals(i.getTipo()) ? "encontrado" : "perdido" %>">
                    <%= i.getTipo().toUpperCase() %>
                </span>
                <p class="local"><i class="fas fa-map-marker-alt"></i> <%= i.getLocalizacao() %></p>
                <small><%= i.getDescricao() %></small>
                <div class="card-actions">
                    <button class="btn-edit" onclick="abrirEdicao(<%= i.getId() %>, '<%= i.getTitulo().replace("'", "\\'") %>', '<%= i.getTipo() %>', '<%= i.getLocalizacao().replace("'", "\\'") %>', '<%= i.getDescricao().replace("'", "\\'") %>')">
                        <i class="fas fa-edit"></i> Editar
                    </button>
                    <button class="btn-delete" onclick="confirmarDelecao(<%= i.getId() %>)">
                        <i class="fas fa-trash"></i> Deletar
                    </button>
                </div>
            </div>
        </div>
        <% } %>
    </div>
</section>

<footer class="modern-footer" id="contato">
    <div class="container footer-grid">
        <div class="footer-col">
            <h3>Fale com a gente</h3>
            <form class="contactform">
                <input type="text" placeholder="Seu nome" required>
                <input type="email" placeholder="Seu email" required>
                <textarea placeholder="Digite sua mensagem" required></textarea>
                <button class="cta" type="button">Enviar</button>
            </form>
        </div>
        <div class="footer-col">
            <h3>Transparência & Utilidade</h3>
            <p>O FindGo é gratuito e sempre será.<br>Relate, encontre e ajude alguém localmente!</p>
        </div>
    </div>
    <div class="footer-copy">
        <small>&copy; 2026 FindGo • Todos os direitos reservados</small>
    </div>
</footer>

<!-- MODAL PUBLICAR NOVO ITEM -->
<div id="modalAnuncio" class="modal2">
    <div class="modal2-content">
        <span class="close2" onclick="document.getElementById('modalAnuncio').style.display='none'">&times;</span>
        <h2>Divulgue um Item</h2>
        <form action="${pageContext.request.contextPath}/publicar" method="post">
            <input class="f-title" name="titulo" type="text" placeholder="Título do item" required>
            <select class="f-type" name="tipo" required>
                <option value="">Tipo</option>
                <option value="perdido">Perdido</option>
                <option value="achado">Achado</option>
            </select>
            <input class="f-local" name="localizacao" type="text" placeholder="Onde você viu/achou?" required>
            <textarea class="f-desc" name="descricao" placeholder="Descreva o item" required></textarea>
            <button class="cta" type="submit">Publicar</button>
        </form>
    </div>
</div>

<!-- MODAL EDITAR ITEM -->
<div id="modalEdicao" class="modal2">
    <div class="modal2-content">
        <span class="close2" onclick="document.getElementById('modalEdicao').style.display='none'">&times;</span>
        <h2>Editar Item</h2>
        <form action="${pageContext.request.contextPath}/editar" method="post">
            <input type="hidden" id="editId" name="id">
            <input class="f-title" id="editTitulo" name="titulo" type="text" placeholder="Título do item" required>
            <select class="f-type" id="editTipo" name="tipo" required>
                <option value="">Tipo</option>
                <option value="perdido">Perdido</option>
                <option value="achado">Achado</option>
            </select>
            <input class="f-local" id="editLocal" name="localizacao" type="text" placeholder="Onde você viu/achou?" required>
            <textarea class="f-desc" id="editDesc" name="descricao" placeholder="Descreva o item" required></textarea>
            <button class="cta" type="submit">Atualizar</button>
        </form>
    </div>
</div>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">

<script>
    function logout() {
        window.location.href = '${pageContext.request.contextPath}/logout';
    }

    function abrirEdicao(id, titulo, tipo, localizacao, descricao) {
        document.getElementById('editId').value = id;
        document.getElementById('editTitulo').value = titulo;
        document.getElementById('editTipo').value = tipo;
        document.getElementById('editLocal').value = localizacao;
        document.getElementById('editDesc').value = descricao;
        document.getElementById('modalEdicao').style.display = 'flex';
    }

    function confirmarDelecao(id) {
        if (confirm('Tem certeza que deseja deletar este item? Esta ação é irreversível.')) {
            window.location.href = '${pageContext.request.contextPath}/deletar?id=' + id;
        }
    }

    window.addEventListener('scroll', function() {
        let nav = document.getElementById('mainNavbar');
        if (window.scrollY > 70) { nav.style.background = 'rgba(255, 255, 255, 0.95)'; }
        else { nav.style.background = 'rgba(255, 255, 255, 0.85)'; }
    });

    const filterButtons = document.querySelectorAll(".filters button");
    const cards = document.querySelectorAll(".modern-card");

    filterButtons.forEach(btn => {
        btn.addEventListener("click", () => {
            filterButtons.forEach(b => b.classList.remove("active"));
            btn.classList.add("active");
            const filtro = btn.dataset.filter;
            cards.forEach(card => {
                const tipo = card.dataset.type;
                card.style.display = (filtro === "todos" || filtro === tipo) ? "block" : "none";
            });
        });
    });

    const searchInput = document.getElementById("searchInput");

    searchInput.addEventListener("input", () => {
        const value = searchInput.value.toLowerCase().trim();

        const filtroAtivo = document.querySelector(".filters button.active").dataset.filter;

        cards.forEach(card => {
            const title = card.querySelector("h3").innerText.toLowerCase();
            const tipo = card.dataset.type;

            const matchesSearch = value === "" || title.includes(value);
            const matchesFilter = filtroAtivo === "todos" || tipo === filtroAtivo;

            if (matchesSearch && matchesFilter) {
                card.style.display = "block";
            } else {
                card.style.display = "none";
            }
        });
    });

    // Fecha modais ao clicar fora delas
    window.onclick = function(event) {
        const modalAnuncio = document.getElementById("modalAnuncio");
        const modalEdicao = document.getElementById("modalEdicao");
        if (event.target === modalAnuncio) {
            modalAnuncio.style.display = "none";
        }
        if (event.target === modalEdicao) {
            modalEdicao.style.display = "none";
        }
    }
</script>
</body>
</html>