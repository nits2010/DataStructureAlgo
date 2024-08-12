package DataStructureAlgo.Java.helpers.templates;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-18
 * Description: https://medium.com/algorithm-and-datastructure/employee-free-time-795c7682c973
 * Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.
 * Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.
 * Example 1:
 * Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
 * Output: [[3,4]]
 * Explanation:
 * There are a total of three employees, and all common
 * free time intervals would be [-inf, 1], [3, 4], [10, inf].
 * We discard any intervals that contain inf as they aren't finite.
 * Example 2:
 * Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
 * Output: [[5,6],[7,9]]
 */

public class Interval implements Cloneable {
    public int start, end;


    @Override
    public Interval clone()  {
        return new Interval(this.start, this.end);
    }

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[" + start + "," + end + ']';
    }
}
