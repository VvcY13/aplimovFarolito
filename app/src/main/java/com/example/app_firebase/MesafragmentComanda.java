package com.example.app_firebase;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.content.Context;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.app_firebase.Entidades.DetalleComanda;
import com.example.app_firebase.Entidades.Producto;
import com.example.app_firebase.Interface.productoSeleccionadoListener;

import java.util.ArrayList;
import java.util.List;


public class MesafragmentComanda extends Fragment implements productoSeleccionadoListener {


    private List<DetalleComanda> listaProductosSeleccionados = new ArrayList<>();
    private List<Producto> listaProductos = new ArrayList<>();
    private DetalleComanda detalleComanda;

    public MesafragmentComanda() {

    }
    public static MesafragmentComanda newInstance(String param1, String param2) {
        MesafragmentComanda fragment = new MesafragmentComanda();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mesacomanda, container, false);
        ListView listViewComanda = view.findViewById(R.id.listViewComanda);
        DetalleComandaAdapter detalleComandaAdapter = new DetalleComandaAdapter(getActivity(), listaProductosSeleccionados, listaProductos);
        listViewComanda.setAdapter(detalleComandaAdapter);
        detalleComanda = new DetalleComanda();
        return view;
    }

    @Override
    public void productoSeleccionado(DetalleComanda detalleComanda) {
        listaProductosSeleccionados.add(detalleComanda);
        obtenerInformacionProducto(detalleComanda.getIdProducto());
        actualizarListaProductos(listaProductosSeleccionados);
        imprimirProductosRecibidos();
        }

    private void obtenerInformacionProducto(int idProducto) {
        DetalleComanda nuevoDetalle = new DetalleComanda();
        for (Producto producto : listaProductos) {
            if (producto.getIdProducto() == idProducto) {
                Log.d("obteniendo datos", "Información del producto: " + producto.getIdProducto() + ", " + producto.getPrecioProducto());
                nuevoDetalle.setIdProducto(producto.getIdProducto());
                nuevoDetalle.setSubtotal(producto.getPrecioProducto()* detalleComanda.getCantidad());
                break;
            }
        }

    }

    public void actualizarListaProductos(List<DetalleComanda> nuevaListaProductos) {
        for (DetalleComanda detalleComanda : nuevaListaProductos) {
        }
}
    private void imprimirProductosRecibidos() {
        Log.d("MesafragmentComanda", "Productos recibidos:");
        for (DetalleComanda detalleComanda : listaProductosSeleccionados) {
            Log.d("MesafragmentComanda", detalleComanda.toString());
        }
    }
}