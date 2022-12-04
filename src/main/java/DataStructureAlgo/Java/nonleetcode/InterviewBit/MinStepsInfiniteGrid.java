package DataStructureAlgo.Java.nonleetcode.InterviewBit;

import java.util.ArrayList;

/**
 * Author: Nitin Gupta
 * Date: 2019-05-20
 * Description:
 * https://www.interviewbit.com/problems/min-steps-in-infinite-grid/
 */
public class MinStepsInfiniteGrid {

    public static void main(String arg[]) {
        int p[][] = {{0, 0}, {1, 1}, {1, 2}};
        ArrayList<Integer> a = new ArrayList<>();
        a.add(-7);
        a.add(-13);
        ArrayList<Integer> b = new ArrayList<>();
        b.add(1);
        b.add(-5);
        System.out.println(coverPoints(a, b));
    }

    public static int coverPoints(ArrayList<Integer> A, ArrayList<Integer> B) {

        int x = A.get(0);
        int y = B.get(0);

        int distance = 0;
        for (int i = 1; i < A.size(); i++) {

            int xx = A.get(i);
            int yy = B.get(i);

            int dist = distance(x, y, xx, yy);

            x = xx;
            y = yy;

            distance += dist;

        }

        return distance;

    }

    private static int distance(int x, int y, int xx, int yy) {

        int p = xx - x;
        int q = yy - y;

        return Math.max(Math.abs(p), Math.abs(q));

    }
}
