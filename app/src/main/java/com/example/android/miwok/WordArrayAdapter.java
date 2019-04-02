package com.example.android.miwok;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class WordArrayAdapter extends ArrayAdapter<Word> {

    public WordArrayAdapter(Context context, ArrayList<Word> words) {
        super(context, 0, words);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent,false);
        }

        // Get the data item for this position
        Word word = getItem(position);

        TextView englishTextView = (TextView) convertView.findViewById(R.id.default_text_view);
        TextView miwokTextView = (TextView) convertView.findViewById(R.id.miwok_text_view);

        englishTextView.setText(word.getEnglishWord());
        miwokTextView.setText(word.getMiwokWord());

        return convertView;
    }
}
