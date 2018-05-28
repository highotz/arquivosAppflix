package com.example.a17170132.apicontatos;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class CadastrarContato extends AppCompatActivity {
    String url;
    String retorno;

    EditText edtNome;
    EditText edtEmail;
    EditText edtTelefone;

    String nome;
    String email;
    String telefone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_contato);

    }

    public void salvarContato(View view) {
        edtNome = (EditText) findViewById(R.id.edtNome);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtTelefone = (EditText) findViewById(R.id.edtTelefone);

        nome = edtNome.getText().toString();
        email = edtEmail.getText().toString();
        telefone = edtTelefone.getText().toString();

        url = "http://10.0.2.2/sites_higor/apicontatos/inserir.php?";

        new AsyncTask<Void, Void, String>(){
            @Override
            protected String doInBackground(Void... voids) {

                HashMap<String, String> dadosPost = new HashMap<>();
                dadosPost.put("nome",nome);
                dadosPost.put("email", email);
                dadosPost.put("telefone", telefone);
                //retorno = HttpConnection.get(url);
                Log.d("retorno", retorno);
                return  HttpConnection.post(url, dadosPost);
            }

            @Override
            protected void onPostExecute(String aVoid) {
                super.onPostExecute(aVoid);

                try {
                    JSONObject retJson = new JSONObject(retorno);
                    retorno = retJson.getString("sucesso");
                    Toast.makeText(getApplicationContext(), retorno, Toast.LENGTH_LONG).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }.execute();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);


    }

}
