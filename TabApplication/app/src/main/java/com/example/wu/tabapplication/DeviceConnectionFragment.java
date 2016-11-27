package com.example.wu.tabapplication;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class DeviceConnectionFragment extends Fragment {
    Button button_connect_done;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_device_connection, container, false);
    }

    public void setView() {
        button_connect_done = (Button) getActivity().findViewById(R.id.button_connect_done);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        setView();

        button_connect_done.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                
//                if (LoginActivity.SPO2_CONNECT_STATE == 0) {
//                    Toast.makeText(getActivity(), "No Connecion ", Toast.LENGTH_SHORT).show();
//                    LoginActivity.SPO2_CONNECT_STATE = 1;
//                } else {
//                    MainActivity.SPO2_CONNECT_STATE = 1;
                    Intent i = new Intent();
                    i.setClass(getActivity(), MainActivity.class);
                    startActivity(i);
                    getActivity().finish();

//                }


            }
        });

    }

    public DeviceConnectionFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
