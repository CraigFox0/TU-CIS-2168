import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class Main {

    public static void main(String[] args) {
        List<String> testStringList = new ArrayList<String>();
        List<String> testStringListTwo = new ArrayList<String>();
        isPermutation(testStringList, testStringListTwo);
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
}
