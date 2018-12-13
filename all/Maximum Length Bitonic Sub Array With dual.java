/* package whatever; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

//Dual {20,4,5,6,7,6,3,2,20,30,40,50,30,20,10,5,4} either start with increasing or decreasing 
//non-dual:  {20, 4, 1, 2, 3, 4, 2, 10} : start with increasing only

//MaximumLengthBitonicSubarray
class MLBitonicSA{
	private int i;
	private int j;
	private int length = 0; 
	private int input[];
	private boolean dualBitonic = false;  
	
	public int getI(){
		return i;
	}
	
	public int getJ(){
		return j;
	}
	
	public int getLength(){
		return length;
	}
	
	public void setInput(int in[]){
		this.input = in; 
	}
	
	public boolean MaximumLengthBitonicSubarray(int input[], boolean dual){
		if ( input.length == 0)
			return false; 
		this.dualBitonic = dual; 
		setInput(input);
		return calculateMaximumLengthBitonicSubarray();
	}
	
	public void print(){
		System.out.print("\n");
		for ( int i =0; i<input.length; i++)
			System.out.print(input[i] + " " );
	}
	
	//false if no subarray exist otherwise true 
	private boolean calculateMaximumLengthBitonicSubarray(){
		boolean found = false; // does any BSA found
		boolean directionChange = false; //does direction change increase to decrease 
		int countOfChange = 0;
		int i=0;
		
		if(this.dualBitonic == false){
			//find the first point from where BSA can start
			for (  i=0; i<input.length-1; i++){
				if ( input[i+1] > input[i])
					break; 
			}
			if ( i==input.length-1)
				return false;
		}
		
		directionChange = false;
		int start = i; 
		i++;
		for ( ; i<input.length; i++){
			if(countOfChange!=0 && countOfChange%2 == 0){
				if ( this.length < (i-2-start+1) ) {
					this.i = start;
					this.j = i-2;
					this.length = this.j - this.i +1; 
				}
				start = i-2; 
				countOfChange=0;
			}
			
			if (input[i] > input[i-1]){
				if ( directionChange == true){
					countOfChange++; 
				}	
					directionChange = false;
			}
			if (input[i] < input[i-1]){
				if ( directionChange == false){
					countOfChange++; 
				}
					directionChange = true;
			}
			
				
		}
		
		if (directionChange == true){
			if ( this.length < (i-1-start+1) ) {
					this.i = start;
					this.j = i-1;
					this.length = this.j - this.i +1; 
				} 
		}
		return directionChange;
	}
	
	public void refersh(){
		i = -1; 
		j = -1; 
		length = 0;
	}
}

/* Name of the class has to be "Main" only if the class is public. */
class Ideone
{
	public static void main (String[] args) throws java.lang.Exception
	{
		MLBitonicSA item = new MLBitonicSA();
		int input[] = {12,4,78,90,45,23,78,122,136,24,22,27,29,34,85,65,12,10,1};
		int input2[] = {1,2,3,4,5,6,7,8,9};
		int input3[] = {1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1};
		int input4[] = {1,1,1,1,1,1,1,1};
		int input5[] = {9,8,7,6,5,4,3,2,1};
		int input6[] = {20,4,5,6,7,6,3,2,20,30,40,50,30,20,10,5,4,6,7,8,9,10,11,12,13,10,9,8,7,6,5,4,3,2,1};
		int input7[] = {20,4,5,6,7,6,3,2,20,30,40,50,30,20,10,5};
		int input8[] = {20,4,5,6,7,6,3,2};
		
		if ( item.MaximumLengthBitonicSubarray(input,false) ) {
			item.print();
			System.out.println(" start : " +item.getI() + " end : "+item.getJ() + " length: "+item.getLength());
		}else{item.print();
			System.out.println("Not found");
		}
		item.refersh();
	
		if ( item.MaximumLengthBitonicSubarray(input2,false) ) {
			item.print();
			System.out.println(" start : " +item.getI() + " end : "+item.getJ() + " length: "+item.getLength());
		}else{item.print();
			System.out.println("Not found");
		}
		item.refersh();
		
		if ( item.MaximumLengthBitonicSubarray(input3,false) ) {
			item.print();
			System.out.println(" start : " +item.getI() + " end : "+item.getJ() + " length: "+item.getLength());
		}else{item.print();
			System.out.println("Not found");
		}
		item.refersh();
		
		if ( item.MaximumLengthBitonicSubarray(input4,false) ) {
			item.print();
			System.out.println(" start : " +item.getI() + " end : "+item.getJ() + " length: "+item.getLength());
		}else{item.print();
			System.out.println("Not found");
		}
		item.refersh();
		
		if ( item.MaximumLengthBitonicSubarray(input5,false) ) {
			item.print();
			System.out.println(" start : " +item.getI() + " end : "+item.getJ() + " length: "+item.getLength());
		}else{
			item.print();
			System.out.println("Not found");
		}
		item.refersh();
		
		if ( item.MaximumLengthBitonicSubarray(input6,true) ) {
			item.print();
			System.out.println(" start : " +item.getI() + " end : "+item.getJ() + " length: "+item.getLength());
		}else{
			item.print();
			System.out.println("Not found");
		}
		item.refersh();
		
		
		if ( item.MaximumLengthBitonicSubarray(input7,true) ) {
			item.print();
			System.out.println(" start : " +item.getI() + " end : "+item.getJ() + " length: "+item.getLength());
		}else{
			item.print();
			System.out.println("Not found");
		}
		item.refersh();
		
			if ( item.MaximumLengthBitonicSubarray(input8,true) ) {
			item.print();
			System.out.println(" start : " +item.getI() + " end : "+item.getJ() + " length: "+item.getLength());
		}else{
			item.print();
			System.out.println("Not found");
		}
		item.refersh();

	}
}