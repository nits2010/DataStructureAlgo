package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Graph.CourseSchedule;


import DataStructureAlgo.Java.LeetCode.graph.course.schedule.ICourseSchedule;
import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Author: Nitin Gupta
 * Date: 9/21/2024
 * Question Category: 210. Course Schedule II
 * Description: https://leetcode.com/problems/course-schedule-ii/
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
 *
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.
 *
 *
 *
 * Example 1:
 *
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
 * Example 2:
 *
 * Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,2,1,3]
 * Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
 * So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
 * Example 3:
 *
 * Input: numCourses = 1, prerequisites = []
 * Output: [0]
 *
 *
 * Constraints:
 *
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= numCourses * (numCourses - 1)
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * ai != bi
 * All the pairs [ai, bi] are distinct.
 *
 * File reference
 * -----------
 * Duplicate {@link  DataStructureAlgo.Java.LeetCode.graph.course.schedule.CourseSchedule2}
 * Similar {@link}
 * extension {@link DataStructureAlgo.Java.LeetCode.graph.course.schedule.CourseSchedule1}
 * <p><p>
 * Tags
 * -----
 * @medium
 * @Depth-FirstSearch
 * @Breadth-FirstSearch
 * @Graph
 * @TopologicalSort
 *
 * <p><p>
 * Company Tags
 * -----
 * @tiktok
 * @Amazon
 * @Apple
 * @Bloomberg
 * @DoorDash
 * @Facebook
 * @Google
 * @Intuit
 * @Microsoft
 * @Nutanix
 * @PalantirTechnologies
 * @Pinterest
 * @Square
 * @Uber
 * @WalmartLabs
 * @Zenefits
 * <p><p>
 *
 * @Editorial
 */
public class CourseScheduleII_210 {

    public static void main(String[] args) {
        boolean test = true;
        test &=test(1, new int[][]{}, new int[]{0});
        test &= test(2, new int[][]{}, new int[]{1, 0});
        test &=test(2, new int[][]{{1, 0}}, new int[]{0, 1});
        test &=test(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}}, new int[]{0, 2, 1, 3});
        test &=test(3, new int[][]{{1, 0}, {0, 1}}, new int[0]);
        test &=test(3, new int[][]{{1, 0}}, new int[]{0, 2, 1});
        test &=test(3, new int[][]{{1, 0}, {2, 0}}, new int[]{0, 2, 1});
        test &=test(3, new int[][]{{1, 0}, {2, 0}, {2, 1}}, new int[]{0, 1, 2});
        test &=test(3, new int[][]{{1, 0}, {2, 0}, {1, 2}}, new int[]{0, 2, 1});//
        test &= test(3, new int[][]{{1, 0}, {0, 2}, {2, 1}}, new int[0]);

        CommonMethods.printResult(test);
    }

    private static boolean test(int numCourses, int[][] prerequisites, int[] expected) {
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("Input : Courses " + numCourses + " prerequisites: " + CommonMethods.toStringInline(prerequisites) + " Expected : "+ CommonMethods.toString(expected));

        ICourseSchedule solutionKhansAlgo = new SolutionBFSKhanAlgo();
        int []output = solutionKhansAlgo.findOrder(numCourses, prerequisites);
        boolean khanAlgoTestResult = CommonMethods.equalsValuesWithoutOrder(output, expected);
        System.out.println(" Khan algo output : "+CommonMethods.toString(output) + " khanAlgoTestResult : " + (khanAlgoTestResult ? "PASSED" : "FAILED"));

        ICourseSchedule solutionDFSTarjanAlgo = new SolutionDFSTarjanAlgo();
        output = solutionDFSTarjanAlgo.findOrder(numCourses, prerequisites);
        boolean dfsAlgoTestResult = CommonMethods.equalsValuesWithoutOrder(output, expected);
        System.out.println(" DFS algo output : "+CommonMethods.toString(output) + " dfsAlgoTestResult : " + (dfsAlgoTestResult ? "PASSED" : "FAILED"));

        return khanAlgoTestResult && dfsAlgoTestResult;




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
    static class SolutionBFSKhanAlgo implements ICourseSchedule{
        public int[] findOrder(int numCourses, int[][] prerequisites) {

            int[] inDegree = new int[numCourses];
            List<List<Integer>> adjList = buildGraph(numCourses, prerequisites, inDegree);

            Queue<Integer> queue = new LinkedList<>();
            for (int i=0; i<numCourses; i++) {
                if (inDegree[i] == 0) {
                    queue.offer(i);
                }
            }
            int []courses = new int[numCourses];
//            List<Integer> list = new ArrayList<>();
            while (!queue.isEmpty()){
                int u = queue.poll();
                courses[--numCourses] = u;
//                list.add(u);
                for (int v : adjList.get(u)) {
                    inDegree[v]--;
                    if (inDegree[v] == 0) {
                        queue.offer(v);
                    }
                }
            }

            if(numCourses!=0) return new int[0];

//            System.out.println("list : "+list);
            return courses;
        }

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
     * Tarjan’s Algorithms：
     * https://en.wikipedia.org/wiki/Tarjan%27s_strongly_connected_components_algorithm DFS based，
     *
     * loop through each node of the graph in an arbitrary order，
     * initiating a depth-first search that terminates when it hits any node that has already been visited
     * since the beginning of the topological sort or the node has no outgoing edges (i.e. a leaf node).
     */
    static class SolutionDFSTarjanAlgo implements ICourseSchedule{
        int courseIndex = 0;
        public int[] findOrder(int numCourses, int[][] prerequisites) {

            List<List<Integer>> adjList = buildGraph(numCourses, prerequisites);

            return findOrder(numCourses, adjList);

        }


        /**
         * {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.Graph.CourseSchedule.CourseScheduleI_207.SolutionDFS}
         * @param numCourses
         * @param prerequisites
         * @return
         */
        private int [] findOrder(int numCourses, List<List<Integer>> adjList) {
            int []courses = new int[numCourses];

            //to keep track, if we have visited a course or not
            // 0 -> not visited,
            // 1 -> visited
            // 2 -> course completed, without a cycle
            int []visited = new int[numCourses];

            for (int i=0; i<numCourses; i++){
                if (visited[i]!=2 && findOrder(adjList, i, visited, courses ))
                    return new int[0]; //if cycle exists, then empty array
            }

            return courses;

        }

        private boolean findOrder(List<List<Integer>> adjList, int i, int []visited, int []courses) {

            //cycle exists ?
            if (visited[i] == 1)
                return true;

            //if the course taken, avoid further dfs
            if(visited[i] == 2)
                return false;

            //take the course
            visited[i] = 1;

            for (Integer prerequisite : adjList.get(i)) {
                if(findOrder(adjList, prerequisite, visited, courses))
                    return true;
            }

            //complete the course
            visited[i] = 2;
            courses[courseIndex++] = i;

            return false;


        }

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
    }
}
