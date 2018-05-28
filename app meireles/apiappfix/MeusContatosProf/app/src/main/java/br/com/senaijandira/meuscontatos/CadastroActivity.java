package br.com.senaijandira.meuscontatos;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CadastroActivity extends AppCompatActivity {

    EditText txt_nome,
            txt_telefone,
            txt_email,
            txt_dt_nascimento;

    ImageView img_foto;

    Bitmap foto;

    int COD_GALERIA = 1;

    Boolean modoEdicao = false;
    Contato contato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //findViesbyIds
        txt_nome = (EditText) findViewById(R.id.txt_nome);
        txt_telefone = (EditText) findViewById(R.id.txt_telefone);
        txt_email = (EditText) findViewById(R.id.txt_email);
        txt_dt_nascimento = (EditText) findViewById(R.id.txt_dt_nascimento);

        img_foto = (ImageView) findViewById(R.id.img_foto);

        Integer idContato = getIntent().getIntExtra("idContato", 0);

        if (idContato != 0) {
            //edição
            modoEdicao = true;
            contato = ContatoDAO.getInstance().selecionarUm(this, idContato);

            txt_nome.setText(contato.getNome());
            txt_email.setText(contato.getEmail());
            txt_telefone.setText(contato.getTelefone());
            txt_dt_nascimento.setText(new SimpleDateFormat("dd/MM/yyyy")
                    .format(contato.getDtNasc()));

            if (contato.getFoto() != null) {
                img_foto.setImageBitmap(contato.getFoto());
            }

        }
    }


    public void salvar(View v) {

        //verificar se o nome não esta vazio
        if (txt_nome.getText().toString().isEmpty()) {
            txt_nome.setError("Preencha o nome");
            return;
        }

        Contato c;

        if (modoEdicao) {
            c = contato;
        } else {
            c = new Contato();
        }

        c.setNome(txt_nome.getText().toString());
        c.setTelefone(txt_telefone.getText().toString());
        c.setEmail(txt_email.getText().toString());

        String dt_nascimento = txt_dt_nascimento.getText().toString();

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        try {
            //formatar a data
            Date dt = df.parse(dt_nascimento);

            //definindo a dt de nascimento
            c.setDtNasc(dt);

        } catch (Exception ex) {

            txt_dt_nascimento.setError("Preencha uma data correta.");
            return;
        }


        if (foto != null) {
            //salvando a foto
            c.setFoto(foto);
        }


        if (modoEdicao) {

            ContatoDAO.getInstance().atualizar(this, c);
            //msg de atualizado sucesso
            Toast.makeText(this, "Atualizado com sucesso",
                    Toast.LENGTH_SHORT).show();
        } else {
            //inserir no banco de dados
            ContatoDAO.getInstance().inserir(this, c);

            //msg de inserido sucesso
            Toast.makeText(this, "Inserido com sucesso",
                    Toast.LENGTH_SHORT).show();
        }

        //Analogo ao System.out
        //Log.d("Idcontato", c.getId().toString());

        //finalizar a tela
        finish();

    }


    //abrir a galeria de imagens atraves
    //da imageView
    public void abrirGaleria(View v) {

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");

        startActivityForResult(intent, COD_GALERIA);
    }


    //Aqui que volta o resultado da chamada a galeria
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == COD_GALERIA) {

            if (resultCode == Activity.RESULT_OK) {
                //selecionou alguma coisa

                try {
                    //pegando a img em binario
                    InputStream inp = getContentResolver()
                            .openInputStream(data.getData());

                    //transformando o stream em bitmap
                    foto = BitmapFactory.decodeStream(inp);

                    //colocando a fotinha
                    img_foto.setImageBitmap(foto);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        }
    }

    //Função para tranformar um bitmap em um array de bytes
    //por que no Banco é gravado um array de bytes
    private byte[] tranformarParaBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    //Função para tranformar  um array de bytes em um bitmap
    //por que quando volta do Banco precisa voltar a ser bitmap
    private Bitmap tranformarParaBitmap(byte[] img) {
        return BitmapFactory.decodeByteArray(img, 0, img.length);
    }
}
