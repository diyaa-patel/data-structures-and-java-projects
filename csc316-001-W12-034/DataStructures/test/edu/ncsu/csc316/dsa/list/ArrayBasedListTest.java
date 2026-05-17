package edu.ncsu.csc316.dsa.list;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for ArrayBasedList.
 * Checks the expected outputs of the List abstract data type behaviors when using
 * an array-based list data structure
 *
 * @author Dr. King
 * @author Diya Patel
 *
 */
public class ArrayBasedListTest {

	/** list of elements */ 
    private List<String> list;

    /**
     * Create a new instance of an array-based list before each test case executes
     */
    @Before
    public void setUp() {
        list = new ArrayBasedList<String>();
    }

    /**
     * Test the output of the add(index, e) behavior, including expected exceptions
     */
    @Test
    public void testAddIndex() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());

        list.add(0, "one");
        assertEquals(1, list.size());
        assertEquals("one", list.get(0));
        assertFalse(list.isEmpty());
        
        // Use the statements above to help guide your test cases
        // for data structures: Start with an empty data structure, then
        // add an element and check the accessor method return values.
        // Then add another element and check again. Continue to keep checking
        // for special cases. For example, for an array-based list, you should
        // continue adding until you trigger a resize operation to make sure
        // the resize operation worked as expected.
        
        try{
            list.add(15,  "fifteen");
            fail("An IndexOutOfBoundsException should have been thrown");
        } catch (Exception e) {
            assertTrue(e instanceof IndexOutOfBoundsException);
        }
        
    }

    /**
     * Test the output of the addLast behavior
     */
    @Test
    public void testAddLast() {
    	 assertEquals(0, list.size());
         assertTrue(list.isEmpty());

         list.addLast("last");
         assertEquals(1, list.size());
         assertEquals("last", list.get(list.size() - 1));
         assertFalse(list.isEmpty());
    }

    /**
     * Test the output of the last() behavior, including expected exceptions
     */
    @Test
    public void testLast() {
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());

        list.add(list.size(), "last");
        assertEquals(1, list.size());
        assertEquals("last", list.last());
        assertFalse(list.isEmpty());
    }

    /**
     * Test the output of the addFirst behavior
     */
    @Test
    public void testAddFirst() {
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());

        list.addFirst("first");
        assertEquals(1, list.size());
        assertEquals("first", list.get(0));
        assertFalse(list.isEmpty());
    }

    /**
     * Test the output of the first() behavior, including expected exceptions
     */
    @Test
    public void testFirst() {
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());

        list.add(0, "first");
        assertEquals(1, list.size());
        assertEquals("first", list.first());
        assertFalse(list.isEmpty());
    }

    /**
     * Test the iterator behaviors, including expected exceptions
     */
    @Test
    public void testIterator() {
        // Start with an empty list
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        // Create an iterator for the empty list
        Iterator<String> it = list.iterator();
        
        // Try different operations to make sure they work
        // as expected for an empty list (at this point)
        try{
            it.remove();
            fail("An IllegalStateException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof IllegalStateException);
        }
        assertFalse(it.hasNext());

        // Now add an element
        list.addLast("one");
        
        // Use accessor methods to check that the list is correct
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertEquals("one", list.get(0));
        
        // Create an iterator for the list that has 1 element
        it = list.iterator();
        
        // Try different iterator operations to make sure they work
        // as expected for a list that contains 1 element (at this point)
        assertTrue(it.hasNext());
        assertEquals("one", it.next());
        assertFalse(it.hasNext());
        try{
            it.next();
            fail("A NoSuchElementException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }

        
        list.addLast("two");
        
        assertEquals(2, list.size());
        assertEquals("one", list.get(0));
        assertEquals("two", list.get(1));
     
        it = list.iterator();
        
        assertTrue(it.hasNext());
        assertEquals("one", it.next());
        assertTrue(it.hasNext());
        assertEquals("two", it.next());
        assertFalse(it.hasNext());
        
        it = list.iterator();
        assertTrue(it.hasNext());
        assertEquals("one", it.next());
        it.remove(); 
        assertEquals(1, list.size());
        assertEquals("two", list.get(0)); 
        
        try {
            it.remove();
            fail("An IllegalStateException should have been thrown");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalStateException);
        }
        
        assertTrue(it.hasNext());
        assertEquals("two", it.next());
        it.remove(); 
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        assertFalse(it.hasNext());
        
        try {
            it.next();
            fail("A NoSuchElementException should have been thrown");
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
        try {
            it.remove();
            fail("An IllegalStateException should have been thrown");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalStateException);
        }
        
        list.addLast("three");
        it = list.iterator();
        assertTrue(it.hasNext());
        assertEquals("three", it.next());
        assertFalse(it.hasNext());
        
    }

    /**
     * Test the output of the remove(index) behavior, including expected exceptions
     */
    @Test
    public void testRemoveIndex() {
    	 assertEquals(0, list.size());
         assertTrue(list.isEmpty());

         list.add(0, "one");
         assertEquals(1, list.size());
         assertEquals("one", list.get(0));
         list.add(1, "two");
         assertEquals(2, list.size());
         assertEquals("two", list.get(1));
         assertFalse(list.isEmpty());
         
         assertEquals("two", list.remove(1)); 
         assertEquals(1, list.size());
    }

    /**
     * Test the output of the removeFirst() behavior, including expected exceptions
     */
    @Test
    public void testRemoveFirst() {
    	 assertEquals(0, list.size());
         assertTrue(list.isEmpty());

         list.add(0, "one");
         assertEquals(1, list.size());
         assertEquals("one", list.first());
         assertFalse(list.isEmpty());
         
         list.removeFirst(); 
         assertEquals(0, list.size());
         assertTrue(list.isEmpty());
    }

    /**
     * Test the output of the removeLast() behavior, including expected exceptions
     */
    @Test
    public void testRemoveLast() {
    	 assertEquals(0, list.size());
         assertTrue(list.isEmpty());

         list.add(list.size(), "one");
         assertEquals(1, list.size());
         assertEquals("one", list.last());
         assertFalse(list.isEmpty());
         
         assertEquals("one", list.removeLast()); 
         assertEquals(0, list.size());
         assertTrue(list.isEmpty());
    }

    /**
     * Test the output of the set(index, e) behavior, including expected exceptions
     */
    @Test
    public void testSet() {
    	 assertEquals(0, list.size());
         assertTrue(list.isEmpty());

         list.add(0, "one");
         assertEquals(1, list.size());
         assertEquals("one", list.first());
         assertFalse(list.isEmpty());
         
         assertEquals("one", list.set(0, "new")); 
         assertEquals("new", list.first());
    }
}
