package code.cracking.datastructures.ch03.stacksandqueues;

import java.util.Random;

public class SortingStack {

	public static void sortStack(Stack<Integer> s) {
		if (!s.isEmpty()) {
			int topValue = s.pop();

			sortStack(s);
			
			Stack<Integer> tmp = new Stack<Integer>();
			while (!s.isEmpty() && s.peek() < topValue) {
				tmp.push(s.pop());
			}
			s.push(topValue);
			while (!tmp.isEmpty()) {
				s.push(tmp.pop());
			}
		}
	}
	
	/* 
	 * Reference:
	 * 	 http://stackoverflow.com/questions/4826311/ibm-interview-question-how-to-sort-a-stack
	 */
	public static void sortStack2(Stack<Integer> s) {
		if (!s.isEmpty()) {
			int topValue = s.pop();
			sortStack2(s);			
			insert(s, topValue);
		}
	}
	
	public static void insert(Stack<Integer> s, int value) {
	
		if (!s.isEmpty()) {
			int top = s.peek();
			if (top < value) {
				s.pop();
				insert(s, value);
				s.push(top);
			} 
			else {
				s.push(value);
			}
		} 
		else {
			s.push(value);
		}
		
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random randomGenerator = new Random();
		int numOfNodes = randomGenerator.nextInt(10);
        System.out.println("Will generate " + numOfNodes + " nodes ...");
        Stack<Integer> s = new Stack<Integer>();
    	for(int i=0;i<numOfNodes;i++) {
			int num = randomGenerator.nextInt(50);
	        System.out.println("Push <" + num + ">...");
			s.push(num);
		}	
    			
        System.out.println("\nInitial Stack: ");
        System.out.println(s);
        
        sortStack2(s);        
        System.out.println("\nAfter sorting: ");
        System.out.println(s);
	}

}
