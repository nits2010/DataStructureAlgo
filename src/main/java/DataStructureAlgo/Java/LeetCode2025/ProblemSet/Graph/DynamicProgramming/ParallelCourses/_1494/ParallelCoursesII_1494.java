package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Graph.DynamicProgramming.ParallelCourses._1494;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 9/22/2024
 * Question Category: 1494. Parallel Courses II
 * Description:https://leetcode.com/problems/parallel-courses-ii/description/
 * <p>
 * You are given an integer n, which indicates that there are n courses labeled from 1 to n.
 * You are also given an array relations where relations[i] = [prevCoursei, nextCoursei],
 * representing a prerequisite relationship between course prevCoursei and course nextCoursei: course prevCoursei has to be taken before course nextCoursei.
 * Also, you are given the integer k.
 * <p>
 * In one semester, you can take at most k courses as long as you have taken all the prerequisites in the previous semesters for the courses you are taking.
 * <p>
 * Return the minimum number of semesters needed to take all courses. The testcases will be generated such that it is possible to take every course.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: n = 4, relations = [[2,1],[3,1],[1,4]], k = 2
 * Output: 3
 * Explanation: The figure above represents the given graph.
 * In the first semester, you can take courses 2 and 3.
 * In the second semester, you can take course 1.
 * In the third semester, you can take course 4.
 * Example 2:
 * <p>
 * <p>
 * Input: n = 5, relations = [[2,1],[3,1],[4,1],[1,5]], k = 2
 * Output: 4
 * Explanation: The figure above represents the given graph.
 * In the first semester, you can only take courses 2 and 3 since you cannot take more than two per semester.
 * In the second semester, you can take course 4.
 * In the third semester, you can take course 1.
 * In the fourth semester, you can take course 5.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 15
 * 1 <= k <= n
 * 0 <= relations.length <= n * (n-1) / 2
 * relations[i].length == 2
 * 1 <= prevCoursei, nextCoursei <= n
 * prevCoursei != nextCoursei
 * All the pairs [prevCoursei, nextCoursei] are unique.
 * The given graph is a directed acyclic graph.
 *
 * <p><p>
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @Hard
 * @DynamicProgramming
 * @BitManipulation
 * @Graph
 * @Bitmask <p><p>
 * Company Tags
 * -----
 * @Google
 * @Pinterest
 * <p><p>
 *
 *
 * @Editorial chatgpt: https://chatgpt.com/share/66f1c46c-942c-8011-913d-800b75925028
 * https://leetcode.com/problems/parallel-courses-ii/solutions/1373540/detailed-explanations-diagrams-annotated-code
 *
 * ---------
 * chatgpt
 * -----
 * Approach to Solve the Problem
 * This is a variant of a topological sorting problem combined with bitmask dynamic programming (DP). The key challenge is determining the optimal sequence of taking courses while respecting
 * the prerequisite constraints and the limit k on how many courses can be taken in each semester.
 *
 * Steps:
 * Graph Representation:
 *
 * You can represent the courses and their prerequisites as a directed graph. A course points to its dependent courses.
 * A node will represent a course, and an edge from node u to node v will mean that course u is a prerequisite for course v.
 * Bitmasking:
 *
 * Use a bitmask to represent which courses have been completed. For n courses, a bitmask of size n is sufficient. Each bit in the bitmask represents whether a course is completed (1) or not (0).
 * Topological Sort with Bitmask DP:
 *
 * Use a dynamic programming approach where the state is the bitmask of completed courses.
 * In each DP state, you consider which subset of available courses you can take in the current semester (limited by k courses per semester).
 * Transition from one state to another is done by completing courses whose prerequisites have already been satisfied.
 * Base Case and State Transition:
 *
 * The base case is when no course is taken (mask = 0), and the final state is when all courses are taken (mask = (1 << n) - 1).
 * From each state, check which courses can be taken (those whose prerequisites are already satisfied in the current bitmask). Try all subsets of those courses of size <= k and update the DP accordingly.
 */
public class ParallelCoursesII_1494 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new int[][]{{12,8},{2,4},{3,7},{6,8},{11,8},{9,4},{9,7},{12,4},{11,4},{6,4},{1,4},{10,7},{10,4},{1,7},{1,8},{2,7},{8,4},{10,8},{12,7},{5,4},
                        {3,4},{11,7},{7,4},{13,4},{9,8},{13,8}},
                13, 9, 3);
        test &= test(new int[][]{}, 11, 2, 6);
        test &= test(new int[][]{}, 11, 0, -1);
        test &= test(new int[][]{{2, 1}, {3, 1}, {1, 4}}, 4, 2, 3);
        test &= test(new int[][]{{2, 1}, {3, 1}, {4, 1}, {1, 5}}, 5, 2, 4);
        CommonMethods.printAllTestOutCome(test);
    }

    private static boolean test(int[][] relations, int n, int k, int expected) {
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("Relations :" + CommonMethods.toStringInline(relations) + " n : " + n + " k : " + k + " expected : " + expected);


        int output = 0;
        boolean finalTest = true;


//          this code will not complete and TLE for long test cases. So commenting it.
//        DFS_BitMasking.SolutionRecursive solutionRecursive = new DFS_BitMasking.SolutionRecursive();
//        output = solutionRecursive.minNumberOfSemesters(n, relations, k);
//        boolean recursiveTest = output == expected;
//        System.out.println("Recursive = " + output + " recursiveTest = " + (recursiveTest ? "PASS" : "FAIL"));
//        finalTest &= recursiveTest;

        DFS_BitMasking.SolutionDPTopDown solutionDPTopDown = new DFS_BitMasking.SolutionDPTopDown();
        output = solutionDPTopDown.minNumberOfSemesters(n, relations, k);
        boolean dpTopDownTest = output == expected;
        System.out.println("DP Top Down = " + output + " dpTopDownTest = " + (dpTopDownTest ? "PASS" : "FAIL"));
        finalTest &= dpTopDownTest;

        DFS_BitMasking.SolutionDPBottomUp solutionDPBottomUp = new DFS_BitMasking.SolutionDPBottomUp();
        output = solutionDPBottomUp.minNumberOfSemesters(n, relations, k);
        boolean dpBottomUpTest = output == expected;
        System.out.println("DP Bottom Up = " + output + " dpBottomUpTest = " + (dpTopDownTest ? "PASS" : "FAIL"));
        finalTest &= dpBottomUpTest;

        BFS_BitMasking.SolutionBFS solutionBFS = new BFS_BitMasking.SolutionBFS();
        output = solutionBFS.minNumberOfSemesters(n, relations, k);
        boolean bfsTest = output == expected;
        System.out.println("BFS using Queue = " + output + " bfsTest = " + (bfsTest ? "PASS" : "FAIL"));
        finalTest &= bfsTest;

        DFS_Backtracking.Backtracking backtracking = new DFS_Backtracking.Backtracking();
        output = backtracking.minNumberOfSemesters(n, relations, k);
        boolean backtrackingTest = output == expected;
        System.out.println("Backtracking = " + output + " backtrackingTest = " + (backtrackingTest ? "PASS" : "FAIL"));
        finalTest &= backtrackingTest;

        return finalTest;

    }

    static class DFS_Backtracking {

        static class Backtracking {

            private int minSemesters = Integer.MAX_VALUE;
            public int minNumberOfSemesters(int n, int[][] relations, int k) {
                if (n == 0 || k == 0)
                    return -1;

                if (relations.length == 0)
                    return (int) Math.ceil((double) n / k);

                List<List<Integer>> adjList = graph(n, relations);

                dfs_backtracking(n, k , adjList, new HashSet<>(), 0);

                return minSemesters;
            }

            private void dfs_backtracking(int n, int k, List<List<Integer>> adjList, Set<Integer> coursesTaken, int semester){
                if(coursesTaken.size() == n){
                    minSemesters = Math.min(minSemesters, semester);
                    return;
                }

                //semester + estimated number of semesters left exceeds minSemesters
                if (semester + (int)Math.ceil((n - coursesTaken.size()) / (double)k) >= minSemesters) {
                    return;
                }

                List<Integer> available = availableCourses(adjList, coursesTaken);

                //if we have only <= k courses available, then take them all
                if(available.size() <= k){

                    coursesTaken.addAll(available);

                    dfs_backtracking(n, k, adjList, coursesTaken, semester + 1);

                    //backtrack
                    coursesTaken.removeAll(available);

                }else{

                    //if there are more than k courses available, then we need to choose all of the combinations
                    List<List<Integer>> combinations = combinations(available, k);

                    for(List<Integer> combination : combinations){
                        coursesTaken.addAll(combination);

                        dfs_backtracking(n, k, adjList, coursesTaken, semester + 1);

                        //backtrack
                        coursesTaken.removeAll(combination);
                    }
                }

            }

            private  List<Integer> availableCourses(List<List<Integer>> adjList, Set<Integer> coursesTaken) {
                List<Integer> available = new ArrayList<>();
                for (int i = 0; i< adjList.size(); i++){
                    boolean isAvailable = !coursesTaken.contains(i); // if this is not taken earlier
                    isAvailable = isAvailable && coursesTaken.containsAll(adjList.get(i)); // if all the prerequisites are taken

                    if(isAvailable)
                        available.add(i);
                }
                return available;
            }

            /**
             * {@link DataStructureAlgo.Java.nonleetcode.Combinations}
             * @param available
             * @param k
             * @return
             */
            private  List<List<Integer>> combinations(List<Integer> available, int k) {
                List<List<Integer>> allCombinations = new ArrayList<>();
                combinations(available, k, new ArrayList<>(), allCombinations, 0);
                return allCombinations;
            }


            private  void combinations(List<Integer> available, int k, List<Integer> currentCombination, List<List<Integer>> allCombinations, int pickFrom) {
                if ( k == currentCombination.size()) {
                    allCombinations.add(new ArrayList<>(currentCombination));
                    return;
                }

                for (int i = pickFrom; i<available.size(); i++){
                    currentCombination.add(available.get(i));
                    combinations(available, k, currentCombination, allCombinations, i+1);
                    currentCombination.remove(currentCombination.size()-1);
                }
            }

            private List<List<Integer>> graph(int n, int [][]relations){
                List<List<Integer>> adjList = new ArrayList<>(n+1);

                for (int i = 0; i < n; i++) {
                    adjList.add(new ArrayList<>());
                }

                for(int []relation : relations) {
                    int prevCourse = relation[0] - 1; //courses labeled from 1 to n
                    int nextCourse = relation[1] - 1 ; //courses labeled from 1 to n
                    adjList.get(nextCourse).add(prevCourse); // nextCourse can only be taken when prevCourse is completed
                }
                return adjList;
            }
        }

    }

    static class DFS_BitMasking {
        static class SolutionRecursive {

            public int minNumberOfSemesters(int n, int[][] relations, int k) {
                if (n == 0 || k == 0)
                    return -1;

                if (relations.length == 0)
                    return (int) Math.ceil((double) n / k);

                //build graph and get indegree counted as well
                final int[] prerequisites = buildPrerequisites(n, relations);

                return dfs(n, k, prerequisites, 0);

            }

            private int[] buildPrerequisites(int n, int[][] relations) {
                int[] prerequisites = new int[n];

                for (int[] relation : relations) {
                    int prerequisiteCourse = relation[0] - 1;
                    int course = relation[1] - 1;

                    //1 << prerequisiteCourse will make a specific course bit set
                    // Doing '|' will make sure that prerequisites of current 'course' will be updated with this prerequisiteCourse
                    prerequisites[course] |= 1 << prerequisiteCourse;
                }

                return prerequisites;
            }


            private int dfs(int n, int k, int[] prerequisites, int mask) {

                //termination condition when all courses are taken, hence no more semester needed.
                if (mask == (1 << n) - 1)
                    return 0;

                //get all the available courses as of now.
                int available = 0;

                for (int i = 0; i < n; i++) {
                    boolean isAvailable = (mask & (1 << i)) == 0;
                    // this will only be true, mask has the same bit set (course taken) as prerequisite[i], since prerequisite[i] hold set bit all the courses that i'th course has
                    //dependency
                    isAvailable = isAvailable && ((mask & prerequisites[i]) == prerequisites[i]);

                    if (isAvailable)
                        available |= 1 << i; //if this course is available, add it in available mask, hence we can take it in next semester

                }

                //now we need to find all the possible combination of this 'available' courses such that we take <='K'
                int minSemesters = Integer.MAX_VALUE;

                // ( comb - 1 ) will remove the last set bit and doing ( comb - 1 ) & available will result a combination with available courses
                for (int comb = available; comb > 0; comb = (comb - 1) & available) {
                    if (Integer.bitCount(comb) <= k) { // if set bit count is <=k then take it otherwise ignore it
                        minSemesters = Math.min(minSemesters,
                                1 + dfs(n, k, prerequisites, mask | comb));

                    }
                }
                return minSemesters;


            }


        }

        static class SolutionDPTopDown {

            public int minNumberOfSemesters(int n, int[][] relations, int k) {
                if (n == 0 || k == 0)
                    return -1;

                if (relations.length == 0)
                    return (int) Math.ceil((double) n / k);

                //build graph and get indegree counted as well
                final int[] prerequisites = buildPrerequisites(n, relations);
                int[] dp = new int[1 << n]; // there will be 1<<n states ( 1<<n = 2^n )
                Arrays.fill(dp, Integer.MAX_VALUE); // means, courses has not computed yet.

                return dfs(n, k, prerequisites, 0, dp);

            }

            private int[] buildPrerequisites(int n, int[][] relations) {
                int[] prerequisites = new int[n];

                for (int[] relation : relations) {
                    int prerequisiteCourse = relation[0] - 1;
                    int course = relation[1] - 1;

                    //1 << prerequisiteCourse will make a specific course bit set
                    // Doing '|' will make sure that prerequisites of current 'course' will be updated with this prerequisiteCourse
                    prerequisites[course] |= 1 << prerequisiteCourse;
                }

                return prerequisites;
            }


            private int dfs(int n, int k, int[] prerequisites, int mask, int[] dp) {

                //termination condition when all courses are taken, hence no more semester needed.
                if (mask == (1 << n) - 1)
                    return 0;

                if (dp[mask] != Integer.MAX_VALUE)
                    return dp[mask];

                //get all the available courses as of now.
                int available = 0;

                for (int i = 0; i < n; i++) {
                    boolean isAvailable = (mask & (1 << i)) == 0;
                    // this will only be true, mask has the same bit set (course taken) as prerequisite[i],
                    // since prerequisite[i] hold set bit all the courses that i'th course has dependency
                    isAvailable = isAvailable && ((mask & prerequisites[i]) == prerequisites[i]);

                    if (isAvailable)
                        available |= 1 << i; //if this course is available, add it in available mask, hence we can take it in next semester

                }

                //now we need to find all the possible combination of this 'available' courses such that we take <='K'
                int minSemesters = Integer.MAX_VALUE;

                // ( comb - 1 ) will remove the last set bit and doing ( comb - 1 ) & available will result a combination with available courses
                for (int comb = available; comb > 0; comb = (comb - 1) & available) {
                    if (Integer.bitCount(comb) <= k) { // if a set count a bit is <=k then take it otherwise ignore it
                        minSemesters = Math.min(minSemesters,
                                1 + dfs(n, k, prerequisites, mask | comb, dp));

                    }
                }

                dp[mask] = minSemesters;
                return minSemesters;


            }


        }

        static class SolutionDPBottomUp {

            public int minNumberOfSemesters(int n, int[][] relations, int k) {
                if (n == 0 || k == 0)
                    return -1;

                if (relations.length == 0)
                    return (int) Math.ceil((double) n / k);

                //build graph and get indegree counted as well
                final int[] prerequisites = buildPrerequisites(n, relations);
                int[] dp = new int[1 << n]; // there will be 1<<n states ( 1<<n = 2^n )
                Arrays.fill(dp, Integer.MAX_VALUE); // means, courses has not computed yet.
                dp[0] = 0; // starting with a base case, no course has been taken so far.

                return bottomUp(n, k, prerequisites, dp);

            }

            private int[] buildPrerequisites(int n, int[][] relations) {
                int[] prerequisites = new int[n];

                for (int[] relation : relations) {
                    int prerequisiteCourse = relation[0] - 1;
                    int course = relation[1] - 1;

                    //1 << prerequisiteCourse will make a specific course bit set
                    // Doing '|' will make sure that prerequisites of current 'course' will be updated with this prerequisiteCourse
                    prerequisites[course] |= 1 << prerequisiteCourse;
                }

                return prerequisites;
            }


            private int bottomUp(int n, int k, int[] prerequisites, int[] dp) {

                for (int mask = 0; mask < (1 << n); mask++) {
                    if (dp[mask] == Integer.MAX_VALUE)
                        continue;

                    //get all the available courses as of now.
                    int available = 0;

                    for (int i = 0; i < n; i++) {
                        boolean isAvailable = (mask & (1 << i)) == 0;
                        // this will only be true, mask has the same bit set (course taken) as prerequisite[i], since prerequisite[i] hold set bit all the courses that i'th course has
                        //dependency
                        isAvailable = isAvailable && ((mask & prerequisites[i]) == prerequisites[i]);

                        if (isAvailable)
                            available |= 1 << i; //if this course is available, add it in available mask, hence we can take it in next semester

                    }

                    //now we need to find all the possible combination of this 'available' courses such that we take <='K'

                    // ( comb - 1 ) will remove the last set bit and doing ( comb - 1 ) & available will result a combination with available courses
                    for (int comb = available; comb > 0; comb = (comb - 1) & available) {
                        if (Integer.bitCount(comb) <= k) { // if a set count a bit is <=k then take it otherwise ignore it
                            dp[mask | comb] = Math.min(dp[mask | comb], 1 + dp[mask]);
                        }
                    }
                }
                return dp[(1 << n) - 1];


            }


        }
    }

    static class BFS_BitMasking {

        static class SolutionBFS {
            public int minNumberOfSemesters(int n, int[][] relations, int k) {
                if (n == 0 || k == 0)
                    return -1;

                if (relations.length == 0)
                    return (int) Math.ceil((double) n / k);

                //build graph and get indegree counted as well
                final int[] prerequisites = buildPrerequisites(n, relations);

                Queue<int[]> queue = new LinkedList<>();
                queue.offer(new int[]{0, 0}); // 0 is the mask, 0 is the semester count

                int[] dp = new int[1 << n];
                Arrays.fill(dp, Integer.MAX_VALUE); // means, courses has not computed yet.
                dp[0] = 0; // starting with a base case, no course has been taken so far.

                while (!queue.isEmpty()) {
                    int[] curr = queue.poll();
                    int mask = curr[0];
                    int semesters = curr[1];

                    //termination condition when all courses are taken, hence no more semester needed.
                    if (mask == (1 << n) - 1)
                        return semesters;

                    int available = 0;

                    for (int i = 0; i < n; i++) {

                        boolean isAvailable = (mask & (1 << i)) == 0;
                        isAvailable = isAvailable && ((mask & prerequisites[i]) == prerequisites[i]);

                        if (isAvailable)
                            available |= 1 << i; //if this course is available, add it in available mask, hence we can take it in next semester

                    }

                    for (int comb = available; comb > 0; comb = ((comb - 1) & available)) {

                        if (Integer.bitCount(comb) <= k) {

                            int newMask = mask | comb;

                            if (dp[newMask] > semesters + 1) {
                                dp[newMask] = semesters + 1;
                                queue.offer(new int[]{newMask, semesters + 1});
                            }
                        }
                    }
                }

                return -1;
            }

            private int[] buildPrerequisites(int n, int[][] relations) {
                int[] prerequisites = new int[n];

                for (int[] relation : relations) {
                    int prerequisiteCourse = relation[0] - 1;
                    int course = relation[1] - 1;

                    //1 << prerequisiteCourse will make a specific course bit set
                    // Doing '|' will make sure that prerequisites of current 'course' will be updated with this prerequisiteCourse
                    prerequisites[course] |= 1 << prerequisiteCourse;
                }

                return prerequisites;
            }
        }
    }


}
