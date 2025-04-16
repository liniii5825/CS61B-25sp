package deque;

import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T>{
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

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
        items[nextLast] = x;
        // Move nextLast pointer to the next position (wrapping around if necessary)
        nextLast = Math.floorMod(nextLast + 1, items.length);
        size += 1; 
    }

    @Override
    public List<T> toList() {
        return List.of();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public T removeFirst() {
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }
}
