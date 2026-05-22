package model;

import java.sql.Timestamp;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private String tokenReset;
    private Timestamp dataExpiracaoToken;
    private Timestamp dataCriacao;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getTokenReset() { return tokenReset; }
    public void setTokenReset(String tokenReset) { this.tokenReset = tokenReset; }

    public Timestamp getDataExpiracaoToken() { return dataExpiracaoToken; }
    public void setDataExpiracaoToken(Timestamp dataExpiracaoToken) { this.dataExpiracaoToken = dataExpiracaoToken; }

    public Timestamp getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(Timestamp dataCriacao) { this.dataCriacao = dataCriacao; }
}