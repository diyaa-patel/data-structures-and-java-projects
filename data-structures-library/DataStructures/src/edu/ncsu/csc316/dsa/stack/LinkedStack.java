package edu.ncsu.csc316.dsa.stack;

import java.util.EmptyStackException;

import edu.ncsu.csc316.dsa.list.SinglyLinkedList;

/**
 * The Linked Stack is implemented as a singly-linked list data structure to
 * support efficient, O(1) worst-case Stack abstract data type behaviors.
 * 
 * @author Dr. King
 * @author Diya Patel 
 *
 * @param <E> the type of elements stored in the stack
 */
public class LinkedStack<E> extends AbstractStack<E> {

    /** Delegate to our existing singly linked list class **/
    private SinglyLinkedList<E> list;

    /**
     * Construct a new singly-linked list to use when modeling the last-in-first-out
     * paradigm for the stack abstract data type.
     */
    public LinkedStack() {
        list = new SinglyLinkedList<E>();
    }

    /**
     * Adds a new element to the top of the stack
     * 
     * @param element the new element to add to the stack
     */
	@Override
	public void push(E element) {
		list.add(0, element);
		
	}

	/**
     * Removes and returns the top element from the stack
     * 
     * @return the top element from the stack
     * @throws EmptyStackException if the stack is empty
     */
	@Override
	public E pop() {
		if(list.isEmpty()) {
			throw new EmptyStackException(); 
		}
		return list.remove(0);
	}

	 /**
     * Returns (but does not remove) the top element from the stack
     * 
     * @return the top element on the stack
     * @throws EmptyStackException if the stack is empty
     */
	@Override
	public E top() {
		if(list.isEmpty()) {
			throw new EmptyStackException(); 
		}
		return list.get(0);
	}

	/**
     * Returns the number of elements stored in the stack
     * 
     * @return the number of elements stored in the stack
     */
	@Override
	public int size() {
		return list.size();
	}
   
}