package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * Test class for BinarySearchTreeMap
 * Checks the expected outputs of the Map and Tree abstract data type behaviors when using
 * an linked binary tree data structure 
 *
 * @author Dr. King
 * @author Diya Patel
 *
 */
public class BinarySearchTreeMapTest {

	/** a binary search tree */ 
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of a binary search tree map before each test case executes
     */
    @Before
    public void setUp() {
        tree = new BinarySearchTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        tree.put(1, "one");
        assertEquals(1, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(1, (int)tree.root().getElement().getKey());
        
        tree.put(2, "two");
        assertEquals(2, tree.size());
        assertEquals("two", tree.get(2));

        tree.put(0, "zero");
        assertEquals(3, tree.size());
        assertEquals("zero", tree.get(0));
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        tree.put(1,  "one");
        assertEquals(1, tree.size());
        
        tree.put(2, "two");
        tree.put(3, "three");
        tree.put(0, "zero");
        
        assertEquals("one", tree.get(1));
        assertEquals("two", tree.get(2));
        assertEquals("three", tree.get(3));
        assertEquals("zero", tree.get(0));
        assertNull(tree.get(10)); // Should return null for non-existent key
    }

    /**
     * Test the output of the remove(k) behavior
     */ 
    @Test
    public void testRemove() {
        tree.put(1,  "one");
        assertEquals(1, tree.size());
        
        assertNull(tree.remove(10));
        assertEquals(1, tree.size());
        
        assertEquals("one", tree.remove(1));
        assertEquals(0, tree.size());
        
        tree.put(5, "five");
        tree.put(3, "three");
        tree.put(7, "seven");
        tree.put(2, "two");
        tree.put(4, "four");
        tree.put(6, "six");
        tree.put(8, "eight");
        
        // Removing a node with no children
        assertEquals("two", tree.remove(2));
        assertNull(tree.get(2));
        
        // Removing a node with one child (left)
        assertEquals("three", tree.remove(3));
        assertNull(tree.get(3));
        
        // Removing a node with one child (right)
        assertEquals("seven", tree.remove(7));
        assertNull(tree.get(7));
        
        // Removing a node with two children
        assertEquals("five", tree.remove(5));
        assertNull(tree.get(5));
    }
    
    /**
     * Test the output of the entrySet() behavior
     */
    @Test
    public void testEntrySet() {
        tree.put(10, "ten");
        tree.put(5, "five");
        tree.put(15, "fifteen");
        tree.put(2, "two");
        tree.put(7, "seven");

        Iterator<Entry<Integer, String>> iter = tree.entrySet().iterator();
        
        // Check if all inserted elements are in the entry set
        assertTrue(iter.hasNext());
        assertEquals((Integer) 2, iter.next().getKey());
        assertEquals((Integer) 5, iter.next().getKey());
        assertEquals((Integer) 7, iter.next().getKey());
        assertEquals((Integer) 10, iter.next().getKey());
        assertEquals((Integer) 15, iter.next().getKey());

        // Ensure there are no more elements left in the iterator
        assertFalse(iter.hasNext());
    }

    /**
     * Test the output of the toString() method
     */
    @Test
    public void testToString() {
       //empty tree 
        assertEquals("BalanceableBinaryTree[\n" + "null\n" + "]", tree.toString());
    }
}