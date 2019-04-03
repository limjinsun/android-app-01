package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);

        ListView listView = (ListView) findViewById(R.id.listView);

        ArrayList<Word> wordList = new ArrayList<Word>();
        wordList.add(new Word("nutti","black", R.drawable.color_black));
        wordList.add(new Word("otiiko","brown", R.drawable.color_brown));
        wordList.add(new Word("tolookosu","dusty_yellow", R.drawable.color_dusty_yellow));
        wordList.add(new Word("Ayissa","gray", R.drawable.color_gray));
        wordList.add(new Word("Masaka","green", R.drawable.color_green));
        wordList.add(new Word("Nana","mustard_yellow", R.drawable.color_mustard_yellow));
        wordList.add(new Word("Assa","red", R.drawable.color_red));
        wordList.add(new Word("Chuku","white", R.drawable.color_white));

        WordArrayAdapter itemsAdapter = new WordArrayAdapter(this, wordList, R.color.category_colors_dark);
        listView.setAdapter(itemsAdapter);
    }
}