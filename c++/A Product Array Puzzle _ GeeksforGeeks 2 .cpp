//A Product Array Puzzle _ GeeksforGeeks
/* Logic:
1. Moving Left to Right and store Product of left of A[i] to P[i] ;
2. Moving Right to left and store right product upto A[i] in product and then multiply it to P[i]

ex.     10      3       5       6        2
step1:  1       10      30      150     900
Pro:    180     60      12       2       1
step2:  180     600     360     300     900


TC O(n)
SC O(n)
AC O(1)
*/

#include <iostream>
#include <cstdlib>
using namespace std ;
template<typename T>
void From_Left_Right( T *A ,T *Product , int size)
{
    Product[0] = 1 ;
    for ( int i = 1 ; i<size;i++)
        Product[i] = Product[i-1]*A[i-1] ;


}
template<typename T>
void FromRight_Left( T *A ,T *Product , int size)
{
    T product = A[size-1] ;
    for ( int i = size-2 ; i>=0;i--){
        Product[i] = Product[i]*product ;
        product *=A[i] ; }


}


int main ()
{
    int size ;
    cout <<"\n Enter Size :" ;
    cin >> size;
    int *A=new int[size] ;
    cout <<"\n enter ele:";
    for ( int i = 0 ; i<size ; i++)
        cin >> A[i] ;
    int *Product = new int [size] ;

    From_Left_Right(A,Product,size) ;
     for ( int i = 0 ; i < size ; i++)
        cout <<Product[i] << " " ;
    FromRight_Left(A,Product,size) ;
        cout <<"\n output: \n" ;
    for ( int i = 0 ; i < size ; i++)
        cout <<Product[i] << " " ;
}

