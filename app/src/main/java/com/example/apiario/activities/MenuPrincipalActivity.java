package com.example.apiario.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.apiario.R;
import com.example.apiario.componente.UsuarioBDD;
import com.example.apiario.pojos.Usuario;

public class MenuPrincipalActivity extends AppCompatActivity {

    public static boolean verUsuarios = false;
    public static boolean verColmenas = false;
    public static boolean verApiarios = true;

    private UsuarioBDD usuarioBDD;
    private Usuario usuario = null;

    private TextView textViewAdministrador;
    private TextView textViewGestor;
    private TextView textViewApicultor;
    private Button buttonBuscarUsuarioAdministrador;
    private Button buttonModificarUsuarioGestor;
    private Button buttonVerUsuariosAdministrador;
    private Button buttonVerDatosUsuarioGestor;
    private Button buttonEliminarUsuarioAdministrador;
    private Button buttonModificarUsuarioAdministrador;
    private Button buttonCrearApiarioGestor;
    private Button buttonVerApiariosGestor;
    private Button buttonVerApiariosAdministrador;
    private Button buttonVerColmenasAdministrador;
    private Button buttonVerUsuarioApicultor;
    private Button buttonRegistrarIncidencia;
    private Button buttonVerColmenaApicultor;
    private Button buttonVerColmenasApicultor;
    private Button buttonCrearColmenaApicultor;
    private Button buttonEliminarApiarioGestor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuprincipal);

        textViewAdministrador = (TextView) findViewById(R.id.textViewAdministrador);
        textViewGestor = (TextView) findViewById(R.id.textViewGestor);
        textViewApicultor = (TextView) findViewById(R.id.textViewApicultor);
        buttonBuscarUsuarioAdministrador = (Button) findViewById(R.id.buttonBuscarUsuarioAdministrador);
        buttonModificarUsuarioGestor = (Button) findViewById(R.id.buttonModificarUsuarioGestor);
        buttonVerUsuariosAdministrador = (Button) findViewById(R.id.buttonVerUsuariosAdministrador);
        buttonVerDatosUsuarioGestor = (Button) findViewById(R.id.buttonVerDatosUsuarioGestor);
        buttonEliminarUsuarioAdministrador = (Button) findViewById(R.id.buttonEliminarUsuarioAdministrador);
        buttonCrearApiarioGestor = (Button) findViewById(R.id.buttonCrearApiarioGestor);
        buttonVerApiariosGestor = (Button) findViewById(R.id.buttonVerApiariosGestor);
        buttonVerApiariosAdministrador = (Button) findViewById(R.id.buttonVerApiariosAdministrador);
        buttonVerColmenasAdministrador = (Button) findViewById(R.id.buttonVerColmenasAdministrador);
        buttonModificarUsuarioAdministrador = (Button) findViewById(R.id.buttonModificarUsuarioAdministrador);
        buttonVerUsuarioApicultor = (Button) findViewById(R.id.buttonVerUsuarioApicultor);
        buttonCrearColmenaApicultor = (Button) findViewById(R.id.buttonCrearColmenaApicultor);
        buttonVerColmenaApicultor = (Button) findViewById(R.id.buttonVerColmenaApicultor);
        buttonVerColmenasApicultor = (Button) findViewById(R.id.buttonVerColmenasApicultor);
        buttonRegistrarIncidencia = (Button) findViewById(R.id.buttonRegistrarIncidenciaApicultor);
        buttonEliminarApiarioGestor = (Button) findViewById(R.id.buttonEliminarApiarioGestor);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            usuario = (Usuario) bundle.getSerializable("usuario");
            switch (usuario.getRol()) {
                case "administrador":
                    textViewAdministrador.setVisibility(View.VISIBLE);
                    buttonVerColmenasAdministrador.setVisibility(View.VISIBLE);
                    buttonEliminarUsuarioAdministrador.setVisibility(View.VISIBLE);
                    buttonVerUsuariosAdministrador.setVisibility(View.VISIBLE);
                    buttonBuscarUsuarioAdministrador.setVisibility(View.VISIBLE);
                    buttonVerApiariosAdministrador.setVisibility(View.VISIBLE);
                    buttonModificarUsuarioAdministrador.setVisibility(View.VISIBLE);
                    break;
                case "gestor":
                    textViewGestor.setVisibility(View.VISIBLE);
                    buttonCrearApiarioGestor.setVisibility(View.VISIBLE);
                    buttonVerDatosUsuarioGestor.setVisibility(View.VISIBLE);
                    buttonModificarUsuarioGestor.setVisibility(View.VISIBLE);
                    buttonVerApiariosGestor.setVisibility(View.VISIBLE);
                    buttonEliminarApiarioGestor.setVisibility(View.VISIBLE);
                    break;
                case "apicultor":
                    textViewApicultor.setVisibility(View.VISIBLE);
                    buttonVerColmenasApicultor.setVisibility(View.VISIBLE);
                    buttonRegistrarIncidencia.setVisibility(View.VISIBLE);
                    buttonVerColmenaApicultor.setVisibility(View.VISIBLE);
                    buttonVerUsuarioApicultor.setVisibility(View.VISIBLE);
                    buttonCrearColmenaApicultor.setVisibility(View.VISIBLE);
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
                Intent intent = new Intent(MenuPrincipalActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void buscar(View view) {
        Intent intent = new Intent(MenuPrincipalActivity.this, BuscarActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("usuario", usuario);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    public void verUsuarios(View view) {
        verUsuarios = true;
        verColmenas = false;
        verApiarios = false;
        Intent intent = new Intent(MenuPrincipalActivity.this, VerActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("usuario", usuario);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    public void verApiarios(View view) {
        verUsuarios = false;
        verColmenas = false;
        verApiarios = true;
        Intent intent = new Intent(MenuPrincipalActivity.this, VerActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("usuario", usuario);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    public void verColmenas(View view) {
        verUsuarios = false;
        verColmenas = true;
        verApiarios = false;
        Intent intent = new Intent(MenuPrincipalActivity.this, VerActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("usuario", usuario);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    public void eliminarUsuario(View view) {
        Intent intent = new Intent(MenuPrincipalActivity.this, EliminarActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("usuario", usuario);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    public void modificarUsuario(View view) {
        Intent intent = new Intent(MenuPrincipalActivity.this, ModificarActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("usuario", usuario);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    public void nuevo(View view) {
        Intent intent = new Intent(MenuPrincipalActivity.this, NuevoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("usuario", usuario);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
}
