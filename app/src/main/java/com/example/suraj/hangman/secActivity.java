package com.example.suraj.hangman;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static android.R.attr.value;

public class secActivity extends AppCompatActivity {




    ArrayList<String> words = new ArrayList<>();

    String questionWord;
    char modifiedQuestion[] = new char[50];
    int chances = 0;
    int value;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
           value = extras.getInt("data");

            Log.d("dghs", ""+value);

            initialList();


        }

    }


    public void initialList()
    {
        words.add("acres");
        words.add("adult");
        words.add("advice");
        words.add("arrangement");
        words.add("attempt");
        words.add("august");
        words.add("autumn");
        words.add("border");
        words.add("apple");

        words.addAll(Arrays.asList("the terminator","harry potter and the prisoner of azkaban","inception","kung fu hustle","ice age","madagascar",
                "the shawshank redemption","la la land","home alone","the transformers","the pursuit of happyness","star wars","avatar","titanic",
                "jurassic world","the dark knight","finding nemo","the lion king","iron man","frozen","happy feet","the jungle book","despicable me"
                ,"lord of the rings","skyfall","interstellar","gravity","the da vinci code"));


        words.addAll(Arrays.asList("sachin tendulkar","sunil gavaskar","rahul dravid","sourav ganguly","adam gilchrist",
                "ricky ponting","greame smith","herschelle gibbs","mark boucher","shaun pollock","ishant sharma","glenn mcgrath",
                "clive lloyd","chris gayle","brett lee","virat kohli","ms dhoni","vernon philander","dale steyn","morne morkel","imran tahir",
                "kagiso rabada","inzamam ul haq","yasir arafat","shahid afridi","garfield sobers","brendon mccullum"
        ));

        words.addAll(Arrays.asList("dog","cat","lion","tiger","monkey","leopard","cheetah","ox","hippopotamus","rhinoceros",
                "buffalo","cow","frog","pig","rabbit","goat","sheep","donkey","horse","hyena","panther","mouse","squirrel"
        ));

        words.addAll(Arrays.asList("india","australia","pakistan","sri lanka","china","japan","afghanistan","oman","france",
                "spain","russia","south africa","italy","united kingdom","germany","malaysia","mexico","austria","singapore",
                "canada","netherlands","ireland","iceland","greenland","sweden","switzerland","argentina","brazil"
        ));

        words.addAll(Arrays.asList("sherlock","game of thrones","the mentalist","fringe","friends","how i met your mother","mr robot",
                "the office","breaking bad","westworld","white collar","criminal minds","the walking dead","seinfield","pretty little liars",
                "the flash","arrow","supergirl","homeland","the big bang theory","two and a half men","legends of tomorrow","house of cards",
                "prison break","dexter","the vampire diaries","how to get away with murder","supernatural","suits","black mirror",
                "the blacklist"
        ));

        wordGENERATOR();
    }

    public void wordGENERATOR()
    {

        int noOfWords = words.size();
        int i;

        Random rn = new Random();

       if (value==1) questionWord = words.get(rn.nextInt(28)+9);
       else if (value==2) questionWord = words.get(rn.nextInt(27)+37);
       else if (value==3) questionWord = words.get(rn.nextInt(23)+64);
       else if (value==4) questionWord = words.get(rn.nextInt(28)+87);
       else if (value==5) questionWord = words.get(rn.nextInt(31)+115);

        for(i=0;i<questionWord.length();i++)
            if(questionWord.charAt(i)==' ') modifiedQuestion[i]=' ';
            else modifiedQuestion[i]='-';


        TextView T = (TextView)findViewById(R.id.word);
        T.setText(modifiedQuestion,0,questionWord.length());
    }

    public void playAgain (View v)
    {
        wordGENERATOR();
        resetButtons();
        hideImage();
        resetChances();
        enableButtons();

        TextView t = (TextView) findViewById(R.id.word);
        t.setTextColor(Color.GRAY);

    }

    public void onClick(View v)
    {
        Button b = (Button)v;
        b.setVisibility(View.INVISIBLE);

        String s = b.getResources().getResourceName(b.getId());

        checkLetter(s.charAt(s.length()-1));
    }


    public void checkLetter(char letter)
    {

        int counter =0;int i,j;
        for(i=0,j=0;i<questionWord.length();i++,j+=2)
        {

            if(letter == questionWord.charAt(i))
            {
                modifiedQuestion[i]=letter;
                counter++;
            }
        }

        win();

        if(counter==0) {
            chances++;
            unHideImage();
        }

        if (chances==5) {

            lose();
        }

        else displayLetter();


    }

    public void displayLetter()
    {
        TextView T = (TextView)findViewById(R.id.word);
        T.setText(modifiedQuestion,0,questionWord.length());
    }

    public void unHideImage()
    {
        ImageView unhide;
        switch(chances)
        {
            case 1: unhide = (ImageView)findViewById(R.id.h2);
                unhide.setVisibility(View.VISIBLE);
                break;

            case 2: unhide = (ImageView)findViewById(R.id.h3);
                unhide.setVisibility(View.VISIBLE);
                break;

            case 3: unhide = (ImageView)findViewById(R.id.h4);
                unhide.setVisibility(View.VISIBLE);
                break;

            case 4: unhide = (ImageView)findViewById(R.id.h5);
                unhide.setVisibility(View.VISIBLE);
                break;

            case 5: unhide = (ImageView)findViewById(R.id.h6);
                unhide.setVisibility(View.VISIBLE);
                break;
        }

    }

    public void hideImage()
    {
        ImageView unhide;

        unhide = (ImageView)findViewById(R.id.h2);
        unhide.setVisibility(View.INVISIBLE);


        unhide = (ImageView)findViewById(R.id.h3);
        unhide.setVisibility(View.INVISIBLE);


        unhide = (ImageView)findViewById(R.id.h4);
        unhide.setVisibility(View.INVISIBLE);


        unhide = (ImageView)findViewById(R.id.h5);
        unhide.setVisibility(View.INVISIBLE);


        unhide = (ImageView)findViewById(R.id.h6);
        unhide.setVisibility(View.INVISIBLE);


    }

    public void resetChances ()
    {
        chances=0;
    }

    public void resetButtons()
    {
        Button b = (Button)findViewById(R.id.a);
        b.setVisibility(View.VISIBLE);

        b = (Button)findViewById(R.id.b);
        b.setVisibility(View.VISIBLE);

        b = (Button)findViewById(R.id.c);
        b.setVisibility(View.VISIBLE);

        b = (Button)findViewById(R.id.d);
        b.setVisibility(View.VISIBLE);

        b = (Button)findViewById(R.id.e);
        b.setVisibility(View.VISIBLE);

        b = (Button)findViewById(R.id.f);
        b.setVisibility(View.VISIBLE);

        b = (Button)findViewById(R.id.g);
        b.setVisibility(View.VISIBLE);

        b = (Button)findViewById(R.id.h);
        b.setVisibility(View.VISIBLE);

        b = (Button)findViewById(R.id.i);
        b.setVisibility(View.VISIBLE);

        b = (Button)findViewById(R.id.j);
        b.setVisibility(View.VISIBLE);

        b = (Button)findViewById(R.id.k);
        b.setVisibility(View.VISIBLE);

        b = (Button)findViewById(R.id.l);
        b.setVisibility(View.VISIBLE);

        b = (Button)findViewById(R.id.m);
        b.setVisibility(View.VISIBLE);

        b = (Button)findViewById(R.id.n);
        b.setVisibility(View.VISIBLE);


        b = (Button)findViewById(R.id.o);
        b.setVisibility(View.VISIBLE);

        b = (Button)findViewById(R.id.p);
        b.setVisibility(View.VISIBLE);

        b = (Button)findViewById(R.id.q);
        b.setVisibility(View.VISIBLE);

        b = (Button)findViewById(R.id.r);
        b.setVisibility(View.VISIBLE);

        b = (Button)findViewById(R.id.s);
        b.setVisibility(View.VISIBLE);

        b = (Button)findViewById(R.id.t);
        b.setVisibility(View.VISIBLE);

        b = (Button)findViewById(R.id.u);
        b.setVisibility(View.VISIBLE);

        b = (Button)findViewById(R.id.v);
        b.setVisibility(View.VISIBLE);

        b = (Button)findViewById(R.id.w);
        b.setVisibility(View.VISIBLE);

        b = (Button)findViewById(R.id.x);
        b.setVisibility(View.VISIBLE);

        b = (Button)findViewById(R.id.y);
        b.setVisibility(View.VISIBLE);

        b = (Button)findViewById(R.id.z);
        b.setVisibility(View.VISIBLE);
    }

    public void win ()
    {

        String s = new String(modifiedQuestion);

        if (questionWord.equals(s.substring(0,questionWord.length())))
        {
            TextView T = (TextView) findViewById(R.id.word);
            T.setTextColor(Color.GREEN);

            Context context=getApplicationContext();
            int duration = Toast.LENGTH_LONG;
            CharSequence text = "Congrats! You are alive!";

            Toast t =  Toast.makeText(context,text,duration);
            t.show();

            disableButtons();
        }

    }

    public void lose ()
    {

        TextView t1 = (TextView) findViewById(R.id.word);
        t1.setText(questionWord);
        t1.setTextColor(Color.RED);

        Context context=getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        CharSequence text = "Sorry! You're dead!";

        Toast t =  Toast.makeText(context,text,duration);
        t.show();

        disableButtons();
    }


    public void disableButtons()
    {
        Button b = (Button)findViewById(R.id.a);
        b.setClickable(false);
        b.setTextColor(Color.GRAY);

        b = (Button)findViewById(R.id.b);
        b.setClickable(false);
        b.setTextColor(Color.GRAY);

        b = (Button)findViewById(R.id.c);
        b.setClickable(false);
        b.setTextColor(Color.GRAY);

        b = (Button)findViewById(R.id.d);
        b.setClickable(false);
        b.setTextColor(Color.GRAY);

        b = (Button)findViewById(R.id.e);
        b.setClickable(false);
        b.setTextColor(Color.GRAY);

        b = (Button)findViewById(R.id.f);
        b.setClickable(false);
        b.setTextColor(Color.GRAY);

        b = (Button)findViewById(R.id.g);
        b.setClickable(false);
        b.setTextColor(Color.GRAY);

        b = (Button)findViewById(R.id.h);
        b.setClickable(false);
        b.setTextColor(Color.GRAY);

        b = (Button)findViewById(R.id.i);
        b.setClickable(false);
        b.setTextColor(Color.GRAY);

        b = (Button)findViewById(R.id.j);
        b.setClickable(false);
        b.setTextColor(Color.GRAY);

        b = (Button)findViewById(R.id.k);
        b.setClickable(false);
        b.setTextColor(Color.GRAY);

        b = (Button)findViewById(R.id.l);
        b.setClickable(false);
        b.setTextColor(Color.GRAY);

        b = (Button)findViewById(R.id.m);
        b.setClickable(false);
        b.setTextColor(Color.GRAY);

        b = (Button)findViewById(R.id.n);
        b.setClickable(false);
        b.setTextColor(Color.GRAY);

        b = (Button)findViewById(R.id.o);
        b.setClickable(false);
        b.setTextColor(Color.GRAY);

        b = (Button)findViewById(R.id.p);
        b.setClickable(false);
        b.setTextColor(Color.GRAY);

        b = (Button)findViewById(R.id.q);
        b.setClickable(false);
        b.setTextColor(Color.GRAY);

        b = (Button)findViewById(R.id.r);
        b.setClickable(false);
        b.setTextColor(Color.GRAY);

        b = (Button)findViewById(R.id.s);
        b.setClickable(false);
        b.setTextColor(Color.GRAY);

        b = (Button)findViewById(R.id.t);
        b.setClickable(false);
        b.setTextColor(Color.GRAY);

        b = (Button)findViewById(R.id.u);
        b.setClickable(false);
        b.setTextColor(Color.GRAY);

        b = (Button)findViewById(R.id.v);
        b.setClickable(false);
        b.setTextColor(Color.GRAY);

        b = (Button)findViewById(R.id.w);
        b.setClickable(false);
        b.setTextColor(Color.GRAY);

        b = (Button)findViewById(R.id.x);
        b.setClickable(false);
        b.setTextColor(Color.GRAY);

        b = (Button)findViewById(R.id.y);
        b.setClickable(false);
        b.setTextColor(Color.GRAY);

        b = (Button)findViewById(R.id.z);
        b.setClickable(false);
        b.setTextColor(Color.GRAY);

    }


    public void enableButtons()
    {
        Button b = (Button)findViewById(R.id.a);
        b.setClickable(true);
        b.setTextColor(Color.BLACK);

        b = (Button)findViewById(R.id.b);
        b.setClickable(true);
        b.setTextColor(Color.BLACK);

        b = (Button)findViewById(R.id.c);
        b.setClickable(true);
        b.setTextColor(Color.BLACK);

        b = (Button)findViewById(R.id.d);
        b.setClickable(true);
        b.setTextColor(Color.BLACK);

        b = (Button)findViewById(R.id.e);
        b.setClickable(true);
        b.setTextColor(Color.BLACK);

        b = (Button)findViewById(R.id.f);
        b.setClickable(true);
        b.setTextColor(Color.BLACK);

        b = (Button)findViewById(R.id.g);
        b.setClickable(true);
        b.setTextColor(Color.BLACK);

        b = (Button)findViewById(R.id.h);
        b.setClickable(true);
        b.setTextColor(Color.BLACK);

        b = (Button)findViewById(R.id.i);
        b.setClickable(true);
        b.setTextColor(Color.BLACK);

        b = (Button)findViewById(R.id.j);
        b.setClickable(true);
        b.setTextColor(Color.BLACK);

        b = (Button)findViewById(R.id.k);
        b.setClickable(true);
        b.setTextColor(Color.BLACK);

        b = (Button)findViewById(R.id.l);
        b.setClickable(true);
        b.setTextColor(Color.BLACK);

        b = (Button)findViewById(R.id.m);
        b.setClickable(true);
        b.setTextColor(Color.BLACK);

        b = (Button)findViewById(R.id.n);
        b.setClickable(true);
        b.setTextColor(Color.BLACK);

        b = (Button)findViewById(R.id.o);
        b.setClickable(true);
        b.setTextColor(Color.BLACK);

        b = (Button)findViewById(R.id.p);
        b.setClickable(true);
        b.setTextColor(Color.BLACK);

        b = (Button)findViewById(R.id.q);
        b.setClickable(true);
        b.setTextColor(Color.BLACK);

        b = (Button)findViewById(R.id.r);
        b.setClickable(true);
        b.setTextColor(Color.BLACK);

        b = (Button)findViewById(R.id.s);
        b.setClickable(true);
        b.setTextColor(Color.BLACK);

        b = (Button)findViewById(R.id.t);
        b.setClickable(true);
        b.setTextColor(Color.BLACK);

        b = (Button)findViewById(R.id.u);
        b.setClickable(true);
        b.setTextColor(Color.BLACK);

        b = (Button)findViewById(R.id.v);
        b.setClickable(true);
        b.setTextColor(Color.BLACK);

        b = (Button)findViewById(R.id.w);
        b.setClickable(true);
        b.setTextColor(Color.BLACK);

        b = (Button)findViewById(R.id.x);
        b.setClickable(true);
        b.setTextColor(Color.BLACK);

        b = (Button)findViewById(R.id.y);
        b.setClickable(true);
        b.setTextColor(Color.BLACK);

        b = (Button)findViewById(R.id.z);
        b.setClickable(true);
        b.setTextColor(Color.BLACK);
    }




}
