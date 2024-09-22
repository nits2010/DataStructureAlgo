package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Graph.ParallelCourses;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Author: Nitin Gupta
 * Date: 9/22/2024
 * Question Category: 1494. Parallel Courses II
 * Description:https://leetcode.com/problems/parallel-courses-ii/description/
 *
 *You are given an integer n, which indicates that there are n courses labeled from 1 to n. You are also given an array relations where relations[i] = [prevCoursei, nextCoursei], representing a prerequisite relationship between course prevCoursei and course nextCoursei: course prevCoursei has to be taken before course nextCoursei. Also, you are given the integer k.
 *
 * In one semester, you can take at most k courses as long as you have taken all the prerequisites in the previous semesters for the courses you are taking.
 *
 * Return the minimum number of semesters needed to take all courses. The testcases will be generated such that it is possible to take every course.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 4, relations = [[2,1],[3,1],[1,4]], k = 2
 * Output: 3
 * Explanation: The figure above represents the given graph.
 * In the first semester, you can take courses 2 and 3.
 * In the second semester, you can take course 1.
 * In the third semester, you can take course 4.
 * Example 2:
 *
 *
 * Input: n = 5, relations = [[2,1],[3,1],[4,1],[1,5]], k = 2
 * Output: 4
 * Explanation: The figure above represents the given graph.
 * In the first semester, you can only take courses 2 and 3 since you cannot take more than two per semester.
 * In the second semester, you can take course 4.
 * In the third semester, you can take course 1.
 * In the fourth semester, you can take course 5.
 *
 *
 * Constraints:
 *
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
 * @Hard
 * @DynamicProgramming
 * @BitManipulation
 * @Graph
 * @Bitmask
 *
 * <p><p>
 * Company Tags
 * -----
 * @Google
 * <p><p>
 *
 * @Editorial
 */
public class ParallelCoursesII_1494 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new int[][]{}, 11, 2, 6);
        test &= test(new int[][]{}, 11, 0, -1);
        test &= test(new int[][]{{2,1},{3,1},{1,4}}, 4, 2, 3);
        test &= test(new int[][]{{2,1},{3,1},{4,1},{1,5}}, 5, 2, 4);
        CommonMethods.printResult(test);
    }

    private static boolean test(int[][] relations, int n, int k, int expected) {
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("Relations :" + CommonMethods.toStringInline(relations) + " n : " + n + " k : " + k + " expected : " + expected);

//        SolutionTopologicalSortKhanAlgo khanAlgo = new SolutionTopologicalSortKhanAlgo();
//
//        int khanAlgoObtained = khanAlgo.minNumberOfSemesters(n, relations, k);
//        boolean khanAlgoObtainedTest = khanAlgoObtained == expected;
//
//        System.out.println("khanAlgoObtained = " + khanAlgoObtained + " khanAlgoObtainedTest = " + (khanAlgoObtainedTest ? "PASS" : "FAIL"));
//        return khanAlgoObtainedTest;
        return false;
    }


}
