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
      <h1>Cadastro de Produto</h1>
      <form method="post" action="ProdutoServletComJsp">
        <ul>
          <li><label>Código: <input type="text" name="codigo"/></label></li>
          <li><label>Nome: <input type="text" name="nome" class="tabulation"/></label></li>
          <li><label>Marca: <input type="text" name="marca" class="tabulation"/></label></li>
          <li><label>Categoria: <input type="text" name="categoria"/></label></li>
          <li><label>Descrição: <textarea name="descricao" cols="22"></textarea></label></li>
          <li><input type="submit" value="cadastrar"/></li>
          <li><a href="index.html">Página Inicial</a></li>
        </ul>
      </form>
    </div>
  </body>
</html>