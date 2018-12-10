//Quick Union : Union by Height
/* In Union by Height : Make the Smaller Tree (height wise ) as a SubTree of the larger Tree*/
/* Algo: implementing using Array
Create a Height array which contain the Height of the Tree Rooted at that point

   Find_Algo : from the Given root find its root by moving upword
   Union_Algo: 1. First find the Root of both "a" and "b" say "root1" and "root2"
               2. Then find the Minimum Height Tree and connect it with larger size tree

                 Height updation procedure :
                 ---------------------------------
                        1. if  the height of both tree is 1 then update the height of root node to 2
                        2. if the height of the both tree are equal
                                then increment the height of the root node tree by 1
                                                [since if we connect a "X" height tree to "X" height tree then Height of the total tree will be "X+1"]
                            if the height of the both tree are not equal
                                        then by adding the tree of smaller height to Tree of Larger height
                                        does not effect on the height of total tree


    Complexity:   Union     Find
         Average    O(logN)     O(logN)
         Worst      O( (logN)^2 )  O(logN)

                                    N being the size of data




*/

#include<iostream>
#include <cstdlib>
#include <limits.h>

using namespace std;
//Function for Making disjoint Set
void MAKESET ( int *S , int size ,int *Height_of_Tree )
{
     for ( int i = 0 ; i < size ; i++ )
         {
             S[i] = i ;  // making root of the current element itself

             Height_of_Tree[i] = 1 ; // intially Height of every tree is one: only one node is there
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
void UNION(int S[] , int size ,int *Height_of_Tree , int a , int b ) // a and b being the element that to be union
{
    //Getting the Root of "a" and "b"
    int Root1 = FIND(S,size,a) ;

    int Root2 = FIND(S,size,b) ;

    //checking the Boundy Condition for the Root
    // Root1 == Root2 means both have same root ; so both are already Merge
    if ( Root1 == Root2 || Root1 == INT_MIN || Root2==INT_MIN)
        return ; // returning without any change

    else
    { int diff_of_heights = 0;

       // if the Height of both Tree is 1

       if ( Height_of_Tree[Root1] == 1 && Height_of_Tree[Root2] == 1 ) // if Height of both the Tree is 1
       {
           S[Root2] = Root1; // making root2 as Sub tree of Root1

           Height_of_Tree[Root1] ++ ; // Increment the hight of Tree rooted at Root1
       }

       else if ( Height_of_Tree[Root1] > 1 ||  Height_of_Tree[Root2] > 1) // if the Height of Both the Tree are  > 1
       {
           if ( Height_of_Tree[Root1] != Height_of_Tree[Root2])
           {
               // if the Height of The tree rooted at Root1 is Greater then Height of Tree Rooted at Root2
                 if ( Height_of_Tree[Root1] > Height_of_Tree[Root2])
                                                    S[Root2] = Root1 ; // making Tree rooted at Root2 as Sub tree of Tree Rooted at Root1

                            // if the Height of The tree rooted at Root2 is Greater then Height of Tree Rooted at Root1
                  else if ( Height_of_Tree[Root1] < Height_of_Tree[Root2])
                                   S[Root1] = Root2; // making Tree rooted at Root1 as Sub tree of Tree Rooted at Root2

            }

            // if the Height of The tree rooted at Root2 is equal then Height of Tree Rooted at Root1
            else if ( Height_of_Tree[Root1] == Height_of_Tree[Root2] )
            {
                S[Root1] = Root2 ;// making Tree rooted at Root1 as Sub tree of Tree Rooted at Root2

                Height_of_Tree[Root2] ++ ;// updateing the height of the Tree rooted at Root2
            }

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
    int *Height_of_Tree ;
    cout <<"\n Enter the Size of disjoint Sets : " ; cin >> size ;

    //creating array dynamically
    Set = new int[size] ;
    Height_of_Tree = new int[size] ;

    //Make set
    MAKESET(Set,size,Height_of_Tree) ;
    cout <<"\n Sets Created...\n" ;
    Display_Disjoint_Set(Set,size) ;
    cout <<"\n" ;

    //Union Operation
    UNION(Set,size,Height_of_Tree,3,4) ;
    UNION(Set,size,Height_of_Tree,4,9) ;
    UNION(Set,size,Height_of_Tree,8,0) ;
    UNION(Set,size,Height_of_Tree,2,3) ;
    UNION(Set,size,Height_of_Tree,5,6) ;
    UNION(Set,size,Height_of_Tree,5,9) ;
    UNION(Set,size,Height_of_Tree,7,3) ;
    UNION(Set,size,Height_of_Tree,4,8) ;
    UNION(Set,size,Height_of_Tree,6,1) ;

    cout <<"\n Content of Sets : \n" ;
    Display_Disjoint_Set(Set,size) ;

    cout <<"\n Content of the Size of Tree: \n" ;
    Display_Disjoint_Set(Height_of_Tree,size ) ;



return 0;
}
