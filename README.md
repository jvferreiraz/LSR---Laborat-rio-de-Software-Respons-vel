 # FindGo - Sistema de Achados e Perdidos

![Badge Java](https://img.shields.io/badge/Java-79.2%25-ED8936?style=flat-square)
![Badge CSS](https://img.shields.io/badge/CSS-19.4%25-1572B6?style=flat-square)
![Badge JavaScript](https://img.shields.io/badge/JavaScript-1.4%25-F7DF1E?style=flat-square)
![License](https://img.shields.io/badge/license-Código%20Aberto-brightgreen?style=flat-square)

## 📋 Descrição

**FindGo** é uma plataforma web gratuita e responsável destinada a conectar pessoas que perderam ou encontraram itens. O sistema facilita o cadastro, busca e comunicação entre usuários, ajudando na recuperação de objetos perdidos de forma simples, segura e intuitiva.

A aplicação surgiu como um projeto do **Laboratório de Software Responsável (LSR)**, com foco em oferecer soluções tecnológicas que beneficiem a comunidade local.

---

## ✨ Funcionalidades

- ✅ **Cadastro e Autenticação de Usuários** - Registro seguro com sistema de recuperação de senha
- ✅ **Publicação de Itens** - Divulgue itens perdidos ou encontrados com descrição detalhada
- ✅ **Filtros Avançados** - Busque por tipo de item (achado/perdido)
- ✅ **Geolocalização** - Registro de localização onde o item foi visto ou encontrado
- ✅ **Gerenciamento de Publicações** - Edite ou delete seus anúncios
- ✅ **Formulário de Contato** - Entre em contato direto com a plataforma
- ✅ **Interface Responsiva** - Design moderno e otimizado para todos os dispositivos
- ✅ **Banco de Dados Seguro** - Armazenamento confiável de informações do usuário

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Descrição | Porcentagem |
|-----------|-----------|-----------|
| **Java** | Backend e lógica de negócio | 79.2% |
| **CSS** | Estilização e design responsivo | 19.4% |
| **JavaScript** | Interatividade frontend | 1.4% |
| **MySQL** | Banco de dados relacional | - |
| **JSP** | Páginas dinâmicas no servidor | - |

---

## 📁 Estrutura do Projeto

```
LSR---Laborat-rio-de-Software-Respons-vel/
├── LSR_achador e perdidos/          # Diretório principal da aplicação
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/               # Código Java
│   │   │   └── webapp/             # Arquivos JSP e recursos web
│   │   └── ...
│   └── pom.xml                     # Configuração Maven
├── banco.sql                        # Script de criação do banco de dados
└── README.md                        # Este arquivo
```

---

## 🗄️ Estrutura do Banco de Dados

### Tabela: `usuarios`
```sql
- id (INT, PK, Auto-increment)
- nome (VARCHAR 255)
- email (VARCHAR 255, UNIQUE)
- senha (VARCHAR 255)
- token_reset (VARCHAR 255)
- data_expiracao_token (DATETIME)
- data_criacao (TIMESTAMP)
```

### Tabela: `itens`
```sql
- id (INT, PK, Auto-increment)
- titulo (VARCHAR 255)
- tipo (VARCHAR 100) -- 'achado' ou 'perdido'
- localizacao (VARCHAR 255)
- descricao (TEXT)
- data_criacao (TIMESTAMP)
```

---

## 🚀 Como Começar

### Pré-requisitos
- **Java 8+** ou superior
- **MySQL Server 5.7+**
- **Apache Tomcat 9.0+** (ou servidor similar)
- **Maven 3.6+** (para build)

### Instalação

1. **Clone o repositório:**
```bash
git clone https://github.com/jvferreiraz/LSR---Laborat-rio-de-Software-Respons-vel.git
cd LSR---Laborat-rio-de-Software-Respons-vel
```

2. **Configure o Banco de Dados:**
```bash
mysql -u root -p < banco.sql
```

3. **Configure a conexão no projeto:**
   - Localize o arquivo de configuração de banco de dados
   - Atualize as credenciais (usuário, senha, host)

4. **Compile o projeto:**
```bash
mvn clean install
```

5. **Deploy no Tomcat:**
   - Copie o arquivo `.war` gerado para a pasta `webapps` do Tomcat
   - Inicie o servidor Tomcat

6. **Acesse a aplicação:**
```
http://localhost:8080/LSR_achador-e-perdidos
```

---

## 📝 Como Usar

### Para Usuários

1. **Registre-se ou Faça Login**
   - Crie uma conta com email e senha segura
   - Recupere sua senha se necessário

2. **Publique um Item**
   - Clique em "Divulgue um Item"
   - Selecione o tipo (Achado/Perdido)
   - Adicione título, localização e descrição detalhada
   - Envie o anúncio

3. **Busque Itens**
   - Use os filtros para procurar itens específicos
   - Navegue pelas publicações disponíveis

4. **Entre em Contato**
   - Use o formulário de contato no rodapé
   - Nos envie sugestões ou denuncie problemas

---

## 🔧 Desenvolvimento

### Estrutura do Código

- **Backend Java**: Controladores, serviços e modelos de dados
- **Frontend JSP**: Páginas dinâmicas com lógica renderizada no servidor
- **Estilos CSS**: Design responsivo e moderno
- **JavaScript**: Validações e interatividade

### Como Contribuir

1. Faça um fork do repositório
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

---

## 🔐 Segurança

- **Senhas**: Armazenadas de forma segura (hash recomendado: bcrypt)
- **Email**: Validação e recuperação de conta
- **Autenticação**: Sistema de tokens para reset de senha
- **Dados**: Isolamento seguro no banco de dados MySQL

---

## 📞 Contato e Suporte

Para dúvidas, sugestões ou relatório de bugs:

- 📧 **Email**: [Utilize o formulário de contato na plataforma]
- 🐙 **GitHub Issues**: [Abra uma issue neste repositório](https://github.com/jvferreiraz/LSR---Laborat-rio-de-Software-Respons-vel/issues)
- 👤 **Proprietário**: [@jvferreiraz](https://github.com/jvferreiraz)

---

## 📄 Licença

Este projeto é código aberto e está disponível para uso educacional e comunitário.

---

## 👥 Equipe de Desenvolvimento

- **Thiago Gonzalez Voltolini** - Desenvolvedor Principal
- **jvferreiraz** - Proprietário do Repositório
- **Joaopedroslk** - Analista do Projeto

---

## 🎯 Objetivos do Projeto (LSR)

O FindGo foi desenvolvido como parte do **Laboratório de Software Responsável**, com os seguintes princípios:

- ✅ Criar soluções tecnológicas que beneficiem a comunidade
- ✅ Praticar desenvolvimento seguro e responsável
- ✅ Aprender boas práticas de engenharia de software
- ✅ Manter a plataforma **gratuita e acessível para todos**

---

## 📊 Status do Projeto

- ✅ Funcionalidade de email de contato implementada
- 🔄 Melhorias contínuas em andamento
- 📈 Expansão de funcionalidades planejada

---

**Última atualização**: Junho de 2026

*Relatar, encontre e ajude alguém localmente!*

