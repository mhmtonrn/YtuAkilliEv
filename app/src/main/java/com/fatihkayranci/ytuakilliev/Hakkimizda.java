package com.fatihkayranci.ytuakilliev;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class Hakkimizda extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hakkimizda);


        ActionBar Televizyon=getSupportActionBar();
        Televizyon.setTitle("Hakkımızda");
        Televizyon.setSubtitle("Akıllı Ev Ekibi");
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.home2);
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.arkaplan_action_bilgi));




    }
}
