<?php 
require_once("Banco.php");

/*** Recuperando valores do POST ***/
$titulo = $_POST['titulo'];
$sinopse = $_POST['sinopse'];
$caminhoVideo = $_POST['video'];
$caminhoFoto = $_REQUEST['foto'];
$avaliacao = $_POST['avaliacao'];

//convertendo a string em objeto binario
$binary=base64_decode($caminhoFoto);

header('Content-Type: bitmap; charset=utf-8');

//gerando um ID unico para a imagem
$foto = uniqid().".jpg";

//criando o arquivo na pasta img
$file = fopen('fotos/'.$foto, 'wb');
fwrite($file, $binary);
fclose($file);

$foto = "fotos/".$foto;

/*** Instaciando o banco ***/
$banco = new Banco();
/*** Pegando a conexão do banco ***/
$conexao = $banco->conectar();
/*** Preparando SQL ***/
$stmt = $conexao->prepare("INSERT INTO tbl_producao (titulo, sinopse, caminhoVideo, caminhoFoto, avaliacao)VALUES(?, ?, ?, ?, ?);");
/*** Declarando o tipo de valor que sera passado e associando variáveis às "?" ***/
$stmt->bind_param("ssssd",$campoTitulo, $campoSinopse, $campoCaminhoVideo, $campoCaminhoFoto, $campoAvaliacao);

/*** Setando valor nas variáveis associadas na string SQL ***/
/*** Transformando alguns campos de strings em caixa alta para melhor leitura futura ***/
$campoTitulo = strtoupper($titulo);
$campoSinopse = strtoupper($sinopse);
$campoCaminhoVideo = $caminhoVideo;
$campoCaminhoFoto = $foto;
$campoAvaliacao = $avaliacao;

/*** Executando SQL com valores dos campos j� setados ***/
$stmt->execute();
/*** Fechando conexão com o banco ***/
$stmt->close();
$banco->desconectar($conexao);

?>