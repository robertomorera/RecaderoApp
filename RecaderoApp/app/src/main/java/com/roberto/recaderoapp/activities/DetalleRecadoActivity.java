package com.roberto.recaderoapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.roberto.recaderoapp.R;
import com.roberto.recaderoapp.dominios.Recado;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DetalleRecadoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_recado);
        //Obtenemos el recado seleccionado del intent.
        Recado recado=(Recado)getIntent().getExtras().get("recado");
        //Recuperamos los elementos del layout y seteamos los datos del anuncio.
        TextView caja_cliente=(TextView)findViewById(R.id.caja_cliente);
        caja_cliente.setText(recado.getNombre_cliente());
        TextView caja_telefono=(TextView)findViewById(R.id.caja_telefono);
        caja_telefono.setText(recado.getTelefono());
        TextView caja_dir_recogida=(TextView)findViewById(R.id.caja_dir_recogida);
        caja_dir_recogida.setText(recado.getDireccion_recogida());
        TextView caja_dir_entrega=(TextView)findViewById(R.id.caja_dir_entrega);
        caja_dir_entrega.setText(recado.getDireccion_entrega());
        TextView caja_fecha_max=(TextView)findViewById(R.id.caja_fecha_max);
        Date fecha_max=recado.getFecha_hora_maxima();
        //Formateamos la fecha máxima para mostrarla en la fila en el formato deseado.
        SimpleDateFormat sdf =new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String fecha_max_formateada=sdf.format(fecha_max);
        caja_fecha_max.setText(fecha_max_formateada);
    }
}
