package Java;

import java.util.Arrays;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-01
 * Description:https://www.geeksforgeeks.org/given-a-number-find-next-smallest-palindrome-larger-than-this-number/
 */
public class NextSmallestPalindrome {

    public static void main(String []args) {
        int num[] = {7, 2, 3, 3, 8, 2};
        int next[] = nextPalindrome(num, num.length);

        Arrays.stream(next).forEach(i -> System.out.print(i + " "));
    }

    private static int[] nextPalindrome(int[] num, int length) {

        if (length == 0)
            return num;

        if (isAll9(num)) {
            int result[] = new int[length + 1];
            result[0] = result[length] = 1;
            for (int i = 1; i < length; i++) {
                result[i] = 0;
            }
            return result;
        }

        int mid = length / 2;
        int i, j;

        if (length % 2 == 0) {
            i = mid - 1;
            j = mid;
        } else {
            i = mid - 1;
            j = mid + 1;
        }

        while (i >= 0 && j < length && num[i] == num[j]) {
            i--;
            j++;
        }

        boolean leftSmaller = false;
        if (i < 0 || num[i] < num[j])
            leftSmaller = true;

        mirror(num);

        if (leftSmaller) {

            int carray = 1;
            if (length % 2 != 0) {

                num[mid] += carray;
                carray = num[mid] / 10;
                num[mid] %= 10;

                if (carray == 0)
                    return num;
            }

            i = mid - 1;
            j = length % 2 == 0 ? length / 2 : length / 2 + 1;

            while (i >= 0) {
                num[i] += carray;
                carray = num[i] / 10;
                num[i] %= 10;
                num[j] = num[i];
                i--;
                j++;
                if (carray == 0)
                    return num;
            }


        }
        return num;

    }

    private static void mirror(int[] num) {

        int i = num.length / 2 - 1;
        int j = num.length % 2 == 0 ? num.length / 2 : num.length / 2 + 1;

        while (i >= 0 && j < num.length)
            num[j++] = num[i--];
    }

    private static boolean isAll9(int[] num) {

        for (int i = 0; i < num.length; i++)
            if (num[i] != 9)
                return false;

        return true;
    }
}
