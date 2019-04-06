package com.example.android.miwok;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    private MediaPlayer mPlayer = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases);

        final Context currentContext = getApplicationContext();

        ListView listView = (ListView) findViewById(R.id.listView);

        final ArrayList<Word> wordList = new ArrayList<Word>();
        wordList.add(new Word("minto wuksus", "Where are you going?", R.raw.phrase_where_are_you_going));
        wordList.add(new Word("tinnә oyaase'nә","What is your name?", R.raw.phrase_what_is_your_name));
        wordList.add(new Word("oyaaset...","My name is...", R.raw.phrase_my_name_is));
        wordList.add(new Word("michәksәs?","How are you feeling?", R.raw.phrase_how_are_you_feeling));
        wordList.add(new Word("kuchi achit","I’m feeling good.", R.raw.phrase_im_feeling_good));
        wordList.add(new Word("әәnәs'aa?", "Are you coming?", R.raw.phrase_are_you_coming));
        wordList.add(new Word("hәә’ әәnәm","Yes, I’m coming.", R.raw.phrase_yes_im_coming));
        wordList.add(new Word("әәnәm", "I’m coming.", R.raw.phrase_im_coming));
        wordList.add(new Word("yoowutis","Let’s go.", R.raw.phrase_lets_go));
        wordList.add(new Word("әnni'nem","Come here.", R.raw.phrase_come_here));

        WordArrayAdapter itemsAdapter = new WordArrayAdapter(this, wordList, R.color.category_phrases_dark);
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word currentWord = wordList.get(position);

                mPlayer.reset();
                Toast.makeText(currentContext, "Clicked", Toast.LENGTH_SHORT).show();
                mPlayer = MediaPlayer.create(currentContext, currentWord.getSound());
                mPlayer.start();
            }
        });
    }
}