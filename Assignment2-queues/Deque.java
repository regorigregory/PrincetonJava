/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private class Node<Item> {
        private Item data;
        private Node<Item> next;
        private Node<Item> previous;

        public Node(Item data) {
            this.data = data;
            this.next = null;
            this.previous = null;
        }
    }

    private Node<Item> head;
    private Node<Item> tail;
    private int size;

    public Deque() {
        head = null;
        tail = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return head == null;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node<Item> newNode = new Node<Item>(item);
        if (head == null) {
            head = tail = newNode;
        }
        else {
            newNode.next = head;
            head.previous = newNode;
            head = newNode;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node<Item> newNode = new Node<Item>(item);
        if (tail == null) {
            head = tail = newNode;
        }
        else {
            tail.next = newNode;
            newNode.previous = tail;
            tail = tail.next;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        size--;
        Node<Item> temp = head;
        head = head.next;

        if (head != null) {
            head.previous = null;
            if (size == 1) {
                tail.previous = null;
                head = tail;
            }
        }
        else {
            tail = head = null;
        }
        return temp.data;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (tail == null) {
            throw new NoSuchElementException();
        }
        size--;

        Node<Item> temp = tail;
        tail = tail.previous;
        if (tail != null) {
            tail.next = null;
            if (size() == 1) {
                head.next = null;
                head = tail;
            }
        }
        else {
            tail = head = null;
        }

        return temp.data;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node<Item> current;

        DequeIterator() {
            super();
            current = head;
        }

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (current == null) {
                throw new NoSuchElementException();
            }
            Node<Item> temp = current;
            current = current.next;
            return temp.data;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        System.out.println("Hello world");
        Integer[] intArray = { 1, 2 };
        Deque<Integer> d = new Deque<Integer>();

        for (Integer i : intArray) {
            d.addLast(i);
        }
        d.addFirst(600);
        System.out.println(d.size());
        System.out.println("***********************************");


        d.removeFirst();
        d.removeLast();
        d.addFirst(50);
        d.addLast(100);

        System.out.println(d.size());
        System.out.println("***********************************");

        System.out.println("***********************************");
        int lim = d.size();
        for (int i = 0; i < lim; i++) {
            d.removeLast();
        }
        System.out.println("***********************************");
        Iterator<Integer> it = d.iterator();

        while (it.hasNext()) {
            System.out.println(it.next());
        }
        it.next();


    }


}
