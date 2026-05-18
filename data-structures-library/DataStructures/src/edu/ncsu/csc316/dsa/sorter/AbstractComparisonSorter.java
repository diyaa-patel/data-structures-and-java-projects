package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * extracts the common functionality between InsertionSorter and SelectionSorter 
 * @author Diya Patel 
 * @param <E> the type of data 
 */
public abstract class AbstractComparisonSorter<E extends Comparable<E>> implements Sorter<E> {

	/** the comparator to use */ 
    private Comparator<E> comparator;
    
    /**
     * constructor that sets the comparator 
     * @param comparator the comparator to set it to 
     */
    public AbstractComparisonSorter(Comparator<E> comparator) {
        setComparator(comparator);
    }
    
    /**
     * sets the comparator to the given parameter
     * @param comparator the comparator to set it to 
     */
    private void setComparator(Comparator<E> comparator) {
        if(comparator == null) {
            this.comparator = new NaturalOrder();
        } else {
            this.comparator = comparator;
        }
    }   
    
    /**
     * makes sure the natural order of comparable is held 
     */
    private class NaturalOrder implements Comparator<E> {
        public int compare(E first, E second) {
            return ((Comparable<E>) first).compareTo(second);
        }
    }
    
    /**
     * compares two objects to see if they are equal 
     * @param first the first object we are comparing 
     * @param second the second object we are comparing 
     * @return 0 is first and second are equal 
     */
    public int compare(E first, E second) {
        return comparator.compare(first,  second);
    }
}