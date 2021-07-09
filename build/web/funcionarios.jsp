<%@page import="br.recife.edu.ifpe.model.repositorios.RepositorioFuncionario"%>
<%@page import="br.recife.edu.ifpe.model.classes.Funcionario"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <title>Funcionários</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta charset="utf-8"/>
    <style>
      #botaoCadastroProduto {margin-bottom: 32px;}
      .botaoFechar {margin-bottom: 32px;}
      .modal {position: absolute; left: 550px; top: 0;}
      p {color: #1d7d36; font-weight: 800;}
      span {color: #00f;}
      ul {padding-left: 0;}
      li {list-style: none;}
      th, td {border: 1px solid #888;}
      tr > td:not(:first-child) {text-align: center;}
    </style>
  </head>
  <body>
    <h1>Funcionários Cadastrados</h1>
    <%
      String msg = (String) session.getAttribute("mensagem");
      if (msg != null) {
        out.println("<p>" + msg + "</p>");
        session.removeAttribute(msg);
      }
    %>
    <button id="botaoCadastroProduto" onclick="modalCadastroOpen()">Novo Funcionário</button>
    <div id="modalCadastro" class="modal">
      <%@include file="cadastro-funcionario.jsp"%>
      <ul>
        <li><button class="botaoFechar" onclick="modalCadastroClose()">Fechar</button></li>
        <li><a href="index.html">Página Inicial</a></li>
      </ul>
    </div>
    <div id="modalVisualizacao" class="modal">
      <%@include file="visualizacao-funcionario.jsp" %>
      <ul>
        <li><button class="botaoFechar" onclick="modalVisualizacaoClose()">Fechar</button></li>
        <li><a href="index.html">Página Inicial</a></li>
      </ul>
    </div>
    <table>
      <tr>
        <th>Código</th>
        <th>Nome</th>
        <th>Departamento</th>
        <th>Operações</th>
      </tr>
      <%List<Funcionario> funcionarios = RepositorioFuncionario.getCurrentInstance().readAll();%>
      <%for (Funcionario f: funcionarios) {%>
      <tr>
        <td><%= f.getCodigo() %></td>
        <td><%= f.getNome() %></td>
        <td><%= f.getDepartamento() %></td>
        <td>
          <a href="FuncionarioServletComJsp?codigo=<%= f.getCodigo() %>&redirect=visualizar">Visualizar</a>
          <a href="FuncionarioServletComJsp?codigo=<%= f.getCodigo() %>&redirect=atualizar">Atualizar</a>
          <a href="#" onclick="deletar(<%= f.getCodigo() %>)">Deletar</a>
        </td>
      </tr>
      <%}%>
    </table>
    <script>
      modalCadastro = document.getElementById("modalCadastro");
      modalVisualizacao = document.getElementById("modalVisualizacao");
      
      <%String redirect = request.getParameter("redirect");
        if (redirect == null) {%>
          document.body.removeChild(modalCadastro);
          document.body.removeChild(modalVisualizacao);
      <%} else if (redirect.equals("visualizar")) {%>
        document.body.removeChild(modalCadastro);
      <%} else {%>
        document.body.removeChild(modalVisualizacao);
      <%}%>
        
      function modalCadastroOpen() {
        document.body.appendChild(modalCadastro);
      }
      
      function modalCadastroClose() {
        document.body.removeChild(modalCadastro);
      }
      
      function modalVisualizacaoClose() {
        document.body.removeChild(modalVisualizacao);
      }
      
      function deletar(codigo) {
        fetch("FuncionarioServletComJsp?codigo=" + codigo, {method: "delete"}).then(function(response) {
          location.reload();
        });
      }
    </script>
  </body>
</html>
