///////////////////////////////////////////////////////////////////////////////
// Branch:             GUI-wordSort
// Main Class File:    WordForm.java
// File:               WordForm.java
// Date:               June 2022
//
// Author:             Nicole  Ellsworth
///////////////////////////////////////////////////////////////////////////////

import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;

/**
 * The WordForm class represents a GUI form that creates
 * sorted word lists.
 *
 * Bugs: None known.
 *
 * @author Nicole Ellsworth.
 */
public class WordForm {

    private JFrame frame;
    private JTextField workingPreField;
    private JTextField workingSuffField;
    private JTextArea wordListText;
    private JRadioButton rButtonExMatch;
    private JRadioButton rButtonHalfMatch;
    private JRadioButton rButtonIncMatch;
    private JComboBox<String> tagComboBox;
    private JComboBox<String> charComboBox;
    private JComboBox<String> rmTagscomboBox;
    private ArrayList<String> tags = new ArrayList<String>();
    public static final Color MY_COLOR = new Color(209, 223, 224);

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    WordForm window = new WordForm();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public WordForm() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        // initialize frame
        frame = new JFrame();
        frame.setBounds(100, 100, 1000, 800);
        frame.getContentPane().setBackground(MY_COLOR);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        // prefix entry////////////////////////////////////////////////////////
        JLabel prefixlbl = new JLabel("Add prefix");
        prefixlbl.setBounds(65, 30, 100, 20);
        prefixlbl.setFont(new Font("Verdana", Font.PLAIN, 16));
        frame.getContentPane().add(prefixlbl);

        JTextField prefixField = new JTextField("");
        prefixField.setBounds(200, 28, 86, 20);
        frame.getContentPane().add(prefixField);
        prefixField.setColumns(10);

        // suffix entry////////////////////////////////////////////////////////
        JLabel suffixlbl = new JLabel("Add suffix");
        suffixlbl.setFont(new Font("Verdana", Font.PLAIN, 16));
        suffixlbl.setBounds(65, 90, 100, 20);
        frame.getContentPane().add(suffixlbl);

        JTextField suffixField = new JTextField("");
        suffixField.setBounds(200, 90, 86, 20);
        frame.getContentPane().add(suffixField);
        suffixField.setColumns(10);

        // tags entry//////////////////////////////////////////////////////////
        JLabel taglbl = new JLabel("Add tags");
        taglbl.setFont(new Font("Verdana", Font.PLAIN, 16));
        taglbl.setBounds(65, 150, 100, 25);
        frame.getContentPane().add(taglbl);
        // tag drop down
        tagComboBox = new JComboBox<String>();
        String[] tagNames = {
                "none", "adjective", "adverb", "animal", "beautiful", "black", "blue",
                "brown", "city", "color", "conjunction", "dark", "DFW",
                "flavor", "green", "grey", "happy", "idiom", "interjection", "large",
                "light", "nature", "noun", "old", "orange", "people", "pink", "place",
                "preposition", "pronoun", "purple", "red", "religion", "sad", "sensory",
                "smell", "sound", "taste", "terrain", "texture", "touch", "verb",
                "visual", "weather", "white", "yellow"
        };
        for (String tag : tagNames) {
            tagComboBox.addItem(tag);
        }
        tagComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });
        tagComboBox.setBounds(200, 150, 91, 20);
        tagComboBox.setFont(new Font("Serif", Font.PLAIN, 16));
        frame.getContentPane().add(tagComboBox);

        // first letter entry//////////////////////////////////////////////////
        JLabel charlbl = new JLabel("First letter");
        charlbl.setFont(new Font("Verdana", Font.PLAIN, 16));
        charlbl.setBounds(65, 210, 100, 20);
        frame.getContentPane().add(charlbl);
        // first letter drop down
        charComboBox = new JComboBox<String>();
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        charComboBox.addItem("none");
        for (char c : alphabet) {
            charComboBox.addItem(Character.toString(c));
        }
        charComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });
        charComboBox.setBounds(200, 210, 91, 20);
        charComboBox.setFont(new Font("Serif", Font.PLAIN, 16));
        frame.getContentPane().add(charComboBox);

        // remove tags entry///////////////////////////////////////////////////
        JLabel rmTagslbl = new JLabel("Remove Tags");
        rmTagslbl.setFont(new Font("Verdana", Font.PLAIN, 16));
        rmTagslbl.setBounds(65, 270, 120, 25);
        frame.getContentPane().add(rmTagslbl);

        rmTagscomboBox = new JComboBox<String>();
        rmTagscomboBox.addItem("none");

        rmTagscomboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });
        rmTagscomboBox.setBounds(200, 270, 91, 20);
        rmTagscomboBox.setFont(new Font("Serif", Font.PLAIN, 16));
        frame.getContentPane().add(rmTagscomboBox);

        // exclusive sorting entry/////////////////////////////////////////////
        JLabel exMatchlbl = new JLabel("100% match");
        exMatchlbl.setFont(new Font("Verdana", Font.PLAIN, 12));
        exMatchlbl.setBounds(65, 340, 100, 14);
        frame.getContentPane().add(exMatchlbl);

        rButtonExMatch = new JRadioButton("");
        rButtonExMatch.setBackground(MY_COLOR);
        rButtonExMatch.setBounds(65, 360, 20, 23);
        frame.getContentPane().add(rButtonExMatch);
        rButtonExMatch.setSelected(false);

        // match >= 50% sorting entry//////////////////////////////////////////
        JLabel halfMatchlbl = new JLabel(">= 50% match");
        halfMatchlbl.setFont(new Font("Verdana", Font.PLAIN, 12));
        halfMatchlbl.setBounds(170, 340, 100, 14);
        frame.getContentPane().add(halfMatchlbl);

        rButtonHalfMatch = new JRadioButton("");
        rButtonHalfMatch.setBackground(MY_COLOR);
        rButtonHalfMatch.setBounds(170, 360, 20, 23);
        frame.getContentPane().add(rButtonHalfMatch);
        rButtonHalfMatch.setSelected(false);

        // inclusive sorting entry/////////////////////////////////////////////
        JLabel inclbl = new JLabel("> 0% match");
        inclbl.setFont(new Font("Verdana", Font.PLAIN, 12));
        inclbl.setBounds(280, 340, 100, 14);
        frame.getContentPane().add(inclbl);

        rButtonIncMatch = new JRadioButton("");
        rButtonIncMatch.setBackground(MY_COLOR);
        rButtonIncMatch.setBounds(280, 360, 20, 23);
        frame.getContentPane().add(rButtonIncMatch);
        rButtonIncMatch.setSelected(true);

        // sorting-exclusivity button group////////////////////////////////////
        ButtonGroup group = new ButtonGroup();
        group.add(rButtonExMatch);
        group.add(rButtonHalfMatch);
        group.add(rButtonIncMatch);

        // working tag entry///////////////////////////////////////////////////
        JLabel workingTags = new JLabel("Working tags: ");
        workingTags.setFont(new Font("Verdana", Font.PLAIN, 16));
        workingTags.setBounds(65, 400, 500, 25);
        frame.getContentPane().add(workingTags);

        JTextField wTags = new JTextField("");
        wTags.setBounds(200, 408, 200, 40);
        frame.getContentPane().add(wTags);
        wTags.setEditable(false);
        wTags.setBackground(Color.WHITE);
        JScrollPane scroll2 = new JScrollPane(wTags);
        scroll2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll2.setBounds(200, 400, 200, 40);
        frame.getContentPane().add(scroll2);

        // working prefix entry////////////////////////////////////////////////
        JLabel workingPrelbl = new JLabel("Working Prefix: ");
        workingPrelbl.setFont(new Font("Verdana", Font.PLAIN, 16));
        workingPrelbl.setBounds(65, 460, 500, 25);
        frame.getContentPane().add(workingPrelbl);

        workingPreField = new JTextField("");
        workingPreField.setBounds(200, 460, 86, 20);
        frame.getContentPane().add(workingPreField);
        workingPreField.setColumns(10);
        workingPreField.setEditable(false);
        workingPreField.setBackground(Color.WHITE);

        // working suffix entry////////////////////////////////////////////////
        JLabel workingSufflbl = new JLabel("Working suffix: ");
        workingSufflbl.setFont(new Font("Verdana", Font.PLAIN, 16));
        workingSufflbl.setBounds(65, 520, 500, 25);
        frame.getContentPane().add(workingSufflbl);

        workingSuffField = new JTextField("");
        workingSuffField.setBounds(200, 520, 86, 20);
        frame.getContentPane().add(workingSuffField);
        workingSuffField.setColumns(10);
        workingSuffField.setEditable(false);
        workingSuffField.setBackground(Color.WHITE);

        // word list entry
        JLabel words = new JLabel("Word List");
        words.setFont(new Font("Verdana", Font.PLAIN, 18));
        words.setBounds(450, 20, 500, 18);
        frame.getContentPane().add(words);

        wordListText = new JTextArea("");
        wordListText.setBounds(450, 60, 500, 650);
        frame.getContentPane().add(wordListText);
        JScrollPane scroll = new JScrollPane(wordListText);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(450, 60, 500, 650);
        frame.getContentPane().add(scroll);

        // clear button////////////////////////////////////////////////////////
        JButton btnClear = new JButton("Clear all");

        btnClear.setBackground(new Color(189, 14, 2));
        btnClear.setForeground(Color.WHITE);
        btnClear.setBounds(312, 670, 100, 23);
        btnClear.setFont(new Font("Verdana", Font.PLAIN, 14));
        frame.getContentPane().add(btnClear);

        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                prefixField.setText(null);
                suffixField.setText(null);
                rButtonExMatch.setSelected(false);
                rButtonHalfMatch.setSelected(false);
                rButtonIncMatch.setSelected(true);
                tagComboBox.setSelectedItem("none");
                charComboBox.setSelectedItem("none");
                rmTagscomboBox.setSelectedItem("none");
                wTags.setText("");
                workingPreField.setText("");
                workingSuffField.setText("");
                wordListText.setText("");
                tags.clear();
            }
        });

        // submit/save button//////////////////////////////////////////////////
        JButton btnSubmit = new JButton("Save");

        btnSubmit.setBackground(Color.BLUE);
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.setBounds(65, 670, 89, 23);
        btnSubmit.setFont(new Font("Verdana", Font.PLAIN, 14));
        frame.getContentPane().add(btnSubmit);

        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (wordListText.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Data Missing");
                } else {
                    JOptionPane.showMessageDialog(null, "Data Submitted");
                }
                SortCommands.saveNewWordList();
            }
        });

        // update list button
        JButton btnUpdate = new JButton("Update");

        btnUpdate.setBounds(180, 670, 100, 23);
        btnUpdate.setFont(new Font("Verdana", Font.PLAIN, 14));
        frame.getContentPane().add(btnUpdate);

        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add tags to tag list////////////////////////////////////////
                if (!tags.contains(tagComboBox.getSelectedItem().toString()) &&
                        !tagComboBox.getSelectedItem().toString().equals("none")) {
                    tags.add(tagComboBox.getSelectedItem().toString());
                }
                if (!tags.contains(charComboBox.getSelectedItem().toString()) &&
                        !charComboBox.getSelectedItem().toString().equals("none")) {
                    tags.add(charComboBox.getSelectedItem().toString());
                }
                if (!rmTagscomboBox.getSelectedItem().toString().equals("none")) {
                    tags.remove(rmTagscomboBox.getSelectedItem().toString());
                }

                // create wordlist/////////////////////////////////////////////
                if (prefixField.getText().length() > 0) {
                    workingPreField.setText(prefixField.getText());
                }
                if (suffixField.getText().length() > 0) {
                    workingSuffField.setText(suffixField.getText());
                }
                sortWords();
                // determine exlcusive/inclusive sorting
                if (rButtonExMatch.isSelected()) {
                    SortCommands.sortWordsExclusive(1);
                } else if (rButtonHalfMatch.isSelected()) {
                    SortCommands.sortWordsExclusive(.5);
                }

                // display relevant text to frame//////////////////////////////
                String tagString = tags.toString();
                wTags.setText(tagString);
                wordListText.setText(FilterWords.getNewWords().formatWordList());

                // updating remove tags combo boxes to current tags////////////
                rmTagscomboBox.removeAllItems();
                rmTagscomboBox.addItem("none");
                for (String elem : tags) {
                    rmTagscomboBox.addItem(elem);
                }
                // reset all fields////////////////////////////////////////////
                prefixField.setText(null);
                suffixField.setText(null);
                rButtonExMatch.setSelected(false);
                rButtonHalfMatch.setSelected(false);
                rButtonIncMatch.setSelected(true); // inclusive default
                tagComboBox.setSelectedItem("none");
                charComboBox.setSelectedItem("none");
                rmTagscomboBox.setSelectedItem("none");
            }
        });
    }

    /**
     * Sets values in SortCommand class to
     * user input
     *
     * @return void.
     */
    public void setSortValues() {
        ArrayList<Character> charlist = new ArrayList<Character>();
        ArrayList<String> tlist = new ArrayList<String>();
        // create character and tag lists
        for (String elem : tags) {
            if (elem.length() == 1) {
                charlist.add(elem.charAt(0));
            } else {
                tlist.add(elem);
            }
        }
        // set variables
        SortCommands.setCharList(charlist);
        SortCommands.setTagList(tlist);
        // add prefix and suffix if relevant
        if (workingPreField.getText().length() > 0) {
            SortCommands.setPrefix(workingPreField.getText().trim());
        }
        if (workingSuffField.getText().length() > 0) {
            SortCommands.setSuffix(workingSuffField.getText().trim());
        }
    }

    /**
     * Sorts words into wordlist from input
     *
     * @return void.
     */
    public void sortWords() {
        // read words from text file
        SortCommands.setUpWords();
        setSortValues();
        SortCommands.addPrefix();
        SortCommands.addSuffix();
        // filter words, then rank them
        SortCommands.filterAndSort();
    }
}
