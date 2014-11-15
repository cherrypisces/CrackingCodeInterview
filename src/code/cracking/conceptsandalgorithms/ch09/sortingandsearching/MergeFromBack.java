package code.cracking.conceptsandalgorithms.ch09.sortingandsearching;

public class MergeFromBack {

	public static void mergeFromBack(int[] A, int[]B, int n, int m) 
	{
		int index = n + m -1;
		int i = n-1;
		int j = m-1;

		while(i>=0 && j>=0) {
			if(A[i] > B[j]) {
				A[index--] = A[i--];
			} else {
				A[index--] = B[j--];
			}
		}

		while(j>=0) {
			A[index--] = B[j--];
		}

	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
