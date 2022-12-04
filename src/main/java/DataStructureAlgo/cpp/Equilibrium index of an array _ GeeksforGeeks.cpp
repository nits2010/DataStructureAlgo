//Equilibrium index of an array _ GeeksforGeeks
#include <iostream>
#include <cstdlib>
using namespace std ;
template<typename T>

T Sum_of_Array(T *A , int size)
{
    T sum=0 ;
    for ( int i = 0 ; i < size ; i++)
        sum+=A[i];
    return sum ;
}

template<typename T>
int showEqyukubrium_index( T *A , int size)
{
    T Total_Sum = Sum_of_Array(A,size) ;
    T left_Sum = A[0] ;
    int counter=0;
    for ( int i = 1 ; i<size ; i++)
        {
            if ( left_Sum == (Total_Sum - A[i] - left_Sum))
                {   cout <<"\n " << i ; counter++ ; }
            left_Sum +=A[i] ;
            /* you can also do such that
                Total_Sum -= A[i] ;
                if ( left_Sum == Total_Sum )
                {   cout <<"\n " << i ; counter++ ; }
            left_Sum +=A[i] ;
            */

        }
        return counter ;
}
int main ()
{
    int size ;
    cout <<"\n enter size: " ;
    cin >> size;
    int *A = new int[size]  ;

    for( int i = 0 ; i < size; i++)
        cin >> A[i] ;

    cout <<"\n Total Equilibrium index are " << showEqyukubrium_index(A,size) ;
}
