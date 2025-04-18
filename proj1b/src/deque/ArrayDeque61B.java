package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T>{
    private T[] items;
    private int size;
    private int nextFirst; // moving forwards
    private int nextLast; // moving backwards

    /**
     * Creates an empty array deque with initial capacity of 8.
     */
    @SuppressWarnings("unchecked")
    public ArrayDeque61B() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }
    
    /**
     * Adds an item to the front of the deque.
     * @param x The item to add.
     */
    @Override
    public void addFirst(T x) {
        // Resize if array is full
        if (size == items.length) {
            resize(items.length * 2);
        }
        
        items[nextFirst] = x;
        // Move nextFirst pointer to the previous position (wrapping around if necessary)
        nextFirst = Math.floorMod(nextFirst - 1, items.length);
        size += 1; // Increment size after adding the item
    }

    /**
     * Adds an item to the end of the deque.
     * @param x The item to add.
     */
    @Override
    public void addLast(T x) {
        // Resize if array is full
        if (size == items.length) {
            resize(items.length * 2);
        }
        
        items[nextLast] = x;
        // Move nextLast pointer to the next position (wrapping around if necessary)
        nextLast = Math.floorMod(nextLast + 1, items.length);
        size += 1; 
    }
    
    /**
     * Resizes the array to the target capacity.
     * @param capacity The new capacity of the array.
     */
    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        
        // Copy all elements to the new array starting at index 0
        for (int i = 0; i < size; i++) {
            int oldIndex = Math.floorMod(nextFirst + 1 + i, items.length);
            newItems[i] = items[oldIndex];
        }
        
        items = newItems;
        nextFirst = capacity - 1; // Set nextFirst to the end of the array
        nextLast = size; // Set nextLast right after the last element
    }

    @Override
    public List<T> toList() {
        List<T> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            // Calculate the actual index in the array
            int actualIndex = Math.floorMod(nextFirst + 1 + i, items.length);
            list.add(items[actualIndex]);
        }
        return list;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Removes and returns the first item from the deque.
     * @return The first item, or null if the deque is empty.
     */
    @Override
    public T removeFirst() {
        // Return null if the deque is empty
        if (isEmpty()) {
            return null;
        }

        // Resize if array is large
        if (size < (items.length) / 4 && items.length > 8) {
            resize(items.length / 2);
        }

        // Calculate the index of the first item
        int firstIndex = Math.floorMod(nextFirst + 1, items.length);

        T firstItem = items[firstIndex];

        // Set the item to null to allow garbage collection
        items[firstIndex] = null;
        nextFirst = firstIndex;
        size -= 1;

        return firstItem;
    }

    /**
     * Removes and returns the last item from the deque.
     * @return The last item, or null if the deque is empty.
     */
    @Override
    public T removeLast() {
        // Return null if the deque is empty
        if (isEmpty()) {
            return null;
        }

        // Resize if array is large
        if (size < (items.length) / 4 && items.length > 8) {
            resize(items.length / 2);
        }
        
        // Calculate the index of the last item
        int lastIndex = Math.floorMod(nextLast - 1, items.length);

        T lastItem = items[lastIndex];
        items[lastIndex] = null;
        nextLast = lastIndex;
        size -= 1;
        
        return lastItem;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null; // Return null if index is out of bounds
        }
        // Calculate the actual index in the array
        int actualIndex = Math.floorMod(nextFirst + 1 + index, items.length); // nextFirst + 1 is the first valid index
        return items[actualIndex];
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

    /**
     * Returns an iterator over elements of type T.
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int pos = 0;

            /**
             * Checks if there are more elements to iterate over.
             * @return true if there are more elements, false otherwise.
             */
            @Override
            public boolean hasNext() {
                return pos < size;
            }

            /**
             * Returns the next element in the iteration.
             * @return the next element available. 
             */
            @Override
            public T next() {
                if (!hasNext()) {
                    return null;
                }
                T item = get(pos);
                pos += 1;
                return item;
            }
        };
    }
    
    /**
     * Returns a string representation of the deque.
     * @return A string representation of the deque.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(get(i));
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
