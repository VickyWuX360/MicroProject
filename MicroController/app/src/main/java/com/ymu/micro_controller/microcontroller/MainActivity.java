package com.ymu.micro_controller.microcontroller;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
//import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.ViewPager;
//import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
//import android.view.Menu;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
/*    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private ListView mDrawerList;*/

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

                String strResult = "";
                String rl = null;
                while ((rl = inReader.readLine()) != null) {
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

    /*--------------- Navagation Drawer --------------*/
/*    public void setNavagationDrawer() {
        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {

            *//** Called when a drawer has settled in a completely closed state. *//*
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            *//** Called when a drawer has settled in a completely open state. *//*
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    */

    /**
     * Swaps fragments in the main content view
     *//*
    private void selectItem(int position) {
        // Create a new fragment and specify the planet to show based on position
        Fragment fragment = new HeartFragment();
        Bundle args = new Bundle();
        args.putInt("0", position);
        fragment.setArguments(args);

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();

        // Highlight the selected item, update the title, and close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(getResources().getStringArray(R.array.drawer_menu)[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }*/
    /*--------------- Navagation Drawer --------------*/

    public void setTab() {
                /* set every tab title and icon */
        tabLayout.addTab(tabLayout.newTab().setText(R.string.fragment_heartrate).setIcon(R.drawable.icon_state_heartrate));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.fragment_spo2).setIcon(R.drawable.icon_state_spo2));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.fragment_exercise).setIcon(R.drawable.icon_state_exercise));
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

        switch (item.getItemId()) {
            case R.id.menu_setting:
                return true;
            case R.id.menu_help:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }*/
}
