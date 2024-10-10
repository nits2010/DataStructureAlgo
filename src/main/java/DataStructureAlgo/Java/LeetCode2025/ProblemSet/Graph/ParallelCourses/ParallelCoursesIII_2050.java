package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Graph.ParallelCourses;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 9/22/2024
 * Question Category: 2050. Parallel Courses III
 * Description: https://leetcode.com/problems/parallel-courses-iii/description/
 * You are given an integer n, which indicates that there are n courses labeled from 1 to n. You are also given a 2D integer array relations where relations[j] = [prevCoursej, nextCoursej] denotes that course prevCoursej has to be completed before course nextCoursej (prerequisite relationship). Furthermore, you are given a 0-indexed integer array time where time[i] denotes how many months it takes to complete the (i+1)th course.
 * <p>
 * You must find the minimum number of months needed to complete all the courses following these rules:
 * <p>
 * You may start taking a course at any time if the prerequisites are met.
 * Any number of courses can be taken at the same time.
 * Return the minimum number of months needed to complete all the courses.
 * <p>
 * Note: The test cases are generated such that it is possible to complete every course (i.e., the graph is a directed acyclic graph).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: n = 3, relations = [[1,3],[2,3]], time = [3,2,5]
 * Output: 8
 * Explanation: The figure above represents the given graph and the time required to complete each course.
 * We start course 1 and course 2 simultaneously at month 0.
 * Course 1 takes 3 months and course 2 takes 2 months to complete respectively.
 * Thus, the earliest time we can start course 3 is at month 3, and the total time required is 3 + 5 = 8 months.
 * Example 2:
 * <p>
 * <p>
 * Input: n = 5, relations = [[1,5],[2,5],[3,5],[3,4],[4,5]], time = [1,2,3,4,5]
 * Output: 12
 * Explanation: The figure above represents the given graph and the time required to complete each course.
 * You can start courses 1, 2, and 3 at month 0.
 * You can complete them after 1, 2, and 3 months respectively.
 * Course 4 can be taken only after course 3 is completed, i.e., after 3 months. It is completed after 3 + 4 = 7 months.
 * Course 5 can be taken only after courses 1, 2, 3, and 4 have been completed, i.e., after max(1,2,3,7) = 7 months.
 * Thus, the minimum time needed to complete all the courses is 7 + 5 = 12 months.
 * <p>
 * input; n=9, relations = [[2,7],[2,6],[3,6],[4,6],[7,6],[2,1],[3,1],[4,1],[6,1],[7,1],[3,8],[5,8],[7,8],[1,9],[2,9],[6,9],[7,9]], time = [9,5,9,5,8,7,7,8,4]
 * output: 32
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 5 * 104
 * 0 <= relations.length <= min(n * (n - 1) / 2, 5 * 104)
 * relations[j].length == 2
 * 1 <= prevCoursej, nextCoursej <= n
 * prevCoursej != nextCoursej
 * All the pairs [prevCoursej, nextCoursej] are unique.
 * time.length == n
 * 1 <= time[i] <= 104
 * The given graph is a directed acyclic graph.
 *
 * <p><p>
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link ParallelCoursesI_1136}
 * extension {@link ParallelCoursesI_1136}
 * <p><p>
 * Tags
 * -----
 *
 * @Hard
 * @DynamicProgramming
 * @Array
 * @Graph
 * @TopologicalSort <p><p>
 * Company Tags
 * -----
 * @Pinterest
 * @Uber
 * <p><p>
 * @Editorial https://leetcode.com/problems/parallel-courses-iii/solutions/5836655/in-depth-intuition-derivation-recursive-top-down-bottom-up-bfs-logical-explanation
 */
public class ParallelCoursesIII_2050 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new int[][]{{1, 3}, {2, 3}}, 3, new int[]{3, 2, 5}, 8);
        test &= test(new int[][]{{1, 5}, {2, 5}, {3, 5}, {3, 4}, {4, 5}}, 5, new int[]{1, 2, 3, 4, 5}, 12);
        test &= test(new int[][]{{2, 7}, {2, 6}, {3, 6}, {4, 6}, {7, 6}, {2, 1}, {3, 1}, {4, 1}, {6, 1}, {7, 1}, {3, 8}, {5, 8}, {7, 8}, {1, 9}, {2, 9}, {6, 9}, {7, 9}},
                9, new int[]{9, 5, 9, 5, 8, 7, 7, 8, 4}, 32);
        CommonMethods.printResult(test);
    }

    private static boolean test(int[][] relations, int n, int[] time, int expected) {
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("Input: n = " + n + ", relations = " + CommonMethods.toStringInline(relations) + ", time = " + Arrays.toString(time));
        System.out.println("Expected : " + expected);

        int output;
        boolean finalPass = true;
        boolean pass;

        SolutionRecursive solutionRecursive = new SolutionRecursive();
        output = solutionRecursive.minimumTime(n, relations, time);
        pass = output == expected;
        System.out.println(" Recursive : "+output + " Result : " + (pass ? "PASS" : "Failed"));
        finalPass &= pass;

        SolutionRecursiveTopDownMemoization solutionRecursiveTopDownMemoization = new SolutionRecursiveTopDownMemoization();
        output = solutionRecursiveTopDownMemoization.minimumTime(n, relations, time);
        pass = output == expected;
        System.out.println(" Recursive Top Down : "+output + " Result : " + (pass ? "PASS" : "Failed"));
        finalPass &= pass;

        SolutionRecursiveBottomUpKhans  solutionRecursiveBottomUpKhans = new SolutionRecursiveBottomUpKhans();
        output = solutionRecursiveBottomUpKhans.minimumTime(n, relations, time);
        pass = output == expected;
        System.out.println(" Khans : "+output + " Result : " + (pass ? "PASS" : "Failed"));
        finalPass &= pass;

        return finalPass;
    }


    /**
     * This problem is similar to {@link ParallelCoursesI_1136} The only difference is that, in this problem, we need to find
     * 1. minimum time, as time duration of each course is given
     * 2. and we are not worry about the semester it would take to complete.
     *
     * Now, because we need to care about the time, we need to consider this as a base parameter while choosing an available course and the duration it will take to complete, which is nothing but the maximum one.
     *
     * The most intuitive way would be greedy only,
     * TLE
     */
    static class SolutionRecursive {


        public int minimumTime(int n, int[][] relations, int[] time) {

            if(n == 0)
                return 0;

            //if there is no relation, then we can take all course at once and the max time would be our minimum time required
            if(relations == null || relations.length == 0)
                return Arrays.stream(time).max().getAsInt();

            //build the 0 based graph
            final List<List<Integer>> adjList = graph(n, relations);
            int minTime = 0 ;

            //visit all courses,
            for (int i=0; i<n; i++)
               minTime = Math.max(minTime, dfs(n, adjList, time, i));


            return minTime;
        }

        //DFs to all courses
        private int dfs(int n, List<List<Integer>> adjList, int[] time, int course) {

            //the current course time
            int maxCourseTime = time[course];

            //all the child course
            for(int nextCourse : adjList.get(course)) {
                //if there is a child course, then compute the time of it and then compute the time for current
                maxCourseTime = Math.max(maxCourseTime,
                       time[course] +  dfs(n, adjList, time, nextCourse)  );
            }

            return maxCourseTime;

        }

        private List<List<Integer>> graph(int n, int[][]relations) {
            List<List<Integer>> adjList = new ArrayList<>();
            for (int i=0; i<n; i++)
                adjList.add(new ArrayList<>());

            for (int[] relation : relations) {
                int prevCourse = relation[0] - 1 ;
                int nextCourse = relation[1] - 1 ;
                adjList.get(prevCourse).add(nextCourse);
            }
            return adjList;
        }
    }

    static class SolutionRecursiveTopDownMemoization {


        public int minimumTime(int n, int[][] relations, int[] time) {

            if(n == 0)
                return 0;

            //if there is no relation, then we can take all course at once and the max time would be our minimum time required
            if(relations == null || relations.length == 0)
                return Arrays.stream(time).max().getAsInt();

            final List<List<Integer>> adjList = graph(n, relations);
            //cache
            int []wtTime = new int[n];

            int minTime = 0 ;

            //visit all courses,
            for (int i=0; i<n; i++)
                //if there is a child course, then compute the time of it and then compute the time for current
                minTime = Math.max(minTime, dfs(n, adjList, time, i, wtTime));


            return minTime;
        }

        private int dfs(int n, List<List<Integer>> adjList, int[] time, int course, int []wtTime) {
            //if already computed, then return
            if(wtTime[course]!=0)
                return wtTime[course];

            //the current course time
            int maxCourseTime = time[course];

            //all the child course
            for(int nextCourse : adjList.get(course)) {
                maxCourseTime = Math.max(maxCourseTime,
                        time[course] +  dfs(n, adjList, time, nextCourse, wtTime)  );
            }

            return wtTime[course] = maxCourseTime;

        }

        private List<List<Integer>> graph(int n, int[][]relations) {
            List<List<Integer>> adjList = new ArrayList<>();
            for (int i=0; i<n; i++)
                adjList.add(new ArrayList<>());

            for (int[] relation : relations) {
                int prevCourse = relation[0] - 1 ;
                int nextCourse = relation[1] - 1 ;
                adjList.get(prevCourse).add(nextCourse);
            }
            return adjList;
        }
    }


    static class SolutionRecursiveBottomUpKhans {


        public int minimumTime(int n, int[][] relations, int[] time) {

            if(n == 0)
                return 0;

            //if there is no relation, then we can take all course at once and the max time would be our minimum time required
            if(relations == null || relations.length == 0)
                return Arrays.stream(time).max().getAsInt();

            //hold inDegree of nodes
            int []inDegree = new int[n];
            final List<List<Integer>> adjList = graph(n, relations, inDegree);

            //cache
            int []wtTime =  Arrays.copyOf(time, n);

            //take all the available courses
            Queue<Integer> queue = new LinkedList<>();
            for (int i=0; i<n; i++){
                if(inDegree[i] == 0)
                    queue.offer(i);
            }

            //visit all the available courses
            while (!queue.isEmpty()) {

                //visit all courses, which has 0 indegree
                for (int availableCourse=0; availableCourse<queue.size(); availableCourse++) {
                    int currentCourse = queue.poll();

                    //visit all the next course which is dependent on the current course
                    for(int nextCourse : adjList.get(currentCourse)) {

                        //just like dfs,
                        // the total time for the next course is nothing but the maximum time of its own so far,
                        // or the maximum time of its parent course + time of the current course
                        wtTime[nextCourse] = Math.max(wtTime[nextCourse], wtTime[currentCourse] + time[nextCourse]);

                        //reduce in-degree and enqueue if needed.
                        if(--inDegree[nextCourse] == 0)
                            queue.offer(nextCourse);

                    }
                }
            }


            return Arrays.stream(wtTime).max().getAsInt();
        }



        private List<List<Integer>> graph(int n, int[][]relations,  int []inDegree) {
            List<List<Integer>> adjList = new ArrayList<>();
            for (int i=0; i<n; i++)
                adjList.add(new ArrayList<>());

            for (int[] relation : relations) {
                int prevCourse = relation[0] - 1 ;
                int nextCourse = relation[1] - 1 ;
                adjList.get(prevCourse).add(nextCourse);
                inDegree[nextCourse]++;
            }
            return adjList;
        }
    }

}
