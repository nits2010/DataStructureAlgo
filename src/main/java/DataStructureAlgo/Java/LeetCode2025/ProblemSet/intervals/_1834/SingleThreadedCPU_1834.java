package DataStructureAlgo.Java.LeetCode2025.ProblemSet.intervals._1834;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 6/5/2025
 * Question Title: 1834. Single-Threaded CPU
 * Link: https://leetcode.com/problems/single-threaded-cpu/description/
 * Description: You are given n​​​​​​ tasks labeled from 0 to n - 1 represented by a 2D integer array tasks, where tasks[i] = [enqueueTimei, processingTimei] means that the i​​​​​​th​​​​ task will be available to process at enqueueTimei and will take processingTimei to finish processing.
 * <p>
 * You have a single-threaded CPU that can process at most one task at a time and will act in the following way:
 * <p>
 * If the CPU is idle and there are no available tasks to process, the CPU remains idle.
 * If the CPU is idle and there are available tasks, the CPU will choose the one with the shortest processing time. If multiple tasks have the same shortest processing time, it will choose the task with the smallest index.
 * Once a task is started, the CPU will process the entire task without stopping.
 * The CPU can finish a task then start a new one instantly.
 * Return the order in which the CPU will process the tasks.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: tasks = [[1,2],[2,4],[3,2],[4,1]]
 * Output: [0,2,3,1]
 * Explanation: The events go as follows:
 * - At time = 1, task 0 is available to process. Available tasks = {0}.
 * - Also at time = 1, the idle CPU starts processing task 0. Available tasks = {}.
 * - At time = 2, task 1 is available to process. Available tasks = {1}.
 * - At time = 3, task 2 is available to process. Available tasks = {1, 2}.
 * - Also at time = 3, the CPU finishes task 0 and starts processing task 2 as it is the shortest. Available tasks = {1}.
 * - At time = 4, task 3 is available to process. Available tasks = {1, 3}.
 * - At time = 5, the CPU finishes task 2 and starts processing task 3 as it is the shortest. Available tasks = {1}.
 * - At time = 6, the CPU finishes task 3 and starts processing task 1. Available tasks = {}.
 * - At time = 10, the CPU finishes task 1 and becomes idle.
 * Example 2:
 * <p>
 * Input: tasks = [[7,10],[7,12],[7,5],[7,4],[7,2]]
 * Output: [4,3,2,0,1]
 * Explanation: The events go as follows:
 * - At time = 7, all the tasks become available. Available tasks = {0,1,2,3,4}.
 * - Also at time = 7, the idle CPU starts processing task 4. Available tasks = {0,1,2,3}.
 * - At time = 9, the CPU finishes task 4 and starts processing task 3. Available tasks = {0,1,2}.
 * - At time = 13, the CPU finishes task 3 and starts processing task 2. Available tasks = {0,1}.
 * - At time = 18, the CPU finishes task 2 and starts processing task 0. Available tasks = {1}.
 * - At time = 28, the CPU finishes task 0 and starts processing task 1. Available tasks = {}.
 * - At time = 40, the CPU finishes task 1 and becomes idle.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * tasks.length == n
 * 1 <= n <= 105
 * 1 <= enqueueTimei, processingTimei <= 109
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @Sorting
 * @Heap(PriorityQueue) <p><p>
 * Company Tags
 * -----
 * @Google <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class SingleThreadedCPU_1834 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[][]{{1, 2}, {2, 4}, {3, 2}, {4, 1}}, new int[]{0, 2, 3, 1}));
        tests.add(test(new int[][]{{7, 10}, {7, 12}, {7, 5}, {7, 4}, {7, 2}}, new int[]{4, 3, 2, 0, 1}));
        tests.add(test(new int[][]{{5, 2}, {7, 2}, {9, 4}, {6, 3}, {5, 10}, {1, 1}}, new int[]{5, 0, 1, 3, 2, 4}));
        tests.add(test(new int[][]{{19, 13}, {16, 9}, {21, 10}, {32, 25}, {37, 4}, {49, 24}, {2, 15}, {38, 41}, {37, 34}, {33, 6}, {45, 4}, {18, 18}, {46, 39}, {12, 24}}, new int[]{6, 1, 2, 9, 4, 10, 0, 11, 5, 13, 3, 8, 12, 7}));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[][] tasks, int[] expected) {
        //add print here
        CommonMethods.printTest(new String[]{"Tasks", "Expected"}, true, tasks, expected);

        int[] output;
        boolean pass, finalPass = true;

        output = new Solution().getOrder(tasks);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class Solution {
        class Task {
            int enqueueTime;
            int processingTime;
            int id;

            Task(int id, int eT, int pT) {
                this.id = id;
                enqueueTime = eT;
                processingTime = pT;
            }
        }

        public int[] getOrder(int[][] taskDetails) {
            int taskLength = taskDetails.length;

            if (taskLength <= 1)
                return new int[]{0};

            //[id, enqueueTime, processingTime]
            Task[] tasks = new Task[taskLength];

            for (int i = 0; i < taskLength; i++) {
                tasks[i] = new Task(i, taskDetails[i][0], taskDetails[i][1]);
            }

            //sort the tasks based on start time, otherwise by processing time, otherwise by id.
            Arrays.sort(tasks, (a, b) -> Integer.compare(a.enqueueTime, b.enqueueTime));

            int[] order = new int[taskLength];
            PriorityQueue<Task> minHeap = new PriorityQueue<>((a, b) -> {

                //sort by processingTime
                if (a.processingTime != b.processingTime)
                    return Integer.compare(a.processingTime, b.processingTime);

                //sort by id
                return Integer.compare(a.id, b.id);
            });

            int idx = 0;
            int taskIndex = 0;
            //start the cpu clock with first task time or 0
            int currTime = tasks[taskIndex].enqueueTime > 0 ? tasks[taskIndex].enqueueTime : 0; //1 <= enqueueTimei <= 10^9

            while (idx < taskLength) { // run cpu till all tasks are completed

                //Push all the tasks in minHeap which can be pickup at this cpuTime.
                //Pushing in heap will make them available based on processingTime or idx.
                while (taskIndex < taskLength && tasks[taskIndex].enqueueTime <= currTime) {
                    minHeap.offer(tasks[taskIndex++]);
                }

                //do we have any task to peak  ?
                if (minHeap.isEmpty()) {
                    //no, reset the cpu time to first enqueueTime
                    currTime = tasks[taskIndex].enqueueTime;
                } else {
                    //we have
                    Task t = minHeap.poll();
                    currTime += t.processingTime;
                    order[idx++] = t.id;
                }
            }
            return order;

        }
    }
}
