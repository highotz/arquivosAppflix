package br.com.senaijandira.uploadimage;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by sn1041520 on 22/05/2018.
 */

public class UploadImageAPI extends AsyncTask<Bitmap, Void, String> {

    final String URL_API = "http://10.0.2.2/ApiContatos/upload_image.php";


    ProgressDialog progress;
    Context ctx;
    StringBuilder nomeImage;

    public UploadImageAPI(Context ctx, StringBuilder nomeImage){
        this.ctx = ctx;
        this.nomeImage = nomeImage;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progress = ProgressDialog.show(ctx, "Upload",
                "Aguarde...", false,false);
    }

    @Override
    protected String doInBackground(Bitmap... bitmaps) {

        if(bitmaps.length >0){

            Bitmap img = bitmaps[0];


            byte[] arrBytes = ImageHelper.toByteArray(img);

            String img_str = Base64.encodeToString(arrBytes, Base64.DEFAULT);

            HashMap<String, String> values = new HashMap<>();
            values.put("image", img_str);


            String retorno = HttpConnection.post(URL_API, values);


            return retorno;

        }

        return null;
    }


    @Override
    protected void onPostExecute(String imgNome) {
        super.onPostExecute(imgNome);

        progress.dismiss();

        nomeImage.append(imgNome);

        Log.d("doInBackground", imgNome);


    }
}
