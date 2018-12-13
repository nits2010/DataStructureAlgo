/* package whatever; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

class SolutionSet{
		private int i=-1,j=-1,k=-1;
		
		public int getI(){
			return i;
		}
		
		public int getJ(){
			return j;
		}
		
		public int getK(){
			return k;
		}
		
		public boolean findTriplet3(int input[]){
			if (input.length<=2)
				return false;
				
			return FindSortedSubsequenceOfSize3(input);
		}
		
		private boolean hasFound(){
			if (i==-1 || j ==-1 || k ==-1)
				return false;
			return true;
		}
		
		private boolean FindSortedSubsequenceOfSize3(int input[]){
			
			boolean isJFound = false;
			int tempItem = -1;
			int iItem = -1 ; 
			int jItem=-1;
			
			// let first is tempI
			tempItem = input[0];
			
			
			// for rest of the elements
			for ( int i = 1; i<input.length; i++){
				
				//does current element is for I
				if ( input[i] < tempItem){
				//	System.out.println(k + " " + i );
					tempItem = input[i];
				}else if (input[i] == tempItem)
					continue;
				else if (!isJFound || input[i] < jItem ){
					iItem = tempItem;
					jItem = input[i];
					isJFound = true; 
				}else {
					this.i = iItem;
					this.j = jItem;
					this.k = input[i];
				//	System.out.println(k + " " + i );
					break;
				}
				
			}
			
			
			
			
			return hasFound();
		}
		
	public void refresh(){
		i=j=k =-1;
	}
	
}

/* Name of the class has to be "Main" only if the class is public. */
class Ideone
{
	public static void main (String[] args) throws java.lang.Exception
	{
		int arr1[] = {12, 11, 10, 5, 6, 2, 30};
		int arr2[] = {1, 2, 3, 4};
		int arr3[] = {4, 3, 1, 2};
		int arr5[] = {5,13,6,10,3,7,2};
		int arr4[] = {12, 11, 10, 5, 6, 2, 30};
		SolutionSet s = new SolutionSet();
	
		if ( s.findTriplet3(arr5) ) {
			System.out.println("i: " +s.getI() + " j : "+s.getJ() + " k : " + s.getK() ) ; 
		}else {
				System.out.println("not found");
		}
		s.refresh();
		if ( s.findTriplet3(arr4) ) {
			System.out.println("i: " +s.getI() + " j : "+s.getJ() + " k : " + s.getK() ) ; 
		}else {
				System.out.println("not found");
		}
		s.refresh();
		
		if ( s.findTriplet3(arr3) ) {
			System.out.println("i: " +s.getI() + " j : "+s.getJ() + " k : " + s.getK() ) ; 
		}else {
				System.out.println("not found");
		}
		s.refresh();
		
		if ( s.findTriplet3(arr2) ) {
			System.out.println("i: " +s.getI() + " j : "+s.getJ() + " k : " + s.getK() ) ; 
		}else {
				System.out.println("not found");
		}
		s.refresh();
		
		if ( s.findTriplet3(arr1) ) {
			System.out.println("i: " +s.getI() + " j : "+s.getJ() + " k : " + s.getK() ) ; 
		}else {
				System.out.println("not found");
		}
		s.refresh();
		
		
		// your code goes here
	}
}