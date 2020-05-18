package com.example.apiario.componente;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.apiario.pojos.Usuario;

import java.util.ArrayList;

public class UsuarioBDD {

    private SQLiteDatabase apiario;
    private ApiarioOpenHelper apiarioOpenHelper;

    public UsuarioBDD(Context context) {
        apiarioOpenHelper = new ApiarioOpenHelper(context, "apiario", null, 1);
    }

    public void openForWrite() {
        apiario = apiarioOpenHelper.getWritableDatabase();
    }

    public void close() {
        apiario.close();
    }

    public long insertarUsuario(Usuario usuario) {
        openForWrite();
        long registros = 0;
        ContentValues content = new ContentValues();
        content.put("name", usuario.getName());
        content.put("password", usuario.getPassword());
        content.put("dni", usuario.getDni());
        content.put("rol", usuario.getRol());
        content.put("telefono", usuario.getTelefono());
        registros = apiario.insert("usuario", null, content);
        close();
        return registros;
    }

    public long updateUsuario(Usuario usuario, Integer id) {
        openForWrite();
        long registros = 0;
        ContentValues content = new ContentValues();
        content.put("name", usuario.getName());
        content.put("password", usuario.getPassword());
        content.put("dni", usuario.getDni());
        content.put("rol", usuario.getRol());
        content.put("telefono", usuario.getTelefono());
        registros = apiario.update("usuario", content, "idUsuario = " + id, null);
        close();
        return registros;
    }

    public long updateUsuario(Usuario usuario, String name) {
        openForWrite();
        long registros = 0;
        ContentValues content = new ContentValues();
        content.put("name", usuario.getName());
        content.put("password", usuario.getPassword());
        content.put("dni", usuario.getDni());
        content.put("rol", usuario.getRol());
        content.put("telefono", usuario.getTelefono());
        registros = apiario.update("usuario", content, "name = '" + name + "'", null);
        close();
        return registros;
    }

    public long eliminarUsuario(Integer id) {
        openForWrite();
        long registros = 0;
        registros = apiario.delete("usuario", "idUsuario = " + id, null);
        close();
        return registros;
    }

    public long eliminarUsuario(String name) {
        openForWrite();
        long registros = 0;
        registros = apiario.delete("usuario", "name = '" + name + "'", null);
        close();
        return registros;
    }

    public Usuario leerUsuario(String name) {
        openForWrite();
        Cursor c = apiario.rawQuery("select idUsuario, name, password, dni, rol, telefono from usuario where name = '" + name + "'", null);
        return cursorToUsuario(c);
    }

    public Usuario leerUsuario(Integer id) {
        openForWrite();
        Cursor c = apiario.rawQuery("select idUsuario, name, password, dni, rol, telefono from usuario where idUsuario = " + id, null);
        return cursorToUsuario(c);
    }

    public ArrayList<Usuario> leerUsuarios() {
        openForWrite();
        Cursor c = apiario.rawQuery("select idUsuario, name, password, dni, rol, telefono from usuario", null);
        if (c.getCount() == 0) {
            c.close();
            close();
            return null;
        }
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        while (c.moveToNext()) {
            listaUsuarios.add(new Usuario(c.getInt(0), c.getString(1), c.getString(2),
                    c.getString(3), c.getString(4), c.getString(5)));
        }
        c.close();
        close();
        return listaUsuarios;
    }

    private Usuario cursorToUsuario(Cursor c) {
        if (c.getCount() == 0) {
            c.close();
            close();
            return null;
        }
        Usuario usuario = null;
        if (c.moveToFirst()) {
            usuario = new Usuario(c.getInt(0), c.getString(1), c.getString(2),
                    c.getString(3), c.getString(4), c.getString(5));
        }
        c.close();
        close();
        return usuario;
    }
}
