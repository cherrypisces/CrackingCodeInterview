package code.cracking.conceptsandalgorithms.ch08.recursion;

import java.util.ArrayList;

import code.cracking.datastructures.Utilities;

public class Permutations {

	public static ArrayList<String> getPermutations(String str) {
		if (str == null)
			return null;
		
		ArrayList<String> perms = new ArrayList<String>();
		int length = str.length() ;		
		if(length ==0) {
			perms.add("");
		}
		else if (length == 1) {
			perms.add(str);
		} 
		else if (length > 1){		
			char last = str.charAt(str.length()-1);
			ArrayList<String> subPerms = getPermutations(str.substring(0, length-1)); 
			for(int i=0; i<subPerms.size(); i++) {
				String buffer = subPerms.get(i);
				perms.add(last+buffer);				
				for(int j=1; j<buffer.length(); j++) {
					perms.add(buffer.substring(0, j) + last + buffer.substring(j, buffer.length()));
				}
				perms.add(buffer+last);
			}
		}
		
		return perms;
	}
	
	public static void printAllPerms(ArrayList<String> perms) {
		//System.out.println("Printing all permutations ...");
		for(int i=0; i<perms.size(); i++) {
			String tmp = perms.get(i);
			System.out.print(i+": (");
			for(int j=0; j<tmp.length();j++) {			
				System.out.print(tmp.charAt(j));	
				if(j<tmp.length()-1)
					System.out.print(",");
			}
			System.out.println(") ");
		}
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	//	int n = Utilities.randomIntInRange(0,25);
		int n=3;
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<=n; i++) {
			sb.append((char)('a' + i));
		}
		String s = sb.toString();
		
		System.out.println("Generating permutations for \""+s + "\" ....\n");
		ArrayList<String> perms = getPermutations(s);
		
		printAllPerms(perms);
	}

}
