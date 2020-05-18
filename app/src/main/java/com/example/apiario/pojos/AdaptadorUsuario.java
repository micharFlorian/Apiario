package com.example.apiario.pojos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.apiario.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdaptadorUsuario extends RecyclerView.Adapter<AdaptadorUsuario.ViewHolderDatos> implements View.OnClickListener {

    private View.OnClickListener escuchador;
    ArrayList<Usuario> miLista;

    public AdaptadorUsuario(ArrayList<Usuario> miLista) {
        this.miLista = miLista;
    }

    @Override
    public void onClick(View v) {
        if (escuchador != null) escuchador.onClick(v);
    }

    @NonNull
    @Override
    public AdaptadorUsuario.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View laVista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_usuarios, null, false);
        laVista.setOnClickListener(this);
        return new ViewHolderDatos(laVista);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorUsuario.ViewHolderDatos holder, int position) {
        holder.asignarDatos(miLista.get(position));
    }

    @Override
    public int getItemCount() {
        if (miLista != null) return miLista.size();
        return 0;
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView textViewUsuario;
        TextView textViewDni;
        TextView textViewRol;
        TextView textViewTelefono;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);

            textViewUsuario = (TextView) itemView.findViewById(R.id.idUsuario);
            textViewDni = (TextView) itemView.findViewById(R.id.idDni);
            textViewRol = (TextView) itemView.findViewById(R.id.idRol);
            textViewTelefono = (TextView) itemView.findViewById(R.id.idTelefono);
        }

        public void asignarDatos(Usuario usuario) {
            textViewUsuario.setText(usuario.getName());
            textViewDni.setText(usuario.getDni());
            textViewRol.setText(usuario.getRol());
            textViewTelefono.setText(usuario.getTelefono());
        }
    }

}
