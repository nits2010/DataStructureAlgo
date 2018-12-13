#include <iostream>
#include <cstring>
const int size = 256 ;
using namespace std ;

int * LCSS(char *) ;
int main ( void )
{
    char *string= new char[size];
    cout <<"\n Enter Your String : ";
    cin.getline (string , size ) ;
    int *Max = LCSS ( string ) ;
    cout <<"\n Your String: " << string <<" \n String length "<< strlen(string);
    cout <<"\n Length_of_the_longest_substring_without_repeating is :" ;
    for (int i = Max[1]; i<=Max[2]; i++)
    cout << string[i] ;
    cout <<"\n length: " << Max[0] ;
    return 0 ;
}
int * LCSS(char *string)
{
    int *Visited = new int[size] ;
    int *Max = new int [3] ;
    for (int i = 0 ; i<3 ; i++)
    Max[i] = 0 ;
    for (int i = 0 ; i<size; i++)
    Visited[i] = -1 ;

    int current_length = 1 , start = 0;
    Max[0] = 1 ;
    Visited[string[0] ] = 0 ;
    for (int i = 1 ; i<strlen(string) ; i++)
    {
        if ( Visited[string[i] ]!= -1 )
        {
            if ( (start<= Visited[ string[i]] ) && (i>= Visited[ string[i]]  ) )
            {
                current_length = i - Visited[ string[i] ] ;
                start = Visited[ string[i] ] + 1 ;
                Visited[ string[i] ] = i ;
            }
            else
                {current_length++ ;

                Visited[ string[i] ] = i ;
                }

        }
        else
            {
                current_length++ ;
                Visited[string[i] ] = i ;
            }
            if (Max[0] < current_length)
            {
                Max[0] = current_length ;
                Max[1] = start ;
                Max[2] = i ;
            }

    }

return (Max);
}

