package com.roberto.recaderoapp.utils;

import android.util.Log;

import com.roberto.recaderoapp.dominios.Recado;

import java.text.ParseException;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by Usr on 02/04/2017.
 */

public class ComparaFechas implements Comparator<Recado>
{

    @Override
    public int compare(Recado o1, Recado o2) {

        int resultado=0;
        Date date01 = o1.getFecha_hora();
        Date date02 = o2.getFecha_hora();
        resultado=date01.compareTo(date02);
        return resultado;
    }
}
