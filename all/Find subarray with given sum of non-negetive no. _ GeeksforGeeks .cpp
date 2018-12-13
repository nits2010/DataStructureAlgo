//Find subarray with given sum of non-negetive number array_ GeeksforGeeks
/* Logic
1> moving left to right and store the current sum
if current sum > sum then move tail ahead
if current sum < sum move head ahed
*/

#include <iostream>
#include <cstdlib>
using namespace std ;

bool FindGivenSumSubarray(int *A,size_t size, int &tail , int &head , int sum)
{
    int current_sum = 0 ;

    if ( current_sum == sum || sum < 0 )
    return false  ;

    for ( tail = 0 , head=0 ; head< size ; )
    {

        current_sum +=A[head] ;
        if (current_sum==sum)
        return true ;
        cout <<"\n c:" << current_sum ;
        if ( current_sum > sum)
        {
            current_sum -= A[tail] ;
            current_sum -= A[head] ;
            tail++;
        }
        else head++ ;



    }
    return false ;
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

    int Sum = 0 ;
    cout <<"\n Enter the Sum=" ;
    cin >> Sum ;

    int tail=0 , head = 0 ;
    if ( FindGivenSumSubarray(A,size,tail,head,Sum) )
    {
        cout <<"\n\n:";
        for ( ; tail<=head ; tail++)
        cout <<A[tail]<<" " ;
    }
    else cout <<"\n NO Such array found" ;
}
