package Java.LeetCode;

import Java.HelpersToPrint.Printer;

import java.util.Arrays;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-22
 * Description: https://leetcode.com/discuss/interview-question/363036/walmart-oa-2019-activate-fountains
 * Min number of fountains needed to water the array:
 * <p>
 * There is a linear garden from 1 to n. At each point there is a fountain. Given array a[n] tells info about fountain such that
 * its range is max(i-a[i],1) to the left of fountain to min(i+a[i],n) to the right of fountain. Find minimum no. of fountains needed
 * to be activated so that whole garden is covered.
 * <p>
 * Example: If n=3 and a={1,2,1} then second fountain has range 1 to 3. So only 1 fountain needed. Here fountain at 1 has range of 1 to 2,
 * fountain at 2 has range of 1 to 3 and fountain at 3 has range of 2 to 3 So only fountain 2 is enough to be activated to cover the whole
 * garden
 */
public class ActivateFountains {

    public static void main(String[] args) {
        test(new int[]{0, 0, 0, 3, 0, 0, 2, 0, 0}, 2);
        test(new int[]{0, 0, 0, 3, 0, 0, 2, 0, 0}, 2);
        test(new int[]{3, 0, 2, 0, 1, 0}, 2);
        test(new int[]{3, 0, 1, 0, 1, 0}, 2);
        test(new int[]{3, 0, 1, 0, 0, 1}, 2);
        test(new int[]{2, 0, 2, 0, 1, 0}, 2);
        test(new int[]{2, 0, 0, 0, 0}, 3);
        test(new int[]{0, 0, 0, 0, 0}, 5);
        test(new int[]{1, 2, 1}, 1);
        test(new int[]{0, 1, 0}, 1);
        test(new int[]{2, 1, 2, 1, 2, 4}, 2);
        test(new int[]{0, 0, 0, 0, 2, 0, 2, 3}, 4);


    }

    private static void test(int[] fountains, int expected) {
        System.out.println(Printer.toString(fountains));

        ActivateFountainsIntervalMerge intervalsMerge = new ActivateFountainsIntervalMerge();
        ActivateFountainsIntervalMergeOptimized optimized = new ActivateFountainsIntervalMergeOptimized();
        System.out.println("intervalsMerge; Obtained :" + intervalsMerge.activateFountains(fountains) + "expected :" + expected);
        System.out.println("intervalsMerge-optimized; Obtained :" + optimized.activateFountains(fountains) + "expected :" + expected);
    }


}


/**
 * {@link Java.LeetCode.intervalRelatedProblems.IntervalListInterSections}
 * After observation of fountain range we can see if we find a fountain that covers max garden then we only add another fountain when previous fountain
 * don't cover till this garden point.
 * Means;
 * Fountain A (x,y)
 * and Fountain B (z,m)
 * <p>
 * if(x<=z and y>=m) then x,y already covering garden till point B
 * <p>
 * But if Fountain C ( q,r)
 * <p>
 * if(y < r ) then our fountain (x,y) is not covering till this point, Hence we need to increase its range by making (x,r)
 * We don't need to worry about the x to change, since whatever the case, our minimum value in left is always 1, which is start of the garden.
 * <p>
 * Complexity: O(n*log(n)) / O(n)
 */
class ActivateFountainsIntervalMerge {

    /**
     * *1 2 3 4 5  6
     * [2,1,2,1,2,4] n = 6
     * <p>
     * [(1,3),(1,3),(1,5),(3,5),(3,6), (2,6)]
     * <p>
     * [(1,5),(1,3),(1,3),(2,6),(3,6),(3,5)] l = 1 and r = 5; i=0 F=1
     * [(1,5),(1,3),(1,3),(2,6),(3,6),(3,5)] l = 1 and r = 5; i=1 {(1,3) is in (1,5)}
     * [(1,5),(1,3),(1,3),(2,6),(3,6),(3,5)] l = 1 and r = 5; i=2 {(1,3) is in (1,5)}
     * [(1,5),(1,3),(1,3),(2,6),(3,6),(3,5)] l = 1 and r = 6; i=3 {(2,6) is not in (1,5)} F = 2
     * [(1,5),(1,3),(1,3),(2,6),(3,6),(3,5)] l = 1 and r = 6; i=4 {(3,6) is not in (1,6)}
     * [(1,5),(1,3),(1,3),(2,6),(3,6),(3,5)] l = 1 and r = 6; i=5 {(3,5) is not in (1,6)}
     *
     * @param fountains
     * @return
     */
    public int activateFountains(int[] fountains) {
        int n = fountains.length;

        int interval[][] = new int[n][2];

        for (int i = 1; i <= n; i++) {
            interval[i - 1][0] = Math.max(i - fountains[i - 1], 1);
            interval[i - 1][1] = Math.min(i + fountains[i - 1], n);

        }

        /**
         * Sort the interval such a way that left is sorted in increasing order and right is sorted in decreasing order.
         * To make right in decreasing order, will help us to choose right fountain that cover max garden from start of the garden
         */
        Arrays.sort(interval, (o1, o2) -> {
            if (o1[0] == o2[0])
                return o2[1] - o1[1];
            return o1[0] - o2[0];
        });

        int left, right;
        left = interval[0][0];
        right = interval[0][1];

        int fountainsActivate = 1;

        /**
         * 1 2 3 4 5  6
         * [2,1,2,1,2,4] n = 6
         * <p>
         * [(1,3),(1,3),(1,5),(3,5),(3,6), (2,6)]
         * <p>
         * [(1,3),(1,3),(1,5),(2,6),(3,5),(3,6)] = 2
         *
         * @param fountains
         * @return
         */
        for (int i = 1; i < n; i++) {

            if (left <= interval[i][0] && right >= interval[i][1])
                continue;
            else {

                if (right < interval[i][1]) {
                    right = interval[i][1];
                    fountainsActivate++;
                }


            }

        }

        return fountainsActivate;
    }

}


/**
 * As you can see, in above code, we try to choose the first fountain which can cover as much right as possible, and then once we found a garden
 * which is not cover by this fountain, then we update this fountain range and increase the number of fountain.
 * <p>
 * To do so, we need to know from each index, how far we can go.
 * Hence at each index we'll store the max right possible from this index.
 * <p>
 * *1 2 3 4 5 6
 * [2,1,2,1,2,4] n = 6
 * [(1,3),(1,3),(1,5),(3,5),(3,6), (2,6)]
 * <p>
 * [1->5, 2->6, 3->6, 4->0, 5->0, 6->0] => [5,6,6,0,0,0] => 2
 * <p>
 * O(n)
 * <p>
 * It turns out that after the pre-processing, it is same as below problem
 * {@link MinimumJumpToReachLastJumpGame}
 */
class ActivateFountainsIntervalMergeOptimized {
    /**
     * *1 2 3 4 5  6
     * [2,1,2,1,2,4] n = 6
     * <p>
     * [(1,3),(1,3),(1,5),(3,5),(3,6), (2,6)]
     * <p>
     *
     * @param fountains
     * @return
     */
    public int activateFountains(int[] fountains) {
        int n = fountains.length;

        int interval[] = new int[n];

        for (int i = 1; i <= n; i++) {

            int left = Math.max(i - fountains[i - 1], 1);
            int right = Math.min(i + fountains[i - 1], n);

            interval[left - 1] = right;

        }


        int right = interval[0];
        int nextGreaterRight = right;

        int fountainsActivate = 1;

        /**
         * 1 2 3 4 5  6
         * [2,1,2,1,2,4] n = 6
         * <p>
         * [(1,3),(1,3),(1,5),(3,5),(3,6), (2,6)]
         * <p>
         * [5,6,6,0,0,0]
         *
         * @param fountains
         * @return
         */
        for (int i = 1; i < n; i++) {
            nextGreaterRight = Math.max(nextGreaterRight, interval[i]);

            /**
             * If the last fountain can cover only this point, then update with next fountain.
             */
            if (i == right) {
                fountainsActivate++;
                right = nextGreaterRight;
            }


        }

        return fountainsActivate;
    }

}