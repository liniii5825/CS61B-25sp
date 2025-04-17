import deque.ArrayDeque61B;

import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class Deque61bEnhancementsTest {
    @Test
    @DisplayName("Iterator Test for ArrayDeque61B")
    public void testArrayDeque61BIterator() {
        ArrayDeque61B<Integer> ad = new ArrayDeque61B<>();
        ad.addLast(1);
        ad.addLast(2);
        ad.addLast(3);
        
        int expected = 1;
        for (int item : ad) {
            assertThat(item).isEqualTo(expected);
            expected++;
        }
        
        // Check that we iterated through all elements
        assertThat(expected).isEqualTo(4);
    }

    @Test
    @DisplayName("Iterator Test for LinkedListDeque61B")
    public void testLinkedListDeque61BIterator() {
        deque.LinkedListDeque61B<String> lld = new deque.LinkedListDeque61B<>();
        lld.addLast("a");
        lld.addLast("b");
        lld.addLast("c");
        
        String[] expected = {"a", "b", "c"};
        int index = 0;
        
        for (String item : lld) {
            assertThat(item).isEqualTo(expected[index]);
            index++;
        }
        
        // Check that we iterated through all elements
        assertThat(index).isEqualTo(expected.length);
    }

    @Test
    @DisplayName("Empty Deque Iterator Test")
    public void testEmptyDequeIterator() {
        ArrayDeque61B<Integer> emptyAd = new ArrayDeque61B<>();
        int count = 0;
        for (int item : emptyAd) {
            count++;
        }
        assertThat(count).isEqualTo(0);

        deque.LinkedListDeque61B<String> emptyLld = new deque.LinkedListDeque61B<>();
        count = 0;
        for (String item : emptyLld) {
            count++;
        }
        assertThat(count).isEqualTo(0);
    }

    @Test
    @DisplayName("Equals Test for LinkedListDeque61B")
    public void testLinkedListDequeEquals() {
        deque.LinkedListDeque61B<Integer> lld1 = new deque.LinkedListDeque61B<>();
        deque.LinkedListDeque61B<Integer> lld2 = new deque.LinkedListDeque61B<>();
        
        // Empty deques should be equal
        assertThat(lld1.equals(lld2)).isTrue();
        
        // Add same elements in same order
        lld1.addLast(1);
        lld1.addLast(2);
        lld1.addLast(3);
        
        lld2.addLast(1);
        lld2.addLast(2);
        lld2.addLast(3);
        
        // Deques with same elements should be equal
        assertThat(lld1.equals(lld2)).isTrue();
        
        // Different size
        lld2.addLast(4);
        assertThat(lld1.equals(lld2)).isFalse();
        
        // Reset lld2
        lld2 = new deque.LinkedListDeque61B<>();
        lld2.addLast(1);
        lld2.addLast(2);
        lld2.addLast(4); // Different element
        
        // Same size but different elements
        assertThat(lld1.equals(lld2)).isFalse();
        
        // Different types
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        ad.addLast(1);
        ad.addLast(2);
        ad.addLast(3);
        
        // Should handle comparison with different deque type
        assertThat(lld1.equals(ad)).isFalse();
        
        
        // Null check
        assertThat(lld1.equals(null)).isFalse();
        
        // Self equality
        assertThat(lld1.equals(lld1)).isTrue();
    }
}
