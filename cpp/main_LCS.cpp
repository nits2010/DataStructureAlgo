//LCS main Function
#include "Longest common Subsequence(LCS).h"
#include <cstring>
using namespace std ;

int main ()
{

const char *X = "BCABDCAA";
//"nitin" ;
 const char *Y = "BDCAA" ;

    int length_x = strlen(X) ;
    int length_y = strlen(Y)  ;
    cout <<"\n len: " << length_x << " y : " << length_y ;
    cout <<"\n Without DP : Lenght of Longest Common Subsequenc in \nX: " << X << " \nY : " << Y << "\n is " << LCS_Using_Recursion_WithoutDP(X , Y , length_x , length_y , 0 , 0 ) ;

    cout <<"\n\n Using DP : Top Down \n Lenght of Longest Common Subsequenc in \nX: " << X << " \nY : " << Y << "\n is " << LCS_Using_Top_Down_DP(X , Y , length_x , length_y , 0 , 0 ) ;

    cout <<"\n\n Using DP : Bottom Up \n Lenght of Longest Common Subsequenc in \nX: " << X << " \nY : " << Y << "\n is " << LCS_Using_Bottom_Up_DP(X , Y , length_x , length_y , 0 , 0 ) ;

    cout <<"\n\n Using DP : Memory Efficient \n Lenght of Longest Common Subsequenc in\nX: " << X << " \nY : " << Y << "\n is " << LCS_Using_DP_Memory_Efficient(X , Y , length_x , length_y , 0 , 0 ) ;

    return 0 ;
}
