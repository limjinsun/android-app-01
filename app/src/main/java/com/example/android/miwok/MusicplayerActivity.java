package com.example.android.miwok;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MusicplayerActivity extends AppCompatActivity {

    MediaPlayer mMediaPlayer;
    boolean isMediaPlayerExist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musicplayer);

        final String path = "android.resource://" + getPackageName() + "/raw/color_black";
        final Context context = getApplicationContext();
        final Uri audioUri = Uri.parse(path);

        Button mPlayButton = (Button) findViewById(R.id.button_play);
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isMediaPlayerExist) {
                    mMediaPlayer = new MediaPlayer();
                    MediaPlayerManagerHelper.playAudioFile(mMediaPlayer, context, audioUri);
                    isMediaPlayerExist = true;
                } else {
                    if(mMediaPlayer.isPlaying()) {
                        mMediaPlayer.stop();
                        MediaPlayerManagerHelper.playAudioFile(mMediaPlayer, context, audioUri);
                    } else {
                        MediaPlayerManagerHelper.playAudioFile(mMediaPlayer, context, audioUri);
                    }
                }
                SingleToast.show(context, "Play", Toast.LENGTH_SHORT);

                mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        SingleToast.show(context, "Finished", Toast.LENGTH_SHORT);
                        MediaPlayerManagerHelper.releaseMediaplayer(mp);
                        isMediaPlayerExist = false;
                    }
                });
            }
        });

        Button mStopButton = (Button) findViewById(R.id.button_stop);
        mStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isMediaPlayerExist){
                    SingleToast.show(context, "Stoped", Toast.LENGTH_SHORT);
                    mMediaPlayer.stop();
                    MediaPlayerManagerHelper.releaseMediaplayer(mMediaPlayer);
                    isMediaPlayerExist = false;
                }
            }
        });
    }
}
