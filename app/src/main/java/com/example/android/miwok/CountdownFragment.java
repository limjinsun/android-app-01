package com.example.android.miwok;

import android.content.Context;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class CountdownFragment extends Fragment {

    public CountdownFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_countdown, container, false);

        final TextView mTextView = (TextView) rootView.findViewById(R.id.text_view1);
        final Button mButton = (Button) rootView.findViewById(R.id.button1);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mButton.setEnabled(false);
                mButton.setBackgroundColor(Color.parseColor("#dddddd"));
                // startCountDown();
                new CountDownTimer(10000, 1000) {
                    public void onTick(long millisUntilFinished) {
                        mTextView.setText("seconds remaining: " + millisUntilFinished / 1000);
                    }
                    public void onFinish() {
                        mTextView.setText("done!");
                        mButton.setBackgroundColor(getResources().getColor(R.color.category_countdown));
                        mButton.setEnabled(true);
                    }
                }.start();
            }
        });

        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
