//Maximum Length Dual - Bitonic Subarray
/*

Given an array A[0 ... n-1] containing n positive integers, a subarray A[i ... j] is bitonic if there is
a k with i <= k <= j such that A[i] <= A[i + 1] ... <= A[k] >= A[k + 1] >= .. A[j - 1] > = A[j].
or vice versa(for dual )
Complexity O(n) and O(1)

Logic:
for understanding logic ..we take an example
{20,4,5,6,7,6,3,2,20,30,40,50,30,20,10,5,4}
i=0 -> 20
i = 1-> 4 < 20
i = 2 -> 5 > 4 means 4 is bitonic point where behaviour gets change
i = 3->6 > 5
i = 4 -> 7 > 6
i = 5 -> 6 < 7 means 4 is bitonic point so calculate length up to index 4
i = 6-> 3 < 6
i = 7 -> 2 < 3
i = 8 -> 20 > 2 means 7 is bitonic point
i = 9 -> 30 > 20
i = 10-> 40 > 30
i = 11->50 > 40
i= 12 ->30 < 50 so 11 is bitonic point calculate length
proceed same as above


*/

#include <iostream>
#include <cstdlib>
using namespace std;

bool Maximum_Length_of_Bitonic_Subarray(int *A , int size , int &start , int &end)
{
    int  sign=0 ;  // this variable is use to indicate that does bitonic sequence end or not
    bool flag =0 ;  // for identifying > & <
    int length = 0 ; // it contain maximum length
    int temp_start,temp_end ; // temp. variable for starting index i and ending index j of Subarray
    int Bitonic_Point = 0 ; // this variable contain the pivoted point means K as index

    if ( size==0)
    return false ;

    if ( size==1)
    return true;

    if ( size > 1)
    {
        start = end = 0 ;
        temp_start = temp_end  = 0 ;
        for ( int i = 1 ; i<size;i++)
        {
            if ( flag==0)      // flag =0 indicate for the > sequence
                if ( A[i-1] > A[i])
                {

                }
                else     // this indicate that we reach bitonic point
                {
                    if ( sign == 0) // if we are calculating first time bitonic point
                    Bitonic_Point = i - 1 ; // calculating bitonic point
                    if ( sign!=2)
                    sign++ ;
                    flag = 1 ;
                    if ( sign==2)  // if we are come at point that were we identify end of bitonic subarray
                                    // calculating length of bitonic subarray
                    {
                        //calculate length
                        if ( length < temp_end-temp_start)
                        {
                            length = temp_end-temp_start ;
                            start = temp_start ;
                            end = temp_end ;
                            temp_start = Bitonic_Point ;
                            temp_end = i ;
                        }
                        sign=1;
                        Bitonic_Point = i-1 ;
                    }
                }
            else
            {
                // flag =1 indicate for the < sequence
                if ( A[i-1] < A[i])
                {

                }
                else
                {
                     if ( sign == 0)
                    Bitonic_Point = i - 1 ;


                    if ( sign!=2)
                    sign++ ;
                    flag = 0 ;
                    if ( sign==2)
                    {
                        sign=1;
                        //calculate length
                        if ( length < temp_end-temp_start)
                        {
                            length = temp_end-temp_start ;
                            start = temp_start ;
                            end = temp_end ;
                            temp_start = Bitonic_Point ;
                            temp_end = i ;

                        }
                        Bitonic_Point = i-1 ;

                    }
                }
            }
        temp_end = i ;
        }
        //up to  end
        if ( length < temp_end-temp_start)
                        {
                            length = temp_end-temp_start ;
                            start = temp_start ;
                            end = temp_end ;
                            temp_start = Bitonic_Point ;

                        }

    }
    cout <<"\n\n" ;
    return true ;

}

int main()
{
    int arr[] = {20,4,5,6,7,6,3,2,20,30,40,50,30,20,10,5,4,6,7,8,9,10,11,12,13,10,9,8,7,6,5,4,3,2,1};
    //{12, 4, 78, 90, 45, 23};//{20, 4, 1, 2, 3, 4, 2, 10};//{40, 30, 20, 10};//////{10, 20, 30, 40};////
    int size = sizeof(arr)/sizeof(arr[0]);
    int start =0, end = 0 ;
    if ( Maximum_Length_of_Bitonic_Subarray(arr,size,start,end))
    for ( ; start<=end ; start++)
        cout <<arr[start] << " ";

    return 0;
}
