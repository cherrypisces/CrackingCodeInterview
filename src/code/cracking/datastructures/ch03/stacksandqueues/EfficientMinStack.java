package code.cracking.datastructures.ch03.stacksandqueues;


public class EfficientMinStack extends Stack<Integer> {
	public Stack<Integer> s_mins;
	
	public EfficientMinStack() {
		s_mins = new Stack<Integer>();		
	}
	
	public void push(int value) {
		if (value <= min()) {
			s_mins.push(Integer.valueOf(value));
		}
		super.push(Integer.valueOf(value));
	}
	
	public Integer pop() {
		Integer value = super.pop();
		if (value == min()) {
			s_mins.pop();
		}
		return value;
	}
	
	public Integer min() {
		if (isEmpty()) {
			return Integer.MAX_VALUE;
		} else {
			return s_mins.peek();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		EfficientMinStack s = new EfficientMinStack();
		s.push(2);
		s.push(7);
		s.push(4);
		s.push(1);
		s.push(3);
		s.push(1);
		
		System.out.println(s);
		System.out.println("Current Min value is: " + s.min());
		
		s.pop();
		System.out.println("\nAfter pop : ");
		System.out.println(s);
		System.out.println("Now Min value is: " + s.min());	
		s.pop();
		System.out.println("\nPop again : ");
		System.out.println(s);
		System.out.println("Now Min value is: " + s.min());
		s.pop();
		System.out.println("\nPop once more : ");
		System.out.println(s);
		System.out.println("Now Min value is: " + s.min());
	}

}
