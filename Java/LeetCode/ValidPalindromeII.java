package Java.LeetCode;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-15
 * Description:
 */
public class ValidPalindromeII {
    public static void main (String args[]){
        SolutionValidPalindromeII sol = new SolutionValidPalindromeII();
        System.out.println(sol.validPalindrome("aba"));
        System.out.println(sol.validPalindrome("abc"));
        System.out.println(sol.validPalindrome("abca"));
    }
}

class SolutionValidPalindromeII {
    public boolean validPalindrome(String s) {

        if(s == null || s.isEmpty() || s.length() == 1)
            return true;

        for (int i=0; i<s.length()/2; i++){
            if(s.charAt(i) != s.charAt(s.length()-1-i)){ //alow only one char delete

                int j = s.length()-1-i;

                return (isPalindrome2(s, i+1, j) || isPalindrome2(s, i, j-1));
            }
        }

        return true;
    }



  private  boolean isPalindrome2(String s, int l, int r){

        while (l<r){
            if(s.charAt(l)!=s.charAt(r))
                return false;
            l++;
            r--;
        }

        if(l>=r)
            return true;
        return false;

    }
}