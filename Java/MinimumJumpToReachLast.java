package Java;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-06-21
 * Description:
 * https://www.geeksforgeeks.org/minimum-number-jumps-reach-endset-2on-solution/
 * Given an array of integers where each element represents the max number of steps that can be made forward from that element. Write a function to return the minimum number of jumps to reach the end of the array (starting from the first element). If an element is 0, then we cannot move through that element.
 * <p>
 * Examples:
 * <p>
 * Input :  arr[] = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9}
 * Output :  3 (1-> 3 -> 8 -> 9)
 */
public class MinimumJumpToReachLast {


    public static void main(String args[]) {
        int arr[] = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9};

        System.out.println(jump(arr));
    }

    public static int jump(int[] nums) {

        if (null == nums || nums.length == 0 || nums.length == 1)
            return 0;

        if (nums[0] == 0)
            return 0;

        int jump = 1;

        int maxReach = nums[0];
        int steps = nums[0];

        int i = 1;
        while (i < nums.length ) {

            if (i == nums.length - 1)
                return jump;

            maxReach = Math.max(maxReach, i + nums[i]);
            steps--;

            if (steps == 0) {
                jump++;

                if (i >= maxReach)
                    return -1;

                steps = maxReach - i;
            }
            i++;

        }
        return -1;


    }
}
