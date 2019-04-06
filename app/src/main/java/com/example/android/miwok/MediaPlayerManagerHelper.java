package com.example.android.miwok;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

public class MediaPlayerManagerHelper {

    public static void releaseMediaplayer (MediaPlayer mMediaPlayer) {
        if (mMediaPlayer != null) {
            mMediaPlayer.reset();
            mMediaPlayer.release();
            Log.wtf("-- mediaplayer reset and released ", mMediaPlayer.toString());
        }
    }

    public static void playAudioFile(MediaPlayer mMediaPlayer, Context context, Uri uri) {
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
