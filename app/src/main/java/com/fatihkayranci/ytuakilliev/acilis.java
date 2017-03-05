package com.fatihkayranci.ytuakilliev;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

public class acilis extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acilis);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();

        /*final Runnable runnable3sec = new Runnable() {
            @Override
            public void run() {
                nextActivity();
                finish();
            }
        };
        Handler myHandler = new Handler();
        myHandler.postDelayed(runnable3sec,3000);*/

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                nextActivity();
                finish();
            }
        },4000);


    }

    public void nextActivity(){
        Intent intent = new Intent(this,GirisActivity.class);
        startActivity(intent);
    }
}
