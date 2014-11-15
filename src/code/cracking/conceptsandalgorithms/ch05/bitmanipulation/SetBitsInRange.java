package code.cracking.conceptsandalgorithms.ch05.bitmanipulation;

import java.util.ArrayList;
import java.util.Random;

class BitInteger {
	public static final int INTEGER_SIZE = 32;		
	protected int number = 0;

	public BitInteger(int n) {
		if(n>=0) number = n;						
	}
	 
	public int fetch(int column)
	{
		if ((column>=INTEGER_SIZE) || (column<0) ) {
			return -1;
		} else {
			return (number & (1<<column));
		}
	}
}


public class SetBitsInRange {

	/*
	 * 
	 * Input: N = 10000000000, M = 10101, i = 2, j = 6
	 * Output:N = 10001010100
	 */
	public static int updateBits(int n, int m, int i, int j) 
	{
		int max = ~0;
		
		int left = max - ((1<<j)-1);
		int right = (1<<i)-1;
		int mask = (left | right);
		
		return (n & mask) | (m << i);
		
	}
	
	/*
	 * convert decimal string to binary string
	 */
	public static String printDecimalToBinary(String decimalStr) {
		int intPart = Integer.parseInt(decimalStr.substring(0, decimalStr.indexOf('.')));
		double decPart = Double.parseDouble(decimalStr.substring(decimalStr.indexOf('.'),decimalStr.length()));
		
		String int_Str = "";
		while(intPart > 0) {
			int r = intPart % 2;
			intPart >>= 1;   // intPart = intPart/2
			int_Str = r + int_Str;
		}
		
		final int DEC_LEN = 23 - int_Str.length();
		StringBuffer dec_string = new StringBuffer();
		while(decPart > 0) {
			if(dec_string.length() > DEC_LEN) return "ERROR";
			
			if (decPart == 1) {
				dec_string.append((int)decPart);
				break;
			}
			double r = decPart * 2;
			if (r >= 1) {
				dec_string.append(1);
				decPart = r - 1;
			} else {
				dec_string.append(0);
				decPart = r;
			}
		}		
		return int_Str + "." + dec_string;
	}
	
	/*
	 * 
	 * Reference:
	 * 	  http://www.ruanyifeng.com/blog/2010/06/ieee_floating-point_representation.html
	 * 	  IEEE 754规定，对于32位的浮点数，最高的1位是符号位s，接着的8位是指数E，剩下的23位为有效数字M
	 * 				      对于64位的浮点数，最高的1位是符号位s，接着的11位是指数E，剩下的52位为有效数字M。
	 *  
	 */
	public static String convertToMachineCode(char sign, String binary)
	{
		int exponent = binary.indexOf('.') - 1;
		System.out.println("exponent:" + exponent);
		String e_str = "";
		while(exponent > 0) {
			int e = exponent % 2;
			exponent >>= 1;
			e_str = e + e_str;
		}
		
		while(e_str.length() < 8) {
			e_str = "0" + e_str;
		}
		System.out.println("e_str:" + e_str);
		
		binary = binary.substring(0, binary.indexOf('.')) + 
				 	binary.substring(binary.indexOf('.')+1, binary.length());
		while(binary.length() < 23) {
			binary = "0" + binary;
		}
		
		if(binary.length() > 23) {
			binary = binary.substring(0, 23);
		}
				
		String sign_str = sign=='-' ? "1" : "0";
		return sign_str +  "|" + e_str + "|" + binary;
	}
	
	
	/*********************************************************************************************************************
	 * 
	 * Number Properties Approach for Next Number
	 * Observations:
 	 *		If we “turn on” a 0, we need to “turn off” a 1
	 *		If we turn on a 0 at bit i and turn off a 1 at bit j, the number changes by 2^i - 2^j.
	 *		If we want to get a bigger number with the same number of 1s and 0s, i must be bigger than j.
	 * 
	 * Solution:
	 *		1. Traverse from right to left. Once we’ve passed a 1, turn on the next 0. 
	 *		   We’ve now increased the number by 2^i. 
	 *         Example: xxxxx011100 -> xxxxx111100
	 *		2. Turn off the one that’s just to the right side of that. 
	 *		   We’re now bigger by 2^i - 2^(i-1). 
	 *         Example: xxxxx111100 -> xxxxx101100
	 *		3. Make the number as small as possible by rearranging all the 1s to be as far right as possible: 
	 *		   Example: xxxxx101100 -> xxxxx100011
	 *	
	 * To get the previous number, we do the reverse.
	 * 		1. Traverse from right to left. Once we’ve passed a zero, turn off the next 1. 
	 * 		   Example: xxxxx100011 -> xxxxx000011.
	 *   	2. Turn on the 0 that is directly to the right. 
	 *   	   Example: xxxxx000011 -> xxxxx010011.
	 *		3. Make the number as big as possible by shifting all the ones as far to the left as possible. 
	 *		   Example: xxxxx010011 -> xxxxx011100 .
	 * 
	 ***********************************************************************************************************************/
	// whether number n at bit index is 1 or 0
	public static boolean GetBit(int n, int index)
	{
		return ((n & (1<<index)) > 0);
	}
	
	public static int SetBit(int n, int index, boolean flag)
	{
		if (flag) { // set n at pos index to be 1
			return (n | (1<<index));
		} else {	// set n at pos index to be 0
			return (n & (~(1<<index)));
		}
	}
	
	public static int GetNext_NP(int n)
	{
		if(n<=0) return -1;
		
		int numOfOnes = 0;
		int index = 0;
		
		// Get 1st  '1'
		while(!GetBit(n, index)) index++;
		
		// Get next '0'
		while(GetBit(n, index)) {
			index++;
			numOfOnes++;
		}		
		// turn on next 0:  set '0' -> '1'
		n = SetBit(n, index, true);		
		
		// turn off previous one: set '1' next to prev '0' -> 0
		index--;
		n = SetBit(n, index, false);
		numOfOnes--;
		
		for(int i=index-1; i>= numOfOnes;i--) {
			n = SetBit(n, i, false);
		}
		
		// move all 1s to as left right as possible
		for(int i=numOfOnes-1; i>=0; i--) {
			n = SetBit(n, i, true);
		}
		
		return n;
	}
	
	public static int GetPrev_NP(int n)
	{
		if(n<=0) return -1;
		
		int index = 0;
		int numOfZeros = 0;

		// get 1st '0'
		while(GetBit(n, index)) index++;
		
		// get next '1'
		while(!GetBit(n,index)) {
			index++;
			numOfZeros++;
		}
		// turn off next '1'
		n = SetBit(n, index, false);
		// set '0' next to prev '1' -> '1'
		index--;
		n = SetBit(n, index, true);
		numOfZeros--;
		
		for(int i=index-1; i >= numOfZeros; i--){
			n = SetBit(n, i, true);
		}

		// move all '1's as left as possible
		for (int i=numOfZeros-1; i >= 0; i--){
			n = SetBit(n, i, false);
		}
		
		return n;
	}
	
	
	public static int convertBits(int a, int b)
	{
		int count = 0;
		for(int c=a^b; c != 0; c=c>>1) 
		{
			count += c & 1;
		}
		return count;
	}
	
	
	public static int swapOddEvenBits(int n)
	{
		// [odd  bits] 0xaaaaaaaa: 10101010|10101010|10101010|10101010
		// [even bits] 0x55555555: 01010101|01010101|01010101|01010101
		int odd_bits  = n & 0xaaaaaaaa;
		int even_bits = n & 0x55555555;
		return (odd_bits >> 1) | (even_bits << 1);		
	}
	

	public static int findMissing(ArrayList<BitInteger> array) 
	{
		return findMissing(array, 0);
	}
	
	public static int findMissing(ArrayList<BitInteger> input, int column)
	{
		if(column > BitInteger.INTEGER_SIZE - 1)
			return 0;
		
		ArrayList<BitInteger> zeroIndices = new ArrayList<BitInteger>();
		ArrayList<BitInteger> oneIndices = new ArrayList<BitInteger>();
		for(BitInteger t : input) {
			if(t.fetch(column) == 0){
				zeroIndices.add(t);
			} else {
				oneIndices.add(t);
			}
		}
		
		if(oneIndices.size() >= zeroIndices.size()) {
			return (findMissing(zeroIndices, column + 1) << 1) | 0;
		} else {
			return (findMissing(oneIndices, column + 1) << 1) | 1;
		}
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 1024, m = 21;
		int i = 2, j = Integer.toBinaryString(m).length()-1 + i;
		int res = updateBits(n, m, i, j);
		
		System.out.println("n: " + Integer.toBinaryString(n));
		System.out.println("m: " + Integer.toBinaryString(m));
		System.out.println("Range is: [" + i + ", " + j + "]");
		System.out.println("r: " + Integer.toBinaryString(res));
		
		String decimalNumber = Double.toString(10345.125);
		String toBinary = printDecimalToBinary(decimalNumber);
		System.out.println("\nConvert " + decimalNumber + " to binary: " + toBinary);
		if(toBinary != "ERROR") {
			System.out.println("Machine Code is: " + convertToMachineCode('+', toBinary));
		}
		
		int number = 2435;
		System.out.println("\n"+number);
		int nextNP = GetNext_NP(number);
		System.out.println("Next largest number with same 1 bits of " + number + " is: " + nextNP);
		int prevNP = GetPrev_NP(number);
		System.out.println("Next smallest number with same 1 bits of " + number + " is: " + prevNP);
		System.out.println(number + " to binary is: " + Integer.toBinaryString(number));
		System.out.println(nextNP + " to binary is: " + Integer.toBinaryString(nextNP));
		System.out.println(prevNP + " to binary is: " + Integer.toBinaryString(prevNP));
		
		System.out.println("\nNeed to convert " + convertBits(prevNP, number) + " bits from " + prevNP + " to " + number);	
		System.out.println("Need to convert " + convertBits(number, nextNP) + " bits from " + number + " to " + nextNP);
		
		/////////////////////////////////////////////////////////////////////////////////////////////
		Random randomGen = new Random();
		int numOfNumbers = randomGen.nextInt(100);
		int missingNumber = randomGen.nextInt(numOfNumbers);
		ArrayList<BitInteger> arr = new ArrayList<BitInteger>();	
		System.out.println("\nN: " + numOfNumbers + " (secrete number : " + missingNumber +" )");
		for(int ii=0;ii<numOfNumbers;ii++)
		{
			if (ii == missingNumber)
				continue;
			
			arr.add(new BitInteger(ii));
		}
		int missingFromFunc = findMissing(arr);
		System.out.println("Missing number from func is: " + missingFromFunc);
	}

}
