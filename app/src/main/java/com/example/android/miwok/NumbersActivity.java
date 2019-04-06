package com.example.android.miwok;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        final Context currentContext = getApplicationContext();

        ArrayList<Word> wordList = new ArrayList<Word>();
        wordList.add(new Word("lutti", "one", R.drawable.number_one, R.raw.number_one));
        wordList.add(new Word("otiiko", "two", R.drawable.number_two, R.raw.number_two));
        wordList.add(new Word("tolookosu", "three", R.drawable.number_three, R.raw.number_three));
        wordList.add(new Word("Ayissa", "four", R.drawable.number_four, R.raw.number_four));
        wordList.add(new Word("Masaka", "five", R.drawable.number_five, R.raw.number_five));
        wordList.add(new Word("Nana", "six", R.drawable.number_six, R.raw.number_six));
        wordList.add(new Word("Assa", "seven", R.drawable.number_seven, R.raw.number_seven));
        wordList.add(new Word("Chuku", "eight", R.drawable.number_eight, R.raw.number_eight));
        wordList.add(new Word("Hiema", "nine", R.drawable.number_nine, R.raw.number_nine));
        wordList.add(new Word("Kiku", "ten", R.drawable.number_ten, R.raw.number_ten));

        WordArrayAdapter wordAdapter = new WordArrayAdapter(this, wordList, R.color.category_numbers_dark);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(wordAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SingleToast.show(currentContext, "Clicked", Toast.LENGTH_SHORT);

                ArrayAdapter<Word> myAdapter = (ArrayAdapter<Word>) parent.getAdapter();
                Word currentWord = myAdapter.getItem(position);

                releaseMediaPlayer();
                mPlayer = MediaPlayer.create(currentContext, currentWord.getSound());
                mPlayer.start();
                mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        releaseMediaPlayer();
                    }
                });
            }
        });
    }

    private void releaseMediaPlayer () {
        if(mPlayer != null){
            mPlayer.release();
        }
        mPlayer = null;
    }
}
