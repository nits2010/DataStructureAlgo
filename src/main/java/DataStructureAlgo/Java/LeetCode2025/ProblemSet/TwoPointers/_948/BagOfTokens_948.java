package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers._948;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 4/8/2025
 * Question Title: 948. Bag of Tokens
 * Link: https://leetcode.com/problems/bag-of-tokens/description/
 * Description: You start with an initial power of power, an initial score of 0, and a bag of tokens given as an integer array tokens, where each tokens[i] denotes the value of tokeni.
 * <p>
 * Your goal is to maximize the total score by strategically playing these tokens. In one move, you can play an unplayed token in one of the two ways (but not both for the same token):
 * <p>
 * Face-up: If your current power is at least tokens[i], you may play tokeni, losing tokens[i] power and gaining 1 score.
 * Face-down: If your current score is at least 1, you may play tokeni, gaining tokens[i] power and losing 1 score.
 * Return the maximum possible score you can achieve after playing any number of tokens.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: tokens = [100], power = 50
 * <p>
 * Output: 0
 * <p>
 * Explanation: Since your score is 0 initially, you cannot play the token face-down. You also cannot play it face-up since your power (50) is less than tokens[0] (100).
 * <p>
 * Example 2:
 * <p>
 * Input: tokens = [200,100], power = 150
 * <p>
 * Output: 1
 * <p>
 * Explanation: Play token1 (100) face-up, reducing your power to 50 and increasing your score to 1.
 * <p>
 * There is no need to play token0, since you cannot play it face-up to add to your score. The maximum score achievable is 1.
 * <p>
 * Example 3:
 * <p>
 * Input: tokens = [100,200,300,400], power = 200
 * <p>
 * Output: 2
 * <p>
 * Explanation: Play the tokens in this order to get a score of 2:
 * <p>
 * Play token0 (100) face-up, reducing power to 100 and increasing score to 1.
 * Play token3 (400) face-down, increasing power to 500 and reducing score to 0.
 * Play token1 (200) face-up, reducing power to 300 and increasing score to 1.
 * Play token2 (300) face-up, reducing power to 0 and increasing score to 2.
 * The maximum score achievable is 2.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= tokens.length <= 1000
 * 0 <= tokens[i], power < 104
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @TwoPointers
 * @Greedy
 * @Sorting <p><p>
 * Company Tags
 * -----
 * @Google <p>
 * -----
 * @Editorial https://leetcode.com/problems/bag-of-tokens/editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class BagOfTokens_948 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{100}, 50, 0));
        tests.add(test(new int[]{200, 100}, 150, 1));
        tests.add(test(new int[]{100, 200, 300, 400}, 200, 2));
        tests.add(test(new int[]{71, 55, 82}, 54, 0));
        tests.add(test(new int[]{33, 41, 10, 91, 47, 84, 98, 34, 48, 70}, 43, 4));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] tokens, int power, int expected) {
        //add print here
        CommonMethods.printTestOutcome(new String[]{"Tokens", "Power", "Expected"}, true, tokens, power, expected);

        int output = 0;
        boolean pass, finalPass = true;

        SolutionTwoPointers solutionTwoPointers = new SolutionTwoPointers();
        output = solutionTwoPointers.bagOfTokensScore(tokens, power);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTestOutcome(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class SolutionTwoPointers {
        public int bagOfTokensScore(int[] tokens, int power) {
            if (tokens == null || tokens.length == 0)
                return 0;

            if (tokens.length == 1 && power < tokens[0])
                return 0;

            Arrays.sort(tokens);
            int n = tokens.length;
            int min = 0, max = n - 1;

            int score = 0;
            int maxScore = 0;

            while (min <= max) {

                //face-up possible ?
                if (tokens[min] <= power) {
                    //do faceup
                    score++;
                    maxScore = Math.max(score, maxScore);
                    power -= tokens[min];
                    min++;
                } else if (score >= 1 && min < max) {
                    //facedown only possible when there is at least 1 score
                    //try faceDown with max value, only if we have one more faceup possiblities,
                    // otherwise there is no advantage of doing facedown since our score will reduce further, min < max
                    score--;
                    power += tokens[max];
                    max--;

                } else {
                    break;
                }
            }

            return maxScore;

        }
    }
}
