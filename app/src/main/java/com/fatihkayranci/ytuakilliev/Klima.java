package com.fatihkayranci.ytuakilliev;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
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
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.UUID;

public class Klima extends AppCompatActivity {

    private BluetoothAdapter btAdapter = null;
    private BluetoothSocket btSocket = null;
    private OutputStream outStream = null;
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private static String address = "00:14:04:01:21:24";
    RelativeLayout rl;
    Button tus;
    int derece = 22;
    Toast toast;
    int normal = 0;
    TextView kl_mode, kl_fan, kl_temp;
    Button temp1, temp2, fan1, fan2, cool, warm, hum, face;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_klima);

        ActionBar actionBar=getSupportActionBar();

        actionBar.setTitle("Klima"); //action Bar
        actionBar.setSubtitle("Klima Kontrolü");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.klima);
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.arkaplan_action_kl));



        rl = (RelativeLayout) findViewById(R.id.rl);

        fan1 = (Button) findViewById(R.id.fan);
        fan2 = (Button) findViewById(R.id.fan2);
        temp1 = (Button) findViewById(R.id.temp);
        temp2 = (Button) findViewById(R.id.temp2);
        face = (Button) findViewById(R.id.face);
        hum = (Button) findViewById(R.id.hum);
        cool = (Button) findViewById(R.id.cool);
        warm = (Button) findViewById(R.id.warm);

        fan1.setEnabled(false);
        fan2.setEnabled(false);
        temp2.setEnabled(false);
        temp1.setEnabled(false);
        cool.setEnabled(false);
        hum.setEnabled(false);
        face.setEnabled(false);
        warm.setEnabled(false);

        tus = (Button) findViewById(R.id.io);
        kl_fan = (TextView) findViewById(R.id.kl_fan);
        kl_mode = (TextView) findViewById(R.id.kl_mode);
        kl_temp = (TextView) findViewById(R.id.kl_temp);



        this.btAdapter = BluetoothAdapter.getDefaultAdapter();
        this.checkBTState();


       /* warm= (Button) findViewById(R.id.warm);
        warm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                derece--;
                Klima.this.sendData("Buraya klima soğıutma komtu gelecek");
                Toast toast = Toast.makeText(Klima.this, "soğuma kullamıldı: "+derece, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
                toast.show();
            }
        });
*/

        tus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (normal == 0) {
                    fan1.setEnabled(true);
                    fan2.setEnabled(true);
                    temp2.setEnabled(true);
                    temp1.setEnabled(true);
                    cool.setEnabled(true);
                    hum.setEnabled(true);
                    face.setEnabled(true);
                    warm.setEnabled(true);
                    //rl.setBackgroundColor(Color.GREEN);
                    tus.setBackground(getResources().getDrawable(R.drawable.redtus));

                    Klima.this.sendData("142607175E");
                    createACValue(18, 1, "COOL");
                    Toast toast = Toast.makeText(Klima.this, "Klima Açıldı", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
                    toast.show();


                    //Toast.makeText(Klima.this.getBaseContext(), "Turn on LED", Toast.LENGTH_LONG).show();
                    //Toast.makeText(getApplicationContext(),"bu tost",Toast.LENGTH_LONG).show();
                    normal = 1;
                } else {
                    //rl.setBackgroundColor(Color.RED);
                    tus.setBackground(getResources().getDrawable(R.drawable.tus));
                    Klima.this.sendData("142607175E");

                    createACValue(18, 1, "COOL");
                    Toast toast = Toast.makeText(Klima.this, "Klima Kapandı", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
                    toast.show();

                    fan1.setEnabled(false);
                    fan2.setEnabled(false);
                    temp2.setEnabled(false);
                    temp1.setEnabled(false);
                    cool.setEnabled(false);
                    hum.setEnabled(false);
                    face.setEnabled(false);
                    warm.setEnabled(false);
                    //writen

                    normal = 0;
                }
            }
        });


    }

    int temp = 18, fan = 1; /////////////////

    public void setting(View view) {
        String mode = "COOL";
        int dec = 0;
        switch (view.getId()) {

            case R.id.temp:
                if (temp >= 18 && temp < 30) {

                    dec = createACValue(++temp, fan, mode);
                }
              //  Toast.makeText(Klima.this, "temp: " + temp + "FAN: " + fan + "mode: " + mode + "deger: " + dec, Toast.LENGTH_LONG).show();
                break;
            case R.id.temp2:
                if (temp > 18 && temp <= 30) {
                    dec = createACValue(--temp, fan, mode);
                }
              //  Toast.makeText(Klima.this, "temp: " + temp + "FAN: " + fan + "mode: " + mode + "deger: " + dec, Toast.LENGTH_LONG).show();
                break;
            case R.id.fan:
                if (fan >= 1 && fan < 4) {
                    dec = createACValue(temp, ++fan, mode);
                }
             //  Toast.makeText(Klima.this, "temp: " + temp + "FAN: " + fan + "mode: " + mode + "deger: " + dec, Toast.LENGTH_LONG).show();
                break;
            case R.id.fan2:
                if (fan > 1 && fan <= 4) {
                    dec = createACValue(temp, --fan, mode);
                }
              //  Toast.makeText(Klima.this, "temp: " + temp + "FAN: " + fan + "mode: " + mode + "deger: " + dec, Toast.LENGTH_LONG).show();
                break;
            case R.id.cool:
                dec = createACValue(temp, fan, "COOL");
                mode = "COOL";
             //   Toast.makeText(Klima.this, "temp: " + temp + "FAN: " + fan + "mode: " + mode + "deger: " + dec, Toast.LENGTH_LONG).show();
                break;
            case R.id.face:
                dec = createACValue(temp, ++fan, "FACE");
                mode = "FACE";
             //   Toast.makeText(Klima.this, "temp: " + temp + "FAN: " + fan + "mode: " + mode + "deger: " + dec, Toast.LENGTH_LONG).show();
                break;
            case R.id.hum:
                dec = createACValue(temp, ++fan, "HUM");
                mode = "HUM";
             //   Toast.makeText(Klima.this, "temp: " + temp + "FAN: " + fan + "mode: " + mode + "deger: " + dec, Toast.LENGTH_LONG).show();
                break;
            case R.id.warm:
                dec = createACValue(temp, ++fan, "WARM");
                mode = "WARM";
            //    Toast.makeText(Klima.this, "temp: " + temp + "FAN: " + fan + "mode: " + mode + "deger: " + dec, Toast.LENGTH_LONG).show();
                break;

            default:
                break;
        }
        //dec değişkeni yuklarıda swicth;'de createACValue fonksiyonundan gelen değer

        String tempDEC = String.valueOf(dec);//dec Fonksiyonu Stringe Dönüştürüldü, oluşan string ifadeye "E" eklendi
        Log.d("SSSSSS", tempDEC);//sadece monitorden temdec değişeknin değerini görmek için oluşturuldu
        tempDEC = tempDEC + "E";//dec Fonksiyonu Stringe Dönüştürüldü, oluşan string ifadeye "E" eklendi
        Log.d("String DEĞERİ", tempDEC);//sadece monitorden temdec değişeknin değerini görmek için oluşturuldu
        sendData(tempDEC);//bluetooth cihazina oluşan komut gönderildi
        //Toast.makeText(Klima.this, "gonderilen komut şu: " + tempDEC, Toast.LENGTH_SHORT).show();
        Toast toast = Toast.makeText(Klima.this, "Gönderilen komut şu: "+ tempDEC, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
        toast.show();



    }

    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {//////////////
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


    public void onResume() {////////////////////////////
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

    public void onPause() {/////////////////////
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

    private void checkBTState() {///////////////////////////
        try {
            if (this.btAdapter == null) {
                this.errorExit("Fatal Error", "Bluetooth not support");
            } else if (this.btAdapter.isEnabled()) {
                Log.d("bluetooth1", "...Bluetooth ON...");
                Toast.makeText(Klima.this, "Bluetooth'a bağlanıldı", Toast.LENGTH_SHORT).show();
            } else {
                Intent enableBtIntent = new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE");
                this.startActivityForResult(enableBtIntent, 1);
                Toast.makeText(Klima.this, "Bluetooth'u açma izni verin", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {

        }

    }

    private void errorExit(String title, String message) {/////////////////////////
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




    private int createACValue(int temp, int fan, String mode) {

        kl_fan.setText(Integer.toString(fan));
        kl_temp.setText(Integer.toString(temp));
        kl_mode.setText(mode);
        int dec = 0;
        int[] binary = new int[28]; //{ 1,0,0,0,1};
        binary[0] = 1;
        binary[1] = 0;
        binary[2] = 0;
        binary[3] = 0;
        binary[4] = 1;

        switch (mode) {
            case "COOL":
                binary[12] = 1;
                break;
            case "FACE":
                binary[12] = 1;
                binary[14] = 1;
                binary[15] = 1;
                break;
            case "HUM":
                binary[12] = 1;
                binary[15] = 1;
                break;
            case "WARM":
                binary[13] = 1;
                break;
            default:
                break;
        }

        //for temperature's digits (convert dec 2 bin)
        for (int i = 4; i > 0; i--) {
            binary[15 + i] = temp % 2;
            temp /= 2;
        }
        // for fan's digits
        switch (fan) {
            case 2:
                binary[22] = 1;
                break;
            case 3:
                binary[21] = 1;
                break;
            case 4:
                binary[21] = 1;
                binary[23] = 1;
                break;
            default:
                break;
        }

        // for sum digits
        for (int i = 27; i > 23; i--) {
            binary[i] = binary[i] + binary[i - 4] + binary[i - 8] + binary[i - 12];

            if (binary[i] == 2) {
                binary[i] = 0;
                if (i != 24)
                    binary[i - 1] = 1;
            }
            if (binary[i] == 3) {
                binary[i] = 1;
                if (i != 24)
                    binary[i - 1] = 1;
            }
            if (binary[i] == 4 && i != 24) {
                binary[i] = 0;
                binary[i - 1] = 0;
                if (i > 25)
                    binary[i + 2] = 1;
            }
        }
        // convert bin 2 dec
        for (int i = 27; i >= 0; i--) {
            dec = (int) (dec + Math.pow(2, 27 - i) * binary[i]);
        }
        return dec;
    }
}





