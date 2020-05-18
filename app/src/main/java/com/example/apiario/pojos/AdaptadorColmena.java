package com.example.apiario.pojos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.apiario.R;
import com.example.apiario.componente.ApiarioBDD;

import java.util.ArrayList;


public class AdaptadorColmena extends BaseAdapter {

    private Context context;
    ArrayList<Colmena> miLista;
    ApiarioBDD apiarioBDD;

    public AdaptadorColmena(Context context, ArrayList<Colmena> miLista, ApiarioBDD apiarioBDD) {
        this.context = context;
        this.miLista = miLista;
        this.apiarioBDD = apiarioBDD;
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
        Colmena colmena = (Colmena) getItem(i);

        view = LayoutInflater.from(context).inflate(R.layout.item_colmenas, null);
        TextView textViewIdColmena = (TextView) view.findViewById(R.id.idColmena);
        TextView textViewApiario = (TextView) view.findViewById(R.id.idApiario);
        TextView textViewIncidencia = (TextView) view.findViewById(R.id.idIncidencia);

        textViewIdColmena.setText(colmena.getIdColmena().toString());
        Apiario apiario = apiarioBDD.leerApiario(colmena.getApiario().getIdApiario());
        if (apiario != null) {
            textViewApiario.setText(apiario.getName());
        } else {
            textViewApiario.setText(colmena.getApiario().getIdApiario().toString());
        }
        textViewIncidencia.setText(colmena.getIncidencia());
        return view;
    }
}
