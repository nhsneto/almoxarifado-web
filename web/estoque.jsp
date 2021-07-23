<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="ifpe" uri="br.recife.edu.ifpe.customtags"%>

<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8"/>
    <title>JSP Page</title>
    <style>
      th, td {border: 1px solid #888;}
      td:not(:first-child) {text-align: center;}
      div {margin-top: 2em;}
    </style>
  </head>
  <body>
    <h1>Produtos Inseridos no Estoque</h1>
    <table>
      <tr>
        <th>Código</th>
        <th>Nome do Produto</th>
        <th>Quantidade em Estoque</th>
      </tr>
      <ifpe:carrega lista="Estoque"/>
      <c:forEach var="item" items="${estoque.itens}">
        <tr>
          <td><c:out value="${item.codigo}"/></td>
          <td><c:out value="${item.produto.nome}"/></td>
          <td><c:out value="${item.quantidade}"/></td>
        </tr>
      </c:forEach>
    </table>
    <div>
      <a href="index.html">Página Inicial</a>
    </div>
  </body>
</html>