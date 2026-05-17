package edu.ncsu.csc316.dsa.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A singly-linked list is a linked-memory representation of the List abstract
 * data type. This list maintains a dummy/sentinel front node in the list to
 * help promote cleaner implementations of the list behaviors. This list also
 * maintains a reference to the tail/last node in the list at all times to
 * ensure O(1) worst-case cost for adding to the end of the list. Size is
 * maintained as a global field to allow for O(1) size() and isEmpty()
 * behaviors.
 * 
 * @author Dr. King
 * @author Diya Patel
 *
 * @param <E> the type of elements stored in the list
 */
public class SinglyLinkedList<E> extends AbstractList<E> {

    /** A reference to the dummy/sentinel node at the front of the list **/
    private LinkedListNode<E> front;
    
    /** A reference to the last/final node in the list **/
    private LinkedListNode<E> tail;
    
    /** The number of elements stored in the list **/
    private int size;
        
    /**
     * Constructs an empty singly-linked list
     */     
    public SinglyLinkedList() {
        front = new LinkedListNode<E>(null);
        tail = null;
        size = 0;
    }

    /**
     * Adds a new element to the list at the specified index.
     * 
     * @param index   the index at which to add the new element to the list
     * @param element the new element to add to the list
     * @throws IndexOutOfBoundsException if the provided index is not a valid index
     *                                   based on the current state of the list
     */
	@Override
	public void add(int index, E element) {
		checkIndexForAdd(index);
		LinkedListNode<E> current;
		
		if(index == 0) {
			if(front == null) {
				front = new LinkedListNode<>(element); 
				if(tail == null) {
					tail = front; 
				} 
			}
			else { 
				current = new LinkedListNode<>(element, front.getNext()); 
				front.next = current; 
				if(tail == null) {
					tail = current; 
				}
			}
		}
		else {
			current = front; 
			for(int i = 0; i < index; i++) {
				current = current.next; 
			}
			
			current.next = new LinkedListNode<>(element, current.getNext()); 
			
			if(current.next.getNext() == null) {
				tail = current.next; 
			}
		}
	
		
		this.size++; 
		
	}

	/**
     * Returns the element at the specified index of the list
     * 
     * @param index the index at which to retrieve the element
     * @return the element at the specified index of the list
     * @throws IndexOutOfBoundsException if the specified index is not a valid index
     *                                   based on the current state of the list
     */
	@Override
	public E get(int index) {
		checkIndex(index); 
		
		LinkedListNode<E> current = front.getNext();
		for(int i = 0; i < index; i++) {
			current = current.next; 
		}
			
		return current.getElement();
	}

	/**
     * Removes and returns the element at the specified index of the list
     * 
     * @param index the index of the element to remove from the list
     * @return the element at the specified index of the list
     * @throws IndexOutOfBoundsException if the provided index is not a valid index
     *                                   based on the current state of the list
     */
	@Override
	public E remove(int index) {
		checkIndex(index); 
		
		LinkedListNode<E> current = front; 
		for(int i = 0; i < index; i++) {
			current = current.next; 
		}
		LinkedListNode<E> rtn = current.next; 
		current.next = rtn.next; 
		
		if(rtn == tail) {
			if(current == front) {
				tail = null; 
			}
			else {
				tail = current; 
			}
		}
		size--; 
		return rtn.getElement();
	}

	/**
     * Updates the element at the specified index of the list
     * 
     * @param index   the index at which an existing element should be updated
     * @param element the new element to store are the provided index
     * @return before the original element that was replaced by the updated element
     * @throws IndexOutOfBoundsException if the provided index is not a valid index
     *                                   based on the current state of the list
     */
	@Override
	public E set(int index, E element) {
		checkIndex(index); 
		
		LinkedListNode<E> current = front.next; 
		for(int i = 0; i < index; i++) {
			current = current.next; 
		}
		
		E before = current.getElement(); 
		current.element = element; 
		return before; 
	}

	/**
	 * returns the size of the list 
	 * @return size the size of the list 
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
     * For a singly-linked list, this behavior has O(1) worst-case
     * runtime.
     */
    @Override
    public E last() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("The list is empty");
        }
        return tail.getElement();
    }

    /**
     * For this singly-linked list, addLast(element) behavior has O(1) worst-case runtime.
     */    
    @Override
    public void addLast(E element) { 
    	if(isEmpty()) {
    		front.next = new LinkedListNode<>(element); 
    		tail = front.next; 
    	}
    	else {
    		tail.next = new LinkedListNode<>(element);
    		tail = tail.next; 
    	}
    	size++; 
    }
    

    /**
     * returns a newly constructed iterator 
     * @return ElementIterator constructed instance 
     */
	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}
	
	/**
	 * private class that constructs a ListNode instance to point to the data
	 * @param <E> the type of data in the node 
	 */
	private static class LinkedListNode<E> {
		/** the element */ 
		 private E element;
		 /** next node */ 
	     private LinkedListNode<E> next; 
	     
	     /**
	      * Constructor for LinkedListNode 
	      * @param element the object at the given node 
	      */
	    public LinkedListNode(E element) {
			this(element, null); 
		}
	    
	    /**
	      * Constructor for LinkedListNode 
	      * @param element the object at the given node 
	      * @param next the next node in the list 
	      */
	    public LinkedListNode(E element, LinkedListNode<E> next) {
	    	this.element = element; 
	    	this.next = next; 
		}


	    /**
	     * returns the element in the node 
	     * @return element the element in the node 
	     */
		public E getElement() {
			return element; 
		}
		
		/**
		 * returns the next node in the list 
		 * @return next the next node in the list 
		 */
		public LinkedListNode<E> getNext(){
			return next; 
		}
	   
	 }
	
	/**
	 * Element Iterator that itereates through the list 
	 */
	private class ElementIterator implements Iterator<E> {
	    /**
	     * Keep track of the next node that will be processed
	     */
	    private LinkedListNode<E> current;
	    
	    
	    /**
	     * Construct a new element iterator where the cursor is initialized 
	     * to the beginning of the list.
	     */
	    public ElementIterator() {
	        current = front.getNext(); 
	    }

	    /**
	     * checks if there is a next element in the list. 
	     */
	    @Override
	    public boolean hasNext() {
	        return current != null; 
	    }

	    /**
	     * goes to the next ListNode in the list. 
	     * @throws NoSuchElementException if there is no next element. 
	     */
	    @Override
	    public E next() {
	        if(!hasNext()) {
	        	throw new NoSuchElementException(); 
	        }
	        
	        E element = current.getElement(); 
	        current = current.next;
	        return element; 
	    }
	     
	    /**
	     * remove method that throws exception if called
	     * @throws UnsupportedOperationException if it is called
	     */
	    @Override    
	    public void remove() {
		    // DO NOT CHANGE THIS METHOD
	        throw new UnsupportedOperationException(
	            "This SinglyLinkedList implementation does not currently support removal of elements when using the iterator.");
	    }
	}
    
    
}