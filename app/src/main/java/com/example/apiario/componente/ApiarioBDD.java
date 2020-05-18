package com.example.apiario.componente;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.apiario.pojos.Apiario;
import com.example.apiario.pojos.Usuario;

import java.util.ArrayList;

public class ApiarioBDD {

    private SQLiteDatabase apiario;
    private ApiarioOpenHelper apiarioOpenHelper;

    public ApiarioBDD(Context context) {
        apiarioOpenHelper = new ApiarioOpenHelper(context, "apiario", null, 1);
    }

    public void openForWrite() {
        apiario = apiarioOpenHelper.getWritableDatabase();
    }

    public void close() {
        apiario.close();
    }

    public long insertarApiario(Apiario api) {
        openForWrite();
        long registros = 0;
        ContentValues content = new ContentValues();
        content.put("name", api.getName());
        content.put("idUsuario", api.getUsuario().getIdUsuario());
        registros = apiario.insert("apiarios", null, content);
        close();
        return registros;
    }

    public long updateApiario(Apiario api, Integer id) {
        openForWrite();
        long registros = 0;
        ContentValues content = new ContentValues();
        content.put("name", api.getName());
        content.put("idUsuario", api.getUsuario().getIdUsuario());
        registros = apiario.update("apiarios", content, "idApiario = " + id, null);
        close();
        return registros;
    }

    public long updateApiario(Apiario api, String name) {
        openForWrite();
        long registros = 0;
        ContentValues content = new ContentValues();
        content.put("name", api.getName());
        content.put("idUsuario", api.getUsuario().getIdUsuario());
        registros = apiario.update("apiarios", content, "name = '" + name + "'", null);
        close();
        return registros;
    }

    public long eliminarApiario(Integer id) {
        openForWrite();
        long registros = 0;
        registros = apiario.delete("apiarios", "idApiario = " + id, null);
        close();
        return registros;
    }

    public long eliminarApiario(String name) {
        openForWrite();
        long registros = 0;
        registros = apiario.delete("apiarios", "name = '" + name + "'", null);
        close();
        return registros;
    }

    public Apiario leerApiario(Integer id) {
        openForWrite();
        Cursor c = apiario.rawQuery("select idApiario, name, idUsuario from apiarios where idApiario = " + id, null);
        return cursorToApiario(c);
    }

    public Apiario leerApiario(String name) {
        openForWrite();
        Cursor c = apiario.rawQuery("select idApiario, name, idUsuario from apiarios where name = '" + name + "'", null);
        return cursorToApiario(c);
    }

    public ArrayList<Apiario> leerApiarios() {
        openForWrite();
        Cursor c = apiario.rawQuery("select idApiario, name, idUsuario from apiarios", null);
        if (c.getCount() == 0) {
            c.close();
            close();
            return null;
        }
        ArrayList<Apiario> listaApiarios = new ArrayList<>();
        while (c.moveToNext()) {
            listaApiarios.add(new Apiario(c.getInt(0), c.getString(1), new Usuario(c.getInt(2))));
        }
        c.close();
        close();
        return listaApiarios;
    }

    private Apiario cursorToApiario(Cursor c) {
        if (c.getCount() == 0) {
            c.close();
            close();
            return null;
        }
        Apiario api = null;
        if (c.moveToFirst()) {
            api = new Apiario(c.getInt(0), c.getString(1), new Usuario(c.getInt(2)));
        }
        c.close();
        close();
        return api;
    }
}
