package DataStructureAlgo.Java.LeetCode;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-09
 * Description:
 * https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/
 * same as : https://www.geeksforgeeks.org/allocate-minimum-number-pages/
 * <p>
 * <p>
 * A conveyor belt has packages that must be shipped from one port to another within D days.
 * <p>
 * The i-th package on the conveyor belt has a weight of weights[i].  Each day, we load the ship with packages on the conveyor belt (in the order given by weights). We may not load more weight than the maximum weight capacity of the ship.
 * <p>
 * Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within D days.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: weights = [1,2,3,4,5,6,7,8,9,10], D = 5
 * Output: 15
 * Explanation:
 * A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
 * 1st day: 1, 2, 3, 4, 5
 * 2nd day: 6, 7
 * 3rd day: 8
 * 4th day: 9
 * 5th day: 10
 * <p>
 * Note that the cargo must be shipped in the order given, so using a ship of capacity 14 and splitting the packages into parts like (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) is not allowed.
 * Example 2:
 * <p>
 * Input: weights = [3,2,2,4,1,4], D = 3
 * Output: 6
 * Explanation:
 * A ship capacity of 6 is the minimum to ship all the packages in 3 days like this:
 * 1st day: 3, 2
 * 2nd day: 2, 4
 * 3rd day: 1, 4
 * Example 3:
 * <p>
 * Input: weights = [1,2,3,1,1], D = 4
 * Output: 3
 * Explanation:
 * 1st day: 1
 * 2nd day: 2
 * 3rd day: 3
 * 4th day: 1, 1
 */
public class MinCapacityOfShipMinimumNumberPagesStudent {

    public static void main(String []args) {
        SolutionMinCapacityOfShipMinimumNumberPagesStudent sol = new SolutionMinCapacityOfShipMinimumNumberPagesStudent();
        int weights[] = {3, 2, 2, 4, 1, 4}, d = 3;
        System.out.println(sol.shipWithinDays(weights, d));
    }

}

class SolutionMinCapacityOfShipMinimumNumberPagesStudent {

    /**
     * In order to find, what is the least ship capacity, we need to find first what is the ship
     * max capacity, isn't it?
     * <p>
     * Well question remain same, how to find max capacity?
     * But i think its easier to answer, assume we can get every package in a ship, then the total
     * weight this ship carry can be the maximum capacity (sum of weights).
     * <p>
     * But wait? How we find least capacity from max capacity.
     * On the contrast of max capacity, we may can also assume that our ship can't take anything with it, in this case its minimum         capacity is zero (0)
     * <p>
     * That's great progress, we know minimum capacity (0) and maximum capacity (sum of weights). But still how do we know that
     * what is least capacity to carry items in D days.
     * <p>
     * The important thing is here is D days. Lets suppose we throw some capacity of my ship and ask some one to check
     * does my ship has is capable enough to take to take my weights with in its capacity and days?
     * if this guy tell us, we could make sure that the thrown weight is potential answer.
     * But, since we need least, we try to reduce it further.
     * <p>
     * <p>
     * See any pattern?
     * Yes binary search :)
     * <p>
     * low = 0,
     * high = Sum(weights[0...n-1])
     * mid = thrown capacity
     * <p>
     * important part is how do we implement, is possible or not?
     * That's tricky, we can simply assign weights one by one and check does this ship can ship all of the weights in D days or not.
     * if does then we reduce the space of solutions by (low, mid-1) as mid was potential solution
     * otherwise we increase the capacity by (mid+1, high)
     **/
    private int sum(int[] weights) {
        int sum = 0;
        for (int i = 0; i < weights.length; i++)
            sum += weights[i];

        return sum;
    }

    public int shipWithinDays(int[] weights, int D) {

        int low = 0;
        int high = sum(weights); // Arrays.stream(weights).sum(); <--- this makes it slow
        int capacity = Integer.MAX_VALUE;

        while (low <= high) {

            int thisCapacity = (low + high) >> 1;

            if (isPossible(weights, D, thisCapacity)) {
                capacity = Math.min(thisCapacity, capacity);
                high = thisCapacity - 1;
            } else
                low = thisCapacity + 1;

        }
        return capacity;

    }

    private boolean isPossible(int weights[], int d, int capacity) {

        int requiredDays = 1;
        int currentWeight = 0;

        for (int i = 0; i < weights.length; i++) {

            //if this weights itself bigger than my capacity, then i can't take it with me ..sorry :(
            if (weights[i] > capacity)
                return false;

            currentWeight += weights[i];

            //is it possible really?
            if (currentWeight > capacity) {

                //not really
                requiredDays++;

                if (requiredDays > d)
                    return false;

                currentWeight = weights[i];
            }

        }

        return true;

    }
}
