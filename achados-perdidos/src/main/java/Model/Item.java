package Model;

import java.time.LocalDate;

public class Item {
    private int id;
    private String descricao;
    private String observacao;
    private String status;
    private LocalDate dataEncontro;
    private int categoriaId;
    private int localEncontroId;
    private int statusItemId;
    
    // Campos adicionais para exibição
    private String categoriaNome;
    private String localEncontroNome;
    private String statusItemNome;
    
    // Getters e Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public String getObservacao() {
        return observacao;
    }
    
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public LocalDate getDataEncontro() {
        return dataEncontro;
    }
    
    public void setDataEncontro(LocalDate dataEncontro) {
        this.dataEncontro = dataEncontro;
    }
    
    public int getCategoriaId() {
        return categoriaId;
    }
    
    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }
    
    public int getLocalEncontroId() {
        return localEncontroId;
    }
    
    public void setLocalEncontroId(int localEncontroId) {
        this.localEncontroId = localEncontroId;
    }
    
    public int getStatusItemId() {
        return statusItemId;
    }
    
    public void setStatusItemId(int statusItemId) {
        this.statusItemId = statusItemId;
    }
    
    public String getCategoriaNome() {
        return categoriaNome;
    }
    
    public void setCategoriaNome(String categoriaNome) {
        this.categoriaNome = categoriaNome;
    }
    
    public String getLocalEncontroNome() {
        return localEncontroNome;
    }
    
    public void setLocalEncontroNome(String localEncontroNome) {
        this.localEncontroNome = localEncontroNome;
    }
    
    public String getStatusItemNome() {
        return statusItemNome;
    }
    
    public void setStatusItemNome(String statusItemNome) {
        this.statusItemNome = statusItemNome;
    }
}
