package com.example.app_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.app_firebase.Entidades.Producto;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class dentrodemesa extends AppCompatActivity {
    private MesafragmentComidas comidas;
    private  MesafragmentBebidas bebidas;
    private MesafragmentComanda comanda;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dentrodemesa);

        comidas = new MesafragmentComidas();
        bebidas = new MesafragmentBebidas();
        comanda = new MesafragmentComanda();
        getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragments, comidas).commit();
    }

    public void onClick(View view){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if(view.getId()==R.id.btnComida){
            transaction.replace(R.id.contenedorFragments,comidas);
        } else if (view.getId()==R.id.btnBebidas) {
            transaction.replace(R.id.contenedorFragments,bebidas);
        } else if (view.getId()==R.id.btnComanda) {
            transaction.replace(R.id.contenedorFragments,comanda);
        }
        transaction.commit();
    }
}