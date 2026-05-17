package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentGPAComparator;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;

/**
 * Test cases for QuickSorter
 */
public class QuickSorterTest {
		
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
	
	/** array with data ascending */
	private Integer[] dataAscending = { 1, 2, 3, 4, 5 };
	/** array with data descending */
	private Integer[] dataDescending = { 5, 4, 3, 2, 1 };
	/** array with data that is randomized */
	private Integer[] dataRandom = { 4, 1, 5, 3, 2 };
	
	/** selection sorter*/ 
	private QuickSorter integerSorter; 
	/** first element is selected as pivot */ 
	private QuickSorter<Integer> firstPivot;
	/** last element is selected as pivot */
	private QuickSorter<Integer> lastPivot;
	/** middle element is selected as pivot */ 
	private QuickSorter<Integer> middlePivot;


	/**
	 * set up for test cases 
	 */
	@Before
	public void setUp() {
		integerSorter = new QuickSorter();
		firstPivot = new QuickSorter<>(QuickSorter.FIRST_ELEMENT_SELECTOR);
	    lastPivot = new QuickSorter<>(QuickSorter.LAST_ELEMENT_SELECTOR);
	    middlePivot = new QuickSorter<>(QuickSorter.MIDDLE_ELEMENT_SELECTOR);
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
		
	}
	
	/**
	 * tests the three arrays in selectionSort 
	 */
	@Test
	public void testSortIntegers() {
		integerSorter.sort(dataAscending);
		assertEquals(Integer.valueOf(1), dataAscending[0]);
		assertEquals(Integer.valueOf(2), dataAscending[1]);
		assertEquals(Integer.valueOf(3), dataAscending[2]);
		assertEquals(Integer.valueOf(4), dataAscending[3]);
		assertEquals(Integer.valueOf(5), dataAscending[4]);
	
		integerSorter.sort(dataDescending);
		assertEquals(Integer.valueOf(1), dataDescending[0]);
		assertEquals(Integer.valueOf(2), dataDescending[1]);
		assertEquals(Integer.valueOf(3), dataDescending[2]);
		assertEquals(Integer.valueOf(4), dataDescending[3]);
		assertEquals(Integer.valueOf(5), dataDescending[4]);
	
		integerSorter.sort(dataRandom);
		assertEquals(Integer.valueOf(1), dataRandom[0]);
		assertEquals(Integer.valueOf(2), dataRandom[1]);
		assertEquals(Integer.valueOf(3), dataRandom[2]);
		assertEquals(Integer.valueOf(4), dataRandom[3]);
		assertEquals(Integer.valueOf(5), dataRandom[4]);
	}

	/**
	 * tests the sort method by natural order 
	 */
	@Test
	public void testSortStudentNatural() {
		Student[] original = { sTwo, sOne, sFour, sThree, sFive };
		QuickSorter<Student> natural = new QuickSorter<>();  
		natural.sort(original);
		assertEquals(sFive, original[0]);
		assertEquals(sFour, original[1]);
		assertEquals(sOne, original[2]);
		assertEquals(sThree, original[3]);
		assertEquals(sTwo, original[4]);
	}

	/**
	 * tests sort method by gpa 
	 */
	@Test 
	public void testGpaOrder() {
		Student[] original = { sTwo, sOne, sFour, sThree, sFive };
		Comparator<Student> gpaComparator = new StudentGPAComparator(); 
		QuickSorter<Student> gpaSorter = new QuickSorter<>(gpaComparator); 
		gpaSorter.sort(original);
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
		Comparator<Student> idComparator = new StudentIDComparator(); 
		QuickSorter<Student> idSorter = new QuickSorter<>(idComparator); 
		idSorter.sort(original);
		assertEquals(sOne, original[0]);
		assertEquals(sTwo, original[1]);
		assertEquals(sThree, original[2]);
		assertEquals(sFour, original[3]);
		assertEquals(sFive, original[4]); 
		
	}
	
	/**
	 * Tests the QuickSorter with the First element as the pivot 
	 */
	 @Test
	 public void testSortWithFirstElementPivot() {
		 firstPivot.sort(dataRandom);
	     assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, dataRandom);
	 }
	   
	 /**
	  * Tests the QuickSorter with the Last element as the pivot 
	  */
	 @Test
	 public void testSortWithLastElementPivot() {
	     lastPivot.sort(dataRandom);
	     assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, dataRandom);
	 }
	    
	 /**
	  * Tests the QuickSorter with the Middle element as the pivot 
	  */
	@Test
	public void testSortWithMiddleElementPivot() {
	    middlePivot.sort(dataRandom);
	    assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, dataRandom);
	}

}
