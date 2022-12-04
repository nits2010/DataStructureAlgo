/* Given an array of element in the range of 1 - n ( every element are present from 1 - n )
but only two element are repeated twice ( size become n + 2 )
find those repeated number.
*/
/*
Logic :
step1: XOR of all element of Array and from 1 to n gives XOR of repeated element say x and y
        so this step gives (x XOR y ) i.e. XOR = x ^ y
step2: now we find the set bit in XOR of x and y means from XOR
        in this step we find the first set bit from right towards left and bit postion to the power 2 gives ans.
        for ex. say XOR = 7 then 7 & (~6)= 7 & 1 = 1 means 0th bit (2 to the power 0 = 1)
        for ex. say XOR = 12 then 12 & (~11) = 12 & 4 = 4 means 2th bit is set ( 2 to the power 2 = 4 )
step3: In this step we find all those no. from array , whose set bit same as which we find in above step
        and do XOR with right set bit no. (store in x)
        and the remeaining no. will be XOR with right set bit no. and store in y
        after this step we will get one of the repeated no. in x and y
step4: do the same procedure from 1 to n , time XOR with x and y
        at the end we will get x and y

*/
#include <iostream>
#define n 6
using namespace std ;

void FindRepeatedElement(int *A , int size)
{
    int XOR = A[0] ;
    int i ;
    // Step 1
    for ( i = 1 ; i<size; i++) // size = n + 2
        XOR ^=A[i] ;
    cout <<"\n XOR of Array element is :" << XOR;

    for ( i = 1; i<=n ; i++)
        XOR^=i;
    // End of Step 1
    cout <<"\n XOR of Array element and from 1 to n is :" << XOR ;

    //Step 2
    int x=0, y = 0 , set = 0 ;
    // this step extracting the first set bit from right and corresponding no. ( power ( 2 , possition of set bit) )
    set = XOR & ~(XOR-1) ; // step 2 end




// step 4 ; getting one of the no. in x and y
    for ( i =0; i<size ; i++ )
        if (A[i] & set)  // find those no. whose bit is set like Set
            x= x^A[i] ;
        else
            y = y^A[i] ;


cout << " x = " << x << " y=" << y ;
// step4: find the second repeated no.
    for ( i = 1 ; i<=n ;i++)
        if ( i & set)
            x = x^i ;
        else
            y = y^i ;
    cout <<"\n x = " << x << " y = " << y ;

}
int main ()
{
    int A[]={1,2,3,2,4,5,5,6} ;
    FindRepeatedElement(A,sizeof(A)/sizeof(A[0])) ;
    cin.get();
}
