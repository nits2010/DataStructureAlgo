package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays._2064;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date:14/11/24
 * Question Category: 2064. Minimized Maximum of Products Distributed to Any Store
 * Description: https://leetcode.com/problems/minimized-maximum-of-products-distributed-to-any-store/description/
 * You are given an integer n indicating there are n specialty retail stores. There are m product types of varying amounts, which are given as a 0-indexed integer array quantities, where quantities[i] represents the number of products of the ith product type.
 * <p>
 * You need to distribute all products to the retail stores following these rules:
 * <p>
 * A store can only be given at most one product type but can be given any amount of it.
 * After distribution, each store will have been given some number of products (possibly 0). Let x represent the maximum number of products given to any store. You want x to be as small as possible, i.e., you want to minimize the maximum number of products that are given to any store.
 * Return the minimum possible x.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 6, quantities = [11,6]
 * Output: 3
 * Explanation: One optimal way is:
 * - The 11 products of type 0 are distributed to the first four stores in these amounts: 2, 3, 3, 3
 * - The 6 products of type 1 are distributed to the other two stores in these amounts: 3, 3
 * The maximum number of products given to any store is max(2, 3, 3, 3, 3, 3) = 3.
 * Example 2:
 * <p>
 * Input: n = 7, quantities = [15,10,10]
 * Output: 5
 * Explanation: One optimal way is:
 * - The 15 products of type 0 are distributed to the first three stores in these amounts: 5, 5, 5
 * - The 10 products of type 1 are distributed to the next two stores in these amounts: 5, 5
 * - The 10 products of type 2 are distributed to the last two stores in these amounts: 5, 5
 * The maximum number of products given to any store is max(5, 5, 5, 5, 5, 5, 5) = 5.
 * Example 3:
 * <p>
 * Input: n = 1, quantities = [100000]
 * Output: 100000
 * Explanation: The only optimal way is:
 * - The 100000 products of type 0 are distributed to the only store.
 * The maximum number of products given to any store is max(100000) = 100000.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == quantities.length
 * 1 <= m <= n <= 105
 * 1 <= quantities[i] <= 105
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 * @medium
 * @Array
 * @BinarySearch
 * @Heap(PriorityQueue) <p>
 * Company Tags
 * -----
 * @Editorial
 */

public class MinimizedMaximumOfProductsDistributedToAnyStore_2064 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        //add tests cases here
        tests.add(tests(6, new int[]{11, 6}, 3));
        tests.add(tests(7, new int[]{15, 10, 10}, 5));
        tests.add(tests(1, new int[]{100000}, 100000));

        CommonMethods.printAllTestOutCome(tests);
    }


    private static boolean tests(int n, int[] quantities, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"n", "quantities", "Expected"}, true, n, quantities, expected);

        int output;
        boolean pass, finalPass = true;

        SolutionBinarySearch_UsingStores solutionBinarySearchUsingStores = new SolutionBinarySearch_UsingStores();
        output = solutionBinarySearchUsingStores.minimizedMaximum(n, quantities);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"BinarySearch-UsingStores", "Pass"}, false, output, pass ? "Pass" : "Fail");

        SolutionBinarySearch_UsingQuantities solutionBinarySearchUsingQuantities = new SolutionBinarySearch_UsingQuantities();
        output = solutionBinarySearchUsingQuantities.minimizedMaximum(n, quantities);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"BinarySearch-UsingQuantities", "Pass"}, false, output, pass ? "Pass" : "Fail");

        SolutionPQ solutionPQ = new SolutionPQ();
        output = solutionPQ.minimizedMaximum(n, quantities);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"PQ", "Pass"}, false, output, pass ? "Pass" : "Fail");

        return finalPass;

    }


    static class SolutionBinarySearch_UsingQuantities {
        private int max(int[] quantities) {
            int max = 0;
            for (int i : quantities)
                max = Math.max(max, i);

            return max;
        }

        public int minimizedMaximum(int n, int[] quantities) {
            int high = max(quantities); //maximum number of product can be placed in any store
            int low = 0; //minimum number of product can be placed in any store
            int output = high;

            while (low <= high) {

                //Try mid-number of product to be placed in n stores
                int mid = low + (high - low) / 2;

                //if we can place, then mid is potentially output
                if (isPossibleToDistribute(mid, quantities, n)) {
                    output = mid;

                    //minimize further
                    high = mid - 1;
                } else {

                    //we cannot put mid-product in n stores, there are products left out, increase the product
                    low = mid + 1;
                }
            }

            return output;
        }


        //based on top-down remaining
        private boolean isPossibleToDistribute(int x, int[] quantities, int stores) {

            int store = 0;
            for (int quantity : quantities) {

                //we need quantity/x store to fill all the product of current type
                store += quantity / x;

                //if we need more store, then we cannot place all the product of current type in x stores
                if (quantity % x != 0)
                    store++;

                // stores += (quantity + x - 1) / x; // ceil division

                if (store > stores)
                    return false;

            }

            return store <= stores; // ✅ allow fewer than n stores too, as a store can have quantity = 0 as well.

        }
    }



    static class SolutionBinarySearch_UsingStores {
        private int max(int[] quantities) {
            int max = 0;
            for (int i : quantities)
                max = Math.max(max, i);

            return max;
        }

        public int minimizedMaximum(int n, int[] quantities) {
            int high = max(quantities); //maximum number of product can be placed in any store
            int low = 0; //minimum number of product can be placed in any store
            int output = high;

            while (low <= high) {

                //Try mid-number of product to be placed in n stores
                int mid = low + (high - low) / 2;

                //if we can place, then mid is potentially output
                if (isPossibleToDistribute(mid, quantities, n)) {
                    output = mid;

                    //minimize further
                    high = mid - 1;
                } else {

                    //we cannot put mid-product in n stores, there are products left out, increase the product
                    low = mid + 1;
                }
            }

            return output;
        }


        //based on top-down remaining
        private boolean isPossibleToDistribute(int x, int[] quantities, int stores) {

            int quantity = quantities[0];
            int q = 0;

            for (int i = 0; i < stores; i++) {

                //can we place x product in this store?
                if (quantity > x) {
                    //get remaining stores
                    quantity -= x;
                } else {

                    //product of a q type finished, take next type
                    q++;

                    //no more left, hence placed all
                    if (q == quantities.length)
                        return true;

                    quantity = quantities[q];
                }

            }

            return false;
        }
    }


    static class SolutionPQ {

        public int minimizedMaximum(int n, int[] quantities) {
            int m = quantities.length;

            // Helper arrays - useful for the efficient initialization of the
            // priority queue
            List<int[]> typeStorePairsArray = new ArrayList<>();

            // Push all product types to the list, after assigning one store to each of them
            for (int i = 0; i < m; i++) {
                typeStorePairsArray.add(new int[]{quantities[i], 1});
            }

            PriorityQueue<int[]> typeStorePairs = new PriorityQueue<>((a, b) ->
                    Long.compare((long) b[0] * a[1], (long) a[0] * b[1])
            );

            // Initialize the priority queue
            typeStorePairs.addAll(typeStorePairsArray);

            // Iterate over the remaining n - m stores.
            for (int i = 0; i < n - m; i++) {
                // Pop the first element
                int[] pairWithMaxRatio = typeStorePairs.poll();
                int totalQuantityOfType = pairWithMaxRatio[0];
                int storesAssignedToType = pairWithMaxRatio[1];

                // Push the same element after assigning one more store to its product type
                typeStorePairs.offer(
                        new int[]{totalQuantityOfType, storesAssignedToType + 1}
                );
            }

            // Pop the first element
            int[] pairWithMaxRatio = typeStorePairs.poll();
            int totalQuantityOfType = pairWithMaxRatio[0];
            int storesAssignedToType = pairWithMaxRatio[1];

            // Return the maximum minimum ratio
            return (int) Math.ceil(
                    (double) totalQuantityOfType / storesAssignedToType
            );
        }
    }

}
