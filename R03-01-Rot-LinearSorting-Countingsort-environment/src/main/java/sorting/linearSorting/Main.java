package sorting.linearSorting;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		ExtendedCountingSort c = new ExtendedCountingSort();
		
		Integer[] array = new Integer[] {-5,3,4,8,-9,2,41,89,-4};
		
		System.out.println(Arrays.toString(array));
		c.sort(array, 3, array.length - 1);
		System.out.println(Arrays.toString(array));

	}

}
