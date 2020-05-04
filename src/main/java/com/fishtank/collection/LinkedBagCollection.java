package com.fishtank.collection;

import java.time.Instant;
import java.util.Iterator;
import java.util.ListIterator;

public class LinkedBagCollection<E> implements Bag<E> {
    private int bagSize;
    private static int MAX_CAPACITY = 10;
    private Node<E> front;

    public LinkedBagCollection() {
        front = null;
    }

    public LinkedBagCollection(int maxCapacity) {
        MAX_CAPACITY = maxCapacity;
        front = null;
    }

    @Override
    public boolean add(E item) {
        boolean retVal = false;

        if (isNotValid(item)) {                                         // Perform input validations.
            return false;
        }

        try {
            if (isEmpty()) {
                front = new Node<E>(item);
            } else {
                Node<E> current = front;                                   // Assign front to temp and insert the new node.
                front = new Node<E>(current, item, current.next);
            }

            bagSize++;
        } catch (Exception e) {
            printMessage(Bag.ERROR, "Unable to ADD the item to the bag.");
        }
        return retVal;
    }

    @Override
    public E grab() {
        E retVal = null;
        int i = 0;
        int randomLocation = (int) Math.random() * (bagSize - 0);   // Get random number between 0 and bagsize.
        int traverseBack = bagSize - randomLocation;                // If the bagSize=5 and random=3 then traverse
        // back 5-3 = 2 nodes.
        Node<E> current = front;
        while (current.prev != null && i <= traverseBack) {
            current = current.prev;
            i++;
        }

        retVal = current.get();
        return retVal;
    }

    @Override
    public boolean remove(E item) {
        boolean retVal = false;
        if (isEmpty()) {
            printMessage(Bag.ERROR, "Cannot remove from Empty Bag.");
            retVal = false;
        } else {
            Node<E> current = front;
            while (current != null && !current.get().equals(item)) {
                current = current.prev;
            }

            if (current == null) {
                printMessage(Bag.ERROR, "Cannot remove from Empty Bag.");
            }

            if (current.prev != null) {
                current.prev.next = current.next;
                current.next.prev = current.prev;
            }

            bagSize--;
            retVal = true;
        }

        return retVal;
    }

    @Override
    public int size() {
        return bagSize;
    }

    @Override
    public int capacityRemaining() {
        return MAX_CAPACITY - bagSize;
    }

    @Override
    public void clear() {
        Node<E> current = front;
        while (current != null) {
            current = current.prev;                         // Set all the next items to null until current.prev is null.
            current.next = null;
            bagSize--;
        }
    }

    @Override
    public boolean isEmpty() {
        return bagSize == 0;
    }

    @Override
    public boolean isFull() {
        return bagSize == MAX_CAPACITY;
    }

    @Override
    public Iterator iterator() {
        return new LinkedBagIteragor<E>(front, bagSize);
    }

    @Override
    public E[] toArray() {
        E[] retVal = (E[]) new Object[bagSize];
        Node<E> current = front;
        int arrIndex = bagSize;

        while (current != null) {
            E item = current.get();
            if (arrIndex >= 0) {
                retVal[arrIndex--] = item;
            }
            current = current.prev;
        }

        return retVal;
    }

    /**
     * This method checks if the given input item is valid or not.
     *
     * @param item The input item.
     * @return Returns if the item is valid or not.
     */
    public boolean isNotValid(E item) {
        boolean valid = false;
        if (item == null) {
            printMessage(Bag.ERROR, "The given item is not valid or null.");
            valid = true;
        }

        return valid;
    }

    /**
     * This method will print the given message
     *
     * @param category The type of the message - {ERROR, INFO, WARN, DEBUG}
     * @param msg      The message to be printed.
     */
    public static void printMessage(String category, String msg) {
        System.out.println(Instant.now().toString() + " [" + category + "] [" + ArrayBagCollection.class.getSimpleName() + "] - " + msg);
    }

    private class Node<E> {
        private E element;
        private Node<E> prev;
        private Node<E> next;
        private Node<E> lastAccessed;

        public Node(E elem) {
            this(null, elem, null);
        }

        public Node(Node<E> prev, E elem, Node<E> next) {
            this.element = elem;
            this.prev = prev;
            this.next = next;
        }

        public E get() {
            return element;
        }

        public void set(E item) {
            element = item;
        }
    }

    private class LinkedBagIteragor<E> implements ListIterator<E> {
        private Node<E> current = null;
        private Node<E> lastAccessed = null;
        private int bagSize = 0;
        private int index = 0;

        public LinkedBagIteragor(Node<E> item, int bagSize) {
            this.current = item;
            this.bagSize = bagSize;
        }

        @Override
        public boolean hasPrevious() {
            return index > 0;
        }

        @Override
        public boolean hasNext() {
            return index < bagSize;
        }

        @Override
        public int previousIndex() {
            return index - 1;
        }

        @Override
        public int nextIndex() {
            return index;
        }

        @Override
        public E previous() {
            if (!hasPrevious()) {
                printMessage(Bag.ERROR, "No previous element found");
            }

            current = current.prev;
            index--;
            lastAccessed = current;
            return current.get();
        }

        @Override
        public E next() {
            if (!hasNext()) {
                printMessage(Bag.ERROR, "No Next element found");
            }

            current = current.next;
            index++;
            lastAccessed = current;
            return current.get();
        }

        @Override
        public void remove() {
            if (lastAccessed == null) {
                printMessage(Bag.ERROR, "Invalid State");
            }

            Node<E> x = lastAccessed.prev;
            Node<E> y = lastAccessed.next;
            x.next = y;
            y.prev = x;
            bagSize--;

            if (current == lastAccessed) {
                current = y;
            } else {
                index--;
            }

            lastAccessed = null;
        }

        @Override
        public void set(E e) {
            if (lastAccessed == null) {
                printMessage(Bag.ERROR, "Invalid State.");
            }
            lastAccessed.set(e);
        }

        @Override
        public void add(E e) {
            if (bagSize >= MAX_CAPACITY) {
                printMessage(Bag.ERROR, "Cannot add item as the bag is full.");
            }
            Node<E> temp = current.prev;
            Node<E> newNode = new Node<E>(current, e, null);
            current.next = newNode;
            bagSize++;
            index++;
            lastAccessed = null;
        }
    }
}

