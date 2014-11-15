package code.cracking.datastructures.ch03.stacksandqueues;

import java.lang.reflect.Array;

/*
 * 
 * Reference : 
 * 	 http://stackoverflow.com/questions/4770627/how-to-implement-3-stacks-with-one-array/4770793#4770793
 * 
 */
class StackNode<T> {
	T value;
	int prev;
	
	public StackNode(T v, int p) {
		value = v;
		prev = p;
	}
}

public class NStack1ArrayGen<T> {
	private static int CAPACITY = 10;
	
	private StackNode<T>[] storage = null;
	private int freeListTop =0;
	private int numOfStacks = 3;
	private int[] stackPointers = null;
	
	public NStack1ArrayGen(int n) {
		numOfStacks = n;
		stackPointers = new int[numOfStacks];
		for(int i = 0; i < numOfStacks; i++)
			stackPointers[i] = -1;
		
		storage = new StackNode[CAPACITY];
		
		initFreeList();
	}
	
	private void initFreeList() {
		for(int i=0; i < CAPACITY; i++) {
			storage[i] = new StackNode(0, i+1);
		}
	}
	
	public void push(int stackIndex, T value) throws Exception {
		int lastTopIndex = stackPointers[stackIndex];		
		if (lastTopIndex >= CAPACITY)
			throw new Exception("OVER FLOW");

		int freeNodeIndex = getNextFreeNodeIndex();
		storage[freeNodeIndex].value = value;
		storage[freeNodeIndex].prev = lastTopIndex;
		stackPointers[stackIndex] = freeNodeIndex;
	}
	
	public T pop(int stackIndex) throws Exception {
		int currTopIndex = stackPointers[stackIndex];		
		if (currTopIndex == -1) 
			throw new Exception("UNDER FLOW");
		
		T item = storage[currTopIndex].value;
		stackPointers[stackIndex] = storage[currTopIndex].prev;
		freeStackNode(currTopIndex);
		return item;
	}
	
	private int getNextFreeNodeIndex() throws Exception {
		int temp = freeListTop;
		freeListTop = storage[freeListTop].prev;
		return temp;
	}
	
	private void freeStackNode(int index) throws Exception {
		storage[index].prev = freeListTop;
		freeListTop = index;
	}
	
	public String toString() {
		StringBuilder res = new StringBuilder("");
		
		for(int i=0; i < numOfStacks; i++) {
			res.append("Stack " + i + " :" + "\n");
		
			int index = stackPointers[i];
			while (index != -1) {
				res.append("[" + index + "]: " + storage[index].value + "  <--  " );
				index = storage[index].prev;
			}
			res.append("\n");
		}
		
		return res.toString();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		NStack1ArrayGen<Integer> array = new NStack1ArrayGen<Integer>(3);
		array.push(0, 0);
		array.push(1, 1);
		array.push(2, 2);
		array.push(0, 3);
		array.push(1, 4);
		array.push(2, 5);
				
		System.out.println(array);
		
        System.out.println("\nAfter pop from stack 0: ");
		array.pop(0);
		System.out.println(array);

        System.out.println("\nAfter push value 7 into stack 1: ");
		array.push(1, 7);
		System.out.println(array);
		
	}

}
