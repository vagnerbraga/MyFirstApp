package com.vagner.myapplication.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.vagner.myapplication.dao.EstruturaOpenHelper;

public class Conexao {

    public static SQLiteDatabase getInstance(Context context){

        EstruturaOpenHelper bancoDados = new EstruturaOpenHelper(context);

        return bancoDados.getWritableDatabase();
    }
}
