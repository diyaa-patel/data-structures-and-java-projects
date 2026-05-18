package edu.ncsu.csc316.dsa.set;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for HashSet
 * Checks the expected outputs of the Set abstract data type behaviors when using
 * a hash table data structure 
 *
 * @author Dr. King
 * @author Diya Patel
 *
 */
public class HashSetTest {

	/** set instance */
    private Set<Integer> set;
    
    /** testSet instance */
    private Set<Integer> testSet;
    
    /**
     * Create new instances of a hash-based set before each test case executes
     */ 
    @Before
    public void setUp() {
        // Will use a "production" hash map with random alpha, beta values
        set = new HashSet<Integer>();
        
        // Will use a "testing" hash map which uses alpha=1, beta=1, prime=7
        testSet = new HashSet<Integer>(true);
    }

    /**
     * Test the output of the add behavior
     */      
    @Test
    public void testAdd() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        
     // Add a single element.
        set.add(5);
        assertEquals(1, set.size());
        assertTrue(set.contains(5));
        
        // Add a duplicate element; size should not change.
        set.add(5);
        assertEquals(1, set.size());
        
        // Add several more unique elements.
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());
        assertTrue(set.contains(10));
        assertTrue(set.contains(15));
        assertTrue(set.contains(20));
        assertTrue(set.contains(25));
    }

    /**
     * Test the output of the contains behavior
     */ 
    @Test
    public void testContains() {
    	 // Initially, the set should be empty.
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        
        // Add several elements.
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());

        // Verify that added elements are found.
        assertTrue(set.contains(5));
        assertTrue(set.contains(10));
        assertTrue(set.contains(15));
        assertTrue(set.contains(20));
        assertTrue(set.contains(25));
        
        // Verify that a non-added element is not found.
        assertFalse(set.contains(30));
    }

    /**
     * Test the output of the remove behavior
     */ 
    @Test
    public void testRemove() {
    	 // Add elements.
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());
        
        // Remove an existing element.
        Integer removed = set.remove(15);
        assertNotNull(removed);
        assertEquals(15, (int)removed);
        assertEquals(4, set.size());
        assertFalse(set.contains(15));
        
        // Attempt to remove a non-existing element.
        Integer removedNonExistent = set.remove(100);
        assertNull(removedNonExistent);
        assertEquals(4, set.size());
    }

    /**
     * Test the output of the retainAll behavior
     */     
    @Test
    public void testRetainAll() {       
        // Add elements to the set.
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());
        
        // Create another set with some overlapping elements.
        Set<Integer> other = new HashSet<Integer>();
        other.add(10);
        other.add(20);
        other.add(30);
        
        // Retain only elements that are also in 'other'.
        set.retainAll(other);
        
        // After retainAll, only 10 and 20 should remain.
        assertEquals(2, set.size());
        assertTrue(set.contains(10));
        assertTrue(set.contains(20));
        assertFalse(set.contains(5));
        assertFalse(set.contains(15));
        assertFalse(set.contains(25));
    }


    /**
     * Test the output of the removeAll behavior
     */    
    @Test
    public void testRemoveAll() {
    	 // Add elements to the set.
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());
        
        // Create another set with elements to remove.
        Set<Integer> other = new HashSet<Integer>();
        other.add(10);
        other.add(20);
        other.add(30);
        
        // Remove all elements that are present in 'other'.
        set.removeAll(other);
        
        // After removeAll, 10 and 20 should be gone.
        assertEquals(3, set.size());
        assertTrue(set.contains(5));
        assertTrue(set.contains(15));
        assertTrue(set.contains(25));
        assertFalse(set.contains(10));
        assertFalse(set.contains(20));
    }

    /**
     * Test the output of the addAll behavior
     */     
    @Test
    public void testAddAll() {
    	// Add elements to the set.
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());
        
        // Create another set with additional elements.
        Set<Integer> other = new HashSet<Integer>();
        other.add(30);
        other.add(40);
        other.add(50);
        
        // Add all elements from 'other' to the set.
        set.addAll(other);
        
        // The final set should contain the union of both sets.
        assertEquals(8, set.size());
        assertTrue(set.contains(5));
        assertTrue(set.contains(10));
        assertTrue(set.contains(15));
        assertTrue(set.contains(20));
        assertTrue(set.contains(25));
        assertTrue(set.contains(30));
        assertTrue(set.contains(40));
        assertTrue(set.contains(50));
    }

    /**
     * Test the output of the iterator behavior
     */ 
    @Test
    public void testIterator() {
        // Since our hash map uses random numbers, it can
        // be difficult to test that our hash set iterator returns
        // values in a certain order.
        // We could approach this problem with a few different options:
        // (1) instead of checking the specific order of entries
        //      visited by the iterator, we could save each
        //      iterator.next() into an array, then check that the 
        //      array *contains* the entry, regardless of its exact location
        //
        // (2) implement an isTesting flag for HashSet, similar to testSet in the setUp method. 
        //     This is the more appropriate option since we can control the
        //     entire execution and know exactly which values should appear
        //     in the set and in the correct expected order from using the iterator 
        //
        // Revisit your test cases for HashMap iterator -- those tests can be adapted
        //     to work here, too, since you have already worked out the details
        //     of where entries with certain keys will be stored and in what order
        //     they will be stored
        assertTrue(testSet.isEmpty());
        testSet.add(3);
        testSet.add(5);
        testSet.add(2);
        testSet.add(4);
        testSet.add(1);
        testSet.add(6);
        assertEquals(6, testSet.size());
        assertFalse(testSet.isEmpty());
        
        Iterator<Integer> it = testSet.iterator();
        assertTrue(it.hasNext());
        assertEquals(6, (int)it.next()); // should be index 0
        assertEquals(1, (int)it.next()); // should be index 2
        assertEquals(2, (int)it.next()); // should be index 3
        assertEquals(3, (int)it.next()); // should be index 4
        assertEquals(4, (int)it.next()); // should be index 5
        assertEquals(5, (int)it.next()); // should be index 6   
        assertFalse(it.hasNext());
    }
}