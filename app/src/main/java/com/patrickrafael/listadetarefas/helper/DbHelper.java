package com.patrickrafael.listadetarefas.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public static int VERSION = 1;
    public static String NOME_DB = "DB_Tarefas";
    public static String TABELA_TAREFAS = "tarefas";


    public DbHelper(@Nullable Context context) {
        super(context, NOME_DB, null, VERSION);
    }

    //Criar a primeira vez o bando de dados, só é chamado uma vez
    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE IF NOT EXISTS " + TABELA_TAREFAS + " (id INTEGER PRIMARY KEY AUTOINCREMENT, " + " nome TEXT NOT NULL ); ";

        try {
            db.execSQL(sql);
            Log.i("Indo DB", "sucesso ao criar a tabela");

        } catch (Exception e) {
            Log.i("Indo DB", "Erro ao criar a tabela" + e.getMessage());
        }

    }

    //Fazer alterações na tabela que já existe
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
