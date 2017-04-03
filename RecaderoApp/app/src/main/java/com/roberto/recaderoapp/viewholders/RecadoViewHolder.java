package com.roberto.recaderoapp.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.roberto.recaderoapp.R;
import com.roberto.recaderoapp.dominios.Recado;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Usr on 02/04/2017.
 */

public class RecadoViewHolder extends RecyclerView.ViewHolder {

    private TextView caja_fecha_hora;

    private TextView caja_descripcion;

    public RecadoViewHolder(View itemView){
        super(itemView);
        caja_fecha_hora=(TextView)itemView.findViewById(R.id.caja_fechahora);
        caja_descripcion=(TextView)itemView.findViewById(R.id.caja_descripcion);

    }

    public void cargarRecadoEnHolder(Recado recado){
        Date fecha_hora=recado.getFecha_hora();
        //Formateamos la fecha para mostrarla en la fila en el formato deseado.
        SimpleDateFormat sdf =new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String fechaFormateada=sdf.format(fecha_hora);
        this.caja_fecha_hora.setText(fechaFormateada);
        //Ponemos la descripción del recado.
        this.caja_descripcion.setText(recado.getDescripcion());
    }



}
