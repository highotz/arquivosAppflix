<?php 
class Producao {

	private $id;
	private $titulo;
	private $sinopse;
	private $caminhoVideo;
	private $caminhoFoto;
	private $avaliacao;

	public function getId(){
		return $this->id;
	}
	public function setId($id){
		$this->id = $id;
	}

	public function getTitulo(){
		return $this->titulo;
	}
	public function setTitulo($titulo){
		$this->titulo = $titulo;
	}

	public function getSinopse(){
		return $this->sinopse;
	}
	public function setSinopse($sinopse){
		$this->sinopse = $sinopse;
	}

	public function getCaminhoVideo() {
		return $caminhoVideo;
	}
	public function setCaminho($caminhoVideo){
		$this->caminhoVideo = $caminhoVideo;
	}

	public function getCaminhoFoto() {
		return $caminhoFoto;
	}
	public function setCaminho($caminhoFoto){
		$this->caminhoFoto = $caminhoFoto;
	}

	public function getAvaliacao(){
		return $this->avaliacao;
	}
	public function setAvaliacao($avaliacao){
		$this->avaliacao = $avaliacao;
	}


}
?>