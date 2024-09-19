package com.inu.esc;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class DefaultResoningActivity extends AppCompatActivity {
    SpeechRecognizer mRecognizer;
    Intent i;
    KeywordDatabase database;
    TextView tv1;
    String spee;
    ImageView chk_conv;
    Keyword keyword;
    ImageView gokl;
    Vibrator vibrator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.default_resoning);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        vibrator = (Vibrator) getSystemService(this.VIBRATOR_SERVICE);
        tv1 = findViewById(R.id.number1);
        chk_conv = findViewById(R.id.chk_conv);
        database = KeywordDatabase.getDatabase(this);
        gokl = findViewById(R.id.go_kl);
        chk_conv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), conversation.class);
                i.putExtra("str", spee);
                i.putExtra("keyword", keyword.keyword);
                i.putExtra("import", keyword.important);
                startActivity(i);
            }
        });
        gokl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), keyword_list.class);
                startActivity(i);
            }
        });
        startRecognizer();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        startRecognizer();
    }

    RecognitionListener listener = new RecognitionListener() {
        @Override
        public void onReadyForSpeech(Bundle bundle) {

        }

        @Override
        public void onBeginningOfSpeech() {

        }

        @Override
        public void onRmsChanged(float v) {

        }

        @Override
        public void onBufferReceived(byte[] bytes) {

        }

        @Override
        public void onEndOfSpeech() {

        }

        @Override
        public void onError(int i) {
            startRecognizer();
        }

        @Override
        public void onResults(Bundle bundle) {
            try {
                if (bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION) != null) {
                    keyword = new Keyword();
                    List<String> ary = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                    spee = ary.get(0);
                    Log.i("aaaa", spee);
                    keyword = chkSpeech(ary.get(0));

                    switch (keyword.important) {
                        case 5:
                            vibrator.vibrate(1000);
                            break;
                        case 4:
                            vibrator.vibrate(800);
                            break;
                        case 3:
                            vibrator.vibrate(800);
                            break;
                        case 2:
                            vibrator.vibrate(800);
                            break;
                        case 1:
                            vibrator.vibrate(600);
                            break;
                    }
                    //    Toast.makeText(getApplicationContext(), ary.get(0), Toast.LENGTH_SHORT).show();
                    if (keyword == null) {

                    } else {
                        onRecogedKeyword(keyword);
                    }
                }
                mRecognizer.stopListening();
                //  mRecognizer.destroy();
                mRecognizer.destroy();
                startRecognizer();
            } catch (Exception e) {
                mRecognizer.stopListening();
                //  mRecognizer.destroy();
                mRecognizer.destroy();
                startRecognizer();
            }
        }

        @Override
        public void onPartialResults(Bundle bundle) {

        }

        @Override
        public void onEvent(int i, Bundle bundle) {

        }
    };

    public void onRecogedKeyword(Keyword keyword) {
        tv1.setText(keyword.important + "");

    }

    public void startRecognizer() {
        i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName());
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ko-KR");
        mRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        mRecognizer.setRecognitionListener(listener);
        mRecognizer.startListening(i);
    }


    public Keyword chkSpeech(String speech) {
        try {
            Log.d("aa", speech);
            Keyword k;
            if (database.keywordDAO().findKeyword(speech) != null) {
                k = database.keywordDAO().findKeyword(speech).get(0);
                Log.i("aaaa", k.getKeyword());
                return k;
            } else {
                Log.i("aaaa", "is null");
                k = new Keyword();
                return k;
            }
        } catch (Exception e) {
            Log.i("err", e.getMessage());
            startRecognizer();
            //  System.out.println(e.getMessage());
            //  tv1.setText(e.getLocalizedMessage());
            tv1.setText("0");
            throw e;
        }
        //return null;
    }
}