
///////////////////////////////////////////////////////////////////////////////
// Branch:             main
// Main Class File:    WordSortMain.java
// File:               WordSortMain.java
// Date:               May 2022
//
// Author:             Nicole  Ellsworth
///////////////////////////////////////////////////////////////////////////////
import java.util.*;

/**
 * The WorSortdMain class contains the main method where
 * the user can create their custom word list
 *
 * Bugs: None.
 *
 * @author Nicole Ellsworth.
 */
public class WordSortMain {

    // list of constants to make machine-user interaction simpler
    private final static String WELCOME_MSG = "Welcome to word sorting! Please see commands below \n";

    private final static String COMMANDS = "To filter by letter type 'char:(your char)' \n" +
            "To filter by word tags type 'tags:(tag1) (tag2) etc' \n" +
            "To remove a char type 'rm char:(char1) (char2) etc' \n" +
            "To remove a tag type 'rm tags: (tag1) (tag2) etc' \n" +
            "To see list of possible tags type 'list all:tags' \n" +
            "To see list of current tags and characters in use type 'list current:tags' \n" +
            "To filter words by a prefix, type 'prefix:(your prefix)' \n" +
            "To filter words by a suffix, type 'suffix:(your suffix)' \n" +
            "To reset your pefix type 'clear prefix' \n" +
            "To reset your suffix type 'clear suffix' \n" +
            "To reset your sorted list type 'clear' \n" +
            "To reset your current character sorting type 'clear char' \n" +
            "To reset your current tag sorting 'clear tags' \n" +
            "To reset your sorted list type 'clear' \n" +
            "To see your sorted list with only high matching words, type 'list best matched' \n" +
            "To save your list to a file type 'save' \n" +
            "To leave the program type 'end' \n" +
            "To see list of commands again, type 'man' \n";

    private final static String TYPE = "Type something: ";
    // end of constants
    private static Scanner scan = new Scanner(System.in);

    public static boolean checkValid(boolean validInput, String uInput) {
        try {
            if (validInput == false) {
                throw new IllegalArgumentException("Incorrect input encountered: invalid input");
            }
            for (char cInMsg : uInput.toCharArray()) {
                // invalid character
                if (Character.isDigit(cInMsg)) {
                    throw new IllegalArgumentException("Incorrect input encountered: illegal character");
                }
            }
        } catch (IllegalArgumentException i) {
            System.out.println("Incorrect input encountered: invalid input");
            System.out.println("");
            System.out.println(TYPE);
            runSort(scan.nextLine());
            return false;
        }
        return true;
    }

    public static void runSort(String userInput) {
        // inputType determines what command the program needs to run
        String inputType;
        // inutMsg determines the string to sort by (if relevant)
        String inputMsg;
        if (checkValid(true, userInput) == true) {
            if (userInput.contains(":")) {
                try {
                    inputType = userInput.substring(0, userInput.indexOf(":"));
                    inputMsg = (userInput.substring(userInput.indexOf(":") + 1, userInput.length())).trim();
                    // sort by character
                    if (inputType.equals("char")) {
                        char cInput = (userInput.charAt(userInput.length() - 1));
                        if (SortCommands.checkValidChar(cInput)) {
                            SortCommands.addChar(cInput);
                        }
                    }
                    // sort by tags
                    else if (inputType.equalsIgnoreCase("tags")
                            || inputType.equalsIgnoreCase("tag")) {
                        SortCommands.addTags(inputMsg);
                    }
                    // remove character
                    else if (inputType.equalsIgnoreCase("rm char")) {
                        char cInput = (userInput.charAt(userInput.length() - 1));
                        if (SortCommands.checkValidChar(cInput)) {
                            SortCommands.rmvChar(cInput);
                        }
                    }
                    // remove tags
                    else if (inputType.equalsIgnoreCase("rm tags")
                            || inputType.equalsIgnoreCase("rm tag")) {
                        SortCommands.rmvTags(inputMsg);
                    }
                    // sort by prefix
                    else if (inputType.equalsIgnoreCase("prefix")) {
                        SortCommands.setPrefix(inputMsg);
                        SortCommands.addPrefix();
                        FilterWords.getNewWords().printWordList();
                    }
                    // sort by suffix
                    else if (inputType.equalsIgnoreCase("suffix")) {
                        SortCommands.setSuffix(inputMsg);
                        SortCommands.addSuffix();
                        FilterWords.getNewWords().printWordList();
                    }
                    // list all possible tags
                    else if (inputType.equalsIgnoreCase("list all")) {
                        System.out.println("All tags you can use: ");
                        SortCommands.printAllTags();
                    }
                    // list current tags in use
                    else if (inputType.equalsIgnoreCase("list current")) {
                        SortCommands.printWorkingTags();
                    }
                    // else, not a valid input
                    else {
                        checkValid(false, userInput);
                    }

                }
                // if user mistypes, this may lead to an out of bound exception
                catch (IndexOutOfBoundsException i) {
                    checkValid(false, userInput);
                }
            } else {
                // clear wordlist
                if (userInput.equalsIgnoreCase("clear")) {
                    FilterWords.getNewWords().clear();
                    FilterWords.getNewWords().printWordList();
                }
                // save wordlist
                else if (userInput.equalsIgnoreCase("save")) {
                    SortCommands.saveNewWordList();
                    System.out.println("Saving...");
                }
                // clear char list
                else if (userInput.equalsIgnoreCase("clear char")) {
                    SortCommands.clearChar();
                }
                // clear tag list
                else if (userInput.equalsIgnoreCase("clear tags")
                        || userInput.equalsIgnoreCase("clear tag")) {
                    SortCommands.clearTags();
                }
                // clear prefix
                else if (userInput.equalsIgnoreCase("clear prefix")) {
                    SortCommands.clearPrefix();
                    FilterWords.getNewWords().printWordList();
                }
                // clear suffix
                else if (userInput.equalsIgnoreCase("clear suffix")) {
                    SortCommands.clearSuffix();
                    FilterWords.getNewWords().printWordList();
                }
                // list words that match 50% of tags
                else if (userInput.equalsIgnoreCase("list best matched")) {
                    // default setting of word >= 50% matching
                    SortCommands.sortWordsExclusive();
                    FilterWords.getNewWords().printWordList();
                } else {
                    checkValid(false, userInput);
                }
            }
        }

    }

    /**
     * The main method, where program execution begins.
     * 
     * 
     * @param args Any command-line arguments
     */
    public static void main(String[] args) {
        // read words from file and create WordList
        SortCommands.setUpWords();
        System.out.println(WELCOME_MSG);
        System.out.println(COMMANDS);
        System.out.println("");
        while (true) {
            System.out.println(TYPE);
            String userInput = scan.nextLine();
            System.out.println("");
            if (userInput.equalsIgnoreCase("end")) {
                System.out.println("End sort. Save before ending? Type save if so.");
                if (scan.nextLine().equalsIgnoreCase("save")) {
                    System.out.println("Saving...");
                    SortCommands.saveNewWordList();
                } else {
                    System.out.println("Not saving, now end sorting...");
                }
                break;
            }
            if (userInput.equals("man")) {
                System.out.println(COMMANDS);
                continue;
            }
            runSort(userInput);
            System.out.println("");
        }
    }
}