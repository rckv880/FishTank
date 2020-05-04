//package com.fishtank.collection;
//
//import java.util.AbstractSet;
//import java.util.Collection;
//import java.util.Iterator;
//
///**
// *
// * @author avleendhanjal
// *
// * @param <E>
// */
//
////implements an underlying DOUBLY linked list.
//
//public class LinkedBag<E> extends AbstractSet<E> implements Bag<E>{
//
//
//	protected Node<E> tail; //  which is the last node.
//	protected  int numItems; //size of the linked list.
//	protected Node<E> head; // which is the first node.
//
//	//default constructor that creates a new set.
//	// that is initially empty.
//	public LinkedBag() {
//
//		super();
//		numItems=0;
//		this.head = null; // as initially the length of the linked list is empty hence the tail & head point to null.
//		this.tail = null;
//
//	}
//
//	// constructor for creating a new set which
//	// initially holds the elements in the collection c
//	public LinkedBag(Collection<? extends E> c) {
//		this();
//		for (E element: c) {
//			add(element);
//		}
//	}
//
//
//
//
//	@Override
//	public boolean add(E item) {
//
//		if (!(contains(item)))
//		{ Node <E> newNode = new Node <E>(item);
//
//		// adds the new node to the front of the list.
//		newNode.next = head;
//		newNode = head;
//		numItems++;
//
//		return true;
//
//		}
//		return false;
//	}
//
//	@Override
//	public E grab() {
//
//		return null;
//	}
//
//	@Override
//	public boolean remove (E item) {
//
//		return false;
//	}
//
//	@Override
//	public int size() {
//
//		return numItems;
//	}
//
//	@Override
//	public int capacityRemaining() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//
//	// removes all elements from the set.
//	@Override
//	public void clear() {
//
//		Node <E> n = head;
//
//		while (n!= null) {
//			Node <E> next = n.next;
//			n.previous = n.next = null;
//			n.element = null;
//			n = next;
//		}
//
//		head = tail = n = null;
//		numItems = 0;
//
//	}
//
//	@Override
//	public boolean isEmpty() {
//		return numItems == 0;
//	}
//
//	@Override
//	public boolean isFull() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//
//	// iterator for through the elements in the set.
//	@Override
//	public Iterator<E> iterator() {
//		return new LinkedIterator<E>(head);
//
//	}
//
//	@Override
//	public E[] toArray() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	// inner class which represents a node in a SINGLE but need to
//	// change to DOUBLE- linked list.
//
//	protected class Node<E>
//	{
//		public E element;
//		public Node<E> next; // pointer to the next node.
//		public Node<E> previous; // pointer to the previous node.
//
//		public Node(E element)
//		{
//			this.element = element;
//			next = null;
//		}
//	}
//
//
//	@Override public String toString() {
//
//		return null; //numItems.toString();
//	}
//
//}