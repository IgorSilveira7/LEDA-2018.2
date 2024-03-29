package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos: - Alocar o tamanho minimo
 * possivel para o array de contadores (C) - Ser capaz de ordenar arrays
 * contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		
		if(rightIndex - leftIndex > 0) {
		
			int max = encontraMax(array, leftIndex, rightIndex);
			int min = encontraMin(array, leftIndex, rightIndex);
			
			int tamanho = max - min + 1;
			
			int[] aux = new int[tamanho];
			Integer[] result = new Integer[array.length];
			
			int offset = min;
			
			for(int i = leftIndex; i < array.length;i++) {
				aux[array[i] - offset]++;
			}
			
			for(int j = 1; j < aux.length; j++) {
				aux[j] += aux[j - 1];
			}
			
			for(int k = array.length - 1; k >= leftIndex; k--) {
				aux[array[k] - offset]--;
				result[aux[array[k] - offset]] = array[k]; 
			}
			
			for(int i = leftIndex; i <= rightIndex; i++) {
				array[i] = result[i];
			}
			
		}
		
	}
	
	private int encontraMax(Integer[] array, int leftIndex, int rightIndex) {
		int max = 0;
		
		for (int i = leftIndex; i <= rightIndex; i++) {
			if(array[i] > max) {
				max = array[i];
			}
		}
		
		return max;
	}
	
	private int encontraMin(Integer[] array, int leftIndex, int rightIndex) {
		int min = array[leftIndex];
		
		for(int i = 0; i <= rightIndex; i++) {
			if(array[i] < min) {
				min = array[i];
			}
		}
		
		return min;
	}
	
	

}
