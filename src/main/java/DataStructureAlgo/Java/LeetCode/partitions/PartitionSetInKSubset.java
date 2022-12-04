package DataStructureAlgo.Java.LeetCode.partitions;

import java.util.Arrays;

/**
 * Author: Nitin Gupta
 * Date: 2019-06-25
 * Description:https://www.geeksforgeeks.org/bell-numbers-number-of-ways-to-partition-a-set/
 * https://www.geeksforgeeks.org/count-number-of-ways-to-partition-a-set-into-k-subsets/
 * Given two numbers n and k where n represents number of elements in a set, find number of ways to partition the set into k subsets.
 * <p>
 * Example:
 * <p>
 * Input: n = 3, k = 2
 * Output: 3
 * Explanation: Let the set be {1, 2, 3}, we can partition
 * it into 2 subsets in following ways
 * {{1,2}, {3}},  {{1}, {2,3}},  {{1,3}, {2}}
 * <p>
 * Input: n = 3, k = 1
 * Output: 1
 * Explanation: There is only one way {{1, 2, 3}}
 */
public class PartitionSetInKSubset {

    public static void main(String []args) {

        int res = partitionSetInKSubset(9, 3);
        int res2 = partitionSetInKSubsetSpaceOptimized(9, 3);

        System.out.println(res);
        System.out.println(res2);
    }

    /**
     * in order to make partition of given set say with n numbers in k different sets.
     * each number can go from either of k sets.
     * So each number has a choice to go from 1 to k sets.
     * example; n 3 [ 1,2,3] and k = 2;
     * then
     * { {1,2} ,{3}} -> 1 and 2 goes to set 1 and 3 goes to set 2
     * { {1} ,{2,3}} -> 1 to set 1 and 2 & 3 goes to set 2
     * { {2} ,{1,3}} -> 2 to set 1 and 1 & 3 goes to set 2
     * <p>
     * * so for every element, it could be part of current set we are taking or some other set.
     * <p>
     * S(n,k) is number of partition of dividing n elements in k sets
     * <p>
     * S(n, k-1) elements get added to the last partition. Means add this number to last partition
     * S(n, k) element get added to all sets. so we have to multiple the number of sets which is k here
     * hence k*S(n,k)
     * <p>
     * Hence
     * S(n+1, k) = k*S(n,k) + S(n,k-1)
     * =>
     * S(n, k) = k*S(n-1,k) + S(n-1,k-1)
     * <p>
     *
     * O(n*n) / O(n*n)
     * @param n
     * @param k
     * @return
     */
    private static int partitionSetInKSubset(int n, int k) {

        int s[][] = new int[n + 1][n + 1];

        for (int i = 0; i <= n; i++)
            s[i][0] = 0; //keeping 0 element in a set is not possible so 0

        for (int i = 0; i <= k; i++)
            s[0][i] = 0; //we have 0 element, so not possible

        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= i && j<=k; j++) {

                //if there is only 1 element to partition then only 1 partition is possible
                //if there are n elements but only 1 partition need to make then we have to group all of them in single set so 1
                if (i == 1 || j == 1) {
                    s[i][j] = 1;
                } else
                    s[i][j] = j * s[i - 1][j] + s[i - 1][j - 1];
            }
        }

        return s[n][k];


    }



    /**
     * in order to make partition of given set say with n numbers in k different sets.
     * each number can go from either of k sets.
     * So each number has a choice to go from 1 to k sets.
     * example; n 3 [ 1,2,3] and k = 2;
     * then
     * { {1,2} ,{3}} -> 1 and 2 goes to set 1 and 3 goes to set 2
     * { {1} ,{2,3}} -> 1 to set 1 and 2 & 3 goes to set 2
     * { {2} ,{1,3}} -> 2 to set 1 and 1 & 3 goes to set 2
     * <p>
     * * so for every element, it could be part of current set we are taking for or some other set.
     * <p>
     * S(n,k) is number of partition of dividing n elements in k sets
     * <p>
     * S(n, k-1) elements get added to the last partition. Means add this number to last partition
     * S(n, k) element get added to all sets. so we have to multiple the number of sets which is k here
     * hence k*S(n,k)
     * <p>
     * Hence
     * S(n+1, k) = k*S(n,k) + S(n,k-1)
     * =>
     * S(n, k) = k*S(n-1,k) + S(n-1,k-1)
     * <p>
     *
     * Optimization:
     *
     * We can see at any moment we are only using previous row and previous column.
     * for example:
     * s(n,k) uses
     **     s(n-1,k) -> previous row
     **     s(n-1, k-1) -> previous row and previous column
     *
     * so we can store the two rows and rotate them
     * s[2][k]
     *
     * s[i%2][i] = j*s[1-i%2][j] + s[1-i%2][j-1]
     *
     *  O(n*n) / O(n)
     * @param n
     * @param k
     * @return
     */
    private static int partitionSetInKSubsetSpaceOptimized(int n, int k) {

        int s[][] = new int[2][n + 1];

        Arrays.fill(s[0], 0);
        Arrays.fill(s[1], 0);

        int row = 0; // 0 or 1
        for (int i = 1; i <= n; i++) {

            row = i & 1; // if i is even like 2 (10) then it will be 0 otherwise it will be 1

            for (int j = 1; j <= i && j<=k; j++) {

                //if there is only 1 element to partition then only 1 partition is possible
                //if there are n elements but only 1 partition need to make then we have to group all of them in single set so 1
                if (i == 1 || j == 1) {
                    s[row][j] = 1;
                } else
                    s[row][j] = j * s[1 - row][j] + s[1 - row][j - 1];
            }
        }

        return s[row][k];


    }

    /**
     * S(n, k) = k*S(n-1,k) + S(n-1,k-1)
     * * Bell number can be calculate using bell triangle
     * * <p>
     * * 1
     * * 1  2
     * * 2  3  5
     * * 5  7  10  15
     * * 15 20 27  37 52
     * * <p>
     * * if this is first column, then copy last row last column value
     * * if this is not first column, then its a sum of previous column + (previous row, column)
     * * <p>
     * * Bell(i,j) = Bell (i-1, j-1) if j = 1
     * * *          = Bell(i, j-1) +Bell ( i-1,j-1 )
     * * <p>
     *
     * @param n
     * @return
     */
    private static int[][] bellNumber(int n) {
        int bell[][] = new int[n + 1][n + 1];
        bell[0][0] = 1;

        for (int i = 1; i <= n; i++) {

            bell[i][0] = bell[i - 1][i - 1];
            for (int j = 1; j <= i; j++) {

                bell[i][j] = bell[i - 1][j - 1] + bell[i][j - 1];
            }
        }

        return bell;
    }
}
