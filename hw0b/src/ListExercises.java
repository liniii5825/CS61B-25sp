import java.util.ArrayList;
import java.util.List;

public class ListExercises {

    /** Returns the total sum in a list of integers */
    public static int sum(List<Integer> L) {
        // TODO: Fill in this function.
        int total = 0;
        int size = L.size();
        for (int i = 0; i < size; i++) {
            total += L.get(i);
        }
        return total;
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        // TODO: Fill in this function.
        List<Integer> evenList = new ArrayList<>();
        int size = L.size();
        for (int i = 0; i < size; i++) {
            if (L.get(i) % 2 == 0) {
                evenList.add(L.get(i));
            }
        }
        return evenList;
    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        // TODO: Fill in this function.
        List<Integer> commonList = new ArrayList<>();
        int size1 = L1.size();
        int size2 = L2.size();
        for (int i = 0; i < size1; i++) {
            for (int j = 0; j < size2; j++) {
                if (L1.get(i).equals(L2.get(j))) {
                    commonList.add(L1.get(i));
                    break;
                }
            }
        }
        return commonList;
    }


    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        // TODO: Fill in this function.
        int count = 0;
        int size = words.size();
        for (int i = 0; i < size; i++) {
            String word = words.get(i);
            int wordLength = word.length();
            for (int j = 0; j < wordLength; j++) {
                if (word.charAt(j) == c) {
                    count++;
                }
            }
        }
        return count;   
    }
}
