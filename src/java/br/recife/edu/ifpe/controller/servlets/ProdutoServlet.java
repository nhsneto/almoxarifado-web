/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.recife.edu.ifpe.controller.servlets;

import br.recife.edu.ifpe.model.classes.Produto;
import br.recife.edu.ifpe.model.repositorios.RepositorioProdutos;
import java.io.IOException;
import java.io.PrintWriter;
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
    response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter()) {
      /* TODO output your page here. You may use following sample code. */
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<title>Servlet ProdutoServlet</title>");      
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>Modificado</h1>");
      out.println("</body>");
      out.println("</html>");
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
    int codigo = 0;
    try {
      codigo = Integer.parseInt(request.getParameter("codigo"));
    } catch(NumberFormatException e) {
      response.setContentType("text/html;charset=UTF-8");
      try (PrintWriter out = response.getWriter()) {
      /* TODO output your page here. You may use following sample code. */
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<title>Servlet ProdutoServlet</title>");      
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>Erro! O código do produto deve ser um valor numérico.</h1>");
      out.println("<a href=\"index.html\">Página inicial</a>");
      out.println("<a href=\"cadastroproduto.html\">Cadastrar outro produto</a>");
      out.println("</body>");
      out.println("</html>");
    }
  }
    
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
    
    response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter()) {
      /* TODO output your page here. You may use following sample code. */
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<title>Servlet ProdutoServlet</title>");      
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>Resposta do Server</h1>");
      out.println("<a href=\"index.html\">Página inicial</a>");
      out.println("<a href=\"cadastroproduto.html\">Cadastrar outro produto</a>");
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

}
