package code.cracking.datastructures.ch03.stacksandqueues;

import code.cracking.datastructures.ch02.linkedlists.Node;

public class Stack<T> {
	public Node<T> top = null;
	public Node<T> bottom = null;
	public int capacity = 100;
	public int size = 0;
	public String name = "";
	
	public Stack() {}
	public Stack(String n ) { name = n;}
	
	public Stack(int threshold) {
		capacity = threshold;
	}
	
	public boolean isFull() {
		return size == capacity ? true : false;
	}
   
	public void push(T item) {
		Node<T> node = new Node<T>(item);		
		if (isEmpty()) {
			bottom = node;
		}
		node.next = top;
		top = node;
		size++;
	}
	
	public T pop() {
		if (!isEmpty()) {
			T item = top.data;
			if (top == bottom) {
				bottom = null;
			}
			top = top.next;
			size--;
			return item;
		}
		
		return null;
	}
	
	public T peek() {
		if (!isEmpty()) {
			T value = top.data;
			return value;
		}
		
		return null;
	}
	
	public T getBottom() {
		return isEmpty() ? null : bottom.data;		
	}
	
	public T removeBottom() {
		if (isEmpty()) return null;
		
		T item = bottom.data;
		size--;
		
		if (isEmpty()) {
			bottom = null;
			top = null;
		} else {
			Node<T> p = top;
			while(p.next != bottom) {
				p = p.next;
			}
			bottom = p;
			p.next = null;
		}
		return item;
	}
	
	public boolean isEmpty() {
		return  size==0 ;
	}
	
	public String toString() {
		StringBuilder res = new StringBuilder("<Stack> \n");
		
		Node<T> n = top;
		while(n != null) {
			res.append(n);
			n = n.next;
		}
		res.append("null");
		
		return res.toString();
	}
	
}
