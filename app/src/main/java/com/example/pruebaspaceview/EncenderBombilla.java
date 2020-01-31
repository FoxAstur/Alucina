package com.example.pruebaspaceview;

import android.os.AsyncTask;

import com.mollin.yapi.YeelightDevice;
import com.mollin.yapi.exception.YeelightResultErrorException;
import com.mollin.yapi.exception.YeelightSocketException;

public class EncenderBombilla extends AsyncTask<String,Object,YeelightDevice> {


    @Override
    protected YeelightDevice doInBackground(String... strings) {

        YeelightDevice device = null;
        try {
            device = new YeelightDevice(strings[0]);
        } catch (YeelightSocketException ex) {
            System.err.println("no se ha podido conectar con la bombilla");
        }
        try {

            // Switch on the device
            device.setPower(true);
        } catch (YeelightResultErrorException ex) {

            System.err.println("no se ha podido encender");
        } catch (YeelightSocketException e) {
            System.err.println("no se ha podido conectar con la bomibilla para encender");
        }




        return device;
    }
}
