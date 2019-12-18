package amber;

public class HangmanGame {
    private final String LETTER_PATTERN = "[a-z]";
    private final char SPACE_CHAR = '_';
    private char[] word;
    private char[] guessedWord;

    public HangmanGame(String word) {
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

    public boolean isWordContainChar(String guessChar) {
        if ((guessChar.length() != 1) || (!guessChar.matches(LETTER_PATTERN))) {
            throw new NotLetterException("Sorry, but '" + guessChar + "' is not a letter");
        }

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

    public boolean isGameOver() {
        for (char aGuessedWord : guessedWord) {
            if (aGuessedWord == SPACE_CHAR) {
                return false;
            }
        }
        return true;
    }
}
