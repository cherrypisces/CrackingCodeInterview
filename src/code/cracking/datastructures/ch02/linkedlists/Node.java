package code.cracking.datastructures.ch02.linkedlists;

public class Node <T>{	
	public T data;
	public Node<T> next = null;
	
	public Node(T d) {
		data = d;
		next = null;
	}
	
	public String toString() {
		return  data + " --> ";
	}

}
