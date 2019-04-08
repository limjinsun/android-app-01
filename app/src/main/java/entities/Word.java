package entities;

public class Word {

    private String miwokWord;
    private String englishWord;
    private int pics = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;
    private int sound;

    public Word(String miwokWord, String englishWord, int pics, int sound) {
        this.miwokWord = miwokWord;
        this.englishWord = englishWord;
        this.pics = pics;
        this.sound = sound;
    }

    public Word(String miwokWord, String englishWord, int sound) {
        this.miwokWord = miwokWord;
        this.englishWord = englishWord;
        this.sound = sound;
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

    public int getSound() {
        return sound;
    }

}