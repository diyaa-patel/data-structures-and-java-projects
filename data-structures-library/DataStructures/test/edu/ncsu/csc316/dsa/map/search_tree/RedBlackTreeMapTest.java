package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for RedBlackTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a red-black tree data structure 
 *
 * @author Dr. King
 * @author Diya Patel
 *
 */
public class RedBlackTreeMapTest {

	/** tree */
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of a red-black tree-based map before each test case executes
     */  
    @Before
    public void setUp() {
        tree = new RedBlackTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());

        // You should create test cases to check all the
        // rules for red-black trees. The textbook has examples
        // that you can use to create your test cases

        // You should check the specific keys in each node after adding or
        // removing from the tree. For example, you might use:
        //  assertEquals(4, (int)tree.root().getElement().getKey());
        //  assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());    
        
        tree.put(10, "A");  // First element (root should be black)
        assertEquals(10, (int) tree.root().getElement().getKey());
        //assertTrue(tree.isBlack(tree.root()));

        tree.put(20, "B");  // No rotations required, just insert as red
        assertEquals(20, (int) tree.right(tree.root()).getElement().getKey());
        //assertTrue(tree.isRed(tree.right(tree.root())));

        tree.put(30, "C");  // Causes rotation: 20 should become root
        assertEquals(20, (int) tree.root().getElement().getKey());
        assertEquals(10, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(30, (int) tree.right(tree.root()).getElement().getKey());
        //assertTrue(tree.isBlack(tree.root())); // Root should always be black

        tree.put(15, "D");  // Should cause recoloring
        assertEquals(15, (int) tree.right(tree.left(tree.root())).getElement().getKey());
        //assertTrue(tree.isRed(tree.right(tree.left(tree.root()))));

        tree.put(25, "E");  // Should maintain balance
        assertEquals(25, (int) tree.left(tree.right(tree.root())).getElement().getKey());
        //assertTrue(tree.isRed(tree.left(tree.right(tree.root()))));
    }
    
    /**
     * another test case for remove 
     */
    @Test
    public void testRemove() {
    	tree.put(50, "A");
    	assertEquals(50, (int) tree.root().getElement().getKey());
    	
    	
        tree.put(30, "B");
        assertEquals(50, (int) tree.root().getElement().getKey());
        assertEquals(30, (int) tree.left(tree.root()).getElement().getKey());
        
        tree.put(70, "C");
        assertEquals(50, (int) tree.root().getElement().getKey());
        assertEquals(30, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(70, (int) tree.right(tree.root()).getElement().getKey());
        
        tree.put(20, "D");
        assertEquals(20, (int) tree.left(tree.left(tree.root())).getElement().getKey());
        
        tree.put(40, "E");
        assertEquals(40, (int) tree.right(tree.left(tree.root())).getElement().getKey());
        
        tree.put(60, "F");
        assertEquals(60, (int) tree.left(tree.right(tree.root())).getElement().getKey());
        
        tree.put(80, "G");
        assertEquals(80, (int) tree.right(tree.right(tree.root())).getElement().getKey());
        
        // Removing a black node with a red child (should replace with red child)
        tree.remove(30);
        assertEquals(40, (int) tree.left(tree.root()).getElement().getKey());
        
        tree.remove(40); 
        assertEquals(20, (int) tree.left(tree.root()).getElement().getKey());
      
        tree.put(90, "H");
        assertEquals(80, (int) tree.right(tree.right(tree.root())).getElement().getKey());
        
        tree.remove(80);
        assertEquals(70, (int) tree.right(tree.root()).getElement().getKey());
        
        tree.remove(20); 
        assertEquals(70, (int) tree.root().getElement().getKey());
        assertEquals(50, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(90, (int) tree.right(tree.root()).getElement().getKey());
      
    } 
    
    /** 
     * additional test cases for remove 
     */
    @Test 
    public void testRemove2() {
    	tree.put(1, "one"); 
    	tree.put(2, "two");
    	tree.put(3, "three");
    	tree.put(4, "four");
    	tree.put(5, "five");
    	tree.put(6, "six");
    	assertEquals(6, tree.size()); 
    	
    	//removing leaf 
    	tree.remove(6);
    	assertNull(tree.get(6));
    	assertEquals(2, (int) tree.root().getElement().getKey());
        assertEquals(1, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(4, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals(5, tree.size()); 
        
        //removing node with singular child 
        tree.remove(1);
        assertNull(tree.get(1));
    	assertEquals(4, (int) tree.root().getElement().getKey());
        assertEquals(2, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(5, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals(4, tree.size()); 
        
        //test for double black case 2 
        tree.remove(3); 
        assertNull(tree.get(3)); 
    	assertEquals(4, (int) tree.root().getElement().getKey());
        assertEquals(2, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(5, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals(3, tree.size()); 
        
        tree.remove(4); 
        tree.remove(2); 
        tree.remove(5);
        assertTrue(tree.isEmpty()); 
        
        //test for case 1
        tree.put(3, "three");
        tree.put(2, "two");
        tree.put(5, "five");
    	tree.put(4, "four");
    	tree.put(6, "six"); 
        
    	tree.remove(2); 
        assertNull(tree.get(2)); 
    	assertEquals(4, (int) tree.root().getElement().getKey());
        assertEquals(3, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(5, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals(6, (int) tree.right(tree.right(tree.root())).getElement().getKey()); 
        assertEquals(4, tree.size()); 
    	
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

         assertEquals("A", tree.get(10));
         assertEquals("B", tree.get(20));
         assertEquals("C", tree.get(30));
         assertEquals("D", tree.get(40));

         assertNull(tree.get(50));
    }
    
    /**
     * Test for the comparator 
     */
    @Test 
    public void testComparator() {
    	Comparator<Integer> order = (a, b) -> b.compareTo(a);
    	RedBlackTreeMap<Integer, String> compTree = new RedBlackTreeMap<>(order); 
    	
    	compTree.put(1, "one"); 
    	compTree.put(2, "two"); 
    	compTree.put(3, "three"); 
    	
    	assertEquals("one", compTree.get(1));
    	assertEquals("three", compTree.get(3));
    	assertEquals("two", compTree.get(2));
    	
    }
   
}