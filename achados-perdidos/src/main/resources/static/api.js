// ================================
// API CLIENT - ACHADOS E PERDIDOS
// ================================

class AchadosPerdidosAPI {
    constructor(baseURL = 'http://localhost:8080/api') {
        this.baseURL = baseURL;
    }

    async request(endpoint, options = {}) {
        const url = `${this.baseURL}${endpoint}`;
        const defaultOptions = {
            headers: {
                'Content-Type': 'application/json',
            },
        };

        const config = { ...defaultOptions, ...options };

        try {
            const response = await fetch(url, config);

            if (!response.ok) {
                throw new Error(`HTTP ${response.status}: ${response.statusText}`);
            }

            return await response.json();
        } catch (error) {
            console.error('API Error:', error);
            throw error;
        }
    }

    // ====== ITEMS ======
    async getItems(page = 0, size = 10) {
        return this.request(`/items?page=${page}&size=${size}`);
    }

    async searchItems(termo, status = null, page = 0, size = 10) {
        let endpoint = `/items/buscar?termo=${encodeURIComponent(termo)}&page=${page}&size=${size}`;
        if (status) {
            endpoint += `&status=${status}`;
        }
        return this.request(endpoint);
    }

    async getItemsByStatus(status, page = 0, size = 10) {
        return this.request(`/items/status/${status}?page=${page}&size=${size}`);
    }

    async getItemById(id) {
        return this.request(`/items/${id}`);
    }

    async createItem(itemData) {
        return this.request('/items', {
            method: 'POST',
            body: JSON.stringify(itemData),
        });
    }

    async updateItem(id, itemData) {
        return this.request(`/items/${id}`, {
            method: 'PUT',
            body: JSON.stringify(itemData),
        });
    }

    async deleteItem(id) {
        return this.request(`/items/${id}`, {
            method: 'DELETE',
        });
    }

    // ====== CATEGORIAS ======
    async getCategorias() {
        return this.request('/categorias');
    }

    async getCategoriaById(id) {
        return this.request(`/categorias/${id}`);
    }

    async createCategoria(categoriaData) {
        return this.request('/categorias', {
            method: 'POST',
            body: JSON.stringify(categoriaData),
        });
    }

    // ====== LOCAIS ======
    async getLocais() {
        return this.request('/locais');
    }

    async getLocalById(id) {
        return this.request(`/locais/${id}`);
    }

    async createLocal(localData) {
        return this.request('/locais', {
            method: 'POST',
            body: JSON.stringify(localData),
        });
    }
}

// Export API instance
const api = new AchadosPerdidosAPI();
