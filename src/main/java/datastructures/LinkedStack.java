package datastructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The <tt>LinkedStack</tt> class represents a last-in-first-out (LIFO) stack of generic items.
 * It supports the usual <em>enqueue</em> and <em>dequeue</em> operations, along with methods
 * for peeking at the top item, testing if the stack is empty, and iterating through
 * the items in LIFO order.
 * <p/>
 * All stack operations except iteration are constant time.
 * <p/>
 * For additional documentation, see <a href="http://introcs.cs.princeton.edu/43stack">Section 4.3</a> of
 * <i>Introduction to Programming in Java: An Interdisciplinary Approach</i> by Robert Sedgewick and Kevin Wayne.
 */
public class LinkedStack<T> extends Stack<T> {

    /**
     * Top item of the stack.
     */
    private Node<T> top;

    /**
     * Create an empty stack.
     */
    public LinkedStack() {
        super();

        this.top = new Node<>();
    }

    /**
     * <b>Complexity: O(1)</b>
     * <br/>Add an item to the top stack.
     */
    @Override
    public void push(T item) {
        Node<T> oldTop = top;
        top = new Node<>(item, oldTop);

        size++;
    }

    /**
     * <b>Complexity: O(1)</b>
     * <br/>Delete and return the item on top of the stack.
     * Throw an exception if no such item exists.
     */
    @Override
    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("LinkedStack Underflow");
        }

        T item = top.getItem();
        top = top.getNext();
        size--;

        return item;
    }

    /**
     * <b>Complexity: O(1)</b>
     * <br/>Return the item most recently added to the stack.
     * Throw an exception if no such item exists because the stack is empty.
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("LinkedStack Underflow");
        }

        return top.getItem();
    }

    /**
     * <b>Complexity: O(n)</b>
     * <br/>Returns a string representation of the stack.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        this.forEach(item -> sb.append(item).append(" "));

        // Remove last space
        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }

    /**
     * Return an iterator to the stack that iterates through the items in LIFO order.
     */
    @Override
    public Iterator<T> iterator() {
        return new StackIterator();
    }

    private class StackIterator implements Iterator<T> {

        private Node<T> current = top;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            top.setItem(current.getItem());
            current = current.getNext();
            return top.getItem();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
