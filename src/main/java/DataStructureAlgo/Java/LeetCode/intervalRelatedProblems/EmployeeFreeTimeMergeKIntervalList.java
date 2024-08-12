package DataStructureAlgo.Java.LeetCode.intervalRelatedProblems;

import  DataStructureAlgo.Java.helpers.templates.Interval;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeeFreeTimeMergeKIntervalList {

    private static List<List<Interval>> buildInput1() {
        List<Interval> intervals1 = new ArrayList<>();
        intervals1.add(new Interval(1, 2));
        intervals1.add(new Interval(5, 6));

        List<Interval> intervals2 = new ArrayList<>();
        intervals2.add(new Interval(1, 3));

        List<Interval> intervals3 = new ArrayList<>();
        intervals3.add(new Interval(4, 10));
        intervals1.add(new Interval(5, 6));

        List<List<Interval>> lists = new ArrayList<>();
        lists.add(intervals1);
        lists.add(intervals2);
        lists.add(intervals3);
        return lists;
    }

    private static List<List<Interval>> buildInput2() {
        List<Interval> intervals1 = new ArrayList<>();
        intervals1.add(new Interval(1, 3));
        intervals1.add(new Interval(6, 7));

        List<Interval> intervals2 = new ArrayList<>();
        intervals2.add(new Interval(2, 4));

        List<Interval> intervals3 = new ArrayList<>();
        intervals3.add(new Interval(2, 5));
        intervals1.add(new Interval(9, 12));

        List<List<Interval>> lists = new ArrayList<>();
        lists.add(intervals1);
        lists.add(intervals2);
        lists.add(intervals3);
        return lists;
    }

    public static void main(String []args) {
        test(buildInput1());
        test(buildInput2());
    }

    private static void test(List<List<Interval>> employeeTime) {
        SolutionEmployeeFreeTime employeeFreeTime = new SolutionEmployeeFreeTime();

        System.out.println("\nTesting ");
        System.out.println(employeeTime);
        System.out.println("Free time :" + employeeFreeTime.freeTime(employeeTime));
    }

}

class SolutionEmployeeFreeTime {


    /**
     * Algorithm;
     * 1. Convert List or list of interval to just list of interval;
     * 2. Sort them by start time / if clash then end time
     * 3. Merge them
     * 4. Find the left over interval
     *
     * @param employeeTime
     * @return
     */
    public List<Interval> freeTime(List<List<Interval>> employeeTime) {

        if (employeeTime.isEmpty())
            return Collections.EMPTY_LIST;

        //1. Convert List or list of interval to just list of interval;
        final List<Interval> grouped = employeeTime.stream().flatMap(Collection::stream).collect(Collectors.toList());

        // 2. Sort them by start time / if clash then end time
        Collections.sort(grouped, (o1, o2) -> {
            if (o1.start == o2.start)
                return o1.end - o2.end; //if clash then end time
            return o1.start - o2.start; //sort them by start time
        });


        final List<Interval> union = union(grouped);

        List<Interval> result = new ArrayList<>(union.size() / 2);

        //4. Find the left over interval
        for (int i = 0; i < union.size() - 1; i++) {
            result.add(new Interval(union.get(i).end, union.get(i + 1).start));
        }

        return result;

    }


    /**
     * https://leetcode.com/problems/merge-intervals/
     * 3. Merge them
     *
     * @param intervals
     * @return
     */
    private List<Interval> union(List<Interval> intervals) {

        if (intervals == null || intervals.size() == 0)
            return intervals;


        final List<Interval> result = new ArrayList<>(intervals.size());

        result.add(intervals.get(0));

        int k = 0;

        for (int i = 1; i < intervals.size(); i++) {

            Interval a = result.get(k);
            Interval b = intervals.get(i);

            //if start time of previous > end time of current, then merge
            if (a.end > b.start && b.end >= a.start) {
                a.start = Math.min(a.start, b.start);
                a.end = Math.max(a.end, b.end);
            } else {
                k++;
                result.add(b.clone());
            }


        }

        return result;
    }
}