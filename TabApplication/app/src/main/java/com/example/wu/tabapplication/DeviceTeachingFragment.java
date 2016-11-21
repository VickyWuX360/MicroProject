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


public class DeviceTeachingFragment extends Fragment {

    Button button_login1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_device_teaching, container, false);
    }


    public void setView() {
        button_login1 = (Button) getActivity().findViewById(R.id.button_login1);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        setView();


        button_login1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (LoginActivity.SPO2_DATA_RECEIVE_STATE == 0) {
                    Toast.makeText(getActivity(), "No Signal ", Toast.LENGTH_SHORT).show();
                    LoginActivity.SPO2_DATA_RECEIVE_STATE = 1;
                } else {
                    LoginActivity.SPO2_DATA_RECEIVE_STATE = 1;
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.frame_container, new DeviceConnectionFragment()).addToBackStack(null).commit();

                }
            }
        });

    }

    public DeviceTeachingFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
/*   public static DeviceTeachingFragment newInstance(String param1, String param2) {
        DeviceTeachingFragment fragment = new DeviceTeachingFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }*/

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
