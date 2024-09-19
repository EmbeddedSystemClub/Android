package com.inu.esc;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.github.douglasjunior.bluetoothclassiclibrary.BluetoothService;
import com.github.douglasjunior.bluetoothclassiclibrary.BluetoothStatus;

public class keyword_list extends AppCompatActivity {
    TextView kltv;
    BluetoothService mService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_keyword_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        kltv = findViewById(R.id.kltv);
        mService = BluetoothService.getDefaultInstance();
        KeywordDatabase db = KeywordDatabase.getDatabase(this);
        mService.setOnEventCallback(new BluetoothService.OnBluetoothEventCallback() {
            @Override
            public void onDataRead(byte[] buffer, int length) {

            }

            @Override
            public void onStatusChange(BluetoothStatus status) {

            }

            @Override
            public void onDeviceName(String deviceName) {

            }

            @Override
            public void onToast(String message) {

            }

            @Override
            public void onDataWrite(byte[] buffer) {
                String data = buffer.toString();
                String[] strs = data.split(";");
                for(String dt :strs) {
                   String[] kw =  dt.split(".");
                   Keyword keyword = new Keyword();
                   keyword.setImportant(Integer.parseInt(kw[0]));
                   keyword.setKeyword(kw[1]);
                   db.keywordDAO().addKeyword(keyword);
                }
            }
        });
        String str = new String();
        for(Keyword k:db.keywordDAO().getAllKeywords()) {
            str = str+"\n"+k.keyword;
        }
        kltv.setText(str);
    }
}