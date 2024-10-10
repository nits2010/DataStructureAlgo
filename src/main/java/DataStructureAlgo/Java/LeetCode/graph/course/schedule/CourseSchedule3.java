package DataStructureAlgo.Java.LeetCode.graph.course.schedule;

import DataStructureAlgo.Java.helpers.CommonMethods;
import  DataStructureAlgo.Java.nonleetcode.Knapsack;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-21
 * Description: https://leetcode.com/problems/course-schedule-iii/
 * <p>
 * There are n different online courses numbered from 1 to n. Each course has some duration(course length) t and closed on dth day.
 * A course should be taken continuously for t days and must be finished before or on the dth day. You will start at the 1st day.
 * <p>
 * Given n online courses represented by pairs (t,d), your task is to find the maximal number of courses that can be taken.
 * <p>
 * Example:
 * <p>
 * Input: [[100, 200], [200, 1300], [1000, 1250], [2000, 3200]]
 * Output: 3
 * Explanation:
 * There're totally 4 courses, but you can take 3 courses at most:
 * First, take the 1st course, it costs 100 days so you will finish it on the 100th day, and ready to take the next course on the 101st day.
 * Second, take the 3rd course, it costs 1000 days so you will finish it on the 1100th day, and ready to take the next course on the 1101st day.
 * Third, take the 2nd course, it costs 200 days so you will finish it on the 1300th day.
 * The 4th course cannot be taken now, since you will finish it on the 3300th day, which exceeds the closed date.
 * <p>
 * <p>
 * Note:
 * <p>
 * The integer 1 <= d, t, n <= 10,000.
 * You can't take two courses simultaneously.
 * <p>
 * Explanation: https://leetcode.com/articles/course-schedule-iii/
 * https://leetcode.com/problems/course-schedule-iii/discuss/363735/Recursive-or-DP-Top-Down-or-Dp-Bottom-Up-or-Greedy-or-Greedy-Optimised
 */
public class CourseSchedule3 {

    public static void main(String[] args) {
        test(new int[][]{{100, 200}, {200, 1300}, {1000, 1250}, {2000, 3200}}, 3);
        test(new int[][]{{100, 200}, {50, 200}, {15, 200}, {1000, 200}}, 3);
        test(new int[][]{{100, 200}, {50, 200}, {15, 200}, {1000, 2000}}, 4);
    }

    private static void test(int[][] courses, int expected) {
        System.out.println("\n Input :" + CommonMethods.toString(courses));

        ICourseSchedule recursive = new CourseSchedule3Recursive();
        ICourseSchedule dpTopDown = new CourseSchedule3DPTopDown();
        CourseSchedule3DPBottomUp bottomUp = new CourseSchedule3DPBottomUp();
        CourseScheduleGreedy greedy = new CourseScheduleGreedy();
        CourseScheduleGreedyPriorityQueue priorityQueue = new CourseScheduleGreedyPriorityQueue();

        System.out.println("Recursive - Obtained: " + recursive.scheduleCourse(courses) + " expected :" + expected);
        System.out.println("DP Top down - Obtained: " + dpTopDown.scheduleCourse(courses) + " expected :" + expected);
        System.out.println("DP Bottom Up - Obtained: " + bottomUp.scheduleCourse(courses) + " expected :" + expected);
        System.out.println("Greedy - Obtained: " + greedy.scheduleCourse(courses) + " expected :" + expected);
        System.out.println("Greedy-priorityQueue - Obtained: " + priorityQueue.scheduleCourse(courses) + " expected :" + expected);
    }


}

/**
 * We can think this problem as famous knapsack 0/1 problem.
 * Translation:
 * 1. Course length (t) -> Bag size
 * 2. Max course number:  -> Value
 * <p>
 * {@link Knapsack} #Knapsack0Or1#Recursive
 * <p>
 * <p>
 * Algorithm:
 * At any moement we have following choices
 * 1. Either we take this course
 * 2. Or we don't take.
 *
 *
 * <p>
 * Since we need to maximize the number of course taken, we need to pick the least day possible course first. Hence we need to sort the data based on closed day. dth
 * <p>
 * O(2^n)
 */

class CourseSchedule3Recursive implements ICourseSchedule {

    @Override
    public int scheduleCourse(int[][] courses) {
        if (courses == null || courses.length == 0)
            return 0;

        Arrays.sort(courses, Comparator.comparingInt(c -> c[1]));

        return scheduleCourse(courses, 0, 0);
    }

    private int scheduleCourse(int[][] courses, int i, int time) {
        /**
         * Has taken all course
         */
        if (i == courses.length)
            return 0;

        int[] cur = courses[i];
        int requireDays = cur[0];
        int endDays = cur[1];

        //Take this course
        int include = 0, exclude = 0;

        if (time + requireDays <= endDays)
            include = 1 + scheduleCourse(courses, i + 1, time + requireDays);

        exclude = scheduleCourse(courses, i + 1, time);

        return Math.max(include, exclude);
    }
}


/**
 * Add memoization on above code
 * O(n^2)/O(n*MaxDays) where n is the size of courses
 */
class CourseSchedule3DPTopDown implements ICourseSchedule {

    @Override
    public int scheduleCourse(int[][] courses) {
        if (courses == null || courses.length == 0)
            return 0;

        Arrays.sort(courses, Comparator.comparingInt(c -> c[1]));
        int n = courses.length;
        int cache[][] = new int[n + 1][courses[n - 1][1] + 1];

        return scheduleCourse(courses, 0, 0, cache);
    }

    private int scheduleCourse(int[][] courses, int i, int time, int cache[][]) {
        /**
         * Has taken all course
         */
        if (i == courses.length)
            return 0;

        if (cache[i][time] != 0)
            return cache[i][time];

        int[] cur = courses[i];
        int requireDays = cur[0];
        int endDays = cur[1];

        //Take this course
        int include = 0, exclude = 0;

        if (time + requireDays <= endDays)
            include = 1 + scheduleCourse(courses, i + 1, time + requireDays, cache);

        exclude = scheduleCourse(courses, i + 1, time, cache);

        return cache[i][time] = Math.max(include, exclude);
    }
}


/**
 * Add memoization on above code
 * O(n^2)/O(n*MaxDays) where n is the size of courses
 * <p>
 * dp[i][j] is the maximum number of course can be take till including i'th course of time j
 * <p>
 * dp[i][j] = Max ( a, b )
 * <p>
 * a-> Don't take this course, if you don't take then you left with
 * *****  A) maximum number of course can be taken without this course of time j : dp[i-1][j]
 * *******B) maximum number of course can be take including this course but less time : or dp[i][j-1]
 * <p>
 * b-> Take this course;
 * To take this course, the time limit should not exceed with current course days
 * if(j >= courese[i][0] && j<= course[i][1] )
 */
class CourseSchedule3DPBottomUp implements ICourseSchedule {

    @Override
    public int scheduleCourse(int[][] courses) {
        if (courses == null || courses.length == 0)
            return 0;

        Arrays.sort(courses, Comparator.comparingInt(c -> c[1]));
        int n = courses.length;
        int dp[][] = new int[n + 1][courses[n - 1][1] + 1];


        for (int c = 0; c <= n; c++) {

            for (int d = 0; d <= courses[n - 1][1]; d++) {

                if (c == 0 || d == 0)
                    dp[c][d] = 0;
                else {

                    dp[c][d] = Math.max(dp[c - 1][d], dp[c][d - 1]);

                    if (d >= courses[c - 1][0] && d <= courses[c - 1][1])
                        dp[c][d] = Math.max(dp[c][d], 1 + dp[c - 1][d - courses[c - 1][0]]);
                }
            }
        }


        return dp[n][courses[n - 1][1]];

    }


}


/**
 * We can apply greedy approach here, Once we sort the data based on end days, we can pick the course till we can pick
 * once we can't pick, then we need to see does this give best pick{by taking this can we include more courses} by replacing previous picks
 * <p>
 * O(n^2) / O(n)
 */
class CourseScheduleGreedy implements ICourseSchedule {

    @Override
    public int scheduleCourse(int[][] courses) {
        if (courses == null || courses.length == 0)
            return 0;

        Arrays.sort(courses, Comparator.comparingInt(c -> c[1]));
        int n = courses.length;

        int count = 0, time = 0;
        List<Integer> courseList = new ArrayList<>();
        for (int i = 0; i < n; i++) {

            /**
             * Can we pick this course ?
             */
            if (time + courses[i][0] <= courses[i][1]) {
                time = time + courses[i][0];
                count++;
                courseList.add(courses[i][0]);
            } else {
                //Find a course by which we can replace this course. For that j'th course duration > current duration. By doing this,
                // we can make more days available as duration(j) - duration(i) will be +ve as duration(j) > duration(i)
                //The more duration(j) is bigger than duration(i), more days can be added.
                int best = courseList.size() - 1;

                for (int j = 0; j < courseList.size(); j++) {
                    if (courseList.get(j) > courseList.get(best)) {
                        best = j;
                    }
                }

                if (courseList.get(best) > courses[i][0]) {
                    //remove that course duration
                    time = time - courseList.get(best);

                    //add current course duration
                    time = time + courses[i][0];

                    /**
                     * Update this course in-place.
                     */
                    courseList.set(best, courses[i][0]);


                }
            }

        }
        return count;


    }

}

/**
 * The pain point of the above algorithm is to find the best course to replace.
 * For replacing, we need to find the course which has maximum duration. If we somehow can find this duration fast, then we can reduce this time.
 * <p>
 * Which means, if we can keep our course duration sorted, we can do binary search on it.
 * <p>
 * One way is to make sure whenever we insert course duration in our list, we always place it to a sorted manner only.
 * <p>
 * Or
 * we can use Max-heap that will do this for use, and can give us max duration in O(1) time.
 * <p>
 * Solution: Replace the array list with Priority Queue ( Max heap )
 * <p>
 * <p>
 * Then we keep track of a variable cnt, means the days we spent on courses.
 * <p>
 * And we for loop the sorted list. Whenever we come up with a new course, we pick one of the following 2 actions
 * <p>
 * If cnt + courseLength <= courseDeadline, we are happy to take the course
 * If 1's condition does not meet, we cannot take the course. However, we should not just stop here. We should think:
 * Did we make a silly decision before? Maybe the current course's length is pretty short, but the deadline is late, and we took some course with earlier deadline but longer course length.
 * For example, we have course schedule: [[3,3], [2,4], [2,4]], if we take the [3,3] course, we can not take the two [2,4] course any more. And obviously, taking the [3,3] course is a
 * silly decision. So we do the follwing: If we previously took a course with length longer than the current course, we withdraw that course and take this one. (Well, "withdraw"
 * means cnt -= withDrawnCourseLength)
 * Now the question to ask is: how to find out the longest course we took before? PriorityQueue, of course!
 * O(n*logn) / O(n)
 */
class CourseScheduleGreedyPriorityQueue implements ICourseSchedule {

    @Override
    public int scheduleCourse(int[][] courses) {
        if (courses == null || courses.length == 0)
            return 0;

        Arrays.sort(courses, Comparator.comparingInt(c -> c[1]));
        int time = 0;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);


        for (int[] course : courses) {
            /**
             * Can we pick this course ?
             */
            if (time + course[0] <= course[1]) {
                time = time + course[0];
                maxHeap.offer(course[0]);

            } else if (!maxHeap.isEmpty() && maxHeap.peek() > course[0]) {
                //Find a course by which we can replace this course. For that j'th course duration > current duration. By doing this
                // we can make more days available as duration(j) - duration(i) will be +ve as duration(j) > duration(i)
                //The more duration(j) is bigger than duration(i), more days can be added.

                int maxDuration = maxHeap.poll();

                time = time - maxDuration + course[0];

                maxHeap.offer(course[0]);


            }

        }

        return maxHeap.size();

    }
}