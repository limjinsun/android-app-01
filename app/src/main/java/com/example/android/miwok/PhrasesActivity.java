package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases);

        ListView listView = (ListView) findViewById(R.id.listView);

        ArrayList<Word> wordList = new ArrayList<Word>();
        wordList.add(new Word("minto wuksus", "Where are you going?"));
        wordList.add(new Word("tinnә oyaase'nә","What is your name?"));
        wordList.add(new Word("oyaaset...","My name is..."));
        wordList.add(new Word("michәksәs?","How are you feeling?"));
        wordList.add(new Word("kuchi achit","I’m feeling good."));
        wordList.add(new Word("әәnәs'aa?", "Are you coming?"));
        wordList.add(new Word("hәә’ әәnәm","Yes, I’m coming."));
        wordList.add(new Word("әәnәm", "I’m coming."));
        wordList.add(new Word("yoowutis","Let’s go."));
        wordList.add(new Word("әnni'nem","Come here."));

        WordArrayAdapter itemsAdapter = new WordArrayAdapter(this, wordList);
        listView.setAdapter(itemsAdapter);
    }
}
