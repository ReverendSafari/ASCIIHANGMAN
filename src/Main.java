import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class wordBank {
    static ArrayList<String> wordBankStatic = new ArrayList<String>();
    static ArrayList<String> wordBankCustom  = new ArrayList<String>();

    public static void createWordBank() {
        Scanner s = new Scanner(System.in);
        String currentWord;
        Boolean stop = true;
        while(stop) {
            System.out.println("Please enter a word to add to the bank--Or type 1 to stop:");
            currentWord = s.nextLine().trim();
            if (currentWord.equals("1")) {
                stop = false;
                System.out.println("Word Bank is complete!");

            }
            else {
                wordBankCustom.add(currentWord);
            }
        }
    }
    public static String generateRandomWord(boolean r) {
        Random selector = new Random();
        if(r) {
            int limit = wordBankCustom.size();
            int randomNum = selector.nextInt(limit);
            return wordBankCustom.get(randomNum);
        }
        else {
            int limit = wordBankStatic.size();
            int randomNum = selector.nextInt(limit);
            return wordBankCustom.get(randomNum);
        }
    }
    public void printBank() {
        for (int i=0; i<wordBankCustom.size(); i++) {
            System.out.println(wordBankCustom.get(i));
        }
    }
    public static void main(String[] args) {
        wordBank r = new wordBank();
        createWordBank();
        r.printBank();
    }

}

// hello
class HangMan {
    String secretWord;
    int incorrectGuesses;

    public char getCurrentGuess() {
        return currentGuess;
    }

    public void setCurrentGuess(char currentGuess) {
        this.currentGuess = currentGuess;
    }

    char currentGuess;
    int maxGuesses = 6;
    Boolean gameOver = false;

    void Hangman(boolean c) {
        if (c) {
            secretWord = wordBank.generateRandomWord(c);
        }
        else {
            secretWord = wordBank.generateRandomWord(c);
        }

    }
    boolean CheckGameOver() {
        if (incorrectGuesses >= maxGuesses) {
            gameOver = true;
            return gameOver;
        }
        return gameOver;
    }
}

class HangManGame extends HangMan {
    String[] statusLabel;
    String playerLabel;
    String[] boardStates;
}
