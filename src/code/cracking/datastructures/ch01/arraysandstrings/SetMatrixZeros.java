package code.cracking.datastructures.ch01.arraysandstrings;

public class SetMatrixZeros {

	public static void setZeros(int[][] matrix)
	{
		if (matrix == null)
			return;
		
		int[] rows = new int[matrix.length];
		int[] columns = new int[matrix[0].length];
		
		for(int i=0; i < rows.length; i++)
			for(int j=0; j < columns.length; j++)
			{
				if(matrix[i][j] == 0)
				{
					rows[i] = 1;
					columns[j] = 1;
				}
			}
		
		for(int i=0; i < rows.length; i++)
			for(int j=0; j < columns.length; j++)
			{
				if (rows[i] == 1  || columns[j] ==1) {
					matrix[i][j] = 0;
				}
			}
	}
	
	public static void printMatrix(int[][]matrix) 
	{
		for(int i=0; i < matrix.length; i++)
		{
			for(int j=0; j < matrix[0].length; j++)
			{
				System.out.print(matrix[i][j] + "   ");
			}
			System.out.print("\n");
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        int[][] matrix = {
        		{3,4,5,0},
        		{1,0,7,2},
        		{3,7,2,9}
        };
        
		System.out.println("********* Before Setting **********");
		printMatrix(matrix);
		//rotateMatrix(matrix, rows, cols);
		setZeros(matrix);
		System.out.println("********** After Setting **********");
		printMatrix(matrix);
        
	}

}
