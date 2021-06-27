<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <title>Cadastro Produto</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="cadastroproduto-estilo.css" />
  </head>
  <body>
    <div id="container">
      <h1>${(param.redirect eq "atualizar") ? "Atualizar Produto" : "Cadastrar Produto"}</h1>
      <form method="post" action="ProdutoServletComJsp">
        <ul>
          <li><label>Código: <input type="text" name="codigo" value="${(param.redirect != null && param.redirect eq "atualizar") ? produto.codigo : ""}"/></label></li>
          <li><label>Nome: <input type="text" name="nome" class="tabulation" value="${(param.redirect != null && param.redirect eq "atualizar") ? produto.nome : ""}"/></label></li>
          <li><label>Marca: <input type="text" name="marca" class="tabulation" value="${(param.redirect != null && param.redirect eq "atualizar") ? produto.marca : ""}"/></label></li>
          <li><label>Categoria: <input type="text" name="categoria" value="${(param.redirect != null && param.redirect eq "atualizar") ? produto.categoria : ""}"/></label></li>
          <li><label>Descrição: <textarea name="descricao" cols="22">${(param.redirect != null && param.redirect eq "atualizar") ? produto.descricao : ""}</textarea></label></li>
          <li><input type="hidden" name="${(param.redirect != null && param.redirect eq "atualizar") ? "atualizar" : "cadastrar"}" value="1"/></li>
          <li><input type="submit" value="${(param.redirect != null && param.redirect eq "atualizar") ? "Atualizar" : "Cadastrar"}"/></li>
        </ul>
      </form>
    </div>
  </body>
</html>