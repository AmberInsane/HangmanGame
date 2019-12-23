package amber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class HangmanPicture {
    private final static String HANGMAN_RESOURCE = "Hangman ASCII.txt";
    private final static String HANGMAN_INDICATOR = "Hangman";
    private static HangmanPicture instance;
    private List<String> hangmen;

    private HangmanPicture() {
        hangmen = getHangmenFromResources();
    }

    private List<String> getHangmenFromResources() {
        ArrayList<String> hangmanList = new ArrayList<>();

        try (InputStream resource = ClassLoader.getSystemResourceAsStream(HANGMAN_RESOURCE);
             BufferedReader reader = new BufferedReader(new InputStreamReader(resource))) {
            String line;
            StringBuilder hangmanString = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                if (line.trim().equals(HANGMAN_INDICATOR)) {
                    if (hangmanString.length() > 0) {
                        hangmanList.add(hangmanString.toString());
                        hangmanString.setLength(0);
                    }
                } else {
                    hangmanString.append(line).append("\n");
                }
            }
            hangmanList.add(hangmanString.toString());
        } catch (NullPointerException e) {
            System.err.println("Hangmen resource not found");
        }
         catch (IOException e) {
            System.err.println("Problem with reading hangmen from file");
        }

        return hangmanList;
    }

    public List<String> getHangmen() {
        return hangmen;
    }

    public static synchronized HangmanPicture getInstance() {
        if (instance == null) {
            instance = new HangmanPicture();
        }
        return instance;
    }
}
