package code.cracking.datastructures.ch01.arraysandstrings;

public class StringTest {

	public static String makeSentence(String[] words) {
		StringBuffer sentence = new StringBuffer();
		for (String w : words)  sentence.append(w);
		return sentence.toString();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] words = new String[]{"I ", "believe ", "I ", "can ", "make ", "it","!"};
		System.out.println( makeSentence(words) );
	}

}
