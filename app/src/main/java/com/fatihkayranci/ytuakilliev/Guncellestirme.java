package com.fatihkayranci.ytuakilliev;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

public class Guncellestirme extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guncellestirme);


        ActionBar Televizyon=getSupportActionBar();
        Televizyon.setTitle("Güncelleme");
        Televizyon.setSubtitle("Güncelleme Modülü");
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.home2);
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.arkaplan_action_bilgi));

    }
}
