package br.com.senaijandira.meuscontatos;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener {


    ListView list_view_contatos;
    ContatoAdapter adapter;
    ContatoDAO dao ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Pegando a instancia do DAO
        dao = ContatoDAO.getInstance();

        //cadastros fake
        //cadastrosFake();

        //findviews
        list_view_contatos = (ListView)
                findViewById(R.id.list_view_contatos);


        //criando o adaptador vazio
        adapter = new ContatoAdapter(this, new ArrayList<Contato>());

        //plugando(conectando) o adaptador na lista
        list_view_contatos.setAdapter(adapter);

        //Click do botão flutuante
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();


                //Abrir tela de cadastro
                Intent intent =
                        new Intent(getApplicationContext(),
                                CadastroActivity.class );

                startActivity(intent);


            }
        });


        //listenner de click da lista
        list_view_contatos.setOnItemClickListener(this);

    }


    @Override
    protected void onResume() {
        super.onResume();

        //Pegando os contatos do banco
        ArrayList<Contato> contatosCadastrados;
        contatosCadastrados = dao.selecionarTodos(this);

        //limpar o conteudo
        adapter.clear();

        //preenchendo o adaptador
        adapter.addAll(contatosCadastrados);
    }


    //tratar o click da ListView
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view,
                            int i, long l) {

        //pegando o contato da posição i da lista
        Contato item = adapter.getItem(i);

        //criando o objeto de intenção
        Intent intent = new Intent(this,
                VisualizarActivity.class);

        //pasando o id do contato
        intent.putExtra("idContato", item.getId());

        //abrindo a tela de visualizar
        startActivity(intent);

        //Toast.makeText(this, "Cliquei na lista",
        //        Toast.LENGTH_SHORT).show();

    }
}
