package DataStructureAlgo.Java.LeetCode.strobogrammatic.number;

import java.math.BigInteger;
import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 2019-09-09
 * Description: https://leetcode.com/problems/strobogrammatic-number-iii
 * http://leetcode.liangjiateng.cn/leetcode/strobogrammatic-number-iii/description
 * 248.Strobogrammatic Number III
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 * <p>
 * Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.
 * <p>
 * Example:
 * <p>
 * Input: low = "50", high = "100"
 * Output: 3
 * Explanation: 69, 88, and 96 are three strobogrammatic numbers.
 * Note:
 * Because the range might be a large number, the low and high numbers are represented as string.
 * http://tiancao.me/Leetcode-Unlocked/LeetCode%20Locked/c1.5.html
 */
public class StrobogrammaticNumberIII {

    public static void main(String[] args) {
//        test("50", "100", 3);
        test("0", "100000000000000", 124999);
    }

    private static void test(String low, String high, int expected) {
        System.out.println("[ " + low + "," + high + "] expected: " + expected);
        System.out.println("Poly Obtained : " + StrobogrammaticNumberIIIUsingStrobogrammaticNumberII.strobogrammaticInRange(low, high));
        System.out.println("Cache Obtained : " + StrobogrammaticNumberIIIDfs.strobogrammaticInRange(low, high));

    }


}


/**
 * {@link StrobogrammaticNumberII} give us all the strings with length n,
 * then we can call it to get strings with length between low.length() and high.length(), and remove the strings
 * that less than low and larger than high.
 * <p>
 * Complexity: O(n*D^2); where D = high-low and n is length of  strobogrammatic list between low and high
 */
class StrobogrammaticNumberIIIUsingStrobogrammaticNumberII {

    public static int strobogrammaticInRange(String low, String high) {
        if (low == null || high == null || low.isEmpty() || high.isEmpty())
            return 0;

        final List<String> strobogrammatic = new ArrayList<>();

        for (int i = low.length(); i <= high.length(); i++)
            strobogrammatic.addAll(StrobogrammaticNumberII.findStrobogrammatic(i));


        int count = strobogrammatic.size();

        for (int i = 0; i < strobogrammatic.size() && strobogrammatic.get(i).length() == low.length(); i++) {
            if (strobogrammatic.get(i).compareTo(low) < 0)
                count--;
        }

        for (int i = strobogrammatic.size() - 1; i >= 0 && strobogrammatic.get(i).length() == high.length(); i--) {
            if (strobogrammatic.get(i).compareTo(high) > 0)
                count--;
        }


        return count;
    }
}


/**
 * The problem with above is we generate all strobogrammatic number again n again because we are linearly including
 * from low to high.
 * example ;
 * n = 6 computes n = 6 -> 4 -> 2
 * n = 7 computes n=7 -> 5 -> 3 -> 2
 * n = 8 computes n=8->6 -> 4 -> 2
 * n = 9 computes ; n=9 -> 7 ->  5 -> 3 -> 2
 * <p>
 * Hence overlapping sub-problems, we can cache it to generate next
 * <p>
 * TODO
 * https://www.cnblogs.com/grandyang/p/5203228.html
 */
class StrobogrammaticNumberIIIDfs {
    public static int strobogrammaticInRange(String low, String high) {
        if (low == null || high == null || low.isEmpty() || high.isEmpty())
            return 0;

        final List<String> strobogrammatic = new ArrayList<>();

        return 0;


    }


}
