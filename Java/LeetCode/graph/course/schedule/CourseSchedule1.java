package Java.LeetCode.graph.course.schedule;

import Java.HelpersToPrint.Printer;
import Java.graph.graph.types.DirectedGraph;
import Java.graph.questions.AlienLanguageOrder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-21
 * Description: https://leetcode.com/problems/course-schedule/
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 * <p>
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 * <p>
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 * <p>
 * Example 1:
 * <p>
 * Input: 2, [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0. So it is possible.
 * Example 2:
 * <p>
 * Input: 2, [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0, and to take course 0 you should
 * also have finished course 1. So it is impossible.
 * Note:
 * <p>
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 * You may assume that there are no duplicate edges in the input prerequisites.
 * <p>
 * {@link AlienLanguageOrder}
 */
public class CourseSchedule1 {


    public static void main(String[] args) {
        test(2, new int[][]{{1, 0}}, true);
        test(3, new int[][]{{1, 0}, {0, 1}}, false);
        test(3, new int[][]{{1, 0}}, true);
        test(3, new int[][]{{1, 0}, {2, 0}}, true);
        test(3, new int[][]{{1, 0}, {2, 0}, {2, 1}}, true);
        test(3, new int[][]{{1, 0}, {2, 0}, {1, 2}}, true);
        test(3, new int[][]{{1, 0}, {0, 2}, {2, 1}}, false);
    }

    private static void test(int numCourses, int[][] prerequisites, boolean expected) {

        System.out.println("\n Input : Courses " + numCourses + " prerequisites: \n" + Printer.toString(prerequisites));

        ICourseSchedule bfs = new CourseScheduleITopologicalBFS();
        ICourseSchedule bfsOptimized = new CourseScheduleITopologicalBFSOptimized();
        ICourseSchedule dfs = new CourseScheduleITopologicalDFS();

        System.out.println("BFS: Can finish :" + bfs.canFinish(numCourses, prerequisites) + " expected :" + expected);
        System.out.println("BFS optimized: Can finish :" + bfsOptimized.canFinish(numCourses, prerequisites) + " expected :" + expected);
        System.out.println("DFS : Can finish :" + dfs.canFinish(numCourses, prerequisites) + " expected :" + expected);

    }


}

/**
 * prerequisites Length => E at max
 * <p>
 * V = courses
 * E = Edges
 * Time/Space: O(V+E) / O(V)
 * Runtime: 4 ms, faster than 75.87% of Java online submissions for Course Schedule.
 * Memory Usage: 44.2 MB, less than 96.15% of Java online submissions for Course Schedule.
 * {@link DirectedGraph} #Khan's Algo
 */
class CourseScheduleITopologicalBFS implements ICourseSchedule {

    @Override
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses == 0 || prerequisites == null || prerequisites.length == 0)
            return true;


        List<Integer>[] adjList = buildGraph(numCourses, prerequisites);


        return detectCycle(adjList, numCourses);
    }


    /**
     * Build adj list
     * <p>
     * prerequisites Length => E at max
     * <p>
     * O(E)
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    private List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
        final List<Integer>[] adj = new ArrayList[numCourses];

        for (int i = 0; i < numCourses; i++)
            adj[i] = new ArrayList<>();

        for (int i = 0; i < prerequisites.length; i++) {

            int take = prerequisites[i][0];
            int prerequisite = prerequisites[i][1];
            adj[take].add(prerequisite);
        }


        return adj;
    }

    /**
     * Space: O(V)
     * 1. Queue will have at max V vertices; when there is no dependency of any course to any course O(V)
     * 2. When there is a dependency to every course to every other course:
     * <p>
     * Time: When there is a dependency to every course to every other course, except 1 course: Then we enqueu all the courses one by one and traverse to each
     * one by one. There could be max E edges. So
     * O(V+E)
     *
     * @param adjList
     * @param numCourses
     * @return
     */
    private boolean detectCycle(List<Integer>[] adjList, int numCourses) {
        /**
         * Holds the in-degree of each vertices
         */
        int inDegree[] = inDegree(adjList, numCourses);
        Queue<Integer> queue = new LinkedList<>();

        /**
         * Push all the 0 in-degree vertices
         */
        for (int u = 0; u < numCourses; u++) {
            if (inDegree[u] == 0) {
                queue.offer(u);
            }
        }

        int count = 0;
        while (!queue.isEmpty()) {

            int u = queue.poll();
            count++;

            for (Integer v : adjList[u])
                if (--inDegree[v] == 0)
                    queue.offer(v);

        }
        return count == numCourses;
    }

    /**
     * V = courses
     * E = Edges
     * O(VE)
     *
     * @param adjList
     * @param numCourses
     * @return
     */
    private int[] inDegree(List<Integer>[] adjList, int numCourses) {
        /**
         * Holds the in-degree of each vertices
         */
        int inDegree[] = new int[numCourses];

        for (int u = 0; u < numCourses; u++) {

            for (Integer v : adjList[u]) {
                inDegree[v]++;
            }
        }
        return inDegree;
    }
}


/**
 * We can avoid building graph and then inDegree in two different calls. Rather we'll build both of them in single call to avoid one more O(V+E)
 * iteration.
 * <p>
 * prerequisites Length => E at max
 * V = courses
 * E = Edges
 * Time/Space: O(V+E) / O(V)
 * Runtime: 3 ms, faster than 89.99% of Java online submissions for Course Schedule.
 * Memory Usage: 44.3 MB, less than 96.15% of Java online submissions for Course Schedule.
 * <p>
 * {@link DirectedGraph} #Khan's Algo
 */
class CourseScheduleITopologicalBFSOptimized implements ICourseSchedule {

    @Override
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses == 0 || prerequisites == null || prerequisites.length == 0)
            return true;


        final List<Integer>[] adj = new ArrayList[numCourses];
        int inDegree[] = new int[numCourses];

        for (int i = 0; i < numCourses; i++)
            adj[i] = new ArrayList<>();

        for (int i = 0; i < prerequisites.length; i++) {

            int take = prerequisites[i][0];
            int prerequisite = prerequisites[i][1];
            adj[take].add(prerequisite);
            inDegree[prerequisite]++; //Count how many edges are landing on this course as pre-requisite
        }


        return detectCycle(adj, numCourses, inDegree);
    }


    /**
     * Space: O(V)
     * 1. Queue will have at max V vertices; when there is no dependency of any course to any course O(V)
     * 2. When there is a dependency to every course to every other course:
     * <p>
     * Time: When there is a dependency to every course to every other course, except 1 course: Then we enqueu all the courses one by one and traverse to each
     * one by one. There could be max E edges. So
     * O(V+E)
     *
     * @param adjList
     * @param numCourses
     * @return
     */
    private boolean detectCycle(List<Integer>[] adjList, int numCourses, int inDegree[]) {
        /**
         * Holds the in-degree of each vertices
         */
        Queue<Integer> queue = new LinkedList<>();

        /**
         * Push all the 0 in-degree vertices
         */
        for (int u = 0; u < numCourses; u++) {
            if (inDegree[u] == 0) {
                queue.offer(u);
            }
        }

        int count = 0;
        while (!queue.isEmpty()) {

            int u = queue.poll();
            count++;

            for (Integer v : adjList[u])
                if (--inDegree[v] == 0)
                    queue.offer(v);

        }
        return count == numCourses;
    }


}

/**
 * Above we used BFS to find does graph has cycle or not by counting in-degree.
 * We can also use DFS to do same.
 * 1. Build graph
 * 2. Visit each unvisited node. During DFS if you visit same node again then graph has cycle [ this is called back-edge ]
 * <p>
 * Runtime: 2 ms, faster than 99.90% of Java online submissions for Course Schedule.
 * Memory Usage: 44.6 MB, less than 94.62% of Java online submissions for Course Schedule.
 */
class CourseScheduleITopologicalDFS implements ICourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses == 0 || prerequisites == null || prerequisites.length == 0)
            return true;


        List<Integer>[] adjList = buildGraph(numCourses, prerequisites);


        return !detectCycle(adjList, numCourses);
    }

    private boolean detectCycle(List<Integer>[] adjList, int numCourses) {

        int visited[] = new int[numCourses]; //initially all are false;

        /**
         * Visit each course
         */
        for (int c = 0; c < numCourses; c++) {
            //Visit those which has not visit yet

            if (visited[c] != -1 && detectCycle(adjList, c, visited))
                return true;

        }
        return false;
    }

    /**
     * DFS
     *
     * @param adjList
     * @param course
     * @param visited
     * @return
     */
    private boolean detectCycle(List<Integer>[] adjList, int course, int[] visited) {

        /**
         * If visited already, then there is a cycle
         */
        if (visited[course] == 1)
            return true;

        /**
         * This means, that this course has been covered
         */
        if (visited[course] == -1)
            return false;

        visited[course] = 1;

        for (Integer c : adjList[course])
            if (detectCycle(adjList, c, visited))
                return true;

        /**
         * Mark this course as covered
         */
        visited[course] = -1;

        return false;
    }


    private List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
        final List<Integer>[] adj = new ArrayList[numCourses];

        for (int i = 0; i < numCourses; i++)
            adj[i] = new ArrayList<>();

        for (int i = 0; i < prerequisites.length; i++) {

            int take = prerequisites[i][0];
            int prerequisite = prerequisites[i][1];
            adj[take].add(prerequisite);
        }


        return adj;
    }

}