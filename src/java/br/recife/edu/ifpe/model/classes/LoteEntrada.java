package br.recife.edu.ifpe.model.classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoteEntrada {
  private int codigo;
  private List<ItemEntrada> itens;
  private Date data;
  /*
    * Neste campo ficarão armazenadas as informações de documentos
   */
  private String descricao;

  public LoteEntrada() {
    this.itens = new ArrayList<>();
    this.data = new Date();
  }

  public int getCodigo() {
    return codigo;
  }

  public void setCodigo(int codigo) {
    this.codigo = codigo;
  }

  public List<ItemEntrada> getItens() {
    return itens;
  }

  public void setItens(List<ItemEntrada> itens) {
    this.itens = itens;
  }

  public Date getData() {
    return data;
  }

  public void setData(Date data) {
    this.data = data;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public void addItem(ItemEntrada i) {
    this.itens.add(i);
  }
  
  public int getQuantidadeTotal() {
    int quantidade = 0;
    for (ItemEntrada item : this.itens) {
      quantidade += item.getQuantidade();
    }
    return quantidade;
  }
}