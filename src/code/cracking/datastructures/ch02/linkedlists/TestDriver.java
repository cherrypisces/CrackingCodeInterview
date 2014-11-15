package code.cracking.datastructures.ch02.linkedlists;

import java.util.Random;

public class TestDriver {

	/*
	 * EXAMPLE
	 *	 Input: (3 -> 1 -> 5) + (5 -> 9 -> 2)
	 *	 Output: 8 -> 0 -> 8
	 */
	public static Node<Integer> addLists(Node<Integer> left, Node<Integer> right, int carry) {
		if (left==null && right==null && carry==0)
			return null;
		
		Node<Integer> resNode = new Node<Integer>(carry);
		int value = carry;
		if(left!=null) {
			value += left.data;
		}
		if(right!=null) {
			value += right.data;
		}		
		resNode.data = value % 10;		
		Node<Integer> more = addLists(left==null ? left : left.next,
				                          right==null? right: right.next,
				                          value >= 10 ? 1 : 0);
		resNode.next = more;
		return resNode;
	}

	/*
	 * EXAMPLE
	 *	 Input:  A -> B -> C -> D -> E -> C [the same C as earlier]
	 *	 Output: C
	 *
	 * Notes:
	 * 	 Assume beginning node is k steps from the head, and the circle is n step lap
	 * 	 fast pointer moves 2 nodes at a time, while slow pointer moves 1 
	 *   so when slow pointer get to the start point, 
	 *   fast one is already k steps ahead, (n-k) steps to the start point
	 * 	 when they meet for the first time: within the circle,
	 *  	fast pointer runs k+2(n-k) steps, and slow moves (n-k) steps, 
	 *      that is k steps left to finish that round. At this time, if we move
	 *      slow point to the head, and make they move at the same speed, they
	 *      will meet finally at the circular entry point.
	 */
	public static Node<Character> findCircleBeginning(Node<Character> head) {
		if (head == null) 
			return null;		
		
		Node<Character> fast = head;
		Node<Character> slow = head;
		
		while(fast.next != null) {	
			slow = slow.next;
			fast = fast.next.next;				
			
			if (fast == slow) {
				System.out.println("They first meet at :" + fast.data);
				break;
			}
				
		}
		
		if (fast.next == null)
			return null;
		
		slow = head;
		while (slow != fast) {
			slow = slow.next;
			fast = fast.next;
		}
		System.out.println("They finally meet at :" + fast.data);
		return fast;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList<Integer> list = new LinkedList<Integer> ();
		Random randomGenerator = new Random();
		int size = 10;
		for(int i=0; i<size; i++) {
			int r = randomGenerator.nextInt(100);
			list.appendToTail(r);
		}
		
		list.appendToTail(71);
	//	System.out.println("The linked list is: ");
	//	System.out.println(list);
		
		list.deleteNode(71);
	//  System.out.println("After deletion: ");
	//	System.out.println(list);
		
		LinkedList<Character> list2 = new LinkedList<Character> ();
	/*	for(int i=0; i<size; i++) {
			char r = (char)(97 + randomGenerator.nextInt(26));
			list2.appendToTail(r);
		} */
		
		list2.appendToTail('F');
		list2.appendToTail('O');
		list2.appendToTail('L');
		list2.appendToTail('L');
		list2.appendToTail('O');
		list2.appendToTail('W');
		list2.appendToTail(' ');
		list2.appendToTail('U');
		list2.appendToTail('P');
		list2.appendToTail('W');
		list2.appendToTail('P');
		list2.appendToTail('L');
		list2.appendToTail(' ');

		System.out.println("////////////////////////////////////////////////");
		System.out.println("The linked list is: ");
		System.out.println("\t" + list2);
		
	//	long start = System.nanoTime();
		list2.removeDuplicates();
	//	System.out.println(System.nanoTime()-start);
		System.out.println("After removing duplicates, the list is: ");
		System.out.println("\t" + list2);
		
		/////////////////////////////////////
		int nth = 5;
		Node n = list2.nthToLast(nth);
		System.out.println("\n////////////////////////////////////////////////");
		LinkedList<Character> l = new LinkedList<Character>(n);
		System.out.println(nth + " to last list is:");
		System.out.println("\t" + l);
		
		/////////////////////////////////////
		System.out.println("\n////////////////////////////////////////////////");
		Node d1 = new Node('s');
		Node d2 = new Node('t');
		list2.appendNode(d1);
		list2.appendNode(d2);
		System.out.println("After appending node, the list is: ");
		System.out.println("\t" + list2);
        list2.deleteMidNode(d1);
        list2.deleteMidNode(d2);
		System.out.println("After deleting last node, the list is: ");
		System.out.println("\t" + list2);
		
		
		/////////////////////////////////////
		System.out.println("\n////////////////////////////////////////////////");
		LinkedList<Integer> left = new LinkedList<Integer> ();
		LinkedList<Integer> right = new LinkedList<Integer> ();
		int l_size = randomGenerator.nextInt(10);
	//	int r_size =  randomGenerator.nextInt(10);
		int r_size = l_size;
		for(int i=0; i<l_size; i++) {
			int t = randomGenerator.nextInt(10);
			left.appendToTail(t);
		}
		for(int i=0; i<r_size; i++) {
			int r = randomGenerator.nextInt(10);
			right.appendToTail(r);
		}
		System.out.println("Left operand: ");
		System.out.println("\t" + left);
		System.out.println("Right operand: ");
		System.out.println("\t" + right);
		Node<Integer> resHead = addLists(left.head, right.head, 0);
		LinkedList<Integer> sum = new LinkedList<Integer>();
		sum.head = resHead;
		System.out.println("Sum list: ");
		System.out.println("\t" + sum);
			
		
		/////////////////Circular Linked List ////////////////////
		System.out.println("\n///////////////////// Circular Linked List ///////////////////////////");
		//int s = randomGenerator.nextInt(10);
		int s = 7;
		LinkedList<Character> circular = new LinkedList<Character>(s);
		Node<Character> a = new Node<Character>('a');
		Node<Character> b = new Node<Character>('b');
		Node<Character> c = new Node<Character>('c');
		Node<Character> d = new Node<Character>('d');
		Node<Character> e = new Node<Character>('e');
		Node<Character> f = new Node<Character>('f');
		circular.appendNode(a);
		circular.appendNode(b);
		circular.appendNode(c);
		circular.appendNode(d);
		circular.appendNode(e);
		circular.appendNode(f);
		f.next = d;
		/*for(int i=0; i<s; i++) {
			char r = (char)(97 + randomGenerator.nextInt(26));
			circular.appendToTail(r);
		}*/
		System.out.println("The circular linked list is: ");
		Node<Character> h = circular.head;
		for(int i=0; i<s; i++) {
			System.out.print(h);
			h = h.next;
		}
		System.out.print("null\n");
		Node<Character> begin = findCircleBeginning(circular.head);
		System.out.println("Circle entry point is: " + begin);
		
		
	}

}
