package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Stacks._739;


import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Author: Nitin Gupta
 * Date: 7/31/2024
 * Question Category: 739. Daily Temperatures [medium]
 * Description: https://leetcode.com/problems/daily-temperatures/description/
 * <p>
 * Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: temperatures = [73,74,75,71,69,72,76,73]
 * Output: [1,1,4,2,1,1,0,0]
 * Example 2:
 * <p>
 * Input: temperatures = [30,40,50,60]
 * Output: [1,1,1,0]
 * Example 3:
 * <p>
 * Input: temperatures = [30,60,90]
 * Output: [1,1,0]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= temperatures.length <= 105
 * 30 <= temperatures[i] <= 100
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.DailyTemperatures}
 * Similar {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.Stacks.StockSpan}
 * extension {@link }
 * <p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @Stack
 * @MonotonicStack <p>
 * Company Tags
 * -----
 * @Facebook
 * @Amazon
 * @Microsoft
 * @C3IoT
 * @Uber
 * @Editorial <a href="https://leetcode.com/problems/next-greater-element-i/solutions/5592349/easy-solution-to-beat-100-optimized-solution-using-stacks/">...</a>
 */

public class DailyTemperatures_739 {
    public static void main(String[] args) {
        boolean testResult = true;
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{73, 74, 75, 71, 69, 72, 76, 73}, new int[]{1, 1, 4, 2, 1, 1, 0, 0}));
        tests.add(test(new int[]{30, 40, 50, 60}, new int[]{1, 1, 1, 0}));
        tests.add(test(new int[]{30, 60, 90}, new int[]{1, 1, 0}));

        CommonMethods.printAllTestOutCome(tests);

    }

    private static boolean test(int[] tem, int[] expected) {
        CommonMethods.printTest(new String[]{"temperature", "Expected"}, true, tem, expected);

        boolean pass = true, finalPass = true;
        int[] output;

        DailyTemperatures.SolutionUsingStacks tempStack = new DailyTemperatures.SolutionUsingStacks();
        output = tempStack.dailyTemperatures(tem);
        pass = CommonMethods.equalsValues(output, expected);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Stacks", "Pass"}, false, output, pass ? "PASS" : "FAIL");


        output = tempStack.dailyTemperaturesForward(tem);
        pass = CommonMethods.equalsValues(output, expected);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Stacks-2", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        DailyTemperatures.SolutionWithoutUsingStacks withoutUsingStacks = new DailyTemperatures.SolutionWithoutUsingStacks();
        output = withoutUsingStacks.dailyTemperatures(tem);
        pass = CommonMethods.equalsValues(output, expected);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Without Stacks", "Pass"}, false, output, pass ? "PASS" : "FAIL");


        return finalPass;

    }
}

class DailyTemperatures {
    static class SolutionUsingStacks {

        //Run from back to front, keep the stack for a greater element on the right (Seen so far).
        // For any day, to find warmer, we need to look on the right side. Keeping stack from right to left,
        //can answer this query for i if we know details about i+1 day.
        public int[] dailyTemperatures(int[] temperatures) {
            if (temperatures == null || temperatures.length == 0)
                return temperatures;

            int[] warmerDay = new int[temperatures.length];
            Stack<Integer> stack = new Stack<>();

            //for last day, there can be any warmer day as no data available post-last index.
            warmerDay[temperatures.length - 1] = 0;

            //possibly this can be a warmer day for any of the preceding day.
            stack.push(temperatures.length - 1);

            //start checking from second last day
            for (int i = temperatures.length - 2; i >= 0; i--) {

                //if all the day from day is not a warmer day, then pop it as it can't be a warmer day for anyone before me.
                while (!stack.isEmpty() && temperatures[stack.peek()] <= temperatures[i])
                    stack.pop();

                //if there is no warmer day for i, then put 0.
                if (stack.isEmpty())
                    warmerDay[i] = 0;
                else
                    //if there is a warmer day for i ( means value greater than current ) use its index to calculate the total day
                    warmerDay[i] = stack.peek() - i;

                //push this as a warmer day for anyone the preceding day.
                stack.push(i);
            }

            return warmerDay;
        }


        // We can run from left to right as well.
        // To calculate the warmer ith day, we can look all the days post ith day starting from jth day.
        // If we find any day j that is warmer than ith day, then we can calculate the warmer day for ith day as j-i.
        public int[] dailyTemperaturesForward(int[] temperatures) {
            if (temperatures == null || temperatures.length == 0)
                return temperatures;

            int[] warmerDay = new int[temperatures.length]; //by default, initialize by 0
            Stack<Integer> stack = new Stack<>();

            //put current day; stack will give us the context for the warmer day
            int i = 0;
            stack.push(i);

            int j = i + 1;

            while (j < temperatures.length) {

                //check does the current day, jth, is warmer day for any of the previous days (i's) ?
                while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[j]) {
                    i = stack.pop();
                    //for ith day, jth is the warmer day.
                    warmerDay[i] = j - i;
                }
                //push this keeping this could be a warmer day for any of the upcoming day.
                stack.push(j++);
            }
            return warmerDay;
        }
    }

    static class SolutionWithoutUsingStacks {

        public int[] dailyTemperatures(int[] T) {
            int n = T.length, next = 0;
            int[] stack = new int[n];
            int[] warmer = new int[n];

            for (int i = n - 1; i >= 0; i--) {
                next = i + 1;

                //find the index of next greater element on the right side of i
                while (next < n && T[next] <= T[i])
                    next = stack[next]; // Find the next warmer or  hit boundary

                stack[i] = next;

                //if we found an element on the right side greater than i'th element
                if (next != n)
                    warmer[i] = next - i;
            }
            return warmer;
        }
    }
}
