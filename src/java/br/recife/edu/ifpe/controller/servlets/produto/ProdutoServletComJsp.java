package br.recife.edu.ifpe.controller.servlets.produto;

import br.recife.edu.ifpe.model.classes.ItemEstoque;
import br.recife.edu.ifpe.model.classes.Produto;
import br.recife.edu.ifpe.model.repositorios.RepositorioEstoque;
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
@WebServlet(name = "ProdutoServletComJsp", urlPatterns = {"/ProdutoServletComJsp"})
public class ProdutoServletComJsp extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    int codigo = Integer.parseInt(request.getParameter("codigo"));
    Produto produto = RepositorioProdutos.getCurrentInstance().read(codigo);
    request.setAttribute("produto", produto);
    getServletContext().getRequestDispatcher("/produtos.jsp").forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    try {
      int codigo = Integer.parseInt(request.getParameter("codigo"));
      String nome = request.getParameter("nome");
      String marca = request.getParameter("marca");
      String categoria = request.getParameter("categoria");
      String descricao = request.getParameter("descricao");

      Produto p = new Produto();
      p.setCodigo(codigo);
      p.setNome(nome);
      p.setMarca(marca);
      p.setCategoria(categoria);
      p.setDescricao(descricao);
      RepositorioProdutos.getCurrentInstance().create(p);
      
      ItemEstoque item = new ItemEstoque();
      item.setProduto(p);
      item.setQuantidade(0);
      item.setCodigo(p.getCodigo());
      RepositorioEstoque.getCurrentInstance().read().addItem(item);
      
      HttpSession session = request.getSession();
      session.setAttribute("mensagem", "O Produto " + p.getNome() + " foi cadastrado com sucesso.");
      response.sendRedirect("produtos.jsp");
    } catch (NumberFormatException e) {
      HttpSession session = request.getSession();
      session.setAttribute("mensagem", "Erro! O código do produto deve conter apenas caracteres numéricos.");
      response.sendRedirect("produtos.jsp");
    }
  }

  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>

}
