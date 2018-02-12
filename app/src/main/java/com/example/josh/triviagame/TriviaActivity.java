/*
* By: Joshua Rodstein
* Assignment1 - CS1699
* PItt: jor94@pitt.edu
* ID: 4021607
*
* */


package com.example.josh.triviagame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class TriviaActivity extends AppCompatActivity {

    TextView term;
    String ans;
    ListView list_triv;
    ProgressBar bar;
    GlossaryTable glossary;
    ArrayList gList;
    int qCounter = 0, correct= 0, barProg = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);
        byte[] bytes = new byte[1024];
        list_triv = findViewById(R.id.triv_list);
        term = findViewById(R.id.term_text);
        bar = (ProgressBar) findViewById(R.id.progressBar);
        bar.setMax(5);

        InputStream is1 = getResources().openRawResource(R.raw.glossary);

        try {
            glossary = new GlossaryTable(is1);
            try {
                FileInputStream is2 = openFileInput("new_gloss.txt");
                is2.read(bytes);
                is2.close();
                String string = new String(bytes);
                glossary.addGloss(string);
            } catch (FileNotFoundException fn){

            }
            generateQuiz();
        }catch(Exception e){
            Toast.makeText(this, "Error: " + e + correct, Toast.LENGTH_SHORT).show();
            finish();
        }

        list_triv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPosition = position;
                String  itemValue = (String) list_triv.getItemAtPosition(position);
                // Show Alert

                if(itemValue.contentEquals(ans)){
                    correct++;
                    Toast.makeText(getApplicationContext(), "Correct Answer: Your score is "
                            + correct, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong Answer!",
                            Toast.LENGTH_SHORT).show();
                }

                qCounter++;
                barProg++;
                bar.setProgress(barProg);
                if(qCounter == 5){
                    saveScore(correct);
                    finish();
                } else {
                    generateQuiz();
                }
            }
        });
    }

    /*
    * Save score by creating file in Android internal storage
    * */
    protected void saveScore(int correct){
        int score = correct * 20;
        try {
            FileOutputStream fOut = openFileOutput("score_history.txt",MODE_APPEND);
            String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
            fOut.write((currentDateTimeString + "\t" + score + "\n").getBytes());
            fOut.close();
        } catch(IOException io) {
            Log.e("ERROR", io.toString());
        }
    }

    /*
    * Load, shuffle and read from existing glossary files.
    * */
    protected void generateQuiz(){
        Random rand = new Random();
        if(glossary != null){
            int t = rand.nextInt(5);
            glossary.shuffle();
            String[] qAry = {
                    glossary.getDefinitionByIndex(0),
                    glossary.getDefinitionByIndex(1),
                    glossary.getDefinitionByIndex(2),
                    glossary.getDefinitionByIndex(3),
                    glossary.getDefinitionByIndex(4)
            };
            term.setText(glossary.getTermByIndex(t));
            ans = glossary.getDefinitionByIndex(t);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, qAry);
            list_triv.setAdapter(adapter);
        }
    }
}
