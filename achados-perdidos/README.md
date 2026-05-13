# 🔍 Achados e Perdidos - Sistema Completo

Sistema web integrado para gerenciamento de achados e perdidos com frontend responsivo, backend robusto em Spring Boot e banco de dados MySQL.

## 🎯 Características

### Frontend
- ✅ Interface moderna e responsiva
- ✅ Busca em tempo real com debounce
- ✅ Filtros por status (Perdido/Encontrado/Todos)
- ✅ Paginação intuitiva
- ✅ Modal para publicar itens
- ✅ Notificações Toast
- ✅ Loading spinners
- ✅ Menu mobile
- ✅ Animações suaves

### Backend
- ✅ API REST completa com Spring Boot 3.1.5
- ✅ CRUD completo para itens, categorias e locais
- ✅ Busca avançada com múltiplos filtros
- ✅ Paginação server-side
- ✅ Auditoria de operações
- ✅ Tratamento global de erros
- ✅ CORS configurado
- ✅ DTOs para segurança
- ✅ Validações em camadas

### Banco de Dados
- ✅ Schema otimizado com índices
- ✅ Relacionamentos entre entidades
- ✅ Auditoria com timestamps
- ✅ Seed com dados iniciais
- ✅ Integridade referencial

## 📋 Pré-requisitos

- **Java**: 17+
- **Maven**: 3.8+
- **MySQL**: 8.0+
- **Node.js**: Opcional (para ferramentas de build)

## 🚀 Instalação e Setup

### 1. Clonar o repositório

```bash
git clone <seu-repositorio>
cd achados-perdidos
```

### 2. Configurar Banco de Dados

#### 2.1 Criar banco de dados

```bash
mysql -u root -p < src/main/resources/database-schema.sql
```

#### 2.2 Configurar credenciais

Edite `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/achados_perdidos
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
```

### 3. Instalar dependências

```bash
mvn clean install
```

### 4. Executar aplicação

```bash
mvn spring-boot:run
```

Ou usando IDE:
1. Abra o projeto em sua IDE favorita (IntelliJ, Eclipse, etc.)
2. Execute a classe `AchadosApplication.java`

### 5. Acessar aplicação

```
URL: http://localhost:8080
```

## 📚 Documentação da API

### Base URL
```
http://localhost:8080/api
```

### Endpoints - Itens

#### Listar todos os itens
```
GET /items?page=0&size=10
```

**Response (200 OK):**
```json
{
  "content": [
    {
      "idItem": 1,
      "descricao": "Celular Samsung Galaxy S21",
      "dataEncontro": "2026-05-01",
      "observacao": "Encontrado próximo à praça de alimentação",
      "status": "encontrado",
      "categoriaNome": "Eletrônicos",
      "localEncontroNome": "Shopping Norte"
    }
  ],
  "pageNumber": 0,
  "pageSize": 10,
  "totalPages": 1,
  "totalElements": 5
}
```

#### Buscar itens
```
GET /items/buscar?termo=celular&status=encontrado&page=0&size=10
```

#### Listar por status
```
GET /items/status/perdido?page=0&size=10
```

#### Obter item específico
```
GET /items/{id}
```

#### Criar novo item
```
POST /items
Content-Type: application/json

{
  "descricao": "Celular Samsung Galaxy S21",
  "dataEncontro": "2026-05-13",
  "observacao": "Encontrado próximo à praça de alimentação",
  "status": "encontrado"
}
```

**Response (201 Created):**
```json
{
  "idItem": 6,
  "descricao": "Celular Samsung Galaxy S21",
  "status": "encontrado",
  ...
}
```

#### Atualizar item
```
PUT /items/{id}
Content-Type: application/json

{
  "descricao": "Celular Samsung Galaxy S21",
  "status": "devolvido",
  "observacao": "Devolvido ao proprietário"
}
```

#### Deletar item
```
DELETE /items/{id}
```

**Response (204 No Content)**

### Endpoints - Categorias

#### Listar categorias
```
GET /categorias
```

#### Criar categoria
```
POST /categorias
Content-Type: application/json

{
  "nome": "Celulares"
}
```

### Endpoints - Locais

#### Listar locais
```
GET /locais
```

#### Criar local
```
POST /locais
Content-Type: application/json

{
  "nome": "Terminal Central",
  "endereco": "Av. Brasil, 100"
}
```

## 🏗️ Arquitetura

### Estrutura do Backend

```
src/main/java/com/achados/
├── AchadosApplication.java       # Main + CORS Config
├── controller/                   # REST Controllers
│   ├── ItemController.java
│   ├── CategoriaController.java
│   └── LocalEncontroController.java
├── service/                      # Business Logic
│   ├── ItemService.java
│   ├── CategoriaService.java
│   └── LocalEncontroService.java
├── repository/                   # Data Access
│   ├── ItemRepository.java
│   ├── CategoriaRepository.java
│   ├── LocalEncontroRepository.java
│   └── AuditLogRepository.java
├── model/                        # JPA Entities
│   ├── Item.java
│   ├── Categoria.java
│   ├── LocalEncontro.java
│   ├── StatusItem.java
│   └── AuditLog.java
├── dto/                          # Data Transfer Objects
│   ├── ItemDTO.java
│   ├── CategoriaDTO.java
│   └── LocalEncontroDTO.java
└── exception/                    # Exception Handling
    └── GlobalExceptionHandler.java
```

### Estrutura do Frontend

```
src/main/resources/static/
├── index.html                    # HTML principal
├── style.css                     # Estilos CSS
├── api.js                        # Cliente API
└── app.js                        # Lógica da aplicação
```

## 🔒 Segurança

- ✅ DTOs para separação de camadas
- ✅ Validação de entrada em backend
- ✅ CORS configurado corretamente
- ✅ Escape HTML no frontend
- ✅ Tratamento de erros global
- ✅ Auditoria completa de operações

## 🛠️ Tecnologias Utilizadas

### Backend
- **Spring Boot** 3.1.5
- **Spring Data JPA**
- **MySQL Connector/J** 8.0.33
- **Lombok**
- **Validation API**

### Frontend
- **HTML5**
- **CSS3** (com Flexbox/Grid)
- **JavaScript Vanilla**
- **Font Awesome** 6.5.1

### Banco de Dados
- **MySQL** 8.0+
- **JPA/Hibernate**

## 📝 Exemplos de Uso

### JavaScript - Buscar itens

```javascript
// Buscar itens por termo
const items = await api.searchItems('celular', 'encontrado');

// Buscar por status
const perdidos = await api.getItemsByStatus('perdido');

// Criar novo item
await api.createItem({
  descricao: 'Carteira preta',
  status: 'perdido',
  dataEncontro: '2026-05-13',
  observacao: 'Com documentos'
});
```

## 🐛 Troubleshooting

### Erro de conexão com banco de dados

```
Check: Credenciais em application.properties
Check: MySQL está rodando
Check: Database foi criado com seed
```

### CORS Error no frontend

```
Solução: Verificar CorsConfig em AchadosApplication.java
Verificar: Base URL em api.js (http://localhost:8080/api)
```

### Porta 8080 em uso

```bash
# Mudar porta em application.properties
server.port=8081
```

## 📦 Deploy

### Build para produção

```bash
mvn clean package -DskipTests
```

Gerará: `target/achados-perdidos-1.0.0.jar`

### Executar JAR

```bash
java -jar target/achados-perdidos-1.0.0.jar
```

## 🤝 Contribuindo

1. Crie uma branch para sua feature
   ```bash
   git checkout -b feature/sua-feature
   ```

2. Commit suas mudanças
   ```bash
   git commit -am 'Add nova feature'
   ```

3. Push para a branch
   ```bash
   git push origin feature/sua-feature
   ```

4. Abra um Pull Request

## 📄 Licença

Este projeto está sob licença MIT. Veja LICENSE para mais detalhes.

## 👥 Autores

- **Laboratório de Software Responsável**
- **Desenvolvido por**: Seu Nome
- **Data**: 2026-05-13

## 📞 Suporte

Para suporte, abra uma issue no repositório ou entre em contato através de:
- Email: suporte@achadosperdidos.com
- Issues: GitHub Issues

---

**Desenvolvido com ❤️ para o Laboratório de Software Responsável**
