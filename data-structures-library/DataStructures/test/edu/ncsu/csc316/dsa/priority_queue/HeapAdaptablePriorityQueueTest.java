package edu.ncsu.csc316.dsa.priority_queue;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;
import edu.ncsu.csc316.dsa.priority_queue.PriorityQueue.Entry;

/**
 * Test class for HeapAdaptablePriorityQueue
 * Checks the expected outputs of the Adaptable Priorty Queue abstract
 * data type behaviors when using a min-heap data structure 
 *
 * @author Dr. King
 * @author Diya Patel
 *
 */
public class HeapAdaptablePriorityQueueTest {

	/** heap queue */ 
    private HeapAdaptablePriorityQueue<Integer, String> heap;
    
    /**
     * Create a new instance of a heap before each test case executes
     */   
    @Before
    public void setUp() {
        heap = new HeapAdaptablePriorityQueue<Integer, String>();
    }
    
    /**
     * Test the output of the replaceKey behavior
     */     
    @Test
    public void testReplaceKey() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        
        Entry<Integer, String> e8 = heap.insert(8, "eight");
        Entry<Integer, String> e7 = heap.insert(7, "seven");
        Entry<Integer, String> e6 = heap.insert(6, "six");
        Entry<Integer, String> e5 = heap.insert(5, "five");
        Entry<Integer, String> e4 = heap.insert(4, "four");
        Entry<Integer, String> e3 = heap.insert(3, "three");
        Entry<Integer, String> e2 = heap.insert(2, "two");
        Entry<Integer, String> e1 = heap.insert(1, "one");
        Entry<Integer, String> e0 = heap.insert(0, "zero");
        assertEquals(9, heap.size());
        
        heap.replaceKey(e8,  -5);
        assertEquals(-5, (int)heap.min().getKey());
        assertEquals("eight", heap.min().getValue());
        assertEquals(9, heap.size());
        
        heap.replaceKey(e0, 10);
        assertEquals(-5, (int) heap.min().getKey());
        
        heap.replaceKey(e3, -6);
        assertEquals(-6, (int) heap.min().getKey());

        heap.replaceKey(e5, 12);
        assertEquals(-6, (int) heap.min().getKey());
        
        heap.replaceKey(e7, -7);
        assertEquals(-7, (int) heap.min().getKey());

        heap.replaceKey(e6, 20);
        assertEquals(-7, (int) heap.min().getKey());

        heap.replaceKey(e4, -10);
        assertEquals(-10, (int) heap.min().getKey());

        heap.replaceKey(e2, 15);
        assertEquals(-10, (int) heap.min().getKey());

        heap.replaceKey(e1, 30);
        assertEquals(-10, (int) heap.min().getKey());
    }
    
    /**
     * Test the output of the replaceValue behavior
     */ 
    @Test
    public void testReplaceValue() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        
        Entry<Integer, String> e8 = heap.insert(8, "eight");
        Entry<Integer, String> e7 = heap.insert(7, "seven");
        Entry<Integer, String> e6 = heap.insert(6, "six");
        Entry<Integer, String> e5 = heap.insert(5, "five");
        Entry<Integer, String> e4 = heap.insert(4, "four");
        Entry<Integer, String> e3 = heap.insert(3, "three");
        Entry<Integer, String> e2 = heap.insert(2, "two");
        Entry<Integer, String> e1 = heap.insert(1, "one");
        Entry<Integer, String> e0 = heap.insert(0, "zero");
        assertEquals(9, heap.size());
        
        heap.replaceValue(e0, "ZERO_UPDATED");
        heap.replaceValue(e1, "ONE_UPDATED");
        heap.replaceValue(e2, "TWO_UPDATED");
        heap.replaceValue(e3, "THREE_UPDATED");
        heap.replaceValue(e4, "FOUR_UPDATED");
        heap.replaceValue(e5, "FIVE_UPDATED");
        heap.replaceValue(e6, "SIX_UPDATED");
        heap.replaceValue(e7, "SEVEN_UPDATED");
        heap.replaceValue(e8, "EIGHT_UPDATED");

        assertEquals("ZERO_UPDATED", e0.getValue());
        assertEquals("ONE_UPDATED", e1.getValue());
        assertEquals("TWO_UPDATED", e2.getValue());
        assertEquals("THREE_UPDATED", e3.getValue());
        assertEquals("FOUR_UPDATED", e4.getValue());
        assertEquals("FIVE_UPDATED", e5.getValue());
        assertEquals("SIX_UPDATED", e6.getValue());
        assertEquals("SEVEN_UPDATED", e7.getValue());
        assertEquals("EIGHT_UPDATED", e8.getValue());
    }

    /**
     * Test the output of the remove behavior
     */ 
    @Test
    public void testRemove() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        
        Entry<Integer, String> e8 = heap.insert(8, "eight");
        Entry<Integer, String> e7 = heap.insert(7, "seven");
        Entry<Integer, String> e6 = heap.insert(6, "six");
        Entry<Integer, String> e5 = heap.insert(5, "five");
        Entry<Integer, String> e4 = heap.insert(4, "four");
        Entry<Integer, String> e3 = heap.insert(3, "three");
        Entry<Integer, String> e2 = heap.insert(2, "two");
        Entry<Integer, String> e1 = heap.insert(1, "one");
        Entry<Integer, String> e0 = heap.insert(0, "zero");
        assertEquals(9, heap.size());
        
        assertEquals(0, (int) heap.min().getKey());
        assertEquals("zero", heap.min().getValue());

        heap.remove(e0);
        assertEquals(8, heap.size());
        assertEquals(1, (int) heap.min().getKey());
        assertEquals("one", heap.min().getValue());

        heap.remove(e1);
        assertEquals(7, heap.size());
        assertEquals(2, (int) heap.min().getKey());
        assertEquals("two", heap.min().getValue());

        heap.remove(e2);
        assertEquals(6, heap.size());
        assertEquals(3, (int) heap.min().getKey());
        assertEquals("three", heap.min().getValue());

        heap.remove(e3);
        assertEquals(5, heap.size());
        assertEquals(4, (int) heap.min().getKey());
        assertEquals("four", heap.min().getValue());

        heap.remove(e4);
        assertEquals(4, heap.size());
        assertEquals(5, (int) heap.min().getKey());
        assertEquals("five", heap.min().getValue());

        heap.remove(e5);
        assertEquals(3, heap.size());
        assertEquals(6, (int) heap.min().getKey());
        assertEquals("six", heap.min().getValue());

        heap.remove(e6);
        assertEquals(2, heap.size());
        assertEquals(7, (int) heap.min().getKey());
        assertEquals("seven", heap.min().getValue());

        heap.remove(e7);
        assertEquals(1, heap.size());
        assertEquals(8, (int) heap.min().getKey());
        assertEquals("eight", heap.min().getValue());

        heap.remove(e8);
        assertTrue(heap.isEmpty());
        assertNull(heap.min());
    }
    
    /**
     * Test the output of the heap behavior when using arbitrary key objects to
     * represent priorities
     */     
    @Test
    public void testStudentHeap() {
        AdaptablePriorityQueue<Student, String> sHeap = new HeapAdaptablePriorityQueue<Student, String>(new StudentIDComparator());
        Student s1 = new Student("J", "K", 1, 1, 1, "jk1");
        Student s2 = new Student("J", "S", 2, 1, 2, "js2");
        Student s3 = new Student("S", "H", 3, 1, 3, "sh3");
        Student s4 = new Student("J", "J", 4, 1, 4, "jj4");
        Student s5 = new Student("L", "B", 5, 1, 5, "lb5");
        
        assertTrue(sHeap.isEmpty());
        assertEquals(0, sHeap.size());
        
        Entry<Student, String> e1 = sHeap.insert(s1, "Student 1");
        Entry<Student, String> e2 = sHeap.insert(s2, "Student 2");
        Entry<Student, String> e3 = sHeap.insert(s3, "Student 3");
        Entry<Student, String> e4 = sHeap.insert(s4, "Student 4");
        Entry<Student, String> e5 = sHeap.insert(s5, "Student 5");

        assertEquals(5, sHeap.size());
        assertEquals(s1, sHeap.min().getKey()); // Ensure correct min

        sHeap.replaceKey(e1, new Student("A", "Z", 0, 1, 0, "az0"));
        assertEquals("Student 1", sHeap.min().getValue());

        sHeap.replaceKey(e2, new Student("B", "Y", 1, 1, 1, "by1"));
        assertEquals("Student 1", sHeap.min().getValue());

        sHeap.replaceKey(e3, new Student("C", "X", 2, 1, 2, "cx2"));
        assertEquals("Student 1", sHeap.min().getValue());

        sHeap.replaceKey(e4, new Student("D", "W", 3, 1, 3, "dw3"));
        assertEquals("Student 1", sHeap.min().getValue());

        sHeap.replaceKey(e5, new Student("E", "V", 4, 1, 4, "ev4"));
        assertEquals("Student 1", sHeap.min().getValue());

        // Replace values for all entries
        sHeap.replaceValue(e1, "Updated Student 1");
        sHeap.replaceValue(e2, "Updated Student 2");
        sHeap.replaceValue(e3, "Updated Student 3");
        sHeap.replaceValue(e4, "Updated Student 4");
        sHeap.replaceValue(e5, "Updated Student 5");

        assertEquals("Updated Student 1", e1.getValue());
        assertEquals("Updated Student 2", e2.getValue());
        assertEquals("Updated Student 3", e3.getValue());
        assertEquals("Updated Student 4", e4.getValue());
        assertEquals("Updated Student 5", e5.getValue());

        // Remove all students step by step and check heap properties
        sHeap.remove(e1);
        assertEquals(4, sHeap.size());
        assertEquals("Updated Student 2", sHeap.min().getValue());

        sHeap.remove(e2);
        assertEquals(3, sHeap.size());
        assertEquals("Updated Student 3", sHeap.min().getValue());

        sHeap.remove(e3);
        assertEquals(2, sHeap.size());
        assertEquals("Updated Student 4", sHeap.min().getValue());

        sHeap.remove(e4);
        assertEquals(1, sHeap.size());
        assertEquals("Updated Student 5", sHeap.min().getValue());

        sHeap.remove(e5);
        assertTrue(sHeap.isEmpty());
        assertNull(sHeap.min()); // Heap should be empty
    }
}