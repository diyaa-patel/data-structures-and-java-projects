package edu.ncsu.csc316.dsa.io;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * Tests for StudentReader class 
 */
public class StudentReaderTest {
	
	/**
	 * Test the Reader method 
	 */
	@Test
	public void testReadFile() {
		Student[] contents = StudentReader.readInputAsArray("input/student_ascendingID.csv");
		assertEquals("Amber", contents[0].getFirst());
		assertEquals("Ara", contents[1].getFirst());
		assertEquals("Lacie", contents[2].getFirst());
		assertEquals("Idalia", contents[3].getFirst());
		assertEquals("Evelin", contents[4].getFirst());
		assertEquals("Lewis", contents[5].getFirst());
		assertEquals("Alicia", contents[6].getFirst());
		assertEquals("Tyree", contents[7].getFirst());
		assertEquals("Loise", contents[8].getFirst());
		assertEquals("Roxann", contents[9].getFirst());
		assertEquals("Nichole", contents[10].getFirst());
		assertEquals("Charlene", contents[11].getFirst());
		assertEquals("Shanti", contents[12].getFirst());
		assertEquals("Cristine", contents[13].getFirst());
		assertEquals("Tanner", contents[14].getFirst());
		assertEquals("Dante", contents[15].getFirst());
	}
	
	/**
	 * Tests that the file is read properly by seeing each field 
	 */
	@Test
	public void testStudent() {
		Student[] contents = StudentReader.readInputAsArray("input/student_ascendingID.csv");
		assertEquals("Amber", contents[0].getFirst());
		assertEquals("Michael", contents[0].getLast()); 
		assertEquals("michaea", contents[0].getUnityID()); 
		assertEquals(1, contents[0].getId()); 
		assertTrue(1.1 == contents[0].getGpa()); 
		assertEquals(10, contents[0].getCreditHours()); 
	}
	
}
