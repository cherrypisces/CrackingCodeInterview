package code.cracking.conceptsandalgorithms.ch08.recursion;

import java.util.ArrayList;
import java.util.Random;

import code.cracking.datastructures.Utilities;


class Point {
	int x,y;
	
	public Point(int X, int Y){
		x = X;
		y = Y;
	}
	
	public String toString() {
		return "("+ x +", " + y +")";
	}
}

public class RobotPaths {	
	public static ArrayList<Point> path = new ArrayList<Point>();
	
	public static boolean isVacant(int[][] matrix, int x, int y) {
		if(matrix == null)
			return false;		
		if (x<0 || x>=matrix.length)
			return false;
		if (y<0 || y>=matrix[0].length)
			return false;
		
		if(matrix[x][y] == 0)
			return true;
		else
			return false;
	}
	
	public static boolean getPaths(int[][] matrix, int x, int y) {
		Point p = new Point(x, y);
		path.add(p);
		
		if(x==0 && y==0 ) 
			return true;
		
		if(!isVacant(matrix, x, y))
			return false;
		
		boolean found = false;
		if(x>=1 && isVacant(matrix, x-1,y)) {
			found = getPaths(matrix, x-1, y);
		}
		if(y>=1 && isVacant(matrix, x, y-1)) {
			found = getPaths(matrix, x, y-1);
		}
		
		if(!found)
			path.remove(p);
		
		return found;
	}

	public static void printPath() {
		System.out.println("\nCurrent path is : ");
		for(int i=path.size()-1; i>=0; i--) {
			String direction = "";
			if((i-1) >= 0) {
				Point curr = path.get(i);
				Point next = path.get(i-1);
				if (curr.x+1 == next.x) {
					direction = " -> ";
				} else if(curr.y+1 == next.y) {
					direction = " |\n";
				}
			}
			System.out.print(path.get(i) + direction);
		}
	}
	
	
	public static void findPaths(int[][] matrix, int x, int y, ArrayList<Point> currPath) {
		
		if (!isVacant(matrix, x, y)) 
			return;
			
		currPath.add(new Point(x, y));
		if (x==0 && y==0) {
			printPath(matrix, currPath);
			return;
		} 
		
		if(isVacant(matrix, x-1, y)) {
			ArrayList<Point> prevLeft = (ArrayList<Point>)currPath.clone();
			findPaths(matrix, x-1, y, prevLeft);
		} 

		if(isVacant(matrix, x, y-1)) {
			ArrayList<Point> prevUp = (ArrayList<Point>)currPath.clone();
			findPaths(matrix, x, y-1, prevUp);
		}

	}
	
	
	public static boolean pointInPath(ArrayList<Point> currPath, int x, int y) {
		if(currPath == null)
			return false;
		
		for(int i=0; i<currPath.size();i++) {
			Point p = currPath.get(i);
			if(p.x == x && p.y == y) {
				return true;
			}
		}		
		return false;		
	}
	
	
	public static void printPath(int[][] matrix, ArrayList<Point> currPath) {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		if(matrix == null) return;
		
		for(int i=0; i < matrix.length; i++) 
		{
			for(int j=0; j < matrix[i].length; j++) 
			{
				if (matrix[i][j] > 0)
					System.out.print(" #");
				else 
					System.out.print(" "+ matrix[i][j]);
				if(pointInPath(currPath, i, j)) {
					if ((j+1<matrix[i].length) && pointInPath(currPath,i,j+1)) {
						System.out.print(" -");
					} else {
						System.out.print("  ");
					}
				} else {
					System.out.print("  ");
				}
			}
			System.out.println();
			for(int j=0; j < matrix[i].length; j++) {
				if( (i+1<matrix.length) && pointInPath(currPath,i+1,j) ) {
					System.out.print(" | ");
					break;
				} else {
					System.out.print("    ");
				}
			}			
			System.out.println();
		}
	}
	
	
	
	public static void main(String[] args) {
		int N=8, M=8;
		int[][] matrix = null;
//		Utilities.printMatrix(matrix);
//		if(getPaths(matrix, 9, 9)) {
//			printPath();
//			
//			printPath(matrix, path);
//		}
		
		do {
			matrix = Utilities.randomMatrix(N, M, 0, 1);
			path.clear();
		} while (!getPaths(matrix, N-1, M-1));
		
		Utilities.printMatrix(matrix);	
		path.clear();
		System.out.println("\n Start finding path ... \n");
		findPaths(matrix, N-1, M-1, path);
	}
	
}
