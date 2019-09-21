package Java.LeetCode;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-22
 * Description: https://leetcode.com/problems/rectangle-area/
 * https://leetcode.com/problems/rectangle-area/discuss/337710/Intuitive-draw-solution
 * Find the total area covered by two rectilinear rectangles in a 2D plane.
 * <p>
 * Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.
 * <p>
 * Rectangle Area
 * <p>
 * Example:
 * <p>
 * Input: A = -3, B = 0, C = 3, D = 4, E = 0, F = -1, G = 9, H = 2
 * Output: 45
 */
public class RectangleArea {

    public static void main(String []args) {
        int A = -3, B = 0, C = 3, D = 4, E = 0, F = -1, G = 9, H = 2;
        computeArea(A, B, C, D, E, F, G, H);
    }

    public static int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {

        int[] r1p1 = {A, B};
        int[] r1p2 = {C, D};

        int[] r2p1 = {E, F};
        int[] r2p2 = {G, H};

        /**
         Find total area
         **/
        int r1Area = (r1p2[0] - r1p1[0]) * (r1p2[1] - r1p1[1]); // (C-A) * (D-B)
        int r2Area = (r2p2[0] - r2p1[0]) * (r2p2[1] - r2p1[1]); // (G-E) * (H-F)

        int totalArea = r1Area + r2Area;
        // System.out.println("Total : " + totalArea);

        //No overlapping area
        if (r2p1[0] > r1p2[0] || r1p1[0] > r2p2[0]) // E > C || A > G || F > D
            return totalArea;


        int overlappingAreaX = Math.min(r1p2[0], r2p2[0]) - Math.max(r1p1[0], r2p1[0]); // (C,G) - (A,E)
        // System.out.println("X : " + overlappingAreaX);

        int overlappingAreaY = Math.min(r1p2[1], r2p2[1]) - Math.max(r1p1[1], r2p1[1]); // (D,H) - (B,F)
        // System.out.println("Y : " + overlappingAreaY);

        int overlappingArea = 0;

        if (overlappingAreaX > 0 & overlappingAreaY > 0)
            overlappingArea = overlappingAreaX * overlappingAreaY;

        // System.out.println("coverArea : " + overlappingArea);
        return totalArea - overlappingArea;


    }


}
