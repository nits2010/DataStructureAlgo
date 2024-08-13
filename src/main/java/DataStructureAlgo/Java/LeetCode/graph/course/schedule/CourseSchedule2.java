package DataStructureAlgo.Java.LeetCode.graph.course.schedule;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-21
 * Description: https://leetcode.com/problems/course-schedule-ii/
 *
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 * <p>
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 * Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
 * There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.
 * Example 1:
 * <p>
 * Input: 2, [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take. To take course 1 you should have finished
 * course 0. So the correct course order is [0,1] .
 * Example 2:
 * <p>
 * Input: 4, [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,1,2,3] or [0,2,1,3]
 * Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both
 * courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
 * So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
 * Note:
 * <p>
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 * You may assume that there are no duplicate edges in the input prerequisites.
 * <p>
 * {@link CourseSchedule1} Just need to return courses order also
 * JUST ONE LINE CHANGE FROM {@link CourseSchedule1} TO THIS SOLUTION
 */
public class CourseSchedule2 {


    public static void main(String[] args) {
        test(1, new int[][]{}, new int[]{0});
        test(2, new int[][]{}, new int[]{1, 0});
        test(2, new int[][]{{1, 0}}, new int[]{0, 1});
        test(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}}, new int[]{0, 2, 1, 3});
        test(3, new int[][]{{1, 0}, {0, 1}}, new int[0]);
        test(3, new int[][]{{1, 0}}, new int[]{0, 2, 1});
        test(3, new int[][]{{1, 0}, {2, 0}}, new int[]{0, 2, 1});
        test(3, new int[][]{{1, 0}, {2, 0}, {2, 1}}, new int[]{0, 1, 2});
        test(3, new int[][]{{1, 0}, {2, 0}, {1, 2}}, new int[]{0, 2, 1});//
        test(3, new int[][]{{1, 0}, {0, 2}, {2, 1}}, new int[0]);
    }

    private static void test(int numCourses, int[][] prerequisites, int[] expected) {

        System.out.println("\n Input : Courses " + numCourses + " prerequisites: \n" + CommonMethods.toString(prerequisites));

        ICourseSchedule bfs = new CourseScheduleIIBFS();
        ICourseSchedule dfs = new CourseScheduleIIDFS();

        System.out.println("BFS Order:" + CommonMethods.toString(bfs.findOrder(numCourses, prerequisites)) + " expected :" + CommonMethods.toString(expected));
        System.out.println("DFS Order:" + CommonMethods.toString(dfs.findOrder(numCourses, prerequisites)) + " expected :" + CommonMethods.toString(expected));

    }


}


/**
 * O(V+E) / O(V)
 * Runtime: 3 ms, faster than 96.54% of Java online submissions for Course Schedule II.
 * Memory Usage: 45.3 MB, less than 92.68% of Java online submissions for Course Schedule II.
 */
class CourseScheduleIIBFS implements ICourseSchedule {
    @Override
    public int[] findOrder(int numCourses, int[][] prerequisites) {

        if (numCourses == 0 || prerequisites == null)
            return new int[0];


        final List<Integer>[] adj = new ArrayList[numCourses];
        final int inDegree[] = new int[numCourses];

        for (int i = 0; i < numCourses; i++)
            adj[i] = new ArrayList<>();


        for (int pre[] : prerequisites) {
            int take = pre[0];
            int prerequisite = pre[1];

            adj[take].add(prerequisite);
            inDegree[prerequisite]++;
        }

        return order(adj, inDegree, numCourses);
    }

    private int[] order(List<Integer>[] adj, int[] inDegree, int numCourses) {

        int order[] = new int[numCourses];
        int count = numCourses - 1;

        Queue<Integer> queue = new LinkedList<>();

        for (int u = 0; u < numCourses; u++) {
            if (inDegree[u] == 0)
                queue.offer(u);
        }


        while (!queue.isEmpty()) {

            int u = queue.poll();
            order[count--] = u; //ONE LINE CHANGED

            for (Integer v : adj[u])
                if (--inDegree[v] == 0)
                    queue.offer(v);
        }

        if (count < 0) //Or you have to shortestPath the array if you start from 0th index
            return order;

        return new int[0];
    }

}


/*
 * O(V+E) / O(V)
Runtime: 2 ms, faster than 100.00% of Java online submissions for Course Schedule II.
Memory Usage: 45.4 MB, less than 92.68% of Java online submissions for Course Schedule II.
 */
class CourseScheduleIIDFS implements ICourseSchedule {
    @Override
    public int[] findOrder(int numCourses, int[][] prerequisites) {

        if (numCourses == 0 || prerequisites == null)
            return new int[0];


        final List<Integer>[] adj = buildGraph(numCourses, prerequisites);

        return order(adj, numCourses);
    }

    private final List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
        final List<Integer>[] adj = new ArrayList[numCourses];

        for (int i = 0; i < numCourses; i++)
            adj[i] = new ArrayList<>();

        for (int pre[] : prerequisites) {
            int take = pre[0];
            int prerequisite = pre[1];

            adj[take].add(prerequisite);
        }

        return adj;

    }

    private int[] order(List<Integer>[] adjList, int numCourses) {

        int order[] = new int[numCourses];
        int visited[] = new int[numCourses];

        int index[] = {0};

        /**
         * Visit each course
         */
        for (int c = 0; c < numCourses; c++) {
            //Visit those which has not visit yet
            if (visited[c] != -1 && orderDFS(adjList, c, visited, order, index)) {
                return new int[0];
            }

        }

        return order;

    }

    private boolean orderDFS(List<Integer>[] adjList, int course, int[] visited, int order[], int index[]) {

        if (visited[course] == 1)
            return true;

        if (visited[course] == -1)
            return false;

        visited[course] = 1;
        for (Integer v : adjList[course])
            if (orderDFS(adjList, v, visited, order, index))
                return true;

        visited[course] = -1;
        order[index[0]++] = course; //ONE LINE CHANGED

        return false;
    }

}