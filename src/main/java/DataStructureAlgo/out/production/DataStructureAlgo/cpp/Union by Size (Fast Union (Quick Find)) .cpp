//Quick Union : Union by Size
/* In Union by Size : Make the Smaller Tree as a SubTree of the larger Tree*/
/* Algo: implementing using Array
Create a Size array which contain the Size(no. of nodes) of the Tree Rooted at that point

   Find_Algo : from the Given root find its root by moving upword
   Union_Algo: 1. First find the Root of both "a" and "b" say "root1" and "root2"
               2. Then find the Minimum size Tree and connect it with larger size tree

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
void MAKESET ( int *S , int size ,int *Size_of_Tree )
{
     for ( int i = 0 ; i < size ; i++ )
         {
             S[i] = i ;  // making root of the current element itself
             Size_of_Tree[i] = 1 ; // intially size of every tree is one: only one node is there
         }
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
void UNION(int S[] , int size ,int *Size_of_Tree , int a , int b ) // a and b being the element that to be union
{
    //Getting the Root of "a" and "b"
    int Root1 = FIND(S,size,a) ;
    int Root2 = FIND(S,size,b) ;

    //checking the Boundy Condition for the Root
    // Root1 == Root2 means both have same root ; so both are already Merge
    if ( Root1 == Root2 || Root1 == INT_MIN || Root2==INT_MIN)
        return ; // returning without any change

    else
    {
        if ( Size_of_Tree[Root1] < Size_of_Tree[Root2]) // if the size of the Tree Rooted at Root1 is Smaller then Tree rooted at Root2 then
         {
             S[Root1] = Root2 ;  // then make it Subtree of the Tree Rooted At Root2
             Size_of_Tree[Root2] += Size_of_Tree[Root1] ; // update the Size of Tree Rooted at Root2
         }
         else// if the size of the Tree Rooted at Root1 is Greater then Tree rooted at Root2 then
         {
             S[Root2] = Root1 ;  // then make it Subtree of the Tree Rooted At Root1
             Size_of_Tree[Root1] += Size_of_Tree[Root2] ; // update the Size of Tree Rooted at Root2
         }
    }

}

void Display_Disjoint_Set(int *A , int size)
{
    for (int i =0 ; i< size ; i++)
        cout <<A[i] << " " ;
}
int main()
{
    int *Set ;
    int size  ;
    int *Size_of_Tree ;
    cout <<"\n Enter the Size of disjoint Sets : " ; cin >> size ;

    //creating array dynamically
    Set = new int[size] ;
    Size_of_Tree = new int[size] ;

    //Make set
    MAKESET(Set,size,Size_of_Tree) ;
    cout <<"\n Sets Created...\n" ;
    Display_Disjoint_Set(Set,size) ;
    cout <<"\n" ;

    //Union Operation
    UNION(Set,size,Size_of_Tree,3,4) ;
    UNION(Set,size,Size_of_Tree,4,9) ;
    UNION(Set,size,Size_of_Tree,8,0) ;
    UNION(Set,size,Size_of_Tree,2,3) ;
    UNION(Set,size,Size_of_Tree,5,6) ;
    UNION(Set,size,Size_of_Tree,5,9) ;
    UNION(Set,size,Size_of_Tree,7,3) ;
    UNION(Set,size,Size_of_Tree,4,8) ;
    UNION(Set,size,Size_of_Tree,6,1) ;

    cout <<"\n Content of Sets : \n" ;
    Display_Disjoint_Set(Set,size) ;

    cout <<"\n Content of the Size of Tree: \n" ;
    Display_Disjoint_Set(Size_of_Tree,size ) ;



return 0;
}
