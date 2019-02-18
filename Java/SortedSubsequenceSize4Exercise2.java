package Java;/* package whatever; // don't place package name! */

class SortedSubsequenceSize4Exercise2SolutionSet {
		private int i=-1,j=-1,k=-1,l=-1;
		
		public int getI(){
			return i;
		}
		
		public int getJ(){
			return j;
		}
		
		public int getK(){
			return k;
		}
		
			
		public int getL(){
			return l;
		}
		
		public boolean findTriplet4(int input[]){
			if (input.length<=2)
				return false;
				
			return FindSortedSubsequenceOfSize3(input);
		}
		
		private boolean hasFound(){
			if (i==-1 || j ==-1 || k ==-1 || l == -1)
				return false;
			return true;
		}
		
		private boolean FindSortedSubsequenceOfSize3(int input[]){
			
			boolean isJFound = false;
			boolean isKFound = false;
			
			int tempItem = -1;
			int iItem = -1 ; 
			int jItem=-1;
			int kItem=-1;
			
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
				}else if (!isKFound || input[i] < kItem){
					//iItem = tempItem;
					kItem = input[i];
					isKFound = true; 
				}else {
					this.i = iItem;
					this.j = jItem;
					this.k = kItem;
					this.l = input[i];
				//	System.out.println(k + " " + i );
					break;
				}
				
			}
			
			
			
			
			return hasFound();
		}
		
	public void refresh(){
		i=j=k =l=-1;
	}
	
}

/* Name of the class has to be "Main" only if the class is public. */
class SortedSubsequenceSize4Exercise2
{
	public static void main (String[] args) throws java.lang.Exception
	{
		int arr1[] = {12, 11, 10, 5, 6, 2, 30,35};
		int arr2[] = {1, 2, 3, 4};
		int arr3[] = {4, 3, 1, 2};
		int arr5[] = {5,13,6,10,3,7,2};
		int arr4[] = {12, 11, 10, 5, 6, 2, 30};
		int arr6[] = {12, 11, 13, 5, 14, 2, 15, 16 , 2 , 17, 3 , 18};
		SortedSubsequenceSize4Exercise2SolutionSet s = new SortedSubsequenceSize4Exercise2SolutionSet();
	
		if ( s.findTriplet4(arr5) ) {
			System.out.println("arr5 " + " i: " +s.getI() + " j : "+s.getJ() + " k : " + s.getK()+ " l " +s.getL() ) ; 
		}else {
				System.out.println("arr5 "+"not found");
		}
		s.refresh();
		if ( s.findTriplet4(arr4) ) {
			System.out.println("arr4 " +"i: " +s.getI() + " j : "+s.getJ() + " k : " + s.getK() + " l " +s.getL()) ; 
		}else {
				System.out.println("arr4 "+"not found");
		}
		s.refresh();
		
		if ( s.findTriplet4(arr3) ) {
			System.out.println("arr3 " +"i: " +s.getI() + " j : "+s.getJ() + " k : " + s.getK() + " l " +s.getL() ) ; 
		}else {
				System.out.println("arr3 "+"not found");
		}
		s.refresh();
		
		if ( s.findTriplet4(arr2) ) {
			System.out.println("arr2 " +"i: " +s.getI() + " j : "+s.getJ() + " k : " + s.getK()+ " l " +s.getL() ) ; 
		}else {
				System.out.println("arr2 "+"not found");
		}
		s.refresh();
		
		if ( s.findTriplet4(arr1) ) {
			System.out.println("arr1 " +"i: " +s.getI() + " j : "+s.getJ() + " k : " + s.getK() + " l " +s.getL()) ; 
		}else {
				System.out.println("arr1 "+"not found");
		}
		s.refresh();
		
		if ( s.findTriplet4(arr6) ) {
			System.out.println("arr6 " +"i: " +s.getI() + " j : "+s.getJ() + " k : " + s.getK() + " l " +s.getL()) ; 
		}else {
				System.out.println("arr6 "+"not found");
		}
		s.refresh();
		
		
		// your code goes here
	}
}