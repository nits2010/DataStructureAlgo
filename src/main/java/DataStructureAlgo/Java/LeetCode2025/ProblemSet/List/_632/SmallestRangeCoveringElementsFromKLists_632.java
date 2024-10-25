package DataStructureAlgo.Java.LeetCode2025.ProblemSet.List._632;

import DataStructureAlgo.Java.LeetCode2025.ProblemSet.List._21.MergeTwoSortedLists_21;
import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 10/13/2024
 * Question Category: 632. Smallest Range Covering Elements from K Lists
 * Description: You have k lists of sorted integers in non-decreasing order. Find the smallest range that includes at least one number from each of the k lists.
 * <p>
 * We define the range [a, b] is smaller than range [c, d] if b - a < d - c or a < c if b - a == d - c.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [[4,10,15,24,26],[0,9,12,20],[5,18,22,30]]
 * Output: [20,24]
 * Explanation:
 * List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
 * List 2: [0, 9, 12, 20], 20 is in range [20,24].
 * List 3: [5, 18, 22, 30], 22 is in range [20,24].
 * Example 2:
 * <p>
 * Input: nums = [[1,2,3],[1,2,3],[1,2,3]]
 * Output: [1,1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * nums.length == k
 * 1 <= k <= 3500
 * 1 <= nums[i].length <= 50
 * -105 <= nums[i][j] <= 105
 * nums[i] is sorted in non-decreasing order.
 * You have k lists of sorted integers in non-decreasing order. Find the smallest range that includes at least one number from each of the k lists.
 * <p>
 * We define the range [a, b] is smaller than range [c, d] if b - a < d - c or a < c if b - a == d - c.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [[4,10,15,24,26],[0,9,12,20],[5,18,22,30]]
 * Output: [20,24]
 * Explanation:
 * List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
 * List 2: [0, 9, 12, 20], 20 is in range [20,24].
 * List 3: [5, 18, 22, 30], 22 is in range [20,24].
 * Example 2:
 * <p>
 * Input: nums = [[1,2,3],[1,2,3],[1,2,3]]
 * Output: [1,1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * nums.length == k
 * 1 <= k <= 3500
 * 1 <= nums[i].length <= 50
 * -105 <= nums[i][j] <= 105
 * nums[i] is sorted in non-decreasing order.
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link MergeTwoSortedLists_21}
 * extension {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @hard
 * @Array
 * @HashTable
 * @Greedy
 * @SlidingWindow
 * @Sorting
 * @Heap(PriorityQueue) <p><p>
 * Company Tags
 * -----
 * <p><p>
 * @Editorial <a href="https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/solutions/5908465/easy-minheap-and-sliding-window-solution-inspiration-from-another-leetcode">...</a>
 */
public class SmallestRangeCoveringElementsFromKLists_632 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(List.of(List.of(4, 10, 15, 24, 26), List.of(0, 9, 12, 20), List.of(5, 18, 22, 30)), new int[]{20, 24});
        test &= test(List.of(List.of(1, 2, 3), List.of(1, 2, 3), List.of(1, 2, 3)), new int[]{1, 1});

        CommonMethods.printResult(test);
    }

    private static boolean test(List<List<Integer>> nums, int[] expected) {
        System.out.println("----------------------------------");
        System.out.println("Smallest Range Covering Elements from K Lists");
        System.out.println("Input : " + nums + " Expected : " + Arrays.toString(expected));

        int[] output;
        boolean pass, finalPass = true;

        SolutionPQ solutionpq = new SolutionPQ();
        output = solutionpq.smallestRange(nums);
        pass = output[0] == expected[0] && output[1] == expected[1];
        System.out.println("Output PQ: " + Arrays.toString(output) + " " + (pass ? "PASS" : "FAIL"));
        finalPass &= pass;

        SolutionTwoPointer solutionTwoPointer = new SolutionTwoPointer();
        output = solutionTwoPointer.smallestRange(nums);
        pass = output[0] == expected[0] && output[1] == expected[1];
        System.out.println("Output TwoPointer: " + Arrays.toString(output) + " " + (pass ? "PASS" : "FAIL"));
        finalPass &= pass;

        return finalPass;
    }


    static class SolutionPQ {

        static class Triplet {
            int element, elementIndex, listIndex;

            Triplet(int ele, int eI, int lI) {
                this.element = ele;
                this.elementIndex = eI;
                this.listIndex = lI;
            }
        }

        public int[] smallestRange(List<List<Integer>> nums) {
            if (nums == null || nums.isEmpty())
                return new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE};


            //create a min heap, holding element, list its from and its own index in list.
            PriorityQueue<Triplet> minHeap = new PriorityQueue<>(Comparator.comparingInt(item -> item.element));

            //to track the maximum value seen so far
            int maxValue = Integer.MIN_VALUE;

            int size = nums.size();

            //insert smallest element from all list
            for (int i = 0; i < size; i++) {
                int element = nums.get(i).get(0);
                Triplet triplet = new Triplet(element, 0, i);
                maxValue = Math.max(maxValue, element);
                minHeap.offer(triplet);
            }

            //initial range, 0 to max.
            //we will update this range in each iteration
            int[] range = new int[]{0, Integer.MAX_VALUE};

            //this can be `true` or minHeap.isEmpty()
            while (!minHeap.isEmpty()) {

                //fetch the smallest element among all list
                Triplet triplet = minHeap.poll();
                int listIndex = triplet.listIndex;
                int elementIndex = triplet.elementIndex;
                int element = triplet.element;

                //build a new range
                int[] newRange = new int[]{element, maxValue};

                //compare range with existing range
                range = minimum(range, newRange);

                //check if all element are processed from this `listIndex`
                List<Integer> list = nums.get(listIndex);
                if (elementIndex == list.size() - 1)
                    break; // no more element to the process, hence we found the minimum range

                //build new minimum element
                int newElement = list.get(++elementIndex);
                maxValue = Math.max(maxValue, newElement);
                Triplet newTriplet = new Triplet(newElement, elementIndex, listIndex);

                //add to minHeap
                minHeap.offer(newTriplet);

            }

            return range;

        }

        int[] minimum(int[] currRange, int[] newRange) {
            int a = currRange[0], b = currRange[1];
            int c = newRange[0], d = newRange[1];

            if ((b - a < d - c) || (a < c && (b - a == d - c)))
                return currRange;
            return newRange;
        }
    }


    /**
     * {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.Strings._76.MinimumWindowSubstring_76.SolutionSlidingWindow}
     */
    static class SolutionTwoPointer {

        static class Pair{
            int element, listIndex;

            public Pair(int element, int listIndex) {
                this.element = element;
                this.listIndex = listIndex;
            }
        }


        public int[] smallestRange(List<List<Integer>> nums) {
            if (nums == null || nums.isEmpty())
                return new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE};

            int size = nums.size();
            List<Pair> mergedList = sortedMergeList(nums);

            int []range = new int[]{0, Integer.MAX_VALUE};

            //left denotes the sliding window start
            //right denotes the sliding window end

            int left = 0, right = 0 ;

            //kListFreq denotes, in this window, how many lists have been covered and its frequency
            Map<Integer, Integer> kListFreq = new HashMap<>();
            int hasIncluded = 0 ;

            while(right < mergedList.size()){

                //expand the window
                int listIndex = mergedList.get(right).listIndex;
                int element = mergedList.get(right).element;
                kListFreq.put(listIndex, kListFreq.getOrDefault(listIndex, 0)+1);

                //if this includes another list, increment the hasIncluded count
                if(kListFreq.get(listIndex) == 1)
                    hasIncluded++;

                //if all lists have been included, update range if needed
                //shrink the window
                while (hasIncluded == size) {
                    int []newRange = new int[]{mergedList.get(left).element, element};
                    range = minimum(range, newRange);

                    listIndex = mergedList.get(left).listIndex;

                    kListFreq.put(listIndex, kListFreq.get(listIndex) - 1);

                    if(kListFreq.get(listIndex) == 0)
                        hasIncluded--;

                    left++; //shrink the window
                }

                right++;



            }

            return range;


        }

        /**
         * Merge sort can optimize this
         */
        List<Pair> sortedMergeList(List<List<Integer>> nums){
            List<Pair> mergedList = new ArrayList<>();

            //merge all the sorted list

            for(int i = 0; i<nums.size(); i++){
                for(int num : nums.get(i)){
                    mergedList.add(new Pair(num, i));
                }
            }

            //sort this list
            mergedList.sort(Comparator.comparingInt(item -> item.element));
            return mergedList;
        }
        int[] minimum(int[] currRange, int[] newRange) {
            int a = currRange[0], b = currRange[1];
            int c = newRange[0], d = newRange[1];

            if ((b - a < d - c) || (a < c && (b - a == d - c)))
                return currRange;
            return newRange;
        }
    }

}
