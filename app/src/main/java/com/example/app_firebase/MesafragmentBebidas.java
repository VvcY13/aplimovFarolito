package com.example.app_firebase;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.app_firebase.Entidades.Producto;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MesafragmentBebidas extends Fragment {
    private ListView listView;
    private DatabaseReference ProductosRef;
    private List<Producto> listaDeBebidas = new ArrayList<>();
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

   private String mParam1;
    private String mParam2;

    public MesafragmentBebidas() {
        // Required empty public constructor
    }

   public static MesafragmentBebidas newInstance(String param1, String param2) {
        MesafragmentBebidas fragment = new MesafragmentBebidas();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mesabebidas, container, false);
        ProductosRef = FirebaseDatabase.getInstance().getReference().child("Producto");
        Log.d("MesafragmentComidas", "ProductosRef inicializado correctamente.");
        listView = view.findViewById(R.id.list_view_bebidas);
        ProductoAdapter productoAdapter = new ProductoAdapter(getActivity(), listaDeBebidas);
        listView.setAdapter(productoAdapter);

        ProductosRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listaDeBebidas.clear();
                for (DataSnapshot Dsnapshot : snapshot.getChildren()) {
                    Producto producto = Dsnapshot.getValue(Producto.class);
                    if (producto != null && "Bebidas".equals(producto.getTipoProducto())) {
                        listaDeBebidas.add(producto);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Producto producto = listaDeBebidas.get(i);
                                mostrarDetallesProducto(producto);
                            }
                        });

                    }
                }
                productoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("MesafragmentBebidas", "Error al obtener bebidas", error.toException());
            }
        });


        return view;
    }

    private void mostrarDetallesProducto(Producto producto) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Detalles del Producto");

        builder.setMessage("Nombre: " + producto.getNombreProducto() + "\nPrecio: S/" + producto.getPrecioProducto());

        builder.setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}