package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        ListView listView = (ListView) findViewById(R.id.listView);

        ArrayList<Word> wordList = new ArrayList<Word>();
        wordList.add(new Word("lutti","one", R.drawable.number_one));
        wordList.add(new Word("otiiko","two", R.drawable.number_two));
        wordList.add(new Word("tolookosu","three", R.drawable.number_three));
        wordList.add(new Word("Ayissa","four", R.drawable.number_four));
        wordList.add(new Word("Masaka","five", R.drawable.number_five));
        wordList.add(new Word("Nana","six", R.drawable.number_six));
        wordList.add(new Word("Assa","seven", R.drawable.number_seven));
        wordList.add(new Word("Chuku","eight", R.drawable.number_eight));
        wordList.add(new Word("Hiema","nine", R.drawable.number_nine));
        wordList.add(new Word("Kiku","ten", R.drawable.number_ten));

        WordArrayAdapter itemsAdapter = new WordArrayAdapter(this, wordList);
        listView.setAdapter(itemsAdapter);
    }
}
