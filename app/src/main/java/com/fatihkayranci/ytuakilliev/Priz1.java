package com.fatihkayranci.ytuakilliev;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.UUID;

public class Priz1 extends AppCompatActivity {
    private static final String TAG = "bluetooth1";

    private BluetoothAdapter btAdapter = null;
    private BluetoothSocket btSocket = null;
    private OutputStream outStream = null;
    private InputStream instream = null;
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private static String address = "00:14:04:01:21:24";
    RelativeLayout rl;
    Button ac;
    Toast toast;
    int normal = 0;
    String sicaklik, akim, gerilim;
    TextView layoutSicaklik, layoutAkim, layoutGerilim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_priz1);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setTitle("Priz 1"); //action Bar
        actionBar.setSubtitle("Priz Kontrolü");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.priz);
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.arkaplan_action_priz));


        rl = (RelativeLayout) findViewById(R.id.rl);
        ac = (Button) findViewById(R.id.ac);
        layoutSicaklik = (TextView) findViewById(R.id.sicaklik);
        layoutGerilim = (TextView) findViewById(R.id.gerilim);
        layoutAkim = (TextView) findViewById(R.id.akim);

        this.btAdapter = BluetoothAdapter.getDefaultAdapter();
        this.checkBTState();

/*
      */




        ac.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (normal == 0) {
                    rl.setBackgroundColor(Color.GREEN);
                    ac.setBackground(getResources().getDrawable(R.drawable.redtus));
                    Priz1.this.sendData("C");
                    Toast toast = Toast.makeText(Priz1.this, "Priz 1 açıldı", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
                    toast.show();
                    Priz1.this.sendData("A");
                    akim=Priz1.this.getData();

                    Priz1.this.sendData("B");
                    gerilim=Priz1.this.getData();
//denerken yorum satırları kaldıracak
                    Priz1.this.sendData("D");
                    sicaklik=Priz1.this.getData();

                    layoutSicaklik.setText(sicaklik);
                    layoutAkim.setText(akim);
                    layoutGerilim.setText(gerilim);
                    normal = 1;
                } else {
                    rl.setBackgroundColor(Color.RED);
                    ac.setBackground(getResources().getDrawable(R.drawable.tus));
                    Priz1.this.sendData("C");
                    Toast toast = Toast.makeText(Priz1.this, "Priz 1 kapatıldı", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);

                    toast.show();

                    normal = 0;
                }
            }
        });




    }


    public void onResume() {
        super.onResume();
        Log.d("bluetooth1", "...onResume - try connect...");
        BluetoothDevice device = this.btAdapter.getRemoteDevice(address);

        try {
            this.btSocket = this.createBluetoothSocket(device);
        } catch (IOException var7) {
            this.errorExit("Fatal Error", "In onResume() and socket create failed: " + var7.getMessage() + ".");
        }

        this.btAdapter.cancelDiscovery();
        Log.d("bluetooth1", "...Connecting...");

        try {
            this.btSocket.connect();
            Log.d("bluetooth1", "...Connection ok...");
        } catch (IOException var6) {
            try {
                this.btSocket.close();
            } catch (IOException var5) {
                this.errorExit("Fatal Error", "In onResume() and unable to close socket during connection failure" + var5.getMessage() + ".");
            }
        }

        Log.d("bluetooth1", "...Create Socket...");

        try {
            this.outStream = this.btSocket.getOutputStream();
            this.instream = this.btSocket.getInputStream();
        } catch (IOException var4) {
            this.errorExit("Fatal Error", "In onResume() and output stream creation failed:" + var4.getMessage() + ".");
        }

    }

    public void onPause() {
        super.onPause();
        Log.d("bluetooth1", "...In onPause()...");
        if (this.outStream != null) {
            try {
                this.outStream.flush();
            } catch (IOException var3) {
                this.errorExit("Fatal Error", "In onPause() and failed to flush output stream: " + var3.getMessage() + ".");
            }
        }

        try {
            this.btSocket.close();
        } catch (IOException var2) {
            this.errorExit("Fatal Error", "In onPause() and failed to close socket." + var2.getMessage() + ".");
        }

    }

    private void checkBTState() {
        try {
            if (this.btAdapter == null) {
                this.errorExit("Fatal Error", "Bluetooth not support");
            } else if (this.btAdapter.isEnabled()) {
                Log.d("bluetooth1", "...Bluetooth ON...");
                Toast.makeText(Priz1.this, "Bluetooth'a bağlanıldı", Toast.LENGTH_SHORT).show();
            } else {
                Intent enableBtIntent = new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE");
                this.startActivityForResult(enableBtIntent, 1);
                Toast.makeText(Priz1.this, "Bluetooth'u açma izni verin", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {

        }

    }

    private void errorExit(String title, String message) {
        Toast.makeText(this.getBaseContext(), title + " - " + message, Toast.LENGTH_LONG).show();
        this.finish();
    }

    private void sendData(String message) {
        byte[] msgBuffer = message.getBytes();

        Log.d("bluetooth1", "...Send data: " + message + "...");

        try {
            this.outStream.write(msgBuffer);
            outStream.flush();//kendimiz ekledik hata verirse çıkarırız.

        } catch (IOException var5) {
            String msg = "In onResume() and an exception occurred during write: " + var5.getMessage();
            if (address.equals("00:00:00:00:00:00")) {
                msg = msg + ".\n\nUpdate your server address from 00:00:00:00:00:00 to the correct address on line 35 in the java code";
            }

            msg = msg + ".\n\nCheck that the SPP UUID: " + MY_UUID.toString() + " exists on server.\n\n";
            this.errorExit("Fatal Error", msg);
        }


    }


    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {
        if (Build.VERSION.SDK_INT >= 10) {
            try {
                Method e = device.getClass().getMethod("createInsecureRfcommSocketToServiceRecord", new Class[]{UUID.class});
                return (BluetoothSocket) e.invoke(device, new Object[]{MY_UUID});
            } catch (Exception var3) {
                Log.e("bluetooth1", "Could not create Insecure RFComm Connection", var3);
            }
        }

        return device.createRfcommSocketToServiceRecord(MY_UUID);
    }

    private String getData() {

      int buffer = 0;

        /*try {
            buffer= this.instream.read();
        }
        catch (IOException exception) {
            String msg = "get data and an exception occurred during read: " + exception.getMessage();
            if (address.equals("00:14:04:01:21:24")) {
                msg = msg + ".\n\nUpdate your server address from 00:00:00:00:00:00 to the correct address on line 35 in the java code";
            }

            msg = msg + ".\n\nCheck that the SPP UUID: " + MY_UUID.toString() + " exists on server.\n\n";
            this.errorExit("Fatal Error", msg);
        }*/

        Toast.makeText(Priz1.this, "gelen değer türü "+buffer, Toast.LENGTH_SHORT).show();

        String rtn = String.valueOf(buffer);
        return rtn;
    }
}

