<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Cadastro Produto</title>
  </head>
  <body>
    <h1>Produtos Cadastrados</h1>
    <%
      String mensagem = (String)session.getAttribute("mensagem");
      if (mensagem != null) {
        out.println("<p>" + mensagem + "</p>"); 
      }
    %>
  </body>
</html>
