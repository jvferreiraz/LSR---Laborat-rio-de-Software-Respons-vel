create database achados_perdidos;
use achados_perdidos;

CREATE TABLE usuarios (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    token_reset VARCHAR(255),
    data_expiracao_token DATETIME,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE itens (
    id INT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(255),
    tipo VARCHAR(100),
    localizacao VARCHAR(255),
    descricao TEXT,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);