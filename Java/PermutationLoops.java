package Java;/* package whatever; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;


class Permutation  {

	private final List<String> Permutation  = new ArrayList<String> ();
		
	public List<String> Permutation (){
		return Permutation ;
	}
	
	
	public void generatePermutation(String input){
		populatePermutation(input);
	}
	
	public int [] getCombinationSet(int N){
			int p[] = new int[N+1];
			for ( int i = 0; i<=N; i++)
				p[i] = i ; 
				
				return p; 
	}
	
	public int getJ(int []p, int i){
		if ( i%2!=0) return p[i];
		else return 0;
	}
	
	public String getNextPermutation (String input, int i , int j){
		char [] temp = input.toCharArray();
		char t = temp[i];
		temp[i] = temp[j];
		temp[j] = t; 
		return new String(temp); 
		
	}
	public void populatePermutation(String input){
		
		int j = 0; 
		int N = input.length();
		int p[] = getCombinationSet(N);
		Permutation.add(input);

		int i = 1;
		while ( i<N){
			p[i]--;
			j = getJ(p,i);
			String item = getNextPermutation(input,i,j);
			Permutation .add(item);
			
			i = 1 ; 
			while ( p[i] == 0){
				p[i] = i;
				i++;
			}
			
		}
		
		
		
		
	}
}

/* Name of the class has to be "Main" only if the class is public. */
class PermutationLoops
{
	public static void main (String[] args) throws java.lang.Exception
	{
		Permutation  result = new Permutation ();
		
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		
		String input = r.readLine();
		System.out.println(input);
		result.generatePermutation(input);
		System.out.println(result.Permutation ());
	}
}