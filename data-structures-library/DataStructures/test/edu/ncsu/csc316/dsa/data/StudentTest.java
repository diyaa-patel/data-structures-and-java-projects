package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
/**
 * Tests for Student class 
 */
public class StudentTest {
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
	/** Student five alternate 1 */ 
	private Student sOtherFive;
	/** Student four alternate 1 */
	private Student sOtherFour;
	/** Student five alternate 2 */
	private Student sOtherFive2;
	/** Student five alternate 3 */
	private Student sOtherFive3; 
	/** Student five alternate 4 */
	private Student sOtherFive4;
	/** Student five alternate 5 */
	private Student sOtherFive5;
	/** Student five alternate 6 */
	private Student sOtherFive6; 

	/**
	 * set up for test cases 
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
		sOtherFive = new Student("Five", "FiveLast", 5, 5, 5.0, "fiveUnityID"); 
		sOtherFive2 = new Student ("Fizst", "FiveLast", 5, 5, 5.0, "fiveUnityID"); 
		sOtherFive3 = new Student("FiveFirst", "Last", 5, 5, 5.0, "fiveUnityID");
		sOtherFive4 = new Student("FiveFirst", "Abcd", 5, 5, 5.0, "fiveUnityID");
		sOtherFive5 = new Student("FiveFirst", "FiveLast", 2, 5, 5.0, "fiveUnityID"); 
		sOtherFive6 = new Student("FiveFirst", "FiveLast", 6, 5, 5.0, "fiveUnityID"); 
		sOtherFour = new Student("First", "FourLast", 4, 4, 4.0, "fourUnityID");
	}

	/**
	 * Tests the setFirst() method 
	 */
	@Test
	public void testSetFirst() {
		sOne.setFirst("newOne");
		assertEquals("newOne", sOne.getFirst());
	}

	/**
	 * Tests the setLast() method 
	 */
	@Test
	public void testSetLast() {
		sOne.setLast("newOne");
		assertEquals("newOne", sOne.getLast());
	}

	/**
	 * tests the setId() method 
	 */
	@Test
	public void testSetId() {
		sOne.setId(100);
		assertEquals(100, sOne.getId());
	}

	/**
	 * tests the setGpa() method 
	 */
	@Test
	public void testSetGpa() {
		sOne.setGpa(3.51);
		assertEquals(3.51, sOne.getGpa(), 0.001);
	}
	
	/**
	 * tests the setUnityID() method 
	 */
	@Test
	public void testSetUnityID() {
		sOne.setUnityID("oneUnity");
		assertEquals("oneUnity", sOne.getUnityID());
	}

	/**
	 * tests the compareTo() method 
	 */
	@Test
	public void testCompareTo() {
		assertTrue(sOne.compareTo(sTwo) < 0);
		assertTrue(sTwo.compareTo(sOne) > 0);
		assertTrue(sOne.compareTo(sOne) == 0);
		assertTrue(sTwo.compareTo(sTwo) == 0);
		assertTrue(sFive.compareTo(sFour) < 0);
		assertTrue(sFive.compareTo(sOtherFive) > 0);
		assertTrue(sFour.compareTo(sOtherFour) > 0);
		assertTrue(sFive.compareTo(sOtherFive2) < 0);
		assertTrue(sFive.compareTo(sOtherFive3) < 0);
		assertTrue(sFive.compareTo(sOtherFive4) > 0);
		assertTrue(sFive.compareTo(sOtherFive5) < 0);
		assertTrue(sFive.compareTo(sOtherFive6) > 0);
		assertTrue(sOtherFive6.compareTo(sFive) < 0);
		assertTrue(sOtherFive5.compareTo(sFive) > 0);
		assertTrue(sOtherFive4.compareTo(sFive) < 0);
		
	}
	
	/**
	 * Tests the equals() method 
	 */
	@Test 
	public void testEqualTo() {
		assertTrue(sThree.equals(sThree));
		assertFalse(sThree.equals(sFive)); 
		assertFalse(sThree.equals(sFive)); 
		assertFalse(sFour.equals(sOtherFour)); 
		assertFalse(sFive.equals(sOtherFive)); 
		assertFalse(sFive.equals(sOtherFive2)); 
		assertFalse(sFive.equals(sOtherFive3)); 
		assertFalse(sFive.equals(sOtherFive4)); 
		assertFalse(sFive.equals(sOtherFive5)); 
		assertFalse(sFive.equals(sOtherFive6)); 
		assertFalse(sFive == null); 
	}
	
	/**
	 * Tests the setCreditHours() method 
	 */
	@Test 
	public void testCreditHours() {
		sOne.setCreditHours(1);
		assertEquals(1, sOne.getCreditHours()); 
	}
	
	/**
	 * tests the toString() method 
	 */
	@Test
	public void testToString() {
		String student = "Student [first=" + sOne.getFirst() + ", last=" + sOne.getLast() + ", id=" + sOne.getId() + ", creditHours=" + sOne.getCreditHours() + ", gpa="
				+ sOne.getGpa() + ", unityID=" + sOne.getUnityID() + "]"; 
		assertEquals(student, sOne.toString()); 
	}

}
