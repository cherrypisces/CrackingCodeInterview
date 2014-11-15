package code.cracking.datastructures.ch01.arraysandstrings;

public class UniqueCharacter {

	/*
	 *   O(n2) but no additional space
	 */
	public static boolean hasDupCharacters(final String str) {		
		for(int i=0; i < str.length() -1; i++) {
			for(int j=i+1;j < str.length(); j++) {
				if ( str.charAt(i) == str.charAt(j)) 
					return true;
			}
		}		
		return false;
	}
	
	/*
	 *  extended ASCII table: 256  characters : 256bits space = 128bytes are used
	 *  O(n)
	 */
	public static boolean isUniqueCharacters(final String str) {
		boolean[] char_set = new boolean[256];		
		for (int i=0; i < str.length(); i++) {
			int val = str.charAt(i);
			System.out.println("int value of char \'" + str.charAt(i) + "\' is " + val );
			if (char_set[val]) 
				return false;
			char_set[val] = true;
		}
		return true;
	}
	
	/*
	 *  bit vector :  'a' - 'z'
	 *  O(n)
	 */
	public static boolean isUniqueChars(final String str) {
		int checker = 0;		
		for (int i=0; i < str.length(); i++) {
			int val = str.charAt(i) - 'a';
			System.out.println( " 1 << " + val + " is " + (1<<val));
			if ( ( checker & (1 << val) )  > 0 )  
				return false;
			checker |= (1 << val);
		}
		return true;
	}
	
	
	/*
	 *  Turn an integer into 0's and 1's
	 */
	public static String intToBinary(int theInt) {
		StringBuilder s= new StringBuilder();
		
		for ( int i=31; i>=0; i--) {
			if ( (theInt & (1 << i) ) >0 ) {
				s.append("1");
			} else {
				s.append("0");
			}
		}		
		return s.toString();
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "xdlsaqipqiepwqiep";
		
		System.out.println( "Has unique characters or not ? " + isUniqueCharacters(str) );
		
		System.out.println( "Has unique characters or not ? " + isUniqueChars(str) );
		
		System.out.println("int to binary : (12345) --> " + intToBinary(12345));
		System.out.println(Integer.toBinaryString(12345));
	}

}
