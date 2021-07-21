<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="ifpe" uri="br.recife.edu.ifpe.customtags"%>

<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <meta charset="utf-8"/>
    <style>
      #links {margin-top: 2em;}
      th, td {border: 1px solid #888; text-align: center;}
    </style>
  </head>
  <body>
    <h1>Lotes Inseridos</h1>
    <ifpe:carrega lista="LoteEntrada"/>
    <table>
      <tr>
        <th>Data</th>
        <th>Código</th>
        <th>Quantidade Total</th>
      </tr>
      <c:forEach var="lote" items="${lotesEntrada}">
        <tr>
          <td>${lote.data}</td>
          <td>${lote.codigo}</td>
          <td>${lote.quantidadeTotal}</td>
        </tr>
      </c:forEach>
    </table>
    <div id="links">
      <a href="index.html">Página Inicial</a>
    </div>
  </body>
</html>