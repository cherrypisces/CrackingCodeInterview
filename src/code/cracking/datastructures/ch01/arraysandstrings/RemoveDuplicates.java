package code.cracking.datastructures.ch01.arraysandstrings;

public class RemoveDuplicates {

	/*
	 *  O(n^n)
	 */
	public static void removeDuplicates(char[] str) {
		if(str == null) return;
		
		int len = str.length;
		if (len < 2) return;
		
		int tail = 1;
		
		for (int i = 1; i < len; ++i) {
			int j;
			for (j=0; j < tail; ++j) {
				if (str[i] == str[j]) break;				
			}
			
			if (j == tail) {
				str[tail] = str[i];
				++tail;
			}
		}	
		
		if (tail < str.length) 
		     str[tail] = '\0';
	}
	
	/*
	 *  O(n)
	 */
	public static int myRemoveDuplicates(char[] str) {
		if (str == null) return -1;
		if (str.length < 2) return -1;
		
		int tail = 1;
		long checker = 0;
		checker |= ( (long)1 << str[0] );
		for(int i=1; i < str.length; i++) {
			int val = str[i];
			if ( (checker & ( (long)1 << val)) == 0 )  {  // no duplicates
				str[tail++] = str[i];
				checker |= ((long)1 << val) ; 
			}
		}
		return tail;
	}
	
	/*
	 *  O(n)
	 */
	public static void removeDuplicates2(char[] str) {
		if (str == null) return ;
		if (str.length < 2) return;

		boolean[] hit = new boolean[256];
		
		int tail = 1;
		hit[str[0]] = true;
		for(int i=1; i < str.length; i++) {
			if ( !hit[str[i]]) {
				str[tail++] = str[i];
				hit[str[i]] = true;
			}
		}
		
		if(tail < str.length)
			str[tail] = '\0';
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        char[] nullArray =  null;
        System.out.println("Removing Duplicates: ");
//        removeDuplicates(nullArray);
        
        char[] allDupArray = {'a' , 'a', 'a', 'a'}; 
        System.out.print("All Duplicates ( " + String.valueOf(allDupArray) + " ) --> " );
        System.out.println(  String.valueOf(allDupArray, 0, myRemoveDuplicates(allDupArray)));        
        
        char[] conDupArray = {'a', 'a', 'a', 'b', 'b', 'b'}; 
        System.out.print("Continuous Duplicates ( " + String.valueOf(conDupArray) + " ) --> " );
      //  removeDuplicates(conDupArray);
    //    System.out.println(String.valueOf(conDupArray));
        System.out.println(  String.valueOf(conDupArray, 0, myRemoveDuplicates(conDupArray)));     
        
        char[] noConDupArray = {'a', 'b', 'a', 'b', 'a', 'b', 'a', 'b'};
        System.out.print("No Continuous Duplicates ( " + String.valueOf(noConDupArray) + " ) --> " );
   //     removeDuplicates(noConDupArray);
  //      System.out.println(String.valueOf(noConDupArray));
        System.out.println(  String.valueOf(noConDupArray, 0, myRemoveDuplicates(noConDupArray)));     
        
        char[] noDupArray = {'a', 'b', 'c', 'd', 'x', 'y', 'a', 'x'};
        System.out.print("No Duplicates ( " + String.valueOf(noDupArray) + " ) --> " );
        removeDuplicates2(noDupArray);
        System.out.println(String.valueOf(noDupArray));
  //     System.out.println(  String.valueOf(noDupArray, 0, myRemoveDuplicates(noDupArray)));     

	}

}
