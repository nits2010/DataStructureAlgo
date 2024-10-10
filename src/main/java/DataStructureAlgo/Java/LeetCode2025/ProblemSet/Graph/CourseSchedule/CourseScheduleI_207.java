package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Graph.CourseSchedule;


import DataStructureAlgo.Java.LeetCode.graph.course.schedule.ICourseSchedule;
import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 9/21/2024
 * Question Category: 207. Course Schedule
 * Description: https://leetcode.com/problems/course-schedule/description/?envType=study-plan-v2&envId=top-interview-150
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
 * <p>
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return true if you can finish all courses. Otherwise, return false.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0. So it is possible.
 * Example 2:
 * <p>
 * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= 5000
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * All the pairs prerequisites[i] are unique.
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.graph.course.schedule.CourseSchedule1}
 * Similar {@link}
 * extension {@link DataStructureAlgo.Java.nonleetcode.graph.questions.TopologicalSorts}
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @Depth-FirstSearch
 * @Breadth-FirstSearch
 * @Graph
 * @TopologicalSort <p><p>
 * Company Tags
 * -----
 * @Amazon
 * @Microsoft
 * @Facebook
 * @tiktok
 * @Google
 * @Apple
 * @Bloomberg
 * @Cohesity
 * @eBay
 * @GoldmanSachs
 * @Intuit
 * @LinkedIn
 * @Nutanix
 * @Oracle
 * @Paypal
 * @Salesforce
 * @Uber
 * @Yahoo
 * @Yelp
 * @Zenefits <p></>
 * @Editorial
 */
public class CourseScheduleI_207 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(2, new int[][]{{1, 0}}, true);
        test &= test(2, new int[][]{{1, 0}, {0, 1}}, false);
        test &= test(3, new int[][]{{1, 0}}, true);
        test &= test(3, new int[][]{{1, 0}, {2, 0}}, true);
        test &= test(3, new int[][]{{1, 0}, {2, 0}, {2, 1}}, true);
        test &= test(3, new int[][]{{1, 0}, {2, 0}, {1, 2}}, true);
        test &= test(3, new int[][]{{1, 0}, {0, 2}, {2, 1}}, false);
        CommonMethods.printResult(test);
    }

    private static boolean test(int numCourses, int[][] prerequisites, boolean expected) {
        System.out.println("------------------------------------------------------");
        System.out.println("Input : Courses " + numCourses + " prerequisites: " + CommonMethods.toStringInline(prerequisites) + " expected :" + expected);

        ICourseSchedule solutionDFS = new SolutionDFS();
        boolean actual = solutionDFS.canFinish(numCourses, prerequisites);
        boolean dfsTestResult = actual == expected;
        System.out.println("DFS : " + actual + ", Result: " + (dfsTestResult ? "PASSED" : "FAILED"));

        ICourseSchedule solutionKhansAlgo = new SolutionKhansTopologicalSort();
        actual = solutionKhansAlgo.canFinish(numCourses, prerequisites);
        boolean khanAlgoTestResult = actual == expected;
        System.out.println("Khan Algo : " + actual + ", Result: " + (khanAlgoTestResult ? "PASSED" : "FAILED"));

        ICourseSchedule solutionDFSWithoutGraphBuild = new SolutionDFSWithoutGraphBuild();
        actual = solutionDFSWithoutGraphBuild.canFinish(numCourses, prerequisites);
        boolean dfsWithoutGraphBuildTestResult = actual == expected;
        System.out.println("DFS without graph build: " + actual + ", Result: " + (dfsWithoutGraphBuildTestResult ? "PASSED" : "FAILED"));
        return dfsTestResult && khanAlgoTestResult && dfsWithoutGraphBuildTestResult;


    }

    /**
     * To find, if we can finish the course, then this courses will make a directed graph.
     * Then there cannot be any cycle in the prerequisite graph.
     * Build a directed graph with given prerequisites and apply standard DFS, and if we see the same course again,
     * then it means a cycle exists. To detect, we can do topological sort
     * T/S: O(V + E) / O(V + E):
     * <p>
     * buildGraph function (O(V + E)):
     * detectCycle function (O(V + E)):
     * <p>
     * The outer for loop in detectCycle(List<List<Integer>>, int) runs numCourses times, i.e., O(V), to start cycle detection for every unvisited node.
     * The recursive function detectCycle(List<List<Integer>>, int, int[]) performs a depth-first search (DFS) on each node.
     * DFS will visit each node and edge exactly once, so the time complexity is proportional to the number of vertices and edges, i.e., O(V + E).
     *
     * Tarjan’s Algorithms：
     * https://en.wikipedia.org/wiki/Tarjan%27s_strongly_connected_components_algorithm DFS based，
     *
     * loop through each node of the graph in an arbitrary order，
     * initiating a depth-first search that terminates when it hits any node that has already been visited
     * since the beginning of the topological sort or the node has no outgoing edges (i.e. a leaf node).
     */
    static class SolutionDFS implements ICourseSchedule {
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            if (prerequisites == null || prerequisites.length == 0) return true;

            List<List<Integer>> adjListGraph = buildGraph(numCourses, prerequisites);

            return !detectCycle(adjListGraph, numCourses);
        }

        //O(V + E)
        private List<List<Integer>> buildGraph(int numCourses, int[][] prerequisites) {
            List<List<Integer>> adjListGraph = new ArrayList<>(numCourses);

            for (int i = 0; i < numCourses; i++) {
                adjListGraph.add(i, new ArrayList<>());
            }

            for (int[] prerequisite : prerequisites) {
                int ai = prerequisite[0];
                int bi = prerequisite[1];

                //prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
                //hence it creates a directed edge from ai to bi
                List<Integer> course = adjListGraph.get(ai) == null ? new ArrayList<>() : adjListGraph.get(ai);
                course.add(bi);
                adjListGraph.set(ai, course);
            }

            return adjListGraph;
        }


        private boolean detectCycle(List<List<Integer>> adjListGraph, int numCourses) {

            //to keep track, if we have visited a course or not
            // 0 -> not visited,
            // 1 -> visited
            // 2 -> course completed, without a cycle
            int[] visited = new int[numCourses];

            //if the graph is disconnected, then there will be multiple components; each needs to visit
            //until they are completed
            for (int i = 0; i < numCourses; i++) {
                if (visited[i] != 2 && detectCycle(adjListGraph, i, visited))
                    return true;
            }

            return false;

        }

        //DFS will visit each node and edge exactly once, so the time complexity is proportional to the number of vertices and edges, i.e., O(V + E).
        private boolean detectCycle(List<List<Integer>> adjListGraph, int currentCourse, int[] visited) {

            if (visited[currentCourse] == 1)
                return true;

            if (visited[currentCourse] == 2)
                return false; // course completed without a cycle

            //means visited[currentCourse] == 0, try to take this course
            visited[currentCourse] = 1;

            //try to take all prerequisite courses
            for (Integer prerequisite : adjListGraph.get(currentCourse)) {
                if (detectCycle(adjListGraph, prerequisite, visited))
                    return true;
            }

            //if no cycle has detected, hence we can take this course
            visited[currentCourse] = 2;
            return false;
        }
    }


    /**
     * We can build the graph and do khans topological sort algorithm, if resulted topological sort returns equal number of course then total, then we can do otherwise there is cycle.
     * https://takeuforward.org/data-structure/kahns-algorithm-topological-sort-algorithm-bfs-g-22/
     * <p>
     * {@link DataStructureAlgo.Java.nonleetcode.graph.questions.TopologicalSorts#topologicalSortKhanAlgo(List[], int)}
     * <p>
     * T/S: O(V + E) / O(V + E):
     * <p>
     * buildGraph function (O(V + E)):
     * detectCycle function (O(V + E)):
     */
    static class SolutionKhansTopologicalSort implements ICourseSchedule {

        public boolean canFinish(int numCourses, int[][] prerequisites) {
            int[] inDegree = new int[numCourses];
            List<List<Integer>> adjListGraph = buildGraph(numCourses, prerequisites, inDegree);

            return detectCycle(adjListGraph, numCourses, inDegree);
        }

        private boolean detectCycle(List<List<Integer>> adjListGraph, int numCourses, int[] inDegree) {


            //start with 0 inDegree
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < numCourses; i++) {

                //if inDegree of this node is 0, then there is no required prerequisite for this course, which can independently be taken
                if (inDegree[i] == 0)
                    queue.offer(i);
            }

            //for the remaining courses, visit them one by one from all the independent courses
            int courseTaken = 0;
            while (!queue.isEmpty()) {

                int u = queue.poll();
                courseTaken++; // as this u's course has been taken

                for (Integer prerequisite : adjListGraph.get(u)) {
                    inDegree[prerequisite]--; // as this prerequisite has been taken for this course [ ai, bi ]

                    //if there is no more prerequisite for this course, then we can take it as well
                    if (inDegree[prerequisite] == 0)
                        queue.offer(prerequisite);
                }
            }
            return courseTaken == numCourses;
        }

        //O(V + E)
        private List<List<Integer>> buildGraph(int numCourses, int[][] prerequisites, int[] inDegree) {
            List<List<Integer>> adjListGraph = new ArrayList<>(numCourses);

            for (int i = 0; i < numCourses; i++) {
                adjListGraph.add(i, new ArrayList<>());
            }

            for (int[] prerequisite : prerequisites) {
                int ai = prerequisite[0];
                int bi = prerequisite[1];

                //prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
                //hence it creates a directed edge from ai to bi
                List<Integer> course = adjListGraph.get(ai) == null ? new ArrayList<>() : adjListGraph.get(ai);
                course.add(bi);
                adjListGraph.set(ai, course);

                //calculate the indegree of each prerequisite
                inDegree[bi]++;
            }
            return adjListGraph;
        }

    }

    /**
     * it is the same as the above solution, but without a graph has been built
     */
    static class SolutionDFSWithoutGraphBuild implements ICourseSchedule {

        public boolean canFinish(int numCourses, int[][] prerequisites) {
            int[] inDegree = new int[numCourses];
            int p = prerequisites.length;
            for (int[] prerequisite : prerequisites) {
                int prerequisiteCourse = prerequisite[1];
                inDegree[prerequisiteCourse]++;
            }

            boolean[] visited = new boolean[numCourses];
            boolean courseTaken = true;

            //visit all courses
            while (courseTaken) {
                courseTaken = false;
                for (int i = 0; i < p; i++) {
                    // prerequisites[i] = [ai, bi]
                    int[] prerequisite = prerequisites[i];

                    //if this course hasn't been taken yet, try to take it
                    if (!visited[i]) {
                        //if there is no prerequisite for this course, then we can take it
                        if (inDegree[prerequisite[0]] == 0) {
                            visited[i] = true;

                            //prerequisites[i] = [ai, bi] indicates that you must take course 'bi' first if you want to take course 'ai'.
                            //since we can take the course 'ai', hence the indegree of bi is reduced.
                            inDegree[prerequisite[1]]--;

                            courseTaken = true;

                        }
                    }
                }
            }

            //if any of the course has a inDegree then it is not possible to take all the courses
            for (int i = 0; i < numCourses; i++) {
                if (inDegree[i] != 0)
                    return false;
            }
            return true;

        }
    }
}
