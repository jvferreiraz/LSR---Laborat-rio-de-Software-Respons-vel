// ================================
// APP - ACHADOS E PERDIDOS
// ================================

class AchadosPerdidosApp {
    constructor() {
        this.currentPage = 0;
        this.currentStatus = 'todos';
        this.searchTerm = '';
        this.pageSize = 10;
        this.totalPages = 0;

        this.initializeElements();
        this.attachEventListeners();
        this.loadItems();
    }

    initializeElements() {
        // DOM Elements
        this.modal = document.getElementById('modal');
        this.openModalBtn = document.getElementById('openModal');
        this.closeModalBtn = document.getElementById('closeModal');
        this.itemForm = document.getElementById('itemForm');
        this.contactForm = document.getElementById('contactForm');
        this.cardsContainer = document.getElementById('cardsContainer');
        this.loadingSpinner = document.getElementById('loading');
        this.paginationContainer = document.getElementById('paginationContainer');
        this.searchInput = document.getElementById('searchInput');
        this.filterButtons = document.querySelectorAll('.filter-btn');
        this.menuMobile = document.getElementById('menuMobile');
        this.navMenu = document.getElementById('navMenu');
        this.toast = document.getElementById('toast');
    }

    attachEventListeners() {
        // Modal
        this.openModalBtn.addEventListener('click', () => this.openModal());
        this.closeModalBtn.addEventListener('click', () => this.closeModal());
        window.addEventListener('click', (e) => {
            if (e.target === this.modal) this.closeModal();
        });

        // Forms
        this.itemForm.addEventListener('submit', (e) => this.handleCreateItem(e));
        this.contactForm.addEventListener('submit', (e) => this.handleContactForm(e));

        // Search
        this.searchInput.addEventListener('input', (e) => this.handleSearch(e));

        // Filters
        this.filterButtons.forEach((btn) => {
            btn.addEventListener('click', (e) => this.handleFilter(e));
        });

        // Mobile Menu
        this.menuMobile.addEventListener('click', () => this.toggleMobileMenu());

        // Close mobile menu on link click
        document.querySelectorAll('nav a').forEach((link) => {
            link.addEventListener('click', () => this.closeMobileMenu());
        });
    }

    async loadItems() {
        this.showLoading(true);
        try {
            let data;
            if (this.searchTerm) {
                const status = this.currentStatus !== 'todos' ? this.currentStatus : null;
                data = await api.searchItems(this.searchTerm, status, this.currentPage, this.pageSize);
            } else if (this.currentStatus !== 'todos') {
                data = await api.getItemsByStatus(this.currentStatus, this.currentPage, this.pageSize);
            } else {
                data = await api.getItems(this.currentPage, this.pageSize);
            }

            this.renderCards(data.content);
            this.totalPages = data.totalPages;
            this.renderPagination();
        } catch (error) {
            console.error('Erro ao carregar itens:', error);
            this.showToast('Erro ao carregar itens', 'error');
            this.cardsContainer.innerHTML = '<p class="error-message">Erro ao carregar itens. Tente novamente.</p>';
        } finally {
            this.showLoading(false);
        }
    }

    renderCards(items) {
        if (items.length === 0) {
            this.cardsContainer.innerHTML = '<p class="no-results">Nenhum item encontrado.</p>';
            return;
        }

        this.cardsContainer.innerHTML = items
            .map(
                (item) => `
            <div class="card">
                <span class="badge ${item.status}">${this.formatStatus(item.status)}</span>
                <h3>${this.escapeHtml(item.descricao)}</h3>
                <p class="local">
                    <i class="fas fa-map-marker-alt"></i>
                    ${item.localEncontroNome || 'Não especificado'}
                </p>
                <p class="categoria">
                    <i class="fas fa-tag"></i>
                    ${item.categoriaNome || 'Não categorizado'}
                </p>
                <p class="descricao">${this.escapeHtml(item.observacao || 'Sem observações')}</p>
                <small>${this.formatDate(item.dataEncontro)}</small>
            </div>
        `
            )
            .join('');
    }

    renderPagination() {
        this.paginationContainer.innerHTML = '';

        if (this.totalPages <= 1) return;

        const container = document.createElement('div');
        container.className = 'pagination-buttons';

        // Botão anterior
        if (this.currentPage > 0) {
            const prevBtn = document.createElement('button');
            prevBtn.textContent = 'Anterior';
            prevBtn.className = 'btn-pagination';
            prevBtn.addEventListener('click', () => this.previousPage());
            container.appendChild(prevBtn);
        }

        // Números de página
        for (let i = 0; i < this.totalPages; i++) {
            const pageBtn = document.createElement('button');
            pageBtn.textContent = i + 1;
            pageBtn.className = `btn-pagination ${i === this.currentPage ? 'active' : ''}`;
            pageBtn.addEventListener('click', () => this.goToPage(i));
            container.appendChild(pageBtn);
        }

        // Botão próximo
        if (this.currentPage < this.totalPages - 1) {
            const nextBtn = document.createElement('button');
            nextBtn.textContent = 'Próximo';
            nextBtn.className = 'btn-pagination';
            nextBtn.addEventListener('click', () => this.nextPage());
            container.appendChild(nextBtn);
        }

        this.paginationContainer.appendChild(container);
    }

    async handleCreateItem(e) {
        e.preventDefault();

        const nome = document.getElementById('itemNome').value.trim();
        const status = document.getElementById('itemStatus').value;
        const data = document.getElementById('itemData').value;
        const descricao = document.getElementById('itemDescricao').value.trim();

        if (!nome || !status || !descricao) {
            this.showToast('Preencha todos os campos obrigatórios', 'error');
            return;
        }

        const submitBtn = document.getElementById('submitBtn');
        submitBtn.disabled = true;
        submitBtn.textContent = 'Publicando...';

        try {
            const itemData = {
                descricao: nome,
                status: status,
                dataEncontro: data || null,
                observacao: descricao,
            };

            await api.createItem(itemData);
            this.showToast('Item publicado com sucesso!', 'success');
            this.itemForm.reset();
            this.closeModal();
            this.currentPage = 0;
            this.loadItems();
        } catch (error) {
            console.error('Erro ao criar item:', error);
            this.showToast('Erro ao publicar item', 'error');
        } finally {
            submitBtn.disabled = false;
            submitBtn.textContent = 'Publicar';
        }
    }

    handleContactForm(e) {
        e.preventDefault();
        this.showToast('Mensagem enviada com sucesso!', 'success');
        this.contactForm.reset();
    }

    handleSearch = this.debounce((e) => {
        this.searchTerm = e.target.value.trim();
        this.currentPage = 0;
        this.loadItems();
    }, 500);

    handleFilter(e) {
        this.filterButtons.forEach((btn) => btn.classList.remove('active'));
        e.target.classList.add('active');
        this.currentStatus = e.target.dataset.status;
        this.currentPage = 0;
        this.searchInput.value = '';
        this.searchTerm = '';
        this.loadItems();
    }

    openModal() {
        this.modal.style.display = 'flex';
    }

    closeModal() {
        this.modal.style.display = 'none';
        this.itemForm.reset();
    }

    showLoading(show) {
        this.loadingSpinner.style.display = show ? 'flex' : 'none';
    }

    showToast(message, type = 'info') {
        this.toast.textContent = message;
        this.toast.className = `toast show ${type}`;

        setTimeout(() => {
            this.toast.classList.remove('show');
        }, 3000);
    }

    toggleMobileMenu() {
        this.navMenu.classList.toggle('active');
    }

    closeMobileMenu() {
        this.navMenu.classList.remove('active');
    }

    nextPage() {
        if (this.currentPage < this.totalPages - 1) {
            this.currentPage++;
            this.loadItems();
            window.scrollTo({ top: 0, behavior: 'smooth' });
        }
    }

    previousPage() {
        if (this.currentPage > 0) {
            this.currentPage--;
            this.loadItems();
            window.scrollTo({ top: 0, behavior: 'smooth' });
        }
    }

    goToPage(page) {
        this.currentPage = page;
        this.loadItems();
        window.scrollTo({ top: 0, behavior: 'smooth' });
    }

    debounce(func, delay) {
        let timeoutId;
        return function (...args) {
            clearTimeout(timeoutId);
            timeoutId = setTimeout(() => func.apply(this, args), delay);
        };
    }

    formatStatus(status) {
        const statusMap = {
            perdido: 'PERDIDO',
            encontrado: 'ACHADO',
            devolvido: 'DEVOLVIDO',
        };
        return statusMap[status] || status.toUpperCase();
    }

    formatDate(dateStr) {
        if (!dateStr) return 'Data não informada';
        const date = new Date(dateStr);
        return date.toLocaleDateString('pt-BR');
    }

    escapeHtml(text) {
        const div = document.createElement('div');
        div.textContent = text;
        return div.innerHTML;
    }
}

// ================================
// INITIALIZE APP
// ================================
document.addEventListener('DOMContentLoaded', () => {
    new AchadosPerdidosApp();
});
