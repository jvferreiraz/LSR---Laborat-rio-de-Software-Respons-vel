package model;

public class Item {
    private int id;
    private String descricao;
    private String categoria;
    private String localEncontro;
    
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
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getLocalEncontro() {
		return localEncontro;
	}
	public void setLocalEncontro(String localEncontro) {
		this.localEncontro = localEncontro;
	}
	public String getDataEncontro() {
		return dataEncontro;
	}
	public void setDataEncontro(String dataEncontro) {
		this.dataEncontro = dataEncontro;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	private String dataEncontro;
    private String status;
    private String observacao;
    // getters e setters
}