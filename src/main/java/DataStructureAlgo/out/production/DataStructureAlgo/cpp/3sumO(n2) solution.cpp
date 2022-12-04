//3Sum problem
//Given a set S of n integers, are there elements a, b, c in S such that a + b + c = 0?
// O(n^2 ) solution
/* Algo:
First sort the array in O(nlonn) time
sort(S);
then

 for i=0 to n-3 do
    a = S[i];
    k = i+1;
    l = n-1;
    while (k<l) do
       b = S[k];
       c = S[l];
       if (a+b+c == 0) then
          output a, b, c;
          exit;
       else if (a+b+c > 0) then
          l = l - 1;
       else
          k = k + 1;
       end
    end
 end

loop will take O(n^2) and O(n^2) > O(n logn ) so O(n^2)
 */
#include <iostream>
using namespace std ;

template <class T>
void Quick_Sort ( T *A , unsigned int start , unsigned int end )
{
	// Ending Point of Recursion
	if ( start >=end)
	return ;

	int i = start ;
	int j = end-1 ;

	float pivote_element = A[start] ; // pivote Element in which respect Quick sort is apply
	while ( i <= j )
	{
		if ( A [i ] > A[j] )
		{
			int temp = A[i] ;
			A[i] = A[j] ;
			A[j] = temp ;
		}
		if ( pivote_element == A[j])
		i++ ;
		else
		j-- ;
	}
	// Tail Recursion
	Quick_Sort (A, start , i-1 ) ;
	Quick_Sort (A, j+1, end ) ;
}

int main ()
{

    int S[]= { 5,-6,-2,8,-9,2,0,-4} ;
    int a ,b,c ;
    int required_sum;
    unsigned int size = sizeof(S) / sizeof(int) ;
    cout <<"\n enter the required_sum: "  ;
    cin >> required_sum ;
/* sorting using quick sort */
    Quick_Sort(S, 0 , size);
    for ( int i =0 ; i < size; i++)
    cout <<S[i]<< " " ;

    /* 3sum procedure */
    for ( int i = 0 ; i<size-3 ; i++)
    {
        a = S[i] ;
        int k = i+1 ;
        int l = size-1 ;
        while ( k < l)
        {
            b = S[k] ;
            c = S[l] ;
            if ( a+b+c == required_sum)
            {
                cout <<"\nare: " << a << "," << b <<"," << c ;
                k++; l--;
            }

            else if ( a+b+c > required_sum )
                l--;
                else k++ ;
        }
    }

}
