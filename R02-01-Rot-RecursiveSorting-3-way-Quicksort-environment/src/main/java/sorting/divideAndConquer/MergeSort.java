package sorting.divideAndConquer;

import java.util.LinkedList;
import java.util.List;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if(leftIndex < rightIndex) {
			int middleIndex = (leftIndex + rightIndex) / 2;
			
			sort(array, leftIndex, middleIndex);
			sort(array, middleIndex + 1, rightIndex);
			
			merge(array, leftIndex, middleIndex, rightIndex);
		}
			
	}
	
	private void merge(T[] array, int leftIndex, int middleIndex, int rightIndex) {
		int n1 = middleIndex - leftIndex;
		int n2 = rightIndex - middleIndex;
		
		List<T> leftArray = new LinkedList<T>();
		List<T> rightArray = new LinkedList<T>();
		
		
		for(int i = 0; i <= n1; i++) {
			leftArray.add(array[leftIndex + i]);
		}
		
		for(int j = 0; j < n2; j++) {
			rightArray.add(array[middleIndex + 1 + j]);
		}
		
		int i = 0;
		int j = 0;
		int k = leftIndex;
		
		while(i <= n1 && j < n2) {
			if(leftArray.get(i).compareTo(rightArray.get(j)) < 0) {
				array[k] = leftArray.get(i);
				i++;
			} else {
				array[k] = rightArray.get(j);
				j++;
			}
			k++;
		}
		
		while(i <= n1) {
			array[k] = leftArray.get(i);
			i++;
			k++;
		}
		
		while(j < n2) {
			array[k] = rightArray.get(j);
			j++;
			k++;
		}
	}
}
