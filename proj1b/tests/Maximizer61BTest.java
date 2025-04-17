import deque.Maximizer61B;
import deque.ArrayDeque61B;

import org.junit.jupiter.api.*;

import java.util.Comparator;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class Maximizer61BTest {
    private static class StringLengthComparator implements Comparator<String> {
        public int compare(String a, String b) {
            return a.length() - b.length();
        }
    }

    @Test
    public void basicTest() {
        ArrayDeque61B<String> ad = new ArrayDeque61B<>();
        ad.addFirst("");
        ad.addFirst("2");
        ad.addFirst("fury road");
        assertThat(Maximizer61B.max(ad, new StringLengthComparator())).isEqualTo("fury road");
    }

    @Test
    @DisplayName("Test with null iterable")
    public void testMaxWithNullIterable() {
        ArrayDeque61B<String> ad = null;
        String result = Maximizer61B.max(ad);
        assertThat(result).isNull();
    }
    
    @Test
    @DisplayName("Test with integers")
    public void testMaxWithIntegers() {
        // Test with integer objects that use natural ordering
        ArrayDeque61B<Integer> ad = new ArrayDeque61B<>();
        ad.addLast(5);
        ad.addLast(12);
        ad.addLast(17);
        ad.addLast(23);  // Maximum value
        ad.addLast(3);
        assertThat(Maximizer61B.max(ad)).isEqualTo(23);
    }

    @Test
    @DisplayName("Test with strings")
    public void testMaxWithStrings() {
        // Test with string objects that use lexicographical ordering
        ArrayDeque61B<String> ad = new ArrayDeque61B<>();
        ad.addLast("apple");
        ad.addLast("zebra");  // Lexicographically largest
        ad.addLast("banana");
        ad.addLast("xylophone");
        assertThat(Maximizer61B.max(ad)).isEqualTo("zebra");
    }

    @Test
    @DisplayName("Test with empty iterable")
    public void testMaxWithEmptyIterable() {
        // Edge case: empty collection should return null
        ArrayDeque61B<Integer> ad = new ArrayDeque61B<>();
        assertThat(Maximizer61B.max(ad)).isNull();
    }

    @Test
    @DisplayName("Test with custom comparator for integers")
    public void testMaxWithIntegerComparator() {
        // Test custom comparator that reverses the natural ordering
        ArrayDeque61B<Integer> ad = new ArrayDeque61B<>();
        ad.addLast(5);  // Should be maximum with reverse ordering
        ad.addLast(12);
        ad.addLast(17);
        ad.addLast(23);
        
        // Reverse order comparator
        Comparator<Integer> reverseComparator = (a, b) -> b - a;
        assertThat(Maximizer61B.max(ad, reverseComparator)).isEqualTo(5);
    }

    @Test
    @DisplayName("Test with null comparator")
    public void testMaxWithNullComparator() {
        // Edge case: null comparator should return null
        ArrayDeque61B<String> ad = new ArrayDeque61B<>();
        ad.addLast("apple");
        ad.addLast("banana");
        
        assertThat(Maximizer61B.max(ad, null)).isNull();
    }

    @Test
    @DisplayName("Test with single element")
    public void testMaxWithSingleElement() {
        // Edge case: collection with only one element
        ArrayDeque61B<Integer> ad = new ArrayDeque61B<>();
        ad.addLast(42);
        assertThat(Maximizer61B.max(ad)).isEqualTo(42);
    }
}
