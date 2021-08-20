<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="ifpe" uri="br.recife.edu.ifpe.customtags"%>

<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta charset="utf-8"/>
    <title>JSP Page</title>
    <style>
      h1 + p {font-weight: 900; color: #27b04b;}
      h2 {font-size: 1.2em; font-weight: 800;}
      span {font-weight: 900;}
      ul {padding-left: 5px;}
      li {list-style: none; margin-bottom: 3px;}
      table, div {margin-bottom: 2em;}
      th, td {border: 1px solid #888;}
      td:not(:first-child) {text-align: center;}
      caption {font-size: 1.2em; font-weight: 800;}
      .botaoAdicionar {border: none; background-color: #27b04b; color: #fff; padding: 2px 10px;}
      .botaoAdicionar:active {background-color: #0f0;}
      .botaoRemover {border: none; background-color: #d40d0d; color: #fff; padding: 2px 10px;}
      .botaoRemover:active {background-color: #f00;}
      #botaoCadastrar {display: block; margin-bottom: 2em;}
    </style>
  </head>
  <body>
    <h1>Inserção Lote de Saída</h1>
    <p><c:out value="${msg}"/></p>
    <table>
      <caption>Itens no Estoque</caption>
      <tr>
        <th>Código</th>
        <th>Nome do Produto</th>
        <th>Quantidade em Estoque</th>
        <th>Adicionar</th>
      </tr>
      <ifpe:carrega lista="Estoque"/>
      <c:forEach var="item" items="${estoque.itens}">
        <tr>
          <td><c:out value="${item.codigo}"/></td>
          <td><c:out value="${item.produto.nome}"/></td>
          <td><c:out value="${item.quantidade}"/></td>
          <td><button class="botaoAdicionar" onclick="adicionarItem(${item.codigo})">+</button></td>
        </tr>
      </c:forEach>
    </table>
    <table>
      <caption>Funcionário Responsável:</caption>
      <tr>
        <th>Código</th>
        <th>Nome</th>
        <th>Departamento</th>
        <th>Adicionar</th>
      </tr>
        <ifpe:carrega lista="Funcionario"/>
        <c:forEach var="f" items="${funcionarios}">
        <tr>
          <td>${f.codigo}</td>
          <td>${f.nome}</td>
          <td>${f.departamento}</td>
          <td><button class="botaoAdicionar" onclick="adicionarFuncionario(${f.codigo})">+</button></td>
        </tr>
        </c:forEach>
    </table>
    <c:if test="${loteSaida != null}">
    <table>
      <caption>Lote de Saída - Itens</caption>
      <tr>
        <th>Código</th>
        <th>Nome do Produto</th>
        <th>Quantidade</th>
        <th>Remover</th>
      </tr>
      <c:forEach var="item" items="${loteSaida.itens}">
        <tr>
          <td>${item.codigo}</td>
          <td>${item.produto.nome}</td>
          <td>${item.quantidade}</td>
          <td><button class="botaoRemover" onclick="removerItem(${item.codigo})">-</button></td>
        </tr>
      </c:forEach>
      <c:if test="${loteSaida.responsavel != null}">
        <tr>
          <th>Responsável:</th>
          <td colspan="2">${loteSaida.responsavel.nome}</td>
          <td><button class="botaoRemover" onclick="removerFuncionario()">-</button></td>
        </tr>
      </c:if>
    </table>
    <div>
      <label for="descricao">Descrição:</label>
      <textarea id="descricao" cols="41"></textarea>
    </div>
    <div><button id="botaoCadastrar" onclick="cadastrarLoteSaida()">Cadastrar</button></div>
  </c:if>
  <div>
    <a href="index.html">Página Inicial</a>
  </div>
  <script>
    function adicionarItem(codigo) {
      fetch("LoteSaidaServlet?operacao=adicionar-item&codigo=" + codigo, {method: "put"}).then(function () {
        location.reload();
      });
    }

    function removerItem(codigo) {
      fetch("LoteSaidaServlet?codigo=" + codigo, {method: "delete"}).then(function () {
        location.reload();
      });
    }
    
    function adicionarFuncionario(codigo) {
      fetch("LoteSaidaServlet?operacao=adicionar-funcionario&codigo=" + codigo, {method: "put"}).then(function () {
        location.reload();
      });
    }
    
    function removerFuncionario() {
      fetch("LoteSaidaServlet?operacao=remover-funcionario&codigo=9999999", {method: "delete"}).then(function () {
        location.reload();
      });
    }

    function cadastrarLoteSaida() {
      let textInput = document.getElementById("descricao");
      let value = textInput.value;
      fetch("LoteSaidaServlet?descricao=" + value, {method: "post"}).then(function () {
        location.href = "lote-saida-apresentacao.jsp";
      });
    }
  </script>
</body>
</html>