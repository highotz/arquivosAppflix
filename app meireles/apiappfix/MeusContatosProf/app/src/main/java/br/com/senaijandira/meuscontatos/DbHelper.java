package br.com.senaijandira.meuscontatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper{

    //Nome do banco
    private static String DB_NAME = "contatos.db";

    //Versão do Banco
    private static int  DB_VERSION = 1;

    //Construtor da Classe para criação do Banco
    public DbHelper(Context ctx){
        super(ctx, DB_NAME, null, DB_VERSION);
    }

    //Criação do Banco, é igual criar tabelas ou qualquer estrutura inicial
    @Override
    public void onCreate(SQLiteDatabase db){
        String sql = "CREATE TABLE tbl_contatos( " +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT," +
                "email TEXT," +
                "telefone TEXT," +
                "dt_nascimento INTEGER," +
                "foto BLOOB);";
        db.execSQL(sql);
    }

    //Upgrade no Banco
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table tbl_contatos;");
        onCreate(db);
    }

}
