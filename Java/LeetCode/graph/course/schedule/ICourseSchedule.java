package Java.LeetCode.graph.course.schedule;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-21
 * Description:
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
