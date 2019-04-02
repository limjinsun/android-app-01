package com.example.android.miwok;

public class Word {

    private String miwokWord;
    private String englishWord;
    private int pics = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;

    public Word(String miwokWord, String englishWord) {
        this.miwokWord = miwokWord;
        this.englishWord = englishWord;
    }

    public Word(String miwokWord, String englishWord, int pics) {
        this.miwokWord = miwokWord;
        this.englishWord = englishWord;
        this.pics = pics;
    }

    public String getMiwokWord() {
        return miwokWord;
    }

    public String getEnglishWord() {
        return englishWord;
    }

    public int getPics() {
        return pics;
    }

    public boolean hasPics() {
        if(pics == NO_IMAGE_PROVIDED) return false;
        return true;
    }
}