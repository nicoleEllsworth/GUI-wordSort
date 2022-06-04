# GUI-wordSort
GUI that allows user to filter and sort word lists by prefix, suffix, tags, and starting letter. This application is primarily designed to help writers (especially poets) find the word they are looking for. The user can also save the words to the sortedWords.txt file. 

The application exclusively sorts by the first letter, while the rest of the sorting is by default, inclusive. However, the wordlist is sorted by words that match the most tags, so the user knows which word is most relevant.

The user also has the option to elect for exclusive sorting (all listed words must meet 100% of sorting parameters). Or, for more leniency, the user can choose partial exclusivity, which means that all listed words will have a >= 50% match with tags.

The dictionary for this application (called words.txt) was created via webscraping and manual editing; it is still in progress. Anyone is welcome to add words and tags to this list (see current tag list in the WordForm.java class). Further sorting algorithms may be interesting to add as well, such as sorting by length, rhyme, etc. 
