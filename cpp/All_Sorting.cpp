#include <iostream>
#include <cstdlib>
using namespace std ;

/* Declaration of Function */
// bubble Sort
template <typename T>
void Bubble_Sort(T *Array , size_t size);
/////////////////////////////////////

//Selection Sort
template <typename T>
void Selection_Sort ( T *Array , size_t size) ;
/////////////////////////////////////

//Insertion Sort
template <typename T>
void Insertion_sort(T* Array, size_t size) ;
/////////////////////////////////////

//Shell Sort
template <typename T>
void Shell_sort(T* Array, size_t size) ;
/////////////////////////////////////

// Merge Sort
template <class T>
void Merge ( T *Array , unsigned int leftF , const unsigned int leftE , unsigned int RightF,const unsigned int RightE );
template <class T>
void Merge_Sort ( T *A , unsigned int start  , unsigned int end     ) ;
/////////////////////////////////////

// HEap Sort
template <typename T>
void Heap (T *A , int n ) ;
template <typename T>
void Create_Heap(T *A, int pos , int count) ;
template <typename T>
void Heap_Sort ( T *A, int element ) ;
/////////////////////////////////////

// Function for Quick Sort
template <class T>
void Quick_Sort ( T *A , unsigned int start , unsigned int end );
/////////////////////////////////////

//Function for Counting Sort
template <class T>
void Counting_Sort ( T *A , size_t size , int Range);
/////////////////////////////////////

//bucket Sort
template <class T>
45void Bucket_Sort ( T *A , size_t size , int Bucket_Size);
/////////////////////////////////////

/*Defination of Funtion */
template <typename T>
void Bubble_Sort(T *Array , size_t size)
{

    // Logic of Bubble Sort
    int i , j ,k;
    T temp ;


    for ( int i = 0 ; i< size ; i++ )
    {
        for ( int j = 0 , k = 1 ; k < size ; k++ , j++ )
        {
              if (Array[j]> Array[k])
              {
                        T temp = Array[j] ;
                        Array[j] = Array[k] ;
                        Array[k] = temp ;
              }

        }
    }

}

template <typename T>
void Selection_Sort ( T *Array , size_t size)
{
 // Logic of Selation Sort

           for(int i=size-1,min=Array[0] ,k=0 ; i>=1 ; Array[k]=Array[i] , Array[i]=min , i-- , min = Array[0] , k = 0 )

                      for(int j=1 ;  j<=i ; ( ( Array[j]>min ) ? (min = Array[j], k = j) : 1 ) , j++) ;




         /*  for(i=size-1;i>=1;i--)
           {
                      min=a[0];
                      k=0;
                      for(j=1;j<=i;j++)
                      {
                                       if(a[j]>min)
                                       {
                                                   min=a[j];
                                                   k=j;
                                       }
                      }

                      a[k]=a[i];
                      a[i]=min;
           }*/
}


template <typename T>
void Insertion_sort(T* Array, size_t size)
{
     int i , j ;
     T min  ;

	for( i=1; i<size; i++)
    {
             min = Array[i] ;
             j = i-1;
             while (min < Array[j] && j>=0 )
             {
                   Array[j+1] = Array[j] ;
                   j-- ;
             }
             Array[j+1] = min ;
    }

}



template <typename T>
void Shell_sort(T* Array, size_t size)
{
    int Gap_size = 1  ; // for Gap size
    int value,j ;
    //obtaining valid and good gap_size multiple of 3
    for (Gap_size = 1 ; Gap_size<size ;  Gap_size= Gap_size*3+1 ) ;

    //according to gap size applying Insertion sort
    for ( ; Gap_size>0 ; Gap_size/=3)
    {
        for ( int i = Gap_size ; i<size; i++)
        {
            value = Array[i] ;
            j = i ;
            while ( j>=Gap_size && Array[j-Gap_size] > value)
            {
                Array[j] = Array[j-Gap_size] ;
                j-=Gap_size ;
            }
            Array[j] = value ;
        }
    }
}


// Function for Merge the Sorted array
template <class T>
void Merge ( T *Array , unsigned int leftF , const unsigned int leftE , unsigned int RightF,const unsigned int RightE )
{
	int index =leftF ;
	int i = leftF ;
	T *temp = new T [RightE] ;
	while ( (leftF<=leftE) && (RightF <=RightE) )
	{
		if (Array[leftF] < Array[RightF] )
		{
			temp[index] = Array[leftF];
			leftF++ ;
		}
		else
		{
			temp[index] = Array[RightF];
			RightF++ ;

		}
		index++ ;
	}
	while ( leftF<=leftE)
	{
		temp[index++] = Array[leftF] ;
		leftF++ ;
	}
	while ( RightF<=RightE)
	{
		temp[index++] = Array[RightF] ;
		RightF++ ;
	}

	for ( index = i  ; index<=RightE ; index++)
	{
		Array[index] = temp[index] ;
	}

}

template <class T>
void Merge_Sort ( T *A , unsigned int start  , unsigned int end     )
{
	if ( start < end )
	{
		int mid = (start  + end ) / 2 ;
		Merge_Sort (A , start  , mid);
		Merge_Sort ( A , mid + 1 , end);
		Merge ( A , start , mid , mid + 1 , end);
	}

}


// Heap Sort

/* Working Funtion For Heap Sort */
template <typename T>
void Heap (T *A , int n )
{

	for (int j = (n)/2 ; j>=0;  j--)
    	{
	          Create_Heap ( A , j , n) ;
         }

}

template <typename T>
void Create_Heap(T *A, int pos , int count)
{
    int i = 2*pos + 1 ;
    while (i<=count )
    {
        if ( (i<count) && (A[i]<A[i+1]) )
        i++ ;
        if ( A[pos] < A[i])
        {
            T temp  = A[pos] ;
            A[pos] = A[i] ;
            A[i] = temp ;

        }
        pos = i ;
        i = 2*pos + 1 ;
    }
}

template <typename T>
void Heap_Sort ( T *A, int element )
{
		Heap ( A, element-1 ) ;
		T temp; //= A[1] ;
		 int n = element-1 ;
		for (  ; n >0 ; n-- )
		{
				temp = A[0] ;
				A[0]  = A[n]  ;
				A[n] = temp ;
			      	Heap(A, n-1);
		}

}

//Quick Sort
template <class T>
void Quick_Sort ( T *A , unsigned int start , unsigned int end )
{
	// Ending Point of Recursion
	if ( start >=end)
	return ;

	int i = start ;
	int j = end-1 ;

	T pivote_element = A[start] ; // pivote Element in which respect Quick sort is apply
	while ( i <= j )
	{
		if ( A [i ] > A[j] )
		{
			int temp = A[i] ;
			A[i] = A[j] ;
			A[j] = temp ;
		}
		if ( pivote_element == A[j])
		i++ ;
		else
		j-- ;
	}
	// Tail Recursion
	Quick_Sort (A, start , i-1 ) ;
	Quick_Sort (A, j+1, end ) ;
}

//Counting Sort
template <class T>
void Counting_Sort ( T *A , size_t size , int Range)
{

    //Assumtion of this Algo is : Every no. in A should be in Range of 1 to K where K = O(n)
    //creating Two Array'MinStepsInfiniteGrid of Size K+1 and size
    T *Temp = new T[Range] ;
    T *Sorted_Array = new T [size] ;

    //Intialling Temp array with 0 : ComplexitY O(Range)~O(n)
    for (int i = 0 ; i<Range ; i++)
        Temp[i] = 0 ;

    //Marking the all element of A in Temp : O(n)
    for ( int i =0 ; i<size; i++)
        Temp[A[i]]+=1 ; // due to this all element which are present in A are marked

    //increment the correct count so futher we can map directly : O(Range)~O(n)
    for ( int i =1 ; i<Range ; i++)
        Temp[i] = Temp[i] + Temp[i-1] ;

    //Mapping the Element From Temp and A to Sorted Array : O(n)
    for ( int i=0 ; i<size ; i++)
    {
        Sorted_Array[  Temp[ A[i] ] -1 ] = A[i] ;
        Temp[A[i]]--;
    }

    //Copying the sorted array to Main array: O(n)
    for ( int i = 0 ; i<size ; i++)
        A[i] = Sorted_Array[i] ;

    delete Sorted_Array ;
    delete Temp ;



}
//bucket Sort
template <class T>
void Bucket_Sort ( T *A , size_t size , int Bucket_Size)
{
    T *Bucket = new T [Bucket_Size] ;  // this is the bucket

    //First Fill all the Bucket with Zero'MinStepsInfiniteGrid : O(n)
    for ( int i = 0 ; i<Bucket_Size ; i++)
            Bucket[i] = 0 ;

    //Marking all the element of Input Array in bucket: O(n)
    for ( int i = 0 ; i< size ; i++)
        Bucket[A[i]]++ ;     // if it were 0(not present) then it will be 1(present) and if it were 1 then it will be more then 1(repeted element)


    //assigning the Data in Input Array from Bucket: O(n)
    for ( int i =0 , j = 0 ; j<Bucket_Size ; j++)
        for ( int k = Bucket[j] ; k>0; k--)
            A[i++] = j ;

    delete Bucket ;
}

int main ( )
{
    int *A ;
    int n ;
    cout <<"\n Enter the Size of Array : " ;
    cin >> n ;

    A = new int [n] ;

//    for (int i = 0 ; i<n ; cin >> A[i] , i++ ) ;
    //Selection_Sort(A,n) ;
    //Bubble_Sort(A,n) ;
    //Insertion_sort (A,n );
    //Shell_sort(A,n) ;
   // Merge_Sort(A,0,n) ;
    // Heap_Sort(A,n) ;
   // Quick_Sort(A,0,n);
    /*cout <<"\n Pls enter all no. in Range of 1 to " << n ;
    for (int i = 0 ; i<n ; cin >> A[i] , i++ ) ;
    Counting_Sort(A,n,n+1) ;
    Bucket_Sort(A,n,n+1) ;
*/
    cout <<"\n After Sorting : \n"  ;
    for (int i = 0 ; i<n ; cout << A[i] << endl , i++ ) ;

return 0 ;
}
