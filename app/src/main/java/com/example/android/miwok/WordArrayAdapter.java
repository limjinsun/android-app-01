package com.example.android.miwok;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;

public class WordArrayAdapter extends ArrayAdapter<Word> {

    private int colorResourceId;

    public WordArrayAdapter(Context context, ArrayList<Word> words, int colorResource) {
        super(context, 0, words);
        this.colorResourceId = colorResource;
    }

    // getView 뷰가 필요할때, 리스트를 재 사용해서 뷰를 만들어 내는 함수임.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Log.e("GetView ", getContext().getPackageResourcePath());

        // convertView is used to reuse old view.
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent,false);
        }

        // Get the data item for this position
        Word word = getItem(position);

        LinearLayout textContainerLayout =  (LinearLayout) convertView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(), colorResourceId);
        textContainerLayout.setBackgroundColor(color);

        TextView englishTextView = (TextView) convertView.findViewById(R.id.default_text_view);
        englishTextView.setText(word.getEnglishWord());

        TextView miwokTextView = (TextView) convertView.findViewById(R.id.miwok_text_view);
        miwokTextView.setText(word.getMiwokWord());

        ImageView picsImageView = (ImageView) convertView.findViewById(R.id.pics_image_view);
        if(word.hasPics()){
            picsImageView.setImageResource(word.getPics());
            picsImageView.setVisibility(View.VISIBLE);
        } else {
            picsImageView.setVisibility(View.GONE);
        }

        return convertView;
    }
}
