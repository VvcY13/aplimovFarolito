package com.example.app_firebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.app_firebase.Entidades.Mesa;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class listademesas extends AppCompatActivity {
    private DatabaseReference mesasRef;
    private LinearLayout contenedorMesas;
    ArrayList<Button> mesasList = new ArrayList<>();

    private int idmesaseleccionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listademesas);

        contenedorMesas = findViewById(R.id.contenedor_mesas);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mesasRef = database.getReference("Mesa");

        mesasRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
               int idMesa = snapshot.child("idMesa").getValue(Integer.class);
                Button mesaButton = new Button(listademesas.this);
                mesaButton.setText("Mesa " + idMesa);
                mesaButton.setTag(idMesa);
                mesasList.add(mesaButton);
                ordenarMesas();
                mesaButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        idmesaseleccionada = idMesa;
                        System.out.println(idmesaseleccionada);
                        Intent ventanadentrodemesa = new Intent(getApplicationContext(),dentrodemesa.class);
                        startActivity(ventanadentrodemesa);
                    }
                });
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                String mesaKey = snapshot.getKey();
                for (int i = 0; i < contenedorMesas.getChildCount(); i++) {
                    View child = contenedorMesas.getChildAt(i);
                    if (child instanceof Button) {
                        Button button = (Button) child;
                        if (button.getText().toString().equals("Mesa " + mesaKey)) {
                            contenedorMesas.removeViewAt(i);
                            break;
                        }
                    }
                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void ordenarMesas() {

        Collections.sort(mesasList, new Comparator<Button>() {
            @Override
            public int compare(Button mesa1, Button mesa2) {
                int idMesa1 = (int) mesa1.getTag();
                int idMesa2 = (int) mesa2.getTag();
                return Integer.compare(idMesa1, idMesa2);
            }
        });

        contenedorMesas.removeAllViews();

        for (Button mesaButton : mesasList) {
            contenedorMesas.addView(mesaButton);
        }
    }
}