package DataStructureAlgo.Java.nonleetcode;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.Arrays;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-01
 * Description:https://www.geeksforgeeks.org/given-a-number-find-next-smallest-palindrome-larger-than-this-number/
 * Given a number, find the next smallest palindrome
 * Given a number, find the next smallest palindrome larger than this number. For example, if the input number is “2 3 5 4 5”, the output should be “2 3 6 3 2”.
 * And if the input number is “9 9 9”, the output should be “1 0 0 1”.
 * The input is assumed to be an array. Every entry in array represents a digit in input number. Let the array be ‘num[]’ and size of array be ‘n’
 * <p>
 * There can be three different types of inputs that need to be handled separately.
 * 1) The input number is palindrome and has all 9s. For example “9 9 9”. Output should be “1 0 0 1”
 * 2) The input number is not palindrome. For example “1 2 3 4”. Output should be “1 3 3 1”
 * 3) The input number is palindrome and doesn’t have all 9s. For example “1 2 2 1”. Output should be “1 3 3 1”
 * [Amazon]  [MakeMyTrip] [Microsoft]  [Yahoo]
 */
public class NextSmallestPalindrome {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new int[]{9, 9, 9}, new int[]{1, 0, 0, 1});
        test &= test(new int[]{2, 3, 5, 4, 5}, new int[]{2, 3, 6, 3, 2});
        test &= test(new int[]{7, 2, 3, 3, 8, 2}, new int[]{7, 2, 4, 4, 2, 7});
        System.out.println("\nTests:" + (test ? "Passed" : "Failed"));
    }

    private static boolean test(int[] num, int[] expected) {
        System.out.println("\nNum:" + CommonMethods.toString(num));
        System.out.println("Expected:" + CommonMethods.toString(expected));
        int[] obtained = nextPalindrome(num, num.length);
        System.out.println("obtained:" + CommonMethods.toString(obtained));

        return Arrays.equals(expected, obtained);

    }

    /*
 How to convert a given number to a greater palindrome? To understand the solution, let us first define the following two terms:
Left Side: The left half of given number. Left side of “1 2 3 4 5 6” is “1 2 3” and left side of “1 2 3 4 5” is “1 2”
Right Side: The right half of given number. Right side of “1 2 3 4 5 6” is “4 5 6” and right side of “1 2 3 4 5” is “4 5”
To convert to palindrome, we can either take the mirror of its left side or take mirror of its right side. However, if we take the mirror of the right side,
then the palindrome so formed is not guaranteed to be next larger palindrome. So, we must take the mirror of left side and copy it to right side.
But there are some cases that must be handled in different ways. See the following steps.

We will start with two indices i and j. i pointing to the two middle elements (or pointing to two elements around the middle element in case of n being odd).
We one by one move i and j away from each other.

Step 1. Initially, ignore the part of left side which is same as the corresponding part of right side. For example, if the number is “8 3 4 2 2 4 6 9″,
we ignore the middle four elements. i now points to element 3 and j now points to element 6.

Step 2. After step 1, following cases arise:

Case 1: Indices i & j cross the boundary.
This case occurs when the input number is palindrome. In this case, we just add 1 to the middle digit (or digits in case n is even) propagate the carry towards MSB digit of
left side and simultaneously copy mirror of the left side to the right side.
For example, if the given number is “1 2 9 2 1”, we increment 9 to 10 and propagate the carry. So the number becomes “1 3 0 3 1”

Case 2: There are digits left between left side and right side which are not same. So, we just mirror the left side to the right side & try to minimize the number formed to guarantee
the next smallest palindrome. In this case, there can be two sub-cases.

2.1) Copying the left side to the right side is sufficient, we don’t need to increment any digits and the result is just mirror of left side. Following are some examples of this sub-case.
Next palindrome for “7 '8' 3 3 2 2″ is “7 8 3 3 8 7”
Next palindrome for “1 2 '5' 3 2 2″ is “1 2 5 5 2 1”
Next palindrome for “1 4 '5' 8 7 6 7 8 3 2 2″ is “1 4 5 8 7 6 7 8 5 4 1”
How do we check for this sub-case? All we need to check is the digit just after the ignored part in step 1. This digit is highlighted in above examples. If this digit is greater than the corresponding digit in right side digit, then copying the left side to the right side is sufficient and we don’t need to do anything else.

2.2) Copying the left side to the right side is NOT sufficient. This happens when the above defined digit of left side is smaller. Following are some examples of this case.
Next palindrome for “7 1 3 3 2 2″ is “7 1 4 4 1 7”
Next palindrome for “1 2 3 4 6 2 8″ is “1 2 3 5 3 2 1”
Next palindrome for “9 4 1 8 7 9 7 8 3 2 2″ is “9 4 1 8 8 0 8 8 1 4 9”
We handle this subcase like Case 1. We just add 1 to the middle digit (or digits in case n is even) propagate the carry towards MSB digit of left side and simultaneously copy mirror of the left side to the right side.
     */
    private static int[] nextPalindrome(int[] num, int length) {

        if (length == 0)
            return num;

        //if all 9 , then just make middle 0 and corner 1
        if (isAll9(num)) {
            int[] result = new int[length + 1];
            result[0] = result[length] = 1;
            for (int i = 1; i < length; i++) {
                result[i] = 0;
            }
            return result;
        }

        //find the middle element of the given array
        int mid = length / 2;
        int left, right;

        //if even,
        if (length % 2 == 0) {
            left = mid - 1;
            right = mid;
        } else { //odd
            left = mid - 1;
            right = mid + 1;
        }

        //Skip those elements which are same on either side
        while (left >= 0 && right < length && num[left] == num[right]) {
            left--;
            right++;
        }

        //indicate is left part is smaller as compare to right part of a digit
        boolean leftSmaller = false;

        //if left element is smaller than right element
        if (left < 0 || num[left] < num[right])
            leftSmaller = true;

        //make right as same as left side
        while (left >= 0)
            num[right++] = num[left--];


        //IF left is smaller then add 1 to center element and propagate carry towards left side and copy right side
        if (leftSmaller) {

            int carry = 1;
            //if odd
            if (length % 2 != 0) {

                num[mid] += carry;
                carry = num[mid] / 10;
                num[mid] %= 10;

                //if no carry, no need to propagate it
                if (carry == 0)
                    return num;
            }

            //copy left side values to right side and pull carry too.
            left = mid - 1;
            right = length % 2 == 0 ? length / 2 : length / 2 + 1;

            while (left >= 0) {
                num[left] += carry;
                carry = num[left] / 10;
                num[left] %= 10;
                num[right] = num[left];
                left--;
                right++;
                //if no carry, no need to propagate it, mirror already been place so that value so left and right are same
                if (carry == 0)
                    return num;
            }


        }
        return num;

    }


    private static boolean isAll9(int[] num) {

        for (int value : num)
            if (value != 9)
                return false;

        return true;
    }
}
