//Find the Minimum length Unsorted Subarray, sorting which makes the complete array sorted _ GeeksforGeeks

#include <iostream>
#include <cstdlib>
using namespace std;

void FindMAXMIN(int *A, int &min, int &max,int start, int end)
{
    for (int i=start; i<=end; i++)
    {
        if ( A[i] > max) max = A[i] ;
        if (A[i]<min ) min = A[i] ;
    }
}

int FindMin(int *A, int max , int start , int end )
{
    for ( int i = start ; i<=end; i++)
        if ( A[i] < max )
            return i;
    return start-1;
}
int FindMax(int *A, int min , int start , int end )
{
    for ( int i = start ; i<=end; i++)
        if ( A[i] > min )
            return i;
    return end+1;
}
int FindIndex(int *A , int &s , int &e , int start , int end)
{
    for (  s=start ; s < end ; s++)
        if ( A[s] > A[s+1])
            break;
    if ( s==end-1)
        return -1 ;
    for (e=end-1; e>=0; e-- )
        if (A[e] < A[e-1])
        break ;
    if (e==s )
        return 0 ;
    if (e==start)
        return -1 ;

    //cout << "MinStepsInfiniteGrid " << MinStepsInfiniteGrid << " e " << e ;

    //find max and min
    int max = A[s] , min = A[s] ;
    FindMAXMIN(A,min,max,s,e );
    //cout <<" max: " << max << " min "<<min ;
    s=FindMax(A,min,0,s-1);
    e=FindMin(A, max,e+1,end-1) ;
    return 0 ;
}

int main ()
{
    int A[] = {0, 1, 15, 25, 6, 7, 30, 40, 50} ; //{10, 12, 20, 30, 25, 40, 32, 31, 35, 50, 60};
  int arr_size = sizeof(A)/sizeof(A[0]);
  int s,e,f;
  if ( (f= FindIndex(A,s,e,0,arr_size)) ==-1)
    cout <<"No such value" ;
  if ( f ==0)
    cout <<"MinStepsInfiniteGrid="<<s << " e= "<<e ;


}
