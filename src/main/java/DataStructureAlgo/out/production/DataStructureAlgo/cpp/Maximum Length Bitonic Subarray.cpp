//Maximum Length Bitonic Subarray
/*
Given an array A[0 ... n-1] containing n positive integers, a subarray A[i ... j] is bitonic if there is
a k with i <= k <= j such that A[i] <= A[i + 1] ... <= A[k] >= A[k + 1] >= .. A[j - 1] > = A[j].
or vice versa

*/

#include <iostream>
#include <cstdlib>
using namespace std;

bool Maximum_Length_of_Bitonic_Subarray(int *A , int size , int &start , int &end)
{
    bool sign=0 ;  // sign reperesent for incressing or decressing sequence (-)
    bool flag =0 ;
    int length = 0,i=0 ;
    int temp_start,temp_end ; // temp. variable for starting index i and ending index j of Subarray
    int Bitonic_Point = 0 ; // this variable contain the pivoted point means K as index
    if ( size==0)
    return false ;
    if ( size==1)
    return true;

    if ( size > 1)
    {
        temp_start = 0 ;temp_end = 0 ; //asuming that length is zero and this is also handle corner case {10}
        for ( i = 1 ; i<size ; i++)
        {
            if ( flag == 0) // flag = 0 is for < element
            {
                if ( A[i-1] < A[i]) // checking for <
                {
                    if ( sign ==0) // if we found first element which follow A[i-1] < A[i]
                    {
                        temp_start = i-1 ; // store that index which we found
                        sign = 1 ; // sign =1 will show that if the sign change in futher then store its turning point
                                    // turning point indecate by Bitonic_point
                    }
                }
                else
                {
                    if ( sign == 1) // calcuating Bitonic_point
                    {
                        Bitonic_Point = i - 1 ;
                        flag = 1 ; // flag = 1 shows that now we have to find for > MinStepsInfiniteGrid.t. A[i-1] > A[i]
                        i = i - 1; // i reduce to balance
                    }
                }
            }
            else
            {
                if ( A[i] < A[i-1]) // checking for <
                {
                    temp_end = i ; // storing the end point of bitonic subarray
                }
                else
                {
                    //if bitonic subbarray end then find lenght of that array and compair with previous length and calculate
                    // bitonic point and set flag = 0 ;
                    Bitonic_Point = i - 1 ;
                    if ( length < temp_end-temp_start)
                    {
                        start = temp_start ; // setting main index of bitonic to found bitonic subbarray
                        end = temp_end ;
                        length = end  - start ;
                        temp_start = Bitonic_Point ; // moving towords for next bitonic sub array

                    }
                }
            }
            temp_end = i ;
        }
        if ( length < temp_end-temp_start)
                    {
                        start = temp_start ;
                        end = temp_end ;
                        length = end  - start ;
                        temp_start = Bitonic_Point ;

                    }
           cout <<"\n\n" ;
      return true ;
    }
}

int main()
{
    int arr[] = {5,4,3,2,1} ;
    //{1,2,3,4,5} ; //{10};//{5,10,20,4,5,6,7,6,3,2,20,30,40};
    //{20,4,5,6,7,6,3,2,20,30,40,50,30,20,10,5,4};
    //{20, 4, 1, 2, 3, 4, 2, 10};//{12, 4, 78, 90, 45, 23};
    //{40, 30, 20, 10};//////{10, 20, 30, 40};////
    int size = sizeof(arr)/sizeof(arr[0]);
    int start =0, end = 0 ;
    if ( Maximum_Length_of_Bitonic_Subarray(arr,size,start,end))
    for ( ; start<=end ; start++)
        cout <<arr[start] << " ";

    return 0;
}
