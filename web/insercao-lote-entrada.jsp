<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="ifpe" uri="br.recife.edu.ifpe.customtags"%>

<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <style>
      th, td {border: 1px solid #888;}
      tr > td:not(:first-child) {text-align: center;}
      caption {font-size: 1.2em; font-weight: 800;}
      table {margin-bottom: 2em;}
    </style>
  </head>
  <body>
    <h1>Inserção Lote de Entrada</h1>
    <ifpe:carrega lista="Produto"/> <!-- Tag carrega com o valor 'Produto' para o atributo lista -->
    <table>
      <caption>Produtos</caption>
      <tr>
        <th>Código</th>
        <th>Nome</th>
        <th>Marca</th>
        <th>Categoria</th>
      </tr>
      <c:forEach var="p" items="${produtos}">
        <tr>
          <td>${p.codigo}</td>
          <td>${p.nome}</td>
          <td>${p.marca}</td>
          <td>${p.categoria}</td>
        </tr>
      </c:forEach>
    </table>
    <ifpe:carrega lista="Funcionario"/> <!-- Tag carrega com o valor 'Funcionario' para o atributo lista -->
    <table>
      <caption>Funcionários</caption>
      <tr>
        <th>Código</th>
        <th>Nome</th>
        <th>Departamento</th>
      </tr>
      <c:forEach var="f" items="${funcionarios}">
        <tr>
          <td>${f.codigo}</td>
          <td>${f.nome}</td>
          <td>${f.departamento}</td>
        </tr>
      </c:forEach>
    </table>
    <a href="index.html">Página Inicial</a>
  </body>
</html>
