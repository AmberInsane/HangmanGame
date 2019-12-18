package amber;

import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        HangmanGame hg = new HangmanGame("Ololo");
        Scanner scanner = new Scanner(System.in);
        String letter;

        while (!hg.isGameOver()) {
            System.out.println(hg.getGuessedWord());
            try {
                System.out.println("Guess the letter");
                letter = scanner.next();
                if (hg.isWordContainChar(letter)) {
                    System.out.println("Nice try!");
                } else {
                    System.out.println("Sorry, I can't find " + letter);
                }
            } catch (NotLetterException e) {
                System.out.println(e.getMessage());
                System.out.println("Let's try again");
            }
        }

        System.out.println("The word is " + hg.getGuessedWord());
        System.out.println("Congratulations!");
    }
}
