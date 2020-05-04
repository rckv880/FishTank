package com.fishtank.collection;

import java.time.Instant;
import java.util.Arrays;
import java.util.Iterator;

public class ArrayBagCollection<E> implements Bag<E> {
    private Object[] bagItems;
    private int bagSize;
    private static int MAX_CAPACITY = 10;


    public ArrayBagCollection() {
        bagItems = new Object[MAX_CAPACITY];
    }

    public ArrayBagCollection(int maxCapacity) {
        MAX_CAPACITY = maxCapacity;
        bagItems = new Object[MAX_CAPACITY];
    }

    @Override
    public boolean add(E item) {
        boolean retVal = false;

        if (isNotValid(item)) {                                         // Perform input validations.
            return false;
        }

        try {
            if (bagItems != null && bagSize < MAX_CAPACITY) {                                     // Check if the bag is valid.
                int currentLength = bagSize;
                bagItems[currentLength++] = item;                       // Add after the last element and increment
                bagSize++;                                              // the size;
                printMessage(Bag.INFO, "Successfully ADDED the item to the bag.");
                retVal = true;
            } else {
                printMessage(Bag.ERROR, "The bag is probably not initialized.");
            }
        } catch (Exception e) {
            printMessage(Bag.ERROR, "Unable to ADD the item to the bag.");
        }
        return retVal;
    }

    @Override
    public E grab() {
        E retVal = null;
        int randomLocation = (int) Math.random() * (bagItems.length - 0);   // Get random number between 0 and bagsize.

        try {                                                               // Check if the bag is valid.
            if (bagItems != null && bagItems.length > 0 && randomLocation <= bagItems.length) {
                retVal = (E) bagItems[randomLocation];
            }
        } catch (Exception e) {
            printMessage(Bag.ERROR, "Unable to GRAB the item to the bag.");
        }
        return retVal;
    }

    @Override
    public boolean remove(E item) {
        boolean retVal = false;
        /*
        There are many ways to do this.
            1. Remove the item from the array and shift the subsequent elements left.
            2. create a new array and use it.
            3. just null the value where the element is found and only return valid values. (The Array size will not
            change in this case)

        You have to decide which approach suits your need. Refer below link
        for more details.
                https://www.cs.umd.edu/~clin/MoreJava/Container/arr-remove.html
        See:    http://hg.openjdk.java.net/jdk8/jdk8/jdk/file/tip/src/share/classes/java/util/ArrayList.java
         */
        if (isNotValid(item)) {                                             // Perform input validations.
            retVal = false;
        }
        int foundIndex = -1;
        // Find the index of the given item in the bag. Then Shift (System.arrayCopy) to left.
        try {
            if (bagItems != null && bagSize > 0) {
                for (int i = 0; i < bagItems.length; i++) {
                    if (bagItems[i].equals(item)) {
                        foundIndex = i;
                        break;
                    }
                }

                int numMoved = bagSize - foundIndex - 1;
                if (numMoved > 0)
                    System.arraycopy(bagItems, foundIndex + 1,
                            bagItems, foundIndex, numMoved);
                bagItems[--bagSize] = null;                                 // clear the element by setting it to null.
                retVal = true;
            }
        } catch (Exception e) {
            printMessage(Bag.ERROR, "Unable to REMOVE the item to the bag.");
            retVal = false;
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
        for (int i = 0; i < bagItems.length; i++) {
            bagItems[i] = null;
        }

        bagSize = 0;
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
    public Iterator<E> iterator() {
        return new BagIterator<E>((E[])bagItems, bagSize);
    }

    @Override
    public E[] toArray() {
        E[] returnedArray = (E[])bagItems;
        return Arrays.copyOf(returnedArray, bagSize);
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
}

class BagIterator<E> implements Iterator<E> {
    private E[] items;
    private int cursor;                                     // Next element index.
    private int lastRef = -1;                                    // Previous element index.
    private int bagSize;

    public BagIterator(E[] items, int bagSize) {
        this.items = items;
        this.bagSize = bagSize;
    }

    @Override
    public boolean hasNext() {
        return cursor != bagSize;
    }

    @Override
    public E next() {
        int i = cursor;

        if (i > bagSize || i > items.length) {
            ArrayBagCollection.printMessage(Bag.ERROR, "The ArrayBag has been changed while iterating. Error occured");
        }

        lastRef = i;
        cursor = i + 1;

        return (E)items[i];
    }
}
