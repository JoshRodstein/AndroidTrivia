package com.example.josh.assignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;

public class addtermActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addterm);
    }


    public void ok_onClick(View view){
        EditText term = (EditText) findViewById(R.id.addterm_edittext);
        EditText def = (EditText) findViewById(R.id.adddef_edittext);

        CharSequence termString = term.getText().toString();
        CharSequence defString = def.getText().toString();

        addToGlossary(termString, defString);

        finish();
    }

    public void addToGlossary(CharSequence term, CharSequence def){
        String t = (String)term;
        String d = (String)def;


        try {
            FileOutputStream fOut = openFileOutput("new_gloss.txt",MODE_APPEND);
            fOut.write((t+System.getProperty("line.separator")).getBytes());
            fOut.write((d+System.getProperty("line.separator")).getBytes());
            fOut.close();
            Toast.makeText(this, (String)term + def, Toast.LENGTH_SHORT).show();
        } catch(IOException io) {
            Log.e("ERROR", io.toString());
        }

    }

}
