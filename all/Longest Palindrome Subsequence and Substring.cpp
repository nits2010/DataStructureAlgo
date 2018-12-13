// Longest Palindrome Subsequence and Substring

#include <iostream>
#include <cstdlib>
using namespace std ;

class Longest_Palindrome
{
            int Length ;
            char *String ;
        public:
            Longest_Palindrome ( int length = 0 )
            {
                Length = length ;
                          // Making room for string
                        String = new char [Length] ;
                        Input (  )  ; // For Input the string

            }
            ~Longest_Palindrome ( )
            {
                delete String;
            }

            void Input ( )
            {

                    cout <<"\n Enter String : " ;
                    cin.getline ( String , Length+ 1 ) ; // 1 FOR '\0'
            }

            void show ( )
            {
                // Printing String
                cout << "\nInput String: " << String <<"\n Length = " << Length ;
            }

// T/S-------->             O(n^2)  / O(n^2 ) Solution's
            // Longest Palindrome Subsequence
            void Longest_Palindrome_Subsequence ( ) ;

            // Longest Palindrome Substring
            void Longest_Palindrome_Substring ( ) ;

// T / S  --------> O(n^2 ) / O(1) solution's

            // Longest Palindrome Substring
            void Center_Check ( int &l , int &r) ;
            void Longest_Palindrome_Substring_Space_Optimized ( ) ;

// T / S  --------> O(n ) / O(n) solution's

            // Longest Palindrome Substring
            void Longest_Palindrome_Substring_SpaceAndTime_Optimized ( ) ;


};

void Longest_Palindrome :: Longest_Palindrome_Subsequence ( )
{
    /* Logic:
    Let L[i][j] = Denote the lenght of Longest Palindrome string from A[i....j]
    base case: L[i][i] = 1 Since every character is a palindrome of lenght 1

                    L[i+1][j-1] + 2                                 if A[i] == A[j]
    L[i][j]   =
                    MAX { L[i+1][j] , L[i][j-1] }               otherwise

First case : L[i+1][j-1]   + 2 ;     here we increase i and decrease j Since A[i] and A[j] is matched so we move next character of ith index (forward ) and next character of jth index(backward)
                                            adding 2 because we found LPS of 2 character Since A[i] and A[j] is matched
Seconde case:        MAX { L[i+1][j] , L[i][j-1] } ; here if A[i] and A[j] is not matched then we go either forward means i+1 ( next character of ith index )
                                                and j ( same character as previous) or  fBackward means i
                                                ( same character of ith index ) and j - 1 ( next character ) since no LPS found so we add 0

    */
    // Create a memory Table
    int L [Length][Length] ;


    // Index variable for String Traversing
    int k , i , j ;

    for ( i = 0 ; i<Length ; i++)
    {
            for ( j =0 ; j<Length ; j++)
                    L [ i ] [ j ]  = 0  ;

    }

    // Base Case ; Since each character is LPS so :L[i][i] = 1
    for ( i = 0 ; i<= Length ; i++)
                        L [ i ] [ i  ] = 1 ; // base case

    //Filling the L using above formula: since we checked first two character so start with next one
    for ( k = 2 ; k<=Length ; k++)            //Since indexing start from 0; Complexity O(n - k )
    {
        // K means firnd all the Palindrome of lenght K

        // since we are founding Palindrome of lenght K (intially 2) then we match character in the gap of K means  leaving K-1 character in between
        // Since K = 2 ( intially ) so i (index from where we start )  can go up to Lenght - k ( for ex. n = 9 and k = 2 so 9 - 2 = 7 means i = 0.....6
        //since when i = 6 and j = 8 we are done
        for ( i = 0 ; i<(Length - k +1 ) ; i++ )       // Complxity : O( lenght - k )
        {
            // Since j should point to be the element leaving k - 1 element in between i and j
                j = i + k -1 ;

/*
        if ( String[i] == String [j] && k == 2 )
         L [ i ] [ j ] = 2 ;

        else
    */

         //is A[i] and A[j] are equal
         if ( String[ i ] == String[ j ] )
            {
                L[ i ] [ j ] = 2 + L [ i + 1 ][ j - 1  ] ;
            }
            else // if not matched
                    L[ i ] [  j ] = ( L[ i + 1 ] [ j ] > L [ i ][ j -1 ] ? L[ i + 1 ] [ j ] : L [ i ][ j -1 ]  ) ;
        }
    }


cout <<"\n  Longest_Palindrome_Subsequence of lenght : " << L[0][Length-1] << endl;

}

void Longest_Palindrome :: Longest_Palindrome_Substring ( )
{

    /* Logic:
    Let L[i][j] = Denote the lenght of Longest Palindrome sub-string from A[i....j]
    base case: L[i][i] = 1 Since every character is a palindrome of lenght 1

                 L[i][i] = 1 // Base case

                 L [ i ] [ j ] =     L[ i ] [i + 1]   if A[ i ]   == A[ j ]         for 1<= i <= j <= n- 1

                                        1                           if A[i]  = = A [j]
                 L [ i ] [ j ] =
                                        0                           otherwise
    */

    // Create a memory Table
    bool L [Length][Length] ;
    int longest_string_Begain_from_here = 0 ;

    // Index variable for String Traversing
    int k , i , j ;
    int maxLen = 1 ;

    for ( i = 0 ; i<=Length ; i++)
    {
            for ( j =0 ; j<=Length ; j++)
                    L [ i ] [ j ]  = 0  ;

    }
    // Base Case ; Since each character is LPS so :L[i][i] = 1
    for ( i = 0 ; i< Length ; i++)
    {
                      L [ i ] [ i  ] = 1 ; // base case

                if ( String [i] == String[i+1])
                {
                            L[ i ][ i+ 1 ] = 1 ;
                            longest_string_Begain_from_here = i ;
                            maxLen = 2 ;

                }

    }


/*
cout <<"\n" ;
    for ( i = 0 ; i<=Length ; i++)
    {
            for ( j =0 ; j<=Length ; j++)
                  cout <<  L [ i ] [ j ] << "    " ; //  = 0  ;
cout <<"\n" ;
    }
*/

    //Filling the L using above formula: since we checked first two character so start with next one
    for ( k = 3 ; k<=Length ; k++)            //Since indexing start from 0; Complexity O(n - k )
    {
        // K means firnd all the Palindrome of lenght K

        // since we are founding Palindrome of lenght K (intially 2) then we match character in the gap of K means  leaving K-1 character in between
        // Since K = 2 ( intially ) so i (index from where we start )  can go up to Lenght - k ( for ex. n = 9 and k = 2 so 9 - 2 = 7 means i = 0.....6
        //since when i = 6 and j = 8 we are done
        for ( i = 0 ; i<(Length - k +1 ) ; i++ )       // Complxity : O( lenght - k )
        {
            // Since j should point to be the element leaving k - 1 element in between i and j
                j = i + k -1 ;


         //is A[i] and A[j] are equal
         if ( String[ i ] == String[ j ] && L [ i+1 ][ j - 1 ] )
            {
                L[ i ] [ j ] = 1 ;
                longest_string_Begain_from_here = i ;
                maxLen = k ;
            }
        }
    }

cout <<"\nLongest_Palindrome_Substring  lenght : " <<maxLen << " starting from index : " << longest_string_Begain_from_here << endl;
}


// T / S  --------> O(n^2 ) / O(1) solution's

// Longest Palindrome Substring
void Longest_Palindrome ::Center_Check ( int &l , int &r)
{
    int n = Length ;

    // checking at center and its left and right as much as possible
    while ( l>=0 && r<= n-1 && String[l] == String [r] )
    {
        l-- ; // if match then move back
        r++ ;// if match then move forward
    }

    // finding the possition for substring
    // l indicated starting possition of substring
    // r indicate length of substring
    r =  r - l - 1 ;
    l = l+1 ;

    if ( r <= 0) // if length of substring is 0 or less
    {
        //we indicate we did found any substring which is paildrome
        l = -1 ;
        r = -1 ;
    }

}
void Longest_Palindrome :: Longest_Palindrome_Substring_Space_Optimized ( )
{

        if ( Length == 0)
        return ;

        int start_substring ; //  indicated start of substring LPS
        int len_substring ; // indicate lenght of substring LPS
        int temp_start ;
        int temp_len ;
        //Base case
        // Since every character is by default a palirome string so for base case we assume
        start_substring = 0 ; // substring starting at 0th character
        len_substring = 1 ;  // having lenght len_substring  = 1
        temp_start = 0 ;
        temp_len = 1 ;


        for ( int i = 0 ; i<Length ; i++ ) // for all character of String
        {
            temp_start= i ;
            temp_len = i ;
            Center_Check( temp_start, temp_len) ;

   // checking boundry condition
            if ( ! ( temp_start== -1 && temp_len== -1) )
                    {
                        if ( temp_len > len_substring )
                        {
                            start_substring = temp_start ;
                            len_substring = temp_len ;
                        }
                    }

            temp_start= i ;
            temp_len = i + 1;
            Center_Check( temp_start, temp_len ) ;


   // checking boundry condition
            if ( ! ( temp_start== -1 && temp_len== -1) )
                    {
                        if ( temp_len > len_substring )
                        {
                            start_substring = temp_start ;
                            len_substring = temp_len ;
                        }
                    }
        }

        cout <<"\n Substring Start from index : " << start_substring << " having length : " << len_substring ;
}

int main ( )
{
    int length ;
    cout <<"\n Enter the lenght of String : " ;
    cin >> length ;
     cin.get() ;


    Longest_Palindrome LP ( length) ;

    LP.show();
    cout <<"\n\n Lonest Palindrome Subsequence : \n" ;
    LP.Longest_Palindrome_Subsequence();

    cout <<"\n\n Longest_Palindrome_Substring non space optimieze ouput: \n" ;
    LP.Longest_Palindrome_Substring();

    cout <<"\n\n Space optimized output:\n" ;
    LP.Longest_Palindrome_Substring_Space_Optimized();

}
