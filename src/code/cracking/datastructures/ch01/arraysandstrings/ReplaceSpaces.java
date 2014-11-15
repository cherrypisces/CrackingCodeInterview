package code.cracking.datastructures.ch01.arraysandstrings;

public class ReplaceSpaces {

	public static String replaceSpaces(String str)
	{
		StringBuilder s = new StringBuilder();
		for(int i=0; i < str.length(); i++)
		{
			if (str.charAt(i) == ' ')
				s.append("%20");
			else
				s.append(str.charAt(i));
		}
		return s.toString();
	}
	
	public static void replaceFun(char[] str, int length)
	{
		int space_counter=0;
		for (int i=0; i < length; i++)
		{
			if (str[i] == ' ')
				space_counter++;
		}
		
		int newlength = length + space_counter * 2;
		str[newlength] = '\0';
		for (int i=length - 1; i >=0 ; i--)
		{
			if (str[i] == ' ') {
				str[newlength - 1] = '0';
				str[newlength - 2] = '2';
				str[newlength - 3] = '%';
				newlength -= 3;
			} else {
				str[newlength - 1] = str[i];
				newlength--;
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "I can     be          better!";
        System.out.println( "After replacing spaces, " + s + " is becoming  " + replaceSpaces(s));
  //      char[] array = s.toCharArray();
   //     replaceFun(array, array.length);
  //      System.out.println( "After using C-style array replacing, " + s + " is becoming  " + array);
	}

}
