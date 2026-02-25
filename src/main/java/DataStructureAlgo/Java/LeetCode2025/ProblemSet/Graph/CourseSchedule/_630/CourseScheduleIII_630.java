package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Graph.CourseSchedule._630;

import DataStructureAlgo.Java.LeetCode.graph.course.schedule.*;
import DataStructureAlgo.Java.LeetCode2025.ProblemSet.Graph.CourseSchedule._210.CourseScheduleII_210;
import DataStructureAlgo.Java.LeetCode2025.ProblemSet.Graph.CourseSchedule._207.CourseScheduleI_207;
import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 9/22/2024
 * Question Category: 630. Course Schedule III
 * Description: https://leetcode.com/problems/course-schedule-iii/description/
 * here are n different online courses numbered from 1 to n. You are given an array courses where courses[i] = [durationi, lastDayi] indicate that the ith course should be taken continuously for durationi days and must be finished before or on lastDayi.
 *
 * You will start on the 1st day and you cannot take two or more courses simultaneously.
 *
 * Return the maximum number of courses that you can take.
 *
 *
 *
 * Example 1:
 *
 * Input: courses = [[100,200],[200,1300],[1000,1250],[2000,3200]]
 * Output: 3
 * Explanation:
 * There are totally 4 courses, but you can take 3 courses at most:
 * First, take the 1st course, it costs 100 days so you will finish it on the 100th day, and ready to take the next course on the 101st day.
 * Second, take the 3rd course, it costs 1000 days so you will finish it on the 1100th day, and ready to take the next course on the 1101st day.
 * Third, take the 2nd course, it costs 200 days so you will finish it on the 1300th day.
 * The 4th course cannot be taken now, since you will finish it on the 3300th day, which exceeds the closed date.
 * Example 2:
 *
 * Input: courses = [[1,2]]
 * Output: 1
 * Example 3:
 *
 * Input: courses = [[3,2],[4,3]]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= courses.length <= 104
 * 1 <= durationi, lastDayi <= 104
 * <p><p>
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.graph.course.schedule.CourseSchedule3}
 * Similar {@link}
 * extension {@link CourseScheduleII_210} {@link CourseScheduleI_207}
 * <p><p>
 * Tags
 * -----
 * @hard
 * @Array
 * @Greedy
 * @Sorting
 * @Heap(PriorityQueue)
 *
 * <p><p>
 * Company Tags
 * -----
 * @Google
 * @Bloomberg
 * @WorksApplications
 * <p><p>
 *
 * @Editorial
 */
public class CourseScheduleIII_630 {
    public static void main(String[] args) {
        boolean test = true;
        test &= test(new int[][]{{100, 200}, {200, 1300}, {1000, 1250}, {2000, 3200}}, 3);
        test &=test(new int[][]{{100, 200}, {50, 200}, {15, 200}, {1000, 200}}, 3);
        test &= test(new int[][]{{100, 200}, {50, 200}, {15, 200}, {1000, 2000}}, 4);
        test &= test(new int[][]{{1,2}}, 1);
        test &= test(new int[][]{{3,2}, {4,3}}, 0);
        CommonMethods.printAllTestOutCome(test);
    }

    private static boolean test(int[][] courses, int expected) {
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("Courses :" + CommonMethods.toStringInline(courses) + " expected : "+expected);

        ICourseSchedule solutionGreedy = new SolutionGreedy();
        int greedyObtained = solutionGreedy.scheduleCourse(courses);
        boolean greedyTest = greedyObtained == expected;
        System.out.println("Greedy : " + greedyObtained + " Result: " + (greedyTest ? "PASSED" : "FAILED"));

        ICourseSchedule solutionPQ = new SolutionPQ();
        int pqObtained = solutionPQ.scheduleCourse(courses);
        boolean pqTest = pqObtained == expected;
        System.out.println("PQ : " + pqObtained + " Result: " + (pqTest ? "PASSED" : "FAILED"));
        return greedyTest && pqTest;




    }

    /**
     To optimize the course selection process, we should prioritize courses that can be completed quickly.
     This approach allows us to add more courses to the schedule. Since there are no restrictions on when a course can start,
     but there are deadlines for completion, it is beneficial to choose courses with the nearest deadlines.

     This leads to a greedy strategy where we select the course that ends the soonest. To implement this, we can sort the courses by their deadlines in
     ascending order and start picking the ones that can be completed quickly. However, we must also consider the time required to complete each course.
     If at any point, the overall schedule exceeds a course's deadline, we have two options:

     1. Skip the current course.
     2. Replace a course in the schedule with the current one, but only if the current course takes less time to complete than the one being replaced.

     This strategy works because it reduces the overall scheduled time, allowing us to add more courses later.

     Algorithm:
     1. Sort the courses by their deadlines in ascending order.
     2. Start picking the courses that can be completed quickly.
     3. If the overall schedule exceeds an upcoming course's deadline, then try to find a course by removing that and
     adding current makes the overall schedule less as compared to previously.
     *
     *
     * <p>
     * O(n^2) / O(n)
     */
    static class SolutionGreedy implements ICourseSchedule {
        public int scheduleCourse(int[][] courses) {
            if(courses == null || courses.length == 0)
                return 0;

            // 1. Sort the courses by their deadlines in ascending order.
            Arrays.sort(courses, Comparator.comparingInt(c -> c[1]));

            int scheduledTime = 0;
            List<Integer> schedule = new ArrayList<>();

            // 2. Start picking the courses that can be completed quickly.
            for (int [] course : courses){

                //pick this course if it can be fit under scheduled time and does not exceed deadline
                if(scheduledTime + course[0] <= course[1]){
                    scheduledTime += course[0];
                    schedule.add(course[0]);
                }else if(!schedule.isEmpty()){

                    // 3. If the overall schedule exceeds an upcoming course's deadline, then try to find a course by removing that and
                    // adding current makes the overall schedule less as compared to previously.

                    //course added in scheduled in ascending order of deadline, but in any order of course length.
                    int courseToReplace = schedule.size() - 1; //assume we replace the last course

                    //find a course that can be replaced, a candidate with the highest course length will be picked, as its adding more time in schedule
                    for(int i=0; i<schedule.size()-1; i++){
                        if(schedule.get(i) > schedule.get(courseToReplace)){
                            //we find a course that adds more time in schedule
                            courseToReplace = i;
                        }
                    }

                    //we will replace 'courseToReplace' with 'course' iff courseToReplace takes more time than the current course
                    if(schedule.get(courseToReplace) > course[0]){

                        //if we can replace, then we need to update the scheduleTIme
                        scheduledTime -= schedule.get(courseToReplace);


                        //now take the current course
                        scheduledTime += course[0];

                        //replace the course
                        schedule.set(courseToReplace, course[0]);

                    }
                }
            }

            return schedule.size();
        }
    }

    /**
     * The basic greedy solution touches o(n^2) time and o(n) space. The reason for that is to find the course by which we can replace it.
     * If we can improve on this replacement better, then complexity can be reduced further.
     * It is noted that, in this loop, we always find a course that has 'highest course length'.
     * If we can pick such a course less than O(n) time, then we can improve the complexity.
     *
     * Option 1: Keep the scheduled in sorted manner. This will make sure that the highest course length will always be at last.
     * But keep it in sorted order can go up to O(n) time (best algo is insertion sort for this case ).
     *
     * Option 2: Keep a max-heap, this will make sure that the highest course length would be at the top of the heap and takes only O(logn) time.
     * Also building heap on the go takes O(logn) time, as adding element in heap, is O(logn). Worth to note that, if all courses can be picked up, then the
     * total time taken by heap would be nlogn.
     *
     * Which makes overall time complexity O(n*logn)
     Algorithm:
     1. Sort the courses by their deadlines in ascending order.
     2. Start picking the courses that can be completed quickly. Add them in max heap as well
     3. If the overall schedule exceeds an upcoming course's deadline, peek the heap and see if we need a replacement or not.
     */
    static class SolutionPQ implements ICourseSchedule {
        public int scheduleCourse(int[][] courses) {
            if (courses == null || courses.length == 0)
                return 0;

            // 1. Sort the courses by their deadlines in ascending order.
            Arrays.sort(courses, Comparator.comparingInt(c -> c[1]));

            PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
            int scheduledTime = 0;

            // 2. Start picking the courses that can be completed quickly.
            for (int [] course : courses){

                //pick this course if it can be fit under scheduled time and does not exceed deadline
                if (scheduledTime + course[0] <= course[1]){
                    scheduledTime += course[0];
                    pq.add(course[0]);
                }else if (!pq.isEmpty()){

                    // 3. If the overall schedule exceeds an upcoming course's deadline, then try to find a course by removing that and
                    // adding current makes the overall schedule less as compared to previously.

                    // peek the heap and see if we need a replacement or not.
                    if(pq.peek() > course[0]){

                        //if we can replace, then we need to update the scheduleTIme
                        scheduledTime -= pq.poll();
                        scheduledTime += course[0];
                        pq.add(course[0]);
                    }

                }
            }
            return pq.size();
        }
    }
}
