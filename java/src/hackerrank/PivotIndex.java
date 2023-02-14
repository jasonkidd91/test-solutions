package hackerrank;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PivotIndex {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		
		List<Integer> nums = IntStream.range(0, Integer.MAX_VALUE/10000)
			    .mapToObj(i -> ThreadLocalRandom.current().nextInt(1, 2 + 1))
			    .collect(Collectors.toList());
//		List<Integer> nums = Arrays.asList(-7, 1, 5, 2, -4, 3, 0 );
//		List<Integer> nums = Arrays.asList(1,7,3,6,5,6);
//		List<Integer> nums = Arrays.asList(4, 7, 3, 6, 8, 6);
//		System.out.println(nums); 
		int res = pivotIndex(nums);
		System.out.println("PivotIndex is " + res);
		
		long duration = (System.currentTimeMillis() - startTime) / 1000;
		System.out.println("elapse time: " + duration);
	}
	
	public static int pivotIndex(List<Integer> nums) {
		int res = -1;
		
		Integer right = nums.stream().reduce(0, Integer::sum);
		Integer left = 0;
		
		for(int i=0; i<nums.size(); i++) {
			int current = nums.get(i);
			right -= current;
			if(left.equals(right)) {
				res = i;
				return i;
			}
			left += current;
		}
		
		return res;
	}

}
