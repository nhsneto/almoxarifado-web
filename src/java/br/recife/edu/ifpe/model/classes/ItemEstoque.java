package br.recife.edu.ifpe.model.classes;

public class ItemEstoque {
  private int codigo;
  private Produto produto;
  private int quantidade;

  public int getCodigo() {
    return codigo;
  }

  public void setCodigo(int codigo) {
    this.codigo = codigo;
  }

  public Produto getProduto() {
    return produto;
  }

  public void setProduto(Produto produto) {
    this.produto = produto;
  }

  public int getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(int quantidade) {
    this.quantidade = quantidade;
  }

  public void adiciona(int quantidade) {
    this.quantidade += quantidade;
  }
  
  public void remove(int quantidade) {
    this.quantidade -= quantidade;
  }
}