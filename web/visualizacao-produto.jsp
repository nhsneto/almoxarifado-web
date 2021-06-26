<%@page import="br.recife.edu.ifpe.model.classes.Produto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>JSP Page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style>
      .tabela-visualizacao {margin-bottom: 50px;}
      .tabela-visualizacao th {text-align: left; border: 0; margin-right: 50px; }
      .tabela-visualizacao td {text-align: center; border: 0;}
      #mensagem-visualizacao-erro {color: red; font-weight: 600; margin-bottom: 50px;}
    </style>
  </head>
  <body>
    <h1>Produto Cadastrado</h1>
    <% Produto produto = (Produto) request.getAttribute("produto");
    if (produto != null) { %>
      <table class="tabela-visualizacao">
        <tr><th scope="row">Código:</th><td><%= produto.getCodigo() %></td></tr>
        <tr><th scope="row">Nome:</th><td><%= produto.getNome() %></td></tr>
        <tr><th scope="row">Marca:</th><td><%= produto.getMarca() %></td></tr>
        <tr><th scope="row">Categoria:</th><td><%= produto.getCategoria()%></td></tr>
        <tr><th scope="row">Descrição:</th><td><%= produto.getDescricao()%></td></tr>
      </table>
    <% } else { %>
      <p id="mensagem-visualizacao-erro">Produto não cadastrado.</p>
    <% } %>
  </body>
</html>
