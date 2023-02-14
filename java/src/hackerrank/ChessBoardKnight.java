package hackerrank;

import java.util.Arrays;
import java.util.Vector;

public class ChessBoardKnight {
	
	public static class Coordinate {
		public int x;
		public int y;
		
		public Coordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public boolean equals(Coordinate coordinate) {
			return this.x == coordinate.x && this.y == coordinate.y;
		}
		
		public String toString() {
			return this.x + ", " + this.y;
		}
	}
	
	public static class Knight extends Coordinate {
		public int steps;
		
		public Knight(int x, int y, int steps) {
			super(x, y);
			this.steps = steps;
		}
		
		public String toString() {
			return "used " + this.steps + " steps to reach (" + super.toString() + ")";
		}
	}

	public static void main(String[] args) {
		int boardSize = 30;
//		Coordinate start = new Coordinate(0, 0);
//		Coordinate end = new Coordinate(0, 2);
		
//		Coordinate start = new Coordinate(0, 0);
//		Coordinate end = new Coordinate(4, 8);
		
		Coordinate start = new Coordinate(0, 0);
		Coordinate end = new Coordinate(20, 20);
		
		
		long startTime = System.currentTimeMillis();
		
		int steps = minMoves(boardSize, start.x, start.y, end.x, end.y);
		System.out.println(steps);
		
		long duration = (System.currentTimeMillis() - startTime) / 1000;
		System.out.println("elapse time: " + duration);
	}
	
	public static int minMoves(int n, int startRow, int startCol, int endRow, int endCol) {
		Knight knight = new Knight(startRow, startCol, 0);
		Coordinate destination = new Coordinate(endRow, endCol);
		
		// x and y direction, where a knight can move
		// all possible moves of a knight in all direction
        int[] dx = { -2, -1, 1, 2, -2, -1, 1, 2 }; 
        int[] dy = { -1, -2, -2, -1, 1, 2, 2, 1 }; 
        
        // mark all coordinate as not visit yet
        boolean[][] visited = new boolean[n][n];
        for(boolean [] visit: visited) {
        	Arrays.fill(visit, false);
        }
        
        // mark knight position is visited
        visited[knight.x][knight.y] = true;
        
        // add current position into queue
        Vector<Knight> queue = new Vector<Knight>();
        queue.add(knight);
        
        while(!queue.isEmpty()) {
        	Knight currentPos = queue.firstElement();
        	queue.remove(0);
        	
        	// if destination reached
        	if(currentPos.equals(destination)) {
        		return currentPos.steps;
        	}
        	
        	// add all possible moves into queue
        	for(int i=0; i<dx.length; i++) {
        		int newX = currentPos.x + dx[i];
        		int newY = currentPos.y + dy[i];
        		Knight move = new Knight(newX, newY, currentPos.steps + 1);
        		if(reachable(move, n) && !visited[newX][newY]) {
        			visited[newX][newY] = true;
        			queue.add(move);
        		}
        	}
        }
		
		return -1; // unreachable
	}
	
	public static boolean reachable(Coordinate coordinate, int boundry) {
		return (coordinate.x >= 0 && coordinate.x < boundry) && (coordinate.y >= 0 && coordinate.y < boundry);
	}

}
