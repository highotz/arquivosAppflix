package br.com.senaijandira.room;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.InputStream;
import java.util.HashMap;

public class CadastroActivity extends AppCompatActivity {

    RatingBar rtAvaliacao;
    EditText etxtSinopse,etxtLink,etxtTitulo;
    ImageView imgProducao;
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
        rtAvaliacao = findViewById(R.id.cdRtAvaliacao);
        etxtSinopse=findViewById(R.id.cdEtxtSinopse);
        etxtLink=findViewById(R.id.cdEtxtLink);
        etxtTitulo=findViewById(R.id.cdEtxtTitulo);
        imgProducao = findViewById(R.id.cdImgProducao);
    }



    public void salvarFilme(View view) {
        final Producao producao = new Producao();
        producao.setAvaliacao(rtAvaliacao.getRating());
        producao.setSinopse(etxtSinopse.getText().toString());
        producao.setLink(etxtLink.getText().toString());
        producao.setTitulo(etxtTitulo.getText().toString());

        imgProducao = findViewById(R.id.cdImgProducao);

        new AsyncTask<Bitmap, Void, String>(){

            @Override
            protected String doInBackground(Bitmap... bitmaps) {

                /*** tratar imagem para enviar como string ***/
                //Bitmap img = bitmaps[0];
                byte[] arrBytes = ImageHelper.toByteArray(foto);
                String imgString = Base64.encodeToString(arrBytes, Base64.DEFAULT);
                HashMap<String, String> values = new HashMap<>();
                /***                                      ***/
                String url = "http://10.0.2.2/Inf3T20181/Turma%20B/projetos/apiappfix/ProducaoGravar.php";

                HashMap<String, String> dadosPost = new HashMap<>();
                dadosPost.put("titulo", producao.getTitulo());
                dadosPost.put("sinopse", producao.getSinopse());
                dadosPost.put("video", producao.getLink());
                dadosPost.put("avaliacao", producao.getAvaliacao().toString());
                dadosPost.put("foto", imgString);

                Log.d("onPostExecute", url);

                //efetuando uma chamada POST
                return HttpConnection.post(url, dadosPost);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                if(s != null ){

                    try {
                       /* JSONObject json = new JSONObject(s);

                        boolean sucesso = json.getBoolean("sucesso");

                        if(sucesso){

                            Toast.makeText(getApplicationContext(),
                                    "inserido com sucesso",
                                    Toast.LENGTH_LONG).show();
                            finish();
                        }else{
                            Toast.makeText(getApplicationContext(),
                                    "erro ao inserir",
                                    Toast.LENGTH_LONG).show();
                        }
*/
                        Log.d("onPostExecute", s);
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
        }.execute();
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
                    imgProducao.setImageBitmap(foto);

                }catch (Exception ex){
                    ex.printStackTrace();
                }

            }
        }
    }
}
