package br.com.senaijandira.room;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            startActivity(new Intent(view.getContext(), CadastroActivity.class));

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        new AsyncTask<Void, Void, String>(){
            Bitmap imagem;
            String json = "";
            String url = "http://10.0.2.2/Inf3T20181/Turma%20B/projetoHCE/apicontatos/selecionar.php";

            @Override
            protected String doInBackground(Void... voids) {
                json = HttpConnection.get(url);
                return json;
            }

            @Override
            protected void onPostExecute(String json) {
                super.onPostExecute(json);
                ArrayList<Producao> listaProducoes = new ArrayList<>();
                if(json != null){
                    try {
                        JSONArray arrayContatos = new JSONArray(json);

                        for(int i = 0; i < arrayContatos.length(); i++){
                            JSONObject contatoJson = arrayContatos.getJSONObject(i);

                            Producao producao = new Producao();
                            producao.setId(contatoJson.getInt("id"));
                            producao.setAvaliacao(Float.parseFloat(contatoJson.getString("avaliacao")));
                            producao.setSinopse(contatoJson.getString("sinopse"));
                            producao.setLink(contatoJson.getString("caminhoVideo"));
                            producao.setTitulo(contatoJson.getString("titulo"));
                            String link = "http://10.0.2.2/Inf3T20181/Turma%20B/projetoHCE/apicontatos/" + contatoJson.getString("caminhoFoto");

                            Picaso.with(this).load().into(imagem);
                            producao.setImagem(imagem);
                            listaProducoes.add(producao);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    adapter.addAll(listaContatos);
                }
            }

        }.execute();
    }
}
