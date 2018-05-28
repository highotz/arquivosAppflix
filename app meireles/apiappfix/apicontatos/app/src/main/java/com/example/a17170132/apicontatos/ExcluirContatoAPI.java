package com.example.a17170132.apicontatos;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

public class ExcluirContatoAPI extends AsyncTask<String, Void, String>{
    private ContatoAdapter adapter;
    private Contato contato;

    public ExcluirContatoAPI(ContatoAdapter adapter, Contato contato){
        this.adapter = adapter;
        this.contato = contato;
    }

    @Override
    protected String doInBackground(String... ids) {
        String url = "http://10.0.2.2/sites_higor/apicontatos/deletar.php?";

        if(ids.length > 0){
            String id = ids[0];
            url += "id=" + id;
            return HttpConnection.get(url);
        }
        return null;
    }

    @Override
    protected void onPostExecute(String string) {
        super.onPostExecute(string);

        try{
            JSONObject json = new JSONObject(string);
            boolean sucesso = json.getBoolean("sucesso");
            if(sucesso){
                adapter.removerItem(contato);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }


        Log.d("onPostExecute", string);
    }
}
