// LIS With DP
// O(n^2) solution

#include <iostream>
#include <cstdlib>
using namespace std ;

// Following function accept Sequence and its length
int * LIS_DP ( int *Sequence , int Length_Sequence , int &LIS_lenght )
{
    /* Logic:

    LIS[ i ]    =    Max( LIS[j] ) + 1                          for all i > j and A[j] < A[i] ;

    suppose at any instant we are at index i . so LIS[i] contain the lenght of LIS up to i (including i) from j to i where 0<=j<i

    */


    int LIS[Length_Sequence] ; // Creating a Memory Table
    int Trac_back[Length_Sequence] ;
    //Filling Table by default value : default value = 1 Since every individual element for the Increasing sequence of 1 length
    for ( int i = 0 ; i<Length_Sequence ; i++ )
            {
                LIS[i] = 1 ;
                Trac_back[i] = -1 ; // this is use to trac back ...and at any point from where we can trac back its value is -1... intially we can trac back from any point
            }


    // Filling the Table base on the formula
    for ( int i = 1 ; i <Length_Sequence ; i++ ) // starting at second element ; since LIS of first element is 1 because LIS eding at index = 0 is 1 ( itself )
    {
        for ( int j = 0 ; j<i ; j++)        // starting for first element ( 0th index ) handing the condition i>j
        {
             // First (Sequence [i] > Sequence [j] ) handling the A[i] > A[j]  and  seconde ( LIS [i] < LIS[j] + 1) handling to get maximum at LIS[i]
            if ( (Sequence [i] > Sequence [j] ) && ( LIS [i] < LIS[j] + 1)   )
            {
                   LIS[i]               =       LIS[  j ] + 1  ;  // putting maximum one
                   Trac_back [i]   =        j                  ;    // saving from where we got max
            }
        }

    }

    // finding the maximum LIS value and its index
    int max =0 , index ;
    for (  int i = 0 ; i < Length_Sequence ; i++)
    {
        if ( max < LIS [i] )
        {
            max = LIS [i] ;
            index = i ;
        }
    }

    int *output = new int [max]  , i = 0 ;

   //backSteping for LIS element
   while (index!=-1)
   {

      output[i++] =  Sequence[ index ]   ;
       index = Trac_back[index] ;

   }

   cout <<"\n\n\n Printing LIS \n" ;
    LIS_lenght = max ;
    return output ;
}

int main ()
{
    int *Sequence;
    int length , LIS_Length , test_count;

    cout <<"\n How many time you want to test this program :  " ;
    cin >> test_count ;
 while ( test_count )
 {

        cout <<"\n\n Enter the lenght of Sequence : " ;
        cin >> length ;
        Sequence = new int [length] ;

        for ( int i = 0 ; i < length ; i++)
            cin >>  Sequence [i] ;

        int *LIS  = LIS_DP(Sequence , length  , LIS_Length ) ;


        cout <<"\n Longest Increasing Subsequence is:       " ;

        for ( int i = LIS_Length - 1 ; i>= 0 ; i--)
            cout << LIS[i] << "\t" ;
        cout <<"\n  Length is : " << LIS_Length ;

        delete Sequence ;
        test_count-- ;
    }
}
