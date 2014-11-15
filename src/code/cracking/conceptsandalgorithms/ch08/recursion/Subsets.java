package code.cracking.conceptsandalgorithms.ch08.recursion;

import java.util.ArrayList;

import code.cracking.datastructures.Utilities;

public class Subsets {

	public static ArrayList<ArrayList<Integer>> getSubsets(ArrayList<Integer> set) {
		ArrayList<ArrayList<Integer>> s = new ArrayList<ArrayList<Integer>>();

		if (set.size() == 0) {
		//	s.add(set);  // null set
			return s;
		} /*else if (set.size() == 1) {
			s.add(new ArrayList<Integer>());
			s.add(set);
			return s; 
		} */
		else {		
		    int last = set.size()-1;
			Integer item = set.get(last);
			
			ArrayList<Integer> standalone = new ArrayList<Integer>();
			standalone.add(item);
			s.add(standalone);
			
			set.remove(last);
			ArrayList<ArrayList<Integer>> subset = getSubsets(set);
			
			for(int j=0; j<subset.size(); j++) {
				ArrayList<Integer> tmp = subset.get(j);
				s.add(tmp);
				ArrayList<Integer> tmp2 = (ArrayList<Integer>)tmp.clone();
				tmp2.add(item);
				s.add(tmp2);
			}
		}		
		return s;
	}
	
	public static void printSubsets(ArrayList<ArrayList<Integer>> subsets) {
	//	System.out.print("#  ");
		for(int i=0; i<subsets.size(); i++) {
			ArrayList<Integer> subset = subsets.get(i);
			
			if(subset.size() == 0) {
				System.out.print("#  ");
				continue;
			}			
			
			System.out.print("{");
			for(int j=0; j<subset.size(); j++) {
				if (j>=1)
					System.out.print(", ");
				System.out.print(subset.get(j));
			}
			System.out.print("}  ");
		}
		System.out.println();
	}
	
	public static ArrayList<ArrayList<Integer>> iter_getSubsets(ArrayList<Integer> set) {
		ArrayList<ArrayList<Integer>> allSubsets = new ArrayList<ArrayList<Integer>>();
		
		int max = 1 << set.size();
		for(int i=0; i<max; i++) {
			ArrayList<Integer> subset = new ArrayList<Integer>();
			
			int k = i;
			int index = 0;
			while(k>0) {
				if ((k & 1) > 0) {
					subset.add(set.get(index));
				}				
				k >>= 1;
				index++;
			}					
			allSubsets.add(subset);
		}
		
		return allSubsets;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> set = new ArrayList<Integer>();
		int n = Utilities.randomIntInRange(0, 10);
//		int n =3;
		System.out.println("Will get all subsets for integer set with max number of " + n);
		for(int i=1; i<=n; i++) {
			set.add(i);
		}
	//	ArrayList<ArrayList<Integer>> subsets = getSubsets(set);
		ArrayList<ArrayList<Integer>> subsets = iter_getSubsets(set);
		printSubsets(subsets);
	}

}
