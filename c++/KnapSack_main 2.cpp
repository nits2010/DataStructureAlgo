#include "KnapSack.h"

int main ()
{
    KnapScak KN ;

int i ;
cout <<"\n How many time u test the code : " ;
cin >> i ;
while ( i!=0 )
    {
        KN.Input () ;
        cout <<"\n\n Result when repetation not allowed: \n" ;
        KN.KnapScak_WithoutRepatation () ;


        cout <<"\n\n\n Result when repetation allowed: \n" ;
        KN.KnapScak_WithRepatation () ;

        i--;
    }


    return 0 ;
}
