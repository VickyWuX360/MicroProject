package com.example.wu.tabapplication;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    public static int SPO2_CONNECT_STATE = 1;
    public static int SPO2_DATA_RECEIVE_STATE = 0;

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
