package com.example.app_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_firebase.Entidades.Mesa;
import com.example.app_firebase.Entidades.Producto;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class producto extends AppCompatActivity {
    private EditText idproducto,nombreproducto,precioproducto;
    private CheckBox productodisponibilidad;
    private Button registrarProducto;
    private Spinner categoriaproducto;
    private Spinner tipoproducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);
        idproducto = findViewById(R.id.txtproductoid);
        nombreproducto = findViewById(R.id.txtproductonombre);
        precioproducto = findViewById(R.id.txtprecio);
        categoriaproducto = findViewById(R.id.txtcategoria);
        tipoproducto = findViewById(R.id.txttipo);
        productodisponibilidad = findViewById(R.id.productoDisponible);
        registrarProducto = findViewById(R.id.registrarProducto);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Categorias, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoriaproducto.setAdapter(adapter);

        categoriaproducto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String CategoriaSeleccionada = adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapterdos = ArrayAdapter.createFromResource(this, R.array.Tipo, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipoproducto.setAdapter(adapterdos);

        tipoproducto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String TipoSelecionado = adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(producto.this, "No se ha seleccionado Tipo", Toast.LENGTH_SHORT).show();
            }
        });



        btnRegistrarProducto();
    }
    public void btnRegistrarProducto(){
        registrarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(idproducto.getText().toString().trim().isEmpty()){
                    Toast.makeText(producto.this, "Llenar el campo ID Producto", Toast.LENGTH_SHORT).show();
                } else if (nombreproducto.getText().toString().trim().isEmpty()) {
                    Toast.makeText(producto.this, "Llenar el campo Nombre de Producto", Toast.LENGTH_SHORT).show();
                } else if (precioproducto.getText().toString().trim().isEmpty()) {
                    Toast.makeText(producto.this, "Llenar el campo Precio del Producto", Toast.LENGTH_SHORT).show();
               } else{
                     int IdProducto = Integer.parseInt(idproducto.getText().toString());
                     String nomProducto = nombreproducto.getText().toString();
                     Double precproducto = Double.parseDouble(precioproducto.getText().toString());
                     String cateproducto = categoriaproducto.getSelectedItem().toString();
                     String tipproducto = tipoproducto.getSelectedItem().toString();
                     Boolean productdisponibilidad;
                     if (productodisponibilidad.isChecked()){
                         productdisponibilidad = true;
                     }else {
                         productdisponibilidad = false;
                     }
                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference dbref = db.getReference(Producto.class.getSimpleName());

                    dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            boolean idRepetido = false;
                            boolean nombreRepetido = false;
                            String aux = Integer.toString(IdProducto);

                            for (DataSnapshot productox : snapshot.getChildren()){
                                if(productox.child("idProducto").getValue().toString().equalsIgnoreCase(aux)){
                                    idRepetido = true;
                                    Toast.makeText(producto.this, "Error, El id " +aux+ " ya existe", Toast.LENGTH_SHORT).show();
                                    break;
                                }

                                if(productox.child("nombreProducto").getValue().toString().equals(nomProducto)){
                                    nombreRepetido = true;
                                    Toast.makeText(producto.this, "Error, El nombre " +nomProducto+ " ya existe", Toast.LENGTH_SHORT).show();
                                    break;
                                }
                            }
                            if (!idRepetido && !nombreRepetido){
                                Producto producto = new Producto();
                                producto.setIdProducto(IdProducto);
                                producto.setNombreProducto(nomProducto);
                                producto.setPrecioProducto(precproducto);
                                producto.setCategoriaProducto(cateproducto);
                                producto.setTipoProducto(tipproducto);
                                producto.setDisponibilidadProducto(productdisponibilidad);
                                dbref.push().setValue(producto);
                                Toast.makeText(producto.this, "Producto Registrado", Toast.LENGTH_SHORT).show();
                                idproducto.setText("");
                                nombreproducto.setText("");
                                precioproducto.setText("");
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(producto.this, "Error en la carga de Datos del Producto", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });
    }
}