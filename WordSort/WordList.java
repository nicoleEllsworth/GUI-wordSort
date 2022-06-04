///////////////////////////////////////////////////////////////////////////////
// Branch:             main
// Main Class File:    WordSortMain.java
// File:               WordList.java
// Date:               May 2022
//
// Author:             Nicole  Ellsworth
///////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;

/**
 * The WordList class holds and arbitrary # of word objects.
 *
 * Bugs: None known.
 *
 * @author Nicole Ellsworth.
 */
public class WordList {

    private String name;
    private ArrayList<Word> wordList;

    /**
     * no-arg constructor creates a Word instance.
     *
     */
    public WordList() {
        this.name = "unnamed";
        this.wordList = new ArrayList<Word>();
    }

    /**
     * Constructs a new WordList instance with the specified name,
     * and ArrayList of Words.
     *
     * @param name The name part of this WordList as a String
     *
     */
    public WordList(String name) {
        this.name = name;
        this.wordList = new ArrayList<Word>();
    }

    /**
     * adds a Word object to the ArrayList of words.
     *
     * @return void.
     */
    public void addWord(Word word) {
        wordList.add(word);
    }

    /**
     * deletes a Word object from the ArrayList of words.
     *
     * @return void.
     */
    public void deleteWord(Word word) {
        wordList.remove(word);
    }

    /**
     * clears all word ojects from wordList
     *
     * @return void.
     */
    public void clear() {
        this.wordList.clear();
    }

    /**
     * prints the the words in wordList and their name and tags
     *
     * @return void.
     */
    public void printWordList() {
        if (wordList.size() == 0) {
            System.out.println("list is empty");
        } else {
            System.out.println("Word list: \n");
            for (Word w : wordList) {
                System.out.println(w.getWordName());
                System.out.println("Tags: " + w.getWordTags() + " " + w.getIntRate());
                // System.out.println("Rating: " + w.getRate());
                System.out.println("");
            }
        }

    }

    /**
     * gets the WordList name
     *
     * @return void.
     */
    public String getName() {
        return this.name;
    }

    /**
     * gets the ArrayList of words
     *
     * @return void.
     */
    public ArrayList<Word> getWordList() {
        return this.wordList;
    }

}
