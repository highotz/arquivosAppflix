package com.example.a17170132.jogoforca;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends Activity {
    TextView txtPalavraSorteada;
    TextView txtLetraDigitada;
    TextView txtQuantidadeErros;

    LinearLayout linearPalavra;

    //declarando vetor de palavra
    TextView[] vetorPalavra;

    //controlar quantidade de erros
    int erros = 0;

    //palavras para serem sorteadas
    String[]palavras = new String[] {"futebol", "estadio", "bola", "rede", "jogador", "gol", "arara"};

    //palavra que esta sendo advinhada
    String palavra = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        txtPalavraSorteada = (TextView) findViewById(R.id.txtPalavraSorteada);
        txtLetraDigitada = (TextView) findViewById(R.id.txtLetraDigitada);
        txtQuantidadeErros = (TextView) findViewById(R.id.txtQuantidadeErros);

        linearPalavra = (LinearLayout) findViewById(R.id.linearPalavra);
    }

    public void montarLinearPalavra(){
        vetorPalavra = new TextView[palavra.length()];
        linearPalavra.removeAllViews();
        for(int i = 0; i < palavra.length(); i++){
            TextView text = new TextView(this);
            text.setText("*");
            text.setTextSize(30);
            text.setPadding(0,0,10,0);
            text.setTag(palavra.substring(i, i+1));
            vetorPalavra[i] = text;
            linearPalavra.addView(text);
        }
    }

    public void montarLetraCorreta(String letra){
        for(TextView text: vetorPalavra){
            if(text.getTag().toString().equals(letra)){
                text.setText(letra);
            }
        }
    }


    public void gerarPalavra(View view) {
        //random
        Random rd = new Random();

        //indice aleatorio com base no tamanho do array
        int index = rd.nextInt(palavras.length);

        //setando palavra escolhida
        palavra = palavras[index];

        txtPalavraSorteada.setText(palavra);
        montarLinearPalavra();
    }

    private boolean verificarLetra(String letra){

        return palavra.contains(letra);
    }

    //click do botao
    public void verificarLetra(View view) {

        String letra = txtLetraDigitada.getText().toString();
        montarLetraCorreta(letra);
        boolean existe = verificarLetra(letra);

        if(existe){
            Toast.makeText(this, "Existe", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Não Existe", Toast.LENGTH_SHORT).show();
            erros++;
            txtQuantidadeErros.setText("Erros: " + erros);
        }
        txtLetraDigitada.setText("");
    }
}
