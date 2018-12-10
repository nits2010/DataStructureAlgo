// Coin Change problem
#include <iostream>
#include <cstdlib>
#include <cmath>
#define Infinity 1000000
using namespace std ;

// Input
void Input ( int *D , int &coins , int &Pcent)
{

}

//bottom Up manner ; starting from amount 1 to Amount
void Coin_Change ( int *D , int Number_of_Coins , int P ) // D for coin denominations
{
    // Making Memory table for DP . of size Amount + 1
    /* Let C[p] be the minimum number of coins of denominations d1 , d2 , . . . , dk needed to make change for p cents. */
    int C[ P + 1] ;
    int Tracing_Back[ P+1 ] ;


    // for 0 cents, the value of the optimal solution is clearly 0 coins
    C[0] = 0 ; // Base Case
    Tracing_Back[0] = 0 ;
/* Logic:
 In the optimal solution to making change for p cents, there must exist some first coin di, where di ≤ p. Furthermore,
 the remaining coins in the optimal solution must themselves be the optimal solution to making change for p − di cents,
Thus, if di is the first coin in the optimal solution to making  change for p cents, then C[p] = 1 + C[p − di ];
i.e., one di coin plus C[p − di ] coins to optimally make change for p − di cents. We don’t
know which coin di is the first coin in the optimal solution to making change for p cents; however, we may
check all k such possibilities (subject to the constraint that di ≤ p), and the value of the optimal solution
must correspond to the minimum value of 1 + C[p − di], by definition.
Hence:
                            0                                        if p = 0
            C[P] =
                            min { 1 + C[p-di] } for all di<= p       if p>=1
*/

    int min;
    int coin ;

        // For all cent 1 to P
        for ( int p = 1 ; p<=P ; p++)
        {
                min =  Infinity  ; // setting min to 1000000000
            //for all coin of Denominations  di from 0 to Number of coins
            for ( int di = 0 ; di<Number_of_Coins ; di++) // Loop start from 0 because array indexing start from zero
            {

                if ( D [di] <= p && (p - D[di] ) >= 0 )
                {

                    if (  (min > ( 1 + C[ p - D[ di ] ]  ) ) )
                    {
                        min = 1 + C[ p - D[ di ] ]  ;
                        coin = di ;
                    }
                }
                C[ p ] = min ;
                Tracing_Back[p] = coin ;
            }
        }

        cout <<"\n  For making change of " << P  << " Rupess You need at least " << C[P] << " coins " ;
         int temp[Number_of_Coins+1] ;
        for ( int i =0 ; i<Number_of_Coins ; i++)
                    temp[i] = 0 ;

        //counting coin
        for ( int p = P ; p>0 ;)
        {
            temp [ Tracing_Back[p]] ++ ;
            p = p - D [ Tracing_Back [p] ] ;
        }

        //show
        for ( int i = 0 ; i<Number_of_Coins ; i++)
            {
                if ( temp[i] > 0 )
                {
                    cout<< endl <<D[i] <<" Rupess Coin  picked " << temp[i] <<" times" ;
                }
            }

}

int main ( )
{
        int *D, coins , Pcent ;

    cout <<"\n Ente the number of coins are there : " ;
    cin >> coins ;

    D = new int [coins] ;
    D[0] = 1 ; // base coin


    cout <<"\n Enter the  denominations of coins ( " << coins - 1 << " ) : ";
    cout <<"\n Identity\tDenomination\n" ;
     cout << 1 << "\t\t " << 1 <<endl; // for first coin

    for ( int i = 1 ; i<coins  ; i++ )
    {
        cout <<i+1 << "\t\t " ;
        cin >> D[i] ;
    }

    cout <<"\n Enter the P cent of which you make change : " ;
    cin >> Pcent ;


        Coin_Change ( D , coins, Pcent  ) ;

}


