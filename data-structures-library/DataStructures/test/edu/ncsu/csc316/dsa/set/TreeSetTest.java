package edu.ncsu.csc316.dsa.set;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for TreeSet
 * Checks the expected outputs of the Set abstract data type behaviors when using
 * a balanced search tree data structure 
 *
 * @author Dr. King
 * @author Diya Patel
 *
 */
public class TreeSetTest {

	/** the set instance */
    private Set<Integer> set;

    /**
     * Create a new instance of a tree-based set before each test case executes
     */      
    @Before
    public void setUp() {
        set = new TreeSet<Integer>();
    }

    /**
     * Test the output of the add behavior
     */     
    @Test
    public void testAdd() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        
        set.add(5);
        assertEquals(1, set.size());
        assertFalse(set.isEmpty());
        
        set.add(5);
        assertEquals(1, set.size());
        
        
        set.add(10);
        set.add(15);
        assertEquals(3, set.size());
        assertTrue(set.contains(10));
        assertTrue(set.contains(15));
    }

    /**
     * Test the output of the contains behavior
     */ 
    @Test
    public void testContains() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());

        assertTrue(set.contains(5));
        assertTrue(set.contains(10));
        assertTrue(set.contains(15));
        assertTrue(set.contains(20));
        assertTrue(set.contains(25));

        // Check for a value that wasn't added
        assertFalse(set.contains(30));
    }

    /**
     * Test the output of the remove behavior
     */ 
    @Test
    public void testRemove() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());
        
        Integer removed = set.remove(15);
        assertNotNull(removed);
        assertEquals(15, (int)removed);
        assertEquals(4, set.size());
        assertFalse(set.contains(15));
        
        // Remove an element that does not exist
        Integer removedNonExistent = set.remove(100);
        assertNull(removedNonExistent);
        assertEquals(4, set.size());
    }
    
    /**
     * Test the output of the retainAll behavior
     */ 
    @Test
    public void testRetainAll() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());
        
        Set<Integer> other = new TreeSet<Integer>();
        other.add(10);
        other.add(20);
        other.add(30);
        
     // Retain only the elements that are also in 'other'
        set.retainAll(other);
        
        // After retain, only 10 and 20 should remain
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
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());
        
        Set<Integer> other = new TreeSet<Integer>();
        other.add(10);
        other.add(20);
        other.add(30);
        
        // Remove all elements that are also in 'other'
        set.removeAll(other);
        
        // After removal, the set should no longer contain 10 and 20
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
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());
        
        Set<Integer> other = new TreeSet<Integer>();
        other.add(30);
        other.add(40);
        other.add(50);
        
     // Add all elements from 'other' to the original set
        set.addAll(other);
        
        // The final set should contain the union of both sets
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
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5); 
        set.add(10);
        set.add(15);        
        set.add(20);        
        set.add(25);
        assertEquals(5, set.size());
        
     // Get the iterator and traverse the set
        Iterator<Integer> iter = set.iterator();
        
        // Since TreeSet is based on a search tree, we expect the iterator to return
        // the elements in their natural (sorted) order.
        int[] expectedOrder = {5, 10, 15, 20, 25};
        int index = 0;
        while (iter.hasNext()) {
            int value = iter.next();
            assertEquals(expectedOrder[index++], value);
        }
        assertEquals(expectedOrder.length, index);
    }
}