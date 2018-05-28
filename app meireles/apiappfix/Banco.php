<?php
class Banco{
	private $servidor = "127.0.0.1";
	private $usuario = "root";
	private $senha = "bcd127";
	private $banco = "db_api_appfix";
	private $conexao;
	
	public function conectar(){
		$conexao = new mysqli($this->servidor, $this->usuario, $this->senha, $this->banco);
        if($conexao->connect_error){
            die("Falha ao conectar: " . $conexao->connect_error);
        }
		return $conexao;
    }
	
    public function desconectar($conexao){
        mysqli_close($conexao);
    }

}
?>