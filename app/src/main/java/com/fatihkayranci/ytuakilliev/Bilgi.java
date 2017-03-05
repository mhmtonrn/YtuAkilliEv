package com.fatihkayranci.ytuakilliev;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Bilgi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bilgi);

        ActionBar Televizyon=getSupportActionBar();
        Televizyon.setTitle("Akıllı Ev Detaylı Bilgiler");
        Televizyon.setSubtitle("Akıllı Ev neyi vaad ediyor?");
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.home2);
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.arkaplan_action_bilgi));
    }

}
