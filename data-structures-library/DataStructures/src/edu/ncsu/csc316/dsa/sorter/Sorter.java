package edu.ncsu.csc316.dsa.sorter;

/**
 * Interface that defines the sorting behavior
 * @author Dr. King
 * @author Diya Patel 
 * @param <E> the type of data
 */
public interface Sorter<E> {
	
	/**
	 * sorts the data into the correct order 
	 * @param data the data that needs to be sorted
	 */
	void sort(E[] data);  
}