package com.example.josh.assignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;
import android.media.MediaPlayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Switch music_switch = (Switch)  findViewById(R.id.music_switch);
        final MediaPlayer mp3 = MediaPlayer.create(this, R.raw.jeopardy);
        music_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mp3.isPlaying() == true) {
                    mp3.pause();
                } else {
                    mp3.start();
                    mp3.setLooping(true);
                }
            }
        });
    }

    public void playTrivia(View view) {
        Intent intent = new Intent(this, TriviaActivity.class);
        startActivity(intent);
    }

    public void add_word_onClick(View view){
        Intent intent = new Intent(this, addtermActivity.class);
        startActivity(intent);
    }

    public void score_history(View view){
        Intent intent = new Intent(this, ScoreHistoryActivity.class);
        startActivity(intent);
    }



}
