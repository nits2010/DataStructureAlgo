//Find the minimum distance between two numbers _ GeeksforGeeks
/*
Logic:
1> simply traverse the array and find one of them. say one of them found having index "i"
2> now find next distinct but one of them and calculated distance if found distinct
3> if similier found then point it and find distict one
*/
#include <iostream>
#include <cstdlib>
using namespace std ;
int Find_Distance(int *A , size_t size , int &x , int &y)
{
    int min = size, i , j;
    for ( i = 0 ;i<size;i++ )
    {
        if (A[i] == x || A[i]==y) // if any one of them found
        break ;
    }
    if (i==size)
    return -1 ;

//searching for other one
    for ( j = i+1; j<size; j++)
    {
        if ( A[j]==x|| A[j]==y) // if any one of them found
       {

           if ( A[j]!=A[i] ) // if both found item are distinct
           {

               if( min > j-i) // calculate distance
                min = j-i ;
           }
           i = j ;// if both found item are not distinct but one of them
       }


    }
    return min ; //end of the game

}
int main ()
{
    size_t size ;
    cout <<"\n Enter Size of Array: " ;
    cin >> size ;
    int *A = new int [size] ;

    cout <<"\n Enter the element of Array: " ;
    for ( int i = 0 ;i<size;i++)
    cin >> A[i] ;

    int X,Y ;
    cout <<"\n Enter X and Y=" ;
    cin >> X >> Y;
    cout <<"\n Minimum distance between " <<X << " and " << Y << " is " << Find_Distance(A,size,X,Y) ;

}
