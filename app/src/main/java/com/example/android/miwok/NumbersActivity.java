package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
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
    private String TAG = "-- tag";
    private AudioManager mAudioManager;
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);

        final Context currentContext = getApplicationContext();
        mAudioManager = (AudioManager) currentContext.getSystemService(Context.AUDIO_SERVICE);
        mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
            @Override
            public void onAudioFocusChange(int focusChange) {
                if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                        focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                    mPlayer.pause();
                    mPlayer.seekTo(0);
                } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                    mPlayer.start();
                } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                    MediaPlayerHelper.releaseMediaplayer(mPlayer);
                }
            }
        };

        final MediaPlayer.OnCompletionListener mMyCompletionListner = new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                releaseMediaPlayer();
            }
        };

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

                int res = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(res == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    Log.wtf(TAG, "AUDIOFOCUS_REQUEST_GRANTED");
                    mPlayer = MediaPlayer.create(currentContext, currentWord.getSound());
                    mPlayer.start();
                    mPlayer.setOnCompletionListener(mMyCompletionListner);
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer () {
        if(mPlayer != null){
            mPlayer.release();
            Log.wtf("-- mediaplayer released ", mPlayer.toString());
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
        mPlayer = null;
    }
}
