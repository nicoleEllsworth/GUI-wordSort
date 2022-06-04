///////////////////////////////////////////////////////////////////////////////
// Branch:             consoleApp
// Main Class File:    WordSortMain.java
// File:               FilterWords.java
// Date:               June 2022
//
// Author:             Nicole  Ellsworth
///////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;

/**
 * The FilterWords class sorts the master word list by user preferences
 *
 * Bugs: none.
 *
 * @author Nicole Ellsworth.
 */
public class FilterWords {

    // newWords will store new WordList
    private static WordList newWords = new WordList("sortedWords");
    // sound words stores all words beginning with prefix or ending with suffix
    private static WordList soundWords = new WordList("soundWords");

    /**
     * sortByTagsAndLetters sorts a WordList object by its tags (inclusive)
     * and characters (exclusive) in a new list WordList instance 'newWords'.
     *
     * @param tags The tags the user wants applied to wordList.
     * @param c    The list of characters the user wants each word to start with.
     * 
     * @return void.
     */
    public static void sortByTagsAndLetters(String[] tags, ArrayList<Character> c) {
        newWords.clear(); // reset, then add again
        for (Word w : ReadWords.wordList.getWordList()) {
            if (tags.length != 0) { // accounts for tag list of size 0
                for (String tag : tags) {
                    if (c.size() != 0) { // acounts for a size 0 char list
                        if (w.getWordTags().contains(tag) && !w.isListed(newWords)
                                && c.contains(w.getStartsWith())) {
                            newWords.addWord(w);
                            break;
                        }
                    } else {
                        if (w.getWordTags().contains(tag)
                                && !w.isListed(newWords)) {
                            newWords.addWord(w);
                            break;
                        }
                    }
                }
            } else {
                // if tag list size is 0 and char list size != 0
                if (c.size() != 0 && c.contains(w.getStartsWith())
                        && !w.isListed(newWords)) {
                    newWords.addWord(w);
                }
            }
        }
        addSoundWords();
    }

    /**
     * Adds any words matching the prefix and/or suffix
     * 
     * @return void.
     */
    public static void addSoundWords() {
        for (Word w : soundWords.getWordList()) {
            if (!newWords.getWordList().contains(w)) {
                newWords.addWord(w);
            }
        }
    }

    /**
     * Adds all words that start with prefix to sound words
     * 
     * @param prefix is the String to sort by
     * 
     * @return void.
     */
    public static void addStartsWith(String prefix) {
        SortCommands.setPrefix(prefix);
        for (Word w : ReadWords.wordList.getWordList()) {
            if (w.getWordName().startsWith(prefix) && !w.isListed(soundWords)) {
                soundWords.addWord(w);
            }
        }
    }

    /**
     * Adds all words that end with suffix to sound words
     * 
     * @param suffix is the String to sort by
     * 
     * @return void.
     */
    public static void addEndsWith(String suffix) {
        SortCommands.setSuffix(suffix);
        for (Word w : ReadWords.wordList.getWordList()) {
            String name = w.getWordName().replaceAll("tags", "").trim();
            if (name.endsWith(suffix) && !w.isListed(soundWords)) {
                soundWords.addWord(w);
            }
        }
    }

    /**
     * Gets new word list
     * 
     * @return void.
     */
    public static WordList getNewWords() {
        return newWords;
    }

}
