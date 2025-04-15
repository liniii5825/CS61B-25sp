import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/** Performs some basic linked list tests. */
public class LinkedListDeque61BTest {

     @Test
     /** In this test, we have three different assert statements that verify that addFirst works correctly. */
     public void addFirstTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addFirst("back"); // after this call we expect: ["back"]
         assertThat(lld1.toList()).containsExactly("back").inOrder();

         lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
         assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

         lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
     }

     @Test
     /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
      *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
     public void addLastTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addLast("front"); // after this call we expect: ["front"]
         lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
         lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
     }

     @Test
     /** This test performs interspersed addFirst and addLast calls. */
     public void addFirstAndAddLastTest() {
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
         lld1.addLast(0);   // [0]
         lld1.addLast(1);   // [0, 1]
         lld1.addFirst(-1); // [-1, 0, 1]
         lld1.addLast(2);   // [-1, 0, 1, 2]
         lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

         assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
     }

    // Below, you'll write your own tests for LinkedListDeque61B.
    
    @Test
    /**
     * This test checks the behavior of the IsEmpty method.
     * It verifies that the deque is empty when no elements are added,
     * and not empty after adding elements.
     */
    public void testIsEmpty() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        
        // Test on empty deque
        assertThat(lld1.isEmpty()).isTrue();
        
        // Test after adding an element
        lld1.addFirst("item");
        assertThat(lld1.isEmpty()).isFalse();
        
        // Test with multiple elements
        lld1.addLast("another");
        assertThat(lld1.isEmpty()).isFalse();
    }

    @Test
    /**
     * This test checks the behavior of the size method.
     * It verifies that the size is correct after adding and removing elements.
     */
    public void testSize() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

        // Test on empty deque
        assertThat(lld1.size()).isEqualTo(0);

        // Test after adding elements
        lld1.addFirst(10);
        assertThat(lld1.size()).isEqualTo(1);

        lld1.addLast(20);
        assertThat(lld1.size()).isEqualTo(2);

        lld1.addFirst(5);
        lld1.addLast(25);
        assertThat(lld1.size()).isEqualTo(4);
    }
    
    @Test
    /**
     * This test checks the behavior of the get method.
     * It verifies that the get method returns the correct item at the given index,
     * and returns null for invalid indices.
     */
    public void testGet() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        
        // Test on empty deque
        assertThat(lld1.get(0)).isNull();
        
        // Test with one element
        lld1.addLast("first");
        assertThat(lld1.get(0)).isEqualTo("first");
        
        // Test with multiple elements
        lld1.addLast("second");
        lld1.addLast("third");
        assertThat(lld1.get(0)).isEqualTo("first");
        assertThat(lld1.get(1)).isEqualTo("second");
        assertThat(lld1.get(2)).isEqualTo("third");
        
        // Test with invalid indices
        assertThat(lld1.get(-1)).isNull();
        assertThat(lld1.get(3)).isNull();
        assertThat(lld1.get(28723)).isNull();
    }
}