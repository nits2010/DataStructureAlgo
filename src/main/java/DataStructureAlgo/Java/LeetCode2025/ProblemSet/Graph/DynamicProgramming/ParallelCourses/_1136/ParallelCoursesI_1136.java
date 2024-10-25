package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Graph.DynamicProgramming.ParallelCourses._1136;

import DataStructureAlgo.Java.LeetCode2025.ProblemSet.Graph.CourseSchedule._207.CourseScheduleI_207;
import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Author: Nitin Gupta
 * Date: 9/22/2024
 * Question Category: 1136. Parallel Courses
 * Description:
 * https://leetcode.com/problems/parallel-courses/description/
 * https://leetcode.ca/all/1136.html
 *
 * There are N courses, labelled from 1 to N.
 *
 * We are given relations[i] = [X, Y], representing a prerequisite relationship between course X and course Y: course X has to be studied before course Y.
 *
 * In one semester you can study any number of courses as long as you have studied all the prerequisites for the course you are studying.
 *
 * Return the minimum number of semesters needed to study all courses.  If there is no way to study all the courses, return -1.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: N = 3, relations = [[1,3],[2,3]]
 * Output: 2
 * Explanation:
 * In the first semester, courses 1 and 2 are studied. In the second semester, course 3 is studied.
 * Example 2:
 *
 *
 *
 * Input: N = 3, relations = [[1,2],[2,3],[3,1]]
 * Output: -1
 * Explanation:
 * No course can be studied because they depend on each other.
 *
 *
 * Note:
 *
 * 1 <= N <= 5000
 * 1 <= relations.length <= 5000
 * relations[i][0] != relations[i][1]
 * There are no repeated relations in the input.
 * <p><p>
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link CourseScheduleI_207.SolutionKhansTopologicalSort}
 * extension {@link }
 * <p><p>
 * Tags
 * -----
 * @Hard
 * @LeetCodeLockedProblem
 * @PremimumQuestion
 * @DynamicProgramming
 * @BitManipulation
 * @Graph
 * @Bitmask
 *
 * <p><p>
 * Company Tags
 * -----
 * @Google
 * @Uber
 * <p><p>
 *
 * @Editorial
 */
public class ParallelCoursesI_1136 {

    public static void main(String[] args) {
        boolean test = true;

        test &= test(new int[][]{{1,3},{2,3}}, 3, 2);
        test &= test(new int[][]{{1,2},{2,3},{3,1}}, 3, -1);

        CommonMethods.printResult(test);
    }

    private static boolean test(int[][] relations, int n, int expected) {
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("Relations :" + CommonMethods.toStringInline(relations) + " expected : "+expected);

        SolutionTopologicalSortKhanAlgo khanAlgo = new SolutionTopologicalSortKhanAlgo();
        int khanAlgoObtained = khanAlgo.minimumSemesters(n, relations);
        boolean khanAlgoTest = khanAlgoObtained == expected;
        System.out.println("Khan Algo : " + khanAlgoObtained + " Result: " + (khanAlgoTest ? "PASSED" : "FAILED"));

        SolutionTarjanAlgo tarjanAlgo = new SolutionTarjanAlgo();
        int tarjanAlgoObtained = tarjanAlgo.minimumSemesters(n, relations);
        boolean tarjanAlgoTest = tarjanAlgoObtained == expected;
        System.out.println("Tarjan Algo : " + tarjanAlgoObtained + " Result: " + (tarjanAlgoTest ? "PASSED" : "FAILED"));

        return khanAlgoTest && tarjanAlgoTest;

    }

    /**
     * If we observe the graph, it is a directed acyclic graph. The course can only be completed if there is no cycle in the graph (like in example 2, there is
     * a cycle hence we can't complete it).
     * Also, all the courses which have 0 in-degree can be taken parallel. Performing topological sort on the graph will give us the required answer.
     * As always, if topological sort has the same size as the number of nodes in the graph, then there is no cycle otherwise it has a cycle.
     *
     * {@link DataStructureAlgo.Java.nonleetcode.graph.questions.TopologicalSorts#topologicalSortKhanAlgo(List[], int)}
     *
     */
    static class SolutionTopologicalSortKhanAlgo {

        public int minimumSemesters(int n, int[][] relations) {
            if(n == 0 || relations.length == 0)
                return -1;

            //build graph and get indegree counted as well
            final int []inDegree = new int[n];
            List<List<Integer>> adjList = buildGraph(n, relations, inDegree);

           //enque all the indegree=0 node
            Queue<Integer> queue = Arrays.stream(inDegree)
                    .filter(i -> inDegree[i] == 0)
                    .boxed()
                    .collect(Collectors.toCollection(LinkedList::new));

            int semesters = 0;
            int courseTaken = 0;
            while(!queue.isEmpty()){

                // we need to take all the indegree = 0 course at once in a single semester
                for (int i = queue.size(); i > 0; i--) {

                    //take the inDegree = 0 course
                    int course = queue.poll();
                    courseTaken++;
                    //all the course where this 'course' is a prerequisite, can be decrease by 1 (aka indegree - 1 )
                    for (int nextCourse : adjList.get(course)){

                        if(--inDegree[nextCourse] == 0){

                            //this course can be taken parallel with another course which has 0 indegree in the next semester
                            queue.offer(nextCourse);
                        }
                    }
                }

                //a semester is over now
                semesters++;
            }

            return courseTaken == n ? semesters : -1;
        }

        private List<List<Integer>> buildGraph(int n, int[][] relations, int[] inDegree) {
            List<List<Integer>> adjList = new ArrayList<>(n);

            IntStream.range(0, n).forEach(i -> adjList.add(new ArrayList<>()));

            for(int []relation : relations) {
                int prevCourse = relation[0] - 1; //courses labeled from 1 to n
                int nextCourse = relation[1] - 1 ; //courses labeled from 1 to n

                adjList.get(prevCourse).add(nextCourse);
                inDegree[nextCourse]++;
            }

            return adjList;

        }
    }


    /**
     * If we observe the graph, it is a directed acyclic graph. The course can only be completed if there is no cycle in the graph (like in example 2, there is
     * a cycle hence we can't complete it).
     * Post making DAG, we can perform Tarjan Algo for topological Sort, in this sort, we can get the depth of the node, using dfs.
     * Post DFS, we get the dfs tree, a node the leaf, is the last course. So the maximum depth of this tree would be the number of semesters required.
     * The longest Path is the order of courses in each semester.
     *
     * {@link DataStructureAlgo.Java.nonleetcode.graph.questions.TopologicalSorts#topologicalSortDFS(int, List[])}
     *
     */
    static class SolutionTarjanAlgo {

        public int minimumSemesters(int n, int[][] relations) {
            if (n == 0 || relations.length == 0)
                return -1;

            List<List<Integer>> adjList = buildGraph(n, relations);
            int []depth = new int[n]; //depth of each node
            Arrays.fill(depth, 1); // at least a semester is required to take even a single course

            //to keep track, if we have visited a course or not
            // 0 -> not visited,
            // 1 -> visited
            // 2 -> course completed, without a cycle
            int []visited = new int [n];

            //for all the disconnected courses
            for (int i=0; i<n; i++) {
                if(detectCycle(adjList, depth, 0, visited))
                    return -1; //cycle detected
            }
            return Arrays.stream(depth).max().getAsInt();

        }

        private boolean detectCycle( List<List<Integer>> adjList, int[] depth, int course, int []visited) {

            if(visited[course] == 1)
                return true; //already visited

            if(visited[course] == 2)
                return false; //course completed, without a cycle

            visited[course] = 1; //mark as visited, course taken

            for (int nextCourse : adjList.get(course)) {
                if(detectCycle(adjList, depth, nextCourse, visited))
                    return true;

                //calculate the depth of the course in DAG, it's either the current depth or depth based on next course
                depth[course] = Math.max(depth[course], depth[nextCourse] + 1);
            }

            visited[course] = 2; //mark as completed
            return false;

        }

        private List<List<Integer>> buildGraph(int n, int[][] relations) {
            List<List<Integer>> adjList = new ArrayList<>(n);

            IntStream.range(0, n).forEach(i -> adjList.add(new ArrayList<>()));

            for(int []relation : relations) {
                int prevCourse = relation[0] - 1; //courses labeled from 1 to n
                int nextCourse = relation[1] - 1 ; //courses labeled from 1 to n

                adjList.get(prevCourse).add(nextCourse);
            }

            return adjList;

        }
    }
}
