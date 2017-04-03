package com.roberto.recaderoapp.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.roberto.recaderoapp.R;
import com.roberto.recaderoapp.activities.DetalleRecadoActivity;
import com.roberto.recaderoapp.activities.MainActivity;
import com.roberto.recaderoapp.dominios.Recado;
import com.roberto.recaderoapp.viewholders.RecadoViewHolder;

import java.util.ArrayList;

/**
 * Created by Usr on 02/04/2017.
 */

public class ListadoRecadosAdapter extends RecyclerView.Adapter<RecadoViewHolder> {

    private ArrayList<Recado> listaRecados;

    private ArrayList<Boolean> recadosMarcados;

    private MainActivity mainActivity;

    private Recado recado;

    public ListadoRecadosAdapter(ArrayList<Recado> listaRecados,ArrayList<Boolean> recadosMarcados,MainActivity mainActivity){
        this.listaRecados=listaRecados;
        this.recadosMarcados=recadosMarcados;
        this.mainActivity=mainActivity;
    }

    @Override
    public RecadoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecadoViewHolder recadoViewHolder=null;
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View fila_inflada=inflater.inflate(R.layout.layout_recado,parent,false);
        recadoViewHolder=new RecadoViewHolder(fila_inflada);
        return recadoViewHolder;
    }

    @Override
    public void onBindViewHolder(RecadoViewHolder holder, final int position) {
        recado=(Recado)listaRecados.get(position);
        holder.cargarRecadoEnHolder(recado);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Iniciamos la activity para mostrar el detalle del recado seleccionado.
                Intent intent=new Intent(mainActivity,DetalleRecadoActivity.class);
                recado=(Recado)listaRecados.get(position);
                intent.putExtra("recado",recado);
                mainActivity.startActivity(intent);

            }
        });

        //Obtenemos el checkBox de la fila.
        CheckBox checkBox=(CheckBox)holder.itemView.findViewById(R.id.checkRecado);
        if(recadosMarcados.get(position)){
            checkBox.setChecked(true);
        }else{
            checkBox.setChecked(false);
        }
        checkBox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(recadosMarcados.get(position)){
                    //El check estaba seleccionado.
                    recadosMarcados.set(position,false);
                }else{
                    recadosMarcados.set(position,true);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaRecados.size();
    }

}
