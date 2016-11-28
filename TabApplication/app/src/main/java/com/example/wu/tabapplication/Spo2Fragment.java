package com.example.wu.tabapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;


public class Spo2Fragment extends Fragment {

    private TextView textView_value_spo2, textView_value_lowtimes, textView_value_lowindex, textView_value_totalsleeptime;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_spo2, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        setView();

        textView_value_lowtimes.setText("0.0");
        textView_value_lowindex.setText("0.0");
        textView_value_totalsleeptime.setText("0.0");

    }

    public void setView() {
        textView_value_spo2 = (TextView) getActivity().findViewById(R.id.textView_value_spo2);
        textView_value_lowtimes = (TextView) getActivity().findViewById(R.id.textView_value_lowtimes);
        textView_value_lowindex = (TextView) getActivity().findViewById(R.id.textView_value_lowindex);
        textView_value_totalsleeptime = (TextView) getActivity().findViewById(R.id.textView_value_totalsleeptime);
    }

    public Spo2Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
