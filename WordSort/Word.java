///////////////////////////////////////////////////////////////////////////////
// Branch:             main
// Main Class File:    WordSortMain.java
// File:               Word.java
// Date:               May 2022
//
// Author:             Nicole  Ellsworth
///////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;

/**
 * The Word class represents some arbitrary word.
 *
 * Bugs: None known.
 *
 * @author Nicole Ellsworth.
 */
public class Word {
    private String wordName;
    private ArrayList<String> wordTags;
    private char startsWith;
    private Integer rate; // to rate a word's relevance to user input
    private int rateInt;

    /**
     * no-arg constructor creates a Word instance.
     *
     */
    public Word() {
        this.wordName = "unnamed";
        this.wordTags = new ArrayList<String>();
        this.startsWith = '\u0000';
        rate = 0; // tracks how many matches a word has to a users input
        rateInt = 0;
    }

    /**
     * Constructs a new Word instance with the specified name,
     * tags, and first letter.
     *
     * @param name       The name part of this word (the word itself) as any string.
     * @param wordTags   ArrayList containing attributes of the word,
     *                   such as type and meaning.
     * @param startsWith The first letter of the word as a char.
     */
    public Word(String wordName, ArrayList<String> wordTags) {
        this.wordName = wordName;
        this.wordTags = wordTags;
        this.startsWith = wordName.charAt(0);
        rate = 0;
        rateInt = 0;
    }

    /**
     * Gets the name of the Word instance.
     *
     * @return the name as a string.
     */
    public String getWordName() {
        return this.wordName;
    }

    /**
     * Gets the tags of the Word instance.
     *
     * @return the tags as an ArrayList.
     */
    public ArrayList<String> getWordTags() {
        return wordTags;
    }

    /**
     * adds new tags from ArrayList to word object.
     *
     * @return void.
     */
    public void addTags(ArrayList<String> tags) {
        for (String tag : tags) {
            this.wordTags.add(tag);
        }
    }

    /**
     * Gets the first letter of the Word instance.
     *
     * @return the first leter as a char.
     */
    public char getStartsWith() {
        return startsWith;
    }

    /**
     * Checks if word name in word list.
     *
     * @return boolean true if word is listed.
     */
    public boolean isListed(WordList words) {
        for (Word w : words.getWordList()) {
            if (w.getWordName().equals(this.wordName)) {
                return true;
            }
        }
        return false;
    }

    /////////////////// STARTING RATE METHODS///////////////////////////////////////
    /**
     * gets the Integer value of rate
     *
     * @return void.
     */
    public Integer getRate() {
        return this.rate;
    }

    /**
     * Helper method that converts int to Integer.
     *
     * @return void.
     */
    public static Integer valueOf​(int i) {
        return i;
    }

    /**
     * sets the rate to an integer value.
     *
     * @return void.
     */
    public void setRate(int num) {
        this.rate = Integer.valueOf(num);
    }

    /**
     * sets the mutable int value rate
     *
     * @return void.
     */
    public void setIntRate(int num) {
        this.rateInt = 0;
    }

    /**
     * gets the int value of rate
     *
     * @return void.
     */
    public int getIntRate() {
        return rateInt;
    }

    /**
     * increments the int value of rate by 1
     *
     * @return void.
     */
    public void addIntRate() {
        this.rateInt += 1;
    }

}
