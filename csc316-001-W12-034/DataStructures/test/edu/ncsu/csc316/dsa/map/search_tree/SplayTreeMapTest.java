package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for SplayTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a splay tree data structure 
 *
 * @author Dr. King
 * @author Diya Patel
 *
 */
public class SplayTreeMapTest {

	/** tree */
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of a splay tree-based map before each test case executes
     */     
    @Before
    public void setUp() {
        tree = new SplayTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        // You should create test cases to check all the
        // splay scenarios. The textbook has examples
        // that you can use to create your test cases
        
        // You should check the specific keys in each node after adding or
        // removing from the tree. For example, you might use:
        //  assertEquals(4, (int)tree.root().getElement().getKey());
        //  assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());   
        
        tree.put(10, "A");  // First element (becomes root)
        assertEquals(10, (int) tree.root().getElement().getKey());

        tree.put(20, "B");  // Should splay 20 to the root
        assertEquals(20, (int) tree.root().getElement().getKey());
        assertEquals(10, (int) tree.left(tree.root()).getElement().getKey());

        tree.put(30, "C");  // Zig-Zig case: splay 30 to root
        assertEquals(30, (int) tree.root().getElement().getKey());
        assertEquals(20, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(10, (int) tree.left(tree.left(tree.root())).getElement().getKey());

        tree.put(25, "D");  // Zig-Zag case: splay 25 to root
        assertEquals(25, (int) tree.root().getElement().getKey());
        assertEquals(20, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(30, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals(10, (int) tree.left(tree.left(tree.root())).getElement().getKey());
    }
    
    /**
     * Test the output of the get(k) behavior
     */ 
    @Test
    public void testGet() {
    	tree.put(10, "A");
        tree.put(20, "B");
        tree.put(30, "C");
        tree.put(40, "D");

        assertEquals("B", tree.get(20));
        assertEquals(20, (int) tree.root().getElement().getKey());
        assertEquals(10, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(30, (int) tree.right(tree.root()).getElement().getKey());

        assertEquals("A", tree.get(10)); 
        assertEquals(10, (int) tree.root().getElement().getKey());
        assertEquals(20, (int) tree.right(tree.root()).getElement().getKey());

        assertNull(tree.get(50)); // Accessing a non-existent key should return null
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        
        // You should create test cases to check all the
        // splay scenarios. The textbook has examples
        // that you can use to create your test cases
        
        // You should check the specific keys in each node after adding or
        // removing from the tree. For example, you might use:
        //  assertEquals(4, (int)tree.root().getElement().getKey());
        //  assertEquals(2, (int)tree.left(tree.root()).getElement().getKey()); 
    	tree.put(20, "B");
    	assertEquals(20, (int)tree.root().getElement().getKey());
    	
    	tree.put(10, "A");
    	assertEquals(10, (int)tree.root().getElement().getKey());
    	assertEquals(20, (int)tree.right(tree.root()).getElement().getKey()); 
 
        tree.put(40, "D");
        assertEquals(40, (int)tree.root().getElement().getKey());
    	assertEquals(20, (int)tree.left(tree.root()).getElement().getKey());
    	assertEquals(10, (int)tree.left(tree.left(tree.root())).getElement().getKey()); 
    	
        tree.put(30, "C");
        assertEquals(30, (int)tree.root().getElement().getKey());
    	assertEquals(20, (int)tree.left(tree.root()).getElement().getKey());
    	assertEquals(40, (int)tree.right(tree.root()).getElement().getKey());
    	assertEquals(10, (int)tree.left(tree.left(tree.root())).getElement().getKey()); 

        // Remove root (30 is currently root after put operations)
        tree.remove(30);
        assertEquals(40, (int) tree.root().getElement().getKey());
        assertEquals(20, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(10, (int)tree.left(tree.left(tree.root())).getElement().getKey());
        
        // Remove leaf node
        tree.remove(10);
        assertEquals(20, (int) tree.root().getElement().getKey());
        assertEquals(40, (int)tree.right(tree.root()).getElement().getKey());

        // Remove another node
        tree.remove(20);
        assertEquals(40, (int) tree.root().getElement().getKey());
        //assertNull(tree.left(tree.root()));
        //assertNull(tree.right(tree.root()));

        // Remove last node
        tree.remove(40);
        assertTrue(tree.isEmpty());
    }
}