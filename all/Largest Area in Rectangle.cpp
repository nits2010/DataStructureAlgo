//Largest Area in Rectangle
#include <iostream>
#include <stack>

using namespace std ;
int main ( )
{
    int arr[10] = {9,6,2,1,3,5,4,7,2,6};
    int area[10]={0}  , t;
     stack<int> S ;
    for ( int i = 0 ; i<10 ;  i++)
    {
        while (!S.empty() )
        {
            if ( arr[i] <= arr[S.top()])
            {
                S.pop() ;
            }
            else
                break ;
        }
        if ( S.empty())
        t = -1 ;
        else
        t = S.top() ;
        cout << "i: " << i << "   " << t <<"\n";
        area[i] = i - t - 1 ;
        S.push(i) ;

    }

    for ( int i = 0 ; i<10 ;  i++)
        cout << area[i] << "   " ;


}
