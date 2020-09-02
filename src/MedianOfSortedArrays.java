/* Name: Zackary McClamma
 * Course: CPS 530
 * Date: 22 June 2020
 * Assignment 2 Part A: Median of Two Sorted Arrays (Divide and Conquer)
 * */

import java.util.Arrays;

public class MedianOfSortedArrays {
	public static void main(String[] args)
	{
		int[] arr2 = {3, 5, 7, 8, 13};
		int[] arr1 = {1, 6, 11, 10, 15};
		
		double result = findMedianSortedArrays(arr1, arr2);
		
		System.out.println("========McClamma Assignment 2A============\n");
		System.out.println("------Test 1 even number of elements------");
		System.out.println("Array 1: " + Arrays.toString(arr1));
		System.out.println("Array 2: " + Arrays.toString(arr2));
		System.out.println("The median of the two arrays: " + result);
		System.out.println("-----------------End Test 1---------------\n");
		
		arr1 = new int[] {3, 5, 7, 11, 13};
		arr2 = new int[] {1, 6, 8, 10};
		
		result = findMedianSortedArrays(arr1, arr2);
		
		System.out.println("------Test 2 odd number of elements------");
		System.out.println("Array 1: " + Arrays.toString(arr1));
		System.out.println("Array 2: " + Arrays.toString(arr2));
		System.out.println("The median of the two arrays: " + result);
		System.out.println("-----------------End Test 2---------------\n\n");
		
		arr1 = new int[] {3,6,7,8,9};
		arr2 = new int[] {4,5};
		
		result = findMedianSortedArrays(arr1, arr2);
		
		System.out.println("------------------Test 3------------------");
		System.out.println("Array 1: " + Arrays.toString(arr1));
		System.out.println("Array 2: " + Arrays.toString(arr2));
		System.out.println("The median of the two arrays: " + result);
		System.out.println("-----------------End Test 3---------------\n\n");
		
		arr1 = new int[] {3,6,7,8,10};
		arr2 = new int[] {4,15};
		
		result = findMedianSortedArrays(arr1, arr2);
		
		System.out.println("------------------Test 4------------------");
		System.out.println("Array 1: " + Arrays.toString(arr1));
		System.out.println("Array 2: " + Arrays.toString(arr2));
		System.out.println("The median of the two arrays: " + result);
		System.out.println("-----------------End Test 4---------------\n\n");
		
	}
	
	/* This method finds the median value given two sorted integer arrays as input*/
	public static double findMedianSortedArrays(int[] array1, int[] array2)
	{
		//Calculate combined size of arrays
		int total_size = array1.length + array2.length;
		
		//Calculate k value (middle of the combined arrays
		int k = (total_size +1)/2;
		
		//Initialize start positions of arrays 
		int arr1_start = 0;
		int arr2_start = 0;
		
		// If the total number of elements is odd
		if (total_size % 2 == 1)
		{
			return kthSmallest(k,array1, arr1_start,array2, arr2_start);
		}
		//If the total number of elements is even
		else
		{
			
			return (kthSmallest(k,array1, arr1_start,array2, arr2_start) +
					kthSmallest(k+1,array1, arr1_start,array2, arr2_start-1))/2.0;
		}
	}
	
	
	/* This function contains the Divide and Conquer algorithm for finding the k-th smallest
	 * value in two sorted arrays, given the k value, two arrays, and the index at which to
	 * start searching for each of the arrays*/
	public static double kthSmallest(int k, int[] a, int a_start, int[] b, int b_start)
	{
		//Initialize return value (because compiler will throw error if it isn't)
		double median = 0.0;
		
		
		// compute indicies to search:
		int a_mid = a_start + k/2 -1;
		int b_mid = b_start + k/2 -1;
		

		if (k != 1)
		{
			//decrement k for next iteration
			k = k-k/2;
			
			// If b has the larger value add one to the a index
			if (a[a_mid] <= b[b_mid])
			{
				//If next a index will be out of range return b[b_mid]
				if (a_mid >= a.length-1)
					return b[b_mid];
				median = kthSmallest(k, a, a_mid + 1, b, b_mid);
			}
			// if b has the larger value add one to the b index
			if(a[a_mid] >= b[b_mid])
			{
				if (b_mid >= b.length-1)
					return a[a_mid];
				median = kthSmallest(k, a, a_mid, b, b_mid + 1);
			}
		}
		
		// If k == 1 it is the last iteration and should return the 
		// minimum of the current 'mid' indexes of the two arrays
		if(k == 1)
			return median = Math.min(a[a_mid], b[b_mid]);
		
		// This will return the value from the if statement above
		// when the function returns from the recursive calls
		return median;
		
	}
	
}
