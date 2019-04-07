package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FamilyFragment extends Fragment {

    public FamilyFragment() {
        // empty constructor.
    }

    private MediaPlayer mPlayer;
    private String TAG = "-- tag";
    private AudioManager mAudioManager;
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.list_layout, container, false);

        final Context currentContext = rootView.getContext();
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
        final MediaPlayer.OnCompletionListener mMyOnCompletionListner = new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                releaseMediaPlayer();
            }
        };

        final ArrayList<Word> wordList = new ArrayList<Word>();
        wordList.add(new Word("nutti","father", R.drawable.family_father, R.raw.family_father));
        wordList.add(new Word("otiiko","grandfather", R.drawable.family_grandfather, R.raw.family_grandfather));
        wordList.add(new Word("tolookosu","mother", R.drawable.family_mother, R.raw.family_mother));
        wordList.add(new Word("Ayissa","grandmother", R.drawable.family_grandmother, R.raw.family_grandmother));
        wordList.add(new Word("Masaka","older_brother", R.drawable.family_older_brother, R.raw.family_older_brother));
        wordList.add(new Word("Nana","older_sister", R.drawable.family_older_sister, R.raw.family_older_sister));
        wordList.add(new Word("Assa","son", R.drawable.family_son, R.raw.family_son));
        wordList.add(new Word("Chuku","younger_brother", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        wordList.add(new Word("Hiema","younger_sister", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        wordList.add(new Word("Kiku","daughter", R.drawable.family_daughter, R.raw.family_daughter));

        WordArrayAdapter itemsAdapter = new WordArrayAdapter(currentContext, wordList, R.color.category_family_dark);
        ListView listView = (ListView) rootView.findViewById(R.id.listView);
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
                    mPlayer.setOnCompletionListener(mMyOnCompletionListner);
                }
            }
        });
        return rootView;
    }

    @Override
    public void onStop() {
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
