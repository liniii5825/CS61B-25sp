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
    

    @Override
    public void addFirst(T x) {

    }

    @Override
    public void addLast(T x) {

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
