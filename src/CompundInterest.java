
public class CompundInterest {

	static double value(double base, int years, double rate) {
		double balance = 0.0;
		
		for(int i=years; i>=1; i--) {
			balance += base * Math.pow(1+rate, i);			
		}
		return balance;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double v = value(12.0, 20, 0.03) - 12.0*20;
		System.out.println(v);

		int s = 0xFFFFFFFF;
		System.out.println(s);
		s = s >> 8;
		System.out.println(s);
	}

}
