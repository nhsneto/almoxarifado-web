<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="ifpe" uri="br.recife.edu.ifpe.customtags"%>

<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <style>
      h1 + p {font-weight: 900; color: #27b04b;}
      table {margin-bottom: 2em;}
      th, td {border: 1px solid #888;}
      tr > td:not(:first-child) {text-align: center;}
      caption {font-size: 1.2em; font-weight: 800;}
      .botaoAdicionar {border: none; background-color: #27b04b; color: #fff; padding: 2px 10px;}
      .botaoAdicionar:active {background-color: #0f0;}
      .botaoRemover {border: none; background-color: #d40d0d; color: #fff; padding: 2px 10px;}
      .botaoRemover:active {background-color: #f00;}
      #botaoCadastrar {display: block; margin-bottom: 2em;}
    </style>
  </head>
  <body>
    <h1>Inserção Lote de Entrada</h1>
    <p><c:out value="${msg}"/></p>
    <c:remove var="msg" scope="session"/>
    <ifpe:carrega lista="Produto"/> <!-- Tag carrega com o valor 'Produto' para o atributo lista -->
    <table>
      <caption>Produtos Cadastrados</caption>
      <tr>
        <th>Código</th>
        <th>Nome</th>
        <th>Marca</th>
        <th>Categoria</th>
        <th>Adicionar</th>
      </tr>
      <c:forEach var="p" items="${produtos}">
        <tr>
          <td>${p.codigo}</td>
          <td>${p.nome}</td>
          <td>${p.marca}</td>
          <td>${p.categoria}</td>
          <td>
            <button class="botaoAdicionar" onclick="adiciona(${p.codigo})">+</button>
          </td>
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
    <c:if test="${loteEntrada != null}">
      <table>
        <caption>Produtos Inseridos no Lote de Entrada</caption>
        <tr>
          <th>Código</th>
          <th>Nome</th>
          <th>Marca</th>
          <th>Categoria</th>
          <th>Quantidade</th>
          <th>Operações</th>
        </tr>
        <c:forEach var="i" items="${loteEntrada.itens}">
          <tr>
            <td>${i.produto.codigo}</td>
            <td>${i.produto.nome}</td>
            <td>${i.produto.marca}</td>
            <td>${i.produto.categoria}</td>
            <td>${i.quantidade}</td>
            <td>
              <button class="botaoAdicionar" onclick="adiciona(${i.produto.codigo})">+</button>
              <button class="botaoRemover" onclick="remove(${i.produto.codigo})">-</button>
            </td>
          </tr>
        </c:forEach>
      </table>
      <button id="botaoCadastrar" onclick="cadastrarLote()">Cadastrar Lote</button>
    </c:if>
    <a href="index.html">Página Inicial</a>
    <script>
      function adiciona(codigo) {
        fetch("LoteEntradaServlet?operacao=adicionar&codigo=" + codigo, {method: "put"}).then(function() {
          location.reload();
        });
      }
      
      function remove(codigo) {
        fetch("LoteEntradaServlet?operacao=remover&codigo=" + codigo, {method: "put"}).then(function() {
          location.reload();
        });
      }
      
      function cadastrarLote() {
        fetch("LoteEntradaServlet", {method: "post"})
                .then(function(response) {
                  if (response.status === 500) {
                    location.reload();
                  } else {
                    location.href = "index.html";
                  }
                }).catch(function(erro) {
                  location.reload();
                });
      }
    </script>
  </body>
</html>