package code.cracking.datastructures.ch01.arraysandstrings;

public class Anagram {

	public static boolean isReverse(final String s, final String t) 
	{
		if (s.length() != t.length())
			return false;
		
		for(int i=0, j=t.length()-1; i<s.length() && j >=0; i++, j--)
		{
			if (s.charAt(i) != t.charAt(j))
				return false;
		}
		
		return true;
	}
	
	public static boolean anagram(final String s, final String t)
	{		
		if (s.length() != t.length())
			return false;
		
		int[] letters = new int[256];
		int num_unique_chars = 0;
		int num_completed_t = 0;
		
		char[] s_array = s.toCharArray();
		for(char c: s_array) {
			if (letters[c] == 0)
				++num_unique_chars;			
			++letters[c];
		}
		
		for(int i=0 ; i < t.length(); i++) {
			int c = t.charAt(i);
			if (letters[c] == 0)
				return false;
			--letters[c];
			if (letters[c] == 0) {
				++num_completed_t;
				if (num_completed_t == num_unique_chars)
					return i == t.length() - 1;				
			}
		}
		
		return false;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
         String s = "euwosda";
         String t = "aosdwue" ;
         
         System.out.println("Anagrams or not ? " + anagram(s, t) );
	}

}
