package com.example.suraj.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class theme extends AppCompatActivity {

    public static int x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);
    }


    public void start (View v)
    {
        RadioButton r = (RadioButton) findViewById(R.id.movies);
        if (r.isChecked()==true) x=1;

        r = (RadioButton) findViewById(R.id.cricketers);
        if (r.isChecked()==true) x=2;

        r = (RadioButton) findViewById(R.id.animals);
        if (r.isChecked()==true) x=3;

        r = (RadioButton) findViewById(R.id.countries);
        if (r.isChecked()==true) x=4;

        r = (RadioButton) findViewById(R.id.tvseries);
        if (r.isChecked()==true) x=5;


        Intent i = new Intent(this,secActivity.class);
        i.putExtra("data",x);
        startActivity(i);

    }
}
