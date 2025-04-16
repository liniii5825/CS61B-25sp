package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {
    /** 
     * A node in the linked list. It contains a reference to the item, the previous node, and the next node.
     */
    private class Node {
        private T item;
        private Node prev;
        private Node next;

        private Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }

        private Node() {
            this.item = null;
            this.prev = this;
            this.next = this;
        }
    }

    private Node sentinel; 
    private int size;

    /** 
     * Creates an empty deque.
     * The sentinel node is created and points to itself for both previous and next.
     * The size is initialized to 0.
     */
    public LinkedListDeque61B() {
        sentinel = new Node();
        size = 0;
    }
    

    /** 
     * Adds an item to the head of the deque.
     * A new node is created with the item, and it is added to the head of the deque.
     * The size is incremented by 1.
     */
    @Override
    public void addFirst(T x) {
        Node newNode = new Node(x, sentinel, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size += 1;
    }

    /** 
     * Adds an item to the end of the deque.
     * A new node is created with the item, and it is added to the end of the deque.
     * The size is incremented by 1.
     */
    @Override
    public void addLast(T x) {
        Node newNode = new Node(x, sentinel.prev, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size += 1;
    }

    /** 
     * Returns the item at the front of the deque.
     * If the deque is empty, it returns null.
     */
    @Override
    public List<T> toList() {
        ArrayList<T> result = new ArrayList<>();
        Node current = sentinel.next;
        while (current != sentinel) {
            result.add(current.item);
            current = current.next;
        }
        return result;
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
     * Removes and returns the first item in the deque.
     * If the deque is empty, returns null.
     */
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        
        Node firstNode = sentinel.next;
        T removedItem = firstNode.item;
        
        // Update references to remove the first node
        sentinel.next = firstNode.next;
        firstNode.next.prev = sentinel;
        
        size -= 1;
        return removedItem;
    }

    /**
     * Removes and returns the last item in the deque.
     * If the deque is empty, returns null.
     */
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        
        Node lastNode = sentinel.prev;
        T removedItem = lastNode.item;
        
        // Update references to remove the last node
        sentinel.prev = lastNode.prev;
        lastNode.prev.next = sentinel;
        
        size -= 1;
        return removedItem;
    }

    /** 
     * Returns the item at the specified index.
     * If the index is invalid, it returns null.
     * The method traverses the list to find the node at the specified index.
     */
    @Override
    public T get(int index) {
        // Return null for invalid indices
        if (index < 0 || index >= size) {
            return null;
        }

        // Traverse the list to find the node at the specified index
        Node current = sentinel.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.item;
    }

    /** 
     * Returns the item at the specified index using recursion.
     * If the index is invalid, it returns null.
     * The method calls a recursive helper method to find the node at the specified index.
     */
    @Override
    public T getRecursive(int index) {
        // Return null for invalid indices
        if (index < 0 || index >= size) {
            return null;
        }

        // Call the recursive helper method to get the item at the specified index
        return getRecursiveHelper(sentinel.next, index);
    }
    
    private T getRecursiveHelper(Node current, int index) {
        // Base case: if the index is 0, return the item at the current node
        if (index == 0) {
            return current.item;
        }

        // Recursive case: move to the next node and decrement the index
        return getRecursiveHelper(current.next, index - 1);
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
}
