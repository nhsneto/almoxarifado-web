<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="ifpe" uri="br.recife.edu.ifpe.customtags"%>

<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>JSP Page</title>
    <style>
      h1 + p {font-weight: 900; color: #1d7d36;}
      th, td {border: 1px solid #888; text-align: center;}
      #links {margin-top: 2em;}
    </style>
  </head>
  <body>
    <h1>Lotes Retirados</h1>
    <p>${msg}</p>
    <c:remove var="msg" scope="session"/>
    <table>
      <tr>
        <th>Data</th>
        <th>Código</th>
        <th>Responsável</th>
        <th>Quantidade</th>
        <th>Visualizar</th>
      </tr>
      <ifpe:carrega lista="LoteSaida"/>
      <c:forEach var="l" items="${lotesSaida}">
        <tr>
          <td>${l.data}</td>
          <td>${l.codigo}</td>
          <td>${l.responsavel.nome}</td>
          <td>${l.quantidadeTotal}</td>
          <!-- <td><button onclick="visualizar(${lote.codigo})">Visualizar</button></td> -->
        </tr>
      </c:forEach>
    </table>
    <div id="links">
      <a href="index.html">Página Inicial</a>
    </div>
    <script>
      
    </script>
  </body>
</html>
