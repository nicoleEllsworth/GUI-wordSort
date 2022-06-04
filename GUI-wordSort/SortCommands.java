///////////////////////////////////////////////////////////////////////////////
// Branch:             main
// Main Class File:    WordForm.java
// File:               SortWords.java
// Date:               June 2022
//
// Author:             Nicole  Ellsworth
///////////////////////////////////////////////////////////////////////////////

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.*;

/**
 * The SortWords class contains all the methods for a user
 * to create their custom sorted word list
 *
 * Bugs: None.
 *
 * @author Nicole Ellsworth.
 */
public class SortCommands {

    // CONSTANTS
    final static String INVALID_INPUT = "Invalid input, please try again";

    // tracks current tag filter settings
    private static ArrayList<String> currentTagList = new ArrayList<String>();
    // tracks current character filter settings
    private static ArrayList<Character> currentCharList = new ArrayList<Character>();
    private static String prefix = "no prefix";
    private static String suffix = "no suffix";

    /**
     * Calls methods from the ScrapeWords class to create
     * a WordList object containing all the scraped words.
     *
     * @return void.
     */
    public static void setUpWords() {
        try {
            ReadWords.readWords();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    // BEGIN CHARACTER LIST METHODS////////////////////////////////////////////

    /**
     * Gets current, working character list.
     *
     * @return ArrayList<Character> currentCharList.
     */
    public static ArrayList<Character> getCharList() {
        return currentCharList;
    }

    /**
     * Sets current, working character to new list.
     *
     * @return void.
     */
    public static void setCharList(ArrayList<Character> charlist) {
        currentCharList = charlist;
    }

    // BEGIN TAG LIST METHODS//////////////////////////////////////////////////

    /**
     * Gets current, working taglist.
     *
     * @return ArrayList<String> currentTagList.
     */
    public static ArrayList<String> getTagList() {
        return currentTagList;
    }

    /**
     * Sets current, working tag list to new list.
     *
     * @return void.
     */
    public static void setTagList(ArrayList<String> tlist) {
        currentTagList = tlist;
    }

    // BEGIN PREFIX AND SUFFX METHODS//////////////////////////////////////////

    /**
     * Gets prefix.
     *
     * @return prefix as a string.
     */
    public static String getPrefix() {
        return prefix;
    }

    /**
     * sets prefix to string pre.
     *
     * @param pre is the string to set prefix to
     * 
     * @return void.
     */
    public static void setPrefix(String pre) {
        prefix = pre;
    }

    public static void addPrefix() {
        if (!prefix.equals("no prefix")) {
            FilterWords.addStartsWith(prefix);
            FilterWords.addSoundWords();
        }
    }

    /**
     * Gets suffix.
     *
     * @return suffix as a string.
     */
    public static String getSuffix() {
        return suffix;
    }

    /**
     * sets suffix to string suff.
     *
     * @param suff is the string to set suffix to
     * 
     * @return void.
     */
    public static void setSuffix(String suff) {
        suffix = suff;
    }

    public static void addSuffix() {
        if (!suffix.equals("no suffix")) {
            FilterWords.addEndsWith(suffix);
            FilterWords.addSoundWords();
        }
    }

    // BEGIN RANKING WORDS BY MATCHES//////////////////////////////////////////

    /**
     * Rates all the words in newWords by how many matches
     * they have with current tags, characters, and prefix/suffix
     *
     * @return void.
     */
    public static void rateWords() {
        for (Word w : FilterWords.getNewWords().getWordList()) {
            w.setIntRate(0);
            for (String s : currentTagList) {
                if (w.getWordTags().contains(s)) {
                    w.addIntRate();
                }
            }
            for (char c : currentCharList) {
                if (c == (w.getStartsWith())) {
                    w.addIntRate();
                }
            }
            if (w.getWordName().startsWith(prefix) && prefix.length() > 0) {
                w.addIntRate();
            }
            String name = w.getWordName().replaceAll("tags", "").trim();
            if (name.endsWith(suffix) && suffix.length() > 0) {
                w.addIntRate();
            }
            // sets the word's Integer value of rate
            w.setRate(w.getIntRate());
        }
    }

    /**
     * Sorts newWords by highest rated words to lowest
     *
     * @return void.
     */
    public static void sortWordsByMatch() {
        rateWords();
        Collections.sort(FilterWords.getNewWords().getWordList(),
                new Comparator<Word>() {
                    public int compare(Word w1, Word w2) {
                        return w2.getRate().compareTo(w1.getRate());
                    }
                });
    }

    /**
     * Sorts newWords by highest rated words only
     * so that all words match >= m * matched tags
     * 
     * @param m double that determines how many
     *          of the tags the words need to match, with
     *          1 being the highest
     *
     * @return void.
     */
    public static void sortWordsExclusive(double m) {
        // Total number of possible matches
        int matchTotal = currentCharList.size() + currentTagList.size();
        // toDelte: list of words that have less than m * matched rating
        ArrayList<Word> toDelete = new ArrayList<Word>();
        if (!prefix.equals("no prefix")) {
            matchTotal += 1;
        }
        if (!prefix.equals("no suffix")) {
            matchTotal += 1;
        }
        for (Word w : FilterWords.getNewWords().getWordList()) {
            int r = w.getRate();
            if (r < (matchTotal) * m) {
                System.out.println(w.getWordName() + ", r: " + r +
                        ", matched: " + matchTotal);
                toDelete.add(w);
            }
        }
        // loop deletes the words that don't match
        for (Word w : toDelete) {
            FilterWords.getNewWords().deleteWord(w);
        }
        // re-sort
        SortCommands.sortWordsByMatch();
    }

    // BEGIN OTHER METHODS/////////////////////////////////////////////////////

    /**
     * Saves words to text file called "sortedWords.txt"
     *
     * @return void.
     */
    public static void saveNewWordList() {
        // make sure list is sorted before saving
        SortCommands.sortWordsByMatch();
        String newLine = System.getProperty("line.separator");
        try {
            FileWriter myWriter = new FileWriter("GUI-wordSort\\sortedWords.txt");
            myWriter.write(newLine);
            myWriter.write("Tags: " + currentTagList);
            myWriter.write(newLine);
            myWriter.write("Starting letters: " + currentCharList);
            myWriter.write(newLine);
            myWriter.write("Prefix: " + prefix);
            myWriter.write(newLine);
            myWriter.write("Suffix: " + suffix);
            myWriter.write(newLine);
            myWriter.write(newLine);
            myWriter.write(newLine);
            for (Word w : FilterWords.getNewWords().getWordList()) {
                myWriter.write(w.getWordName());
                myWriter.write(" tags: ");
                for (String t : w.getWordTags()) {
                    myWriter.write(t + " ");
                }
                myWriter.write(newLine);
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Helper method that converts ArrayList<String>
     * to array of strings
     *
     * @return void.
     */
    public static String[] convStringArray(ArrayList<String> tags) {
        String[] convTags = new String[tags.size()];
        int i = 0;
        for (String s : tags) {
            convTags[i] = s;
            i++;
        }
        return convTags;
    }

    /**
     * Helper method that filters newWords and then
     * sorts by ratings
     *
     * @return void.
     */
    public static void filterAndSort() {
        FilterWords.sortByTagsAndLetters(
                convStringArray(currentTagList), currentCharList);
        SortCommands.sortWordsByMatch();
    }

}
