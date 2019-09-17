package Java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 01/04/19
 * Description:
 */
public class ClosestPairPoints {

    static class Point {
        public int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

        public static Comparator<Point> byX = (o1, o2) -> o1.x - o2.x;

        public static Comparator<Point> byY = (o1, o2) -> o1.y - o2.y;
    }

    public static void main(String []args) {

        Point points[] = {new Point(2, 3), new Point(12, 30), new Point(40, 50), new Point(5, 1), new Point(12, 10), new Point(3, 4)};


        Point pointsX[] = Arrays.copyOf(points, points.length);
        Point pointsY[] = Arrays.copyOf(points, points.length);

        Arrays.sort(pointsX, Point.byX);
        Arrays.sort(pointsY, Point.byY);


        System.out.println(closestPairPoint(pointsX, pointsY, 0, points.length));

    }

    private static double distance(Point[] points) {

        double minDistance = Integer.MAX_VALUE;

        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                minDistance = Math.min(minDistance, distance(points[i], points[j]));
            }
        }

        return minDistance;
    }

    private static double distance(Point x, Point y) {
        return Math.sqrt(Math.pow((y.x - x.x), 2) + Math.pow((y.y - x.y), 2));
    }

    private static double closestPairPoint(Point[] pointsX, Point[] pointsY, int low, int high) {
        if (pointsX.length <= 3) {
            return distance(pointsX);
        }


        int mid = (high + low) / 2;

        Point midPX = pointsX[mid];


        List<Point> pointsYL = new ArrayList<>();
        List<Point> pointsYR = new ArrayList<>();

        for (int i = 0; i < pointsX.length; i++) {
            if (pointsY[i].x <= midPX.x)
                pointsYL.add(pointsY[i]);
            else
                pointsYR.add(pointsY[i]);
        }


        double distanceOnLeft = closestPairPoint(Arrays.copyOfRange(pointsX, low, mid), pointsYL.toArray(new Point[pointsYL.size()]), low, mid);
        double distanceOnRight = closestPairPoint(Arrays.copyOfRange(pointsX, mid, high), pointsYR.toArray(new Point[pointsYR.size()]), mid, high);

        double distance = Math.min(distanceOnLeft, distanceOnRight);

        Point strip[] = new Point[high];
        int j = 0;

        for (int i = 0; i < high; i++) {

            if (Math.abs(pointsY[i].x - midPX.x) < distance) {
                strip[j++] = pointsY[i];
            }

        }

        return Math.max(distance, stripDistance(strip, distance, j));


    }

    private static double stripDistance(Point[] strip, double distance, int limit) {

        double min = distance;

        for (int i = 0; i < limit; i++) {
            for (int j = i + 1; j < limit && strip[i].y - strip[j].y < min; j++) {
                min = Math.min(min, distance(strip[i], strip[j]));
            }
        }

        return min;
    }
}
