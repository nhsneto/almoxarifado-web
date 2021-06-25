package br.recife.edu.ifpe.controller.servlets;

import br.recife.edu.ifpe.model.classes.ItemEstoque;
import br.recife.edu.ifpe.model.classes.Produto;
import br.recife.edu.ifpe.model.repositorios.RepositorioEstoque;
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

/**
 *
 * @author Nelson
 */
@WebServlet(name = "ProdutoServlet", urlPatterns = {"/ProdutoServlet"})
public class ProdutoServlet extends HttpServlet {

  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
  /**
   * Handles the HTTP <code>GET</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    String visualizar = request.getParameter("visualizar");
    String atualizar = request.getParameter("atualizar");
    String deletar = request.getParameter("deletar");
    String codigoAux = request.getParameter("codigo");
    
    if (visualizar != null) {
      int codigo = Integer.parseInt(codigoAux);
      Produto p = RepositorioProdutos.getCurrentInstance().read(codigo);
      response.setContentType("text/html;charset=UTF-8");
      try (PrintWriter out = response.getWriter()) {
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"pt-br\">");
        out.println("<head>");
        out.println("<title>Servlet ProdutoServlet</title>");
        out.println("<meta charset=\"utf-8\" />");
        out.println("<style>li {list-style: none;}</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1 style=\"color:#1d7d36;\">Produto Recuperado</h1>");
        out.println("<ul>");
        out.println("<li>Código:    " + p.getCodigo() + "</li>");
        out.println("<li>Nome:      " + p.getNome() + "</li>");
        out.println("<li>Marca:     " + p.getMarca() + "</li>");
        out.println("<li>Categoria: " + p.getCategoria() + "</li>");
        out.println("<li>Descrição: " + p.getDescricao() + "</li>");
        out.println("</ul>");
        out.println("<a href=\"index.html\">Página Inicial</a>");
        out.println("</body>");
        out.println("</html>");
      }
    }
    
    if (deletar != null) {
      int codigo = Integer.parseInt(codigoAux);
      Produto p = RepositorioProdutos.getCurrentInstance().read(codigo);
      RepositorioProdutos.getCurrentInstance().delete(p);
      response.setContentType("text/html;charset=UTF-8");
      try (PrintWriter out = response.getWriter()) {
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"pt-br\">");
        out.println("<head>");
        out.println("<title>Servlet ProdutoServlet</title>");
        out.println("<meta charset=\"utf-8\" />");
        out.println("<style>li {list-style: none;}</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1 style=\"color:#1d7d36;\">Produto deletado com sucesso.</h1>");
        out.println("<a href=\"index.html\">Página Inicial</a>");
        out.println("</body>");
        out.println("</html>");
      }
    }
    
    if (atualizar != null) {
      int codigo = Integer.parseInt(codigoAux);
      Produto p = RepositorioProdutos.getCurrentInstance().read(codigo);
      response.setContentType("text/html;charset=UTF-8");
      try (PrintWriter out = response.getWriter()) {
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"pt-br\">");
        out.println("<head>");
        out.println("<title>Servlet ProdutoServlet</title>");
        out.println("<meta charset=\"utf-8\" />");
        out.println("<style>li {list-style: none;}</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1 style=\"color:#1750ad;\">Atualizar Produto</h1>");
        out.println("<form method=\"post\" action=\"ProdutoServlet\">\n" +
"        <ul>\n" +
"          <li><input type=\"hidden\" name=\"codigo\" value=\""+ p.getCodigo() +"\"/></li>\n" +
"          <li><label>Nome: <input type=\"text\" name=\"nome\" class=\"tabulation\" value=\""+ p.getNome()+"\"/></label></li>\n" +
"          <li><label>Marca: <input type=\"text\" name=\"marca\" class=\"tabulation\" value=\""+ p.getMarca()+"\"/></label></li>\n" +
"          <li><label>Categoria: <input type=\"text\" name=\"categoria\" value=\""+ p.getCategoria()+"\"/></label></li>\n" +
"          <li><label>Descrição: <textarea name=\"descricao\" cols=\"22\">" + p.getDescricao() + "</textarea></label></li>\n" +
"          <li><input type=\"hidden\" name=\"atualizar\" value=\"1\"/></li>" +
"          <li><input type=\"submit\" value=\"atualizar\"/></li>\n" +
"          \n" +
"        </ul>\n" +
"      </form>");
        out.println("<ul>");
        out.println("<li><a href=\"index.html\">Página Inicial</a></li>");
        out.println("</ul>");
        out.println("</body>");
        out.println("</html>");
      }
    }
    
    if (codigoAux == null && atualizar == null && deletar == null && visualizar == null) {
      List<Produto> produtos = RepositorioProdutos.getCurrentInstance().readAll();
      response.setContentType("text/html;charset=UTF-8");
      try (PrintWriter out = response.getWriter()) {
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"pt-br\">");
        out.println("<head>");
        out.println("<title>Servlet ProdutoServlet</title>");
        out.println("<meta charset=\"utf-8\" />");
        out.println("<style>th, td {border: 1px solid #666;} caption {font-size: 1.5em; font-weight: 800;} td a {text-decoration: none;}</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<table>");
        out.println("<caption>Produtos Cadastrados</caption>");
        out.println("<tr><th>Código</th><th>Nome</th><th>Marca</th><th>Categoria</th><th colspan=\"3\">Operações</th></tr>");
        for (Produto p: produtos) {
          out.println("<tr>");
          out.println("<td>" + p.getCodigo() + "</td>");
          out.println("<td>" + p.getNome() + "</td>");
          out.println("<td>" + p.getMarca() + "</td>");
          out.println("<td>" + p.getCategoria() + "</td>");
          out.println("<td><a href=\"ProdutoServlet?codigo=" + p.getCodigo() + "&visualizar=1\">Visualizar</a></td>"
                  + "<td><a href=\"ProdutoServlet?codigo=" + p.getCodigo() + "&atualizar=1\">Atualizar</a></td>"
                  + "<td><a href=\"ProdutoServlet?codigo=" + p.getCodigo() + "&deletar=1\">Deletar</a></td>");
          out.println("</tr>");
        }
        out.println("</table>");
        out.println("<a href=\"index.html\">Página Inicial</a>");
        out.println("</body>");
        out.println("</html>");
      }
    }
  }

  /**
   * Handles the HTTP <code>POST</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    try {
      int codigo = Integer.parseInt(request.getParameter("codigo"));
      String nome = request.getParameter("nome");
      String marca = request.getParameter("marca");
      String categoria = request.getParameter("categoria");
      String descricao = request.getParameter("descricao");
      String atualizar = request.getParameter("atualizar");
    
      Produto p = new Produto();
      p.setCodigo(codigo);
      p.setNome(nome);
      p.setMarca(marca);
      p.setCategoria(categoria);
      p.setDescricao(descricao);
      if (atualizar != null) {
        RepositorioProdutos.getCurrentInstance().update(p);
      } else {
        RepositorioProdutos.getCurrentInstance().create(p);
        ItemEstoque item = new ItemEstoque();
        item.setProduto(p);
        item.setQuantidade(0);
        item.setCodigo(p.getCodigo());
        RepositorioEstoque.getCurrentInstance().read().addItem(item);
      }

      response.setContentType("text/html;charset=UTF-8");
      try (PrintWriter out = response.getWriter()) {
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"pt-br\">");
        out.println("<head>");
        out.println("<title>Servlet ProdutoServlet</title>");
        out.println("<meta charset=\"utf-8\" />");
        out.println("</head>");
        out.println("<body>");
        if (atualizar == null) {
          out.println("<h1 style=\"color:#1d7d36;\">Produto " + p.getNome() + " adicionado com sucesso.</h1>");
        } else {
          out.println("<h1 style=\"color:#1d7d36;\">O Produto " + p.getNome() + " foi atualizado com sucesso.</h1>");
        }
        out.println("<ul>");
        out.println("<li><a href=\"index.html\">Página inicial</a></li>");
        out.println("<li><a href=\"cadastro-produto.html\">Cadastrar outro produto</a></li>");
        out.println("</ul>");
        out.println("</body>");
        out.println("</html>");
      }
    } catch (NumberFormatException e) {
      response.setContentType("text/html;charset=UTF-8");
      try (PrintWriter out = response.getWriter()) {
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"pt-br\">");
        out.println("<head>");
        out.println("<title>Servlet ProdutoServlet</title>");
        out.println("<meta charset=\"utf-8\" />");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1 style=\"color:#f00;\">Erro! O Cadastro do Produto Não Foi Realizado.</h1>");
        out.println("<p style=\"color:#f00;font-weight:800;\">O código do produto deve conter apenas caracteres numéricos.</p>");
        out.println("<ul>");
        out.println("<li><a href=\"index.html\">Página Inicial</a></li>");
        out.println("<li><a href=\"cadastro-produto.html\">Cadastrar Produto</a></li>");
        out.println("</ul>");
        out.println("</body>");
        out.println("</html>");
      }
    }
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    super.doPut(req, resp); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    super.doDelete(req, resp); //To change body of generated methods, choose Tools | Templates.
  }
  
  /**
   * Returns a short description of the servlet.
   *
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>
  
}
