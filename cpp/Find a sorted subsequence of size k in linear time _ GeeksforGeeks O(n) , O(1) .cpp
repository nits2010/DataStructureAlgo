//Find a sorted subsequence of size 3 in linear time _ GeeksforGeeks O(n)/O(n) solution
/*
Logic:
Logic is very simple
actuallY we have first find the minimum value .once we encounter minimum value at index i we need to find greater value
greater than previous value
    {12, 11, 10, 5, 6, 2, 30,5,3,32,2,40};
first we found minimum value 11 then 10 then 5 (since 6 > 5 )
now we find greater value'MinStepsInfiniteGrid which is greater to previous one
we have 5 now next is 6 next is 30 next is 32 and 40

Time complexity : O(n) (only one scan )
space complexity : O(1) {Ans array is not count in space complexity of program)
Answer is store in "Ans" array
  */
#include <iostream>
#include <cstdlib>
using namespace std ;
// A function to fund a sorted subsequence of size 3

bool findKNumbers(int arr[],int size , int n, int *Ans)
{
 int i=0,j=size,k=size,l;

  if(size<n)
  {
    cout <<"Not enough elements";
     return false;
  }

        int Ans_index = 0 ;
        Ans[Ans_index++] = arr[0] ; // by default we assuming that we need n=1 so ans will be arr[0]

        if ( Ans_index == n)
        return true ;

        // if n > 1 then we proceed as below from index 1 to size
        for (i = 1 ; i<size ; i++)
        {
            if ( Ans_index-1 == 0 )// untill at 0th index correct value not inserted we have to insert all element at 0th index
            {
                if ( arr[i]<Ans[Ans_index-1])
                Ans[Ans_index-1] = arr[i] ;
            }
            if ( arr[i] > Ans[Ans_index-1]) // since at 0th index we have correct value
            {                                   // means we have sorted sequence from 0th to Ans_index-1 so we need to think about
                                            // only element for Ans_index not at Ans_index-1 or below
                Ans[Ans_index] = arr[i] ;
                if (Ans_index == n)   // it Ans_index reach to required element then stops
                return true ;
                Ans_index++;
            }

        }
        if ( Ans_index !=n)
        {
            cout << "No such subsequence found";
            return false ;
        }
}

// Driver program to test above function
int main()
{
    int arr[] ={1,2,3,4};//{12, 11, 10, 5, 6, 2, 30,5,3,32,2,40};//
    int k ;
    cout <<"\n Enter the value of K:" ;
    cin >> k ;

    int *Ans = new int [k] ;//Answer array
    int n = sizeof(arr)/sizeof(arr[0]);

    if ( findKNumbers(arr, n, k , Ans))
    for ( int i=0; i <k ; i++)
        cout <<Ans[i]<<" " ;
    return 0;
}
