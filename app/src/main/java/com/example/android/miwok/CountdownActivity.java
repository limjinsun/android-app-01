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

        // Hey, why does it hat to be final?
        // https://stackoverflow.com/a/4556562/4735043
        /*  Final essentially means that the variable will not be reassigned at any point and will remain around.
            This means that inner classes, like your listener, can trust that it wont be reassigned by some other thread which could cause all kinds of trouble.
            final can also be used to modify a method or class definition, that would mean that the method can't be overriden by a subclass, or that the class cannot be extended.
         */

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