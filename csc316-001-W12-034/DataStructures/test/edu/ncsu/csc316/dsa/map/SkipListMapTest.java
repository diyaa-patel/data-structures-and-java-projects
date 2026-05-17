package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.data.Student;

/**
 * Test class for SkipListMap
 * Ensures correct behavior of the SkipListMap data structure
 * 
 * @author Dr. King
 * @author Diya Patel
 *
 */
public class SkipListMapTest {

    /** Map for Integer to String */
    private Map<Integer, String> map;
    /** Map for Student to Integer */
    private Map<Student, Integer> studentMap;
    
    /**
     * Create a new instance of a SkipListMap before each test case executes
     */     
    @Before
    public void setUp() {
        map = new SkipListMap<Integer, String>();
        studentMap = new SkipListMap<Student, Integer>();
    }

    /**
     * Test the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());

        assertNull(map.put(3, "string3"));
        assertEquals("SkipListMap[3]", map.toString());
        assertEquals(1, map.size());

        assertNull(map.put(5, "string5"));
        assertNull(map.put(1, "string1"));
        assertEquals("SkipListMap[1, 3, 5]", map.toString());

        // Overwriting an existing key
        assertEquals("string3", map.put(3, "NewString3"));
        assertEquals("NewString3", map.get(3)); 
    }

    /**
     * Test the get(k) behavior
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
        assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());

        assertEquals("string1", map.get(1));
        assertEquals("string3", map.get(3));
        assertEquals("string5", map.get(5));

        // Key not present should return null
        assertNull(map.get(100));
        assertNull(map.get(-1));
    }

    /**
     * Test the remove(k) behavior
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
        assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());

        assertEquals("string3", map.remove(3));
        assertEquals("SkipListMap[1, 2, 4, 5]", map.toString());

        // Removing a key that doesn't exist should return null
        //assertNull(map.remove(100));
    }
    
    /**
     * Tests Map abstract data type behaviors when using custom objects as keys
     */
    @Test
    public void testStudentMap() {
        Student s1 = new Student("J", "K", 1, 0, 0, "jk");
        Student s2 = new Student("J", "S", 2, 0, 0, "js");
        Student s3 = new Student("S", "H", 3, 0, 0, "sh");
        Student s4 = new Student("J", "J", 4, 0, 0, "jj");
        Student s5 = new Student("L", "B", 5, 0, 0, "lb");

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
     * Test iterator behavior
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
     * Test entrySet() behavior
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
        assertEquals("string1", entry.getValue());

        entry = it.next();
        assertEquals(2, (int)entry.getKey());
        assertEquals("string2", entry.getValue());
    }

    /**
     * Test values() behavior
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
}
