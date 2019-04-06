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

public class ColorsActivity extends AppCompatActivity {

    private MediaPlayer mPlayer;
    boolean isMediaPlayerExist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);

        final Context currentContext = getApplicationContext();

        ListView listView = (ListView) findViewById(R.id.listView);

        final ArrayList<Word> wordList = new ArrayList<Word>();
        wordList.add(new Word("nutti","black", R.drawable.color_black, R.raw.color_black));
        wordList.add(new Word("otiiko","brown", R.drawable.color_brown, R.raw.color_brown));
        wordList.add(new Word("tolookosu","dusty_yellow", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        wordList.add(new Word("Ayissa","gray", R.drawable.color_gray, R.raw.color_gray));
        wordList.add(new Word("Masaka","green", R.drawable.color_green, R.raw.color_green));
        wordList.add(new Word("Nana","mustard_yellow", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));
        wordList.add(new Word("Assa","red", R.drawable.color_red, R.raw.color_red));
        wordList.add(new Word("Chuku","white", R.drawable.color_white, R.raw.color_white));

        WordArrayAdapter itemsAdapter = new WordArrayAdapter(this, wordList, R.color.category_colors_dark);
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word currentWord = wordList.get(position);
                SingleToast.show(currentContext, "Clicked", Toast.LENGTH_SHORT);

                if(isMediaPlayerExist){
                    MediaPlayerManagerHelper.releaseMediaplayer(mPlayer);
                    isMediaPlayerExist = false;
                }
                mPlayer = MediaPlayer.create(currentContext, currentWord.getSound());
                isMediaPlayerExist = true;
                mPlayer.start();
                mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        MediaPlayerManagerHelper.releaseMediaplayer(mp);
                        isMediaPlayerExist = false;
                    }
                });
            }
        });

    }
}