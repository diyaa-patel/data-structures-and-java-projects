package edu.ncsu.csc316.dsa.manager;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentGPAComparator;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;
import edu.ncsu.csc316.dsa.sorter.SelectionSorter;
/**
 * Tests the Student Manager class
 */
public class StudentManagerTest {

	/** Student Manager */ 
	private StudentManager sm;
	
	/**
	 * set up for test cases 
	 */
	@Before
	public void setUp() {
		sm = new StudentManager("input/student_ascendingID.csv");
	}
	
	/**
	 * Test for sort() method 
	 */
	@Test
	public void testSort() {
		Student[] sorted = sm.sort();
		assertEquals("Tanner", sorted[0].getFirst());
		assertEquals("Roxann", sorted[1].getFirst());
		assertEquals("Shanti", sorted[2].getFirst());
		assertEquals("Dante", sorted[3].getFirst());
		assertEquals("Cristine", sorted[4].getFirst());
		assertEquals("Ara", sorted[5].getFirst());
		assertEquals("Lewis", sorted[6].getFirst());
		assertEquals("Charlene", sorted[7].getFirst());
		assertEquals("Amber", sorted[8].getFirst());
		assertEquals("Lacie", sorted[9].getFirst());
		assertEquals("Idalia", sorted[10].getFirst());
		assertEquals("Tyree", sorted[11].getFirst());
		assertEquals("Evelin", sorted[12].getFirst());
		assertEquals("Alicia", sorted[13].getFirst());
		assertEquals("Loise", sorted[14].getFirst());
		assertEquals("Nichole", sorted[15].getFirst());
	}
	
	/**
	 * Tests the natural order 
	 */
	@Test 
	public void testNaturalOrder() {
		StudentManager natural = new StudentManager("input/student_ascendingID.csv"); 
		Student[] sorted = natural.sort();
		assertEquals("Tanner", sorted[0].getFirst());
		assertEquals("Roxann", sorted[1].getFirst());
		assertEquals("Shanti", sorted[2].getFirst());
		assertEquals("Dante", sorted[3].getFirst()); 
	}
	
	/**
	 * Tests the id order 
	 */
	@Test 
	public void testIdOrder() {
		Comparator<Student> idComparator = new StudentIDComparator(); 
		SelectionSorter<Student> idSorter = new SelectionSorter<>(idComparator); 
		StudentManager id = new StudentManager("input/student_ascendingID.csv", idSorter); 
		Student[] sorted = id.sort();
		assertEquals("Amber", sorted[0].getFirst());
		assertEquals("Ara", sorted[1].getFirst());
		assertEquals("Lacie", sorted[2].getFirst());
		assertEquals("Idalia", sorted[3].getFirst()); 
		
	}
	
	/**
	 * Tests the gpa order 
	 */
	@Test 
	public void testGpaOrder() {
		Comparator<Student> gpaComparator = new StudentGPAComparator(); 
		SelectionSorter<Student> gpaSorter = new SelectionSorter<>(gpaComparator);
		StudentManager gpa = new StudentManager("input/student_ascendingID.csv", gpaSorter); 
		Student[] sorted = gpa.sort();
		assertEquals("Nichole", sorted[0].getFirst());
		assertEquals("Alicia", sorted[1].getFirst());
		assertEquals("Charlene", sorted[2].getFirst());
		assertEquals("Cristine", sorted[3].getFirst()); 
		
	}

}
