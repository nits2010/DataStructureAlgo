package DataStructureAlgo.Java.LeetCode2025.easy;

/**
 * Author: Nitin Gupta
 * Date: 7/14/2024
 * Question Category: 9. Palindrome Number [easy]
 * Description: https://leetcode.com/problems/palindrome-number/description/
 * Given an integer x, return true if x is a
 * palindrome and false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: x = 121
 * Output: true
 * Explanation: 121 reads as 121 from left to right and from right to left.
 * Example 2:
 *
 * Input: x = -121
 * Output: false
 * Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
 * Example 3:
 *
 * Input: x = 10
 * Output: false
 * Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
 *
 *
 * Constraints:
 *
 * -231 <= x <= 231 - 1
 * <p>
 * File reference
 * -----------
 * Duplicate {@link PalindromeNumber_9}
 * Similar {@link}
 * <p>
 *
 * Tags
 * -----
 * @easy
 * @LinkedList
 * @TwoPointers
 * @Stack
 * @Recursion
 *
 * Company Tags
 * ------------
 * @Google
 * @Facebook
 * @Amazon
 * @Bloomberg
 * @Adobe
 *
 * @Editorial
 * : <a href="https://leetcode.com/problems/palindrome-linked-list/solutions/5549850/multiple-solution-multiple-ds-with-simple-explanation/">...</a>
 *
 */
public class PalindromeNumber_9 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isPalindrome2(121));
        System.out.println(solution.isPalindrome2(1221));
        System.out.println(solution.isPalindrome2(-1221));
        System.out.println(solution.isPalindrome2(0));
        System.out.println(solution.isPalindrome2(123));
    }

    static class Solution {
        /*
         https://leetcode.com/problems/palindrome-number/submissions/1320928546/
         */
        public boolean isPalindrome(int x) {

            if(x <0 || (x %10 ==0 && x!=0))
                return false;

            if(x>=1 && x<=9)
                return true;

            int reversed = 0;
            int temp = x;

            while(temp!=0){
                reversed = reversed *10 + temp%10;
                temp /=10;
            }
            return reversed == x;
        }

        public boolean isPalindrome2(int x) {

            if(x <0 || (x %10 ==0 && x!=0))
                return false;

            if(x>=1 && x<=9)
                return true;

            int reversed = 0;
            int temp = x;

            while(temp > reversed){
                reversed = reversed *10 + temp%10;
                temp /=10;
            }
            // When the length is an odd number, we can get rid of the middle digit by revertedNumber/10
            // For example when the input is 12321, at the end of the while loop we get x = 12, revertedNumber = 123,
            // since the middle digit doesn't matter in palidrome(it will always equal to itself), we can simply get rid of it.
            return reversed == temp || temp == reversed/10;
        }
    }
}
