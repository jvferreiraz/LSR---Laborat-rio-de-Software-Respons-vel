-- =========================================
-- Criação do banco
-- =========================================
CREATE DATABASE IF NOT EXISTS achados_perdidos;
USE achados_perdidos;

-- =========================================
-- Tabela: local_encontro
-- =========================================
CREATE TABLE IF NOT EXISTS local_encontro (
    id_local_encontro INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(45) NOT NULL,
    endereco VARCHAR(45),
    INDEX idx_nome (nome)
);

-- =========================================
-- Tabela: categorias
-- =========================================
CREATE TABLE IF NOT EXISTS categorias (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(45) NOT NULL,
    INDEX idx_nome (nome)
);

-- =========================================
-- Tabela: status_item
-- =========================================
CREATE TABLE IF NOT EXISTS status_item (
    id_status_item INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(45) NOT NULL
);

-- =========================================
-- Tabela: item
-- =========================================
CREATE TABLE IF NOT EXISTS item (
    id_item INT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    data_encontro DATE,
    observacao TEXT,
    status ENUM('perdido', 'encontrado', 'devolvido') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    categorias_id_categoria INT,
    local_encontro_id_local_encontro INT,
    status_item_id_status_item INT,
    INDEX idx_status (status),
    INDEX idx_descricao (descricao),
    INDEX idx_created_at (created_at),
    CONSTRAINT fk_item_categoria
        FOREIGN KEY (categorias_id_categoria)
        REFERENCES categorias(id_categoria),
    CONSTRAINT fk_item_local
        FOREIGN KEY (local_encontro_id_local_encontro)
        REFERENCES local_encontro(id_local_encontro),
    CONSTRAINT fk_item_status
        FOREIGN KEY (status_item_id_status_item)
        REFERENCES status_item(id_status_item)
);

-- =========================================
-- Tabela: audit_log
-- =========================================
CREATE TABLE IF NOT EXISTS audit_log (
    id INT AUTO_INCREMENT PRIMARY KEY,
    acao VARCHAR(50) NOT NULL,
    data_acao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    item_id_item INT,
    INDEX idx_data_acao (data_acao),
    CONSTRAINT fk_audit_item
        FOREIGN KEY (item_id_item)
        REFERENCES item(id_item)
);

-- =========================================
-- SEED: local_encontro
-- =========================================
INSERT INTO local_encontro (nome, endereco) VALUES
('Terminal Central', 'Av. Brasil, 100'),
('Shopping Norte', 'Rua das Flores, 250'),
('Parque Municipal', 'Av. Verde, 890'),
('Rodoviária Sul', 'Rua do Transporte, 45'),
('Universidade Federal', 'Campus Central, Bloco A');

-- =========================================
-- SEED: categorias
-- =========================================
INSERT INTO categorias (nome) VALUES
('Eletrônicos'),
('Documentos'),
('Roupas'),
('Acessórios'),
('Objetos Pessoais');

-- =========================================
-- SEED: status_item
-- =========================================
INSERT INTO status_item (nome) VALUES
('Perdido'),
('Encontrado'),
('Em análise'),
('Devolvido'),
('Arquivado');

-- =========================================
-- SEED: item
-- =========================================
INSERT INTO item (
    descricao,
    data_encontro,
    observacao,
    status,
    categorias_id_categoria,
    local_encontro_id_local_encontro,
    status_item_id_status_item
) VALUES
(
    'Celular Samsung Galaxy S21',
    '2026-05-01',
    'Encontrado próximo à praça de alimentação',
    'encontrado',
    1,
    2,
    2
),
(
    'Carteira preta com documentos',
    '2026-05-02',
    'Possui CNH e cartões bancários',
    'perdido',
    2,
    1,
    1
),
(
    'Jaqueta jeans azul',
    '2026-05-03',
    'Esquecida em um banco do parque',
    'encontrado',
    3,
    3,
    2
),
(
    'Relógio prata',
    '2026-05-04',
    'Marca Casio',
    'devolvido',
    4,
    4,
    4
),
(
    'Mochila escolar preta',
    '2026-05-05',
    'Continha cadernos e estojo',
    'perdido',
    5,
    5,
    3
);
