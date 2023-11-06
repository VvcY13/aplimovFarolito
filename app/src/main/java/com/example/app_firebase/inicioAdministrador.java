package com.example.app_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app_firebase.Entidades.Administrador;
import com.example.app_firebase.Entidades.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class inicioAdministrador extends AppCompatActivity {
    private Button btnloginadministrador;
    private EditText txtusuarioAdmin;
    private EditText txtcontraseñaAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_administrador);
        btnloginadministrador = findViewById(R.id.logearseAdministrador);
        txtusuarioAdmin = findViewById(R.id.adminLoginAdministrador);
        txtcontraseñaAdmin = findViewById(R.id.contraseñaLoginAdministrador);

        btnloginadministrador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtusuarioAdmin.getText().toString().trim().isEmpty()){
                    Toast.makeText(inicioAdministrador.this, "Rellenar el campo Usuario", Toast.LENGTH_SHORT).show();
                } else if (txtcontraseñaAdmin.getText().toString().trim().isEmpty()) {
                    Toast.makeText(inicioAdministrador.this, "Rellenar el campo contraseña", Toast.LENGTH_SHORT).show();
                }else {
                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference dbref = db.getReference("Administrador");

                    String usuario = txtusuarioAdmin.getText().toString();
                    String contraseñatxt = txtcontraseñaAdmin.getText().toString();

                    dbref.orderByChild("usuario").equalTo(usuario).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                for (DataSnapshot Snapshot : snapshot.getChildren() ){
                                    Administrador admin = Snapshot.getValue(Administrador.class);
                                    if(admin.getContraseña().equals(contraseñatxt)){
                                        Toast.makeText(inicioAdministrador.this, "Inicio exitoso", Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                }
                                Toast.makeText(inicioAdministrador.this, "Contraseña Erronea", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(inicioAdministrador.this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            }
        });


    }
}