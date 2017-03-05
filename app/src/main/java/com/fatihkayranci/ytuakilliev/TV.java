package com.fatihkayranci.ytuakilliev;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.UUID;

public class TV extends AppCompatActivity {
    RelativeLayout rl;
    private static final String TAG = "bluetooth1";
    private BluetoothAdapter btAdapter = null;
    private BluetoothSocket btSocket = null;
    private OutputStream outStream = null;
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private static String address = "00:14:04:01:21:24";

    Button tus;
    int normal = 0;
    Button t0,t1,t2,t3,t4,t5,t6,t7,t8,t9,p1,p2,v1,v2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv);

        ActionBar Televizyon=getSupportActionBar();
        Televizyon.setTitle("Televizyon");
        Televizyon.setSubtitle("Tv Kumandası");
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.tv);
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.arkaplan_action_tv));



        rl = (RelativeLayout) findViewById(R.id.rl);

        tus = (Button) findViewById(R.id.io);
        this.btAdapter = BluetoothAdapter.getDefaultAdapter();
        this.checkBTState();

        t0 = (Button) findViewById(R.id.t0);
        t1 = (Button) findViewById(R.id.t1);
        t2 = (Button) findViewById(R.id.t2);
        t3 = (Button) findViewById(R.id.t3);
        t4 = (Button) findViewById(R.id.t4);
        t5 = (Button) findViewById(R.id.t5);
        t6 = (Button) findViewById(R.id.t6);
        t7 = (Button) findViewById(R.id.t7);
        t8 = (Button) findViewById(R.id.t8);
        t9 = (Button) findViewById(R.id.t9);
        p1 = (Button) findViewById(R.id.p1);
        p2 = (Button) findViewById(R.id.p2);
        v1 = (Button) findViewById(R.id.v1);
        v2 = (Button) findViewById(R.id.v2);


        t0.setEnabled(false);
        t1.setEnabled(false);
        t2.setEnabled(false);
        t3.setEnabled(false);
        t4.setEnabled(false);
        t5.setEnabled(false);
        t6.setEnabled(false);
        t7.setEnabled(false);
        t8.setEnabled(false);
        t9.setEnabled(false);
        p1.setEnabled(false);
        p2.setEnabled(false);
        v1.setEnabled(false);
        v2.setEnabled(false);






        tus.setOnClickListener(new View.OnClickListener() {

            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                if (normal == 0) {
                    // rl.setBackgroundColor(Color.GREEN);
                    tus.setBackground(getResources().getDrawable(R.drawable.redtus));
                    TV.this.sendData("31212E");

                    t0.setEnabled(true);
                    t1.setEnabled(true);
                    t2.setEnabled(true);
                    t3.setEnabled(true);
                    t4.setEnabled(true);
                    t5.setEnabled(true);
                    t6.setEnabled(true);
                    t7.setEnabled(true);
                    t8.setEnabled(true);
                    t9.setEnabled(true);
                    p1.setEnabled(true);
                    p2.setEnabled(true);
                    v1.setEnabled(true);
                    v2.setEnabled(true);
                    Toast.makeText(TV.this.getBaseContext(), "TV on", Toast.LENGTH_SHORT).show();
                    normal = 1;
                } else {
                    //  rl.setBackgroundColor(Color.RED);

                    tus.setBackground(getResources().getDrawable(R.drawable.tus2));
                    normal = 0;
                    TV.this.sendData("31212E");

                    t0.setEnabled(false);
                    t1.setEnabled(false);
                    t2.setEnabled(false);
                    t3.setEnabled(false);
                    t4.setEnabled(false);
                    t5.setEnabled(false);
                    t6.setEnabled(false);
                    t7.setEnabled(false);
                    t8.setEnabled(false);
                    t9.setEnabled(false);
                    p1.setEnabled(false);
                    p2.setEnabled(false);
                    v1.setEnabled(false);
                    v2.setEnabled(false);
                    Toast.makeText(TV.this.getBaseContext(), "TV Off", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }

    public void setting(View view){
        String kanal;

        switch (view.getId()){
            case R.id.t0:  TV.this.sendData("3120E");
                Toast.makeText(TV.this, "0", Toast.LENGTH_SHORT).show();
                break;
            case R.id.t1: TV.this.sendData("3121E");Toast.makeText(TV.this, "1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.t2: TV.this.sendData("3122E");Toast.makeText(TV.this, "2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.t3:TV.this.sendData("3123E");Toast.makeText(TV.this, "3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.t4:TV.this.sendData("3124E");Toast.makeText(TV.this, "4", Toast.LENGTH_SHORT).show();
                break;
            case R.id.t5:TV.this.sendData("3125E");Toast.makeText(TV.this, "5", Toast.LENGTH_SHORT).show();
                break;
            case R.id.t6:TV.this.sendData("3126E");Toast.makeText(TV.this, "6", Toast.LENGTH_SHORT).show();
                break;
            case R.id.t7:TV.this.sendData("3127E");Toast.makeText(TV.this, "7", Toast.LENGTH_SHORT).show();
                break;
            case R.id.t8:TV.this.sendData("3128E");Toast.makeText(TV.this, "8", Toast.LENGTH_SHORT).show();
                break;
            case R.id.t9:TV.this.sendData("3129E");Toast.makeText(TV.this, "9", Toast.LENGTH_SHORT).show();
                break;
            case R.id.p1:TV.this.sendData("31217E");Toast.makeText(TV.this, "p+", Toast.LENGTH_SHORT).show();
                break;
            case R.id.p2:TV.this.sendData("31216E");Toast.makeText(TV.this, "p-", Toast.LENGTH_SHORT).show();
                break;
            case R.id.v1:TV.this.sendData("31219E");Toast.makeText(TV.this, "v+", Toast.LENGTH_SHORT).show();
                break;
            case R.id.v2:TV.this.sendData("31218E");Toast.makeText(TV.this, "v-", Toast.LENGTH_SHORT).show();
                break;
            default:break;
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
        if (this.btAdapter == null) {
            this.errorExit("Fatal Error", "Bluetooth not support");
        } else if (this.btAdapter.isEnabled()) {
            Log.d("bluetooth1", "...Bluetooth ON...");
            Toast.makeText(TV.this, "Bluetooth'a bağlanıldı", Toast.LENGTH_SHORT).show();


        } else {
            Intent enableBtIntent = new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE");
            this.startActivityForResult(enableBtIntent, 1);
            Toast.makeText(TV.this, "Bluetooth'u açma izni verin", Toast.LENGTH_SHORT).show();

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
            outStream.flush();

        } catch (IOException var5) {
            String msg = "In onResume() and an exception occurred during write: " + var5.getMessage();
            if (address.equals("00:00:00:00:00:00")) {
                msg = msg + ".\n\nUpdate your server address from 00:00:00:00:00:00 to the correct address on line 35 in the java code";
            }

            msg = msg + ".\n\nCheck that the SPP UUID: " + MY_UUID.toString() + " exists on server.\n\n";
            this.errorExit("Fatal Error", msg);
        }


    }
}