package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers._825;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 4/16/2025
 * Question Title: 825. Friends Of Appropriate Ages
 * Link: https://leetcode.com/problems/friends-of-appropriate-ages/description/
 * Description: There are n persons on a social media website. You are given an integer array ages where ages[i] is the age of the ith person.
 *
 * A Person x will not send a friend request to a person y (x != y) if any of the following conditions is true:
 *
 * age[y] <= 0.5 * age[x] + 7
 * age[y] > age[x]
 * age[y] > 100 && age[x] < 100
 * Otherwise, x will send a friend request to y.
 *
 * Note that if x sends a request to y, y will not necessarily send a request to x. Also, a person will not send a friend request to themself.
 *
 * Return the total number of friend requests made.
 *
 *
 *
 * Example 1:
 *
 * Input: ages = [16,16]
 * Output: 2
 * Explanation: 2 people friend request each other.
 * Example 2:
 *
 * Input: ages = [16,17,18]
 * Output: 2
 * Explanation: Friend requests are made 17 -> 16, 18 -> 17.
 * Example 3:
 *
 * Input: ages = [20,30,100,110,120]
 * Output: 3
 * Explanation: Friend requests are made 110 -> 100, 120 -> 110, 120 -> 100.
 *
 *
 * Constraints:
 *
 * n == ages.length
 * 1 <= n <= 2 * 104
 * 1 <= ages[i] <= 120
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 * @medium
 * @HashTable
 * @TwoPointers
 * @BinarySearch
 *
 * <p><p>
 * Company Tags
 * -----
 * @Facebook
 * <p>
 * -----
 *
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class FriendsOfAppropriateAges_825 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{16, 16}, 2));
        tests.add(test(new int[]{16, 17, 18}, 2));
        tests.add(test(new int[]{20, 30, 100, 110, 120}, 3));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[]ages, int expected) {

        CommonMethods.printTestOutcome(new String[]{"ages", "Expected"}, true, ages, expected);

        int output = 0;
        boolean pass, finalPass = true;

       Solution solution = new Solution();
       output = solution.numFriendRequests(ages);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTestOutcome(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class Solution {
        public int numFriendRequests(int[] ages) {

            //since there are only 120 ages are there, we can count the frnd request send easily by counting them
            int[] count = new int[121];
            int result = 0;
            for (int age : ages)
                count[age]++;

            for (int age = 1; age<=120; age++) { //consider all the persons in the agest list to find the frnd request count
                int countX = count[age];

                for (int i = 1; i <= 120; i++) { //consider all the other possible ages where the condition hold true

                    int countY = count[i];

                    int x = age;
                    int y = i;

                    if (!(y <= 0.5 * x + 7 || y > x || (y > 100 && x < 100))) {
                        result += countX * countY;

                        if(x==y)
                            result -= countX;



                    }

                }
            }
            return result;
        }
    }
}
