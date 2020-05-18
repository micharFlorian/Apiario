package com.example.apiario.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apiario.R;
import com.example.apiario.componente.UsuarioBDD;
import com.example.apiario.pojos.Usuario;

import java.util.ArrayList;
import java.util.Iterator;

public class LoginActivity extends AppCompatActivity {

    private ArrayList<Usuario> listaUsuarios;
    private EditText editTextUsuario;
    private EditText editTextPassword;
    private Button buttonEntrar;
    private Button buttonRegistrarse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        editTextUsuario = (EditText) findViewById(R.id.editTextUsuarioLogin);
        editTextPassword = (EditText) findViewById(R.id.editTextPasswordLogin);
        buttonEntrar = (Button) findViewById(R.id.buttonLogin);
        buttonRegistrarse = (Button) findViewById(R.id.buttonRegister);

        UsuarioBDD usuarioBDD = new UsuarioBDD(this);
        listaUsuarios = usuarioBDD.leerUsuarios();
    }

    public void nuevoUsuario(View view) {
        Intent intent = new Intent(LoginActivity.this, RegistroActivity.class);
        startActivity(intent);
    }

    public void entrar(View view) {
        if (listaUsuarios != null) {
            Iterator itr = listaUsuarios.listIterator();
            if (!editTextUsuario.getText().toString().isEmpty() && !editTextPassword.getText().toString().isEmpty()) {
                while (itr.hasNext()) {
                    Usuario usuario = (Usuario) itr.next();
                    if (usuario.getName().equals(editTextUsuario.getText().toString())) {
                        if (usuario.getPassword().toString().equals(editTextPassword.getText().toString())) {
                            Intent intent = new Intent(LoginActivity.this, MenuPrincipalActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("usuario", usuario);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Usuario o Contrase\u0148a incorrecta", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            } else {
                Toast.makeText(getApplicationContext(), "Campos vacios", Toast.LENGTH_SHORT).show();

            }
        } else {
            Toast.makeText(getApplicationContext(), "No hay Usuarios", Toast.LENGTH_SHORT).show();
        }
    }
}
