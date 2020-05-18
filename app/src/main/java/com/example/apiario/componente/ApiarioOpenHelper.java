package com.example.apiario.componente;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ApiarioOpenHelper extends SQLiteOpenHelper {

    public ApiarioOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase apiario) {
        apiario.execSQL("create table usuario(idUsuario Integer primary key autoincrement, name text, password text, dni text, rol text, telefono text)");
        apiario.execSQL("create table apiarios(idApiario Integer primary key autoincrement, name text, idUsuario Integer)");
        apiario.execSQL("create table colmena(idColmena Integer primary key autoincrement, idApiario Integer, incidencia text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase apiario, int i, int i1) {
        apiario.execSQL("Drop Table usuario");
        apiario.execSQL("Drop Table apiario");
        apiario.execSQL("Drop Table colmena");
        onCreate(apiario);
    }
}
