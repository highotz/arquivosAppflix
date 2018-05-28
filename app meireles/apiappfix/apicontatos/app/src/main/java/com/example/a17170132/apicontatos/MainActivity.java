package com.example.a17170132.apicontatos;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ContatoAdapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new ContatoAdapter(this);

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();

        new AsyncTask<Void, Void, String>(){

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
                ArrayList<Contato> listaContatos = new ArrayList<>();
                if(json != null){
                    try {
                        JSONArray arrayContatos = new JSONArray(json);

                        for(int i = 0; i < arrayContatos.length(); i++){
                            JSONObject contatoJson = arrayContatos.getJSONObject(i);

                            Contato contato = new Contato();
                            contato.setId(contatoJson.getInt("id"));
                            contato.setNome(contatoJson.getString("nome"));
                            contato.setEmail(contatoJson.getString("email"));
                            contato.setTelefone(contatoJson.getString("telefone"));

                            listaContatos.add(contato);
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
