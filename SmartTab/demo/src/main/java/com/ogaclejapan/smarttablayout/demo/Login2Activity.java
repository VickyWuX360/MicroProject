package com.ogaclejapan.smarttablayout.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login2Activity extends Activity {

    Button button_login2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);



        button_login2 = (Button) findViewById(R.id.button_login2);
        button_login2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent();
                i.setClass(Login2Activity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });


    }
}
