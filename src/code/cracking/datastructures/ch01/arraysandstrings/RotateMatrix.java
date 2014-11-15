package code.cracking.datastructures.ch01.arraysandstrings;

public class RotateMatrix {

	/**
	 * @param args
	 *  rotate 90* : (i, j) ==> (j, col-i)
	 */
	public static void rotateMatrix(int[][] matrix, int rows, int columns)
	{
		if (matrix == null)
			return;
		
		int[][] tmp = new int[rows][columns];
		
		for(int i=0; i < rows; i++)
			for(int j=0; j< columns; j++)
			{
				tmp[j][columns-1-i] = matrix[i][j];
			}
		
		for(int i=0; i < rows; i++)
			for(int j=0; j< columns; j++)
			{
				matrix[i][j] = tmp[i][j];
			}
	
	}
	
	/*
	 *    (i , j) ==> (j, col-i) ==> (col-i, col-j) ==> (col-j, col-(col-i)) ==> (col-(col-i), col-(col-j))
	 *    i.e.
	 *      (i , j) ==> (j, col-i) ==> (col-i, col-j) ==> (col-j, i)) ==> (i, j)
	 */
	public static void rotateMatrixInPlace(int[][] matrix, int rows, int columns)
	{
		if (matrix == null)
			return;
		
		for(int i=0; i < rows/2; i++)
		{
			for(int j=i; j< columns-(i+1); j++)
			{
				int tmp = matrix[i][j];
				matrix[i][j] = matrix[columns-1-j][i];
				matrix[columns-1-j][i] = matrix[columns-1-i][columns-1-j];
				matrix[columns-1-i][columns-1-j] = matrix[j][columns-1-i];
				matrix[j][columns-1-i] = tmp;
			}
		}
	}
	
	public static void printMatrix(int[][]matrix, int rows, int cols) 
	{
		for(int i=0; i < rows; i++)
		{
			for(int j=0; j < cols; j++)
			{
				System.out.print(matrix[i][j] + "   ");
			}
			System.out.print("\n");
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
    	int [][] matrix_odd = {
				{1,2,3,4,3,2,1},
				{5,6,7,8,7,6,5},
				{9,10,11,12,11,10,9},
				{13,14,15,16,15,14,13},
				{17,18,19,20,19,18,17},
				{21,22,23,24,23,22,21},
				{25,26,27,28,27,26,25}
		}; 
		
		int cols_odd=7, rows_odd=7;
		
	   	int [][] matrix_even = {
				{1,2,3,3,2,1},
				{4,5,6,6,5,4},
				{7,8,9,9,8,7},
				{10,11,12,12,11,10},
				{13,14,15,15,14,13},
				{16,17,18,18,17,16}
		}; 
		int cols_even=6, rows_even=6;
		
		System.out.println("********* Before Rotating Odd Matrix **********");
		printMatrix(matrix_odd, rows_odd, cols_odd);
		//rotateMatrix(matrix, rows, cols);
		rotateMatrixInPlace(matrix_odd, rows_odd, cols_odd);
		System.out.println("********** After Rotating Odd Matrix **********");
		printMatrix(matrix_odd, rows_odd, cols_odd);
		
		System.out.println("********* Before Rotating Even Matrix **********");
		printMatrix(matrix_even, rows_even, cols_even);
		//rotateMatrix(matrix, rows, cols);
		rotateMatrixInPlace(matrix_even, rows_even, cols_even);
		System.out.println("********** After Rotating Even Matrix **********");
		printMatrix(matrix_even, rows_even, cols_even);
		
	}

}
