package br.recife.edu.ifpe.controller.servlets.loteentrada;

import br.recife.edu.ifpe.model.classes.ItemEntrada;
import br.recife.edu.ifpe.model.classes.LoteEntrada;
import br.recife.edu.ifpe.model.classes.Produto;
import br.recife.edu.ifpe.model.repositorios.RepositorioProdutos;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nelson
 */
@WebServlet(name = "LoteEntradaServlet", urlPatterns = {"/LoteEntradaServlet"})
public class LoteEntradaServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    super.doPut(req, resp);
    int codigo = Integer.parseInt(req.getParameter("codigo"));
    HttpSession session = req.getSession();
    LoteEntrada loteEntrada = (LoteEntrada) session.getAttribute("loteEntrada");
    if (loteEntrada == null) {
      loteEntrada = new LoteEntrada();
      session.setAttribute("loteEntrada", loteEntrada);
    }
    boolean controle = false;
    for (ItemEntrada item: loteEntrada.getItens()) {
      if (item.getProduto().getCodigo() == codigo) {
        item.setQuantidade(item.getQuantidade() + 1);
        controle = true;
        session.setAttribute("msg", "O produto " + item.getProduto().getNome()+ " foi incrementado no lote com sucesso.");
        break;
      }
    }
    if (!controle) {
      ItemEntrada item = new ItemEntrada();
      Produto p = RepositorioProdutos.getCurrentInstance().read(codigo);
      item.setProduto(p);
      item.setCodigo((int) (Math.random() * 10000));
      item.setQuantidade(1);
      loteEntrada.addItem(item);
      session.setAttribute("msg", "O produto " + p.getNome() + " foi inserido no lote com sucesso.");
    }
  }

  @Override
  public String getServletInfo() {
    return "Short description";
  }
}