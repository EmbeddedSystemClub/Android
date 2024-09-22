package com.inu.esc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.github.douglasjunior.bluetoothclassiclibrary.BluetoothService;
import com.github.douglasjunior.bluetoothclassiclibrary.BluetoothWriter;

public class reg_words extends AppCompatActivity {
    EditText keyword;
    EditText important;
    ImageView btnReg;

    KeywordDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reg_words);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
  //     mService = BluetoothService.getDefaultInstance();
        db = KeywordDatabase.getDatabase(this);
        keyword = findViewById(R.id.reg_keyword_et);
        important = findViewById(R.id.reg_important_et);
        btnReg = findViewById(R.id.btn_reg);
    //    bluetoothWriter = new BluetoothWriter(mService);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(getApplicationContext(),BluetootConServ.class));
                Keyword keyword1 = new Keyword();
                keyword1.setKeyword(keyword.getText().toString());
                keyword1.setImportant(Integer.parseInt(important.getText().toString()));
                db.keywordDAO().addKeyword(keyword1);
                //bluetoothWriter.write(keyword1.getImportant()+"."+keyword1.getKeyword());
                Toast.makeText(reg_words.this, "등록되었습니다.", Toast.LENGTH_SHORT).show();
                startService(new Intent(getApplicationContext(),BluetootConServ.class));
            }
        });

    }
}