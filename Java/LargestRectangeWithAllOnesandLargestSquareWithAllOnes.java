package Java;/* package whatever; // don't place package name! */

//source https://www.youtube.com/watch?v=E5C5W6waHlo
import java.util.*;
import java.lang.*;
import java.io.*;

class HightWidth{
	private int i=-1, j=-1; 
	private int width = 0;
	private int hight = 0;
	
	public void setWidth(int width){
		this.width = width;
	}

	public void setHight(int hight){
		this.hight = hight;
	}
	
	public int getWidth(){
		return width;
	}

	public int getHight(){
		return hight;
	}
	
	public void setI(int i){
		this.i = i;
	}

	public void setJ(int j){
		this.j = j;
	}
	
	public int getI(){
		return i;
	}

	public int getJ(){
		return j;
	}
	
	
	

}

class LargestRectangeWithAllOnes{
	
	private int area = 0;
	private int result[][]= null;
	private int width;
	private int hight;
	private int startI, startJ; 
	private int endI, endJ;
	
	public int getArea(){
		return this.area;
	}
	
	public void setArea(int area){
		this.area = area;
	}
	
	public int [][] getResult(){
		return result;
	}
	
	public void print(int auxilaryArray[][]){
		System.out.println("");
		for ( int i=0; i<auxilaryArray.length; i++){
			for ( int j = 0; j<auxilaryArray[0].length; j++)
				System.out.print (auxilaryArray[i][j] + " ");
			System.out.println("");
		}
	}
	
	public void getRectangleWithAllOnes(int input[][]){
		if (input!=null || input.length !=0)
			calculateRectangleWithAllOnes(input);
	}
	
	private void createHistogram(int input[][]){
		
		for (int i=1; i<input.length; i++){
			for ( int j = 0; j<input[0].length; j++){
				if (input[i][j] ==1 )
					input[i][j] += input[i-1][j];
			}
		}
		
		//print(input);
		System.out.println();
	}
	
	private int max(int a, int b){
		return a>b?a:b;
	}
	
	//https://www.youtube.com/watch?v=E5C5W6waHlo
	public HightWidth histoArea(int input[], int row){
		HightWidth hw = new HightWidth();
		Stack<Integer> hights = new Stack<Integer> ();
		Stack<Integer> indexs = new Stack<Integer> ();
		
		hights.push(input[0]);
		indexs.push(0);
		
		
		int lastindex = 0;
		int carea;
		int maxArea = 0;
		int hight,width;
		for ( int i = 1; i<input.length; i++){
			
			//case 1: The current element hight is more than top element hight of stack, push it
			if (hights.isEmpty() || hights.peek() < input[i] ){
				hights.push(input[i]);
				indexs.push(i);
			}else if(hights.peek() > input[i] ) {  //case 3: if the current element hight is less then the top element hight, calculate histogram
				lastindex = 0; 
				while ( !hights.isEmpty()  && hights.peek() > input[i]){
					lastindex = indexs.pop();
					hight = hights.pop();
					width =  i - lastindex;
					carea = hight * width;
					maxArea = getMaxArea(row, hw, lastindex, carea, maxArea, hight, width);
				} 
				
				hights.push(input[i]);
				indexs.push(lastindex);
			}
			
		}
		
		//process left itmes 
		while (!hights.isEmpty() ){
			lastindex = indexs.pop();
			hight = hights.pop();
			width = input.length - lastindex;
			carea = hight*width;
			maxArea = getMaxArea(row, hw, lastindex, carea, maxArea, hight, width);

		}
		
		
		return hw;
	}

	private int getMaxArea(int row, HightWidth hw, int lastindex, int carea, int maxArea, int hight, int width) {
		if ( carea > maxArea){
				maxArea = carea;
				hw.setI(row);
				hw.setJ(lastindex);
				hw.setWidth(width);
				hw.setHight(hight);
		}
		return maxArea;
	}

	private int [][] getCopy(int input[][]){
		int temp[][] = new int [input.length][input[0].length];
		for ( int i = 0; i<input.length; i++)
			for ( int j = 0; j<input[0].length; j++)
				temp[i][j] = input[i][j];
		return temp;
	}
	
	private void calculateRectangleWithAllOnes(int input[][]){
		int i=0,j=0,width=0,hight=0;
		
		int temp[][] = getCopy(input);
		//print(temp);
		createHistogram(input);
		HightWidth hw = null;
		for (int k=0; k<input.length; k++){
			hw = histoArea(input[k], k);
		
			if ( this.area < hw.getWidth()*hw.getHight()){
				i = hw.getI();
				j = hw.getJ();
				width = hw.getWidth();
				hight = hw.getHight();
				
				this.area = width*hight;
				
				//System.out.println (" i: " +i + " j " + j + " width " + width + " hight " + hight + " area " + this.area ); 
			}
			
			
		}
		
		this.startI = i-hight + 1;
		this.startJ = j;
		this.endI = i;
		this.endJ = j + width -1; 
		this.width = width;
		this.hight = hight;
	
		createResultMatrix(temp);
	}
	
	public void createResultMatrix(int input[][]){
	
		if ( this.area!=0){
			this.result = new int [this.hight][this.width];
			for ( int t=startI; t<=endI; t++)
				for ( int p = startJ; p<=endJ; p++)
					this.result[t-startI][p-startJ] = input[t][p];
		}		
		
	}
}


class LargestSquareWithAllOnes{
	

	private int size = 0;
	private int result [][] = null; 

	public void setSize(int size){
		this.size = size;
	}
	
	public int getSize(){
		return this.size;
	}
	
	public int [][] getResult(){
		return this.result;
	}
	
	public void getSquareWithAllOnes(int input[][]){
		if (input!=null || input.length != 0) 
			calculateMaxSquareWithOnes(input);
	}
	
	private int [][] getAuxilaryArrayWithFirstRowCol(int input[][]){
		int auxilaryArray[][] = new int [input.length][input[0].length] ; 
		
		for (int i=0; i<input[0].length; i++ ) 
			auxilaryArray[0][i] = input[0][i];
		
		for (int i=0; i<input.length; i++ ) 
			auxilaryArray[i][0] = input[i][0];
		
		//print(auxilaryArray);
		return auxilaryArray;
			
	}
	
	public void print(int auxilaryArray[][]){
		System.out.println("");
		for ( int i=0; i<auxilaryArray.length; i++){
			for ( int j = 0; j<auxilaryArray[0].length; j++)
				System.out.print (auxilaryArray[i][j] + " ");
			System.out.println("");
		}
	}
	
	int min(int a, int b, int c){
	  int m = a;
	  if (m > b) 
	    m = b;
	  if (m > c) 
	    m = c;
	  return m;
	}
	
	private void calculateMaxSquareWithOnes ( int input[][]){
		 int startI = -1;
		 int startJ = -1;
		 int endI = -1;
		 int endJ = -1;
		// get Auxilary Arra yWith First Row & Col data 
		int auxilaryArray[][] = getAuxilaryArrayWithFirstRowCol(input) ; 
		
		// for rest of rows and column's
		for ( int i = 1; i<input.length; i++){
			for ( int j = 1; j<input[0].length; j++){
				
				if (input[i][j] == 1){
					auxilaryArray[i][j] = min(auxilaryArray[i-1][j],auxilaryArray[i][j-1], auxilaryArray[i-1][j-1]) + 1;
					if (this.size < auxilaryArray[i][j] ){ 
						this.size = auxilaryArray[i][j];
						endI = i;
						endJ = j;
						startI = i-this.size+1;
						startJ = j-this.size+1 ; 
					}
				}else 
					auxilaryArray[i][j]  = 0;  
			}
		}
		
	//	print(auxilaryArray);
		if ( this.size!=0){
			result = new int [this.size][this.size];
			for ( int i=startI; i<=endI; i++)
				for ( int j = startJ; j<=endJ; j++)
					result[i-startI][j-startJ] = input[i][j];
		}
	}
}


/* Name of the class has to be "Main" only if the class is public. */
class LargestRectangeWithAllOnesandLargestSquareWithAllOnes
{
	private static void largestSquare(int input[][]){
		 LargestSquareWithAllOnes square = new LargestSquareWithAllOnes();
        square.getSquareWithAllOnes(input);
        
        System.out.println("size : "+square.getSize());
        if (square.getSize()!=0)
        	square.print(square.getResult());
        else 
        	System.out.println("No square available");
        
	}
	
	private static void largestRectangle(int input[][]){
		 LargestRectangeWithAllOnes rectangle = new LargestRectangeWithAllOnes();
    	 rectangle.getRectangleWithAllOnes(input);
         System.out.println("area : "+rectangle.getArea());
       	 rectangle.print(rectangle.getResult());
   
	}
	
	
	
	public static void main (String[] args) throws java.lang.Exception
	{
		 int input[][] =  {	{0, 0, 0, 0, 1, 0  }, 
                   			{0, 0, 1, 0, 0, 1}, 
                   			{0, 1, 1, 1, 1, 0 },
                   			{1, 1, 1, 1, 1, 0},
                   			{0, 1, 1, 1, 1 ,1},
                   			{0, 1, 1, 1, 1, 0}};
                   			
          int input2[][] = {{0, 1, 1, 0, 1}, 
                   {1, 1, 0, 1, 0}, 
                   {0, 1, 1, 1, 0},
                   {1, 1, 1, 1, 0},
                   {1, 1, 1, 1, 1},
                   {0, 0, 0, 0, 0}};
         
         largestSquare(input);   
         largestRectangle(input);
         
         largestSquare(input2);   
         largestRectangle(input2);
       
        
	}
}