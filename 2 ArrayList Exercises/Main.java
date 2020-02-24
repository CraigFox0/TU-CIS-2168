package co.craigfox;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class Main {

    public static void main(String[] args) {
        List<Integer> testIntList = new ArrayList<Integer>();
        testIntList.add(1);
        testIntList.add(4);
        testIntList.add(5);
        testIntList.add(6);
        testIntList.add(5);
        testIntList.add(5);
        testIntList.add(2);
        List<String> testStringList = new ArrayList<String>();
        List<String> testStringListTwo = new ArrayList<String>();
        unique(testIntList);
        allMultiples(testIntList, 2);
        allStringsOfSize(testStringList, 3);
        isPermutation(testStringList, testStringListTwo);
        stringToListOfWords("Test. hello, ,,,a dsf.af sdas#sad");
        removeAllInstances(testIntList, 5);
        System.out.println(testIntList);
    }

    public static <E> boolean unique(List<E> providedList) {
        for (int i = 0; i < providedList.size(); i++) {
            for (int j = i + 1; j < providedList.size(); j++) {
                if (!providedList.get(i).equals(j)) return false;
            }
        }
        return true;
    }

    public static List<Integer> allMultiples(List<Integer> providedList, int multipleValue) {
        int i = 0;
        while (i < providedList.size()) {
            if (providedList.get(i) % multipleValue != 0) providedList.remove(providedList.get(i));
            else i++;
        }
        return providedList;
    }

    public static List<String> allStringsOfSize(List<String> providedList, int length) {
        int i = 0;
        while (i < providedList.size()) {
            if (providedList.get(i).length() != length) providedList.remove(providedList.remove(i));
            else i++;
        }
        return providedList;
    }

    public static <E> boolean isPermutation(List<E> A, List<E> B) {
        if (A.size() != B.size()) return false;
        for (E item: A) {
            int countA = 0;
            int countB = 0;
            for (E a: A) {
                if (item.equals(a)) countA++;
            }
            for (E b: B) {
                if (item.equals(b)) countB++;
            }
            if (countA != countB) return false;
        }
        return true;
    }

    public static List<String> stringToListOfWords(String providedString) {
        List<String> splitWords = new ArrayList<String>();
        for (String word: providedString.split("\\s+")) {
            word = word.replaceAll("\\W", "");
            splitWords.add(word);
        }
        return splitWords;
    }

    public static <E> void removeAllInstances(List<E> providedList, E item) {
        int i = 0;
        while (i < providedList.size()) {
            if (providedList.get(i).equals(item)) providedList.remove(i);
            else i++;
        }
    }
}
