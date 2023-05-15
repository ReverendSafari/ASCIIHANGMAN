import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
//Important Imports ^^

//This class is for storing an generating the wordbank used for the game
class wordBank {
    //This is the static wordbank that the game comes with
    static ArrayList<String> wordBankStatic = new ArrayList<>(Arrays.asList("apple", "banana", "cherry", "date"));
    //This is the customizable wordbank the player can choose to add too, keeping both word bank as ArrayList
    //to make using them eaiser.
    static ArrayList<String> wordBankCustom  = new ArrayList<String>();

    //This function generates the custom word bank with the player
    //Using a scanner to take user input and add the user's words to the custom word bank
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

    //This function selects a random word from one of the two words bank, which is decided based upon the Bool Value passed in
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
    //Test method to allow us that the custom word bank is being populated correctly
    public void printBank() {
        for (int i=0; i<wordBankCustom.size(); i++) {
            System.out.println(wordBankCustom.get(i));
        }
    }
    public static void main(String[] args) {
        HangManGame game;
        boolean playAgain;

        do {
            game = new HangManGame();
            game.playGame();
            playAgain = game.playAgain();
        } while (playAgain);
    }


}
//HangMan class is used to store important game info as well as accept user input for the game
class HangMan {
    //Stores the current secretWord being used in the game
    String secretWord;
    //Keeps track of the number of wrong guesses in the current game
    int incorrectGuesses;
    //Stores the last guess added from the user
    String currentGuess;
    //Hardcoded maxGuesses since it never changes
    int maxGuesses = 6;
    //gameOver Var thats initially set to false so the game loop can work
    Boolean gameOver = false;
    //Returns the current guess
    public String getCurrentGuess() {
        return currentGuess;
    }
    //Uses a scanner object to take a user guessed letter, and lowercases it, also tests to make sure the user is entering
    //a single letter and not some invalid input
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

    //Uses wordBank method to get a random word from one of the word banks depending on the value of the bool passed in
    void setSecretWord(boolean c) {
        if (c) {
            secretWord = wordBank.generateRandomWord(c);
        }
        else {
            secretWord = wordBank.generateRandomWord(c);
        }

    }
    //Method to make sure the user has not surpassed the maxGuesses, and if they do return a true value so the loop can end
    boolean CheckGameOver() {
        if (incorrectGuesses >= maxGuesses) {
            gameOver = true;
            return gameOver;
        }
        return gameOver;
    }
    //Test method to make sure the secretWord is being set
    public String getWord() {
        return secretWord;
    }
}
//This class is a subclass of HangMan that deals with the actual game functions/game loop/and visual aspects of the game
class HangManGame extends HangMan {
    //Method after the game ends to prompt user if they want to start another game
    //Uses scanner to take user input to decide whether to stop or start new game
    public boolean playAgain() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to play again? (y/n)");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("y");
    }
    //Label to keep track of the hidden letters and properly guessed letters
    String statusLabel;
    //Sets the users name so it can be printed on the screen
    public void setPlayerLabel(String playerLabel) {
        this.playerLabel = playerLabel;
    }
    //Prints the players name on the board
    public void printPlayerLabel() {
        System.out.println("Player: " + playerLabel);
    }
    //Stores the players name
    String playerLabel;
    //Intializes the bank of incorrect guesses, and stores further incorrect guesses
    String incorrectBank = "";
    //Stores the different visual states of the hangman board
    String[] hangmanStages = new String[] {
            "+--+ \n|    \n|    \n|    \n|    \n|    \n=========",
            "+--+ \n|  O \n|    \n|    \n|    \n|    \n=========",
            "+--+ \n|  O \n|  |  \n|  |  \n|    \n|    \n=========",
            "+--+ \n|  O \n| /|  \n|  |  \n|    \n|    \n=========",
            "+--+ \n|  O \n| /|\\ \n|  |  \n|    \n|    \n=========",
            "+--+ \n|  O \n| /|\\ \n|  |  \n| /   \n|    \n=========",
            "+--+ \n|  O \n| /|\\ \n|  |  \n| / \\ \n|    \n========="
    };
    //Gets and prints (displays) the current state of the hangman board
    public void getStage(int n) {
        System.out.println(hangmanStages[n]);
    }
    //Adds a incorrect guess to the incorrect bank
    public void addIncorrect(String s) {
        incorrectBank = incorrectBank + " " + s;
    }
    //Prints the incorrect guesses
    public void getIncorrect() {
        System.out.println("Incorrect Guesses:" + incorrectBank);
    }

    //Reads the secretWord and hides all the letters with underscores
    public void setStatusLabel() {
        statusLabel = secretWord.replaceAll("[a-zA-Z]", "_");
    }
    //Prints the status label
    public void getStatusLabel() {
        System.out.println(statusLabel);
    }
    //Checks whether the player has guessed every letter correctly
    public boolean checkVictory() {
        if(secretWord.equals(statusLabel)) {
            System.out.println("V I C T O R Y");
            return true;
        }
        return false;
    }
    //Updates the hidden word with a correct guess from the user
    public void updateStatusLabel() {
        char c = getCurrentGuess().charAt(0);
        for(int i = 0; i < statusLabel.length(); i++) {
            if(secretWord.charAt(i) == c) {
                statusLabel = statusLabel.substring(0,i) + c + statusLabel.substring(i+1);
            }
        }
    }
    //Method that STARTS a new game, creates a scanner, prompts them for their name and wordbank choice
    //Generates a custom word bank if that is selected
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
            System.out.println("Not a choice!");
        }
        setStatusLabel();
    }
    //Main game loop
    //Starts the game and goes thru loop for user to play the game
    //While loop keeps checking if the game is LOST or WON, or still going
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
