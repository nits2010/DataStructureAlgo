//Quick Union : Lazy method
/* Algo: implementing using Array
   Find_Algo : from the Given root find its root by moving upword
   Union_Algo: 1. First find the Root of both "a" and "b" say "root1" and "root2"
               2. Then makes S[root1] = root2 ;
    Complexity:   Union     Find
                  O(N*)     O(N)
                                    where * shows time for Find per union operation
                                    N being the size of data




*/
#include<iostream>
#include <cstdlib>
#include <limits.h>

using namespace std;
//Function for Making disjoint Set
void MAKESET ( int *S , int size )
{
     for ( int i = 0 ; i < size ; i++ )
         S[i] = i ;  // making root of the current element itself
}

//Find algorithm
int FIND(int S[] , int size , int X)
{
    if ( X<0 || X>size ) return INT_MIN ; //Chcking the Boundry condition

    while ( X!=S[X] )
          X = S[X] ;  // Marching towords root
    return X ; // Returning the Root
}

//Find the Union Algorithm
void UNION(int S[] , int size ,int a , int b ) // a and b being the element that to be union
{
    //Getting the Root of "a" and "b"
    int Root1 = FIND(S,size,a) ;
    int Root2 = FIND(S,size,b) ;

    //checking the Boundy Condition for the Root
    // Root1 == Root2 means both have same root ; so both are already Merge
    if ( Root1 == Root2 || Root1 == INT_MIN || Root2==INT_MIN)
        return ; // returning without any change

    else
        S[Root1] = Root2 ;
}

void Display_Disjoint_Set(int *S , int size)
{
    for (int i =0 ; i< size ; i++)
        cout <<S[i] << " " ;
}
int main()
{
    int *S ;
    int size  ;
    cout <<"\n Enter the Size of disjoint Sets : " ; cin >> size ;
    S = new int[size] ;
    MAKESET(S,size) ;
    cout <<"\n Sets Created...\n" ;
    Display_Disjoint_Set(S,size) ;
    cout <<"\n" ;
    UNION(S,size,3,4) ;
    UNION(S,size,4,9) ;
    UNION(S,size,8,0) ;
    UNION(S,size,2,3) ;
    UNION(S,size,5,6) ;
    UNION(S,size,5,9) ;
    UNION(S,size,7,3) ;
    UNION(S,size,4,8) ;
    UNION(S,size,6,1) ;

    cout <<"\n Content of Sets : \n" ;
    Display_Disjoint_Set(S,size) ;









system("pause");
return 0;
}
