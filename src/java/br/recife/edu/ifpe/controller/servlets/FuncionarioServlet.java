package br.recife.edu.ifpe.controller.servlets;

import br.recife.edu.ifpe.model.classes.Funcionario;
import br.recife.edu.ifpe.model.repositorios.RepositorioFuncionario;
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
@WebServlet(name = "FuncionarioServlet", urlPatterns = {"/FuncionarioServlet"})
public class FuncionarioServlet extends HttpServlet {
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    String visualizar = request.getParameter("visualizar");
    if (visualizar != null) {
      int codigo = Integer.parseInt(request.getParameter("codigo"));
      Funcionario f = RepositorioFuncionario.getCurrentInstance().read(codigo);
      response.setContentType("text/html;charset=UTF-8");
      try (PrintWriter out = response.getWriter()) {
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Funcionário</title>");
        out.println("<style>li {list-style:none;} ul li:nth-child(4) {margin-top:2em;}</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Funcionário</h1>");
        out.println("<ul>");
        out.println("<li>Código: " + f.getCodigo() + "</li>");
        out.println("<li>Nome: " + f.getNome()+ "</li>");
        out.println("<li>Departamento: " + f.getDepartamento() + "</li>");
        out.println("<li><a href=\"FuncionarioServlet\">Funcionários Cadastrados</a></li>");
        out.println("<li><a href=\"index.html\">Página Inicial</a></li>");
        out.println("</ul>");
        out.println("</body>");
        out.println("</html>");
      }
    }
    
    String atualizar = request.getParameter("atualizar");
    if (atualizar != null) {
      int codigo = Integer.parseInt(request.getParameter("codigo"));
      Funcionario f = RepositorioFuncionario.getCurrentInstance().read(codigo);
      response.setContentType("text/html;charset=UTF-8");
      try (PrintWriter out = response.getWriter()) {
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"pt-br\">");
        out.println("<head>");
        out.println("<title>Servlet FuncionarioServlet</title>");
        out.println("<link rel=\"stylesheet\" href=\"cadastro-funcionario-estilo.css\"/>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div id=\"container\">");
        out.println("<h1>Atualizar Funcionário</h1>");
        out.println("<form method=\"post\" action=\"FuncionarioServlet\">");
        out.println("<ul>");
        out.println("<li><input type=\"hidden\" name=\"codigo\" value=\"" + f.getCodigo() + "\"/></li>");
        out.println("<li><label>Nome: <input type=\"text\" name=\"nome\" value=\"" + f.getNome() + "\"/></label></li>");
        out.println("<li><label>Departamento: <input type=\"text\" name=\"departamento\" value=\""+ f.getDepartamento() +"\"/></labe>");
        out.println("<li><input type=\"submit\" value=\"Atualizar\"/></li>");
        out.println("<li><input type=\"hidden\" name=\"atualizar\" value=\"1\"/></li>");
        out.println("<li><a href=\"index.html\">Página Inicial</a></li>");
        out.println("</ul>");
        out.println("</form>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
      }
    }
    
    String deletar = request.getParameter("deletar");
    if (deletar != null) {
      int codigo = Integer.parseInt(request.getParameter("codigo"));
      Funcionario f = RepositorioFuncionario.getCurrentInstance().read(codigo);
      RepositorioFuncionario.getCurrentInstance().delete(f);
      response.setContentType("text/html;charset=UTF-8");
      try (PrintWriter out = response.getWriter()) {
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet FuncionarioServlet</title>");
        out.println("<style>li {list-style: none;}</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1 style=\"color:#1d7d36;\">Operação Realizada com Sucesso!</h1>");
        out.println("<p style=\"font-weight:900;color:#1d7d36;\">O(A) funcionário(a) foi deletado(a) do sistema.</p>");
        out.println("<ul>");
        out.println("<li><a href=\"FuncionarioServlet\">Funcionários Cadastrados</a></li>");
        out.println("<li><a href=\"index.html\">Página Inicial</a></li>");
        out.println("</ul>");
        out.println("</body>");
        out.println("</html>");
      }
    }
    
    if (visualizar == null && atualizar == null && deletar == null) {
      List<Funcionario> funcionarios = RepositorioFuncionario.getCurrentInstance().readAll();
      response.setContentType("text/html;charset=UTF-8");
      try (PrintWriter out = response.getWriter()) {
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet FuncionarioServlet</title>");
        out.println("<style>th, td {border: 1px solid #444;} td {text-align: center;} td:first-child {text-align: left;} td > a {text-decoration: none;}</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<table>");
        out.println("<caption style=\"font-size:1.5em;font-weight:900;\">Funcionários Cadastrados</caption>");
        out.println("<tr><th>Código</th><th>Nome</th><th>Departamento</th><th colspan=\"3\">Operações</th></tr>");
        for (Funcionario f: funcionarios) {
          out.println("<tr>");
          out.println("<td>"+ f.getCodigo() +"</td>");
          out.println("<td>"+ f.getNome()+"</td>");
          out.println("<td>"+ f.getDepartamento()+"</td>");
          out.println("<td><a href=\"FuncionarioServlet?codigo=" + f.getCodigo() + "&visualizar=1\">Visualizar</a></td>");
          out.println("<td><a href=\"FuncionarioServlet?codigo=" + f.getCodigo() + "&atualizar=1\">Atualizar</a></td>");
          out.println("<td><a href=\"FuncionarioServlet?codigo=" + f.getCodigo() + "&deletar=1\">Deletar</a></td>");
          out.println("</tr>");
        }
        out.println("</table>");
        out.println("<a style=\"display:block;margin-top:2em;\" href=\"index.html\">Página Inicial</a>");
        out.println("</body>");
        out.println("</html>");
      }
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    try {
      int codigo = Integer.parseInt(request.getParameter("codigo"));
      String nome = request.getParameter("nome");
      String departamento = request.getParameter("departamento");
      Funcionario f = new Funcionario();
      f.setCodigo(codigo);
      f.setNome(nome);
      f.setDepartamento(departamento);
      
      String atualizar = request.getParameter("atualizar");
      if (atualizar != null) {
        RepositorioFuncionario.getCurrentInstance().update(f);
      } else {
        RepositorioFuncionario.getCurrentInstance().create(f);
      }
      
      response.setContentType("text/html;charset=UTF-8");
      try (PrintWriter out = response.getWriter()) {
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"pt-br\">");
        out.println("<head>");
        out.println("<title>Servlet FuncionarioServlet</title>");
        out.println("<meta charset=\"utf-8\" />");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1 style=\"color:#1d7d36;\">Operação Realizada com Sucesso!</h1>");
        if (atualizar != null) {
          out.println("<p style=\"font-weight:900;color:#1d7d36;\">O(A) funcionário(a) "
              + "<span style=\"color:#00f;\">" + f.getNome() + "</span> foi atualizado(a) com sucesso.</p>");
        } else {
          out.println("<p style=\"font-weight:900;color:#1d7d36;\">O(A) funcionário(a) "
              + "<span style=\"color:#00f;\">" + f.getNome() + "</span> foi cadastrado(a) com sucesso.</p>");
        }
        out.println("<ul>");
        out.println("<li><a href=\"cadastro-funcionario.html\">Cadastrar Outro Funcionário</a></li>");
        out.println("<li><a href=\"index.html\">Página Inicial</a></li>");
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
        out.println("<title>Servlet FuncionarioServlet</title>");
        out.println("<meta charset=\"utf-8\" />");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1 style=\"color:#f00;\">Ocorreu um Erro na Operação.</h1>");
        out.println("<p style=\"font-weight:900;color:#f00;\">O código do funcionário deve apenas "
              + "conter caracteres numéricos.</p>");
        out.println("<ul>");
        out.println("<li><a href=\"cadastro-funcionario.html\">Cadastrar Funcionário</a></li>");
        out.println("<li><a href=\"index.html\">Página Inicial</a></li>");
        out.println("</ul>");
        out.println("</body>");
        out.println("</html>");
      }
    }
  }
  
  @Override
  public String getServletInfo() {
    return "Short description";
  }
}