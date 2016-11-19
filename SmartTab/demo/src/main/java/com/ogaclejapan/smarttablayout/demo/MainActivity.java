package com.ogaclejapan.smarttablayout.demo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

public class MainActivity extends AppCompatActivity {
  // implements AbsListView.OnItemClickListener
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);


    ViewGroup tab = (ViewGroup) findViewById(R.id.tab);
    tab.addView(LayoutInflater.from(this).inflate(R.layout.demo_custom_tab_icon_and_text, tab, false));

    ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
    SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);

    FragmentPagerItems pages = new FragmentPagerItems(this);

    pages.add(FragmentPagerItem.of(getString(R.string.fragment_heart_rate), HeartRateFragment.class));
    pages.add(FragmentPagerItem.of(getString(R.string.fragment_spo2), DemoFragment.class));
    pages.add(FragmentPagerItem.of(getString(R.string.fragment_exercise), DemoFragment.class));
    FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
            getSupportFragmentManager(), pages);

    viewPager.setAdapter(adapter);
    viewPagerTab.setViewPager(viewPager);




  }
  public void onBackPressed() {

//    super.onBackPressed();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.app_name);
        builder.setMessage("離開應用程式?");
        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();

  }

//  public boolean onOptionsItemSelected(MenuItem item) {
//    switch (item.getItemId()) {
//      case R.id.menu_github:
////        openGitHub();
//        return true;
//      default:
//        return super.onOptionsItemSelected(item);
//    }
//  }

//  @Override
//  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//    Demo demo = Demo.values()[position];
//
//
//
//
//    demo.startActivity(this);
//  }



/*  private void openGitHub() {
    Uri uri = Uri.parse(getString(R.string.app_github_url));
    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
    startActivity(intent);
  }*/

}
