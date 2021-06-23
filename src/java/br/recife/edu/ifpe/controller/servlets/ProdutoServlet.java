package br.recife.edu.ifpe.controller.servlets;

import br.recife.edu.ifpe.model.classes.ItemEstoque;
import br.recife.edu.ifpe.model.classes.Produto;
import br.recife.edu.ifpe.model.repositorios.RepositorioEstoque;
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
    String codigoAux = request.getParameter("codigo");
    if (codigoAux == null) {
      List<Produto> produtos = RepositorioProdutos.getCurrentInstance().readAll();
      response.setContentType("text/html;charset=UTF-8");
      try (PrintWriter out = response.getWriter()) {
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"pt-br\">");
        out.println("<head>");
        out.println("<title>Servlet ProdutoServlet</title>");
        out.println("<meta charset=\"utf-8\" />");
        out.println("<style>th, td {border: 1px solid #666;} caption {font-size: 1.5em; font-weight: 800;}</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<table>");
        out.println("<caption>Produtos Cadastrados</caption>");
        out.println("<tr><th>Código</th><th>Nome</th><th>Marca</th><th>Categoria</th><th>Operações</th></tr>");
        for (Produto p: produtos) {
          out.println("<tr>");
          out.println("<td>" + p.getCodigo() + "</td>");
          out.println("<td>" + p.getNome() + "</td>");
          out.println("<td>" + p.getMarca() + "</td>");
          out.println("<td>" + p.getCategoria() + "</td>");
          out.println("<td><a href=\"ProdutoServlet?codigo=" + p.getCodigo() + "\">Visualizar</a></td>");
        }
        out.println("</table>");
        out.println("<a href=\"index.html\">Página Inicial</a>");
        out.println("</body>");
        out.println("</html>");
      }
    } else {
      int codigo = 0;
      try {
        codigo = Integer.parseInt(codigoAux);
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
          out.println("<h1 style=\"color:#f00;\">Produto não encontrado</h1>");
          out.println("<ul>");
          out.println("<li><a href=\"index.html\">Página inicial</a></li>");
          out.println("<li><a href=\"cadastroproduto.html\">Cadastrar Produto</a></li>");
          out.println("</ul>");
          out.println("</body>");
          out.println("</html>");
        }
      }
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
    int codigo;
    
    /*
     * Apesar de preferir corrigir este erro apenas trocando o input do tipo text para number no formulário do
     * 'cadastroproduto.html', escolhi usar o try-catch em uma função recursiva.
     */
    codigo = avaliarCodigoTipoNumerico(response, request.getParameter("codigo"));
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
    
    response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter()) {
      out.println("<!DOCTYPE html>");
      out.println("<html lang=\"pt-br\">");
      out.println("<head>");
      out.println("<title>Servlet ProdutoServlet</title>");
      out.println("<meta charset=\"utf-8\" />");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1 style=\"color:#1d7d36;\">Produto " + p.getNome() + " adicionado com sucesso.</h1>");
      out.println("<ul>");
      out.println("<li><a href=\"index.html\">Página inicial</a></li>");
      out.println("<li><a href=\"cadastroproduto.html\">Cadastrar outro produto</a></li>");
      out.println("</ul>");
      out.println("</body>");
      out.println("</html>");
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
  
  int avaliarCodigoTipoNumerico(HttpServletResponse response, String codigoAuxiliar) throws ServletException, IOException {
    int codigo = 0;
    boolean isCorreto = true;
    try {
      codigo = Integer.parseInt(codigoAuxiliar);
    } catch(NumberFormatException e) {
      isCorreto = false;
      response.setContentType("text/html;charset=UTF-8");
      try (PrintWriter out = response.getWriter()) {
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"pt-br\">");
        out.println("<head>");
        out.println("<title>Servlet ProdutoServlet</title>");
        out.println("<meta charset=\"utf-8\" />");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />");
        out.println("<link rel=\"stylesheet\" href=\"cadastroproduto-estilo.css\" />");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1 style=\"color:#f00;text-align:center;\">Erro! O Cadastro do Produto é Inválido.</h1>");
        out.println("<p style=\"color:#f00;text-align:center;\">O código do produto deve ter apenas caracteres numéricos.</p>");
        out.println("<div id=\"container\">");
        out.println("<form method=\"post\" action=\"ProdutoServlet\">");
        out.println("<ul>");
        out.println("<li><label>Código: <input type=\"text\" name=\"codigo\"/></label></li>");
        out.println("<li><label>Nome: <input type=\"text\" name=\"nome\" class=\"tabulation\"/></label></li>");
        out.println("<li><label>Marca: <input type=\"text\" name=\"marca\" class=\"tabulation\"/></label></li>");
        out.println("<li><label>Categoria: <input type=\"text\" name=\"categoria\"/></label></li>");
        out.println("<li><label>Descrição: <textarea name=\"descricao\" cols=\"22\"></textarea></label></li>");
        out.println("<li><input type=\"submit\" value=\"cadastrar\"/></li>");
        out.println("<li><a href=\"index.html\">Página Inicial</a></li>");
        out.println("</ul>");
        out.println("</form>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
      }
    }
    if (isCorreto) {
      return codigo;
    } else {
      return avaliarCodigoTipoNumerico(response, codigoAuxiliar);
    }
  }

}
