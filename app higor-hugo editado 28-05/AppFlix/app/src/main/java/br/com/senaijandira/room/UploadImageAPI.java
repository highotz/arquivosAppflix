package br.com.senaijandira.room;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by 17170106 on 21/05/2018.
 */

public class UploadImageAPI extends AsyncTask<Bitmap, Void, String>{

    private final String API_URL = "http://10.0.2.2/higao/apiUpload/upload_imagem.php";


    ProgressDialog progress;

    Context ctx;

    StringBuilder nomeImagem;

    public UploadImageAPI(Context ctx, StringBuilder nomeImagem){
        this.nomeImagem = nomeImagem;
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progress = ProgressDialog.show(ctx,"Upload","aguarde");
    }

    @Override
    protected String doInBackground(Bitmap... bitmaps) {

        if(bitmaps.length > 0){
            Bitmap img = bitmaps[0];

            byte[] arrBytes = ImageHelper.toByteArray(img);

            String img_string = Base64.encodeToString(arrBytes, Base64.DEFAULT);

            HashMap<String, String> valores = new HashMap<>();

            valores.put("image",img_string);

            String retorno = HttpConnection.post(API_URL,valores);

            return retorno;
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);


        progress.dismiss();

        if(s != null){
            nomeImagem.append(s);
            Log.d("imagem",s);
        }
    }
}
