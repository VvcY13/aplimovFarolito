package com.example.app_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_firebase.Entidades.Administrador;
import com.example.app_firebase.Entidades.Mesa;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class mesa extends AppCompatActivity {
    private EditText idmesa,ubicacionmesa,capacidadmesa;

    private Button registrarMesa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesa);
        idmesa = findViewById(R.id.txtmesaid);
        ubicacionmesa = findViewById(R.id.txtubicacion);
        capacidadmesa = findViewById(R.id.txtcapacidad);

        registrarMesa = findViewById(R.id.registrarMesa);

        btnRegistrarMesa();

    }
    public void btnRegistrarMesa(){
        registrarMesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(idmesa.getText().toString().trim().isEmpty()){
                    Toast.makeText(mesa.this, "Rellenar el campo ID", Toast.LENGTH_SHORT).show();
                } else if (ubicacionmesa.getText().toString().trim().isEmpty()) {
                    Toast.makeText(mesa.this, "Rellenar el campo Ubicacion", Toast.LENGTH_SHORT).show();
                } else if (capacidadmesa.getText().toString().trim().isEmpty()) {
                    Toast.makeText(mesa.this, "Rellenar el campo Capacidad", Toast.LENGTH_SHORT).show();
                }else {
                    int IDmesa = Integer.parseInt(idmesa.getText().toString());
                    String ubimesa = ubicacionmesa.getText().toString();
                    int capacimesa = Integer.parseInt(capacidadmesa.getText().toString());


                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference dbref = db.getReference(Mesa.class.getSimpleName());

                    dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            boolean idRepetido = false;
                            String aux = Integer.toString(IDmesa);
                            for (DataSnapshot mesax : snapshot.getChildren()) {
                                if (mesax.child("idMesa").getValue().toString().equalsIgnoreCase(aux)) {
                                    idRepetido = true;
                                    Toast.makeText(mesa.this, "Error, El id " + aux + " ya existe", Toast.LENGTH_SHORT).show();
                                    break;
                                }
                            }
                            if(!idRepetido) {
                                Mesa mesa = new Mesa();
                                mesa.setIdMesa(IDmesa);
                                mesa.setUbicacionMesa(ubimesa);
                                mesa.setCapacidadMesa(capacimesa);
                                mesa.setDisponibilidadMesa(true);
                                dbref.push().setValue(mesa);
                                Toast.makeText(mesa.this, "Mesa Registrada", Toast.LENGTH_SHORT).show();
                                idmesa.setText("");
                                ubicacionmesa.setText("");
                                capacidadmesa.setText("");
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(mesa.this, "Error al cargar Datos de las Mesa", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

}