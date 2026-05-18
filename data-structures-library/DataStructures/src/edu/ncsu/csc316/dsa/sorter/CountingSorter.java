package edu.ncsu.csc316.dsa.sorter;

import edu.ncsu.csc316.dsa.data.Identifiable;

/**
 * CountingSorter uses the counting sort algorithm to sort data
 * @author Dr. King
 * @author Diya Patel
 *
 * @param <E> the generic type of data to sort
 */
public class CountingSorter<E extends Identifiable> implements Sorter<E> {

	/**
	 * sorts the array of data using Counting Sorter 
	 * @param data the data to sort 
	 */
	public void sort(E[] data) {
		int min = data[0].getId(); 
		int max = data[0].getId(); 
		for(int i = 0; i < data.length; i++) {
			int id = data[i].getId(); 
			min = Math.min(id, min); 
			max = Math.max(max, id); 
		}
		int k = max - min + 1; 
		int[] arrayB = new int[k]; 
		for(int i = 0; i < data.length; i++) {
			int id = data[i].getId(); 
			arrayB[id - min] = arrayB[id - min] + 1; 
		}
		for(int i = 1; i < k; i++) {
			arrayB[i] = arrayB[i - 1] + arrayB[i]; 
		}
		
		@SuppressWarnings("unchecked")
		E[] arrayF = (E[]) new Identifiable [data.length]; 
		for(int i = data.length - 1; i >= 0; i--) {
			int id = data[i].getId(); 
			arrayF[arrayB[id - min] - 1] = data[i]; 
			arrayB[id - min]--; 
		}
		
		for(int i = 0; i < data.length; i++) {
			data[i] = arrayF[i]; 
		}
		
	}
	
	

	
}
