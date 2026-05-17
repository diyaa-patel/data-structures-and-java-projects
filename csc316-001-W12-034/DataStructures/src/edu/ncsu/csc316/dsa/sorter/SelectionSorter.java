package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * SelectionSorter uses the selection sort algorithm to sort data
 * @author Dr. King
 * @author Diya Patel 
 *
 * @param <E> the generic type of data to sort
 */
public class SelectionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
	
	 /**
     * constructor that calls abstract method and sets the comparator to null
     */
    public SelectionSorter() {
    	super(null); 
    }
    
	/**
	 * constructor that sets the comparator
	 * @param comparator the comparator to set it to 
	 */
    public SelectionSorter(Comparator<E> comparator) {
        super(comparator);
    }
    
    
    /**
     * sorts the data into order using selection sort 
     * @param data the array of data to sort 
     */
    public void sort(E[] data) {
    	int min; 
    	E x; 
        for(int i = 0; i < data.length; i++) {
        	min = i; 
        	for(int j = i + 1; j < data.length; j++) {
        		if(compare(data[j], data[min]) < 0) {
        			min = j; 
        		}
        	}
        	if(i != min) {
        		x = data[i]; 
        		data[i] = data[min]; 
        		data[min] = x; 
        	}
        }
    }
}
