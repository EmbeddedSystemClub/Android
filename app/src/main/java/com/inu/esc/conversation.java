package com.inu.esc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.security.Key;
import java.util.List;

public class conversation extends AppCompatActivity {
    TextView all_speak;
    TextView import_speak;
    KeywordDatabase keywordDatabase;
    ConvDatabase convDatabase;
    BroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_conversation);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        all_speak = findViewById(R.id.all_speak);
        import_speak  = findViewById(R.id.import_speak);
        keywordDatabase = KeywordDatabase.getDatabase(getApplicationContext());
        convDatabase = ConvDatabase.getDatabase(getApplicationContext());
        List<Conv> convList = convDatabase.convDAO().getAllConv();
        String str = "";
        for(Conv conv : convList) {
            str += conv.getMsg()+"\n";
        }
        all_speak.setText(str);

        if(keywordDatabase.keywordDAO().findKeyword(convList.get(0).getMsg()).size()>0) {
            Keyword keyword = keywordDatabase.keywordDAO().findKeyword(convList.get(0).getMsg()).get(0);

            import_speak.setText("키워드 : " + keyword.getKeyword() + "\n" + "중요도 : " + keyword.getImportant());
        }
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.i("received","abcd");
                List<Conv> convList = convDatabase.convDAO().getAllConv();
                String str = "";
                for(Conv conv : convList) {
                    str += conv.getMsg()+"\n";
                }
                all_speak.setText(str);

                if(keywordDatabase.keywordDAO().findKeyword(convList.get(0).getMsg()).size()> 0) {

                    Keyword keyword = keywordDatabase.keywordDAO().findKeyword(convList.get(0).getMsg()).get(0);
                    import_speak.setText("키워드 : " + keyword.getKeyword() + "\n" + "중요도 : " + keyword.getImportant());
                }
            }
        };
        IntentFilter filter = new IntentFilter(getString(R.string.inten));
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(receiver,filter);
        Intent i = getIntent();


    }
    public Keyword chkSpeech(String speech) {
        try {
            Log.d("aa", speech);
            Keyword k;
            if (keywordDatabase.keywordDAO().findKeyword(speech) != null) {
                k = keywordDatabase.keywordDAO().findKeyword(speech).get(0);
                Log.i("aaaa", k.getKeyword());
                return k;
            } else {
                Log.i("aaaa", "is null");
                k = new Keyword();
                return k;
            }
        } catch (Exception e) {
            return new Keyword();
        }
    }
}