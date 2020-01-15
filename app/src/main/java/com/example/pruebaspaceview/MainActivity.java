package com.example.pruebaspaceview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

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
        SpaceNavigationView spaceNavigationView = (SpaceNavigationView) findViewById(R.id.space);
        spaceNavigationView.initWithSaveInstanceState(savedInstanceState);

        SpaceItem itmInicio = new SpaceItem("INICIO", R.drawable.ic_action_home);


        spaceNavigationView.addSpaceItem(itmInicio);
        SpaceItem itmAjustes = new SpaceItem("AJUSTES", R.drawable.ic_action_settings);
        spaceNavigationView.addSpaceItem(itmAjustes);

        spaceNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                //PONER LO QUE QUIERO QUE HAGA EL BOTON CENTRAL
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {

            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {

            }
        });
    }
}
