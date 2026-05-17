package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentGPAComparator;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;

/**
 * Test cases for BubbleSorter class 
 */
public class BubbleSorterTest {
	/** Student one */ 
	private Student sOne;
	/** Student two */
	private Student sTwo;
	/** Student three */
	private Student sThree;
	/** Student four */
	private Student sFour;
	/** Student five */
	private Student sFive;
	
	/** natural order */
	private BubbleSorter natural; 
	
	/** gpa order */ 
	private BubbleSorter gpa; 

	/** id order */ 
	private BubbleSorter id; 
	
	/** original */ 
	private BubbleSorter sorter; 
	
	/**
	 * set up for test cases 
	 */
	@Before
	public void setUp() {
		sOne = new Student("A", "A", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("B", "B", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("C", "C", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("D", "D", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("E", "E", 5, 5, 5.0, "fiveUnityID");
		
		sorter = new BubbleSorter<Student>();
		gpa = new BubbleSorter<>(new StudentGPAComparator());
		id = new BubbleSorter<>(new StudentIDComparator()); 
		natural = new BubbleSorter<>(null); 
		
	}
	
	/**
	 * tests that the array was sorted correctly 
	 */
	@Test
	public void testSortStudentOriginal() {
		Student[] original = { sTwo, sOne, sFour, sThree, sFive };
		sorter.sort(original);
		assertEquals(sOne, original[0]);
		assertEquals(sTwo, original[1]);
		assertEquals(sThree, original[2]);
		assertEquals(sFour, original[3]);
		assertEquals(sFive, original[4]);
	}
	
	/**
	 * tests the sort method by natural order 
	 */
	@Test
	public void testSortStudent() {
		Student[] original = { sTwo, sOne, sFour, sThree, sFive };
		natural.sort(original);
		assertEquals(sOne, original[0]);
		assertEquals(sTwo, original[1]);
		assertEquals(sThree, original[2]);
		assertEquals(sFour, original[3]);
		assertEquals(sFive, original[4]);
	}
	
	/**
	 * tests sort method by gpa 
	 */
	@Test 
	public void testGpaOrder() {
		Student[] original = { sTwo, sOne, sFour, sThree, sFive };
		gpa.sort(original);
		assertEquals(sFive, original[0]);
		assertEquals(sFour, original[1]);
		assertEquals(sThree, original[2]);
		assertEquals(sTwo, original[3]);
		assertEquals(sOne, original[4]); 
		
	}
	
	/**
	 * tests sort method by id 
	 */
	@Test 
	public void testIdOrder() {
		Student[] original = { sTwo, sOne, sFour, sThree, sFive };
		id.sort(original);
		assertEquals(sOne, original[0]);
		assertEquals(sTwo, original[1]);
		assertEquals(sThree, original[2]);
		assertEquals(sFour, original[3]);
		assertEquals(sFive, original[4]); 
		
	}
	

}
