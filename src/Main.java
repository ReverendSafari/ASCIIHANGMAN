import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Hangman!");
    }
}
class HangMan {
    String secretWord;
    int incorrectGuesses;
    char currentGuess;
    int maxGuesses;
    Boolean gameOver;
}
class HangManClass {
    int Hangman;
    String PlayerName;
    String[] StatusLabel;
    String[] WordLabel;
    String[] GuessesLabel;
}
class wordBank {
    ArrayList<String> cars = new ArrayList<String>();
    static ArrayList<String> wordBankCustom  = new ArrayList<String>();

    public static void createWordBank() {
        Scanner s = new Scanner(System.in);
        String currentWord;
        String stop = "";
        while(stop == "") {
            currentWord = s.nextLine().trim();
            wordBankCustom.add(currentWord);
        }
    }
}

