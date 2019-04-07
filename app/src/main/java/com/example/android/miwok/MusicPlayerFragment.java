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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MusicPlayerFragment extends Fragment {

    private MediaPlayer mMediaPlayer;
    private boolean isMediaPlayerExist;
    private String TAG = "-- tag";
    private AudioManager mAudioManager;
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener;

    public MusicPlayerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_musicplayer, container, false);

        final String path = "android.resource://" + getActivity().getPackageName() + "/raw/music";
        final Context context = rootView.getContext();
        final Uri audioUri = Uri.parse(path);
        final MediaPlayer.OnCompletionListener mMyCompletionListener = new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                SingleToast.show(context, "Finished", Toast.LENGTH_SHORT);
                if(isMediaPlayerExist){
                    MediaPlayerHelper.releaseMediaplayer(mp);
                    isMediaPlayerExist = false;
                    mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
                }
            }
        };

        mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
            @Override
            public void onAudioFocusChange(int focusChange) {
                switch (focusChange) {
                    case AudioManager.AUDIOFOCUS_GAIN:
                        Log.e(TAG, "AUDIOFOCUS_GAIN");
                        mMediaPlayer.start();
                        break;
                    case AudioManager.AUDIOFOCUS_GAIN_TRANSIENT:
                        Log.e(TAG, "AUDIOFOCUS_GAIN_TRANSIENT");
                        mMediaPlayer.start();
                        break;
                    case AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK:
                        Log.e(TAG, "AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK");
                        break;
                    case AudioManager.AUDIOFOCUS_LOSS:
                        Log.e(TAG, "AUDIOFOCUS_LOSS");
                        if(isMediaPlayerExist){
                            MediaPlayerHelper.releaseMediaplayer(mMediaPlayer);
                        }
                        break;
                    case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                        Log.e(TAG, "AUDIOFOCUS_LOSS_TRANSIENT");
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);
                        break;
                    case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                        Log.e(TAG, "AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK");
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);
                        break;
                    case AudioManager.AUDIOFOCUS_REQUEST_FAILED:
                        Log.e(TAG, "AUDIOFOCUS_REQUEST_FAILED");
                        break;
                    default:
                }
            }
        };

        Button mPlayButton = (Button) rootView.findViewById(R.id.button_play);
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SingleToast.show(context, "Play", Toast.LENGTH_SHORT);

                int res = mAudioManager.requestAudioFocus(
                        mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN
                );

                if(res == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    Log.e(TAG, "AUDIOFOCUS_GAIN");
                    if(!isMediaPlayerExist) {
                        mMediaPlayer = new MediaPlayer();
                        isMediaPlayerExist = true;
                        MediaPlayerHelper.playAudioFile(mMediaPlayer, context, audioUri);
                    } else {
                        if(mMediaPlayer.isPlaying()) {
                            mMediaPlayer.stop();
                            MediaPlayerHelper.playAudioFile(mMediaPlayer, context, audioUri);
                        } else {
                            MediaPlayerHelper.playAudioFile(mMediaPlayer, context, audioUri);
                        }
                    }
                    mMediaPlayer.setOnCompletionListener(mMyCompletionListener);
                }
            }
        });

        Button mStopButton = (Button) rootView.findViewById(R.id.button_stop);
        mStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isMediaPlayerExist){
                    SingleToast.show(context, "Stoped", Toast.LENGTH_SHORT);
                    mMediaPlayer.stop();
                    MediaPlayerHelper.releaseMediaplayer(mMediaPlayer);
                    isMediaPlayerExist = false;
                    mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
                }
            }
        });

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        if(isMediaPlayerExist){
            MediaPlayerHelper.releaseMediaplayer(mMediaPlayer);
            isMediaPlayerExist = false;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

}
