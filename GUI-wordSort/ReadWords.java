///////////////////////////////////////////////////////////////////////////////
// Branch:             main
// Main Class File:    WordForm.java
// File:               ReadWords.java
// Date:               June 2022
//
// Author:             Nicole  Ellsworth
///////////////////////////////////////////////////////////////////////////////

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * The ReadWords class parses the words.txt file
 * into a WordList instance.
 *
 * Bugs: None.
 *
 * @author Nicole Ellsworth.
 */
public class ReadWords {

    // creates a WordList instance
    static WordList wordList = new WordList("rootWords");

    /**
     * Method reads words from text file and creates
     * a new wordlist object
     *
     * @return void.
     */
    public static void readWords() throws FileNotFoundException {
        java.io.File file = new java.io.File("GUI-wordSort\\words.txt");
        try (
                Scanner scan = new Scanner(file);) {
            while (scan.hasNextLine()) {
                String word = scan.nextLine();
                // gets the word tags
                String[] tags = (word.substring(word.indexOf(":") + 2,
                        word.length())).split(" ");
                if (word.contains("tags")) {
                    // gets the word without its tags
                    word = word.substring(0, word.indexOf("tags:")).trim();
                }
                ArrayList<String> tagList = new ArrayList<String>();
                // adds word tags to list of tags
                for (String tag : tags) {
                    tagList.add(tag);
                }
                // create word instance
                Word fileWord = new Word(word, tagList);
                // adds word to WordList object wordList
                wordList.addWord(fileWord);
            }

        } catch (FileNotFoundException read) {
            System.out.println(read.getMessage());
            throw read;
        }
    }
}
