import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

class wordBank {
    static ArrayList<String> wordBankStatic = new ArrayList<>(Arrays.asList("apple", "banana", "cherry", "date"));
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
        //testing wordbank
//        wordBank r = new wordBank();
//        createWordBank();
//        r.printBank();

        //testing basic board prints
        HangManGame testGame = new HangManGame();
        testGame.setPlayerLabel("Safari");
        testGame.printPlayerLabel();
        testGame.addIncorrect("r");
        testGame.addIncorrect("s");
        testGame.addIncorrect("v");
        testGame.setSecretWord(true);
        System.out.println(testGame.getWord());

        testGame.getStage(3);
        testGame.getIncorrect();

    }

}

// hello
class HangMan {
    String secretWord;
    int incorrectGuesses;
    String currentGuess;
    int maxGuesses = 6;
    Boolean gameOver = false;

    public String getCurrentGuess() {
        return currentGuess;
    }
    public void setCurrentGuess() {
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter a letter to guess: ");
        this.currentGuess = s.next().trim();
    }
    void setSecretWord(boolean c) {
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
    public String getWord() {
        return secretWord;
    }
}

class HangManGame extends HangMan {
    //
    String statusLabel;

    public void setPlayerLabel(String playerLabel) {
        this.playerLabel = playerLabel;
    }
    public void printPlayerLabel() {
        System.out.println("Player: " + playerLabel);
    }

    String playerLabel;
    String incorrectBank;
    String[] hangmanStages = new String[] {
            "+--+ \n|    \n|    \n|    \n|    \n|    \n=========",
            "+--+ \n|  O \n|    \n|    \n|    \n|    \n=========",
            "+--+ \n|  O \n|  |  \n|  |  \n|    \n|    \n=========",
            "+--+ \n|  O \n| /|  \n|  |  \n|    \n|    \n=========",
            "+--+ \n|  O \n| /|\\ \n|  |  \n|    \n|    \n=========",
            "+--+ \n|  O \n| /|\\ \n|  |  \n| /   \n|    \n=========",
            "+--+ \n|  O \n| /|\\ \n|  |  \n| / \\ \n|    \n========="
    };

    public void getStage(int n) {
        System.out.println(hangmanStages[n]);
    }
    public void addIncorrect(String s) {
        incorrectBank = incorrectBank + " " + s;
    }
    public void getIncorrect() {
        System.out.println("Incorrect Guesses:" + incorrectBank.substring(4));
    }
    public void setStatusLabel() {
        statusLabel = secretWord.replaceAll("[a-zA-Z]", "_");
    }
    public void getStatusLabel() {
        System.out.println(statusLabel);
    }
    public void updateStatusLabel() {
        char c = getCurrentGuess().charAt(0);
        for(int i = 0; i < statusLabel.length(); i++) {
            if(secretWord.charAt(i) == c) {
                statusLabel = statusLabel.substring(0,i) + c + statusLabel.substring(i+1);
            }
        }
    }
}
