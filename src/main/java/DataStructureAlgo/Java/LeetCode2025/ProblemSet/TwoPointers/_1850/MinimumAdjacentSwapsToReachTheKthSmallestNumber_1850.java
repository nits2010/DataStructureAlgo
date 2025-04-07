package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers._1850;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 4/7/2025
 * Question Title: 1850. Minimum Adjacent Swaps to Reach the Kth Smallest Number
 * Link: https://leetcode.com/problems/minimum-adjacent-swaps-to-reach-the-kth-smallest-number/description/
 * Description: You are given a string num, representing a large integer, and an integer k.
 * <p>
 * We call some integer wonderful if it is a permutation of the digits in num and is greater in value than num. There can be many wonderful integers. However, we only care about the smallest-valued ones.
 * <p>
 * For example, when num = "5489355142":
 * The 1st smallest wonderful integer is "5489355214".
 * The 2nd smallest wonderful integer is "5489355241".
 * The 3rd smallest wonderful integer is "5489355412".
 * The 4th smallest wonderful integer is "5489355421".
 * Return the minimum number of adjacent digit swaps that needs to be applied to num to reach the kth smallest wonderful integer.
 * <p>
 * The tests are generated in such a way that kth smallest wonderful integer exists.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: num = "5489355142", k = 4
 * Output: 2
 * Explanation: The 4th smallest wonderful number is "5489355421". To get this number:
 * - Swap index 7 with index 8: "5489355142" -> "5489355412"
 * - Swap index 8 with index 9: "5489355412" -> "5489355421"
 * Example 2:
 * <p>
 * Input: num = "11112", k = 4
 * Output: 4
 * Explanation: The 4th smallest wonderful number is "21111". To get this number:
 * - Swap index 3 with index 4: "11112" -> "11121"
 * - Swap index 2 with index 3: "11121" -> "11211"
 * - Swap index 1 with index 2: "11211" -> "12111"
 * - Swap index 0 with index 1: "12111" -> "21111"
 * Example 3:
 * <p>
 * Input: num = "00123", k = 1
 * Output: 1
 * Explanation: The 1st smallest wonderful number is "00132". To get this number:
 * - Swap index 3 with index 4: "00123" -> "00132"
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= num.length <= 1000
 * 1 <= k <= 1000
 * num only consists of digits.
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers._31.NextPermutation_31} {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.Stacks.NextGreaterElement._556.NextGreaterElementIII_556}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @TwoPointers
 * @String
 * @Greedy <p><p>
 * Company Tags
 * -----
 * @Google <p>
 * -----
 * @Editorial https://leetcode.com/problems/minimum-adjacent-swaps-to-reach-the-kth-smallest-number/solutions/1186818/c-simple-solution-using-next-permutation/comments/930124/ <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class MinimumAdjacentSwapsToReachTheKthSmallestNumber_1850 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test("5489355142", 4, 2));
        tests.add(test("11112", 4, 4));
        tests.add(test("00123", 1, 1));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(String num, int k, int expected) {
        //add print here
        CommonMethods.printTestOutcome(new String[]{"Num", "k", "Expected"}, true, num, k, expected);

        int output = 0;
        boolean pass, finalPass = true;

        Solution solution = new Solution();
        output = solution.getMinSwaps(num, k);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTestOutcome(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class Solution {
        public int getMinSwaps(String num, int k) {

            char[] s1 = num.toCharArray();
            char[] s2 = kthPermutation(num.toCharArray(), k);
            // System.out.println("->"+new String(s2));
            return minSwaps(s1, s2);
        }

        /**
         * The first thing which we need to do is to find the correct position of the element. For example, 142 and 421. In this 1 needs to be in the last position.
         * Next, we swap the elements until 1 comes to its correct position.
         * We continue the above 2 steps for all the elements.
         * Now, the main question is how this works. So here, the keyword is "adjacent" swaps. In the above example, the only best(minimum) way to move to its correct position is by swapping it two times.
         * <p>
         * Also, one more point which confused me a bit was how this swap will impact the elements which come in succession. It turns out that it works for them too. In the above,
         * when reaches its correct position 4 also reaches its correct position.
         *
         * @param s1
         * @param s2
         * @return
         */
        int minSwaps(char[] s1, char[] s2) {
            int i = 0, j = 0;
            int swaps = 0;

            while (i < s2.length) {
                j = i;

                //find in s1 how much character away this jth character sits, that many swaps would needed to bring it pos
                while (s1[j] != s2[i]) {
                    // System.out.println("s1:"+s1[j] + " s2: "+s2[i]);
                    j++;
                }

                //if they are not on correct pos, swap them in s1
                while (i < j) {
                    swap(s1, j, j - 1);
                    j--;
                    swaps++;
                }
                // System.out.println("=>"+new String(s1));
                i++;
            }
            return swaps;
        }

        /**
         * {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.Stacks.NextGreaterElement._556.NextGreaterElementIII_556}
         */
        char[] kthPermutation(char[] s, int k) {

            while (k-- > 0) {
                nextPermuatation(s);
            }
            return s;
        }

        void nextPermuatation(char[] s) {

            int firstIndexDec = firstIndexDec(s);

            if (firstIndexDec == -1)
                reverse(s, firstIndexDec + 1);

            int secondIndexInc = secondIndexInc(s, firstIndexDec);

            swap(s, firstIndexDec, secondIndexInc);
            reverse(s, firstIndexDec + 1);
        }

        void swap(char[] s, int i, int j) {
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
        }

        void reverse(char[] s, int d) {
            int i = d, j = s.length - 1;
            while (i < j) {
                swap(s, i++, j--);
            }
        }

        int firstIndexDec(char[] s) {
            int i = s.length - 2;
            while (i >= 0 && s[i + 1] <= s[i]) {
                i--;
            }
            return i;
        }

        int secondIndexInc(char[] s, int d) {
            int i = s.length - 1;
            while (i > d && s[i] <= s[d]) {
                i--;
            }
            return i;
        }

    }
}
