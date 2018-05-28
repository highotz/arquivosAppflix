<?php
require_once("Banco.php");

/*** Instaciando o banco ***/
$banco = new Banco();
/*** Pegando a conexão do banco ***/
$conexao = $banco->conectar();
/*** Preparando SQL ***/
$sql = "SELECT * tbl_producao";

$resultado = mysqli_query($conexao, $sql);

$listaProducoes = array();

if(mysqli_num_rows($resultado) > 0){
  
  while($producoes = mysqli_fetch_assoc($resultado)){
      
      $listaProducoes [] = $producoes;
      
  }
}
echo json_encode($listaProducoes);

?>