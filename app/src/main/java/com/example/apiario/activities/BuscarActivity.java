package com.example.apiario.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apiario.R;
import com.example.apiario.componente.ApiarioBDD;
import com.example.apiario.componente.ColmenaBDD;
import com.example.apiario.componente.UsuarioBDD;
import com.example.apiario.pojos.Apiario;
import com.example.apiario.pojos.Colmena;
import com.example.apiario.pojos.TTSManager;
import com.example.apiario.pojos.Usuario;

import java.util.ArrayList;
import java.util.Iterator;

public class BuscarActivity extends AppCompatActivity {

    private Usuario usuario = null;

    private TextView textViewUsuario;
    private TextView textViewApiario;
    private TextView textViewColmena;
    private EditText editTextBuscarUsuario;
    private EditText editTextUsuario;
    private EditText editTextContra;
    private EditText editTextDni;
    private EditText editTextRol;
    private EditText editTextTelefono;
    private Button buttonVolver;
    private Button buttonBuscar;

    private ArrayList<Usuario> listaUsuarios;
    private UsuarioBDD usuarioBDD;
    private ArrayList<Apiario> listaApiarios;
    private ApiarioBDD apiarioBDD;
    private ArrayList<Colmena> listaColmenas;
    private ColmenaBDD colmenaBDD;

    private TTSManager ttsManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        textViewUsuario = (TextView) findViewById(R.id.textViewUsuario);
        textViewColmena = (TextView) findViewById(R.id.textViewColmena);
        textViewApiario = (TextView) findViewById(R.id.textViewApiario);
        editTextBuscarUsuario = (EditText) findViewById(R.id.editTextBuscarUsuario);
        editTextUsuario = (EditText) findViewById(R.id.editTextUsuario);
        editTextContra = (EditText) findViewById(R.id.editTextContrasena);
        editTextDni = (EditText) findViewById(R.id.editTextDni);
        editTextRol = (EditText) findViewById(R.id.editTextRol);
        editTextTelefono = (EditText) findViewById(R.id.editTextTelefono);
        buttonVolver = (Button) findViewById(R.id.buttonVolver);
        buttonBuscar = (Button) findViewById(R.id.buttonBuscar);

        usuarioBDD = new UsuarioBDD(this);
        apiarioBDD = new ApiarioBDD(this);
        colmenaBDD = new ColmenaBDD(this);

        listaUsuarios = usuarioBDD.leerUsuarios();
        listaApiarios = apiarioBDD.leerApiarios();
        listaColmenas = colmenaBDD.leerColmenas();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            usuario = (Usuario) bundle.getSerializable("usuario");
            switch (usuario.getRol()) {
                case "administrador":
                    textViewUsuario.setVisibility(View.VISIBLE);
                    break;
                case "gestor":
                    textViewApiario.setVisibility(View.VISIBLE);
                    break;
                case "apicultor":
                    textViewColmena.setVisibility(View.VISIBLE);
                    break;
            }

            ttsManager = new TTSManager();
            ttsManager.init(this);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mi_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Intent intent = new Intent(BuscarActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void buscar(View view) {
        switch (usuario.getRol()) {
            case "administrador":
                if (listaUsuarios != null) {
                    if (!editTextBuscarUsuario.getText().toString().isEmpty()) {
                        Usuario usu = usuarioBDD.leerUsuario(editTextBuscarUsuario.getText().toString());
                        if (usu != null) {
                            editTextUsuario.setVisibility(View.VISIBLE);
                            editTextContra.setVisibility(View.VISIBLE);
                            editTextDni.setVisibility(View.VISIBLE);
                            editTextRol.setVisibility(View.VISIBLE);
                            editTextTelefono.setVisibility(View.VISIBLE);
                            editTextUsuario.setText(usu.getName().toString());
                            editTextContra.setText(usu.getPassword().toString());
                            editTextDni.setText(usu.getDni().toString());
                            editTextRol.setText(usu.getRol().toString());
                            editTextTelefono.setText(usu.getTelefono().toString());
                            ttsManager.initQueue(editTextUsuario.getText().toString());
                        } else {
                            editTextUsuario.setVisibility(View.INVISIBLE);
                            editTextContra.setVisibility(View.INVISIBLE);
                            editTextDni.setVisibility(View.INVISIBLE);
                            editTextRol.setVisibility(View.INVISIBLE);
                            editTextTelefono.setVisibility(View.INVISIBLE);
                            Toast.makeText(getApplicationContext(), "No existe el usuario", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "El campo esta vacio", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "No hay Usuarios", Toast.LENGTH_SHORT).show();
                }
                break;
            case "gestor":
                if (listaApiarios != null) {
                    if (!editTextBuscarUsuario.getText().toString().isEmpty()) {
                        Apiario apiario = apiarioBDD.leerApiario(editTextBuscarUsuario.getText().toString());
                        if (apiario != null) {
                            Usuario usu = usuarioBDD.leerUsuario(apiario.getUsuario().getIdUsuario());
                            editTextUsuario.setVisibility(View.VISIBLE);
                            editTextContra.setVisibility(View.VISIBLE);
                            editTextUsuario.setText(apiario.getName().toString());
                            editTextContra.setText(usu.getName());
                            ttsManager.initQueue(editTextUsuario.getText().toString());
                        } else {
                            editTextUsuario.setVisibility(View.INVISIBLE);
                            editTextContra.setVisibility(View.INVISIBLE);
                            Toast.makeText(getApplicationContext(), "No existe el apiario", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "El campo esta vacio", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "No hay Apiarios", Toast.LENGTH_SHORT).show();
                }
                break;
            case "apicultor":
                if (listaColmenas != null) {
                    if (!editTextBuscarUsuario.getText().toString().isEmpty()) {
                        if (isInteger(editTextBuscarUsuario.getText().toString())) {
                            Colmena colmena = colmenaBDD.leerColmena(Integer.parseInt(editTextBuscarUsuario.getText().toString()));
                            if (colmena != null) {
                                Apiario apiario = apiarioBDD.leerApiario(colmena.getApiario().getIdApiario());
                                editTextUsuario.setVisibility(View.VISIBLE);
                                editTextContra.setVisibility(View.VISIBLE);
                                editTextTelefono.setVisibility(View.VISIBLE);
                                editTextUsuario.setText(colmena.getIdColmena().toString());
                                editTextContra.setText(apiario.getName());
                                editTextTelefono.setText(colmena.getIncidencia());
                                ttsManager.initQueue(editTextUsuario.getText().toString());
                            } else {
                                editTextUsuario.setVisibility(View.INVISIBLE);
                                editTextContra.setVisibility(View.INVISIBLE);
                                editTextTelefono.setVisibility(View.INVISIBLE);
                                Toast.makeText(getApplicationContext(), "No existe la colmena", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Debe de insertar el id de la colmena", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "El campo esta vacio", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(getApplicationContext(), "No hay Colmenas", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public void volver(View view) {
        Intent intent = new Intent(BuscarActivity.this, MenuPrincipalActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("usuario", usuario);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    private static boolean isInteger(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ttsManager.shutDown();
    }

}
