package com.example.android.miwok;

public class Word {

    private String miwokWord;
    private String englishWord;

    public Word(String miwokWord, String englishWord) {
        this.miwokWord = miwokWord;
        this.englishWord = englishWord;
    }

    public String getMiwokWord() {
        return miwokWord;
    }

    public String getEnglishWord() {
        return englishWord;
    }
}
