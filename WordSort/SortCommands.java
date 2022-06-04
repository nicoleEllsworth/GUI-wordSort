///////////////////////////////////////////////////////////////////////////////
// Branch:             main
// Main Class File:    WordSortMain.java
// File:               SortWords.java
// Date:               May 2022
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
    private final static String ADD_CHAR = "Add char(s): ";
    private final static String REMOVE_CHAR = "Remove char(s): ";
    private final static String ADD_TAG = "Add tag(s): ";
    private final static String REMOVE_TAG = "Remove tag(s): ";
    final static String TAG_NOT_FOUND = "The tag you typed is not valid"; // for removing a tag
    final static String LETTER_NOT_FOUND = "The letter you typed is not valid"; // for removing a character
    final static String TAG_NOT_IN_LIST = "The tag you typed is not a filterable tag";
    final static String INVALID_INPUT = "Invalid input, please try again";

    // tracks current tag filter settings
    private static ArrayList<String> currentTagList = new ArrayList<String>();
    // tracks current character filter settings
    private static ArrayList<Character> currentCharList = new ArrayList<Character>();
    private static String prefix = "none";
    private static String suffix = "none";
    private static ArrayList<String> allTags = new ArrayList<String>();
    private static String[] allTagsArr = { "noun", "nature", "beautiful", "weather", "verb", "adjective",
            "adverb", "terrain", "city", "place", "people", "pronoun", "conjunction",
            "idiom", "interjection", "preposition", "sensory", "visual",
            "sound", "touch", "texture", "smell", "taste", "flavor", "color", "brown", "red",
            "old", "orange", "yellow", "green", "blue", "purple", "pink", "white", "black",
            "grey", "animal", "dark", "light", "happy", "sad", "DFW", "large", "religion" };

    /**
     * Calls methods from the ScrapeWords class to create
     * a WordList object containing all the scraped words.
     *
     * @return void.
     */
    public static void setUpWords() {
        for (String s : allTagsArr) { // creates list of all tags
            allTags.add(s);
        }
        try {
            ReadWords.readWords();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    // BEGIN CHARACTER LIST METHODS/////////////////////////////////////////////

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

    /**
     * Adds character to char list and sorts newWords accordingly
     *
     * @param inputChar character to sort newWords by
     * 
     * @return void.
     */
    public static void addChar(char inputChar) {
        System.out.println(ADD_CHAR + inputChar);
        System.out.println("");
        if (!currentCharList.contains(inputChar)) {
            currentCharList.add(inputChar);
            printWorkingTags();
            filterAndSort();
            FilterWords.getNewWords().printWordList();
        }
        // sort newWord list
        else {
            System.out.println(inputChar + " is already listed.");
        }
        // if there are no words beginning with that character
        if (FilterWords.getNewWords().getWordList().size() == 0) {
            System.out.println("The are no words beginning with this letter");
        }
    }

    /**
     * Removes character from char list and sorts newWords accordingly
     *
     * @param rmCharInput character to remove
     * 
     * @return void.
     */
    public static void rmvChar(char rmCharInput) {
        System.out.println(REMOVE_CHAR + rmCharInput);
        System.out.println("");
        if (currentCharList.contains(rmCharInput)) {
            currentCharList.remove(Character.valueOf(rmCharInput));
            filterAndSort();
            FilterWords.getNewWords().printWordList();
        } else {
            System.out.println(rmCharInput + " is not in the character list");
        }
    }

    /**
     * Clears character list and sorts newWords accordingly
     * 
     * @return void.
     */
    public static void clearChar() {
        currentCharList.clear();
        filterAndSort();
        FilterWords.getNewWords().printWordList();
    }

    /**
     * Checks if character input is valid
     * 
     * @param c character to check.
     * 
     * @return void.
     */
    public static boolean checkValidChar(char c) {
        if (!Character.isLetter(c)) {
            System.out.println(LETTER_NOT_FOUND);
            WordSortMain.checkValid(false, Character.toString(c));
            return false;
        }
        return true;
    }

    // BEGIN TAG LIST METHODS///////////////////////////////////////////////////

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

    /**
     * Adds tag(s) to tag list and sorts newWords accordingly
     *
     * @param inputTags tags to sort newWords by as a String
     * 
     * @return void.
     */
    public static void addTags(String inputTags) {
        boolean changedList = false;
        System.out.println(ADD_TAG + inputTags);
        System.out.println("");
        String[] tagsInput = inputTags.split(" ");
        System.out.println(Arrays.toString(tagsInput));
        // loop adds tags to working tag list
        for (String t : tagsInput) {
            if (!currentTagList.contains(t) && allTags.contains(t)) {
                currentTagList.add(t);
                changedList = true;
            } else if (currentTagList.contains(t)) {
                System.out.println(t + " is already listed.");
            } else {
                System.out.println(TAG_NOT_FOUND + ": " + t);
            }
        }
        if (changedList) {
            filterAndSort();
            FilterWords.getNewWords().printWordList();
        }
    }

    /**
     * Removes tag(s) from tag list and sorts newWords accordingly
     *
     * @param rmTagInput tag(s) to remove
     * 
     * @return void.
     */
    public static void rmvTags(String rmTagInput) {
        boolean changedList = false;
        System.out.println(REMOVE_TAG + rmTagInput);
        System.out.println("");
        String[] tagsRemove = rmTagInput.split(" ");
        // loop removes tags from working tag list
        for (String tag : tagsRemove) {
            if (currentTagList.contains(tag)) {
                changedList = true;
                currentTagList.remove(String.valueOf(tag));
            } else {
                System.out.println(tag + " is not in the current tag list.");
            }
        }
        // re-sort newWords
        if (changedList) {
            filterAndSort();
            FilterWords.getNewWords().printWordList();
        }
    }

    /**
     * Clears tag list and sorts newWords accordingly
     * 
     * @return void.
     */
    public static void clearTags() {
        if (currentTagList.size() != 0) {
            currentTagList.clear();
            filterAndSort();
            FilterWords.getNewWords().printWordList();
        }
    }

    /**
     * Prints all possible tags in alphabetical order
     * 
     * @return void.
     */
    public static void printAllTags() {
        Collections.sort(allTags, String.CASE_INSENSITIVE_ORDER);
        for (String tag : allTags) {
            System.out.println(tag);
        }
    }

    /**
     * Prints current tags in use
     * 
     * @return void.
     */
    public static void printWorkingTags() {
        System.out.println("Current characters in use: ");
        if (currentCharList.size() == 0) {
            System.out.println("no characters in use");
        } else {
            for (char c : currentCharList) {
                System.out.println(c);
            }
        }
        System.out.println("");
        System.out.println("Current tags in use: ");
        if (currentTagList.size() == 0) {
            System.out.println("no tags in use");
        } else {
            for (String s : currentTagList) {
                System.out.println(s);
            }
        }
    }

    // BEGIN PREFIX AND SUFFX METHODS///////////////////////////////////////////

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
        FilterWords.addStartsWith(prefix);
        FilterWords.addSoundWords();
    }

    /**
     * Sets prefix back to "none"
     *
     * @return void.
     */
    public static void clearPrefix() {
        setPrefix("none");
        // re-sort
        filterAndSort();
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
        FilterWords.addEndsWith(suffix);
        FilterWords.addSoundWords();
    }

    /**
     * Sets suffix back to "none"
     *
     * @return void.
     */
    public static void clearSuffix() {
        setSuffix("none");
        // re-sort
        filterAndSort();
    }

    // BEGIN RANKING WORDS BY MATCHES///////////////////////////////////////////

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
     * Sorts newWords by highest rated words only.
     * Requirement for a word to be exclusively rated
     * is that it has a 50% match with all tags
     *
     * @return void.
     */
    public static void sortWordsExclusive() {
        // Total number of possible matches
        int matchTotal = currentCharList.size() + currentTagList.size();
        // toDelte: list of words that have less than m * matched rating
        ArrayList<Word> toDelete = new ArrayList<Word>();
        if (!prefix.equals("none")) {
            matchTotal += 1;
        }
        if (!suffix.equals("none")) {
            matchTotal += 1;
        }
        for (Word w : FilterWords.getNewWords().getWordList()) {
            int r = w.getRate();
            if (r < matchTotal) {
                System.out.println(
                        w.getWordName() + ", r: " + r + ", matched: " + matchTotal);
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
            FileWriter myWriter = new FileWriter("sortedWords.txt");
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
