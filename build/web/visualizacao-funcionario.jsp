<%@page import="br.recife.edu.ifpe.model.classes.Funcionario"%>
<%@page import="br.recife.edu.ifpe.model.repositorios.RepositorioFuncionario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>JSP Page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta charset="utf-8"/>
    <style>
      #tabelaVisualizacao {border-spacing: 10px;}
      #tabelaVisualizacao th {text-align: left; border: 0;}
      #tabelaVisualizacao td {text-align: center; border: 0;}
      #mensagemErro {color: #f00; font-weight: 800;}
    </style>
  </head>
  <body>
    <h1>Visualização Produto</h1>
    <%Funcionario funcionario = (Funcionario) request.getAttribute("funcionario");
    if (funcionario != null) {%>
    <table id="tabelaVisualizacao">
      <tr><th scope="row">Código:</th><td><%= funcionario.getCodigo() %></td></tr>
      <tr><th scope="row">Nome:</th><td><%= funcionario.getNome() %></td></tr>
      <tr><th scope="row">Departamento:</th><td><%= funcionario.getDepartamento() %></td></tr>
    </table>
    <%} else {%>
    <p id="mensagemErro">Produto não cadastrado.</p>
    <%}%>
  </body>
</html>
