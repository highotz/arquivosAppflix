<?php
$conexao = mysql_connect('localhost', 'root', 'bcd127');

mysql_select_db('db_appflix');





  sleep(10);

  //Pegando a imagem enviada em formato de string
  $recievedImage = $_REQUEST['image'];

  //convertendo a string em objeto binario
  $binary=base64_decode($recievedImage);

  header('Content-Type: bitmap; charset=utf-8');

  //gerando um ID unico para a imagem
  $img_name = uniqid().".jpg";

  //criando o arquivo na pasta img
  $file = fopen('img/'.$img_name, 'wb');
  fwrite($file, $binary);
  fclose($file);

  //devolvendo o nome do arquivo que foi salvo
  echo $img_name;


$titulo = $_POST['titulo'];
$sinopse = $_POST['sinopse'];
$avaliacao = $_POST['avaliacao'];
$link = $_POST['link'];


$sql = "insert into tbl_producao set titulo = '".$titulo."', sinopse = '".$sinopse."', avaliacao = ".$avaliacao.",foto='img/".$img_name."', link = '".$link."';";

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