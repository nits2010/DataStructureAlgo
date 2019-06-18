// for 1D and 2D array
//Finding Maximum Sum in Array'MinStepsInfiniteGrid
#include <iostream>
#include <cstdlib>
using namespace std ;

class Kadans_Algorithms
{
            int *One_D ;
            int size ;
            int **Two_D ;
        public:
            Kadans_Algorithms ( )
            {
                One_D = 0 ;
                Two_D = 0;
            }

            ~Kadans_Algorithms ( )
            {
                delete One_D ;
                delete [] Two_D ;

            }

            // Input
            void Create_and_Input_One_D ( size_t size );
            void Create_and_Input_Two_D ( size_t size) ;

            //Finding Maximum Sum in Array
            int Maximum_Sum_Kadans_One_D ( int &start , int &end ) ;
            void View_One_D ( int start , int end ) ;

            void View_Two_D ( int x1 ,int y1 , int x2 , int y2) ;
            int Maximum_Sum_Kadans_Two_D(  int &x1 ,int &y1 , int &x2 , int &y2) ;


};

//Create and Input 1 D array
void Kadans_Algorithms ::Create_and_Input_One_D ( size_t size_i)
{
     size = size_i ;
    //Creating
    One_D = new int [size] ;


//Input
    cout <<"\n Enter" << size <<" values in array:  " ;
    for ( int i = 0 ; i<size ; i++)
    {
        cin >> One_D [ i ] ;
    }
}

//Create and Input 2 D array
void Kadans_Algorithms ::Create_and_Input_Two_D ( size_t size_i)
{
    size = size_i ;
    //Creating
    Two_D = new int *[size] ;
    for ( int i = 0 ; i<size ; i++)
            Two_D[i] = new int [size] ;

    cout <<"\n Enter" << size <<" values in array:  " ;
    for ( int i = 0 ; i<size ; i++)
    {
        for ( int j = 0 ; j<size; j++)
            cin >> Two_D [ i ] [ j ] ;
        cout <<"   \n" ;
    }
}

//Finding Maximum Sum in Array
int Kadans_Algorithms :: Maximum_Sum_Kadans_One_D ( int &start   , int &end)
{
    int i , j ;

    int current_sum = 0 , Max_sum = 1<<31;

//Boundry check; if all element are negitive then return a number which is near to 0
    //Conforming all are negitive or not
    for ( int i = 0 ; i < size ; i++)
    {
        if ( One_D[ i ] < 0 )
        continue ;
        else
        break ;
    }

    //if i reached to end then all are negitive
    if (i == size )
    {
        //all are negitive then return max
        current_sum = One_D[0] ;
        for ( i = 1 ; i< size ; i++)
            if( current_sum < One_D[ i ])
                    {
                        current_sum = One_D[ i ] ;
                        start = end = i ;
                    }
        return current_sum ;
    }

    else
    {

        j =  i = 0 ;
        while ( i< size )
        {
            //Calculating Current sum
            current_sum += One_D[ i ] ;

            //if Current sum is maximum then max_sum then update
            if ( current_sum > Max_sum)
            {
                Max_sum = current_sum ;
                // Get the starting and ending index of subarray
                start = j ;
                end = i ;
            }
            else if ( current_sum < 0 ) // if current sum gets negtive then set it back to 0
            {
                //setting back to 0
                current_sum = 0 ;

                //for new sequenece update j
                j = i + 1 ; // Since at i there is negetive number

            }

            i++;
        }
        return Max_sum ;
    }
}

//View
void Kadans_Algorithms :: View_One_D  ( int start , int end )
{
    for (  ; start <=end ; start++)
    cout << One_D[ start ] << "   " ;
}

// 2 D array

int Kadans_Algorithms :: Maximum_Sum_Kadans_Two_D(  int &x1 ,int &y1 , int &x2 , int &y2)
{
    int temp_sum_row[size] ;
    int t ; // for temp sum
    int Max_Sum = 1<<31 , current_row_sum = 0 , start , end ;
     x1 = x2 = y1 =  y2 = 0  ;
     int j ;


    for ( int Row = 0 ; Row<size; Row++)
    {
        // intiallizing temp_sum_row array for current row to 0
        for ( int i = 0 ; i<size; i++)
            temp_sum_row[i] = 0 ;

// Eximine all row for Row
        for ( int x = Row ; x<size; x++)
        {
            // current temp sum
            t = 0 ;

            //current sum for xth row
            current_row_sum = 1<<31 ;

            j = 0 ;

            // start and end for max sum for current row
            start = 0 ;
            end = 0 ;

            //for all column
            for ( int i = 0 ; i<size; i++)
            {
            // intiallizing for temp_sum_row for current row corrospoinding to column
              temp_sum_row[i] = temp_sum_row[i] + Two_D[x][i] ;
              //update temp sum for xth row
              t += temp_sum_row[i] ;

              //if temp sum greater then current sum for xth row
              if ( t > current_row_sum )
              {
                  //update
                  current_row_sum = t ;
                  // take starting and end index for xth row max sum
                  start = j ;
                  end = i ;

              }
              // if negitive
              if ( t<0)
              {
                  //reset all
                  t = 0 ;
                  j = i+1 ;
              }
            }

            // Eximining max sum for sub array
            if ( current_row_sum > Max_Sum )
            {
                //update
                Max_Sum = current_row_sum ;
                x1 =x ;
                y1 = end ;
                x2 = Row ;
                y2 = start ;
            }
        }
    }

return Max_Sum ;
}

//View
void Kadans_Algorithms :: View_Two_D ( int x1 ,int y1 , int x2 , int y2)
{

        for ( ; x1 <= x2 ; x1++)
        {
            for ( int j = y1; j<=y2 ; j++)
                cout <<Two_D[x1][j]  << "  ";
            cout <<"\n" ;
        }
}

int main ()
 {
     Kadans_Algorithms K ;
     int N ;
     cout <<"\n Enter the size of array: " ;
     cin >> N ;
   K.Create_and_Input_One_D(N) ;
     int start , end ;
   int max_sum = K.Maximum_Sum_Kadans_One_D(start , end ) ;

     if ( start== end )
     {
         cout <<"\n Maximum Sum : " << max_sum ;
         cout <<"\n Start/end index : " << start <<" / " << end ;
              cout <<"\n element : " << max_sum ;
     }
     else
     {

        cout <<" Max Sum : " << max_sum ;
         cout <<"\n Elements are " ;
            K.View_One_D(start , end ) ;
     }

     int x1,y1,x2,y2 ;
     K.Create_and_Input_Two_D(N) ;
       int max_sum_2d = K.Maximum_Sum_Kadans_Two_D(x1,y1,x2,y2) ;
       cout <<"\n MAx Sum: " << max_sum_2d ;
       cout <<"\n Start from index " << x2 << " " << y2 << " and end up " << x1 << "  " << y1 ;
       cout <<"\n\n Subarray is : \n" ;
       K.View_Two_D(x2,y2,x1,y1) ;
 }
