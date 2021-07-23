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
      #feedback {font-weight: 900; color: #1d7d36;}
      #links {margin-top: 2em;}
      th, td {border: 1px solid #888; text-align: center;}
      .modal {position: absolute; top: 100px; left: 530px;}
      .tabelaItens {margin-bottom: 2em;}
    </style>
  </head>
  <body>
    <h1>Lotes Inseridos</h1>
    <p id="feedback"><c:out value="${msg}"/></p>
    <c:remove var="msg" scope="session"/>
    <table>
      <tr>
        <th>Data</th>
        <th>Código</th>
        <th>Quantidade Total</th>
        <th>Visualizar</th>
      </tr>
      <ifpe:carrega lista="LoteEntrada"/>
      <c:forEach var="lote" items="${lotesEntrada}">
        <tr>
          <td>${lote.data}</td>
          <td>${lote.codigo}</td>
          <td>${lote.quantidadeTotal}</td>
          <td><button onclick="visualizar(${lote.codigo})">Visualizar</button></td>
        </tr>
      </c:forEach>
    </table>
    <div id="links">
      <a href="index.html">Página Inicial</a>
    </div>
      <script>
        var modal;
        function visualizar(codigo) {
          fetch("LoteEntradaServlet?codigo=" + codigo, {method: "get"}).then(function(response) {
            response.text().then(function(text) {
              let lote = JSON.parse(text);
              let titulo = document.createElement("h1");
              titulo.innerHTML = "Visualizar Lote Entrada";
              let frase = document.createElement("p");
              frase.innerHTML = "Codigo: " + lote.codigo + "<br/>Descrição: " + lote.descricao;
              let tabela = document.createElement("table");
              tabela.setAttribute("class", "tabelaItens");
              for (let i = 0; i < lote.itens.length; i++) {
                let celula1 = document.createElement("td");
                celula1.innerHTML = lote.itens[i].codigo;
                let celula2 = document.createElement("td");
                celula2.innerHTML = lote.itens[i].nomeProduto;
                let celula3 = document.createElement("td");
                celula3.innerHTML = lote.itens[i].quantidade;
                let linha = document.createElement("tr");
                linha.appendChild(celula1);
                linha.appendChild(celula2);
                linha.appendChild(celula3);
                tabela.appendChild(linha);
              }
              let botao = document.createElement("button");
              botao.setAttribute("onclick", "fecharModal()");
              botao.innerHTML = "Fechar";
              modal = document.createElement("div");
              modal.setAttribute("class", "modal");
              modal.appendChild(titulo);
              modal.appendChild(frase);
              modal.appendChild(tabela);
              modal.appendChild(botao);
              document.body.appendChild(modal);
            });
          });
        }
        
        function fecharModal() {
          document.body.removeChild(modal);
          modal = "";
        }
      </script>
  </body>
</html>