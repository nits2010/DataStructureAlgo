package DataStructureAlgo.Java.LeetCode2025.easy.Heap_PriorityQueue;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * Author: Nitin Gupta
 * Date: 8/31/2024
 * Question Category: 215. Kth Largest Element in an Array
 * Description: https://leetcode.com/problems/kth-largest-element-in-an-array/description/
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 * <p>
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * <p>
 * Can you solve it without sorting?
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,2,1,5,6,4], k = 2
 * Output: 5
 * Example 2:
 * <p>
 * Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
 * Output: 4
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= k <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @DivideandConquer
 * @Sorting
 * @Heap(PriorityQueue)
 * @Quickselect <p>
 * <p>
 * Company Tags
 * -----
 * @Adobe
 * @Airbnb
 * @Alibaba
 * @Amazon
 * @Apple
 * @Atlassian
 * @Baidu
 * @Bloomberg
 * @ByteDance
 * @Expedia
 * @Facebook
 * @GoldmanSachs
 * @Google
 * @caMorgan
 * @LinkedIn
 * @Microsoft
 * @Oracle
 * @Pocket
 * @Gems
 * @Salesforce
 * @Snapchat
 * @Uber
 * @VMware
 * @Yahoo
 * @Editorial https://leetcode.com/problems/kth-largest-element-in-an-array/solutions/5715695/best-solution-2-way-partition-2-variation-3-way-partition-handle-duplicate-and-large-input
 */
public class KthLargestElementInAnArray_245 {

    public static void main(String[] args) {
        boolean test = true;


        test &= test(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6, 7, 7, 8, 2, 3, 1, 1, 1, 10, 11, 5, 6, 2, 4, 7, 8, 5, 6}, 2, 10);
        test &= test(new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -5, -4, -3, -2, -1}, 4, 1);

        test &= test(new int[]{3, 2, 1, 5, 6, 4}, 2, 5);
        test &= test(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4, 4);
        System.out.println("===================================");
        System.out.println(test ? "All Passed" : "Something Failed");
    }

    private static boolean test(int[] nums, int k, int expected) {
        System.out.println("------------------------------------");
        System.out.println(" Nums : " + Arrays.toString(nums) + " k = " + k + " Expected Output:" + expected);

        SolutionUsingPriorityQueue solutionUsingHeap = new SolutionUsingPriorityQueue();
        int actual = solutionUsingHeap.findKthLargest(Arrays.copyOf(nums, nums.length), k);
        boolean testResultPQ = actual == expected;
        System.out.println("Actual Output: " + actual + " testResult " + (testResultPQ ? "Pass" : "Fail"));

        SolutionUsingQuickSelect solutionUsingQuickSelect = new SolutionUsingQuickSelect();
        actual = solutionUsingQuickSelect.findKthLargest(Arrays.copyOf(nums, nums.length), k);
        boolean testResultQuick = actual == expected;
        System.out.println("QuickSelect Actual Output: " + actual + " testResultQuick " + (testResultQuick ? "Pass" : "Fail"));


        SolutionUsingQuickSelect3Way solutionUsingQuickSelect3Way = new SolutionUsingQuickSelect3Way();
        actual = solutionUsingQuickSelect3Way.findKthLargest(Arrays.copyOf(nums, nums.length), k);
        boolean testResultQuick3Way = actual == expected;
        System.out.println("QuickSelect Actual Output: " + actual + " testResultQuick3Way " + (testResultQuick3Way ? "Pass" : "Fail"));
        return testResultPQ && testResultQuick && testResultQuick3Way;

    }


    /**
     * Building PQ will take O(n*log(k)) time.
     * T/s : O(n*log(k)) / O(k)
     */
    static class SolutionUsingPriorityQueue {
        public int findKthLargest(int[] nums, int k) {
            final PriorityQueue<Integer> pq = new PriorityQueue<>(k);
            for (int num : nums)
                add(pq, k, num);

            return pq.isEmpty() ? Integer.MIN_VALUE : pq.peek();

        }

        public void add(final PriorityQueue<Integer> pq, int k, int val) {
            pq.offer(val);
            if (pq.size() > k)
                pq.poll();

        }

    }

    /**
     * {@link DataStructureAlgo.Java.nonleetcode.Sorts.QuickSort}
     * This algo will work in O(n) time and O(1) space however worst case could be O(n^2).
     * To fix that, we can sort the array if size <= 5, or we can choose a pivot element as medianOfMedian of array
     */
    static class SolutionUsingQuickSelect {

        Random random = new Random();

        public int findKthLargest(int[] nums, int k) {
            if (nums == null || nums.length == 0)
                return Integer.MIN_VALUE;

            // this algorithm works on the same principle that quicksort works;
            // however, we don't need to sort the array fully, we will sort the array partially till kth the largest element
            // we will use partition method of quickSort.
            // partition method, find the partition point at which all the elements on the left side are smaller than partition point,
            // and all the elements on the right side are greater than partition point
            // as soon as the partition point hit the kth the largest element (which is nothing but n - k the element from the back, where n is size of array )
            // we will break.

            int n = nums.length;
            int low = 0;
            int high = n - 1;

            while (low < high) {

                int partition = _2WayPartitionV2(nums, low, high);
                if (partition == n - k)
                    break;
                if (partition < n - k)
                    low = partition + 1;
                else
                    high = partition - 1;
            }

            return nums[n - k];
        }

        /**
         * This algo works in O(n) time.
         * This is also known as 2-way partition as we are partition array at pivot on two sides.
         * <p>
         * Run flow;
         * nums  = { 4,2,3,8,2,6,9} low = 0, high = 6
         * l = 1, r = 6, pivot = 0; l < r
         * //skip all elements that are less than pivot
         * nums[l] <= nums[pivot] => nums[1] <= nums[0] => 2 < 4 => l++
         * l will eventually reach at l=3
         * <p>
         * //skip all elements that are greater than pivot
         * nums[r] > nums[pivot] => nums[6] <= nums[0] => 9 > 4 => r--
         * r will eventually reach at r = 4
         * <p>
         * here l < r => swap l,r
         * { 4,2,3,2,8,6,9}
         * l=3, r=4 =>  l = 4 & r = 3
         * <p>
         * l > r; break;
         * swap pivot , r
         * { 2,2,3,4,8,6,9}
         * partition point = 3
         *
         * @param nums
         * @param low
         * @param high
         * @return
         */

        private int _2WayPartition(int[] nums, int low, int high) {
            if (low >= high)
                return -1;

            int l = low + 1;
            int r = high;

            // to avoid hitting the worst case, assume an array is sorted ascending order
            // randomize pivot/boundary
            int randomIdx = low + random.nextInt(high - low + 1); // to avoid hitting the worst case, assume an array is sorted ascending order
            swap(nums, randomIdx, low);

            int pivot = low;

            while (l < r) {

                //skip all the element towards right, which are lesser/equal to a pivot element
                while (l < high && nums[l] <= nums[pivot])
                    l++;

                //skip all the element towards left, which are greater than a pivot element
                while (r > low && nums[r] > nums[pivot])
                    r--;

                //if there is a gap b/w l and r, means our pivot will be siting in b/w of it. hence swap it and close the gap
                if (l < r)
                    swap(nums, l, r);
            }
            //if the gap is closed, then put pivot its right position which is on r
            swap(nums, pivot, r);
            return r;
        }


        /**
         * This is another way of doing two-way partition.
         * <p>
         * https://www.youtube.com/watch?v=dOytFZFYbvo
         * https://www.youtube.com/watch?v=pM-6r5xsNEY
         * <p>
         * <p>
         * num = [ 4 , 5 , 3 , 8 , 1 ], low=0, high=4
         * l = 1, boundary = 0, pivotElement = 4
         * num[l] <= pivotElement => num[1] <= 4 => 5 > 4 => no swap -> l++
         * l = 2, boundary = 0, pivotElement = 4
         * num[l] <= pivotElement => num[2] <= 4 => 3 < 4 => boundary++ = 1 -> swap (l,boundary)
         * num = [ 4 , 3 , 5 , 8 , 1 ]
         * l = 3, boundary = 1, pivotElement = 4
         * num[l] <= pivotElement => num[3] <= 4 => 8 > 4 => no swap -> l++
         * l = 4, boundary = 1, pivotElement = 4
         * num[l] <= pivotElement => num[4] <= 4 => 1 <= 4 =>  boundary++ = 2 -> swap (l,boundary)
         * num = [ 4 , 3 , 1 , 8 , 5 ]
         * l = 5, boundary = 1, pivotElement = 4 ; break
         * <p>
         * swap (low, boundary) -> swap (0, 2)
         * num = [ 1 , 3 , 4 , 8 , 5 ] pivot = 2 (boundary)
         *
         * @param nums
         * @param low
         * @param high
         * @return
         */
        private int _2WayPartitionV2(int[] nums, int low, int high) {

            int l = low + 1;

            // to avoid hitting the worst case, assume an array is sorted ascending order
            // randomize pivot/boundary
            int randomIdx = low + random.nextInt(high - low + 1);
            swap(nums, randomIdx, low);

            int boundary = low; //same as pivot
            int pivotElement = nums[boundary];

            while (l <= high) {
                if (nums[l] <= pivotElement) {
                    boundary++; // move boundary
                    swap(nums, l, boundary);
                }
                l++;
            }
            swap(nums, boundary, low);
            return boundary;

        }


        private void swap(int[] nums, int l, int r) {
            int temp = nums[l];
            nums[l] = nums[r];
            nums[r] = temp;
        }


    }


    static class SolutionUsingQuickSelect3Way {

        Random random = new Random();

        public int findKthLargest(int[] nums, int k) {
            if (nums == null || nums.length == 0)
                return Integer.MIN_VALUE;

            // this algorithm works on the same principle that quicksort works;
            // however, we don't need to sort the array fully, we will sort the array partially till kth the largest element
            // we will use partition method of quickSort.
            // partition method, find the partition point at which all the elements on the left side are smaller than partition point,
            // and all the elements on the right side are greater than partition point
            // as soon as the partition point hit the kth the largest element (which is nothing but n - k the element from the back, where n is size of array )
            // we will break.

            int n = nums.length;
            int low = 0;
            int high = n - 1;

            while (low < high) {

                int[] partition = _3WayPartition(nums, low, high);

                if (partition[0] > n - k) {
                    high = partition[0] - 1;
                } else if (partition[1] < n - k)
                    low = partition[1] + 1;
                else if (partition[0] <= n - k && partition[1] >= n - k)
                    break;
            }

            return nums[n - k];
        }


        /**
         * In the 3-way partition method, we are indeed finding the left and right boundaries. Here’s a quick summary:
         * <p>
         * Left Boundary (lt): This marks the end of the section where all elements are less than the pivot.
         * Right Boundary (gt): This marks the start of the section where all elements are greater than the pivot.
         * The elements between these boundaries are equal to the pivot. This method is particularly useful when the array contains many duplicate elements, as it efficiently groups them together.
         * <p>
         * Recap of the Process
         * Initialize: lt to the start, gt to the end, and l to the element next to the pivot.
         * Traverse: Move through the array with l:
         * If nums[l] is less than the pivot, swap it with nums[lt] and move both lt and l forward.
         * If nums[l] is greater than the pivot, swap it with nums[gt] and move gt backward.
         * If nums[l] is equal to the pivot, just move l forward.
         * Result: The array is partitioned into three sections:
         * Elements less than the pivot.
         * Elements equal to the pivot.
         * Elements greater than the pivot.
         * This approach ensures that the pivot elements are grouped together, making the sorting process more efficient for arrays with many duplicates.
         * <p>
         * <p>
         * Step-by-Step Tracing with Highlighted Swaps
         * Example
         * Consider the array: [4, 9, 4, 4, 1, 9, 4, 4, 9, 4, 4, 1, 4] with pivot 4 (first element).
         * <p>
         * Initial array: [4, 9, 4, 4, 1, 9, 4, 4, 9, 4, 4, 1, 4]
         * Pivot: 4
         * Steps:
         * Start: lt = 0, gt = 12, l = 1
         * Iteration 1: nums[l] = 9 (greater than pivot)
         * Swap nums[l] and nums[gt]: [4, **4**, 4, 4, 1, 9, 4, 4, 9, 4, 4, 1, **9**]
         * Update gt = 11
         * Iteration 2: nums[l] = 4 (equal to pivot)
         * Move l to 2
         * Iteration 3: nums[l] = 4 (equal to pivot)
         * Move l to 3
         * Iteration 4: nums[l] = 4 (equal to pivot)
         * Move l to 4
         * Iteration 5: nums[l] = 1 (less than pivot)
         * Swap nums[l] and nums[lt]: [**1**, 4, 4, 4, **4**, 9, 4, 4, 9, 4, 4, 1, 9]
         * Update lt = 1, l = 5
         * Iteration 6: nums[l] = 9 (greater than pivot)
         * Swap nums[l] and nums[gt]: [1, 4, 4, 4, 4, **1**, 4, 4, 9, 4, 4, **9**, 9]
         * Update gt = 10
         * Iteration 7: nums[l] = 1 (less than pivot)
         * Swap nums[l] and nums[lt]: [1, **1**, 4, 4, 4, 4, 4, 4, 9, 4, 4, 9, 9]
         * Update lt = 2, l = 6
         * Iteration 8: nums[l] = 4 (equal to pivot)
         * Move l to 7
         * Iteration 9: nums[l] = 4 (equal to pivot)
         * Move l to 8
         * Iteration 10: nums[l] = 9 (greater than pivot)
         * Swap nums[l] and nums[gt]: [1, 1, 4, 4, 4, 4, 4, 4, **4**, 4, 9, 9, 9]
         * Update gt = 9
         * Iteration 11: nums[l] = 4 (equal to pivot)
         * Move l to 9
         * Iteration 12: nums[l] = 4 (equal to pivot)
         * Move l to 10
         * Final Array
         * [1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 9, 9, 9]
         * Pivot indices: 2 to 9
         *
         * @param nums
         * @param low
         * @param high
         * @return
         */
        private int[] _3WayPartition(int[] nums, int low, int high) {


            // to avoid hitting the worst case, assume an array is sorted ascending order
            // randomize pivot/boundary
            int randomIdx = low + random.nextInt(high - low + 1);
            swap(nums, randomIdx, low);

            //leftBoundary denotes the left index of the pivot element
            int leftBoundary = low;

            //rightBoundary denotes the right index of the pivot element
            int rightBoundary = high; //same as pivot

            int pivotElement = nums[leftBoundary];

            int l = low + 1;

            while (l <= rightBoundary) {

                //the current element is lesser than a pivot element, hence the left boundary needs to change ; the element should be on the left side
                if (nums[l] < pivotElement) {

                    swap(nums, l, leftBoundary);

                    l++;

                    leftBoundary++;

                } else if (nums[l] > pivotElement) {
                    //the current element is greater than a pivot element, hence the right boundary needs to change; the element should be on the right side
                    swap(nums, l, rightBoundary);
                    rightBoundary--;
                } else if (nums[l] == pivotElement) {
                    //skip such duplicate elements, hence move to next element
                    l++;
                }
            }
            return new int[]{leftBoundary, rightBoundary};


        }


        private void swap(int[] nums, int l, int r) {
            int temp = nums[l];
            nums[l] = nums[r];
            nums[r] = temp;
        }


    }
}
