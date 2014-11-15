package code.cracking.datastructures.ch03.stacksandqueues;

import code.cracking.datastructures.ch02.linkedlists.Node;

public class Queue<T> {

	protected Node<T> front;
	protected Node<T> back;
	
	public T dequeue() {
		if(front != null) {
			T item = front.data;
			front = front.next;
			return item;
		}
		return null;
	}
	
	public void enqueue(T item) {
		Node<T> node = new Node<T>(item);
		if (back != null) {
			back.next = node;
		} else {
			front = node;
		}
		back = node;
	}
}
