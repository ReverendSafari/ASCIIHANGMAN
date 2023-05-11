import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

class wordBank {
    static ArrayList<String> wordBankStatic = new ArrayList<>(Arrays.asList("apple", "banana", "cherry", "date", "safari", "lainey", "gamer",
            "hamid", "java", "eclipse", "ice cream", "heart", "skittles", "veganism"));
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
                wordBankCustom.add(currentWord.toLowerCase());
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
            return wordBankStatic.get(randomNum);
        }
    }
    public void printBank() {
        for (int i=0; i<wordBankCustom.size(); i++) {
            System.out.println(wordBankCustom.get(i));
        }
    }
    public static void main(String[] args) {
        HangManGame game = new HangManGame();
        game.playGame();

    }

}

// hello
class HangMan {
    /*
    Need a method to check current guess, and increment incorrectGuesses, and add to incorrect bank OR update status label
    Need a way to make sure they are just typing one letter
    Make Sure everything is getting lowercased

    */
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
        String input;
        boolean isValid;

        do {
            System.out.println("Please enter a letter to guess: ");
            input = s.next().trim().toLowerCase();
            isValid = input.matches("[a-z]");

            if (!isValid) {
                System.out.println("Invalid input. Please enter a single letter (a-z).");
            }
        } while (!isValid);

        this.currentGuess = input;
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
    /*
    Need to create game LOOP
    Need to create Starting Screen
    Game loop should be based on one big function, that prints the player name, then game board then incorrect bank then status bank --
    would be best if we could clear terminal before each function call
    Make sure custom words can't have numbers or non letters?

     */
    String statusLabel;

    public void setPlayerLabel(String playerLabel) {
        this.playerLabel = playerLabel;
    }
    public void printPlayerLabel() {
        System.out.println("Player: " + playerLabel);
    }

    String playerLabel;
    String incorrectBank = "";
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
        System.out.println("Incorrect Guesses:" + incorrectBank);
    }


    public void setStatusLabel() {
        statusLabel = secretWord.replaceAll("[a-zA-Z]", "_");
    }
    public void getStatusLabel() {
        System.out.println(statusLabel);
    }

    public boolean checkVictory() {
        if(secretWord.equals(statusLabel)) {
            System.out.println("V I C T O R Y");
            return true;
        }
        return false;
    }
    public void updateStatusLabel() {
        char c = getCurrentGuess().charAt(0);
        for(int i = 0; i < statusLabel.length(); i++) {
            if(secretWord.charAt(i) == c) {
                statusLabel = statusLabel.substring(0,i) + c + statusLabel.substring(i+1);
            }
        }
    }
    public void startGame(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Hangman!");
        System.out.print("Please enter your name: ");

        String playerName = scanner.nextLine().trim();
        setPlayerLabel(playerName);

        System.out.println("Hello " + playerName + "\nType \"1\" if you would like to make a custom word bank or \"0\" if you want to play with the default: " );
        int bankBool = scanner.nextInt();
        if (bankBool == 1) {
            wordBank.createWordBank(); // Add this line
            setSecretWord(true);
        } else if (bankBool == 0) {
            setSecretWord(false);
        } else {
            System.out.println("ADMIN pls put something in here to make sure its an int");
        }
        setStatusLabel();
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        startGame();


        while (!gameOver) {
            printPlayerLabel();
            getStage(incorrectGuesses);
            getIncorrect();
            getStatusLabel();

            setCurrentGuess();
            String currentGuess = getCurrentGuess().toLowerCase();

            if (secretWord.contains(currentGuess)) {
                updateStatusLabel();
            } else {
                incorrectGuesses++;
                addIncorrect(currentGuess);
            }

            if (checkVictory()) {
                System.out.println("Congratulations, " + playerLabel + "! You guessed the secret word: " + secretWord);
                gameOver = true;
            } else if (CheckGameOver()) {
                System.out.println("Game over, " + playerLabel + ". The secret word was: " + secretWord);
                gameOver = true;
            }
        }
    }

}
