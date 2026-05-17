package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * InsertionSorter uses the insertion sort algorithm to sort data.
 * 
 * @author Dr. King
 * @author Diya Patel
 * 
 * @param <E> the type of data 
 */
public class InsertionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

    
	/**
	 * contructor for insertion sorter which sets the comparator to null 
	 */
    public InsertionSorter() {
        super(null);
    }
    
    /**
     * constructor for insertion sorter that sets the comparator to the parameter 
     * @param comparator the comparator to set to 
     */
    public InsertionSorter(Comparator<E> comparator) {
        super(comparator);
    }
    
    /**
	 * sorts the data given in the array of objects 
	 * @param dataAscending an a array of objects to sort 
	 */
	public void sort(E[] dataAscending) {
		E x; 
		int j; 
		for(int i = 1; i < dataAscending.length; i++) {
			x = dataAscending[i]; 
			j = i - 1; 
			while(j >= 0 && compare(dataAscending[j], x) > 0) {
				dataAscending[j + 1] = dataAscending[j]; 
				j--; 
				dataAscending[j + 1] = x; 
			}
		}
		
	}

	
	
}
