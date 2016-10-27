package com.ogaclejapan.smarttablayout.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends Activity {

    Button button_login1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        button_login1 = (Button) findViewById(R.id.button_login1);
        button_login1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent();
                i.setClass(LoginActivity.this,Login2Activity.class);
                startActivity(i);
                finish();
            }
        });


    }
}
