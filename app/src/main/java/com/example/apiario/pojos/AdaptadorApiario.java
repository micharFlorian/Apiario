package com.example.apiario.pojos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.apiario.R;
import com.example.apiario.componente.UsuarioBDD;

import java.util.ArrayList;

public class AdaptadorApiario extends BaseAdapter {

    private Context context;
    ArrayList<Apiario> miLista;
    UsuarioBDD usuarioBDD;

    public AdaptadorApiario(Context context, ArrayList<Apiario> miLista, UsuarioBDD usuarioBDD) {
        this.context = context;
        this.miLista = miLista;
        this.usuarioBDD = usuarioBDD;
    }

    @Override
    public int getCount() {
        if (miLista != null) return miLista.size();
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return miLista.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Apiario apiario = (Apiario)getItem(i);

        view = LayoutInflater.from(context).inflate(R.layout.item_apiarios, null);
        TextView textViewIdApiario = (TextView) view.findViewById(R.id.idApiario);
        TextView textViewNameApiario = (TextView) view.findViewById(R.id.idNameApiario);
        TextView textViewUsuario = (TextView) view.findViewById(R.id.idUsuario);

        textViewIdApiario.setText(apiario.getIdApiario().toString());
        Usuario usuario = usuarioBDD.leerUsuario(apiario.getUsuario().getIdUsuario());
        if(usuario != null){
            textViewUsuario.setText(usuario.getName());
        }else{
            textViewUsuario.setText(apiario.getUsuario().getIdUsuario().toString());
        }
        textViewNameApiario.setText(apiario.getName());
        return view;
    }
}
