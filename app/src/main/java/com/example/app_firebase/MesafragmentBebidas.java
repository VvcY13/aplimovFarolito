package com.example.app_firebase;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.app_firebase.Entidades.Producto;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class MesafragmentBebidas extends Fragment {
    private LinearLayout linearLayoutBebidas;
    private DatabaseReference ProductosRef;
    private List<Producto> listaDeBebidas = new ArrayList<>();
    private StorageReference storageReference;
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
        linearLayoutBebidas = view.findViewById(R.id.linear_layout_bebidas);
        ProductosRef = FirebaseDatabase.getInstance().getReference().child("Producto");
        Log.d("MesafragmentComidas", "ProductosRef inicializado correctamente.");

        ProductoAdapter productoAdapter = new ProductoAdapter(getActivity(), listaDeBebidas);


        ProductosRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listaDeBebidas.clear();
                linearLayoutBebidas.removeAllViews();
                for (DataSnapshot Dsnapshot : snapshot.getChildren()) {
                    Producto producto = Dsnapshot.getValue(Producto.class);
                    if (producto != null && "Bebidas".equals(producto.getTipoProducto())) {
                        Button button = new Button(getActivity());
                        int newWidth = 700;
                        int newHeight = 300;
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(newWidth, newHeight);
                        layoutParams.setMargins(10, 10, 10, 25);
                        button.setLayoutParams(layoutParams);


                        storageReference = FirebaseStorage.getInstance().getReference();
                        // Obtener la URL de la imagen desde el producto
                        String imageUrl = producto.getUrl();
                        Log.e("URL", "es  " +imageUrl+"");

                        Glide.with(getActivity())
                                .load(imageUrl)
                                .centerCrop()
                                .into(new CustomTarget<Drawable>() {
                                    @Override
                                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                                        button.setBackground(resource);
                                    }

                                    @Override
                                    public void onLoadCleared(@Nullable Drawable placeholder) {
                                    }
                                });

                        linearLayoutBebidas.addView(button);

                        listaDeBebidas.add(producto);

                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
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