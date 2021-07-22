package br.recife.edu.ifpe.controller.servlets.loteentrada;

import br.recife.edu.ifpe.model.classes.Estoque;
import br.recife.edu.ifpe.model.classes.ItemEntrada;
import br.recife.edu.ifpe.model.classes.ItemEstoque;
import br.recife.edu.ifpe.model.classes.LoteEntrada;
import br.recife.edu.ifpe.model.classes.Produto;
import br.recife.edu.ifpe.model.repositorios.RepositorioEstoque;
import br.recife.edu.ifpe.model.repositorios.RepositorioLoteEntrada;
import br.recife.edu.ifpe.model.repositorios.RepositorioProdutos;
import java.io.IOException;
import java.io.PrintWriter;
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
    int codigo = Integer.parseInt(request.getParameter("codigo"));
    LoteEntrada loteEntrada = RepositorioLoteEntrada.getCurrentInstance().read(codigo);
    String responseJSON = "{\"codigo\":" + loteEntrada.getCodigo() + ", "
                          + "\"descricao\": \"" + loteEntrada.getDescricao() + "\", "
                          + "\"itens\": [";
    for (ItemEntrada i : loteEntrada.getItens()) {
      responseJSON += "{\"codigo\": " + i.getCodigo() + ", \"nomeProduto\": \"" + i.getProduto().getNome() + "\"}";
      if (loteEntrada.getItens().indexOf(i) != loteEntrada.getItens().size() - 1) {
        responseJSON += ", ";
      }
    }
    responseJSON += "]}";
    response.setContentType("text/plain");
    try (PrintWriter out = response.getWriter()) {
      out.println(responseJSON);
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    HttpSession session = request.getSession();
    LoteEntrada loteEntrada = (LoteEntrada) session.getAttribute("loteEntrada");
    Estoque estoque = RepositorioEstoque.getCurrentInstance().read();
    for (ItemEntrada itemEntrada : loteEntrada.getItens()) {
      if (itemEntrada.getQuantidade() > 10) {
        session.setAttribute("msg", "A quantidade máxima permitida do produto " + itemEntrada.getProduto().getNome() + " foi excedida.");
        response.sendError(500);
        return;
      }
    }
    for (ItemEntrada itemEntrada : loteEntrada.getItens()) {
      for (ItemEstoque itemEstoque : estoque.getItens()) {
        if (itemEntrada.getProduto().getCodigo() == itemEstoque.getProduto().getCodigo()) {
          itemEstoque.adiciona(itemEntrada.getQuantidade());
          break;
        }
      }
    }
    RepositorioLoteEntrada.getCurrentInstance().create(loteEntrada);
    session.removeAttribute("loteEntrada");
    session.setAttribute("msg", "O lote de entrada foi inserido com sucesso.");
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    super.doPut(req, resp);
    String operacao = req.getParameter("operacao");
    int codigo = Integer.parseInt(req.getParameter("codigo"));
    HttpSession session = req.getSession();
    LoteEntrada loteEntrada = (LoteEntrada) session.getAttribute("loteEntrada");
    if (loteEntrada == null) {
      loteEntrada = new LoteEntrada();
      session.setAttribute("loteEntrada", loteEntrada);
    }
    
    boolean controle = false;
    if (operacao.equals("adicionar")) {
      for (ItemEntrada item : loteEntrada.getItens()) {
        if (item.getProduto().getCodigo() == codigo) {
          item.setQuantidade(item.getQuantidade() + 1);
          controle = true;
          session.setAttribute("msg", "O produto " + item.getProduto().getNome() + " foi incrementado no lote com sucesso.");
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
    } else if (operacao.equals("remover")) {
      for (ItemEntrada i : loteEntrada.getItens()) {
        if (i.getProduto().getCodigo() == codigo) {
          if (i.getQuantidade() == 1) {
            session.setAttribute("msg", "O produto " + i.getProduto().getNome() + " foi removido do lote com sucesso.");
            loteEntrada.getItens().remove(i);
            break;
          }
          i.setQuantidade(i.getQuantidade() - 1);
          break;
        }
      }
    }
    
    if (loteEntrada.getItens().isEmpty()) {
      session.removeAttribute("loteEntrada");
    }
  }

  @Override
  public String getServletInfo() {
    return "Short description";
  }
}