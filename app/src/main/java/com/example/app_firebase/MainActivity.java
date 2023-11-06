package com.example.app_firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {




    private Button gestionaradministrador;
    private Button gestionarusuario;
    private Button gestionarmesa;
    private Button gestionarproducto;
    private Button loginUsuario;
    private Button loginAdministrador;

    private Button iramesas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gestionaradministrador = findViewById(R.id.gestionarAdministrador);
        gestionarusuario = findViewById(R.id.gestionarUsuario);
        gestionarmesa = findViewById(R.id.gestionarMesa);
        gestionarproducto = findViewById(R.id.gestionarProducto);
        loginUsuario = findViewById(R.id.iniciarSesionUsuario);
        loginAdministrador = findViewById(R.id.iniciarSesionAdministrador);
        iramesas = findViewById(R.id.agregarpedidos);

       gestionaradministrador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ventanaGestionAdmin = new Intent(getApplicationContext(), gestionarAdministrador.class);
                startActivity(ventanaGestionAdmin);
            }
        });
        gestionarusuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ventanaGestionUsuario = new Intent(getApplicationContext(), gestionarUsuario.class);
                startActivity(ventanaGestionUsuario);
            }
        });
        gestionarproducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ventanaGestionProducto = new Intent(getApplicationContext(), gestionarProducto.class);
                startActivity(ventanaGestionProducto);
            }
        });
        gestionarmesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ventanaGestionMesa = new Intent(getApplicationContext(), gestionarMesa.class);
                startActivity(ventanaGestionMesa);
            }
        });
        loginUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ventanaInicioUsuario = new Intent(getApplicationContext(), inicioUsuario.class);
                startActivity(ventanaInicioUsuario);
            }
        });
        loginAdministrador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ventanaInicioAdministrador = new Intent(getApplicationContext(), inicioAdministrador.class);
                startActivity(ventanaInicioAdministrador);
            }
        });
        iramesas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ventanamesas = new Intent(getApplicationContext(),listademesas.class);
                startActivity(ventanamesas);
            }
        });
    }
}