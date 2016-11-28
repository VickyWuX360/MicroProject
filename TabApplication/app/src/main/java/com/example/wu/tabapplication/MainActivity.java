package com.example.wu.tabapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    public static int SPO2_CONNECT_STATE = 1;
    public static int SPO2_DATA_RECEIVE_STATE = 0;

    private class HttpTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            invalidateOptionsMenu();
            Log.i("MainActivity", "AsyncTask start");
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                URL url = new URL("http://140.115.197.16/?school=ym&app=OSA_Detector");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setConnectTimeout(10000);
                urlConnection.setReadTimeout(10000);

                InputStream in = new BufferedInputStream((urlConnection.getInputStream()));
                BufferedReader inReader = new BufferedReader(new InputStreamReader(in));

                String strResult ="";
                String rl = null;
                while((rl = inReader.readLine())!= null){
                    strResult += rl;
                }

                Log.e("strResult", strResult);



            } catch (Exception e) {
                e.printStackTrace();

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);

            Log.i("MainActivity", "AsyncTask completed");
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setView();
        setTab();

        if (SPO2_DATA_RECEIVE_STATE == 0) { // no signal input
            //  signal input訊號指示燈 red
            SPO2_DATA_RECEIVE_STATE = 1;
            Intent i = new Intent();
            i.setClass(MainActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        }
        if (SPO2_CONNECT_STATE == 0) { // connection is broken
            //  connection訊號指示燈 red
        }

    }

    public void setView() {
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.pager);
    }


    public void setTab() {
                /* set every tab title and icon */
        tabLayout.addTab(tabLayout.newTab().setText("Heart Rate").setIcon(R.drawable.icon_state_heartrate));
        tabLayout.addTab(tabLayout.newTab().setText("SPO2").setIcon(R.drawable.icon_state_spo2));
        tabLayout.addTab(tabLayout.newTab().setText("Exercise").setIcon(R.drawable.icon_state_exercise));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}
