package com.example.apiario.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apiario.R;
import com.example.apiario.componente.ApiarioBDD;
import com.example.apiario.componente.ColmenaBDD;
import com.example.apiario.componente.UsuarioBDD;
import com.example.apiario.pojos.Apiario;
import com.example.apiario.pojos.Colmena;
import com.example.apiario.pojos.Usuario;

public class NuevoActivity extends AppCompatActivity {

    private Usuario usuario = null;

    private TextView textViewNuevaColmena;
    private TextView textViewNuevoApiario;
    private EditText editTextNombreApiario;
    private EditText editTextNombreUsuario;
    private EditText editTextIncidencia;

    UsuarioBDD usuarioBDD;
    ColmenaBDD colmenaBDD;
    ApiarioBDD apiarioBDD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        colmenaBDD = new ColmenaBDD(this);
        apiarioBDD = new ApiarioBDD(this);
        usuarioBDD = new UsuarioBDD(this);

        textViewNuevaColmena = (TextView) findViewById(R.id.textViewNuevaColmena);
        textViewNuevoApiario = (TextView) findViewById(R.id.textViewNuevoApiario);
        editTextNombreApiario = (EditText) findViewById(R.id.editTextNuevoApiario);
        editTextNombreUsuario = (EditText) findViewById(R.id.editTextNuevoApiario1);
        editTextIncidencia = (EditText) findViewById(R.id.editTextNuevaColmena);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            usuario = (Usuario) bundle.getSerializable("usuario");
            switch (usuario.getRol()) {
                case "gestor":
                    textViewNuevoApiario.setVisibility(View.VISIBLE);
                    editTextNombreApiario.setVisibility(View.VISIBLE);
                    editTextNombreUsuario.setVisibility(View.VISIBLE);
                    break;
                case "apicultor":
                    textViewNuevaColmena.setVisibility(View.VISIBLE);
                    editTextNombreApiario.setVisibility(View.VISIBLE);
                    editTextIncidencia.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mi_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Intent intent = new Intent(NuevoActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void volver(View view) {
        Intent intent = new Intent(NuevoActivity.this, MenuPrincipalActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("usuario", usuario);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    public void insertar(View view) {
        switch (usuario.getRol()) {
            case "gestor":
                Usuario usu = usuarioBDD.leerUsuario(editTextNombreUsuario.getText().toString());
                if (usu != null) {
                    apiarioBDD.insertarApiario(new Apiario(editTextNombreApiario.getText().toString(),usu));
                    Intent intent = new Intent(NuevoActivity.this, MenuPrincipalActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("usuario", usuario);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "No existe el usuario", Toast.LENGTH_SHORT).show();
                }
                break;
            case "apicultor":
                Apiario apiario = apiarioBDD.leerApiario(editTextNombreApiario.getText().toString());
                if (apiario != null) {
                    colmenaBDD.insertarColmena(new Colmena(apiario, editTextIncidencia.getText().toString()));
                    Intent intent = new Intent(NuevoActivity.this, MenuPrincipalActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("usuario", usuario);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "No existe el usuario", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
