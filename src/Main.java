import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Hangman!");
    }
}
// hello
class HangMan {
    String secretWord;
    int incorrectGuesses;
    char currentGuess;
    int maxGuesses;
    Boolean gameOver;
}
class wordBank {
    ArrayList<String> cars = new ArrayList<String>();
    ArrayList<String> wordBankCustom  = new ArrayList<String>();

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

}