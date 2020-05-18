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

public class RegistroActivity extends AppCompatActivity {

    UsuarioBDD usuarioBDD;
    private EditText editTextUsuario;
    private EditText editTextPassword;
    private EditText editTextDni;
    private EditText editTextRol;
    private EditText editTextTelefono;
    private Button buttonRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        usuarioBDD = new UsuarioBDD(this);
        editTextUsuario = (EditText) findViewById(R.id.editTextUsuarioRegistro);
        editTextPassword = (EditText) findViewById(R.id.editTextPasswordRegistro);
        editTextDni = (EditText) findViewById(R.id.editTextDniRegistro);
        editTextRol = (EditText) findViewById(R.id.editTextRolRegistro);
        editTextTelefono = (EditText) findViewById(R.id.editTextTelefonoRegistro);
        buttonRegistrar = (Button) findViewById(R.id.buttonRegistrarUsuario);
    }

    public void registrarUsuario(View view) {

        if (!editTextUsuario.getText().toString().isEmpty() && !editTextPassword.getText().toString().isEmpty()
                && !editTextDni.getText().toString().isEmpty() && !editTextRol.getText().toString().isEmpty()
                && !editTextTelefono.getText().toString().isEmpty()) {

            Usuario usuario = new Usuario(editTextUsuario.getText().toString(), editTextPassword.getText().toString(),
                    editTextDni.getText().toString(), editTextRol.getText().toString(), editTextTelefono.getText().toString());
            usuarioBDD.insertarUsuario(usuario);
            Intent intent = new Intent(RegistroActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Deben de completarse los campos", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void cancelar(View view) {
        Intent intent = new Intent(RegistroActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
