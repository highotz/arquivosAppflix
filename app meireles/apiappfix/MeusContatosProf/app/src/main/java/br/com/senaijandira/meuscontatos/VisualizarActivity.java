package br.com.senaijandira.meuscontatos;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class VisualizarActivity extends AppCompatActivity {

    //TextView txt_id_contato;
    Integer idContato;

    TextView txt_nome, txt_telefone, txt_email, txt_dt_nascimento;
    ImageView img_contato;


    Contato contato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       // txt_id_contato = (TextView) findViewById(R.id.txt_id_contato);

        txt_nome = (TextView) findViewById(R.id.txt_nome);
        txt_telefone = (TextView) findViewById(R.id.txt_telefone);
        txt_email = (TextView) findViewById(R.id.txt_email);
        txt_dt_nascimento = (TextView) findViewById(R.id.txt_dt_nascimento);
        img_contato = (ImageView) findViewById(R.id.img_contato);

        //acessar o ID passado por Intent
        Intent intent = getIntent();
        idContato = intent.getIntExtra("idContato", 0);

       // txt_id_contato.setText("O id do contato é: "+ idContato.toString());

    }

    @Override
    protected void onResume() {
        super.onResume();

        //selecionar contato do banco
        contato = ContatoDAO.getInstance().selecionarUm(this, idContato);

        txt_nome.setText(contato.getNome());
        txt_telefone.setText(contato.getTelefone());
        txt_email.setText(contato.getEmail());


        String data_niver = new SimpleDateFormat("dd/MM/yyyy")
                .format(contato.getDtNasc());

        txt_dt_nascimento.setText( data_niver );

        if(contato.getFoto() !=null){
            img_contato.setImageBitmap(contato.getFoto());
        }

    }

    public void ligar(View v){
        Intent intent;
        intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+ contato.getTelefone()));
        startActivity(intent);

    }

    public void enviarEmail(View v){
        Intent intent;
        intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"+ contato.getEmail()));
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_visualizar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_excluir){
            //excluir
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Excluir");
            builder.setMessage("Tem certeza que deseja excluir esse contatinho?");
            final Context context = this;
            builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ContatoDAO.getInstance().remover(context, idContato);
                    finish();
                }
            });

            builder.setNegativeButton("NÃO", null);
            builder.create().show();
        }

        if(item.getItemId() == R.id.menu_editar){
            Intent intent = new Intent(this, CadastroActivity.class);
            intent.putExtra("idContato", idContato);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
