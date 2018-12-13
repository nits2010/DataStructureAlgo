#include<iostream>
#include<cstdlib>
#define false 0
#define true 1
using namespace std;
/* Helper functions to get minimum and maximum in an array */
int getMin(int arr[], int n);
int getMax(int arr[], int n);

/* The function checks if the array elements are consecutive
  If elements are consecutive, then returns true, else returns
  false */
int areConsecutive(int *arr, int n)
{

    if ( n <  1 )
        return false;

    /* 1) Get the minimum element in array */
    int min = getMin(arr, n);

    /* 2) Get the maximum element in array */
    int max = getMax(arr, n);

    /* 3) max – min + 1 is equal to n then only check all elements */
    if ( max – min  + 1 == n)
    {
        int i;
        for(i = 0; i < n; i++)
        {
            int j;
            if ( i < 0 ) { cout << "\n HEEHEHEH"  ; }
            if (arr[i] < 0)
                j = -arr[i] – min;
            else
                j = arr[i] – min;

            // if the value at index j is negative then
            // there is repitition
            if ( j < 0 ) { cout << "\n HEEHEHEH"  ;}

            if (arr[j] > 0)
                arr[j] = -arr[j];
            else
                return false;
        }

        /* If we do not see a negative value then all elements
           are distinct */
        return true;
    }

    return false; // if (max – min  + 1 != n)
}

/* UTILITY FUNCTIONS */
int getMin(int arr[], int n)
{
    int min = arr[0];
    int i;
    for (i = 1; i < n; i++)
        if (arr[i] < min)
            min = arr[i];
    return min;
}

int getMax(int arr[], int n)
{
    int max = arr[0];
    int i ;
    for (i= 1; i < n; i++)
        if (arr[i] > max)
            max = arr[i];
    return max;
}

/* Driver program to test above functions */
int main()
{
    int arr[]= {7,6,5,4,3,5};
    int n = sizeof(arr)/sizeof(arr[0]);
    if(areConsecutive(arr, n) == true)
        cout << "yes" ;
    else
        cout << "no" ;
    //getchar();
    return 0;
}
