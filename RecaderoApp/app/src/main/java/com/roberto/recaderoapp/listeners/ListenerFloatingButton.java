package com.roberto.recaderoapp.listeners;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.roberto.recaderoapp.R;
import com.roberto.recaderoapp.activities.MainActivity;
import com.roberto.recaderoapp.asyntasks.PedirRecados;

/**
 * Created by Usr on 02/04/2017.
 */

public class ListenerFloatingButton implements View.OnClickListener {

    private MainActivity mainActivity;

    public ListenerFloatingButton(MainActivity mainActivity){
        this.mainActivity=mainActivity;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.fab_pedir_recados:
                Log.d(getClass().getCanonicalName(),"El recadero ha pedido recados nuevos");
                //Iniciamos la petición de descarga de nuevos recados.
                //La peticíon se inicia hacemos visible el progress bar.
                ProgressBar progressBar=(ProgressBar)mainActivity.findViewById(R.id.progress_bar);
                progressBar.setVisibility(ProgressBar.VISIBLE);
                PedirRecados pedirRecados=new PedirRecados(mainActivity);
                pedirRecados.execute();
                break;
            case R.id.fab_eliminar_recados:
                Log.d(getClass().getCanonicalName(),"El recadero ha pulsado para borrar los recados finalizados");
                mainActivity.borrarRecados();
                break;
        }
    }
}
