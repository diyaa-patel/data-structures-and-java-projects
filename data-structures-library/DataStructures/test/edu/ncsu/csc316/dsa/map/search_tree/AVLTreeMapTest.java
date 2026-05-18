package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for AVLTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * an AVL tree data structure 
 *
 * @author Dr. King
 * @author Diya Patel
 *
 */
public class AVLTreeMapTest {

	/** tree */ 
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of an AVL tree-based map before each test case executes
     */     
    @Before
    public void setUp() {
        tree = new AVLTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        // You should create test cases to check all the
        // trinode restructuring scenarios. The textbook has visual examples
        // that you can use to create your test cases
        
        // You should check the specific keys in each node after adding or
        // removing from the tree. For example, you might use:
        //  assertEquals(4, (int)tree.root().getElement().getKey());
        //  assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());
        
        tree.put(2, "two");
        assertEquals(2, (int)tree.root().getElement().getKey());
        
        tree.put(5, "five");
        assertEquals(2, (int)tree.root().getElement().getKey());
        assertEquals(5, (int)tree.right(tree.root()).getElement().getKey());
        
        tree.put(3, "three");
        assertEquals(3, (int)tree.root().getElement().getKey());
        assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(5, (int)tree.right(tree.root()).getElement().getKey());
        
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        assertTrue(tree.isEmpty());
        tree.put(5, "five");
        tree.put(3, "three");
        tree.put(7, "seven");
        
        assertEquals("five", tree.get(5));
        assertEquals("three", tree.get(3));
        assertEquals("seven", tree.get(7));
        assertNull(tree.get(10)); // Key does not exist
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        assertTrue(tree.isEmpty());
       
        // You should create test cases to check all the
        // trinode restructuring scenarios. The textbook has visual examples
        // that you can use to create your test cases
        
        // You should check the specific keys in each node after adding or
        // removing from the tree. For example, you might use:
        //  assertEquals(4, (int)tree.root().getElement().getKey());
        //  assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());
        
        tree.put(5, "five");
        tree.put(3, "three");
        tree.put(7, "seven");
        tree.put(2, "two");
        tree.put(4, "four");
        tree.put(6, "six");
        tree.put(8, "eight");
        
        // Removing a leaf node
        assertEquals("two", tree.remove(2));
        assertNull(tree.get(2));
        
        // Removing a node with one child
        assertEquals("three", tree.remove(3));
        assertNull(tree.get(3));
        
        // Removing a node with two children (triggers restructuring)
        assertEquals("five", tree.remove(5));
        assertNull(tree.get(5));
        assertEquals(6, (int)tree.root().getElement().getKey());
    }
}