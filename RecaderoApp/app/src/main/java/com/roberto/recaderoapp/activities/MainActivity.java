package com.roberto.recaderoapp.activities;

import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.roberto.recaderoapp.R;
import com.roberto.recaderoapp.adapters.ListadoRecadosAdapter;
import com.roberto.recaderoapp.dominios.Recado;
import com.roberto.recaderoapp.listeners.ListenerFloatingButton;
import com.roberto.recaderoapp.utils.ComparaFechas;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private ListadoRecadosAdapter listadoRecadosAdapter;

    private ArrayList<Recado> listaRecados;

    private ArrayList<Boolean> recadosMarcados;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState!=null){
            listaRecados=(ArrayList<Recado>)savedInstanceState.getSerializable("listaRecados");
            recadosMarcados=(ArrayList<Boolean>)savedInstanceState.getSerializable("recadosMarcados");
        }else{
            listaRecados=new ArrayList<Recado>();
            recadosMarcados=new ArrayList<Boolean>();
        }
        //Obtenemos referencia del recycler view.
        recyclerView=(RecyclerView)findViewById(R.id.lista_recados);
        //Obtenemos referencia del floating button de descargar anuncios.
        FloatingActionButton fab_descargar=(FloatingActionButton)findViewById(R.id.fab_pedir_recados);
        //Creamos el listener asociado.
        ListenerFloatingButton listenerFabDescargar=new ListenerFloatingButton(this);
        //Asociamos el listener al button.
        fab_descargar.setOnClickListener(listenerFabDescargar);
        //Obtenemos referencia del floating button de borrar anuncios.
        FloatingActionButton fab_borrar=(FloatingActionButton)findViewById(R.id.fab_eliminar_recados);
        //Creamos el listener asociado.
        ListenerFloatingButton listenerFabBorrar=new ListenerFloatingButton(this);
        //Asociamos el listener al button.
        fab_borrar.setOnClickListener(listenerFabDescargar);
        //Creamos el adapter del recycler view.
        listadoRecadosAdapter=new ListadoRecadosAdapter(listaRecados,recadosMarcados,this);
        recyclerView.setAdapter(listadoRecadosAdapter);
        //Asignamos la orientacion del recycler view.
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    /**
     * Asigna a la vista los recados descargados por el AsyncTask
     * @param listaRecadosDescargados
     */
    public void setListadoRecados(ArrayList<Recado> listaRecadosDescargados){
        //listaRecados=listaRecadosDescargados;
        //Recorramos la lista de recados descargados.
        for(int i=0;i<listaRecadosDescargados.size();i++){
            //Obtenemos el recado.
            Recado recado=(Recado)listaRecadosDescargados.get(i);
            listaRecados.add(recado);
            //Como se un anuncio nuevo el recadero no ha podido marcalos aún.
            recadosMarcados.add(false);
        }
        //Ordenamos por fecha los recados descargados.
        Collections.sort(listaRecados,new ComparaFechas());
        //Creamos el adapter del recycler view.
        listadoRecadosAdapter=new ListadoRecadosAdapter(listaRecados,recadosMarcados,this);
        //Creamos el adapter del recycler view.
        recyclerView.setAdapter(listadoRecadosAdapter);
        //Asignamos la orientacion del recycler view.
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("listaRecados",listaRecados);
        outState.putSerializable("recadosMarcados",recadosMarcados);
    }

    public void borrarRecados(){

        if(listaRecados.size()==0){
            Toast.makeText(this,R.string.toast_borrado,Toast.LENGTH_SHORT).show();
        }

        ArrayList<Boolean> recadosMarcadosAux=new ArrayList<Boolean>();
        ArrayList<Recado> listaRecadosAux=new ArrayList<Recado>();
        for(int i=0;i<recadosMarcados.size();i++){
            //Si el anuncio no está marcado por el recadero se conserva
            if(!recadosMarcados.get(i)){
                recadosMarcadosAux.add(recadosMarcados.get(i));
                listaRecadosAux.add(listaRecados.get(i));
            }
        }
        //Actualizamos las variables.
        recadosMarcados=recadosMarcadosAux;
        listaRecados=listaRecadosAux;
        //Creamos el adapter del recycler view.
        listadoRecadosAdapter=new ListadoRecadosAdapter(listaRecados,recadosMarcados,this);
        //Creamos el adapter del recycler view.
        recyclerView.setAdapter(listadoRecadosAdapter);

    }
}
