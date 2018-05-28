package br.com.senaijandira.meuscontatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;

public class ContatoDAO {

    ArrayList<Contato> lstContato = new ArrayList<>();
    Integer id = 0;

    private static ContatoDAO instance;

    public static ContatoDAO getInstance() {
        if (instance == null) {
            instance = new ContatoDAO();
        }
        return instance;
    }

    public boolean inserir(Context context, Contato contato) {

        //Acessando o Banco no modo escrita
        SQLiteDatabase db = new DbHelper(context).getWritableDatabase();

        //Montando os valores vara inserir no banco
        ContentValues valores = new ContentValues();
        valores.put("nome", contato.getNome());
        valores.put("email", contato.getEmail());
        valores.put("telefone", contato.getTelefone());

        //TODO:Arrumar data de anscimento e foto
        //valores.put("dt_nascimento",contato.getDtNasc());

        if (contato.getFoto() != null) {
            valores.put("foto", tranformarParaBytes(contato.getFoto()));
        }
        //Inserindo os valores na tabela (nome da tabela, coluna que recebera
        // valor nulo(caso tenha valor nulo), valores)
        Long id = db.insert("tbl_contatos", null, valores);

        if (id != -1) {
            return true;
        } else {
            return false;
        }

    }

    public ArrayList<Contato> selecionarTodos(Context context) {
        //Criando a lista que sera retornada
        ArrayList<Contato> lstContatos = new ArrayList<>();

        //Acessando o Banco no modo leitura
        SQLiteDatabase db = new DbHelper(context).getReadableDatabase();

        String sql = "select * from tbl_contatos";

        //Executando no Banco (comando e null (os where caso tenha))e retornado o resultado
        //em forma de colunas
        Cursor cursor = db.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            Contato contato = new Contato();

            //Valor dentro dos gets é o indice da coluna que retorna no cursor
            contato.setId(cursor.getInt(0));
            contato.setNome(cursor.getString(1));
            contato.setTelefone(cursor.getString(2));
            contato.setEmail(cursor.getString(3));

            //TODO: Arrumar data de nascimento e foto
            contato.setDtNasc(new Date());

            byte[] fotobanco = cursor.getBlob(5);
            if (fotobanco != null && fotobanco.length > 0) {
                contato.setFoto(tranformarParaBitmap(fotobanco));

            }
            lstContatos.add(contato);
        }

        return lstContatos;
    }

    public Contato selecionarUm(Context context, int id) {
        SQLiteDatabase db = new DbHelper(context).getReadableDatabase();
        String sql = "select * from tbl_contatos where _id = " + id;

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            Contato contato = new Contato();
            contato.setId(cursor.getInt(0));
            contato.setNome(cursor.getString(1));
            contato.setTelefone(cursor.getString(2));
            contato.setEmail(cursor.getString(3));

            //TODO: Arrumar data de nascimento e foto
            contato.setDtNasc(new Date());

            byte[] fotobanco = cursor.getBlob(5);
            if (fotobanco != null && fotobanco.length > 0) {
                contato.setFoto(tranformarParaBitmap(fotobanco));

            }

            cursor.close();
            return contato;
        }
        return null;
    }

    public boolean remover(Context context, Integer id) {
        SQLiteDatabase db = new DbHelper(context).getWritableDatabase();
        db.delete("tbl_contatos", "_id = ?", new String[]{id.toString()});
        return true;
    }

    public boolean atualizar(Context context, Contato contato) {
        SQLiteDatabase db = new DbHelper(context).getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("nome", contato.getNome());
        valores.put("email", contato.getEmail());
        valores.put("telefone", contato.getTelefone());
        //TODO: Arrumar data de nascimento e foto
        //valores.put("dt_nascimento",contato.getDtNasc());

        if (contato.getFoto() != null) {
            valores.put("foto", tranformarParaBytes(contato.getFoto()));
        }

        db.update("tbl_contatos", valores, "_id = ?",
                new String[]{contato.getId().toString()});

        return true;
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
