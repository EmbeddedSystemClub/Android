package com.inu.esc;

import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.github.douglasjunior.bluetoothclassiclibrary.BluetoothConfiguration;
import com.github.douglasjunior.bluetoothclassiclibrary.BluetoothService;
import com.github.douglasjunior.bluetoothclassiclibrary.BluetoothStatus;

import java.util.UUID;

public class Login extends AppCompatActivity {
    ImageView img;
    BluetoothService mService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        img = findViewById(R.id.login_btn);
        BluetoothConfiguration config = new BluetoothConfiguration();
        config.context = getApplicationContext();
        config.bluetoothServiceClass = BluetoothService.class;
        config.bufferSize = 1024;
        config.characterDelimiter = '\n';
        config.deviceName = "Phone";
        config.callListenersInMainThread = true;
        config.uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"); // Required
        BluetoothService.init(config);
        mService = BluetoothService.getDefaultInstance();
        mService.setOnScanCallback(new BluetoothService.OnBluetoothScanCallback() {
            @Override
            public void onDeviceDiscovered(BluetoothDevice device, int rssi) throws SecurityException {
                if (device.getName()!=null) {
                    Toast.makeText(Login.this, "블루투스 연결됨", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onStartScan() {

            }

            @Override
            public void onStopScan() {

            }
        });
        mService.startScan();
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),choose_mode.class);
                startActivity(i);
            }
        });

    }
}