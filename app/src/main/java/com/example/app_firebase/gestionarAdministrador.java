package com.example.app_firebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.app_firebase.Entidades.Administrador;
import com.example.app_firebase.Entidades.Usuario;
import com.google.firebase.Firebase;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class gestionarAdministrador extends AppCompatActivity {
    private Button agregarAdministrador;
    private Button modificarAdmin;
    private Button borrarAdmin;
    private Button buscarAdmin;
    private EditText idadmin;
    private EditText getuseradmin;

    private ListView listaAdmins;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestionar_administrador);
        agregarAdministrador = findViewById(R.id.AgregarAdmin);
        modificarAdmin = findViewById(R.id.ModificarAdmin);
        borrarAdmin = findViewById(R.id.BorrarAdmin);
        listaAdmins = findViewById(R.id.listaAdmin);
        buscarAdmin = findViewById(R.id.buscar);
        idadmin = findViewById(R.id.txtingresarId);
        getuseradmin = findViewById(R.id.txtusuarioencontrado);

        ListarAdministradores();
        BuscarAdministrador();
        BorrarAdministrador();

        agregarAdministrador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ventanaAgregar = new Intent(getApplicationContext(), administrador.class);
                startActivity(ventanaAgregar);
            }
        });


    }
    private void ListarAdministradores(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference dbref = db.getReference(Administrador.class.getSimpleName());
        ArrayList<Administrador>listadmin = new ArrayList<Administrador>();
        ArrayAdapter<Administrador> adapteradmin = new ArrayAdapter<Administrador>(getApplicationContext(), android.R.layout.simple_list_item_1,listadmin);
        listaAdmins.setAdapter(adapteradmin);

        dbref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Administrador admin = snapshot.getValue(Administrador.class);
                listadmin.add(admin);
                adapteradmin.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                adapteradmin.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        listaAdmins.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Administrador admin = listadmin.get(i);
                AlertDialog.Builder a = new AlertDialog.Builder(gestionarAdministrador.this);
                a.setCancelable(true);
                a.setTitle("Usuario Seleccionado");
                String msg = "Nombres : " +admin.getNombre() + "\n\n";
                msg += "Apellidos : " +admin.getApellido() + "\n\n";
                msg += "Correo : " +admin.getCorreo() + "\n\n";

                a.setMessage(msg);
                a.show();
            }
        });

    }
    public void BuscarAdministrador(){
        buscarAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(idadmin.getText().toString().trim().isEmpty()){
                    Toast.makeText(gestionarAdministrador.this, "Ingresar el Id a Buscar", Toast.LENGTH_SHORT).show();
                }else {
                    int id = Integer.parseInt(idadmin.getText().toString());
                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference dbref = db.getReference(Administrador.class.getSimpleName());

                    dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String auxiliar = Integer.toString(id);
                            boolean respuesta = false;
                            for(DataSnapshot adminx : snapshot.getChildren()){
                                if(auxiliar.equalsIgnoreCase(adminx.child("id").getValue().toString())){
                                    respuesta = true;
                                    getuseradmin.setText(adminx.child("usuario").getValue().toString());
                                    break;
                                }
                            }
                            if (respuesta == false){
                                Toast.makeText(gestionarAdministrador.this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
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
    public void BorrarAdministrador(){
        borrarAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(idadmin.getText().toString().trim().isEmpty()){
                    Toast.makeText(gestionarAdministrador.this, "Ingresar el Id a Eliminar", Toast.LENGTH_SHORT).show();
                }else {
                    int id = Integer.parseInt(idadmin.getText().toString());
                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference dbref = db.getReference(Administrador.class.getSimpleName());

                    dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String auxiliar = Integer.toString(id);
                            boolean respuesta = false;
                            for(DataSnapshot adminx : snapshot.getChildren()){
                                if(auxiliar.equalsIgnoreCase(adminx.child("id").getValue().toString())){
                                    respuesta = true;
                                    adminx.getRef().removeValue();
                                    ListarAdministradores();
                                    break;
                                }
                            }
                            if (respuesta == false){
                                Toast.makeText(gestionarAdministrador.this, "Id no encontrado", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
            }
        };
    });
        }
}