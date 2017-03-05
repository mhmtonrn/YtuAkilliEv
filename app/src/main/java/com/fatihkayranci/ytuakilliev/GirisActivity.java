package com.fatihkayranci.ytuakilliev;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


//Implementing the interface OnTabSelectedListener to our MainActivity
//This interface would help in swiping views

public class GirisActivity extends AppCompatActivity{
    Button button,button2,button3,button4,button5,button6,button7,button8;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);

        ActionBar actionBar=getSupportActionBar();

        actionBar.setTitle("Akıllı Ev"); //action Bar
        actionBar.setSubtitle("Giriş Ekranı");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.home2);
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.arkaplan_action));

        button = (Button) findViewById(R.id.TV);
        button2 = (Button) findViewById(R.id.klima);
        button3 = (Button) findViewById(R.id.priz1);
        button4 = (Button) findViewById(R.id.priz2);
        button5 = (Button) findViewById(R.id.priz3);
        button6 = (Button) findViewById(R.id.hakkimizda);
        button7 = (Button) findViewById(R.id.guncelleme);
        button8=(Button) findViewById(R.id.bilgi);
        View imageButton=findViewById(R.id.imageButton);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GirisActivity.this, TV.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GirisActivity.this, Klima.class);
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GirisActivity.this, Priz1.class);
                startActivity(intent);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GirisActivity.this, Priz2.class);
                startActivity(intent);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GirisActivity.this, Priz3.class);
                startActivity(intent);
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GirisActivity.this, Hakkimizda.class);
                startActivity(intent);
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GirisActivity.this, Guncellestirme.class);
                startActivity(intent);
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GirisActivity.this, Bilgi.class);
                startActivity(intent);
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GirisActivity.this,Bilgi2.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(R.mipmap.akilli_ev_logo)
                .setTitle("Emin misin?")
                .setMessage("Akıllı Ev uygulamasından çıkılsın mı?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        GirisActivity.super.onBackPressed();
                    }
                }).create().show();
    }
}