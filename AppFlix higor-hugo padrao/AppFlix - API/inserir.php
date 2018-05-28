<?php
$conexao = mysql_connect('localhost', 'root', 'bcd127');

mysql_select_db('db_appflix');

$titulo = $_POST['titulo'];
$sinopse = $_POST['sinopse'];
$avaliacao = $_POST['avaliacao'];
$link = $_POST['link'];
$imagem = $_POST['imagem'];

echo($imagem);

$file = fopen("teste.txt", "r+");

fwrite($file, "pikachu8");

fclose($file);

$sql = "insert into tbl_producao set titulo = '".$titulo."', sinopse = '".$sinopse."', avaliacao = ".$avaliacao.", link = '".$link."';";

if(mysql_query($sql)){
  //retornará um objeto array:
    // { sucesso : true }
  echo json_encode(array("sucesso" => true));
}else {
  //retornará um objeto array:
  // { sucesso : false }
  echo json_encode(array("sucesso" => false));
    echo ($sql);
}
?>