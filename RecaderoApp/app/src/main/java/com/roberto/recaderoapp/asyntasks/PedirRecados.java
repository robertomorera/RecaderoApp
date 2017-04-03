package com.roberto.recaderoapp.asyntasks;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.roberto.recaderoapp.R;
import com.roberto.recaderoapp.activities.MainActivity;
import com.roberto.recaderoapp.dominios.Recado;
import com.roberto.recaderoapp.utils.Constantes;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Usr on 01/04/2017.
 */

public class PedirRecados extends AsyncTask<Void,Void,ArrayList<Recado>> {

    private MainActivity mainActivity;

    public PedirRecados(MainActivity mainActivity){
        this.mainActivity=mainActivity;
    }

    @Override
    protected ArrayList<Recado> doInBackground(Void... params) {

        ArrayList<Recado> listaRecados=null;
        //Obtenemos la ruta del servicio
        String url_servlet= Constantes.END_POINT_SERVICIO_RECADOS;
        URL url=null;
        HttpURLConnection peticion=null;
        InputStream inputStream=null;
        InputStreamReader inputStreamReader=null;
        int codigoRespuesta=-1;
        String mensajeDevuelto=null;
        try{
            //Creamos la URL.
            url=new URL(url_servlet);
            //Abrimos la conexión.
            peticion=(HttpURLConnection)url.openConnection();
            peticion.setRequestMethod("GET");
            //Recuperamos el código de respuesta.
            codigoRespuesta=peticion.getResponseCode();
            if(codigoRespuesta==HttpURLConnection.HTTP_OK){
                //Accedemos al cuerpo de la petición.
                inputStream=peticion.getInputStream();
                //Creamos el InputStreamReader para leer texto.
                inputStreamReader=new InputStreamReader(inputStream);
                //Creamos un BufferedReader.
                BufferedReader buffer=new BufferedReader(inputStreamReader);
                //Obtenemos el mensaje devuelto por el servlet.
                mensajeDevuelto=buffer.readLine();
                //Cerramos el buffer.
                inputStream.close();
                //Para obtener la lista de recados deserializamos el JSON devuelto por el
                //servlet.
                //Creamos el GSON.
                Gson gson =new Gson();
                listaRecados=gson.fromJson(mensajeDevuelto,new TypeToken<ArrayList<Recado>>(){}.getType());
            }
        }catch(Throwable t){
            Log.d(getClass().getCanonicalName(),"La petición de recados fue mal: "+t);
        }finally{
            //Cerramos la conexión de la petición HTTP.
            peticion.disconnect();
        }

        return listaRecados;


    }

    @Override
    protected void onPostExecute(ArrayList<Recado> recados) {
        super.onPostExecute(recados);
        //La peticíon ha finalizado hacemos invisible el progress bar.
        ProgressBar progressBar=(ProgressBar)mainActivity.findViewById(R.id.progress_bar);
        progressBar.setVisibility(ProgressBar.INVISIBLE);
        mainActivity.setListadoRecados(recados);
    }
}
