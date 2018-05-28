package br.com.senaijandira.room;

import android.graphics.Bitmap;

public class Producao {

    private int id;
    private String titulo;
    private String sinopse;
    private String link;
    private Float avaliacao;



    private Bitmap imagem;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Float getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Float avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Bitmap getImagem() {
        return imagem;
    }
    public void setImagem(Bitmap imagem) {
        this.imagem = imagem;
    }
}
