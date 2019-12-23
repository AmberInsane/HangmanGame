package amber;

import java.util.HashSet;
import java.util.Set;

public class HangmanGameManager {
    private final String LETTER_PATTERN = "[a-z]";
    private final char SPACE_CHAR = '*';
    private char[] word;
    private char[] guessedWord;
    private int counter = 0;
    private Set<Character> alreadyGuessed = new HashSet<>();

    private HangmanPicture hangmanPicture = HangmanPicture.getInstance();

    public HangmanGameManager(String word) {
        this.word = word.toLowerCase().toCharArray();
        guessedWord = new char[word.length()];
        for (int i = 0; i < guessedWord.length; i++) {
            guessedWord[i] = SPACE_CHAR;
        }
    }

    public String getWord() {
        return String.valueOf(word);
    }

    public String getGuessedWord() {
        return String.valueOf(guessedWord);
    }

    public void guessLetter(String letter) {
        try {
            checkChar(letter);
            if (alreadyGuessed.contains(letter.charAt(0))) {
                System.out.println("You have already tried letter " + letter);
            } else {
                if (isWordContainChar(letter)) {
                    System.out.println("Nice try!");
                } else {
                    System.out.println("Sorry, I can't find " + letter);
                    System.out.println(hangmanPicture.getHangmen().get(counter++));
                }
                alreadyGuessed.add(letter.charAt(0));
                System.out.println("Number of left errors is " + (hangmanPicture.getHangmen().size() - counter));
            }
        } catch (NotLetterException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean isWordContainChar(String guessChar) {
        char currentChar = guessChar.charAt(0);
        boolean isContain = false;
        int counter = 0;
        for (int i = 0; i < word.length; i++) {
            if (word[i] == currentChar) {
                guessedWord[i] = currentChar;
                counter++;
            }
        }
        if (counter > 0) {
            isContain = true;
        }
        return isContain;
    }

    public void checkChar(String guessChar) {
        if ((guessChar.length() != 1) || (!guessChar.matches(LETTER_PATTERN))) {
            throw new NotLetterException("Sorry, but '" + guessChar + "' is not a letter");
        }
    }

    public boolean isGameOver() {
        if (hangmanPicture.getHangmen().size() == 0) {
            return true;
        }
        if (counter == hangmanPicture.getHangmen().size()) {
            System.out.println("You lose! The word was " + getWord());
            return true;
        }
        for (char aGuessedWord : guessedWord) {
            if (aGuessedWord == SPACE_CHAR) {
                return false;
            }
        }
        System.out.println("You won! The word was " + getWord());
        System.out.println("Congratulations!");

        return true;
    }
}
