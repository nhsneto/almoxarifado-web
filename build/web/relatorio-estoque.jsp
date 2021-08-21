<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="ifpe" uri="br.recife.edu.ifpe.customtags"%>

<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>JSP Page</title>
    <style>
      table {border: 1px solid #888; margin-bottom: 2em;}
      caption {font-size: 1.2em; font-weight: 900;}
      th, td {border: 1px solid #888; text-align: center;}
      .index {margin-bottom: 2em;}
    </style>
  </head>
  <body>
    <h1>Relatório de Estoque - Movimentações</h1>
    <div class="index">
      <a href="index.html">Página Inicial</a>
    </div>
    <ifpe:carrega lista="LoteEntrada"/>
    <ifpe:carrega lista="LoteSaida"/>
    <ifpe:carrega lista="Data"/>
      
    <c:forEach var="d" items="${data}">
      
      <c:forEach var="loteE" items="${lotesEntrada}">
        <c:if test="${loteE.data eq d}">
          <table>
            <caption>${loteE.data}</caption>
            <tr><th>Movimentação</th><td colspan="2">ENTRADA</td></tr>
            <tr><th>Código</th><td colspan="2">${loteE.codigo}</td></tr>
            <tr><th>Quantidade Total</th><td colspan="2">${loteE.quantidadeTotal}</td></tr>
            <tr><th colspan="3">Itens</th></tr>
            <tr><th>Código</th><th>Nome</th><th>Quantidade</th></tr>
            <c:forEach var="i" items="${loteE.itens}">
              <tr><td>${i.codigo}</td><td>${i.produto.nome}</td><td>${i.quantidade}</td></tr>
            </c:forEach>
            <tr><th>Descrição</th><td colspan="2">${loteE.descricao}</td></tr>
          </table>
        </c:if>
      </c:forEach>

      <c:forEach var="loteS" items="${lotesSaida}">
        <c:if test="${loteS.data eq d}">
          <table>
            <caption>${loteS.data}</caption>
            <tr><th>Movimentação</th><td colspan="2">SAÍDA</td></tr>
            <tr><th>Código</th><td colspan="2">${loteS.codigo}</td></tr>
            <tr><th>Quantidade Total</th><td colspan="2">${loteS.quantidadeTotal}</td></tr>
            <tr><th>Responsável</th><td colspan="2">${loteS.responsavel.nome}</td></tr>
            <tr><th colspan="3">Itens</th></tr>
            <tr><th>Código</th><th>Nome</th><th>Quantidade</th></tr>
            <c:forEach var="i" items="${loteS.itens}">
              <tr><td>${i.codigo}</td><td>${i.produto.nome}</td><td>${i.quantidade}</td></tr>
            </c:forEach>
            <tr><th>Descrição</th><td colspan="2">${loteS.descricao}</td></tr>
          </table>
        </c:if>
      </c:forEach>
    
    </c:forEach>
    <div class="index">
      <a href="index.html">Página Inicial</a>
    </div>
  </body>
</html>