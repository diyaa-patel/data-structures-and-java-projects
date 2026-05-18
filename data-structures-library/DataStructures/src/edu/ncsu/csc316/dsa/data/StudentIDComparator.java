package edu.ncsu.csc316.dsa.data;

import java.util.Comparator;

/**
 * Comparator to compare students based on id number
 * @author Dr. King
 * @author Diya Patel
 *
 */
public class StudentIDComparator implements Comparator<Student> {

	/**
	 * Compares students based on id number in ascending order
	 * @param one , one of the students to compare
	 * @param two , the other student that we are comparing the ID's 
	 * @return 0 if the id is the same 
	 */
	@Override
	public int compare(Student one, Student two) {
		if(two.getId() < one.getId()) {
			return 1; 
		}
		else if(two.getId() > one.getId()) {
			return -1; 
		}
		return 0;
	}

}
