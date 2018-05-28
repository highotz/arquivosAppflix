package br.com.senaijandira.room;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import java.io.InputStream;

public class CadastroActivity extends AppCompatActivity {

    RatingBar rt_avaliacao;
    EditText txt_sinopse,txt_link,txt_titulo;
    ImageView img_producao;
    Bitmap foto;

    int COD_GALERIA = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //findview by Ids
        rt_avaliacao = findViewById(R.id.rt_avaliacao);
        txt_sinopse=findViewById(R.id.txt_sinopse);
        txt_link=findViewById(R.id.txt_link);
        txt_titulo=findViewById(R.id.txt_titulo);
        img_producao = findViewById(R.id.img_producao);

    }


    public void salvarFilme(View view) {



        Toast.makeText(this, "Filme salvo com sucesso " , Toast.LENGTH_SHORT)
                .show();

        finish();
    }


    //abrir a galeria de imagens atraves
    //da imageView
    public void abrirGaleria(View v){

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");

        startActivityForResult(intent , COD_GALERIA);
    }

    //Aqui que volta o resultado da chamada a galeria
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == COD_GALERIA ){

            if(resultCode == Activity.RESULT_OK){
                //selecionou alguma coisa

                try {
                    //pegando a img em binario
                    InputStream inp = getContentResolver()
                            .openInputStream(data.getData());

                    //transformando o stream em bitmap
                    foto = BitmapFactory.decodeStream(inp);

                    //colocando a fotinha
                    img_producao.setImageBitmap(foto);

                }catch (Exception ex){
                    ex.printStackTrace();
                }

            }
        }
    }
}
