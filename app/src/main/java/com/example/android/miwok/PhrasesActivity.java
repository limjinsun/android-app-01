package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    private MediaPlayer mPlayer;
    private String TAG = "-- tag";
    private AudioManager mAudioManager;
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases);

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

        final MediaPlayer.OnCompletionListener mMyCompletionListener = new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                releaseMediaPlayer();
            }
        };

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
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word currentWord = wordList.get(position);
                SingleToast.show(currentContext, "Clicked", Toast.LENGTH_SHORT);
                releaseMediaPlayer();

                int res = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(res == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    Log.wtf(TAG, "AUDIOFOCUS_REQUEST_GRANTED");
                    mPlayer = MediaPlayer.create(currentContext, currentWord.getSound());
                    mPlayer.start();
                    mPlayer.setOnCompletionListener(mMyCompletionListener);
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