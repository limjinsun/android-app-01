package com.example.android.miwok;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CountdownActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);

        final TextView mTextView = (TextView) findViewById(R.id.text_view1);

        final Button mButton = (Button) findViewById(R.id.button1);

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
    }
}