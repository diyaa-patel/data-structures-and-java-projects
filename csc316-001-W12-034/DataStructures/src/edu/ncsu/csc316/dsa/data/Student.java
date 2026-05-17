package edu.ncsu.csc316.dsa.data;

/**
 * A student is comparable and identifiable.
 * Students have a first name, last name, id number, 
 * number of credit hours, gpa, and unityID.
 * 
 * @author Dr. King
 * @author Diya Patel
 *
 */
public class Student implements Comparable<Student>, Identifiable {
	/** Student's first name */ 
	private String first; 
	/** Student's last name */
	private String last; 
	/** Student's id */
	private int id; 
	/** Student's credit hours */
	private int creditHours; 
	/** Student's gpa */
	private double gpa; 
	/** Student's unity ID */
	private String unityID; 
	
	/**
	 * Constructor for student object 
	 * @param first the first name of the student 
	 * @param last the last name of the student 
	 * @param id the student id of the student 
	 * @param creditHours the amount of credit hours the student has 
	 * @param gpa the gpa the student has 
	 * @param unityID the unityID of the student 
	 */
	public Student(String first, String last, int id, int creditHours, double gpa, String unityID) {
		setFirst(first); 
		setLast(last);
		setId(id); 
		setCreditHours(creditHours); 
		setGpa(gpa); 
		setUnityID(unityID); 
	}

	/**
	 * returns the first name of the student object 
	 * @return the first name of the student
	 */
	public String getFirst() {
		return first;
	}

	/**
	 * sets the first name of the student object 
	 * @param first the name to set the students first name to
	 */
	public void setFirst(String first) {
		this.first = first;
	}

	/**
	 * gets the last name of the student object 
	 * @return the last name of the student 
	 */
	public String getLast() {
		return last;
	}

	/**
	 * sets the last name of the student object 
	 * @param last the last name to set to the student 
	 */
	public void setLast(String last) {
		this.last = last;
	}

	/**
	 * gets the id of the student object 
	 * @return the id of the student 
	 */
	public int getId() {
		return id;
	}

	/**
	 * sets the id of the student object 
	 * @param id the id to set to the student 
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * gets the credit hours of the student 
	 * @return the creditHours the amount of credit hours the student has 
	 */
	public int getCreditHours() {
		return creditHours;
	}

	/**
	 * sets the amount of credit hours the student has  
	 * @param creditHours the creditHours to set to the student 
	 */
	public void setCreditHours(int creditHours) {
		this.creditHours = creditHours;
	}

	/**
	 * gets the student's GPA 
	 * @return the gpa of the student 
	 */
	public double getGpa() {
		return gpa;
	}

	/**
	 * sets the gpa of the student 
	 * @param gpa the gpa to set to the student object 
	 */
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	/**
	 * gets the unityID of the student object 
	 * @return the unityID the unity id of the student 
	 */
	public String getUnityID() {
		return unityID;
	}

	/**
	 * sets the unityID of the student object 
	 * @param unityID the unityID to set to the student 
	 */
	public void setUnityID(String unityID) {
		this.unityID = unityID;
	}

	/**
	 * hashcode for student objects 
	 * @return string of hashcode equivalent 
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + id;
		result = prime * result + ((last == null) ? 0 : last.hashCode());
		return result;
	}

	
	/**
	 * checks if two student objects are equal 
	 * @param obj the student object being compared to 
	 * @return true if the student objects are equal 
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (first == null) {
			if (other.first != null)
				return false;
		} else if (!first.equals(other.first))
			return false;
		if (id != other.id)
			return false;
		if (last == null) {
			if (other.last != null)
				return false;
		} else if (!last.equals(other.last))
			return false;
		return true;
	}

	/**
	 * makes a string of student's fields 
	 * @return StudentString the string of student fields 
	 */
	public String toString() {
		return "Student [first=" + first + ", last=" + last + ", id=" + id + ", creditHours=" + creditHours + ", gpa="
				+ gpa + ", unityID=" + unityID + "]";
	}

	/**
	 * compares two student objects 
	 * @param other the student being compared to 
	 * @return -1 if other student comes before the student 
	 */
	public int compareTo(Student other) {
		int firstName = this.first.compareTo(other.first); 
		int lastName = this.last.compareTo(other.last); 
		
		if(firstName == 0 && lastName == 0 && this.id == other.id) {
			return 0; 
		}
		
		if(lastName > 0) {
			return 1; 
		}
		else if(lastName < 0) {
			return -1; 
		}
		else if (firstName > 0) {
			return 1; 
		}
		else if(firstName < 0) {
			return -1; 
		}
		else if(this.id < other.id) {
			return 1; 
		}
		return -1; 
	}
}
