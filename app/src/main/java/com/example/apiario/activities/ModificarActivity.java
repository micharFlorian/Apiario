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
import com.example.apiario.pojos.Usuario;

import java.util.ArrayList;

public class ModificarActivity extends AppCompatActivity {

    private Usuario usuario = null;

    private TextView textViewModificarUsuario;
    private TextView textViewModificarColmena;
    private TextView textViewModificarApiario;
    private EditText editTextBuscarUsuario;
    private EditText editTextUsuario;
    private EditText editTextContra;
    private EditText editTextDni;
    private EditText editTextRol;
    private EditText editTextTelefono;
    private Button buttonModificar;

    private UsuarioBDD usuarioBDD;
    private ColmenaBDD colmenaBDD;
    private ApiarioBDD apiarioBDD;

    private ArrayList<Usuario> listaUsuarios;
    private ArrayList<Apiario> listaApiarios;
    private ArrayList<Colmena> listaColmenas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        textViewModificarUsuario = (TextView) findViewById(R.id.textViewUsuarioModificar);
        textViewModificarApiario = (TextView) findViewById(R.id.textViewApiarioModificar);
        textViewModificarColmena = (TextView) findViewById(R.id.textViewColmenaModificar);
        editTextBuscarUsuario = (EditText) findViewById(R.id.editTextBuscarUsuarioModificar);
        editTextUsuario = (EditText) findViewById(R.id.editTextUsuarioModificar);
        editTextContra = (EditText) findViewById(R.id.editTextContrasenaModificar);
        editTextDni = (EditText) findViewById(R.id.editTextDniModificar);
        editTextRol = (EditText) findViewById(R.id.editTextRolModificar);
        editTextTelefono = (EditText) findViewById(R.id.editTextTelefonoModificar);
        buttonModificar = (Button) findViewById(R.id.buttonModificar);

        usuarioBDD = new UsuarioBDD(this);
        colmenaBDD = new ColmenaBDD(this);
        apiarioBDD = new ApiarioBDD(this);

        listaUsuarios = usuarioBDD.leerUsuarios();
        listaApiarios = apiarioBDD.leerApiarios();
        listaColmenas = colmenaBDD.leerColmenas();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            usuario = (Usuario) bundle.getSerializable("usuario");
            switch (usuario.getRol()) {
                case "administrador":
                    textViewModificarUsuario.setVisibility(View.VISIBLE);
                    break;
                case "gestor":
                    textViewModificarApiario.setVisibility(View.VISIBLE);
                    break;
                case "apicultor":
                    textViewModificarColmena.setVisibility(View.VISIBLE);
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
                Intent intent = new Intent(ModificarActivity.this, LoginActivity.class);
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
                            buttonModificar.setVisibility(View.VISIBLE);
                            editTextUsuario.setText(usu.getName().toString());
                            editTextContra.setText(usu.getPassword().toString());
                            editTextDni.setText(usu.getDni().toString());
                            editTextRol.setText(usu.getRol().toString());
                            editTextTelefono.setText(usu.getTelefono().toString());
                        } else {
                            editTextUsuario.setVisibility(View.INVISIBLE);
                            editTextContra.setVisibility(View.INVISIBLE);
                            editTextDni.setVisibility(View.INVISIBLE);
                            editTextRol.setVisibility(View.INVISIBLE);
                            editTextTelefono.setVisibility(View.INVISIBLE);
                            buttonModificar.setVisibility(View.INVISIBLE);
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
                            if (usu != null) {
                                editTextUsuario.setVisibility(View.VISIBLE);
                                editTextContra.setVisibility(View.VISIBLE);
                                editTextUsuario.setText(apiario.getName().toString());
                                editTextContra.setText(usu.getName());
                                buttonModificar.setVisibility(View.VISIBLE);
                            } else {
                                editTextUsuario.setVisibility(View.VISIBLE);
                                editTextContra.setVisibility(View.VISIBLE);
                                editTextUsuario.setText(apiario.getName().toString());
                                editTextContra.setText(apiario.getUsuario().getIdUsuario().toString());
                                buttonModificar.setVisibility(View.VISIBLE);
                            }

                        } else {
                            editTextUsuario.setVisibility(View.INVISIBLE);
                            editTextContra.setVisibility(View.INVISIBLE);
                            buttonModificar.setVisibility(View.INVISIBLE);
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
                                if (apiario != null) {
                                    editTextUsuario.setVisibility(View.VISIBLE);
                                    editTextContra.setVisibility(View.VISIBLE);
                                    editTextTelefono.setVisibility(View.VISIBLE);
                                    editTextUsuario.setText(colmena.getIdColmena().toString());
                                    editTextContra.setText(apiario.getName());
                                    editTextTelefono.setText(colmena.getIncidencia());
                                    buttonModificar.setVisibility(View.VISIBLE);
                                } else {
                                    editTextUsuario.setVisibility(View.VISIBLE);
                                    editTextContra.setVisibility(View.VISIBLE);
                                    editTextTelefono.setVisibility(View.VISIBLE);
                                    editTextUsuario.setText(colmena.getIdColmena().toString());
                                    editTextContra.setText(colmena.getApiario().getIdApiario().toString());
                                    editTextTelefono.setText(colmena.getIncidencia());
                                    buttonModificar.setVisibility(View.VISIBLE);
                                }
                            } else {
                                editTextUsuario.setVisibility(View.INVISIBLE);
                                editTextContra.setVisibility(View.INVISIBLE);
                                editTextTelefono.setVisibility(View.INVISIBLE);
                                buttonModificar.setVisibility(View.INVISIBLE);
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
        Intent intent = new Intent(ModificarActivity.this, MenuPrincipalActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("usuario", usuario);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    public void modificar(View view) {
        switch (usuario.getRol()) {
            case "administrador":
                editTextUsuario.setVisibility(View.INVISIBLE);
                editTextContra.setVisibility(View.INVISIBLE);
                editTextDni.setVisibility(View.INVISIBLE);
                editTextRol.setVisibility(View.INVISIBLE);
                editTextTelefono.setVisibility(View.INVISIBLE);
                buttonModificar.setVisibility(View.INVISIBLE);
                usuarioBDD.updateUsuario(new Usuario(editTextUsuario.getText().toString(), editTextContra.getText().toString(),
                                editTextDni.getText().toString(), editTextRol.getText().toString(), editTextTelefono.getText().toString()),
                        editTextBuscarUsuario.getText().toString());
                Toast.makeText(getApplicationContext(), "Usuario modificado", Toast.LENGTH_SHORT).show();
                break;
            case "gestor":
                if (isInteger(editTextContra.getText().toString())) {
                    Usuario usu = usuarioBDD.leerUsuario(Integer.parseInt(editTextContra.getText().toString()));
                    if (usu != null) {
                        editTextUsuario.setVisibility(View.INVISIBLE);
                        editTextContra.setVisibility(View.INVISIBLE);
                        buttonModificar.setVisibility(View.INVISIBLE);
                        apiarioBDD.updateApiario(new Apiario(editTextUsuario.getText().toString(),
                                usu), editTextBuscarUsuario.getText().toString());
                        Toast.makeText(getApplicationContext(), "Apiario modificado", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "El usuario no existe", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Usuario usu = usuarioBDD.leerUsuario(editTextContra.getText().toString());
                    if (usu != null) {
                        editTextUsuario.setVisibility(View.INVISIBLE);
                        editTextContra.setVisibility(View.INVISIBLE);
                        buttonModificar.setVisibility(View.INVISIBLE);
                        apiarioBDD.updateApiario(new Apiario(editTextUsuario.getText().toString(),
                                usu), editTextBuscarUsuario.getText().toString());
                        Toast.makeText(getApplicationContext(), "Apiario modificado", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "El usuario no existe", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case "apicultor":
                if (isInteger(editTextContra.getText().toString())) {
                    Apiario apiario = apiarioBDD.leerApiario(Integer.parseInt(editTextContra.getText().toString()));
                    if (apiario != null) {
                        editTextUsuario.setVisibility(View.INVISIBLE);
                        editTextContra.setVisibility(View.INVISIBLE);
                        editTextTelefono.setVisibility(View.INVISIBLE);
                        buttonModificar.setVisibility(View.INVISIBLE);
                        colmenaBDD.updateColmena(new Colmena(apiario, editTextTelefono.getText().toString()),
                                Integer.parseInt(editTextBuscarUsuario.getText().toString()));
                        Toast.makeText(getApplicationContext(), "Colmena modificada", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "La apiario no existe", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Apiario apiario = apiarioBDD.leerApiario(editTextContra.getText().toString());
                    if (apiario != null) {
                        editTextUsuario.setVisibility(View.INVISIBLE);
                        editTextContra.setVisibility(View.INVISIBLE);
                        editTextTelefono.setVisibility(View.INVISIBLE);
                        buttonModificar.setVisibility(View.INVISIBLE);
                        colmenaBDD.updateColmena(new Colmena(apiario, editTextTelefono.getText().toString()),
                                Integer.parseInt(editTextBuscarUsuario.getText().toString()));
                        Toast.makeText(getApplicationContext(), "Colmena modificada", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "La apiario no existe", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    private static boolean isInteger(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
