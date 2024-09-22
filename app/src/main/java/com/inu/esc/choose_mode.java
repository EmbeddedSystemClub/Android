package com.inu.esc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class choose_mode extends AppCompatActivity {

    ImageView chooseModeDefault;
    ImageView chooseModeImportant;
    ImageView word;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_choose_mode);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        word = findViewById(R.id.setword);
        chooseModeDefault = findViewById(R.id.choose_mode_default);
        chooseModeImportant = findViewById(R.id.choose_mode_important);
        chooseModeDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  i = new Intent(getApplicationContext(), conversation.class);
                startActivity(i);
            }
        });
        word.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), reg_words.class);
                startActivity(i);
            }
        });
        chooseModeImportant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), jipjung.class);
                startActivity(i);
            }
        });

    }
}