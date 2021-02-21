import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private static final String NO_SUCH_ELEMENT_ERR_MSG =
            "The list has been emptied. Check with 'isEmpty' to make sure you don't trigger this error.";
    private static final String ILLEGAL_ARG_ERR_MSG =
            "You did not provide a value! Please specify something other than 'null'.";

    public int size = 0;
    private Item[] elements;

    public RandomizedQueue() {
        elements = (Item[]) new Object[4];
    }

    public RandomizedQueue(int n) {
        elements = (Item[]) new Object[n];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    private void resize(int n) {
        Item[] newList = (Item[]) new Object[n];
        for (int i = 0; i < size; i++) {
            newList[i] = elements[i];
        }
        elements = newList;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException(RandomizedQueue.ILLEGAL_ARG_ERR_MSG);
        }
        size++;

        if (elements.length == size) {
            resize(elements.length * 2);
        }
        elements[size - 1] = item;
    }

    // remove and return a random item

    public Item dequeue() {
        if (size == 0) {
            throw new NoSuchElementException(RandomizedQueue.NO_SUCH_ELEMENT_ERR_MSG);
        }
        int indexToRemoved = getRandomIndex();
        Item temp = elements[indexToRemoved];

        // This is dodgy. What if --size == indexToBeRemoved?

        elements[indexToRemoved] = elements[size - 1];
        elements[size - 1] = null;

        size--;

        if (elements.length == size * 4) {
            resize(elements.length / 4);
        }
        return temp;
    }

    private int getRandomIndex() {
        return StdRandom.uniform(size);
    }

    public void fillGap(int indexToBeRemoved) {
        while (elements[indexToBeRemoved] != null) {
            elements[indexToBeRemoved] = elements[++indexToBeRemoved];
        }
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (size == 0) {
            throw new NoSuchElementException(RandomizedQueue.NO_SUCH_ELEMENT_ERR_MSG);
        }
        return elements[getRandomIndex()];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {
        private RandomizedQueue<Item> tempQue;

        RandomIterator() {
            tempQue = new RandomizedQueue<Item>();
            tempQue.elements = (Item[]) new Object[size];
            tempQue.size = size;
            System.arraycopy(elements, 0, tempQue.elements, 0, size);

        }


        public boolean hasNext() {
            return !tempQue.isEmpty();
        }

        public Item next() {
            return tempQue.dequeue();

        }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        Integer[] items = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        for (Integer i : items) {
            rq.enqueue(i);
        }
        Iterator<Integer> it = rq.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println(rq.size());
        System.out.println(rq.dequeue());
        System.out.println(rq.size());

    }
}
