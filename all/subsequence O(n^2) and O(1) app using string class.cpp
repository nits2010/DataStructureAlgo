#include <iostream>
#include <cstring>
using namespace std ;
string expandAroundCenter(string s, int c1, int c2)
 {
        int l = c1, r = c2;
        cout <<"\n before:  l "<< l << " r = " << r ;
        int n = s.length();
        while (l >= 0 && r <= n-1 && s[l] == s[r])
        {
                l--;
                r++;
        }
        cout <<"\n L = " << l << " r = " << r << " l+1 "<<l+1 <<"  r-l-1  " << r-l-1 ;
    return s.substr(l+1, r-l-1);
}

string longestPalindromeSimple(string s)
 {
    int n = s.length();
    if (n == 0)
            return "";
    string longest = s.substr(0, 1); // a single char itself is a palindrome

    for (int i = 0; i < n-1; i++)
     {
         cout <<" \n\n i : " << i ;
        string p1 = expandAroundCenter(s, i, i);
        cout <<"\n i,i = " << i << " re: " << p1 ;
        if (p1.length() > longest.length())
                longest = p1;
        string p2 = expandAroundCenter(s, i, i+1);
        cout <<"\n i,i+1 = " << i << " re: " << p2 ;

        if (p2.length() > longest.length())
            longest = p2;

            cout <<"\n till longest : " << longest ;
    }

return longest;
}

int main ()
{
string s = "AGACBTAGA" ;
string s1 = longestPalindromeSimple(s) ;
cout <<s1 ;
}
