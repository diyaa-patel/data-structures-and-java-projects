package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;
import java.util.Random;

/**
 * QuickSorter sorts arrays of comparable elements using the quicksort
 * algorithm. This implementation allows the client to specify a specific pivot
 * selection strategy: (a) use the first element as the pivot, (b) use the last
 * element as the pivot, (c) use the middle element as the pivot, or (d) use an
 * element at a random index as the pivot.
 * 
 * Using the randomized pivot selection strategy ensures O(nlogn)
 * expected/average case runtime when sorting n elements that are comparable
 * 
 * @author Dr. King
 * @author Diya Patel
 *
 * @param <E> the type of elements to sort; elements must be {@link Comparable}
 */
public class QuickSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

	/**
     * Pivot selection strategy that uses the element at the first index each time a
     * pivot must be selected
     */
    public static final PivotSelector FIRST_ELEMENT_SELECTOR = new FirstElementSelector();
    
    /**
     * Pivot selection strategy that uses the element at the last index each time a
     * pivot must be selected
     */
    public static final PivotSelector LAST_ELEMENT_SELECTOR = new LastElementSelector();
    
    /**
     * Pivot selection strategy that uses the element at the middle index each time
     * a pivot must be selected
     */
    public static final PivotSelector MIDDLE_ELEMENT_SELECTOR = new MiddleElementSelector();
    
    /**
     * Pivot selection strategy that uses the element at a randomly-chosen index
     * each time a pivot must be selected
     */
    public static final PivotSelector RANDOM_ELEMENT_SELECTOR = new RandomElementSelector();
 
    /** the client's chosen PivotSelector */ 
    private PivotSelector selector;

    /**
     * Constructs a new QuickSorter with a provided custom Comparator and a
     * specified PivotSelector strategy
     * 
     * @param comparator a custom comparator to use when sorting
     * @param selector   the pivot selection strategy to use when selecting pivots
     */
    public QuickSorter(Comparator<E> comparator, PivotSelector selector) {
        super(comparator);
        setSelector(selector);
    }

    /**
     * Constructs a new QuickSorter using the natural ordering of elements. Pivots
     * are selected using the provided PivotSelector strategy
     * 
     * @param selector the pivot selection strategy to use when selecting pivots
     */
    public QuickSorter(PivotSelector selector) {
        this(null, selector);
    }

    /**
     * Constructs a new QuickSorter with a provided custom Comparator and the
     * default random pivot selection strategy
     * 
     * @param comparator a custom comparator to use when sorting
     */
    public QuickSorter(Comparator<E> comparator) {
        this(comparator, null);
    }

    /**
     * Constructs a new QuickSorter that uses an element's natural ordering and uses
     * the random pivot selection strategy
     */
    public QuickSorter() {
        this(null, null);
    }
    
    private void setSelector(PivotSelector selector) {
        if(selector == null) {
            this.selector = new RandomElementSelector();
        } else {
            this.selector = selector;
        }
    }

	/**
	 * sorts the data into the correct order 
	 * @param data the data that needs to be sorted
	 */
	@Override
	public void sort(E[] data) {
		quickSort(data, 0, data.length - 1); 
	}

	/**
	 * partitions the array 
	 * @param data the data in the array 
	 * @param low the lowest index 
	 * @param high the highest index 
	 * @return the partition index 
	 */
	private int partition(E[] data, int low, int high) {
		int pivInd = selector.selectPivot(low, high); 
		swap(data, pivInd, high); 
		return partitionHelper(data, low, high);
	}
	
	/**
	 * sorts the array based on the piviot 
	 * @param data the data in the array 
	 * @param low the lowest index in the array 
	 * @param high the highest index in the array 
	 */
	private void quickSort(E[] data, int low, int high) {
		if(low < high) {
			int pivot = partition(data, low, high); 
			quickSort(data, low, pivot - 1); 
			quickSort(data, pivot + 1, high); 
		}
	}
	
	private int partitionHelper(E[] data, int low, int high) {
		E pivot = data[high];
		int seperator = low; 
		for(int i = low; i < high; i++) {
			if(compare(data[i], pivot) <= 0) {
				swap(data, seperator, i); 
				seperator++; 
			}
		}
		swap(data, seperator, high); 
		return seperator; 
	}
	
	/**
	 * swaps the pivot to be the last element in the array 
	 * @param data the array of data
	 * @param index1 the pivot to be switched
	 * @param index2 the last index in the array
	 */
	private void swap(E[] data, int index1, int index2) {
		E temp = data[index1];
		data[index1] = data[index2]; 
		data[index2] = temp; 
	}

    /**
     * FirstElementSelector chooses the first index of the array as the index of the
     * pivot element that should be used when sorting
     * 
     * @author Dr. King
     *
     */
    public static class FirstElementSelector implements PivotSelector {

        @Override
        public int selectPivot(int low, int high) {
            return low;
        }
    }
    
    /**
     * LastElementSelector chooses the last index of the array as the index of the
     * pivot element that should be used when sorting
     * 
     * @author Dr. King
     * @author Diya Patel 
     *
     */
    public static class LastElementSelector implements PivotSelector {

        @Override
        public int selectPivot(int low, int high) {
            return high;
        }
    }
    
    /**
     * MiddleElementSelector chooses the middle index of the array as the index of the
     * pivot element that should be used when sorting
     * 
     * @author Dr. King
     * @author Diya Patel 
     *
     */
    public static class MiddleElementSelector implements PivotSelector {

        @Override
        public int selectPivot(int low, int high) {
            return (high + low) / 2;
        }
    }
    
    /**
     * RandomElementSelector chooses the random index of the array as the index of the
     * pivot element that should be used when sorting
     * 
     * @author Dr. King
     * @author Diya Patel 
     *
     */
    public static class RandomElementSelector implements PivotSelector {
    	/** random sorter */
    	Random random = new Random(); 
        @Override
        public int selectPivot(int low, int high) {
            return random.nextInt(high - low + 1) + low;
        }
    }
    
    /**
     * Defines the behaviors of a PivotSelector
     * 
     * @author Dr. King
     *
     */
    private interface PivotSelector {
        /**
         * Returns the index of the selected pivot element
         * 
         * @param low  - the lowest index to consider
         * @param high - the highest index to consider
         * @return the index of the selected pivot element
         */
        int selectPivot(int low, int high);
    }
 
}