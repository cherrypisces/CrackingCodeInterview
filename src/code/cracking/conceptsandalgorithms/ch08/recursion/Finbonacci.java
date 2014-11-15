package code.cracking.conceptsandalgorithms.ch08.recursion;

import java.util.Random;
import java.util.Scanner;

public class Finbonacci {
		
	public static long recur_fibonacci(int n)
	{
		if(n == 0)
			return 0;
		else if(n == 1)
			return 1;
		else if(n > 1)
			return recur_fibonacci(n-1) + recur_fibonacci(n-2);
		else 
			return -1;
			
	}
	
	public static long iter_fibonacci(int n)
	{
		if(n < 0)
			return -1;
		else if(n == 0)
			return 0;
		else if(n == 1)
			return 1;
		
		long f0 = 0;
		long f1 = 1;
		int times = 2;
		long fn = f0 + f1;
		while(times <= n) {
			fn = f0+f1;
			f0 = f1;
			f1 = fn;
			times++;
		}
		return fn;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		Random rand = new Random();
		String answer;
		do {
			int n = rand.nextInt(30);
			long fn = iter_fibonacci(n);
			System.out.println(n+ "th Fibonacci number is: "+fn);
			System.out.println("Continue?");
			answer = scan.next();
		} while(!answer.equalsIgnoreCase("no"));
		
		scan.close();
		System.out.println("Out of loop now. Finished!");
	}

}
