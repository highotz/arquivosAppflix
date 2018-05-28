package br.com.senaijandira.uploadimage;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    ImageView image_view;

    Bitmap imagemBitMap;
    int COD_GALERIA = 1;

    StringBuilder nomeImage = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        image_view = findViewById(R.id.image_view);
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
                    imagemBitMap = BitmapFactory.decodeStream(inp);

                    //colocando a fotinha
                    image_view.setImageBitmap(imagemBitMap);

                }catch (Exception ex){
                    ex.printStackTrace();
                }

            }
        }
    }


    public void uploadImage(View view) {

        nomeImage.setLength(0);//limpando o stringbuilder
        new UploadImageAPI(this, nomeImage)
                .execute(imagemBitMap);


    }

    public void verificar(View view) {

        AlertDialog.Builder buider = new AlertDialog.Builder(this);

        buider.setMessage(nomeImage.toString());

        buider.setPositiveButton("OK", null);

        buider.show();

    }
}
