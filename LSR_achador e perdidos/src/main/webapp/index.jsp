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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <link rel="stylesheet" href="css/style.css">
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
    <div class="footer-grid">
        <div class="footer-col">
            <h3>📧 Contato com a Escola</h3>
            <p>Envie suas dúvidas ou sugestões diretamente para a escola FindGo.</p>
            <button class="cta" type="button" onclick="abrirModalEmailEscola()" style="width: 100%;">
                <i class="fas fa-envelope"></i> Enviar Email
            </button>
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

<!-- MODAL ENVIAR EMAIL PARA ESCOLA -->
<div id="modalEmailEscola" class="modal2">
    <div class="modal2-content">
        <span class="close2" onclick="document.getElementById('modalEmailEscola').style.display='none'">&times;</span>
        <h2><i class="fas fa-envelope"></i> Enviar Email para Escola</h2>
        <form action="${pageContext.request.contextPath}/enviar-email-escola" method="post">
            <textarea name="mensagem" placeholder="Digite sua mensagem..." required style="border: 1px solid var(--border); padding: 14px 16px; border-radius: 16px; font-size: 1rem; outline: none; min-height: 120px; resize: none;"></textarea>
            <button class="cta" type="submit">
                <i class="fas fa-paper-plane"></i> Enviar
            </button>
        </form>
    </div>
</div>

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
        const modalEmailEscola = document.getElementById("modalEmailEscola");
        
        if (event.target === modalAnuncio) {
            modalAnuncio.style.display = "none";
        }
        if (event.target === modalEdicao) {
            modalEdicao.style.display = "none";
        }
        if (event.target === modalEmailEscola) {
            modalEmailEscola.style.display = "none";
        }
    }

    function abrirModalEmailEscola() {
        document.getElementById('modalEmailEscola').style.display = 'flex';
    }
</script>
</body>
</html>
