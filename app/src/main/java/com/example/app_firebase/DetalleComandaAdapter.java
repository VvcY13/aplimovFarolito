package com.example.app_firebase;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.widget.TextView;

import com.example.app_firebase.Entidades.DetalleComanda;
import com.example.app_firebase.Entidades.Producto;

import java.util.List;

public class DetalleComandaAdapter extends ArrayAdapter<DetalleComanda> {

    private List<Producto> listaProductos;
    public DetalleComandaAdapter(Context context, List<DetalleComanda> detalleComandas, List<Producto> listaProductos) {
        super(context, 0, detalleComandas);
        this.listaProductos = listaProductos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DetalleComanda detalleComanda = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_comanda, parent, false);
        }

        TextView idproductoTextView = convertView.findViewById(R.id.nombreProductoTextView);
        TextView precioProductoTextView = convertView.findViewById(R.id.precioProductoTextView);
        TextView cantidadProductoTextView = convertView.findViewById(R.id.cantidadProductoTextView);

        // Obtener el nombre del producto utilizando el ID
        int idProducto = detalleComanda.getIdProducto();


        idproductoTextView.setText(String.valueOf(idProducto));
        precioProductoTextView.setText(String.valueOf(detalleComanda.getSubtotal()));
        cantidadProductoTextView.setText(String.valueOf(detalleComanda.getCantidad()));

        return convertView;
    }

}
