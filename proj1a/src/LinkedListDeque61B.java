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
