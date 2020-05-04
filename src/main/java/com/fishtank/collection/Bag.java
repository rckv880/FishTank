package com.fishtank.collection;

import java.util.Iterator;

/**
 * @author Avleen Dhanjal
 */
public interface Bag<E> {
    // Message Categories.
    public static String ERROR = "ERROR";
    public static String INFO = "INFO";


    /**
     * This method adds the given item to the bag.
     * @param item The input Item
     * @return Returns if the item is successfully added or not.
     */
    public boolean add(E item);

    /**
     * This method returns a random item in the bag without removing it from it.
     * @return Returns the item.
     */
    public E grab();

    /**
     * This method removes the item from the bag. The item is no longer available
     * once it is removed.
     * @param item The input item which needs to be removed from the bag.
     * @return Returns if the item is successfully removed or not.
     */
    public boolean remove(E item);

    /**
     * This method returns the size of the bag.
     * @return Returns the bag size.
     */
    public int size();

    /**
     * This method returns the remaining capacity of the bag. If a bag is initialized with a fixed size,
     * the remaining capacity is returned.
     * @return The available capacity of the bag.
     */
    public int capacityRemaining();

    /**
     * This method removes all the items from the bag. Use Cautiously as
     * it cannot be recovered.
     */
    public void clear();

    /**
     * This method returns if the bag is empty or not.
     * @return Whether or not the bag is empty.
     */
    public boolean isEmpty();

    /**
     * This method returns if the bag is full or not.
     * @return Whether or not the bag is full.
     */
    public boolean isFull();

    /**
     * This method returns the iterator which can be used to retrieve the items in
     * a sequence one after another.
     * @return Returns an iterator interface which returns the items from the bag
     * one after another.
     */
    public Iterator<E> iterator();

    /**
     * This method returns the items in the bag as an Array. This is useful if you want to convert
     * the bag to an Array to perform array operations.
     * @return Bag will be returned as an Array.
     */
    public E[] toArray();

}

