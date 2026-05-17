package edu.ncsu.csc316.dsa.sorter;

import java.util.Arrays;
import java.util.Comparator;

/**
 * MergeSorter sorts arrays of comparable elements using the merge sort
 * algorithm. This implementation ensures O(nlogn) worst-case runtime to sort an
 * array of n elements that are comparable.
 * 
 * @author Dr. King
 * @author Diya Patel
 *
 * @param <E> the type of elements to sort; elements must be {@link Comparable}
 */
public class MergeSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

    /**
     * Constructs a new MergeSorter with a specified custom Comparator
     * 
     * @param comparator a custom Comparator to use when sorting
     */
    public MergeSorter(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * Constructs a new MergeSorter with comparisons based on the element's natural
     * ordering
     */ 
    public MergeSorter() {
        this(null);
    }

    /**
	 * sorts the data into the correct order 
	 * @param data the data that needs to be sorted
	 */
	@Override
	public void sort(E[] data) {
		if(data.length >= 2) {
			int mid = data.length / 2; 
			E[] left = Arrays.copyOfRange(data, 0, mid);
			E[] right = Arrays.copyOfRange(data, mid, data.length);
			
			sort(left); 
			sort(right); 
			
			merge(left, right, data); 
		} 
	}
	
	/**
	 * merges the data in the array 
	 * @param left the left side of the array 
	 * @param right the right side of the array 
	 * @param data the original array 
	 */
	private void merge(E[] left, E[] right, E[] data) {
		int leftIndex = 0; 
		int rightIndex = 0; 
		while(leftIndex + rightIndex < data.length) {
			if(rightIndex == right.length || (leftIndex < left.length && compare(left[leftIndex], right[rightIndex]) <= 0)) {
				data[leftIndex + rightIndex] = left[leftIndex];  
				leftIndex = leftIndex + 1; 
			}
			else {
				data[leftIndex + rightIndex] = right[rightIndex]; 
				rightIndex = rightIndex + 1; 
			}
		}
	}

}