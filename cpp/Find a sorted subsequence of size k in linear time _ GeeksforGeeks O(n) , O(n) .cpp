//Find a sorted subsequence of size 3 in linear time _ GeeksforGeeks O(n)/O(n) solution
/*

Basic Logic:
Logic is very simple
actuall we have first find the minimum value .once we encounter minimum value at index i we need to find greater value
greater than previous value
    {12, 11, 10, 5, 6, 2, 30,5,3,32,2,40};
first we found minimum value 11 then 10 then 5 (since 6 > 5 )
now we find greater value'MinStepsInfiniteGrid which is greater to previous one
we have 5 now next is 6 next is 30 next is 32 and 40

another Logic:
1) Create an auxiliary array smaller[0..n-1]. smaller[i] should store the index of a number which is smaller than arr[i] and is on left side of arr[i]. smaller[i] should contain -1 if there is no such element.
2) Create another auxiliary array greater[0..n-1]. greater[i] should store the index of a number which is greater than arr[i] and is on right side of arr[i]. greater[i] should contain -1 if there is no such element.
3) Finally traverse both smaller[] and greater[] and find the index i for which both smaller[i] and greater[i] are not -1.
  do step 3 for k index
Answer is store in "Ans" array

  */
#include <iostream>
#include <cstdlib>
using namespace std ;
// A function to fund a sorted subsequence of size 3

// this function checks, does input array is sorted in incresing oreder or not ?
// if its is then there is no need to calculate smaller and greater at all
bool Check_For_Incressing_Order( int arr[], int n , int k, int *Ans)
{
    int i=0 ;
    for ( i = 0 ; i< n-1 ; i++)
        {
            if ( arr[i] < arr[i+1])
            continue ;
            else
            break ;
        }


    if ( i == n-1 )
    {
        int Ans_index = 0 ;
        for ( Ans_index=0; Ans_index<k; Ans_index++)
            Ans[Ans_index] = arr[Ans_index] ;

        return 1 ;
    }
    else
    return 0 ;

}

bool findKNumbers(int arr[], int n , int k , int *Ans )
{
   int max = n-1; //Index of maximum element from right side
   int min = 0; //Index of minimum element from left side
   int i, Ans_index = 0;

   bool flag = 0 ; // this flag is use in this case if the input array is sorted in incressing order



   // checking that does input array is sorted in incresing oreder or not
    flag = Check_For_Incressing_Order(arr,n,k,Ans) ;

    int *greater ;
    if ( flag == 0)
    {
   // Create another array that will store index of a
   // greater element on right side. If there is no greater
   // element on right side, then greater[i] will be -1.
        greater = new int[n] ;
        greater[n-1] = -1;  // last entry will always be -1

        for (i = n-2; i >= 0; i--)
        {
            if (arr[i] >= arr[max])
            {
                max = i;
                greater[i] = -1;
            }
            else
            greater[i] = max;
        }
   }
   else
   {
            return true ;
   }


   // Create an array that will store index of a smaller
   // element on left side. If there is no smaller element
   // on left side, then smaller[i] will be -1.
   int *smaller = new int[n];
   smaller[0] = -1;  // first entry will always be -1
   for (i = 1; i < n; i++)
   {
       if (arr[i] <= arr[min])
       {
          min = i;
          smaller[i] = -1;
       }
       else
          smaller[i] = min;
   }


   // Now find a number which has both a greater number on
   // right side and smaller number on left side
   cout <<"\n\n\n" ;
   for (i = 0; i < n; i++)
   {
       if (smaller[i] != -1 && greater[i] != -1)
       {

    // puting the ans in Ans according to k
    // to avoid the multiple if we need for loop which takes O(k) excution but both are (using if or loop) are equal
    // both need k+1 checks

              if ( k == 1 || k > 1)
              {
                  Ans[Ans_index++] = arr[smaller[i]] ;
                    if ( Ans_index == k)
                        {
                             delete smaller ;
                             delete greater ;
                             return true ;
                        }
              }
              if ( k == 2 || k>2)
              {       Ans[Ans_index++] = arr[i] ;
                      if ( Ans_index == k)
                        {    delete smaller ;
                             delete greater ;
                             return true ;
                        }
                        }


              Ans[Ans_index++] = arr[greater[i]] ;

              i = Ans[Ans_index-1] ;

              break ;
          //printf("%d %d %d", arr[smaller[i]],
            //     arr[i], arr[greater[i]]);

       }
   }
   for ( ; i<n ; i++)
   {
       if ( Ans_index!=k)
        {
             if ( greater[i]!=-1)
             Ans[Ans_index++] = greater[i] ;
        }
        else break ;
   }


   // If we reach number, then there are no such 3 numbers
  if ( Ans_index!=k)
   {
       cout << "No such k element found";
        delete smaller ;
        delete greater ;
        return false;
   }
   // Free the dynamically alloced memory to avoid memory leak
   delete [] smaller;
   delete [] greater;

   return true;
}

// Driver program to test above function
int main()
{
    int arr[] ={1,2,3,4};//{12, 11, 10, 5, 6, 2, 30};
    int k ;
    cout <<"\n Enter the value of K:" ;
    cin >> k ;

    int *Ans = new int [k] ;//Answer array
    int n = sizeof(arr)/sizeof(arr[0]);

    if ( findKNumbers(arr, n, k , Ans))
    for ( int i=0; i <k ; i++)
        cout <<Ans[i]<<" " ;
    return 0;
}
