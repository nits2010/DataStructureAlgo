//Print a given matrix in spiral form
#include <iostream>
#include <cstdlib>
#define R 5
#define C 6
using namespace std ;
void spiralPrint(int Row_end , int Col_end, int A[][C])
{
    Row_end--; Col_end--;
    int Row_start = 0 , Col_start = 0 ;
    int iterator=0;
    while ( Row_start <= Row_end && Col_start <= Col_end)
    {
        for ( iterator = Col_start ; iterator<=Col_end ; iterator++)  //Proceeding left to right for that row.
            cout <<A[Row_start][iterator] <<" " ;
        Row_start++ ;

        for ( iterator = Row_start ; iterator<=Row_end; iterator++) //Proceeding top to bottom for that col
                cout <<A[iterator][Col_end] <<" " ;
        Col_end-- ;

        if ( Row_start < Row_end) //Proceeding right to left for that row
        {
            for ( iterator = Col_end ; iterator>=Col_start ; iterator--)
                    cout <<A[Row_end][iterator] <<" " ;
            Row_end--;
        }
        if ( Col_start < Col_end) //Proceeding bottom to top for that col
        {
            for ( iterator = Row_end ; iterator>=Row_start ; iterator--)
                cout <<A[iterator][Col_start]<<" ";
                Col_start++;
        }


    }

}


int main()
{
    int a[R][C] = { {1, 2, 3, 4, 5, 6},
                    {7, 8, 9, 10, 11, 12},
                    {13, 14, 15, 16, 17, 18},
                    {19, 20, 21, 22, 23, 24},
                    {26, 27, 28, 29, 30, 31}
                 };
spiralPrint(R, C, a);
return 0;
}
