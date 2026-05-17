package edu.ncsu.csc316.dsa.map.hashing;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Test class for LinearProbingHashMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a linear probing hash map data structure 
 *
 * @author Dr. King
 * @author Diya Patel
 *
 */
public class LinearProbingHashMapTest {

    // 'Testing' Map used (no randomization) to check placement of entries in the hash table
	/** test map */ 
    private Map<Integer, String> testMap;
    
    // 'Production' Map (with randomization) to check correctness of ADT behaviors
    /** production map */ 
    private Map<Integer, String> prodMap;

    /**
     * Create a new instance of a linear probing hash map before each test case executes
     */     
    @Before
    public void setUp() {
        // Use the "true" flag to indicate we are testing.
        // Remember that (when testing) alpha = 1, beta = 1, and prime = 7
        // based on our AbstractHashMap constructor.
        // That means you can draw the hash table by hand
        // if you use integer keys, since Integer.hashCode() = the integer value, itself
        // Finally, apply compression. For example:
        // for key = 1: h(1) = ( (1 * 1 + 1) % 7) % 7 = 2
        // for key = 2: h(2) = ( (1 * 2 + 1) % 7) % 7 = 3
        // for key = 3: h(3) = ( (1 * 3 + 1) % 7) % 7 = 4
        // for key = 4: h(4) = ( (1 * 4 + 1) % 7) % 7 = 5
        // for key = 5: h(5) = ( (1 * 5 + 1) % 7) % 7 = 6
        // for key = 6: h(6) = ( (1 * 6 + 1) % 7) % 7 = 0
        // etc.
        testMap = new LinearProbingHashMap<Integer, String>(7, true);
        prodMap = new LinearProbingHashMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, testMap.size());
        assertTrue(testMap.isEmpty());
       
        // You should create some collisions to check that entries
        // are placed in the correct buckets.
        //
        // You should also use the prodMap to check that put works
        // as expected when randomization is used internally. You can't
        // check the placement of entries within the hash table,
        // but you can still check that put gives the correct outputs when
        // randomization is used internally.
        
        assertNull(testMap.put(3, "string3"));
        assertNull(testMap.put(4, "string4"));
        assertNull(testMap.put(10, "string10")); // Collision with index 4
        assertNull(testMap.put(17, "string17")); // Collision with index 4
        
        assertEquals(4, testMap.size());
        assertFalse(testMap.isEmpty());
        
        assertEquals("string3", testMap.get(3));
        assertEquals("string4", testMap.get(4));
        assertEquals("string10", testMap.get(10));
        assertEquals("string17", testMap.get(17));
        
        assertNull(prodMap.put(100, "A"));
        assertNull(prodMap.put(200, "B"));
        assertNull(prodMap.put(300, "C"));
        assertEquals(3, prodMap.size());
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        assertTrue(testMap.isEmpty());

        //
        // You should also use the prodMap to check that get works
        // as expected when randomization is used internally.
        
        testMap.put(3, "string3");
        testMap.put(10, "string10");
        testMap.put(17, "string17");

        assertEquals("string3", testMap.get(3));
        assertEquals("string10", testMap.get(10));
        assertEquals("string17", testMap.get(17));
        assertNull(testMap.get(4)); // Non-existent key
        
        prodMap.put(500, "X");
        prodMap.put(600, "Y");
        assertEquals("X", prodMap.get(500));
        assertEquals("Y", prodMap.get(600));
        assertNull(prodMap.get(700));
    }
    
    /**
     * Test the output of the remove(k) behavior
     */ 
    @Test
    public void testRemove() {
        assertTrue(testMap.isEmpty());
        //
        // You should also use the prodMap to check that remove works
        // as expected when randomization is used internally. You can't
        // check the placement of entries within the hash table,
        // but you can still check that remove gives the correct outputs when
        // randomization is used internally.  
        testMap.put(3, "string3");
        testMap.put(4, "string4");
        testMap.put(10, "string10"); // Will collide with index 4
        testMap.put(17, "string17"); // Will collide with index 4 and 10

        // Ensure the entries exist before removal
        assertEquals(4, testMap.size());
        assertEquals("string3", testMap.get(3));
        assertEquals("string4", testMap.get(4));
        assertEquals("string10", testMap.get(10));
        assertEquals("string17", testMap.get(17));

        // Remove an entry 
        assertEquals("string4", testMap.remove(4));
        assertNull(testMap.get(4));
        assertEquals(3, testMap.size());

        // Remove an entry with collision and verify probing still works
        assertEquals("string10", testMap.remove(10));
        assertNull(testMap.get(10));
        assertEquals(2, testMap.size());

        // Remove a non-existent key
        assertNull(testMap.remove(100));
        assertEquals(2, testMap.size()); 

        // Remove remaining entries
        assertEquals("string3", testMap.remove(3));
        assertEquals("string17", testMap.remove(17));

        // The map should now be empty
        assertTrue(testMap.isEmpty());
        
        prodMap.put(500, "X");
        prodMap.put(600, "Y");
        assertEquals("X", prodMap.remove(500));
        assertNull(prodMap.get(500));
        assertEquals(1, prodMap.size());
    }
    
    /**
     * Test the output of the iterator() behavior, including expected exceptions
     */     
    @Test
    public void testIterator() {
    	 testMap.put(1, "one");
         testMap.put(2, "two");
         testMap.put(3, "three");
         
         Iterator<Integer> it = testMap.iterator();
         assertTrue(it.hasNext());
         assertEquals(1, (int)it.next());
         assertEquals(2, (int)it.next());
         assertEquals(3, (int)it.next());
         assertFalse(it.hasNext());

    }
    
    /**
     * Test the output of the entrySet() behavior
     */     
    @Test
    public void testEntrySet() {
    	 testMap.put(5, "five");
         testMap.put(6, "six");
         
         Iterator<Map.Entry<Integer, String>> it = testMap.entrySet().iterator();
         assertEquals(6, (int)it.next().getKey());
         assertEquals(5, (int)it.next().getKey());
        
    }
    
    /**
     * Test the output of the values() behavior
     */  
    @Test
    public void testValues() {
    	 testMap.put(7, "seven");
         testMap.put(8, "eight");
         
         Iterator<String> it = testMap.values().iterator();
         assertEquals("seven", it.next());
         assertEquals("eight", it.next());
         

    }
}