package br.recife.edu.ifpe.controller.servlets.lotesaida;

import br.recife.edu.ifpe.model.classes.Estoque;
import br.recife.edu.ifpe.model.classes.Funcionario;
import br.recife.edu.ifpe.model.classes.ItemEstoque;
import br.recife.edu.ifpe.model.classes.ItemSaida;
import br.recife.edu.ifpe.model.classes.LoteSaida;
import br.recife.edu.ifpe.model.classes.Produto;
import br.recife.edu.ifpe.model.repositorios.RepositorioData;
import br.recife.edu.ifpe.model.repositorios.RepositorioEstoque;
import br.recife.edu.ifpe.model.repositorios.RepositorioFuncionario;
import br.recife.edu.ifpe.model.repositorios.RepositorioLoteSaida;
import br.recife.edu.ifpe.model.repositorios.RepositorioProdutos;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
@WebServlet(name = "LoteSaidaServlet", urlPatterns = {"/LoteSaidaServlet"})
public class LoteSaidaServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    int codigo = Integer.parseInt(request.getParameter("codigo"));
    LoteSaida ls = RepositorioLoteSaida.getCurrentInstance().read(codigo);
    
    String loteSaidaJson = "{\"codigo\": " + ls.getCodigo() + ", "
                          + "\"descricao\": \"" + ls.getDescricao() + "\", "
                          + "\"responsavel\": \"" + ls.getResponsavel().getNome() + "\", "
                          + "\"itens\": [";
    for (ItemSaida i : ls.getItens()) {
      loteSaidaJson += "{\"codigo\": " + i.getCodigo() + ", "
                      + "\"nomeProduto\": \"" + i.getProduto().getNome() + "\", "
                      + "\"quantidade\": " + i.getQuantidade() + "}";
      if (ls.getItens().indexOf(i) != ls.getItens().size() - 1) {
        loteSaidaJson += ", ";
      }
    }
    loteSaidaJson += "]}";
    
    response.setContentType("text/plain");
    try (PrintWriter out = response.getWriter()) {
      out.println(loteSaidaJson);
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    HttpSession session = request.getSession();
    LoteSaida loteSaida = (LoteSaida) session.getAttribute("loteSaida");
    String descricao = request.getParameter("descricao");
    loteSaida.setDescricao(descricao);
    RepositorioLoteSaida.getCurrentInstance().create(loteSaida);
    RepositorioData.getInstance().create(loteSaida.getData());
    session.removeAttribute("loteSaida");
    session.setAttribute("msg", "O lote de saída foi retirado com sucesso.");
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    super.doPut(req, resp);
    int codigo = Integer.parseInt(req.getParameter("codigo"));
    String operacao = req.getParameter("operacao");
    HttpSession session = req.getSession();
    LoteSaida loteSaida = (LoteSaida) session.getAttribute("loteSaida");

    if (loteSaida == null) {
      loteSaida = new LoteSaida();
      loteSaida.setCodigo((int) (Math.random() * 100000));
      session.setAttribute("loteSaida", loteSaida);
    }

    // Item:
    
    if (operacao.equals("adicionar-item")) {
      Estoque estoque = RepositorioEstoque.getCurrentInstance().read();
      boolean itemExistente = false;
      for (ItemSaida itemSaida : loteSaida.getItens()) {
        if (itemSaida.getProduto().getCodigo() == codigo) {
          itemExistente = true;
          for (ItemEstoque itemEstoque : estoque.getItens()) {
            if (itemEstoque.getProduto().getCodigo() == codigo && itemEstoque.getQuantidade() > 0) {
              itemSaida.setQuantidade(itemSaida.getQuantidade() + 1);
              itemEstoque.setQuantidade(itemEstoque.getQuantidade() - 1);
              session.setAttribute("msg", "O produto " + itemSaida.getProduto().getNome() + " foi adicionado com sucesso.");
              break;
            }
          }
          break;
        }
      }

      if (!itemExistente) {
        ItemSaida item = new ItemSaida();
        Produto p = RepositorioProdutos.getCurrentInstance().read(codigo);
        item.setCodigo((int) (Math.random() * 10000));
        item.setProduto(p);
        item.setQuantidade(1);
        for (ItemEstoque e : estoque.getItens()) {
          if (e.getProduto().getCodigo() == codigo) {
            e.setQuantidade(e.getQuantidade() - 1);
            break;
          }
        }
        loteSaida.addItem(item);
        session.setAttribute("msg", "O produto " + p.getNome() + " foi inserido no lote com sucesso.");
      }
    }

    // Funcionario:
    
    if (operacao.equals("adicionar-funcionario")) {
      List<Funcionario> funcionarios = RepositorioFuncionario.getCurrentInstance().readAll();
      for (Funcionario f : funcionarios) {
        if (f.getCodigo() == codigo) {
          loteSaida.setResponsavel(f);
          session.setAttribute("msg", "O funcionário " + f.getNome() + " foi adicionado com sucesso.");
        }
      }
    }
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    super.doDelete(req, resp);
    int codigo = Integer.parseInt(req.getParameter("codigo"));
    String operacao = req.getParameter("operacao");
    HttpSession session = req.getSession();
    LoteSaida loteSaida = (LoteSaida) session.getAttribute("loteSaida");
    Estoque estoque = RepositorioEstoque.getCurrentInstance().read();

    if (operacao == null) {
      for (ItemSaida i : loteSaida.getItens()) {
        if (i.getCodigo() == codigo) {
          for (ItemEstoque ie : estoque.getItens()) {
            if (ie.getProduto().getCodigo() == i.getProduto().getCodigo()) {
              ie.setQuantidade(ie.getQuantidade() + 1);
              break;
            }
          }

          if (i.getQuantidade() == 1) {
            loteSaida.getItens().remove(i);
            session.setAttribute("msg", "O produto " + i.getProduto().getNome() + " foi removido do lote com sucesso.");
            break;
          }

          i.setQuantidade(i.getQuantidade() - 1);
          session.setAttribute("msg", "O produto " + i.getProduto().getNome() + " foi removido do lote com sucesso.");
          break;
        }
      }

      if (loteSaida.getItens().isEmpty()) {
        session.removeAttribute("loteSaida");
      }
    }
    
    // Remove funcionario:
    
    if (operacao != null && operacao.equals("remover-funcionario")) {
      Funcionario f = null;
      loteSaida.setResponsavel(f);
    }
  }

  @Override
  public String getServletInfo() {
    return "Short description";
  }
}
