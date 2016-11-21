package com.example.wu.tabapplication;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {

    public static int SPO2_CONNECT_STATE = 0;
    public static int SPO2_DATA_RECEIVE_STATE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_container, new DeviceTeachingFragment()).addToBackStack(null).commit();


    }
}
