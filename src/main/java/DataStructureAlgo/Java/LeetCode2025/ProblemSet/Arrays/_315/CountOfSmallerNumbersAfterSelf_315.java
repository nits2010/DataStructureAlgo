package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays._315;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 6/14/2025
 * Question Title: 315. Count of Smaller Numbers After Self
 * Link: https://leetcode.com/problems/count-of-smaller-numbers-after-self/description/
 * Description: Given an integer array nums, return an integer array counts where counts[i] is the number of smaller elements to the right of nums[i].
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [5,2,6,1]
 * Output: [2,1,1,0]
 * Explanation:
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 * Example 2:
 * <p>
 * Input: nums = [-1]
 * Output: [0]
 * Example 3:
 * <p>
 * Input: nums = [-1,-1]
 * Output: [0,0]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays._327.CountOfRangeSum_327}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @hard
 * @Array
 * @BinarySearch
 * @DivideandConquer
 * @BinaryIndexedTree
 * @SegmentTree
 * @MergeSort
 * @OrderedSet <p><p>
 * Company Tags
 * -----
 * @Amazon
 * @Apple
 * @Facebook
 * @Google
 * @Microsoft
 * @Oracle
 * @Uber
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class CountOfSmallerNumbersAfterSelf_315 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{5, 2, 6, 1}, Arrays.asList(2, 1, 1, 0)));
        tests.add(test(new int[]{-1}, Arrays.asList(0)));
        tests.add(test(new int[]{-1, -1}, Arrays.asList(0, 0)));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, List<Integer> expected) {
        //add print here
        CommonMethods.printTest(new String[]{"nums", "Expected"}, true, nums, expected);

        List<Integer> output;
        boolean pass, finalPass = true;

        output = new Solution().countSmaller(nums);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"MergeSort", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = new Solution_BST().countSmaller(nums);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"BST", "Pass"}, false, output, pass ? "PASS" : "FAIL");
        return finalPass;

    }

    /**
     * ref: https://leetcode.com/problems/count-of-smaller-numbers-after-self/solutions/445769/merge-sort-clear-simple-explanation-with-examples-o-n-lg-n
     * Naive Approach: For each element, scan in a right and find how many elements are smaller than the current element. That takes total O(n^2) time.
     * How can we improve ?
     * Important observations:
     * 1. We don't care about how many elements are greater than current on the left.
     * 2. If we sort the array, then we can easily find how many elements are greater than current elements; however, this will mess up the index. To preserve, we need to make sure to cache the indexes as well.
     * 3. For a single element array, we can get answer (0) in constant time, similarly for 2 element arrays we can get in constant time O(1).
     * 4. If we divide the array in smaller chunks such that each left and right has its own answer previously, then we need to find how many more elements are now greater than current if we join the left and right array.
     * <p>
     * This leads to divide and concur approach. Since, sorting (with index cache) giving advantage, we can try merge Sort technique where a merge process will sort the array and divide process make an array smaller.
     * <p>
     * Example:
     * [5,2,6,1]
     * [5,2][6,1]
     * [5][2][6][1]
     * <p>
     * for bottom most, we know its 0 always. Now when we merge [5] [2] then just like during a merge process, we know that we keep the smallest element b/w current element pointed by index i , j. Once we know that nums[i] < nums[j], then it means that
     * element at j has at least one element which is smaller than it, nums[i], however in order to make in count, we need to make sure that its index is greater than itself, i.e. it should be on the right side, for which we can utilize the index cache.
     * if that follows, then we increment the count for nums[j], however, if nums[i] > nums[j] then same applies here.
     * <p>
     * left subarray : [5]
     * right subarray : [2]
     * merged subarray: [2,5]
     * resultant counts : 5(1), 2(0) ; not the index of 2 is 1 while the index of 5 is 0, hence its valid count.
     * <p>
     * similarly
     * [6] <>[1] => [1,6] ; 6(1), 1(0) ; note here 6 > 1 and index of 1 is 3 while for 6 its 2
     * <p>
     * left : [2,5]
     * right: [1,6]
     * <p>
     * now as size grows, finding all index for given element would become O(n), ( you may think to use map to reduce, but that won't work since, once the element is passed in merge process, it will never be going to touch again, which makes map not worth).
     * We need to know for every element, how many are greater than it.
     * <p>
     * Example:
     * left:  [9,11,15]
     * right: [1,3,6]
     * here all elements on right subarray are smaller than left subarray. Consider [9] & [2] since 2 < 9 and (assume) 2 is on the right side of 9 in the original array, then it means we need to increase the count for 9 by 1. However, in the left subarray
     * all elements would be greater than 2 since it's smaller than its first element itself. Which implies that we have to increase the count of all elements in left by 1 for the first element 2 in the right subarray.
     * This will go on, as 3 < 9, means in such cases; we need to update the count of each element in left subarray by the times the number of elements in right subarray. Which makes its O(n).
     * <p>
     * To improve on this, we can use lazy update. So instead of updating right away, we will keep a count that how many elements are less than the current element, and once that condition is break where the current element becomes smaller, then we will update its count.
     * In the above example,
     * count = 0, since 2 < 9 -> count = 1
     * count = 1, since 3 < 9 -> count = 2
     * count = 2, since 6 < 9 -> count = 3
     * <p>
     * now a right array is completed in this case; now we can update all elements starting from 9, with count.
     * This way it will be O(n) only and will be done during the merge process itself
     */
    static class Solution {
        public List<Integer> countSmaller(int[] nums) {

            int length = nums.length;

            int[] result = new int[length];
            int[] index = new int[length]; // this will keep the cache of index of each element

            //cache the each index of nums[i] as i, initially it will be index[i] = i
            for (int i = 0; i < length; i++)
                index[i] = i;

            mergeSort(nums, index, 0, length - 1, result);

            return Arrays.asList(Arrays.stream(result).boxed().toArray(Integer[]::new));

        }

        private void mergeSort(int[] nums, int[] index, int start, int end, int[] result) {

            if (start >= end)
                return;

            int mid = (start + end) >>> 1;
            mergeSort(nums, index, start, mid, result);
            mergeSort(nums, index, mid + 1, end, result);
            merge(nums, index, start, mid, end, result);

        }

        private void merge(int[] nums, int[] index, int start, int mid, int end, int[] result) {

            int i = start;
            int j = mid + 1;
            int countSmaller = 0; //represent the count of element being smaller the current element
            int[] temp = new int[end - start + 1]; // this will contain the index of merged sorted element
            int k = 0;
            while (i <= mid && j <= end) {
                int elementAti = nums[index[i]]; // get the element based on original index
                int elementAtj = nums[index[j]];

                if (elementAtj < elementAti) {
                    //merge them, cache the index
                    temp[k++] = index[j++];

                    //count this element since nums[i] > nums[j]
                    countSmaller++;

                } else {
                    // element at nums[i] <= nums[j], means it breaks and move the to next element of i, update result of current element
                    result[index[i]] += countSmaller;

                    //merge them
                    temp[k++] = index[i++];

                }

            }

            //merge remainings
            while (i <= mid) {
                result[index[i]] += countSmaller;
                temp[k++] = index[i++];
            }

            while (j <= end) {
                temp[k++] = index[j++];
            }

            //copy it to an original index array.
            i = start;
            int idx = 0;
            while (idx < k) {
                index[i++] = temp[idx++];
            }
        }
    }


    /**
     * We can solve this problem using binary search tree as well. Since we need to know all the element which are smaller the current element, we can leverge the binary search tree property.
     * For each root, all element on left side are smaller then root element it self.
     * We can scan from right to left and keep inserting the element in BST.
     * While inserting, whenever a new nodes goes in left, we can count it for this root and once it goes right, then we can update the result for this element which is getting insert.
     * <p>
     * Worst case is O(n^2)
     * this can be improved by balanced binary search tree
     */
    static class Solution_BST {

        class TreeNode {
            int val;
            int countSmaller = 1; // Total nodes in its left subtree + itself.
            //countSmaller keeps track of:
            // The number of nodes in the left subtree, plus
            // The current node itself (hence initialized to 1)

            TreeNode(int v) {
                this.val = v;
            }

            TreeNode left, right;
        }

        public List<Integer> countSmaller(int[] nums) {
            int length = nums.length;
            List<Integer> result = new ArrayList<>(length);

            //for the last element, there will not be any smaller on right side.
            //hence this is our root
            TreeNode root = new TreeNode(nums[length - 1]);
            result.add(0);
            //traverse right to left
            for (int i = length - 2; i >= 0; i--) {
                result.add(insert(root, nums[i]));
            }

            Collections.reverse(result); //since we traverse right to left,
            return result;
        }

        //return how many element are smaller then ele on right side
        private int insert(TreeNode root, int ele) {
            int countSmaller = 0;

            TreeNode temp = root;

            while (temp != null) {

                if (temp.val >= ele) {
                    // increase the count for this root
                    temp.countSmaller++;

                    //insert or move left
                    if (temp.left == null) {
                        temp.left = new TreeNode(ele);
                        break;
                    } else {
                        temp = temp.left;
                    }
                } else {
                    //update the count for this element
                    countSmaller += temp.countSmaller;

                    //insert or move right
                    if (temp.right == null) {
                        temp.right = new TreeNode(ele);
                        break;
                    } else {
                        temp = temp.right;
                    }
                }
            }
            return countSmaller;

        }
    }

}
