package br.recife.edu.ifpe.controller.tags;

import br.recife.edu.ifpe.model.classes.Estoque;
import br.recife.edu.ifpe.model.classes.Funcionario;
import br.recife.edu.ifpe.model.classes.LoteEntrada;
import br.recife.edu.ifpe.model.classes.LoteSaida;
import br.recife.edu.ifpe.model.classes.Produto;
import br.recife.edu.ifpe.model.repositorios.RepositorioData;
import br.recife.edu.ifpe.model.repositorios.RepositorioEstoque;
import br.recife.edu.ifpe.model.repositorios.RepositorioFuncionario;
import br.recife.edu.ifpe.model.repositorios.RepositorioLoteEntrada;
import br.recife.edu.ifpe.model.repositorios.RepositorioLoteSaida;
import br.recife.edu.ifpe.model.repositorios.RepositorioProdutos;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author Nelson
 */
public class Carrega extends SimpleTagSupport {
  private String lista;
  
  public void setLista(String lista) {
    this.lista = lista;
  }

  @Override
  public void doTag() throws JspException, IOException {
    super.doTag();
    switch (this.lista) {
      case "Produto":
        List<Produto> produtos = RepositorioProdutos.getCurrentInstance().readAll();
        getJspContext().setAttribute("produtos", produtos, PageContext.PAGE_SCOPE);
        break;
      case "Funcionario":
        List<Funcionario> funcionarios = RepositorioFuncionario.getCurrentInstance().readAll();
        getJspContext().setAttribute("funcionarios", funcionarios, PageContext.PAGE_SCOPE);
        break;
      case "LoteEntrada":
        List<LoteEntrada> lotesEntrada = RepositorioLoteEntrada.getCurrentInstance().readAll();
        getJspContext().setAttribute("lotesEntrada", lotesEntrada, PageContext.PAGE_SCOPE);
        break;
      case "LoteSaida":
        List<LoteSaida> lotesSaida = RepositorioLoteSaida.getCurrentInstance().readAll();
        getJspContext().setAttribute("lotesSaida", lotesSaida, PageContext.PAGE_SCOPE);
        break;
      case "Estoque":
        Estoque estoque = RepositorioEstoque.getCurrentInstance().read();
        getJspContext().setAttribute("estoque", estoque, PageContext.PAGE_SCOPE);
        break;
      case "Data":
        List<Date> data = RepositorioData.getInstance().readAll();
        getJspContext().setAttribute("data", data, PageContext.PAGE_SCOPE);
        break;
      default:
        break;
    }
  }
}