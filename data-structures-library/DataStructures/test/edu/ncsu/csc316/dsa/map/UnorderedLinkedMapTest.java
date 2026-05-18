package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * Test class for UnorderedLinkedMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * an unordered link-based list data structure that uses the move-to-front heuristic for
 * self-organizing entries based on access frequency
 *
 * @author Dr. King
 * @author Diya Patel
 *
 */
public class UnorderedLinkedMapTest {

	/** map instance */ 
    private Map<Integer, String> map;
    
    /**
     * Create a new instance of an unordered link-based map before each test case executes
     */     
    @Before
    public void setUp() {
        map = new UnorderedLinkedMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertEquals("UnorderedLinkedMap[3]", map.toString());
        assertEquals(1, map.size());

        //adding additional entries 
        assertNull(map.put(1, "string1"));
        assertNull(map.put(2, "string2"));
        assertEquals("UnorderedLinkedMap[2, 1, 3]", map.toString());
        
        //updating the key 
        assertEquals("string1", map.put(1, "newString1")); 
        assertEquals("UnorderedLinkedMap[1, 2, 3]", map.toString()); 
    }

    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        assertFalse(map.isEmpty());
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
        
        assertEquals("string1", map.get(1));
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
        

        // Accessing 3 should move it to the front
        assertEquals("string3", map.get(3));
        assertEquals("UnorderedLinkedMap[3, 1, 4, 2, 5]", map.toString());

        // Accessing 2 should move it to the front
        assertEquals("string2", map.get(2));
        assertEquals("UnorderedLinkedMap[2, 3, 1, 4, 5]", map.toString());

        // Accessing a non-existent key
        assertNull(map.get(10)); 
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        assertFalse(map.isEmpty());
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
        
        // Removing an existing key
        assertEquals("string2", map.remove(2));
        assertEquals("UnorderedLinkedMap[1, 4, 5, 3]", map.toString());

        // Removing the front element
        assertEquals("string1", map.remove(1));
        assertEquals("UnorderedLinkedMap[4, 5, 3]", map.toString());

        // Removing a non-existent key
        assertNull(map.remove(10));

        // Removing last element
        assertEquals("string3", map.remove(3));
        assertEquals("UnorderedLinkedMap[4, 5]", map.toString());
    }

    /**
     * Test the output of the iterator behavior, including expected exceptions
     */     
    @Test
    public void testIterator() {
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));

        Iterator<Entry<Integer, String>> it = map.entrySet().iterator();
        assertTrue(it.hasNext());
        assertEquals(Integer.valueOf(1), it.next().getKey());
        assertEquals(Integer.valueOf(4), it.next().getKey());
        assertEquals(Integer.valueOf(2), it.next().getKey());
        assertEquals(Integer.valueOf(5), it.next().getKey());
        assertEquals(Integer.valueOf(3), it.next().getKey());
        assertFalse(it.hasNext());
    }

    /**
     * Test the output of the entrySet() behavior, including expected exceptions
     */     
    @Test
    public void testEntrySet() {
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        
        Iterable<Entry<Integer, String>> entries = map.entrySet();
        Iterator<Entry<Integer, String>> it = entries.iterator();

        assertEquals(Integer.valueOf(1), it.next().getKey());
        assertEquals(Integer.valueOf(4), it.next().getKey());
        assertEquals(Integer.valueOf(2), it.next().getKey());
        assertEquals(Integer.valueOf(5), it.next().getKey());
        assertEquals(Integer.valueOf(3), it.next().getKey());
    }

    /**
     * Test the output of the values() behavior, including expected exceptions
     */     
    @Test
    public void testValues() {
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        
        assertEquals("string1", map.get(1));
        assertEquals("string2", map.get(2));
        assertEquals("string3", map.get(3));
        assertEquals("string4", map.get(4));
        assertEquals("string5", map.get(5));
    }
}