package code.cracking.conceptsandalgorithms.ch08.recursion;

import code.cracking.datastructures.Utilities;

public class CombParenthese {

	public static void printValidParenthsis(int l, int r) {
		if(l==0 && r==0)
			return;
		
		if(l < r) {
			printValidParenthsis(l-1, r);
			System.out.print(")");
		} 
	
		if (r == 1) {
			printValidParenthsis(l, r-1);
			System.out.print(")");
		} 
	}
	
	
	public static void printPar(int l, int r, char[] str, int count)
	{
		if(l<0 || r<l) return;
		if(l==0 && r==0) {
			System.out.println(str);
		} else {
			if(l > 0) {
				str[count] = '(';
				printPar(l-1, r, str, count+1);
			}
			
			if(r > l) {
				str[count] = ')';
				printPar(l, r-1, str, count+1);
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	//	int n = Utilities.randomIntInRange(0, 4);
		int n = 3;
		System.out.println("All valid combinations of "+ n + "-pairs of parentheses are: \n");
//		printValidParenthsis(n,n);
		
		char[] str = new char[n*2];
		printPar(n, n, str, 0);
	}

}
