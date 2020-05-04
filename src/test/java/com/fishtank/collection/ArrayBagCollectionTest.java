package com.fishtank.collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.util.Iterator;

import static org.junit.Assert.*;

@Suite.SuiteClasses({ ArrayBagCollection.class })
public class ArrayBagCollectionTest {

    public ArrayBagCollectionTest() {}

    @Test
    public void testAdd() {
        ArrayBagCollection<Integer> bag = fillBag();

        assertEquals(5, bag.size());
    }


    @Test
    public void grab() {
        ArrayBagCollection<Integer> bag = fillBag();

        System.out.println(bag.grab());
        assertNotNull(bag.grab());
        assertEquals(5, bag.size());
    }


    @Test
    public void remove() {
        ArrayBagCollection<Integer> bag = fillBag();

        bag.remove(5);
        assertEquals(4, bag.size());
    }

    @Test
    public void size() {
        ArrayBagCollection<Integer> bag = fillBag();

        assertNotNull(bag.size());
        assertEquals(5, bag.size());
        bag.add(11);
        assertEquals(6, bag.size());
    }

    @Test
    public void capacityRemaining() {
        ArrayBagCollection<Integer> bag = fillBag();

        assertEquals(5, bag.capacityRemaining());
        bag.remove(7);
        assertEquals(6, bag.capacityRemaining());
        bag.remove(3);
        assertEquals(7, bag.capacityRemaining());
        bag.remove(20);
        assertEquals(7, bag.capacityRemaining());
    }

    @Test
    public void clear() {
        ArrayBagCollection<Integer> bag = fillBag();

        assertNotNull(bag.size());
        bag.clear();
        assertEquals(0, bag.size());
        bag.clear();
        assertEquals(0, bag.size());
    }

    @Test
    public void isEmpty() {
        ArrayBagCollection<Integer> bag = fillBag();

        assertNotNull(bag.size());
        bag.clear();
        assertEquals(0, bag.size());
        assertTrue(bag.isEmpty());
    }

    @Test
    public void isFull() {
        ArrayBagCollection<Integer> bag = fillBag();

        assertNotNull(bag.size());
        bag.add(11);
        bag.add(12);
        bag.add(13);
        bag.add(14);
        bag.add(15);

        assertTrue(bag.isFull());
    }

    @Test
    public void iterator() {
        ArrayBagCollection<Integer> bag = fillBag();

        Iterator<Integer> it = bag.iterator();
        int value = it.next();
        assertEquals(1, value);
        value = it.next();
        assertEquals(3, value);
        value = it.next();
        assertEquals(5, value);
        value = it.next();
        assertEquals(7, value);
        value = it.next();
        assertEquals(9, value);
    }


    //@Test
    public void testToArray() {
        ArrayBagCollection<Integer> bag = fillBag();

        Integer[] returnedArray = bag.toArray();
        assertNotNull(returnedArray);
        assertEquals(5, returnedArray.length);
    }

    public static ArrayBagCollection fillBag() {
        Integer[] intArr = new Integer[] {1, 3, 5, 7, 9};
        ArrayBagCollection bag = new ArrayBagCollection();

        for(int i: intArr) {
            bag.add(i);
        }

        return bag;
    }
}