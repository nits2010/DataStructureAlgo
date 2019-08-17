//Count smaller elements on right side
/*

this logic use either array or stack
we use array .
Logic:
we travers the main array from right to left and keep inserting the current element in the temp array  temp array always a sorted one
ex.
index         0  1 2 3 4 5
main array    12 1 3 0 11 1
for this from right to left
i = 5 ele= 1 since its right most so put zero in that index of Ans. array and push it into temp array

index              0  1 2 3 4 5
main array    12 1 3 0 11 1
temp array    1
Ans. array                       0

i = 4: ele = 11 and compair this element to temp array index k-1 ( its k = 1 now ) so we compair it to index 0 means 1
which shows its max so all the element before index 0 are smaller so number of element we (0-0+1) = 1

index             0  1 2 3 4 5
main array    12 1 3 0 11 1
temp array    1  11
Ans. array                    1 0

i = 3 ele = 0 compair it wiht index k=2-1 = 1 so 11 > 0(ele) shift 11 to right and put 0 at his previous place MinStepsInfiniteGrid.t
temp array:  1 0 11
now again compair 0 with its previous index means with 1 and 1> 0  so shift 1 to right and put 0 at his place
temp array:  0 1 11  anc comparision over  hence 0


index         0  1 2 3 4 5
main array    12 1 3 0 11 1
temp array    0  1 11
Ans. array            0 1 0

follow same procedure

index         0  1 2 3 4 5
main array    12 1 3 0 11 1
temp array    0  1 1 3 11 12
Ans. array    5  1 2  0 1 0

complexity :
total complexity : O(n)
because working on temp array will be O(1 ) average case and < O(n) in worst case
*/
#include <iostream>
#include <cstdlib>
using namespace std ;

//This function checks for Sorted Sequence if its find this sequence
//then its calculate Ans_array

short checkForSorted (int *A , size_t size, int *Ans_array )
{
    int i =0,Ans_index=0 ;
    short Sorted_order = -1 ; // -1 for in case all element are equal ;
    //0 for decressing and 1 for incressing sequence checking for
    //

    for ( i =1 ; i<size ; i++)
    {
            if ( A[i-1] < A[i])
            {
                Sorted_order = 1 ; // incressing sequence
                break ;

            }
            else if ( A[i-1] > A[i])
                {
                    Sorted_order = 0 ; // Decressing sequence

                    break ;
                }
            else
            {

                Sorted_order = -1 ; // for equality
            }


    }
    if ( i == size-1   || Sorted_order == -1) // since if the all element are equal or except last one
    {
        if ( i==size-1 && (!(Sorted_order==-1)) )// if all element are equal except last one then
        {

                if ( Sorted_order == 0) // then it must be either decressing
                {
                    // then put 1 in all element except last one
                    for ( Ans_index = 0 ; Ans_index < size-1 ; Ans_index++)
                        Ans_array[Ans_index] = 1 ;
                     //put 0 for last one
                    Ans_array[Ans_index] = 0 ;

                }
                else  // then it must be either decressing
                {
                    // then put 0  in all element
                    //which is already done

                }
        }

        return Sorted_order ; // tell to next procedure that all element are equal
    }
    else
    {

        if ( Sorted_order==0) // if the order is decressing order
            {


                for ( i = 1; i<size ; i++)
                {

                    if ( A[i-1] > A[i])
                    {
                        Ans_array[Ans_index] =  0 ; // storing the resulf from right to left
                        Ans_index -- ;
                    }
                    else
                    break ;
                }
            }
            else
            {

                Ans_index = size-1 ;
                Ans_array[0] = Ans_index ;
                for ( i = 1; i<size ; i++)
                {
                    if ( A[i-1] < A[i])
                    {
                        Ans_array[Ans_index] =  size - Ans_index  - 1 ;  ; // storing the resulf from right to left
                        Ans_index -- ;
                    }
                    else
                    break ;
                }
            }


    }
   // cout <<"\n final i =" << i ;

    if ( i == size ){
        return Sorted_order ; }// for telling next procedure that we Don't need to calculate Ans_array
    else
    {

        for ( i = 0 ; i<size ; i++)
        Ans_array[i] = 0 ;


        return 3 ; // for telling next procedure that we need to calculate Ans_array

    }

}

// THis funtion shift the temp_array for making sorted temp array and calculate how many element are less then in left side of temp array

int shift_left_side ( int *temp_array, int &Last_index , int element )

// Last index is for position of last element
{
    // element is the element which is to be insert

int lesser_element_count =0 ;
    if ( temp_array[Last_index] <= element ) // checking that last element in temp array is < or = to current element
    {
        if( temp_array[Last_index] == element) // if last element is = to current element than total less element are Last index
        lesser_element_count = Last_index ;
        else
        lesser_element_count = Last_index + 1 ; // other wise more then 1

        temp_array[++Last_index] = element ;
    }
    else // if last element of temp array is > current element then we need to shift it
    { lesser_element_count = Last_index ;
     // assuming only last element is greater to current element in this case less element on left

                                         //side is Last index
        temp_array[++Last_index] = element ; // inserting element at last + 1
        for ( int i = Last_index-1 ; i>=0 ; i--)
        {
            swap(temp_array[i+1] , temp_array[i] ) ;
            if ( i-1 >=0 && (temp_array[i] <= temp_array[i-1]))
            // " <= " due to that we dont want that if 1 1 is sequence then ans is:  1 0 rather it should be  0 0
             {
                 lesser_element_count -- ;
             }
             else
             break ;

        }
    }
    return lesser_element_count ;

}
// This procedure Calculate Ans array

bool Calculate_Count_of_Right_side ( int *A , size_t size , int *Ans)
{
int return_value = checkForSorted(A,size,Ans) ;
cout <<"\n return value: " << return_value <<endl;

    if ( return_value == 0 || return_value == 1 || return_value == -1)
    {
        //then no need to calculate Ans array
        return true ;
    }
    else
    {

        // now we need to calculate Ans array
        int i = size-1 ;
        Ans[i++] = 0 ; //since for tha last element of main array , there is no element in right side so count = 0
        int temp_array[size]  ;
        int temp_index = 0 ;
        temp_array[temp_index] = A[size-1] ; // put last element of main array in temp array in advance


        for ( i = size-2 ; i>=0 ; i--)
        {

            int shift = shift_left_side( temp_array, temp_index , A[i]) ; // calculating Ans array using Shift function
      //  cout <<"\n come shift : " << shift ;
            Ans[i] = shift ;
        }



    }
    return true ;

}

int main ()
{
    int Arr[] = {1,1,1,2};//{1,1,1,1,0};//{1,1,1,1,1,1};//{1,2,5,3,6,9,10,2,0,1,6};//{1,2,3,4,5};//{5,4,3,2,1};//////{12,1,3,0,11,1} ;
   const size_t size = sizeof(Arr) / sizeof(Arr[0]) ;
    int Ans_array[size]={0};
    cout <<"\n Ans array\n";
if ( Calculate_Count_of_Right_side(Arr, size , Ans_array) )
   {
   cout <<"\n"  ;
    for ( int i =0 ; i<size; i++)
        cout <<Ans_array[i] << " ";
   }


}
