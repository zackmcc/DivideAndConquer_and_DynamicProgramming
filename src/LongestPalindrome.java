/* Name: Zackary McClamma
 * Course: CPS 530
 * Date: 22 June 2020
 * Assignment 2 Part A: Longest Palindrome (Dynamic Programming)
 * */


public class LongestPalindrome
{
	/* Index object which stores the length of the palindrome
	   and the start index in the original string. The table in
	   the findSubstring method is constructed of Index objects */
	static class Index
	{
		int start = 0;
		int len = 0;
		
		/* Default constructor is used to fill table with default
		   start and len values of 0 */
		Index()
		{
			this.start = 0;
			this.len = 0;
		}
		
		/* This constructor is for placing Index objects in
		 * the table when a palindrome is found in the orignal
		 * substring */
		Index(int start_in, int len_in)
		{
			this.start = start_in;
			this.len = len_in;
		}
		
	};
	
	public static void main(String[] args)
	{
		String s = "ASFDXXXXXXXXXXJKLIYO";
		
		System.out.println("========McClamma Assignment 2B============\n");
		String subStr = longestPalindrome(s);
		System.out.println("----------Test 1----------");
		System.out.println("Original string: " + s);
		System.out.println("Longest Palindrome: " + subStr);
		System.out.println("----------End Test 1----------\n\n");
		
		
		s = "RACECAR";
		subStr = longestPalindrome(s);
		
		System.out.println("----------Test 2----------");
		System.out.println("Original string: " + s);
		System.out.println("Longest Palindrome: " + subStr);
		System.out.println("----------End Test 2----------\n\n");
		
		s = "FDCBABCDXYX";
		subStr = longestPalindrome(s);
		
		System.out.println("----------Test 3----------");
		System.out.println("Original string: " + s);
		System.out.println("Longest Palindrome: " + subStr);
		System.out.println("----------End Test 3----------\n\n");
		
		s = "aaabaa";
		subStr = longestPalindrome(s);
		
		System.out.println("----------Test 3----------");
		System.out.println("Original string: " + s);
		System.out.println("Longest Palindrome: " + subStr);
		System.out.println("----------End Test 3----------\n\n");
	}
	
	/* This method just checks if the string (s) passed in is a palindrome
	 * if it is then it returns the original string, if it is not then the
	 * findSubstring method is called which searches the input string for 
	 * a palindrome */
	public static String longestPalindrome(String s)
	{
		//Check if entire string is a palindrome
		StringBuilder reverse = new StringBuilder();
		reverse.append(s);
		reverse.reverse();

		if (reverse.toString().contentEquals(s))
			return s;
		
		
		//Check for substrings
		Index values = findSubstring(s);
		
		// return largest palindrome
		return s.substring(values.start, (values.start + values.len));
		
	}
	
	/* This method contains the Dynamic Programming algorithm for finding the largest
	 * palindrome in a string. It starts by initializing a table of Index objects and then
	 * runs through three seperate for loops, the first for loop places a object in the table
	 * for each substring of length 1 (i.e. each letter in the string is its own palindrome)
	 * the next */
	public static Index findSubstring(String s)
	{

		int strLen = s.length();
		int[] subStrIdx = {0, 0}; //Used to store the table indices of the longest palindrome
		Index table[][] = new Index [strLen][strLen];
		
		//Initialize table to avoid NullPointerException
		for(int i = 0; i < strLen-1; i++)
		{
			for(int j = 0; j < strLen-1; j++)
			{
				table[i][j] = new Index();
			}
		}
		
		
		for(int i = 0; i < strLen - 1; i++)
		{
			for (int j = 0; j < strLen - 1; j++)
			{
				if (i == j)
				{
					table[i][i] = new Index(i, 1);
					subStrIdx[0] = i;
					subStrIdx[1] = i;
				}
				if (j == i+1 && s.charAt(i) == s.charAt(j))
				{
					//Add entry to table
					table[i][j] = new Index(i, 2);
					// Update longest palindrome indices for lookup at the end
					subStrIdx[0] = i;
					subStrIdx[1] = j;
				}
					
			}
		}
		
		
		//Substrings greater than 2
		for (int start = 3; start < strLen-1; start++) //defines length of the substring being searched
		{
			for(int i = 0; i < strLen - start + 1; i++) 
			{
				int endIdx = (i + start) - 1;//Calculate ending index using substring length and start index
				
				/* Check if the letters inside the current indices are a palindrome
				   and also check if the two characters at the current indices are
				   equal. */
				if(table[i + 1][endIdx - 1].len != 0 
						&& s.charAt(i) == s.charAt(endIdx))
				{
					// Add entry to table
					table[i][endIdx] = new Index(i, (endIdx - i) + 1);
					// Update longest palindrome indicies for lookup at the end
					subStrIdx[0] = i;
					subStrIdx[1] = endIdx;
				}
			}
		}

		
		return table[subStrIdx[0]][subStrIdx[1]];
	}
	
	
}



