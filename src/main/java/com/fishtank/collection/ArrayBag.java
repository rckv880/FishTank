//package com.fishtank.collection;
//
//import java.util.AbstractSet;
//import java.util.Collection;
//import java.util.Iterator;
//import java.util.NoSuchElementException;
//import java.util.Random;
//
//// implements a bag with an underlying array.
///**
// *
// * @author avleendhanjal
// *
// * @param <E>
// */
////
//public  class ArrayBag<E> extends AbstractSet<E> implements Bag<E> {
//
//
//	private final int FIXED_CAPACITY = 10;
//	protected int numItems; // number of elements in array to use.
//	protected E[] items; // the array to use.
//	Random generator; // to grab item in the bag at random.
//
//
//	    // constructor 1 which instantiates a bag with
//
//		// a fixed capacity of 10.
//
//
//	public ArrayBag() {
//
//			super();
//			numItems = 0;
//
//			items = (E[])(new Object [FIXED_CAPACITY]);
//			generator = new Random();
//		}
//
//
//
//		//allows a using program to specify capacity.
//		public ArrayBag(Collection<? extends E> c) {
//			this();
//
//			for (E items : c)
//			{
//				add(items);
//			}
//
//		}
//
//
//
//	@Override
//	public boolean add(E o)
//	{
//
//		// Adding a new item, but only if it can fit.
//
//		if (!(contains(o))) {
//
//			items[numItems++]= o;
//		}
//
//		return false;
//
//
//	}
//
//
//
//	@Override
//	public E grab()
//	{
//		return null;
//	}
//
//
//
//
//// see Random Obtainable lab.
//     // return super.get(generator.nextInt(super.size()));
//
//
//// gives back an item in the bag at random but does not remove it.
//
//
//
//
//
//
//	//remove the element from the set and returns true if the
//	// element was in the set.
//
//	@Override
//	public boolean remove(E item)
//	{
//		// search for the index of the element o in the set.
//		int index = 0;
//		boolean found = false;
//
//		for (int i= 0; i<numItems && !found; i++)
//		{ if ((items[i] == null && item==null)
//				|| (items[i] !=null && items[i].equals(item)))
//	{
//		index =i;
//		found =true;
//		}
//
//		}
//
//		if(found) // replace the element at index by last element.
//		{
//			items[index] = items[numItems -1];
//			items[numItems-1] = null; // removes reference to element.
//			numItems--;
//		}
//
//		return found;
//
//
//
//
//
//	}
//
//	@Override
//	public int size() {
//
//		// returns the number of items in the bag.
//		return numItems;
//
//	}
//
//	@Override
//	public int capacityRemaining() {
//
//		return 0;
//	}
//
//
//
//
//	@Override
//	public void clear() {
//
//		for (int i =0; i<numItems; i++) {
//			items[i] = null;
//			numItems = 0;
//
//		}
//
//	}
//
//
//	@Override
//	public boolean isEmpty() {
//
//		if (items.length == 0) {
//		}
//		return true;
//	}
//
//
//
//	@Override
//	public Iterator<E> iterator() {
//
//return new ArrayIterator<E>(items, numItems);
//
//	}
//
//	@Override
//	public boolean isFull() {
//		if (items.length == FIXED_CAPACITY) {
//
//		}
//		return false;
//
//}
//
//
//
//
//
//	@Override
//	public E[] toArray()
//	{
//         //need help.
//		return null;
//	}
//
//
//
//
//
//
//// Inner class which represents an iterator for an array.
//	private class ArrayIterator<E> implements Iterator<E>
//	{
//
//
//
//		private int numItems; // number of items in array to use.
//		private E[] items; // the array to use.
//		private int nextIndex; // index of next element for the iterator.
//
//
//		// constructor which accepts an array of elements.
//		public ArrayIterator(E[] items, int numItems)
//		{
//			if (numItems > items.length)
//				numItems = items.length;
//			this.numItems = numItems;
//			this.items = items;
//			nextIndex = 0; // start with index of first element in array.
//		}
//
//		// returns whether there is still another element.
//	@Override
//	public boolean hasNext() {
//
//		return (nextIndex<numItems);
//	}
//
//
//
//	// returns the next element or throws a NoSuchElementException.
//	// it there are no further elements.
//	@Override
//	public E next() {
//
//		if (!hasNext())
//
//			throw new NoSuchElementException("There are no elements to remove");
//
//		return items[nextIndex++];
//
//	}
//
//	}
//
//
//
//
//
//
//
//
//
//
//
//
//
//	}