package edu.ncsu.csc316.dsa.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An array-based list is a contiguous-memory representation of the List
 * abstract data type. This array-based list dynamically resizes to ensure O(1)
 * amortized cost for adding to the end of the list. Size is maintained as a
 * global field to allow for O(1) size() and isEmpty() behaviors.
 * 
 * @author Dr. King
 * @author Diya Patel 
 *
 * @param <E> the type of elements stored in the list
 */
public class ArrayBasedList<E> extends AbstractList<E> {

    /**
     * The initial capacity of the list if the client does not provide a capacity
     * when constructing an instance of the array-based list
     **/
    private final static int DEFAULT_CAPACITY = 0;

    /** The array in which elements will be stored **/
    private E[] data;

    /** The number of elements stored in the array-based list data structure **/
    private int size;

    /**
     * Constructs a new instance of an array-based list data structure with the
     * default initial capacity of the internal array
     */
    public ArrayBasedList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructs a new instance of an array-based list data structure with the
     * provided initial capacity
     * 
     * @param capacity the initial capacity of the internal array used to store the
     *                 list elements
     */
    @SuppressWarnings("unchecked")
    public ArrayBasedList(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    /**
     *  The code for this method is based on the mergesort algorithm on page 260 in the course textbook
     * "Data Structures and Algorithms" by Goodrich, Tamassia, Goldwasser.
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
		ensureCapacity(size + 1); 
		
		for(int i = size - 1; i >= index; i--) {
			data[i + 1] = data[i]; 
		}
			
		data[index] = element; 
		size++;  
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
		return data[index]; 
	}

	 /**
	 *  The code for this method is based on the mergesort algorithm on page 260 in the course textbook
     * "Data Structures and Algorithms" by Goodrich, Tamassia, Goldwasser.
     * Removes and returns the element at the specified index of the list
     * 
     * @param index the index of the element to remove from the list
     * @return rtn the element at the specified index of the list
     * @throws IndexOutOfBoundsException if the provided index is not a valid index
     *                                   based on the current state of the list
     */ 
	@Override
	public E remove(int index) {
		checkIndex(index); 

		E rtn = data[index]; 
		for(int i = index; i < size() - 1; i++) {
			data[i] = data[i + 1]; 
	    }
	    data[size() - 1] = null; 
		size--;  
		return rtn; 
	}

	/**
	 *  The code for this method is based on the mergesort algorithm on page 260 in the course textbook
     * "Data Structures and Algorithms" by Goodrich, Tamassia, Goldwasser.
     * Updates the element at the specified index of the list
     * 
     * @param index   the index at which an existing element should be updated
     * @param element the new element to store are the provided index
     * @return rtn the original element that was replaced by the updated element
     * @throws IndexOutOfBoundsException if the provided index is not a valid index
     *                                   based on the current state of the list
     */
	@Override
	public E set(int index, E element) {
		checkIndex(index); 
		E rtn = data[index];
		data[index] = element; 
		return rtn; 
	}

	/**
     * Returns the number of elements in the list
     * 
     * @return the number of elements in the list
     */
	@Override
	public int size() {
		return size;
	}

	/**
	 * interator for the arraybased List 
	 * @return a new instance of the element iterator
	 */
	@Override
	public Iterator<E> iterator() {
		return new ElementIterator(); 
	}
	
	/**
	 * private class ElementIterator that iterates to the correct place and goes to the next 
	 * and checks to see if there is a has next in the list 
	 */
	private class ElementIterator implements Iterator<E> {
		/** current position */
	    private int position;
	    /** remove boolean */
	    private boolean removeOK;

	    /**
	     * Construct a new element iterator where the cursor is initialized 
	     * to the beginning of the list.
	     */
	    public ElementIterator() {
	        this.position = 0; 
	        this.removeOK = false; 
	    }

	    /**
	     * returns true if there are additional elements to iterate through
	     * if we haven't reached the size yet than there must be additional data. 
	     * @return true if there are more elements
	     */
	    @Override
	    public boolean hasNext() {
	        return position < size; 
	    }

	    /**
	     * returns the next element in the array 
	     * 
	     * @return the next element
	     * @throws NoSuchElementException if there are no more elements to iterate through
	     */
	    @Override
	    public E next() {
	    	if(!hasNext()) {
	    		throw new NoSuchElementException(); 
	    	}
	    	E element = data[position]; 
	    	position++; 
	    	removeOK = true; 
	    	return element;  
	    }
	        
	    /**
	     * removes the last element that was returned by next 
	     * 
	     * @throws IllegalStateException if removeOK is false
	     */
	    @Override
	    public void remove() {
	        if(!removeOK) {
	        	throw new IllegalStateException();
	        }
	        
	        //shifts the elements from the current position to the left 
	        for(int i = position - 1; i < size - 1; i++) {
	        	data[i] = data[i + 1]; 
	        }
	        
	        data[size - 1] = null; 
	        size--; 
	        position--; 
	        removeOK = false; 
	    }
	}
	
	/**
	 * To ensure amortized O(1) cost for adding to the end of the array-based list,
	 * use the doubling strategy on each resize. Here, we add +1 after doubling to
	 * handle the special case where the initial capacity is 0 (otherwise, 0*2 would
	 * still produce a capacity of 0).
	 * 
	 * @param minCapacity the minimium capacity that must be supported by the
	 *                    internal array
	 */
    private void ensureCapacity(int minCapacity) {
        int oldCapacity = data.length;
        if (minCapacity > oldCapacity) {
            int newCapacity = (oldCapacity * 2) + 1;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            data = Arrays.copyOf(data, newCapacity);
        }
    }
    
}
