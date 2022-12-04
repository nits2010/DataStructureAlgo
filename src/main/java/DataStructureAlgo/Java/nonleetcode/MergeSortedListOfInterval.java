package DataStructureAlgo.Java.nonleetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Author: Nitin Gupta
 * Date: 31/03/19
 * Description:
 */
public class MergeSortedListOfInterval {

    static class Interval {
        public int start, end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Interval interval = (Interval) o;
            return start == interval.start &&
                    end == interval.end;
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, end);
        }

        @Override
        public String toString() {
            return "(" + start + "," + end + ')';
        }
    }

    private static void test(List<Interval> a, List<Interval> b) {
        System.out.println();
        merge(a, b).stream().forEach(x -> System.out.print(x + "->"));
    }

    public static void main(String []args) {

        List<Interval> a = new ArrayList<>();
        List<Interval> b = new ArrayList<>();


        a.add(new Interval(1, 2));
        a.add(new Interval(3, 9));
        a.add(new Interval(12, 15));


        b.add(new Interval(4, 6));
        b.add(new Interval(8, 10));
        b.add(new Interval(11, 12));

        test(a, b);


        a.clear();
        b.clear();

        a.add(new Interval(1, 5));
        a.add(new Interval(10, 14));
        a.add(new Interval(16, 18));

        b.add(new Interval(2, 6));
        b.add(new Interval(8, 10));
        b.add(new Interval(11, 20));

        test(a, b);


        a.clear();
        b.clear();

        /**
         * [(1,2),(3,4)]
         * [(2,3),(5,6)]
         *
         * [(1,3)
         */
        a.add(new Interval(1, 2));
        a.add(new Interval(3, 4));

        b.add(new Interval(2, 3));
        b.add(new Interval(5, 6));

        test(a, b);
    }


    private static boolean areOverlap(Interval a, Interval b) {
        if (a == null || b == null)
            return false;

        if (a.end < b.start || b.end < a.start)
            return false;
        return true;
    }

    private static Interval merge(Interval a, Interval b) {
        if (areOverlap(a, b)) {
            return new Interval(Math.min(a.start, b.start), Math.max(a.end, b.end));
        }
        return null;
    }

    private static List<Interval> merge(List<Interval> a, List<Interval> b) {

        if (a == null || a.isEmpty())
            return b;
        if (b == null || b.isEmpty())
            return a;

        final LinkedList<Interval> resuList = new LinkedList<>();

        int aIndex = 0, bIndex = 0;
        int aSize = a.size(), bSize = b.size();


        while (aIndex < aSize && bIndex < bSize) {


            Interval x = a.get(aIndex);
            Interval y = b.get(bIndex);
            Interval z = resuList.isEmpty() ? null : resuList.getLast();

            if (x.end < y.start) {
                mergeUpdate(x, z, resuList);
                aIndex++;
            } else if (y.end < x.start) {
                mergeUpdate(y, z, resuList);
                bIndex++;
            } else {
                aIndex++;
                bIndex++;

                mergeUpdate(merge(x, y), z, resuList);
            }


        }

        while (aIndex < aSize){
            Interval x= a.get(aIndex++);
            Interval z = resuList.isEmpty() ? null : resuList.getLast();
            mergeUpdate(x, z, resuList);

        }

        while (bIndex < bSize){
            Interval x= b.get(bIndex++);
            Interval z = resuList.isEmpty() ? null : resuList.getLast();
            mergeUpdate(x, z, resuList);

        }


        return resuList;
    }

    private static void mergeUpdate(Interval finalInterval, Interval z, List<Interval> result) {
        Interval merged = merge(finalInterval, z);

        if (merged != null) {
            z.start = merged.start;
            z.end = merged.end;
        } else {
            result.add(finalInterval);
        }

    }

}
