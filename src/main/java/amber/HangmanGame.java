package amber;

import java.util.Scanner;

public class HangmanGame implements Runnable {
    @Override
    public void run() {
        System.out.println("Welcome to HangmanGame!");
        String word = getWord();
        HangmanGameManager manager = new HangmanGameManager(word);
        startGame(manager);
    }

    private void startGame(HangmanGameManager manager) {
        Scanner scanner = new Scanner(System.in);
        String letter;
        while (!manager.isGameOver()) {
            System.out.println("Word: " + manager.getGuessedWord());
            System.out.println("Guess the letter");
            letter = scanner.next();
            manager.guessLetter(letter);
        }
    }

    private String getWord() {
        return "cat";
    }
}
