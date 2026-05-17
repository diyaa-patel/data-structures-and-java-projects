package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.data.Student;

/**
 * Test class for SearchTableMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a sorted array-based data structure that uses binary search to locate entries
 * based on the key of the entry
 *
 * @author Dr. King
 * @author Diya Patel
 *
 */
public class SearchTableMapTest {

	/** map of integers and strings*/ 
    private Map<Integer, String> map;
    /** map of student and id */ 
    private Map<Student, Integer> studentMap;
    
    /**
     * Create a new instance of a search table map before each test case executes
     */     
    @Before
    public void setUp() {
        map = new SearchTableMap<Integer, String>();
        studentMap = new SearchTableMap<Student, Integer>();
    }

    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertEquals("SearchTableMap[3]", map.toString());
        assertEquals(1, map.size());
        
        assertNull(map.put(5, "string5"));
        assertNull(map.put(1, "string1"));
        assertEquals("SearchTableMap[1, 3, 5]", map.toString());
        
        // putting in already inserted value 
        assertEquals("string3", map.put(3, "NewString3"));
        assertEquals("NewString3", map.get(3)); 
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
        assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
        
        assertEquals("string1", map.get(1));
        assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
        
        assertEquals("string3", map.get(3));
        assertEquals("string5", map.get(5));
        
        // non-existent keys return null 
        assertNull(map.get(100));
        assertNull(map.get(-1));
        
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
        assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
        
        assertEquals("string3", map.remove(3));
        assertEquals("SearchTableMap[1, 2, 4, 5]", map.toString());
        assertNull(map.remove(100)); 
    }
    
    /**
     * Tests Map abstract data type behaviors to ensure the behaviors work
     * as expected when using arbitrary objects as keys
     */
    @Test
    public void testStudentMap() {
        Student s1 = new Student("J", "K", 1, 0, 0, "jk");
        Student s2 = new Student("J", "S", 2, 0, 0, "js");
        Student s3 = new Student("S", "H", 3, 0, 0, "sh");
        Student s4 = new Student("J", "J", 4, 0, 0, "jj");
        Student s5 = new Student("L", "B", 5, 0, 0, "lb");
        
        // Suggestions: since search table map keys are Comparable,
        // make sure the search table works with Comparable objects like Students
        
        assertNull(studentMap.put(s1, 10));
        assertNull(studentMap.put(s2, 20));
        assertNull(studentMap.put(s3, 30));
        assertNull(studentMap.put(s4, 40));
        assertNull(studentMap.put(s5, 50));

        assertEquals(Integer.valueOf(30), studentMap.get(s3));
        assertEquals(Integer.valueOf(20), studentMap.get(s2));

        assertEquals(Integer.valueOf(30), studentMap.remove(s3));
        assertNull(studentMap.get(s3));
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
        
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
        assertTrue(it.hasNext());
        assertEquals(Integer.valueOf(1), it.next().getKey());
        assertEquals(Integer.valueOf(2), it.next().getKey());
        assertEquals(Integer.valueOf(3), it.next().getKey());
        assertEquals(Integer.valueOf(4), it.next().getKey());
        assertEquals(Integer.valueOf(5), it.next().getKey());
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
        
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
        assertTrue(it.hasNext());
        Map.Entry<Integer, String> entry = it.next();
        assertEquals(1, (int)entry.getKey());
        assertEquals("string1", (String)entry.getValue());

        entry = it.next();
        assertEquals(2, (int)entry.getKey());
        assertEquals("string2", entry.getValue());
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
        
        Iterator<String> it = map.values().iterator();
        assertTrue(it.hasNext());
        
        assertEquals("string1", it.next());
        assertEquals("string2", it.next());
        assertEquals("string3", it.next());
        assertEquals("string4", it.next());
        assertEquals("string5", it.next());
        assertFalse(it.hasNext());
    }
    
    /**
     * test the output of the keys() behavior, including expected exceptions() 
     */
    @Test 
    public void testKeys() {
    	
    	 assertNull(map.put(3, "string3"));
         assertNull(map.put(5, "string5"));
         assertNull(map.put(2, "string2"));
         assertNull(map.put(4, "string4"));
         assertNull(map.put(1, "string1"));
         
         Iterator<Integer> it = map.iterator();
         assertTrue(it.hasNext());
         
         assertEquals(Integer.valueOf(1), it.next());
         assertEquals(Integer.valueOf(2), it.next());
         assertEquals(Integer.valueOf(3), it.next());
         assertEquals(Integer.valueOf(4), it.next());
         assertEquals(Integer.valueOf(5), it.next());
         assertFalse(it.hasNext());
    	
    }
}
