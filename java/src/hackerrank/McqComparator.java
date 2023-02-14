package hackerrank;

import java.util.Arrays;
import java.util.Comparator;

public class McqComparator {
	
	public static void main(String[] args) {
		String[] cities = {"Bangkok", "Paris", "Singapore", "New Zealand"};
		Arrays.sort(cities, new MySort());
		System.out.println("cities: " + Arrays.toString(cities));
		// it will returns the insertion point like a magic you don't understand
		// Arrays.binarySearch must be in ascending order
		System.out.println(Arrays.binarySearch(cities, "New Zealand"));
		
	}
	
	static class MySort implements Comparator<String> {

		public int compare(String a, String b) {
			// descending
			// return b.compareTo(a);
			//ascending
			return a.compareTo(b);
		}
		
	}

}
