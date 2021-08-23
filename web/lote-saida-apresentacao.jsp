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
      .modal {position: absolute; top: 100px; left: 580px;}
      .modal h1 + p {color: #000;}
      .tabelaItens {margin-bottom: 2em;}
    </style>
  </head>
  <body>
    <h1>Lotes Retirados</h1>
    <!-- <p><c:out value="${msg}"/></p>
    <c:remove var="msg" scope="session"/>
    -->
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
          <<td><button onclick="visualizar(${l.codigo})">Visualizar</button></td>
        </tr>
      </c:forEach>
    </table>
    <div id="links">
      <a href="index.html">Página Inicial</a>
    </div>
    <script>
      var modal;
      function visualizar(codigo) {
        fetch("LoteSaidaServlet?codigo=" + codigo, {method: "get"}).then(function(response) {
            response.text().then(function(text) {
            let lote = JSON.parse(text);
            let titulo = document.createElement("h1");
            titulo.innerHTML = "Visualizar Lote Saída";
            let frase = document.createElement("p");
            frase.innerHTML = "Codigo: " + lote.codigo + "<br/>Responsável: " + lote.responsavel +
            "<br/>Descrição: " + lote.descricao;

            let tabela = document.createElement("table");
            tabela.setAttribute("class", "tabelaItens");
            let header1 = document.createElement("th");
            header1.innerHTML = "Código";
            let header2 = document.createElement("th");
            header2.innerHTML = "Produto";
            let header3 = document.createElement("th");
            header3.innerHTML = "Quantidade";
            let linhaHeader = document.createElement("tr");
            linhaHeader.appendChild(header1);
            linhaHeader.appendChild(header2);
            linhaHeader.appendChild(header3);
            tabela.appendChild(linhaHeader);

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