package br.recife.edu.ifpe.controller.servlets.funcionario;

import br.recife.edu.ifpe.model.classes.Funcionario;
import br.recife.edu.ifpe.model.repositorios.RepositorioFuncionario;
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
@WebServlet(name = "FuncionarioServletComJsp", urlPatterns = {"/FuncionarioServletComJsp"})
public class FuncionarioServletComJsp extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
      int codigo = Integer.parseInt(request.getParameter("codigo"));
      Funcionario funcionario = RepositorioFuncionario.getCurrentInstance().read(codigo);
      request.setAttribute("funcionario", funcionario);
      getServletContext().getRequestDispatcher("/funcionarios.jsp").forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    try {
      int codigo = Integer.parseInt(request.getParameter("codigo"));
      String nome = request.getParameter("nome");
      String departamento = request.getParameter("departamento");
      String redirect = request.getParameter("atualizar");
      Funcionario funcionario = new Funcionario();
      funcionario.setCodigo(codigo);
      funcionario.setNome(nome);
      funcionario.setDepartamento(departamento);
      
      HttpSession session = request.getSession();
      if (redirect != null) {
        RepositorioFuncionario.getCurrentInstance().update(funcionario);
        session.setAttribute("mensagem", "O(A) funcionário(a) <span>" + funcionario.getNome() + "</span> foi atualizado(a) com sucesso!");
        response.sendRedirect("funcionarios.jsp");
      } else {
        RepositorioFuncionario.getCurrentInstance().create(funcionario);
        session.setAttribute("mensagem", "O(A) funcionário(a) <span>" + funcionario.getNome() + "</span> foi adicionado(a) com sucesso!");
        response.sendRedirect("funcionarios.jsp");
      }
    } catch (NumberFormatException e) {
      HttpSession session = request.getSession();
      session.setAttribute("mensagem", "Erro! O código deve conter apenas caracteres numéricos.");
      response.sendRedirect("funcionarios.jsp");
    }
  }

  @Override
  protected void doDelete(HttpServletRequest request, HttpServletResponse response) 
          throws ServletException, IOException {
    super.doDelete(request, response);
    int codigo = Integer.parseInt(request.getParameter("codigo"));
    Funcionario funcionario = RepositorioFuncionario.getCurrentInstance().read(codigo);
    RepositorioFuncionario.getCurrentInstance().delete(funcionario);
    HttpSession session = request.getSession();
    session.setAttribute("mensagem", "O(a) funcionário(a) foi deletado(a) com sucesso.");
  }

  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>
}
