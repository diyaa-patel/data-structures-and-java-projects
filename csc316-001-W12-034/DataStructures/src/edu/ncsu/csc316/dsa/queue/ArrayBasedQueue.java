package edu.ncsu.csc316.dsa.queue;

import java.util.NoSuchElementException;

/**
 * The Array-based Queue is implemented as a circular array-based data structure
 * to support efficient, O(1) worst-case Queue abstract data type behaviors. The
 * internal array dynamically resizes using the doubling strategy to ensure O(1)
 * amortized cost for adding to the queue.
 * 
 * @author Dr. King
 * @author Diya Patel 
 *
 * @param <E> the type of elements stored in the queue
 */
public class ArrayBasedQueue<E> extends AbstractQueue<E> {

    /**
     * Internal array to store the data within the queue
     */
    private E[] data;

    /**
     * A reference to the index of the first element in the queue
     */
    private int front;

    /**
     * A reference to the index immediately after the last element in the queue
     */
    private int rear;

    /**
     * The number of elements stored in the queue
     */
    private int size;

    /**
     * The initial default capacity of the internal array that stores the data
     */
    private static final int DEFAULT_CAPACITY = 0;

    /**
     * Constructs a new array-based queue with the given initialCapcity for the
     * array
     * 
     * @param initialCapacity the initial capacity to use when creating the initial
     *                        array
     */
    @SuppressWarnings("unchecked")
    public ArrayBasedQueue(int initialCapacity) {
        data = (E[]) new Object[initialCapacity];
        size = 0;
    }

    /**
     * Constructs a new array-based queue with the default initial capacity for the
     * array
     */
    public ArrayBasedQueue() {
        this(DEFAULT_CAPACITY);
    }
    
    /**
     * To ensure amortized O(1) cost for adding to the array-based queue, use the
     * doubling strategy on each resize. Here, we add +1 after doubling to handle
     * the special case where the initial capacity is 0 (otherwise, 0*2 would still
     * produce a capacity of 0).
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
            @SuppressWarnings("unchecked")
            E[] newData = (E[]) new Object[newCapacity];
            
            for(int i = 0; i < size(); i++) {
            	newData[i] = data[front]; 
            	front = (front + 1) % oldCapacity; 
            }
            
            data = newData; 
            front = 0; 
            rear = size; 

            // Remember that we cannot copy an array the same way we do
            // when resizing an array-based list since we do not want to
            // "break" the elements in the queue since there may be wrap-around
            // at the end of the array
        }
    }

    /**
     * The code for this method is based on the mergesort algorithm on page 242 in the course textbook
     * "Data Structures and Algorithms" by Goodrich, Tamassia, Goldwasser.
     * 
	 * Adds a new element to the back of the queue
	 * 
	 * @param element the new element to add to the queue
	 */
	@Override
	public void enqueue(E element) {
	    ensureCapacity(size + 1); 
		data[rear] = element; 
		rear = (rear + 1) % data.length; 
		size++; 
	}

	/**
	 * Removes and returns the front/first element in the queue
	 * 
	 * @return the front/first element in the queue
	 * @throws NoSuchElementException if the queue is empty
	 */
	@Override
	public E dequeue() {
		if(isEmpty()) {
			throw new NoSuchElementException(); 
		}
		E element = data[front];
		data[front] = null; 
		front = (front + 1) % data.length; 
		size--; 
		return element;
	}

	/**
	 * Returns (but does not remove) the front/first element in the queue
	 * 
	 * @return the front/first element in the queue
	 * @throws NoSuchElementException if the queue is empty
	 */
	@Override
	public E front() {
		if(isEmpty()) {
			throw new NoSuchElementException(); 
		} 
		return data[front];
	}

	/**
	 * Returns the number of elements stored in the queue
	 * 
	 * @return the number of elements stored in the queue
	 */
	@Override
	public int size() {
		return size;
	}

} 