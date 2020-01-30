package com.example.pruebaspaceview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;

import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;
import com.mollin.yapi.YeelightDevice;
import com.mollin.yapi.exception.YeelightResultErrorException;
import com.mollin.yapi.exception.YeelightSocketException;

import ui.fragmentBulbs.BulbFragment;
import ui.fragmentHome.HomeFragment;
import ui.fragmentSettings.SettingsFragment;

public class MainActivity extends AppCompatActivity {

    Context ctx;

    @Override
    protected void onStart() {
        super.onStart();
        ctx=this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState==null){

            FragmentManager manager = getSupportFragmentManager();

            FragmentTransaction trans = manager.beginTransaction();
            trans.replace(R.id.act_main_container_frame, HomeFragment.newInstance());
            trans.commitNow();


        }
        initComp(savedInstanceState);
    }

    private void initComp(Bundle savedInstanceState) {

        SpaceNavigationView spaceNavigationView = (SpaceNavigationView) findViewById(R.id.act_main_spaceViewer);
        spaceNavigationView.initWithSaveInstanceState(savedInstanceState);

        SpaceItem itmInicio = new SpaceItem("INICIO", R.drawable.ic_action_home);


        spaceNavigationView.addSpaceItem(itmInicio);
        SpaceItem itmAjustes = new SpaceItem("AJUSTES", R.drawable.ic_action_settings);
        spaceNavigationView.addSpaceItem(itmAjustes);

        spaceNavigationView.showIconOnly();

        spaceNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {

                YeelightDevice device = null;

                try {
                    device = new YeelightDevice("192.168.1.172");

                    device.setPower(true);
                    bulbCall();

                } catch (YeelightSocketException e) {
                    e.printStackTrace();
                    System.err.println("Error al conectar con la bombilla");

                } catch (YeelightResultErrorException ex){
                    ex.printStackTrace();
                    System.err.println("Error no se ha podido encender la bombilla");
            }
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {


                if(itemIndex<=0){
                    //Boton HOME

                    homeCall();

                }else{
                    //Boton SETTINGS

                    settingsCall();

                }

            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {

            }
        });
    }

    private void homeCall() {

        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction trans = manager.beginTransaction();
        trans.replace(R.id.act_main_container_frame,new HomeFragment(),"home_01");
        trans.commitNow();

    }

    private void settingsCall() {

        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction trans = manager.beginTransaction();
        trans.replace(R.id.act_main_container_frame,new SettingsFragment(),"sett_01");
        trans.commitNow();

    }

    private void bulbCall() {

        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction trans = manager.beginTransaction();
        trans.replace(R.id.act_main_container_frame,new BulbFragment(),"bulb_01");
        trans.commitNow();

    }
}
