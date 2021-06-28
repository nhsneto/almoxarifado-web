<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <title>Cadastro Funcionário</title>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" href="cadastro-funcionario-estilo.css"/>
  </head>
  <body>
    <div id="container">
      <h1>${(param.redirect != null && param.redirect eq "atualizar") ? "Atualizar Funcionário" : "Cadastrar Funcionário"}</h1>
      <form method="post" action="FuncionarioServletComJsp">
        <ul>
          <li>
            <label>
              <%String redirectAtualizar = request.getParameter("redirect");
              if (redirectAtualizar != null && redirectAtualizar.equals("atualizar")) {%>
              <input type="hidden" name="codigo" value="${funcionario.codigo}"/>
              <%} else {%>
              Código: <input type="number" name="codigo" value=""/>
              <%}%>
            </label>
          </li>
          <li><label>Nome: <input type="text" name="nome" value="${(param.redirect != null && param.redirect eq "atualizar") ? funcionario.nome : ""}"/></label></li>
          <li><label>Departamento: <input type="text" name="departamento" value="${(param.redirect != null && param.redirect eq "atualizar") ? funcionario.departamento : ""}"/></label></li>
          <li><input type="hidden" name="${(param.redirect != null && param.redirect eq "atualizar") ? "atualizar" : "cadastrar"}" value="1"/></li>
          <li><input type="submit" value="${(param.redirect != null && param.redirect eq "atualizar") ? "Atualizar" : "Cadastrar"}"/></li>
        </ul>
      </form>
    </div>
  </body>
</html>
