package code.cracking.conceptsandalgorithms.ch09.sortingandsearching;

import java.util.Stack;

import code.cracking.datastructures.Utilities;

public class SortAlgos {

	public static void swap (int[] array, int i, int j) {
		if(i != j) {
			array[i] = array[i] ^ array[j];
			array[j] = array[i] ^ array[j];
			array[i] = array[i] ^ array[j];
		}
	}
	
	public static void swap2 (int[] array, int i, int j) {
		if(i != j) {
			array[i] = array[j] - array[i];
			array[j] = array[j] - array[i];
			array[i] = array[i] + array[j];
		}
	}
	
	public static void swap3(int[] array, int i, int j) {
		int tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}
	
	public static void bubbleSort(int[] array) {
		boolean swapped = false;
		int length = array.length;
		for(int i=1; i<length-1; i++) 
		{
			swapped = false;
			for(int j=0; j<length-i; j++)
			{
				if(array[j] > array[j+1]) {
					swap(array, j, j+1);
					swapped = true;
				}
			}
			if (swapped == false)
				break;
		}

	}
	
	/*
	 * let heaviest sink down to the bottom
	 */
	public static void bubbleSort2(int[] array) {
		boolean swapped = false;
		int length = array.length;
		for(int t=length-1; t>0; t--) 
		{
			swapped = false;
			for(int j=0; j<t; j++)
			{
				if(array[j] > array[j+1]) {
					swap(array, j, j+1);
					swapped = true;
				}
			}
			if (swapped == false)
				break;
		}

	}
	
	/**
	 * find the smallest element in each scan and put to the front 
	 */
	public static void selectionSort(int[] array) {		
		int min;
		int length = array.length;
		for(int i=0; i<length-1; i++) 
		{
			min = i;
			for(int j=i+1; j<length; j++)
			{
				if(array[j] < array[min])
					min = j;
			}

			if (min != i) {
				swap(array, i, min);
			}
		}	

	}
	
	public static void mergeSort(int[] array, int low, int high) {
		if (low < high) {
			int middle = (low+high)/2;
			mergeSort(array, low, middle);	
			mergeSort(array, middle+1, high);
			merge(array, low, middle, high);
		}
	}

	public static void merge(int[] array, int low, int mid, int high) {
			int[] workArray = array.clone();
			
			int index = low;
			int left = low, right=mid+1;
			while(left <= mid && right<=high) {
				if (workArray[left] <= workArray[right]) {
					array[index] = workArray[left];
					left++;
				} else {
					array[index] = workArray[right];
					right++;
				}
				index++;
			}
			
			while(left <= mid) {
				array[index] = workArray[left];
				left++; 
				index++;
			}
			
			while(right<=high) {
				array[index] = workArray[right];
				right++;
				index++;
			}
	}

	
	public static void quickSort(int[] array, int low, int high) {
		if (low <= high) {
			int pivot= partition(array, low, high);
			quickSort(array, low, pivot-1);
			quickSort(array, pivot+1, high);
		}	
	}

	public static int partition(int[] array, int low, int high) {
		int p = high; 	// last item is chosen to be the pivot
	//	int pivot = array[p];
		
		int less = low - 1;
		for(int i=low; i < high; i++) {
			if(array[i] <= array[p]) {
				swap(array, i, ++less);
			}
		}
		swap(array, less+1, p);
		return less+1;
	}
	
	public static int partition2(int[] array, int low, int high) {
		int p = high; 	// last item is chosen to be the pivot
	
		int seperator = low-1;
		for(int i=low; i < high;i++) {
			if(array[i] < array[p]) {
				seperator++;
				swap(array, i, seperator);
			}
		}
		swap(array, seperator+1, p);
		return seperator+1;
	}

	public static void noRecurQuickSort(int[] array) {
		if(array == null) return;
		else if (array.length < 2) return;
		
		Stack<Integer> stack = new Stack<Integer>();
		
		int low	= 0; 
		int high= array.length-1;		
		stack.push(high);
		stack.push(low);
		
		do {
			low = stack.pop();
			high = stack.pop();
			
			if(low <= high) {
				int pos = partition(array, low, high);
				// solve smaller subproblem first to avoid making stack too deep 
				// but i doubt such thought
				// Reference: https://community.oracle.com/thread/1661642?tstart=0
				if(pos-low <= high-pos) {				
					stack.push(high);
					stack.push(pos+1);	
					
					stack.push(pos-1);
					stack.push(low);
				} else {
					stack.push(pos-1);
					stack.push(low);

					stack.push(high);
					stack.push(pos+1);
				}
			}
			
		} while(!stack.isEmpty());
	}

	public static void bucketSort(int[] array) {
		if (array.length <=0)
			return;
		
		int min = array[0];
		int max = min;
		for(int i=1; i<array.length; i++) {
			if(max < array[i]) {
				max = array[i];
			} else if (min > array[i]) {
				min = array[i];
			}
		}
		int[] buckets = new int[max-min+1];
		for(int i=0; i<array.length; i++) {
			buckets[array[i]-min]++;
		}
		
		int index = 0;
		for(int b=0; b < buckets.length; b++) {
			for(int j=1; j<=buckets[b]; j++)
				array[index++] = b+min;
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int NUM_OF_SORTS = 7;
//		int[] array = Utilities.randomArray(12, 0, 100);
		final int L=1100;
		int[] array = new int[L];
		for(int i=0; i<L; i++)
			array[i] = L-i;
		System.out.println("Before sorting ... ");
		Utilities.printArray(array);
		System.out.println("");
		int [] copy_array = null;
		
		for(int i = 0; i < NUM_OF_SORTS; i++) {
			System.out.println("-----------------------------------");
			switch(i) {
			case 0 :
				copy_array = array.clone(); 
				System.out.println("<Bubble Sort>");
				bubbleSort(copy_array);
				break;
			case 1 :
				copy_array = array.clone(); 
				System.out.println("<Selection Sort>");
				selectionSort(copy_array);
				break;
			case 2 :
				copy_array = array.clone(); 
				System.out.println("<Merge Sort>");
				mergeSort(copy_array, 0, copy_array.length-1);
				break;
			case 3 :
				copy_array = array.clone(); 
				System.out.println("<Quick Sort>");
				quickSort(copy_array, 0, copy_array.length-1);
				break;
			case 4 :
				copy_array = array.clone(); 
				System.out.println("<Bucket Sort>");
				bucketSort(copy_array);
				break;
			case 5:
				copy_array = array.clone(); 
				System.out.println("<Bubble Sort 2>");
				bubbleSort2(copy_array);
				break;
			case 6:
				copy_array = array.clone(); 
				System.out.println("<None Recursive QuickSort>");
				noRecurQuickSort(copy_array);
				break;
			default:
				System.out.println("<Unknown Sort>");
				break;
			}
			
			Utilities.printArray(copy_array);
			System.out.println();
		}
	}

}
