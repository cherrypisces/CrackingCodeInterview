package code.cracking.datastructures.ch03.stacksandqueues;

import java.util.ArrayList;
import java.util.Random;

import code.cracking.datastructures.ch02.linkedlists.Node;

public class SetOfStacks<T> {
	private int capacity;
	public ArrayList<Stack<T>> stacks = new ArrayList<Stack<T>>();

	
	public SetOfStacks(int threshold) {
		capacity = threshold;
	}
	
	public boolean isEmpty() {
	/*	for(int i=stacks.size()-1; i >= 0; i--) {
			if (!stacks.get(i).isEmpty())
				return false;				
		} 
		return true;*/		
		return stacks.size() == 0 ? true : false;
	}
	
	private Stack<T> getLastStack() {
		return isEmpty() ? null : stacks.get(stacks.size() - 1);
	}
	
	public void push(T item) {
		if (isEmpty() || (getLastStack().isFull())) {
			Stack<T> s = new Stack(capacity);
			s.push(item);
			stacks.add(s);
		} else {
			Stack<T> s = getLastStack();
			s.push(item);
		}
	}
	
	public T pop() {
		if (isEmpty()) return null;
		
		T value = getLastStack().pop();
		if (getLastStack().isEmpty())
			stacks.remove(stacks.size()-1); 
		return value;		
	}
	
	public T popAt(int stackIndex){
//		if (stackIndex > (stacks.size()-1))
//			throw new Exception("Stack Index OUT OF BOUND");
		
		return rollover(stackIndex, true);
	}
	
	/*
	 * removeTopOrBottom: true-removeTop, false-removeBottom
	 */
	private T rollover(int stackIndex, boolean removeTopOrBottom) {
		
//		if (stackIndex > (stacks.size()-1) ) 
//			return null;
	
		Stack<T> s = stacks.get(stackIndex);
		T removed_item;
		if (removeTopOrBottom) {
			removed_item = s.pop();
		} 
		else {
			removed_item = s.removeBottom();
		} 
		
		if (stackIndex+1 < stacks.size()) {
			T item  = rollover(stackIndex+1, false);
			s.push(item);
		} else if (s.isEmpty()) {
			stacks.remove(stackIndex);
		}
		
		return removed_item;
	}
	
	
	public String toString() {
		StringBuilder res = new StringBuilder("");
		
		for(int i=0; i < stacks.size(); i++) {
			res.append("****** Stack <" + i + ">" + " ******\n");
		    Stack<T> s = stacks.get(i);
			Node<T> p = s.top;
			while (p != null) {
				res.append(p.data + "  <--  " );
				p = p.next;
			}
			res.append("null\n");
		}
		
		return res.toString();
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random randomGenerator = new Random();
		int numOfNodes = randomGenerator.nextInt(21);
        System.out.println("Will generate " + numOfNodes + " nodes ...");
		SetOfStacks<Integer> s = new SetOfStacks<Integer>(3);
		for(int i=0;i<numOfNodes;i++) {
			int num = randomGenerator.nextInt(50);
			s.push(num);
		}		
		
        System.out.println("\nInitial set of stacks: ");
        System.out.println(s);
        
        int popStackIndex = randomGenerator.nextInt(numOfNodes/s.capacity);
        System.out.println("\nPop stack " + popStackIndex + " ...");
        int v = s.popAt(popStackIndex);
        System.out.println("Get value <" + v + ">");
        System.out.println("Now stacks look like: ");
        System.out.println(s);
        
        System.out.println("\nPop the whole stack ...");
        v = s.pop();
        System.out.println("Get value <" + v + ">");
        System.out.println("Now stacks look like: ");
        System.out.println(s);
        
	}

}
