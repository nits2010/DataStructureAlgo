#include <iostream>

#include <cstring>
using namespace std ;
void Reverse ( char *str , char s )
{

    if ( !str ) return  ;

    static int len = strlen ( str ) ;
    static int i = 0  , j = 0 ;

    if ( i > len )  return  ;

    Reverse( str , str[i++] ) ;
    str[j++] = s ;
}
int main ()
{
    char S[]="ABCDEFGHI" ;
    Reverse ( S , '\0') ;
    cout << " S : " << S ;
}
