//include header file for String MAtching
#include "String Matching Algorithm.h"

#define MAX 80
using namespace std ;
int main ( )
{
    int L_T , L_P ; // Length of Text String and Patter String

    char *Text , *Pattern; // text and patter string pointer

// Allocating memory for Text and Pattern String
    Text = new char [MAX] ;
    Pattern = new char [MAX] ;

    //Input the Text and pattern String
    cout <<"\n Input the Text String: \n" ;
    cin.getline(Text, MAX) ;

    cout <<"\n Input the Pattern String To be Match: \n"  ;
    cin.getline ( Pattern , MAX) ;

    //Obtaing length of String
    L_T = strlen ( Text ) ;
    L_P = strlen ( Pattern ) ;

    //if Pattern is bigger then Text
    if ( L_T < L_P )
    {
        cout <<"\n Your Pattern String Size is Greater then Text String Size, Can not be match...." ;
        cin.get() ;
        return 0 ;
    }

    //Creating object of String Class
    STRING      S(  Text , Pattern ,  L_T ,     L_P    ) ;
    int end_point ;
    int start_point = S.KMP(end_point) ;
   // cout <<"\n L_T : " << L_T << " L_P: "<< L_P ;

        if ( start_point == -1 )
                cout <<"\n String did Not Match " ;
        else
        {
            cout <<"\n String Matched from : "<< start_point << " to " << end_point ;
        }

    return 0;
}
