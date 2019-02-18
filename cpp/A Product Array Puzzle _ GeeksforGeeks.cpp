//A Product Array Puzzle _ GeeksforGeeks
/* Logic:
1. Get the left Product of A[i] excluding A[i] in Left[i] from left to right
2. Get the Right Product of A[i] excluding A[i] in Right[i] from Right to Left
3.Prod[i] = Left[i] * Right[i]

TC O(n)
SC O(n)
AC O(n)
*/

#include <iostream>
#include <cstdlib>
using namespace std ;
template<typename T>
void GetLeft_Product( T *A ,T *Left_Product , int size)
{
    Left_Product[0] = 1 ;
    for ( int i = 1 ; i<size;i++)
        Left_Product[i] = Left_Product[i-1]*A[i-1] ;


}
template<typename T>
void GetRight_Product( T *A ,T *Right_Product , int size)
{
    Right_Product[size-1] = 1 ;
    for ( int i = size-2 ; i>=0;i--)
        Right_Product[i] = Right_Product[i+1]*A[i+1] ;


}

template<typename T>
void Get_Product( T *Left,T *Right ,T *Product , int size)
{

    for ( int i = 0 ; i<size;i++)
        Product[i] = Left[i]*Right[i] ;

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
    int *Left = new int [size] ;
    int *Right = new int [size] ;
    int *Product = new int [size] ;

    GetLeft_Product(A,Left,size) ;
    GetRight_Product(A,Right,size) ;
    Get_Product(Left,Right,Product,size) ;

    cout <<"\n output: \n" ;
    for ( int i = 0 ; i < size ; i++)
        cout <<Product[i] << " " ;
}
