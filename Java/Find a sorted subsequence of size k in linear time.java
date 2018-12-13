/* package whatever; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/*
Logic:
Logic is very simple
actuallY we have first find the minimum value that will be our first element in sequence.
Once we encounter minimum value at index i we need to find greater values for next itmes
greater than previous value
    {12, 11, 10, 5, 6, 2, 30,5,3,32,2,40};
first we found minimum value: 11 then 10 then 5 (since 6 > 5 ); now that will be our first item in sequence

now we find greater value's which is greater to previous one
we have 5 now next is 6 next is 30 next is 32 and 40

Time complexity : O(n) (only one scan )
space complexity : O(1) {Ans array is not count in space complexity of program)
Answer is store in "Ans" array
  */
 
class SolutionSet{
	private int solution[] = null;
	private int k;
	
	SolutionSet(int k){
		this.k = k;
		solution = new int[k];
	}
	
	public int [] getSolution(){
		return solution; 
	}
	
	public boolean findSequenceFoKSize(int input[], int k){
		if(input.length < k){
			return false;
		}
		return findSortedSequenceFoKSize(input, k);
	}
	
	private boolean findSortedSequenceFoKSize(int arr[], int k){
		int Ans_index = 0 ;
		
        this.solution[Ans_index++] = arr[0] ; // by default we assuming that we need n=1 so ans will be arr[0]

        if ( Ans_index == k)
        return true ;

        // if n > 1 then we proceed as below from index 1 to size
        for (int i = 1 ; i<arr.length ; i++)
        { //System.out.println("h: "+Ans_index);
            if ( Ans_index-1 == 0 )// untill at 0th index correct value not inserted we have to insert all element at 0th index
            {
                if ( arr[i]<this.solution[Ans_index-1])
                this.solution[Ans_index-1] = arr[i] ;
                
               
            }
            if ( arr[i] > this.solution[Ans_index-1]) // since at 0th index we have correct value
            {                                   // means we have sorted sequence from 0th to Ans_index-1 so we need to think about
                                            // only element for Ans_index not at Ans_index-1 or below
                this.solution[Ans_index] = arr[i] ;
                 Ans_index++;
              if (Ans_index == k)   // it Ans_index reach to required element then stops
                return true ;
            }

        }
        if ( Ans_index !=k)
        {
            return false ;
        }
        return true;
	}
	
	public void ref(){
			solution = new int[k];
	}
	
	public void print(){
		System.out.println();
		for ( int i =0; i<solution.length; i++)
			System.out.print(solution[i] + " " );
	}
	
	public void inputPrint(int ar[]){
		System.out.println();
		for ( int i =0; i<ar.length; i++)
			System.out.print(ar[i] + " " );
	}
}
/* Name of the class has to be "Main" only if the class is public. */
class Ideone
{
	public static void main (String[] args) throws java.lang.Exception
	{
		int arr1[] = {12, 11, 10, 5, 6, 2, 30,35};
		int arr2[] = {1, 2, 3, 4};
		int arr3[] = {4, 3, 1, 2};
		int arr4[] = {12, 11, 10, 5, 6, 2, 30};
		int arr5[] = {5,13,6,10,3,7,2};
		int arr6[] = {12, 11, 10, 5, 6, 2, 30,5,3,32,2,40}; 
		
		SolutionSet s3 = new SolutionSet(3);
		s3.inputPrint(arr1);
		if (s3.findSequenceFoKSize(arr1,3) ) {
			
			s3.print();
		}else {
			System.out.println("not found");

		}
		s3.ref();
		
		s3.inputPrint(arr2);
		if (s3.findSequenceFoKSize(arr2,3) ) {
			s3.print();
		}else {
					System.out.println("not found");
		}
		s3.ref();
		
			s3.inputPrint(arr3);
		if (s3.findSequenceFoKSize(arr3,3) ) {
			s3.print();
		}else {
					System.out.println("not found");
		}
		s3.ref();
		
			s3.inputPrint(arr4);
		if (s3.findSequenceFoKSize(arr4,3) ) {
			s3.print();
		}else {
				System.out.println("not found");
		}
		s3.ref();
		
			s3.inputPrint(arr5);
		if (s3.findSequenceFoKSize(arr5,3) ) {
			s3.print();
		}else {
				System.out.println("not found");
		}
		s3.ref();
		
			s3.inputPrint(arr6);
		if (s3.findSequenceFoKSize(arr6,3) ) {
			s3.print();
		}else {
					System.out.println("not found");
		}
		s3.ref();
		
		
		
		
		SolutionSet s4 = new SolutionSet(4);

		System.out.println("\nSize 4 \n");
			s4.inputPrint(arr1);
		if (s4.findSequenceFoKSize(arr1,4) ) {
			s4.print();
		}else {
					System.out.println("not found");
		}
		s4.ref();
		
			s4.inputPrint(arr2);
		if (s4.findSequenceFoKSize(arr2,4) ) {
			s4.print();
		}else {
					System.out.println("not found");
		}
		s4.ref();
		
			s4.inputPrint(arr3);
		if (s4.findSequenceFoKSize(arr3,4) ) {
			s4.print();
		}else {
					System.out.println("not found");
		}
		s4.ref();
		
			s4.inputPrint(arr4);
		if (s4.findSequenceFoKSize(arr4,4) ) {
			s4.print();
		}else {
					System.out.println("not found");
		}
		s4.ref();
		
			s4.inputPrint(arr5);
		if (s4.findSequenceFoKSize(arr5,4) ) {
			s4.print();
		}else {
					System.out.println("not found");
		}
		s4.ref();
		
			s4.inputPrint(arr6);
		if (s4.findSequenceFoKSize(arr6,4) ) {
			s4.print();
		}else {
				System.out.println("not found");

		}
		s4.ref();
		
	
		
		// your code goes here
	}
}