<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastrar Item</title>
</head>
<body>

<h1>Cadastrar Item</h1>

<form action="cadastrar" method="post">

    <label>Descrição:</label>
    <input type="text" name="descricao"><br><br>

    <label>Categoria:</label>
    <input type="text" name="categoria"><br><br>

    <label>Local:</label>
    <input type="text" name="local"><br><br>

    <label>Data:</label>
    <input type="date" name="data"><br><br>

    <label>Observação:</label>
    <textarea name="observacao"></textarea><br><br>

    <button type="submit">
        Cadastrar
    </button>

</form>

</body>
</html>