import java.util.ArrayList;
import java.util.Scanner;

public class Encryption {

    //All methods assume input is a randomized deck numbered 1 to 28
    public static void main(String[] args){
        CircularLinkedList<Integer> deck = new CircularLinkedList<>();
        deck.add(1);
        deck.add(4);
        deck.add(7);
        deck.add(10);
        deck.add(13);
        deck.add(16);
        deck.add(19);
        deck.add(22);
        deck.add(25);
        deck.add(28);
        deck.add(3);
        deck.add(6);
        deck.add(9);
        deck.add(12);
        deck.add(15);
        deck.add(18);
        deck.add(21);
        deck.add(24);
        deck.add(27);
        deck.add(2);
        deck.add(5);
        deck.add(8);
        deck.add(11);
        deck.add(14);
        deck.add(17);
        deck.add(20);
        deck.add(23);
        deck.add(26);
        //1 4 7 10 13 16 19 22 25 28 3 6 9 12 15 18 21 24 27 2 5 8 11 14 17 20 23 26
        System.out.println(deck.toString());
        ArrayList<String> messagesToDecode = new ArrayList<>();
        System.out.println("Please enter messages to decode: ");
        Scanner in = new Scanner(System.in);
        String currentString = in.nextLine();
        while (currentString.length() != 0) {
            System.out.println("You added " + currentString + " to the decode list");
            System.out.println("Hit enter with a blank line when you are done");
            messagesToDecode.add(currentString);
            currentString = in.nextLine();
        }

        for (String mes: messagesToDecode) {
            String standardMessage = standardizeFormat(mes);
            ArrayList<Integer> keystream = generateKeystream(deck, standardMessage.length());
            System.out.println(keystream);
            System.out.println(decode(standardMessage, keystream));
        }

    }

    public static String encode(String phrase, ArrayList<Integer> keystream) {
        String encodedText = "";
        for (int i = 0; i < phrase.length(); i++) {
            encodedText += (char)((((phrase.charAt(i)-'a') + keystream.get(i)) % 26) +'a');
        }
        return encodedText;
    }

    public static String decode(String phrase, ArrayList<Integer> keystream) {
        String decodedText = "";
        for (int i = 0; i < phrase.length(); i++) {
            decodedText += (char)((((phrase.charAt(i)-'a') + (26 - keystream.get(i))) % 26) +'a');
        }
        return decodedText;
    }

    public static String standardizeFormat(String text) {
        while (!((text.length() % 5) == 0)) {
            text += "x";
        }
        text = text.toLowerCase();
        return text.replaceAll("[^a-z]", "");
    }

    public static ArrayList<Integer> generateKeystream(CircularLinkedList<Integer> deck, int numberOfKeys) {
        ArrayList<Integer> stream = new ArrayList<>();
        while (stream.size() < numberOfKeys) {
            int value = 28;
            while (value > 26) {
                moveAJoker(deck);
                moveBJoker(deck);
                tripleCut(deck);
                bottomCardCount(deck);
                value = topCardCount(deck);
            }
            stream.add(value);
        }
        return stream;
    }

    public static CircularLinkedList<Integer> moveAJoker(CircularLinkedList<Integer> deck) {
        int jokerIndex = deck.getIntegerIndex(27);
        deck.add((jokerIndex+1)%deck.size, deck.remove(jokerIndex)); //mod handles if the card goes past the end of the list and needs to be added to the beginning
        return deck;
    }

    public static CircularLinkedList<Integer> moveBJoker(CircularLinkedList<Integer> deck) {
        int jokerIndex = deck.getIntegerIndex(28);
        deck.add((jokerIndex+2)%deck.size, deck.remove(jokerIndex)); //mod handles if the card goes past the end of the list and needs to be added to the beginning
        return deck;
    }

    public static CircularLinkedList<Integer> tripleCut(CircularLinkedList<Integer> deck) {
        boolean firstJokerFound = false;
        boolean secondJokerFound = false;
        CircularLinkedList<Integer> firstDeck = new CircularLinkedList<>();
        CircularLinkedList<Integer> secondDeck = new CircularLinkedList<>();
        CircularLinkedList<Integer> newDeck;
        while (!firstJokerFound) {
            if (deck.head.item != 27 && deck.head.item != 28) firstDeck.add(deck.remove(0));
            else {
                secondDeck.add(deck.remove(0));
                firstJokerFound = true;
            }
        }
        while (!secondJokerFound) {
            if (deck.head.item == 27 || deck.head.item == 28) secondJokerFound = true;
            secondDeck.add(deck.remove(0));
        }
        newDeck = deck;
        while (secondDeck.size != 0) {
            newDeck.add(secondDeck.remove(0));
        }
        while (firstDeck.size != 0) {
            newDeck.add(firstDeck.remove(0));
        }
        return newDeck;
    }

    public static CircularLinkedList<Integer> bottomCardCount(CircularLinkedList<Integer> deck) {
        int bottomCard = deck.remove(deck.size-1);
        int count = bottomCard;
        if (count > 27) count = 27;
        CircularLinkedList<Integer> countdownPile = new CircularLinkedList<Integer>();
        while (count > 0) {
            countdownPile.add(deck.remove(0));
            count--;
        }
        while (countdownPile.size > 0) {
            deck.add(countdownPile.remove(0));
        }
        deck.add(bottomCard);
        return deck;
    }

    public static int topCardCount(CircularLinkedList<Integer> deck) {
        int count = deck.head.item;
        if (count > 27) count = 27;
        return deck.getNode(count-1).next.item;
    }
}
