import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Hangman {

    public static void main(String[] args){
        boolean gameOngoing = true;
        while(gameOngoing) {
            HashMap<String, Integer> words = new HashMap<>();
            String fileName = "words.txt";
            int size = 0;
            int wrongGuesses = 0;
            Set<String> wordList;
            HashSet<Character> guessedLetters = new HashSet<>();

            //add all the words to the tree
            try {
                Scanner scanner = new Scanner(new File(fileName));
                while (scanner.hasNextLine()) {
                    String word = scanner.nextLine().toLowerCase();
                    words.put(word, word.length());
                }
                scanner.close();

            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }

            Scanner scan = new Scanner(System.in);
            while (true) {
                System.out.println("Enter size of word");
                size = scan.nextInt();
                scan.nextLine();
                if (words.containsValue(size)) break;
                System.out.println("Invalid size");
            }
            System.out.println("Enter number of wrong guesses");
            wrongGuesses = scan.nextInt();
            scan.nextLine();

            words.values().retainAll(Collections.singleton(size));

            wordList = words.keySet();

            while (true) {
                System.out.println("Guessed letters: " + guessedLetters);
                System.out.println("Guesses Remaining: " + wrongGuesses);
                char guess;
                while (true) {
                    System.out.println("Enter next guess");
                    guess = scan.nextLine().toLowerCase().charAt(0);
                    if (Character.isLetter(guess)) {
                        if (!guessedLetters.contains(guess)) {
                            guessedLetters.add(guess);
                            break;
                        }
                        System.out.println("Letter already guessed");
                    } else {
                        System.out.println("Error: Letter not entered");
                    }
                }
                HashMap<String, Set<String>> wordConfigurations = new HashMap<>();
                for (String word : wordList) {
                    String configuration = "";
                    for (Character c : word.toCharArray()) {
                        if (c == guess) configuration += c;
                        else configuration += "-";
                    }
                    if (!wordConfigurations.containsKey(configuration)) wordConfigurations.put(configuration, new HashSet<>());
                    wordConfigurations.get(configuration).add(word);
                }
                HashSet<String> largestSet = new HashSet<>();
                String letterConfiguration = "";
                for (String config : wordConfigurations.keySet()) {
                    if (wordConfigurations.get(config).size() > largestSet.size()) {
                        letterConfiguration = config;
                        largestSet = (HashSet<String>) wordConfigurations.get(config);
                    }
                }
                wordList = largestSet;
                if (letterConfiguration.contains("" + guess)) {
                    System.out.println("Correct guess");
                }
                else {
                    System.out.println("Wrong guess");
                    wrongGuesses--;
                }
                boolean wordGuessed = true;
                if (wordList.size() != 1) wordGuessed = false;
                else {
                    for (char c: wordList.toArray()[0].toString().toCharArray()) {
                        if (!guessedLetters.contains(c)) {
                            wordGuessed = false;
                            break;
                        }
                    }
                }
                if (wrongGuesses == 0 || wordGuessed) {
                    if (wrongGuesses == 0) {
                        System.out.println("Sorry but you are out of guesses");
                    }
                    else {
                        System.out.println("You win!");
                    }
                    System.out.println("The word was " + wordList.toArray()[0]);
                    System.out.println("Enter Y to play again");
                    char c = scan.nextLine().toLowerCase().charAt(0);
                    if (c != 'y') gameOngoing = false;
                    break;
                }
                else {
                    String progress = "";
                    for (char c: ((String) wordList.toArray()[0]).toCharArray()) {
                        if (guessedLetters.contains(c)) progress += c;
                        else progress += "_";
                    }
                    System.out.println("Current Progress: " + progress);
                    System.out.println("Hidden Words " + wordList.size());
                    System.out.println("Hidden Words " + wordList);
                }
            }
        }

    }
}