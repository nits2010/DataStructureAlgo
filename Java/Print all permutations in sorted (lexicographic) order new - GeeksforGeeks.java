/* package whatever; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */

class ResultPermutationSet {

	List<String> permutations = new ArrayList<String> ();
	
	public List<String> getPermutations(){
		return permutations;
	}
	

	public void generatePermutation(String input){
		permutations.add(input);
		populatePermutation(input.toCharArray());
	}
	

	private void swap(char [] input, int i , int j){
		char t = input[i];
		input[i] = input[j];
		input[j] = t; 
	}
	
	private int getFirst(char []input){
		int len = input.length;
		for (int i = len-2; i>=0; i--){
			if ( input[i] < input[i+1])
				return i; 
		
		}
		return -1;
	} 
	
	private int getCeilOfFirst ( char []input,int first){
		int len = input.length;
		int ceil = first+1 ;
		for ( int i = ceil+1; i<len; i++)
			if ( input[first] < input[i] && input[i] < input[ceil])
				ceil = i ; 
		return ceil; 
		
	}
	
	private void reverse(char []input, int start, int end){
		if (start>end || start == end) return ; 
		while ( start < end ){
			swap(input,start,end);
			start++;
			end--;
		}
	}
	
	private void populatePermutation(char []input){
		
		int first  = -1 ; 
		int ceil = -1 ; 
		
		while ( true ){
			
			first = getFirst(input); 
			
			//we are done
			if ( first < 0)
				return ;
				
			ceil = getCeilOfFirst(input,first);
			
			swap(input,first,ceil);
			
			reverse(input, first+1, input.length-1);
			
			permutations.add(new String(input));
			
			
			
		}
		
		
		
		
	}	
		
		
	
}

class Ideone
{

	public static void main (String[] args) throws java.lang.Exception
	{
		ResultPermutationSet result = new ResultPermutationSet();
		
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		
		String input = r.readLine();
		System.out.println(input);
		result.generatePermutation(input);
		System.out.println(result.getPermutations());

		// your code goes here
	}
}