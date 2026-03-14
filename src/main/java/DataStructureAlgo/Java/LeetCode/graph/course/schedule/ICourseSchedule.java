package DataStructureAlgo.Java.LeetCode.graph.course.schedule;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-21
 * Question Title: I Course Schedule
 * Link: https://leetcode.com/problems/i-course-schedule/
 * Description:
 * Description:
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
 <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public interface ICourseSchedule {

    //https://leetcode.com/problems/course-schedule/
    default boolean canFinish(int numCourses, int[][] prerequisites) {
        return false;
    }

    //https://leetcode.com/problems/course-schedule-ii/
    default int[] findOrder(int numCourses, int[][] prerequisites) {
        return null;
    }

    //https://leetcode.com/problems/course-schedule-iii/
    default int scheduleCourse(int[][] courses) { return 0; }
}
