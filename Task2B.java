import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Task2B {// RGU ID : 2237953

    static String [] lexicon = new String[350];
    static ArrayList<String> suggestions = new ArrayList();
    static String yourWord;
    static Scanner input = new Scanner(System.in);



    public static void main(String[] args) {

        File wordsText = new File("src/wordsDictionary.txt"); // giving access to the words Dictionary text file
        int index=0;
        try{
            Scanner scanner = new Scanner(wordsText);
            while (scanner.hasNextLine() && index < 350){
                String line = scanner.nextLine();
                lexicon[index] = line;
                index++; // adding the word one by one to the lexicon array.
            }
            scanner.close();
        }catch(FileNotFoundException e){
            System.out.println("File not found");
        }

        System.out.println("Enter your word: "); // taking the user input
        yourWord = input.next();

        correctWordSpellChecker(yourWord); // immediately calling the correctWordSpellChecker() method to check the word is correct
        for (int i=0; i<lexicon.length; i++) {
            swappingLettersSpellChecker(yourWord, lexicon[i]);// calling swappingLettersSpellChecker() method to suggest the correct word when letter were in wrong order
            insertLetterSpellChecker(yourWord, lexicon[i]);// calling insertLetterSpellChecker() method to suggest the correct word when a letter was missed comparing the correct word
            removeLetterSpellChecker(yourWord, lexicon[i]);// calling removeLetterSpellChecker() method to suggest the correct word when there is an extra letter comparing the correct word
            replaceLetterSpellChecker(yourWord, lexicon[i]);// calling replaceLetterSpellChecker() method to suggest the correct word when there is a wrong letter comparing the correct word

        }
        System.out.println("Suggestion : "+suggestions);
    }
    public static void correctWordSpellChecker(String a) {
        if (suggestions.size() == 0) {
            for (int i = 0; i < lexicon.length; i++) {
                if (a.equals(lexicon[i])) {
                    suggestions.clear(); // used clear to clear former suggestions
                    suggestions.add(a);
                }
            }
        }
    }
    public static void swappingLettersSpellChecker(String a, String correctWord) {
        if (suggestions.size() == 0 && a.length() == correctWord.length() && a.charAt(0)==correctWord.charAt(0)) {
            // check a and correctWord have the same number of words and the 1st letters are equal
            for (int i=1; i<correctWord.length()-1; i++){
                if (a.charAt(i)==correctWord.charAt(i+1) && a.charAt(i+1)==correctWord.charAt(i)){
                    suggestions.clear();
                    // checks if the letters were swapped for example checks if 2nd letter of a and 3rd letter of correctWord are the same
                    suggestions.add(correctWord);
                }
            }
        }
    }

    public static void insertLetterSpellChecker(String a, String correctWord){
        if (suggestions.size() == 0 && a.length()+1 == correctWord.length() && a.charAt(0)==correctWord.charAt(0)){
            // checks if there was a letter missed
            for (int i = 0; i < a.length(); i++) {
                for (char c = 'a'; c <= 'z'; c++) {
                    // this will add a letter (a to z) to every index of the typed word
                    StringBuilder missedLetter = new StringBuilder(a);
                    missedLetter.insert(i, c); // insert part using .insert
                    String added = missedLetter.toString();
                    // this will create a set of words and in the next step finds the equal word
                    if (added.equals(correctWord)){
                        // when one of the created new words is equal to the correctWord correct word will be displayed.
                        suggestions.clear();
                        suggestions.add(correctWord);
                    }
                }
            }
        }
    }

    public static void removeLetterSpellChecker(String a, String correctWord){
        if (suggestions.size() == 0 && a.length()-1 == correctWord.length() && a.charAt(0)==correctWord.charAt(0)){
            for (int i = 0; i < a.length(); i++) {
                // this will remove a letter in every index from typed word
                StringBuilder extraLetter = new StringBuilder(a);
                extraLetter.deleteCharAt(i);// removing part using .deleteCharAt()
                String removed = extraLetter.toString();
                // this will create a set of words and in the next step finds the equal word
                if (removed.equals(correctWord)){
                    suggestions.clear();
                    suggestions.add(correctWord);
                }
            }
        }
    }

    public static void replaceLetterSpellChecker(String a, String correctWord) {
        if (suggestions.size() == 0 && a.length() == correctWord.length() && a.charAt(0) == correctWord.charAt(0)) {
            for (int i = 0; i < a.length(); i++) {
                for (char c = 'a'; c <= 'z'; c++) {
                    // this will replace a letter in every index from typed word
                    StringBuilder wrongLetter = new StringBuilder(a);
                    wrongLetter.setCharAt(i, c);// replace part using .setCharAt()
                    String replace = wrongLetter.toString();
                    // this will create a set of words by replacing every letter of the word and in the next step finds the equal word
                    if (replace.equals(correctWord)) {
                        suggestions.clear();
                        suggestions.add(correctWord);


                    }
                }
            }
        }
    }
}

