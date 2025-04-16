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
    
    @Test
    @DisplayName("get returns items at correct indices")
    void getTest() {
        ArrayDeque61B<String> deque = new ArrayDeque61B<>();
        deque.addLast("a");
        deque.addLast("b");
        deque.addLast("c");
        deque.addFirst("z");
        deque.addFirst("y");

        // Test regular indices
        assertThat(deque.get(0)).isEqualTo("y");
        assertThat(deque.get(1)).isEqualTo("z");
        assertThat(deque.get(2)).isEqualTo("a");
        assertThat(deque.get(3)).isEqualTo("b");
        assertThat(deque.get(4)).isEqualTo("c");

        // Test out of bounds index
        assertThat(deque.get(-1)).isNull();
        assertThat(deque.get(5)).isNull();

        // Test with empty deque
        ArrayDeque61B<Integer> emptyDeque = new ArrayDeque61B<>();
        assertThat(emptyDeque.get(0)).isNull();
    }
    
    @Test
    @DisplayName("size returns correct number of items")
    void sizeTest() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
        assertThat(deque.size()).isEqualTo(0);
        
        deque.addFirst(1);
        assertThat(deque.size()).isEqualTo(1);
        
        deque.addLast(2);
        assertThat(deque.size()).isEqualTo(2);
        
        deque.addFirst(0);
        deque.addLast(3);
        assertThat(deque.size()).isEqualTo(4);
    }

    @Test
    @DisplayName("isEmpty returns correct boolean value")
    void isEmptyTest() {
        ArrayDeque61B<String> deque = new ArrayDeque61B<>();
        assertThat(deque.isEmpty()).isTrue();
        
        deque.addFirst("item");
        assertThat(deque.isEmpty()).isFalse();
        
        // Test after removing items
        ArrayDeque61B<Integer> deque2 = new ArrayDeque61B<>();
        deque2.addFirst(1);
        deque2.addLast(2);
        deque2.removeFirst();
        deque2.removeLast();
        assertThat(deque2.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("toList returns correct list representation")
    void toListTest() {
        ArrayDeque61B<Integer> emptyDeque = new ArrayDeque61B<>();
        assertThat(emptyDeque.toList()).isEmpty();
        
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
        deque.addLast(10);
        deque.addLast(20);
        deque.addFirst(5);
        deque.addFirst(1);
        
        List<Integer> expected = List.of(1, 5, 10, 20);
        assertThat(deque.toList()).containsExactlyElementsIn(expected).inOrder();
        
        // After removal operations
        deque.removeFirst();
        assertThat(deque.toList()).containsExactly(5, 10, 20).inOrder();
        
        deque.removeLast();
        assertThat(deque.toList()).containsExactly(5, 10).inOrder();
    }

}
