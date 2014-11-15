package code.cracking.datastructures.ch03.stacksandqueues;

import java.util.Random;

import code.cracking.datastructures.ch02.linkedlists.Node;

public class TwoStacksSimOneQueue<T> {
	protected Stack<T> one, another;
	
	public TwoStacksSimOneQueue () {
		one = new Stack<T>();
		another = new Stack<T>();
	}
		
	public void enqueue(T item) {
		one.push(item);
	}
	
	public T dequeue() {
		if (!another.isEmpty())
			return another.pop();
		
		while(!one.isEmpty()) {
			another.push(one.pop());
		}
		return another.pop();
	}

	public String toString() {
		StringBuilder s = new StringBuilder("");
		
		if (!another.isEmpty()) {
			Node<T> p = another.top;
			while (p.next != null) {
				s.append(p.data + "-->");
				p = p.next;
			}
			s.append(p.data);
		}
		
		if (!one.isEmpty()) {
			Node<T> p =	one.bottom;
			while (p != one.top) {
				Node<T> tmp = one.top;
				while(tmp.next != p) {
					tmp = tmp.next;
				}			
				s.append( p.data + "-->");
				p = tmp;
			}
			s.append( p.data );
		}

		return s.toString();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random randomGenerator = new Random();
		int numOfNodes = randomGenerator.nextInt(10);
        System.out.println("Will generate " + numOfNodes + " nodes ...");
		TwoStacksSimOneQueue<Integer> s = new TwoStacksSimOneQueue<Integer>();
		for(int i=0;i<numOfNodes;i++) {
			int num = randomGenerator.nextInt(50);
	        System.out.println("Enqueue <" + num + ">...");
			s.enqueue(num);
		}		
		
        System.out.println("\nInitial Queue: ");
        System.out.println(s);
        
        System.out.println("\nDequeue ...");
        int v = s.dequeue();
        System.out.println("Get value <" + v + ">");
        System.out.println("Now Queue look like: ");
        System.out.println(s);
	}

}
