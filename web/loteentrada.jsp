<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="ifpe" uri="br.recife.edu.ifpe.customtags"%>

<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <style>th, td {border: 1px solid #888;}</style>
  </head>
  <body>
    <h1>Cadastro de Lote de Entrada</h1>
    <ifpe:carregaproduto/>
    <table>
      <caption>Produtos Cadastrados</caption>
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
  </body>
</html>