<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="br.recife.edu.ifpe.model.repositorios.RepositorioProdutos"%>
<%@page import="br.recife.edu.ifpe.model.classes.Produto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="ifpe" uri="br.recife.edu.ifpe.customtags" %>

<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <!--
    <ifpe:helloworld/>
    <c:if test="${2 + 2 == 4}">
      <h1>Entrou no if</h1>
    </c:if>
    -->
    <title>Produtos</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta charset="utf-8"/>
    <style>
      #botaoNovoProduto {margin: 32px 0;}
      .modal {position: absolute; left: 550px; top: 0;}
      th, td {border: 1px solid #888;}
      h1 + p {color:#1d7d36; font-weight: 600;}
      p + #botaoNovoProduto {margin: 0 0 32px;}
      #links {margin-top: 2em;}
    </style>
  </head>
  <body>
    <h1>Produtos Cadastrados</h1>
    <%
      String mensagem = (String)session.getAttribute("mensagem");
      if (mensagem != null) {
        out.println("<p>" + mensagem + "</p>");
        session.removeAttribute("mensagem");
      }
    %>
    <button id="botaoNovoProduto" onclick="modalCadastroOpen()">Novo Produto</button>
    <div id="modal-cadastro" class="modal">
      <%@include file="cadastro-produto.jsp" %>
      <button onclick="modalCadastroClose()">Fechar</button>
    </div>
    <div id="modal-visualizacao" class="modal">
      <%@include file="visualizacao-produto.jsp" %>
      <button onclick="modalVisualizacaoClose()">Fechar</button>
    </div>
    <% List<Produto> produtos = RepositorioProdutos.getCurrentInstance().readAll(); %>
    <% request.setAttribute("produtos", produtos); %>
    <!-- <ifpe:tag2 var="teste" attr3="${produtos}">Testando o corpo da tag2...</ifpe:tag2> -->
    <table>
      <tr>
        <th>Código</th>
        <th>Nome</th>
        <th>Marca</th>
        <th>Categoria</th>
        <th>Operações</th>
      </tr>
      <% for (Produto p: produtos) { %>
      <tr>
        <td><%= p.getCodigo() %></td>
        <td><%= p.getNome() %></td>
        <td><%= p.getMarca() %></td>
        <td><%= p.getCategoria() %></td>
        <td>
          <a href="ProdutoServletComJsp?codigo=<%= p.getCodigo() %>&redirect=visualizar">Visualizar</a>
          <a href="ProdutoServletComJsp?codigo=<%= p.getCodigo() %>&redirect=atualizar">Atualizar</a>
          <a href="#" onclick="deletar(<%= p.getCodigo() %>)">Deletar</a>
        </td>
      </tr>
      <% } %>
    </table>
    <div id="links">
      <a href="index.html" id="home">Página Inicial</a>
    </div>
    <script>
      var modalCadastro = document.getElementById("modal-cadastro");
      var modalVisualizacao = document.getElementById("modal-visualizacao");
      
      <% String redirect = request.getParameter("redirect");
        if (redirect == null) { %>
          document.body.removeChild(modalCadastro);
          document.body.removeChild(modalVisualizacao);
      <% } else if (redirect.equals("visualizar")) { %>
            document.body.removeChild(modalCadastro);
      <% } else { %>
            document.body.removeChild(modalVisualizacao);
      <% } %>
      
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
        fetch("ProdutoServletComJsp?codigo="+ codigo, {method: "delete"}).then(function(response) {
          location.reload();
        });
      }
    </script>
  </body>
</html>
