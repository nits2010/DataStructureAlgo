package DataStructureAlgo.Java.LeetCode;


/**
 * Author: Nitin Gupta
 * Date: 2019-08-02
 * Description: https://leetcode.com/problems/super-egg-drop/
 * You are given K eggs, and you have access to a building with N floors from 1 to N.
 * <p>
 * Each egg is identical in function, and if an egg breaks, you cannot drop it again.
 * <p>
 * You know that there exists a floor F with 0 <= F <= N such that any egg dropped at a floor higher than F will break, and any egg dropped at or below floor F will not break.
 * <p>
 * Each move, you may take an egg (if you have an unbroken one) and drop it from any floor X (with 1 <= X <= N).
 * <p>
 * Your goal is to know with certainty what the value of F is.
 * <p>
 * What is the minimum number of moves that you need to know with certainty what F is, regardless of the initial value of F?
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: K = 1, N = 2
 * Output: 2
 * Explanation:
 * Drop the egg from floor 1.  If it breaks, we know with certainty that F = 0.
 * Otherwise, drop the egg from floor 2.  If it breaks, we know with certainty that F = 1.
 * If it didn't break, then we know with certainty F = 2.
 * Hence, we needed 2 moves in the worst case to know what F is with certainty.
 * Example 2:
 * <p>
 * Input: K = 2, N = 6
 * Output: 3
 * Example 3:
 * <p>
 * Input: K = 3, N = 14
 * Output: 4
 * <p>
 * Video: https://www.youtube.com/watch?v=iOaRjDT0vjc
 * References: https://www.geeksforgeeks.org/egg-dropping-puzzle-dp-11/ , https://www.geeksforgeeks.org/eggs-dropping-puzzle-binomial-coefficient-and-binary-search-solution/
 * <p>
 * Logic: https://leetcode.com/articles/super-egg-drop/
 */
public class EggDropProblem {
    public static void main(String[] args) {
        test(2, 10);
        test(2, 6);
        test(1, 6);
        test(3, 14);
    }

    private static void test(int eggs, int floors) {

        System.out.println("\n.................\n");
        System.out.println("\nRecursive ->      Floors : " + floors + " eggs :" + eggs + " Trials :" + EggDropProblemRecursive.superEggDrop(eggs, floors));
        System.out.println("\nDP  ->            Floors : " + floors + " eggs :" + eggs + " Trials :" + EggDropProblemDP.superEggDrop(eggs, floors));
        System.out.println("\nBinary Searchg -> Floors : " + floors + " eggs :" + eggs + " Trials :" + EggDropProblemBinarySearch.superEggDrop(eggs, floors));

    }

}

/**
 * We can solve this problem using recursive manner. See we have choices
 * 1. Either egg breaks at a floor [ then we need to try below floors only]
 * 2. Or it does not break at a floor. [ we can try above floors then]
 * <p>
 * We need to find the minimum trails it needed to find the optimal floor.
 * The problem is not actually to find the critical floor, but merely to decide floors from
 * which eggs should be dropped so that total number of trials are minimized.
 * <p>
 * With above choice; if n eggs is available and k is floor then
 * <p>
 * min(n,k) is the minimum number of drops (trials) needed to find the optimal floor
 * <p>
 * min[n][k] = Min{ Max { egg breaks , egg don't break}  + 1}
 * <p>
 * *        = Min {
 * *                 Max ( eggDrop[n-1][x-1] , eggDrop[n][k-x] )  + 1 where 1<=x<=k ; x denotes attempt
 * <p>
 * *              }
 * <p>
 * Egg breaks:  if at the x'th floor and egg got break then we need to try below floor (x-1) and n-1 eggs only
 * Egg dont breaks: if at the x'th floor and egg not break then we need to try above floor (k-x) and n eggs only
 * <p>
 * LEETCODE: Time Limit Exceeded
 * <p>
 * O(eggs^floor )
 */
class EggDropProblemRecursive {


    public static int superEggDrop(int eggs, int floor) {


        /**
         * if there is only one floor or no floor, then only that many trials required
         */
        if (floor == 0 || floor == 1)
            return floor;

        /**
         * No eggs left, we can't make any trial
         */
        if (eggs == 0)
            return eggs;
        /**
         * only one egg is there, we can try at every floor
         */
        if (eggs == 1)
            return floor;

        int min = Integer.MAX_VALUE;
        for (int x = 1; x <= floor; x++)
            min = Math.min(min, Math.max(superEggDrop(eggs - 1, x - 1), superEggDrop(eggs, floor - x)));

        return min + 1;
    }
}

/**
 * Add memo
 * <p>
 * We can solve this problem using recursive manner. See we have choices
 * 1. Either egg breaks at a floor [ then we need to try below floors only]
 * 2. Or it does not break at a floor. [ we can try above floors then]
 * <p>
 * We need to find the minimum trails it needed to find the optimal floor.
 * The problem is not actually to find the critical floor, but merely to decide floors from
 * which eggs should be dropped so that total number of trials are minimized.
 * <p>
 * With above choice; if n eggs is available and k is floor then
 * <p>
 * min(n,k) is the minimum number of drops (trials) needed to find the optimal floor
 * <p>
 * min[n][k] = Min{ Max { egg breaks , egg don't break}  + 1}
 * <p>
 * *        = Min {
 * *                 Max ( min[n-1][x-1] , min[n][k-x] )  + 1 where 1<=x<=k ; x denotes attempt
 * <p>
 * *              }
 * <p>
 * Egg breaks:  if at the x'th floor and egg got break then we need to try below floor (x-1) and n-1 eggs only
 * Egg dont breaks: if at the x'th floor and egg not break then we need to try above floor (k-x) and n eggs only
 * <p>
 * O(eggs*floor^2)
 * LEETCODE: Time Limit Exceeded
 */
class EggDropProblemDP {

    public static int superEggDrop(int eggs, int floors) {

        /**
         * if there is only one floor or no floor, then only that many trials required
         */
        if (floors == 0 || floors == 1)
            return floors;

        /**
         * No eggs left, we can't make any trial
         */
        if (eggs == 0)
            return eggs;
        /**
         * only one egg is there, we can try at every floor
         */
        if (eggs == 1)
            return floors;

        int min[][] = new int[eggs + 1][floors + 1];

        /**
         * Only 0 or 1 floor
         */
        for (int i = 1; i <= eggs; i++) {
            min[i][0] = 0; // 0 floor no trails needed
            min[i][1] = 1; // 1 floor only one trial needed
        }


        /**
         * Only  1 egg
         */
        for (int i = 1; i <= floors; i++) {
            min[1][i] = i;
        }

        min[0][0] = 0; //no egg, no floor, no trail


        //Bottom up
        for (int egg = 2; egg <= eggs; egg++) {

            for (int floor = 2; floor <= floors; floor++) {

                min[egg][floor] = Integer.MAX_VALUE;

                for (int x = 1; x <= floor; x++)
                    min[egg][floor] = Math.min(
                            min[egg][floor],
                            1 + Math.max(
                                    min[egg - 1][x - 1], //eggs break; go below
                                    min[egg][floor - x] // egg don't break; go above
                            ));

            }
        }

        return min[eggs][floors];

    }


}


class EggDropProblemDP2 {
    public int superEggDrop(int eggs, int floors) {

        int[][] f = new int[eggs + 1][floors + 1];
        int m = 0;

        while (f[eggs][m] < floors) {
            ++m; // trials

            for (int egg = 1; egg <= eggs; ++egg) {
                f[egg][m] = f[egg - 1][m - 1] + f[egg][m - 1] + 1;
            }
        }
        return m;
    }
}

/**
 * After clever observation on above recurrence relationship;
 * <p>
 * min(n,k)  = 1 + min ( max ( eggDrop ( n-1, x-1 ) , eggDrop ( n, k-x) )
 * <p>
 * It seems like binomial coefficient but with small changes
 * C(n,k) = C(n-1, k-1) + C(n-1, k)
 * <p>
 * So above would look like
 * C(x, n )  = Sum ( (xCi) ) where 1<=i<=floor
 * <p>
 * But since
 * C(x,n) >= floor
 * then our solution lies in between
 * <p>
 * Logic: https://leetcode.com/articles/super-egg-drop/
 */
class EggDropProblemBinarySearch {

    public static int superEggDrop(int eggs, int floors) {
        /**
         * if there is only one floor or no floor, then only that many trials required
         */
        if (floors == 0 || floors == 1)
            return floors;

        /**
         * No eggs left, we can't make any trial
         */
        if (eggs == 0)
            return eggs;
        /**
         * only one egg is there, we can try at every floor
         */
        if (eggs == 1)
            return floors;

        /**
         * Minimum floor would be 1 as 0 floor would give you 0 trials
         * Maximum floor we can try is all floors
         */
        int minFloor = 1, maxFloor = floors;

        while (minFloor < maxFloor) {

            int currentFloor = (minFloor + maxFloor) >> 1;

            if (isLeast(currentFloor, eggs, floors) >= floors)
                maxFloor = currentFloor;
            else
                minFloor = currentFloor + 1;
        }
        return minFloor;
    }

    /**
     * Apply binomial coefficient C(x, n )  = Sum ( (xCi) ) where 1<=i<=floor
     *
     * @param currentFloor
     * @param eggs
     * @param floors
     * @return
     */
    private static int isLeast(int currentFloor, int eggs, int floors) {

        int sum = 0;
        int term = 1;

        /**
         * C(n,k) = C(n-1,k-1) + C(n-1, k )
         */
        for (int x = 1; x <= eggs && sum < floors; x++) {
            term *= currentFloor - x + 1;
            term /= x;
            sum += term;
        }


        return sum;
    }

}