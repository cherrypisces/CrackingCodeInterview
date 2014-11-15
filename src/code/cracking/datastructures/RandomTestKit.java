package code.cracking.datastructures;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Random;

public class RandomTestKit {
	private static Random randomGenerator = new Random();
	
	public static void testClass(int bSize, int bNumber, Object obj, 
			String method, Class[] paramTypes, int params )
	{
		if (bSize<=0 || bNumber<=0) {
			System.out.println("Bound underflow for generating random numbers");
			return;
		}
			
		int size = randomGenerator.nextInt(bSize);
		System.out.println("Will generate " + size + " data ...");
		
		try {
			Object[] paramList = new Object[params];
		    for(int i=0;i<size;i++) {
	    		System.out.println("~~~~~~~~~~~" + i + "~~~~~~~~~~");
				ArrayList<Integer> helper = new ArrayList<Integer>();
		    	for(int j=0;j<params;j++) {
		    		int num;
		    		do {
		    			num = randomGenerator.nextInt(bNumber);
		    		} while(helper.contains(num));
		    		
		    		System.out.println("Generating <" + num + ">...");
		    		helper.add(num);
		    		
		    		if (paramTypes[j]==int.class) {
			    		paramList[j] = new Integer(num);		    			
		    		} else if (paramTypes[j]==double.class) {
			    		paramList[j] = new Double(num);	
		    		} else {
		    			Class pc = paramTypes[j];
		    			paramList[j] = pc.newInstance();
		    		}
		    	}
		    	
		        refectionCall(obj, method, paramTypes, paramList);	        
		    }
		} 
		catch (Exception e) {}
		
	}
	
	public static void refectionCall(Object obj, 
			String methodName, Class[] paramTypes, Object[] parameters) 
	{
		try {
			Class cls = obj.getClass();
			Method method = cls.getDeclaredMethod(methodName, paramTypes);
			
			method.invoke(obj, parameters);
			
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
