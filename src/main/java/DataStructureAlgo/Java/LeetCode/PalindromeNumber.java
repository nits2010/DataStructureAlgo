package DataStructureAlgo.Java.LeetCode;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-01
 * Description:https://leetcode.com/problems/palindrome-number/description/
 * Given an integer x, return true if x is a
 * palindrome
 * , and false otherwise.
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
 */
public class PalindromeNumber {

    public static void main(String[] args) {
        System.out.println(isPalindrome2(121));
        System.out.println(isPalindrome2(1221));
        System.out.println(isPalindrome2(-1221));
        System.out.println(isPalindrome2(0));
        System.out.println(isPalindrome2(123));
    }

    public static boolean isPalindrome(int x) {

        if (x < 0)
            return false;

        if (x >= 0 && x <= 9)
            return true;

        int reversed = 0;

        int temp = x;
        while (x != 0) {
            reversed = reversed * 10 + x % 10;
            x /= 10;
        }

        return reversed == temp;

    }

    public static boolean isPalindrome2(int x) {

        // Special cases:
        // As discussed above, when x < 0, x is not a palindrome.
        // Also if the last digit of the number is 0, in order to be a palindrome,
        // the first digit of the number also needs to be 0.
        // Only 0 satisfy this property.
        if (x < 0 || (x % 10 == 0 && x != 0))
            return false;


        int reversed = 0;

        while (x > reversed) {
            reversed = reversed * 10 + x % 10;
            x /= 10;
        }

        // When the length is an odd number, we can get rid of the middle digit by revertedNumber/10
        // For example when the input is 12321, at the end of the while loop we get x = 12, revertedNumber = 123,
        // since the middle digit doesn't matter in palidrome(it will always equal to itself), we can simply get rid of it.
        return reversed == x || x == reversed / 10;

    }

}
