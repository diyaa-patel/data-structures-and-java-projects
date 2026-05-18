package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * sorts the array using bubble sort 
 * @param <E> the type of data 
 * @author Diya Patel 
 */
public class BubbleSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

	/**
	 * sets the comparator when not null 
	 * @param comparator sets the comparator to the given parameter 
	 */
	public BubbleSorter(Comparator<E> comparator) {
		super(comparator);
	}
	
	/**
	 * sets the comparator to null if no parameter 
	 */
	public BubbleSorter() {
		super(null); 
	}

	/**
	 * sorts the data given using bubble sort 
	 * @param data the data to sort 
	 */
	public void sort(E[] data) {
		boolean r = true; 
		E x; 
		do {
			r = false;  
			for(int i = 1; i < data.length; i++) {
				if(compare(data[i], data[i - 1]) < 0) {
					x = data[i - 1]; 
					data[i - 1] = data[i]; 
					data[i] = x; 
					r = true; 
				}
			}
		} while (r);
		
	}

}
