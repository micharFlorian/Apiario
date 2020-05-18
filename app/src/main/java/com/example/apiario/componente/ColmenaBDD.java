package com.example.apiario.componente;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.apiario.pojos.Apiario;
import com.example.apiario.pojos.Colmena;

import java.util.ArrayList;

public class ColmenaBDD {

    private SQLiteDatabase apiario;
    private ApiarioOpenHelper apiarioOpenHelper;

    public ColmenaBDD(Context context) {
        apiarioOpenHelper = new ApiarioOpenHelper(context, "apiario", null, 1);
    }

    public void openForWrite() {
        apiario = apiarioOpenHelper.getWritableDatabase();
    }

    public void close() {
        apiario.close();
    }

    public long insertarColmena(Colmena colmena) {
        openForWrite();
        long registros = 0;
        ContentValues content = new ContentValues();
        content.put("idApiario", colmena.getApiario().getIdApiario());
        content.put("incidencia", colmena.getIncidencia());
        registros = apiario.insert("colmena", null, content);
        close();
        return registros;
    }

    public long updateColmena(Colmena colmena, Integer id) {
        openForWrite();
        long registros = 0;
        ContentValues content = new ContentValues();
        content.put("idApiario", colmena.getApiario().getIdApiario());
        content.put("incidencia", colmena.getIncidencia());
        registros = apiario.update("colmena", content, "idColmena = " + id, null);
        close();
        return registros;
    }

    public long eliminarColmena(Integer id) {
        openForWrite();
        long registros = 0;
        registros = apiario.delete("colmena", "idColmena = " + id, null);
        close();
        return registros;
    }

    public Colmena leerColmena(Integer id) {
        openForWrite();
        Cursor c = apiario.rawQuery("select idColmena, idApiario, incidencia from colmena where idColmena = " + id, null);
        return cursorToColmena(c);
    }

    public ArrayList<Colmena> leerColmenas() {
        openForWrite();
        Cursor c = apiario.rawQuery("select idColmena, idApiario, incidencia from colmena", null);
        if (c.getCount() == 0) {
            c.close();
            close();
            return null;
        }
        ArrayList<Colmena> listaColmenas = new ArrayList<>();
        while (c.moveToNext()) {
            listaColmenas.add(new Colmena(c.getInt(0), new Apiario(c.getInt(1)), c.getString(2)));
        }
        c.close();
        close();
        return listaColmenas;
    }

    private Colmena cursorToColmena(Cursor c) {
        if (c.getCount() == 0) {
            c.close();
            close();
            return null;
        }
        Colmena colmena = null;
        if (c.moveToFirst()) {
            colmena = new Colmena(c.getInt(0), new Apiario(c.getInt(1)), c.getString(2));
        }
        c.close();
        close();
        return colmena;
    }
}
