package Java.LeetCode;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-03
 * Description: https://leetcode.com/problems/house-robber/
 */
public class HouseRobber {

    public static void main (String args[]){
        int n1[] = {1,2,3,1};
        System.out.println(rob(n1));

        int n2[] = {2,7,9,3,1};
        System.out.println(rob(n2));
    }

    public static int rob(int[] nums) {

        if (nums == null)
            return 0;

        if(nums.length == 0)
            return 0;

        int firstHouse = nums[0];
        if(nums.length == 1)
            return nums[0];

        int secondHouse = Math.max(nums[0], nums[1]);
        int max = secondHouse;

        for (int i=2; i<nums.length; i++){
            int currentHouse = nums[i];


            max = Math.max(firstHouse + currentHouse , secondHouse );

            firstHouse = secondHouse;

            secondHouse = max;
        }
        return max;

    }

    public static int robDetailed(int[] nums) {

        if (nums == null || nums.length==0)
            return 0;
        int n = nums.length;

        if(n == 0)
            return 0;

        if(n == 1)
            return nums[0];

        int sum = 0 ;
        int firstHouse = nums[0];
        int secondHouse = Math.max(nums[0], nums[1]);
        int max = secondHouse;

        for (int i=2; i<n; i++){
            int currentHouse = nums[i];

            //either we theft current house [ then we have to theif in a house 1 before this house] or leave it;
            max = Math.max(firstHouse + currentHouse , secondHouse );

            firstHouse = secondHouse; // now for next house, second house would be the first house

            secondHouse = max;
        }
        return max;

    }
}
