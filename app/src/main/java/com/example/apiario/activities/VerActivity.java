package com.example.apiario.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.apiario.R;
import com.example.apiario.componente.ApiarioBDD;
import com.example.apiario.componente.ColmenaBDD;
import com.example.apiario.componente.UsuarioBDD;
import com.example.apiario.pojos.AdaptadorApiario;
import com.example.apiario.pojos.AdaptadorColmena;
import com.example.apiario.pojos.AdaptadorUsuario;
import com.example.apiario.pojos.Apiario;
import com.example.apiario.pojos.Colmena;
import com.example.apiario.pojos.Usuario;

import java.util.ArrayList;

public class VerActivity extends AppCompatActivity {

    private Usuario usuario = null;

    private TextView textViewVerUsuarios;
    private RecyclerView miRecycler;
    private ListView miListView;
    private TextView textViewVerApiarios;
    private TextView textViewVerColmenas;

    private AdaptadorUsuario adaptadorUsuario;
    private ArrayList<Usuario> listaUsuarios;
    private UsuarioBDD usuarioBDD;
    private AdaptadorColmena adaptadorColmena;
    private ArrayList<Colmena> listaColmenas;
    private ColmenaBDD colmenaBDD;
    private AdaptadorApiario adaptadorApiario;
    private ArrayList<Apiario> listaApiarios;
    private ApiarioBDD apiarioBDD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            usuario = (Usuario) bundle.getSerializable("usuario");
        }

        usuarioBDD = new UsuarioBDD(this);
        colmenaBDD = new ColmenaBDD(this);
        apiarioBDD = new ApiarioBDD(this);


        listaUsuarios = usuarioBDD.leerUsuarios();
        listaColmenas = colmenaBDD.leerColmenas();
        listaApiarios = apiarioBDD.leerApiarios();

        miRecycler = (RecyclerView) findViewById(R.id.miRecyclerVistaUsuarios);
        miListView = (ListView) findViewById(R.id.listView);
        textViewVerUsuarios = (TextView) findViewById(R.id.textViewVerUsuarios);
        textViewVerColmenas = (TextView) findViewById(R.id.textViewVerColmenas);
        textViewVerApiarios = (TextView) findViewById(R.id.textViewVerApiarios);

        miRecycler.setLayoutManager(new LinearLayoutManager(this));

        adaptadorUsuario = new AdaptadorUsuario(listaUsuarios);
        adaptadorColmena = new AdaptadorColmena(this, listaColmenas, apiarioBDD);
        adaptadorApiario = new AdaptadorApiario(this, listaApiarios, usuarioBDD);

        if (MenuPrincipalActivity.verUsuarios && !MenuPrincipalActivity.verApiarios && !MenuPrincipalActivity.verColmenas) {
            miRecycler.setAdapter(adaptadorUsuario);
            miRecycler.setVisibility(View.VISIBLE);
            textViewVerUsuarios.setVisibility(View.VISIBLE);
        } else if (!MenuPrincipalActivity.verUsuarios && !MenuPrincipalActivity.verApiarios && MenuPrincipalActivity.verColmenas) {
            miListView.setAdapter(adaptadorColmena);
            miListView.setVisibility(View.VISIBLE);
            textViewVerColmenas.setVisibility(View.VISIBLE);
        } else if (!MenuPrincipalActivity.verUsuarios && MenuPrincipalActivity.verApiarios && !MenuPrincipalActivity.verColmenas) {
            miListView.setAdapter(adaptadorApiario);
            miListView.setVisibility(View.VISIBLE);
            textViewVerApiarios.setVisibility(View.VISIBLE);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mi_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Intent intent = new Intent(VerActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void volver(View view) {
        Intent intent = new Intent(VerActivity.this, MenuPrincipalActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("usuario", usuario);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
}
