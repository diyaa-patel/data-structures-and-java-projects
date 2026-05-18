package edu.ncsu.csc316.dsa.sorter;

import edu.ncsu.csc316.dsa.data.Identifiable;

/**
 * RadixSorter uses the radix sort algorithm to sort data
 * @author Dr. King
 * @author Diya Patel 
 *
 * @param <E> the generic type of data to sort
 */
public class RadixSorter<E extends Identifiable> implements Sorter<E> {

	/**
	 * sorts the data using Radix Sorter 
	 * @param data the array of data to be sorted 
	 */
	public void sort(E[] data) {
		int k = 0; 
		for(int i = 0; i < data.length; i++) {
			k = Math.max(k, data[i].getId()); 
		}
		int x = (int) Math.ceil(Math.log(k + 1) / Math.log(10)); 
		
		int p = 1; 
		for(int j = 1; j <= x; j++) {
			int[] arrayB = new int [10]; 
			for(int i = 0; i < data.length; i++) {
				int id = data[i].getId(); 
				arrayB[id / p % 10] = arrayB[id / p % 10] + 1; 
			}
			
			for(int i = 1; i <= 9; i++) {
				arrayB[i] = arrayB[i - 1] + arrayB[i]; 
			}
			
			@SuppressWarnings("unchecked")
			E[] arrayF = (E[]) new Identifiable [data.length]; 

			
			for(int i = data.length - 1; i >= 0; i--) {
				int id = data[i].getId(); 
				arrayF[arrayB[id / p % 10] - 1] = data[i]; 
				arrayB[id / p % 10]--;
			}
			
			for(int i = 0; i < data.length; i++) {
				data[i] = arrayF[i]; 
			}
			
			p = p * 10; 
			
		}
		
	}
	

}
