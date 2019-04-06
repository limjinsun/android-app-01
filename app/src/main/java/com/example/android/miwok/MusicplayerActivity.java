package com.example.android.miwok;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class MusicplayerActivity extends AppCompatActivity {

    MediaPlayer mMediaPlayer = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musicplayer);

        final String path = "android.resource://" + getPackageName() + "/raw/music";
        final Context context = getApplicationContext();
        final Uri audioUri = Uri.parse(path);

        Button mPlayButton = (Button) findViewById(R.id.button_play);
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Play",Toast.LENGTH_SHORT).show();
                if (mMediaPlayer.isPlaying()) {
                    mMediaPlayer.stop();
                    playAudioFile(context, audioUri);
                } else {
                    playAudioFile(context, audioUri);
                }
            }
        });

        Button mStopButton = (Button) findViewById(R.id.button_stop);
        mStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Stop",Toast.LENGTH_SHORT).show();
                mMediaPlayer.stop();
            }
        });
    }

    private void playAudioFile(Context context, Uri uri) {
        try {
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(context, uri);
            mMediaPlayer.prepare();
            mMediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
