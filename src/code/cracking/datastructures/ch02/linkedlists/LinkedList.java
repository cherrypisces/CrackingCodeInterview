package code.cracking.datastructures.ch02.linkedlists;

public class LinkedList <T>{
	protected Node<T> head = null;
	public int size = 0;
	
	public LinkedList() { }
	
	// circular linked list
	public LinkedList(int size) {
		this.size = size;
	}
	
	public LinkedList(Node<T> h) {
		head = h;
	}
	
	public void appendToTail(T d) {
		Node<T> end = new Node<T>(d);	
        appendNode(end);
	}
	
	public void appendNode(Node<T> d) {
		if (d==null) return;		
		if (head == null) {
			head = d;
			return;
		}
		Node<T> n = head;
		while(n.next != null) { n = n.next; }		
		n.next = d;
	}
	
	public void deleteNode(T d) {
		Node<T> n = head;
		if (n.data == d) {
			head = head.next;
			return;
		}
		
		while(n.next != null) {
			if (n.next.data == d) {
				n.next = n.next.next;
				return;
			}
			n = n.next;
		}
	}
	
	/*
	 *  O(n2)
	 */
	public void removeDuplicates() {
		if (head!=null && head.next!=null) {
			Node<T> n = head;
			while (n.next!=null) {
				//System.out.println("Testing ---- " + n.next.data);
				Node<T> tmp = head;
				while(tmp != n.next) {
					if (tmp.data == n.next.data) break;
					tmp = tmp.next;
				}				
				if (tmp!=n.next) {  // find duplicates
					n.next = n.next.next;					
					continue;
				}
				
				n=n.next;
			}
		}
	}
	
	/*
	 *	O(n2)
	 */
	public void removeDuplicates2() {
		if (head == null) return;
		
		Node<T> prev = head;
		Node<T> curr = head.next;
		while (curr != null) {
			Node<T> runner = head;
			while (runner != curr) {	// check for ealier dups
				if (runner.data == curr.data) {
					Node<T> tmp = curr.next;	// remove current
					prev.next = tmp;
					curr = tmp;			// update curr to next node
					break;		// all other dups have been removed
				}
				runner =runner.next;
			}
			if (runner == curr) {		// curr not updated, update now
				prev = curr;
				curr = curr.next;
			}
		}
	}
	
	public Node<T> nthToLast(int n) {
		if(head == null || n < 1)
			return null;
		
		Node<T> p1 = head;
		Node<T> p2 = head;
		for(int j=1;j<n;j++){
			if(p2.next == null) 
				return null;
			p2 = p2.next;
		}
		
		while(p2.next != null) {
			p1 = p1.next;
			p2 = p2.next;
		}
		
		return p1;
	}
	
	public void deleteMidNode(Node<T> n) {
		if(n==null || n.next==null) return;
		
		n.data = n.next.data;
		n.next = n.next.next;
	}
	
	public String toString() {
	    String s = "";
		Node<T> n = head;
		while (n != null) { 
			s += n; 
			n=n.next;
	    }
		s += "null";
		return s;
	}
}
