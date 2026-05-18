package edu.ncsu.csc316.dsa.list.positional;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * Test class for PositionalLinkedList.
 * Checks the expected outputs of the Positional List abstract data type behaviors when using
 * an doubly-linked positional list data structure
 *
 * @author Dr. King
 * @author Diya Patel
 *
 */
public class PositionalLinkedListTest {

	/** a list made of String objects */ 
    private PositionalList<String> list;
    
    /**
     * Create a new instance of an positional linked list before each test case executes
     */ 
    @Before
    public void setUp() {
        list = new PositionalLinkedList<String>();
    }
    
    /**
     * Test the output of the first() behavior, including any expected exceptions
     */
    @Test
    public void testFirst() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        assertNull(list.first());
        
        Position<String> first = list.addFirst("one");
        assertEquals(1, list.size());
        assertEquals(first, list.first());
        
        Position<String> second = list.addLast("two");
        assertEquals(2, list.size());
        assertEquals(first, list.first());

        list.remove(first);
        assertEquals(1, list.size());
        assertEquals(second, list.first());
    }
    
    /**
     * Test the output of the last() behavior, including any expected exceptions
     */
    @Test
    public void testLast() {
    	assertEquals(0, list.size());
    	assertTrue(list.isEmpty());

	    assertNull(list.last());

	    Position<String> first = list.addFirst("one");
	    Position<String> second = list.addLast("two");
	    assertEquals(2, list.size());
	    assertEquals(second, list.last());

	    list.remove(second);
	    assertEquals(1, list.size());
	    assertEquals(first, list.last());
    }
    
    /**
     * Test the output of the addFirst(element) behavior
     */ 
    @Test
    public void testAddFirst() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addFirst("one");
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        
        assertEquals("one", first.getElement());

        Position<String> newFirst = list.addFirst("zero");
        assertEquals(2, list.size());
        assertEquals("zero", list.first().getElement());
        assertEquals("one", list.after(newFirst).getElement());
        
    }
    
    /**
     * Test the output of the addLast(element) behavior
     */ 
    @Test
    public void testAddLast() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        list.addLast("one");
        assertEquals(1, list.size());
        assertEquals("one", list.first().getElement());

        list.addLast("two");
        assertEquals(2, list.size());
        assertEquals("two", list.last().getElement());

    }
    
    /**
     * Test the output of the before(position) behavior, including any expected exceptions
     */ 
    @Test
    public void testBefore() {
    	 Position<String> first = list.addFirst("one");
    	 Position<String> second = list.addLast("two");

    	 assertEquals(first, list.before(second));
    }
    
    /**
     * Test the output of the after(position) behavior, including any expected exceptions
     */     
    @Test
    public void testAfter() {
    	Position<String> first = list.addFirst("one");
        Position<String> second = list.addLast("two");

        assertEquals(second, list.after(first));
    }
    
    /**
     * Test the output of the addBefore(position, element) behavior, including any expected exceptions
     */     
    @Test
    public void testAddBefore() {
    	list.addFirst("one");
        list.addLast("two");

        Position<String> newFirst = list.addBefore(list.first(), "zero");
        assertEquals("zero", newFirst.getElement());
        assertEquals(newFirst, list.first());
    }
    
    /**
     * Test the output of the addAfter(position, element) behavior, including any expected exceptions
     */     
    @Test
    public void testAddAfter() {
    	Position<String> first = list.addFirst("one");
        Position<String> second = list.addLast("two");

        Position<String> newSecond = list.addAfter(first, "one-and-a-half");
        assertEquals("one-and-a-half", newSecond.getElement());
        assertEquals(newSecond, list.before(second));
    }
    
    /**
     * Test the output of the set(position, element) behavior, including any expected exceptions
     */     
    @Test
    public void testSet() {
    	 Position<String> first = list.addFirst("one");
    	 assertEquals("one", first.getElement());

    	 list.set(first, "oneNew");
    	 assertEquals("oneNew", first.getElement());
    }
    
    /**
     * Test the output of the remove(position) behavior, including any expected exceptions
     */     
    @Test
    public void testRemove() {
    	 Position<String> first = list.addFirst("one");
    	 Position<String> second = list.addLast("two");

    	 assertEquals("one", list.remove(first));
    	 assertEquals(1, list.size());
    	 assertEquals(second, list.first());
    }
    
    /**
     * Test the output of the iterator behavior for elements in the list, 
     * including any expected exceptions
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
        assertEquals("one", list.first().getElement());
        
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
        assertEquals("one", list.first().getElement());
        assertEquals("two", list.last().getElement());
     
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
        assertEquals("two", list.first().getElement()); 
        
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
     * Test the output of the positions() behavior to iterate through positions
     * in the list, including any expected exceptions
     */     
    @Test
    public void testPositions() {
        assertEquals(0, list.size());
        Position<String> first = list.addFirst("one");
        Position<String> second = list.addLast("two");
        Position<String> third = list.addLast("three");
        assertEquals(3, list.size());
        
        Iterator<Position<String>> it = list.positions().iterator();
        assertTrue(it.hasNext());
        assertEquals(first, it.next());
        assertEquals(second, it.next());
        assertEquals(third, it.next());
        assertFalse(it.hasNext()); 
    }

}