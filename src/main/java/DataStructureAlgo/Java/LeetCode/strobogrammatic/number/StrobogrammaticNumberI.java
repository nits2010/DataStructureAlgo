package DataStructureAlgo.Java.LeetCode.strobogrammatic.number;

/**
 * Author: Nitin Gupta
 * Date: 2019-09-09
 * Description: https://leetcode.com/problems/strobogrammatic-number
 * <p>
 * 246.Strobogrammatic Number Or Fancy Number
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 * <p>
 * Write a function to determine if a number is strobogrammatic. The number is represented as a string.
 * <p>
 * Example 1:
 * <p>
 * Input:  "69"
 * Output: true
 * Example 2:
 * <p>
 * Input:  "88"
 * Output: true
 * Example 3:
 * <p>
 * Input:  "962"
 * Output: false
 * <p>
 * http://tiancao.me/Leetcode-Unlocked/LeetCode%20Locked/c1.5.html
 * https://www.geeksforgeeks.org/check-if-a-given-number-is-fancy/
 * 180 degree rotations of 6, 9, 1, 0 and 8 are 9, 6, 1, 0 and 8 respectively
 */
public class StrobogrammaticNumberI {

    public static void main(String[] args) {
        test("69", true);
        test("88", true);
        test("996", false);
        test("121", false);

    }

    private static void test(String num, boolean expected) {
        System.out.println(" num :" + num + " expected :" + expected);
        System.out.println(" is :" + isStrobogrammatic(num));
    }

    /**
     * A number is only Strobogrammatic when both end of string has Strobogrammatic numbers.
     * <p>
     * Strobogrammatic Numsers
     * 6 <-> 9
     * 1 <-> 1
     * 0 <-> 0
     * 8 <-> 8
     * <p>
     * Then apply same as string palindrome logic
     *
     * Complexity: O(n) / O (1)
     * @param num
     * @return
     */
    public static boolean isStrobogrammatic(String num) {

        /**
         * Empty string is a Strobogrammatic
         */
        if (num == null || num.isEmpty())
            return true;


        int i = 0;
        int j = num.length() - 1;

        while (i <= j) { //if this is odd length string then the middle char only be 0 or 1 or 8


            char x = num.charAt(i);
            char y = num.charAt(j);

            switch (x) {

                case '0':
                    if (y != '0')
                        return false;
                    break;

                case '1':
                    if (y != '1')
                        return false;
                    break;

                case '8':
                    if (y != '8')
                        return false;
                    break;

                case '6':
                    if (y != '9')
                        return false;
                    break;

                case '9':
                    if (y != '6')
                        return false;
                    break;

                default: //no other char is allowed
                    return false;
            }

            i++;
            j--;
        }


        return true;

    }
}
