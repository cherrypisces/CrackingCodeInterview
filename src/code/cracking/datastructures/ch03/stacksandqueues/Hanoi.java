package code.cracking.datastructures.ch03.stacksandqueues;

import java.util.Random;

public class Hanoi {
	public Stack<Integer> first, middle, last;
	int numOfDisks;   // num of disks
	
	public Hanoi() {
		first = new Stack<Integer>("FIRST");
		middle = new Stack<Integer>("MIDDLE");
		last = new Stack<Integer>("LAST");
		
		Random randomGenerator = new Random();
	//	numOfDisks = 1+ randomGenerator.nextInt(10);
		numOfDisks = 4;
		for(int i=numOfDisks; i>0; i--) {
			first.push(i);
		}
		System.out.println("Initial tower is: ");
		System.out.println(first);
	}

	/*
	 *  step 1. move top n-1 disks from first to middle with the help of last
	 *  step 2. move bottom disk from first to last
	 *  step 3. move top (n-1) disks from middle to last with the help of the first
	 */
	public void resolveHanoi(int n, Stack<Integer> f, Stack<Integer> m, Stack<Integer> l) {
		if (n > 0) {
			resolveHanoi(n-1, f, l, m);
			move(f,l);
			resolveHanoi(n-1, m, f, l);
		}
	}
	
	private void move(Stack<Integer> from, Stack<Integer> to) {
		if (!from.isEmpty()) 
		{
//			int disk = from.peek().intValue();
//			if (to.isEmpty() || disk < to.peek().intValue()) {
				Integer disk = from.pop();
				System.out.println("Moving Disk " + disk + " from " + from.name + " to " + to.name + " ...");
				to.push(disk);
//			} else {
//				System.out.println("Error Moving Disk " + disk + " from " + from.name + " to " + to.name + " ...");
//			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Hanoi h = new Hanoi();
		h.resolveHanoi(h.numOfDisks, h.first, h.middle, h.last);
	}

}
