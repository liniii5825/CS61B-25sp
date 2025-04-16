import deque.ArrayDeque61B;

import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

     @Test
     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
     void noNonTrivialFields() {
         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
                 .toList();

         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
     }

    @Test
    @DisplayName("addFirst adds item to the front of the deque")
    void addFirstTest() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
        deque.addFirst(10);
        deque.addFirst(20);
        deque.addFirst(30);
        
        // Check the items are in correct order
        List<Integer> actual = deque.toList();
        assertThat(actual).containsExactly(30, 20, 10).inOrder();
        assertThat(deque.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("addLast adds item to the end of the deque")
    void addLastTest() {
        ArrayDeque61B<String> deque = new ArrayDeque61B<>();
        deque.addLast("a");
        deque.addLast("b");
        deque.addLast("c");
        
        // Check the items are in correct order
        List<String> actual = deque.toList();
        assertThat(actual).containsExactly("a", "b", "c").inOrder();
        assertThat(deque.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("addFirst and addLast can be mixed")
    void addFirstAndLastTest() {
        ArrayDeque61B<String> deque = new ArrayDeque61B<>();
        deque.addFirst("b");
        deque.addLast("c");
        deque.addFirst("a");
        deque.addLast("d");
        
        // Check the items are in correct order
        List<String> actual = deque.toList();
        assertThat(actual).containsExactly("a", "b", "c", "d").inOrder();
        assertThat(deque.size()).isEqualTo(4);
    }

}
