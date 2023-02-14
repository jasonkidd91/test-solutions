package hackerrank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class FootballScore {

	public static void main(String[] args) {
		
		List<Integer> teamA = IntStream.range(0, Integer.MAX_VALUE/10000)
			    .mapToObj(i -> ThreadLocalRandom.current().nextInt(0, 10 + 1))
			    .collect(Collectors.toList());
		
		List<Integer> teamB = IntStream.range(0, Integer.MAX_VALUE/10000)
			    .mapToObj(i -> ThreadLocalRandom.current().nextInt(0, 10 + 1))
			    .collect(Collectors.toList());
		
//		List<Integer> teamA = java.util.Arrays.asList(1,2,3,4,5);
//		List<Integer> teamB = java.util.Arrays.asList(4, 5, 0);
		
		
		long startTime = System.currentTimeMillis();
		
		List<Integer> result = counts(teamA, teamB);
		System.out.println(result);
		
		long duration = (System.currentTimeMillis() - startTime) / 1000;
		System.out.println("elapse time: " + duration);
	}
	
	public static List<Integer> counts(List<Integer> teamA, List<Integer> teamB) {
		List<Integer> result = new ArrayList<Integer>();
		Map<Integer, Integer> maps = new HashMap<Integer, Integer>();
		Collections.sort(teamA);
		for(int goal: teamB) {
			if(maps.get(goal)!=null) {
				result.add(maps.get(goal));
			} else {
				result.add(binarySearchCount_lessthanequal(teamA, goal));
			}
		}
		return result;
	}
	
	public static List<Integer> counts_greaterthan(List<Integer> teamA, List<Integer> teamB) {
		List<Integer> result = new ArrayList<Integer>();
		Map<Integer, Integer> maps = new HashMap<Integer, Integer>();
		Collections.sort(teamA, Comparator.reverseOrder());
		for(int goal: teamB) {
			if(maps.get(goal)!=null) {
				result.add(maps.get(goal));
			} else {
				result.add(binarySearchCount_greaterthan(teamA, goal));
			}
		}
		return result;
	}
	
	public static int binarySearchCount_lessthanequal(List<Integer> teamA, Integer findKey) {
		int left = 0;
		int right = teamA.size() -1;
		
		int count = 0;
		
		// decrease and conquer
		while(left <= right) {
			int mid = (left + right)/2;
			//1,2,3,4,5
			if(teamA.get(mid) <= findKey) {
				left = mid + 1;
				count = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		
		return count;
	}
	
	public static int binarySearchCount_greaterthan(List<Integer> teamA, Integer findKey) {
		int left = 0;
		int right = teamA.size()-1;
		
		int count = 0;
		
		// decrease and conquer
		while(left <= right) {
			//5,4,3,2,1
			int mid = (left + right)/2;
			if(teamA.get(mid) > findKey) {
				left = mid + 1;
				count = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		
		return count;
	}
	
	

}
