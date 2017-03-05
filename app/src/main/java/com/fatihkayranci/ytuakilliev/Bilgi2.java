package com.fatihkayranci.ytuakilliev;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

public class Bilgi2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bilgi2);


        ActionBar Televizyon=getSupportActionBar();
        Televizyon.setTitle("Akıllı Ev");
        Televizyon.setSubtitle("Akıllı Ev'in amaçları");
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.home2);
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.arkaplan_action_bilgi));
    }

}
