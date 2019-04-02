package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        ArrayList<Word> wordList = new ArrayList<Word>();

        wordList.add(new Word("lutti","one"));
        wordList.add(new Word("otiiko","two"));
        wordList.add(new Word("tolookosu","three"));
        wordList.add(new Word("Ayissa","four"));
        wordList.add(new Word("Masaka","five"));
        wordList.add(new Word("Nana","six"));
        wordList.add(new Word("Assa","seven"));
        wordList.add(new Word("Chuku","eight"));
        wordList.add(new Word("Hiema","nine"));
        wordList.add(new Word("Kiku","ten"));


        WordArrayAdapter itemsAdapter = new WordArrayAdapter(this, wordList);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);


//        LinearLayout rootLayout = (LinearLayout) findViewById(R.id.RootLayout);
//        for (int index = 0; index < arrayList.size(); index++) {
//            TextView textView = new TextView(this);
//            textView.setText(arrayList.get(index));
//            rootLayout.addView(textView);
//        }

    }
}
