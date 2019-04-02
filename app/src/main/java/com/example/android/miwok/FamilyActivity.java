package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family);
        // 아이디는 액티비티가 다르면 중복되어도 노상관.
        ListView listView = (ListView) findViewById(R.id.listView);

        ArrayList<Word> wordList = new ArrayList<Word>();
        wordList.add(new Word("nutti","father", R.drawable.family_father));
        wordList.add(new Word("otiiko","grandfather", R.drawable.family_grandfather));
        wordList.add(new Word("tolookosu","mother", R.drawable.family_mother));
        wordList.add(new Word("Ayissa","grandmother", R.drawable.family_grandmother));
        wordList.add(new Word("Masaka","older_brother", R.drawable.family_older_brother));
        wordList.add(new Word("Nana","older_sister", R.drawable.family_older_sister));
        wordList.add(new Word("Assa","son", R.drawable.family_son));
        wordList.add(new Word("Chuku","younger_brother", R.drawable.family_younger_brother));
        wordList.add(new Word("Hiema","younger_sister", R.drawable.family_younger_sister));
        wordList.add(new Word("Kiku","daughter", R.drawable.family_daughter));

        WordArrayAdapter itemsAdapter = new WordArrayAdapter(this, wordList);
        listView.setAdapter(itemsAdapter);
    }
}
