
#include <iostream>
#include <cstring>
using namespace std ;
// Transform S into T.
// For example, S = "abba", T = "^#a#b#b#a#$".
// ^ and $ signs are sentinels appended to each end to avoid bounds checking
string preProcess(string s)
{
        int n = s.length();
        if (n == 0)
                return "^$";

        string ret = "^";
        for (int i = 0; i < n; i++)
                ret += "#" + s.substr(i, 1);
        ret += "#$";

return ret;
}

string longestPalindrome(string s)
{
    string T = preProcess(s);
    cout <<"\n Transform String : " << T ;

    int n = T.length();
    cout <<"   of length = " << n ;

    int *P = new int[n];
    P[0] = 0;

    int C = 0, R = 0;
    cout <<"\n Intially C ="  << C << " R : " << R ;

    for (int i = 1; i < n-1; i++)
    {
            int i_mirror = 2*C-i; // equals to i' = C - (i-C)
            cout << "\n i : " << i << " i_mirror: " << i_mirror ;

            P[i] = (R > i) ? min(R-i, P[i_mirror]) : 0;
            cout <<"\n P[ " <<i << " ] : " << P[i] ;

            cout <<"\n Entering in while Loop : \n" ;
        // Attempt to expand palindrome centered at i
        while (T[i + 1 + P[i]] == T[i - 1 - P[i]])
        {
            cout <<"\n Loop : before update  " <<"\n P[ " <<i << " ] : " << P[i]  ;
            P[i]++;
            cout <<"\n Loop :  after update :" <<"\n P[ " <<i << " ] : " << P[i]  ;

        }
        cout <<"\n Exit from while Loop : \n" ;

cout <<"\n Entering in if : \n" ;
    // If palindrome centered at i expand past R,
    // adjust center based on expanded palindrome.
        if (i + P[i] > R)
        {
            cout <<"\n if : before  i " << i << " C = " << C << " R =  " << R  <<"\n P[ " <<i << " ] : " << P[i]  ; ;
            C = i;
            R = i + P[i];
             cout <<"\n if : after i " << i << " C = " << C << " R =  " << R  <<"\n P[ " <<i << " ] : " << P[i]  ; ;
        }
        cout <<"\n\n Exit form if \n\n " ;
    }

cout <<"\n\n Printing T & P :\n\n " ;
    for ( int i = 0 ; i<n ; i++)
        cout <<i << "    " ;
cout <<"\n " ;
    for ( int i = 0 ; i<n ; i++)
        cout <<P[i] << "    " ;

        // Find the maximum element in P.
        int maxLen = 0;
        int centerIndex = 0;
        for (int i = 1; i < n-1; i++)
        {
                if (P[i] > maxLen)
                {
                    maxLen = P[i];
                    centerIndex = i;
                }
        }

        delete[] P;

        return s.substr((centerIndex - 1 - maxLen)/2, maxLen);
}


int main ()
{
string s = "abaaba" ;// "AGACBTAGA" ;
string s1 = longestPalindrome(s) ;
cout <<"\n\n\n" ;
cout <<s1 ;
}
