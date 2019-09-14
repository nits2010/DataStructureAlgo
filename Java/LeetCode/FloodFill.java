package Java.LeetCode;

import Java.HelpersToPrint.Printer;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 13/09/19
 * Description: https://leetcode.com/problems/flood-fill/
 * 733. Flood Fill [EASY]
 * <p>
 * n image is represented by a 2-D array of integers, each integer representing the pixel value of the image (from 0 to 65535).
 * <p>
 * Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood fill, and a pixel value newColor,
 * "flood fill" the image.
 * <p>
 * To perform a "flood fill", consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the
 * same color as the starting pixel, plus any pixels connected 4-directionally to those pixels (also with the same color as the starting pixel),
 * and so on. Replace the color of all of the aforementioned pixels with the newColor.
 * <p>
 * At the end, return the modified image.
 * <p>
 * Example 1:
 * Input:
 * image = [[1,1,1],[1,1,0],[1,0,1]]
 * sr = 1, sc = 1, newColor = 2
 * Output: [[2,2,2],[2,2,0],[2,0,1]]
 * Explanation:
 * From the center of the image (with position (sr, sc) = (1, 1)), all pixels connected
 * by a path of the same color as the starting pixel are colored with the new color.
 * Note the bottom corner is not colored 2, because it is not 4-directionally connected
 * to the starting pixel.
 * Note:
 * <p>
 * The length of image and image[0] will be in the range [1, 50].
 * The given starting pixel will satisfy 0 <= sr < image.length and 0 <= sc < image[0].length.
 * The value of each color in image[i][j] and newColor will be an integer in [0, 65535].
 *
 * https://www.geeksforgeeks.org/flood-fill-algorithm-implement-fill-paint/
 */
public class FloodFill {

    public static void main(String[] args) {
        test(new int[][]{{1, 1, 1}, {1, 1, 0}, {1, 0, 1}}, 1, 1, 2, new int[][]{{2, 2, 2}, {2, 2, 0}, {2, 0, 1}});
        test(new int[][]{{1, 1, 0}, {1, 1, 0}, {1, 0, 0}}, 1, 1, 2, new int[][]{{2, 2, 0}, {2, 2, 0}, {2, 0, 0}});
        test(new int[][]{{0, 0, 0}, {0, 1, 1}}, 1, 1, 1, new int[][]{{2, 2, 0}, {2, 2, 0}, {2, 0, 0}});

    }

    private static void test(int[][] image, int sr, int sc, int newColor, int[][] expectedImage) {
        System.out.println("\nInput image :\n " + Printer.toString(image) + "(sr,sc)= (" + sc + "," + sc + ")\n Expected Image:\n" + Printer.toString(expectedImage));
        FloodFillDFS dfs = new FloodFillDFS();
        final int[][] newImage = dfs.floodFill(image, sr, sc, newColor);
        System.out.println("\n DFS new Image:\n" + Printer.toString(newImage));
    }
}

/**
 * As we need to run through all the adjacent cells and their adjacent cells, we need to perform DFS.
 * Algo:
 * 1. Find the current color on source cell.
 * 2. Run through in all 4 directions, and check does it has same as old color, if then update with new color. otherwise skip.
 */
class FloodFillDFS {

    /**
     * Runtime: 1 ms, faster than 85.96% of Java online submissions for Flood Fill.
     * Memory Usage: 43.2 MB, less than 57.89% of Java online submissions for Flood Fill.
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {

        if (image == null || image.length == 0 || image[0].length == 0 || sr < 0 || sr >= image.length || sc < 0 || sc >= image[0].length)
            return image;

        final int m = image.length;
        final int n = image[0].length;
        if (image[sr][sc] != newColor)
            dfs(image, sr, sc, newColor, image[sr][sc]);

        return image;
    }

    private void dfs(int[][] image, int i, int j, int newColor, int oldColor) {
        if (i < 0 || i >= image.length || j < 0 || j >= image[0].length || image[i][j] != oldColor)
            return;

        image[i][j] = newColor;


        dfs(image, i + 1, j, newColor, oldColor);
        dfs(image, i - 1, j, newColor, oldColor);
        dfs(image, i, j + 1, newColor, oldColor);
        dfs(image, i, j - 1, newColor, oldColor);
    }
}


/**
 * Same as above, but early checks
 */
class FloodFillDFS2 {

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Flood Fill.
     * Memory Usage: 44.4 MB, less than 52.63% of Java online submissions for Flood Fill.
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {

        if (newColor == image[sr][sc]) {
            return image;
        }
        int cur = image[sr][sc];
        image[sr][sc] = newColor;

        if (sr - 1 >= 0 && image[sr - 1][sc] == cur) {
            floodFill(image, sr - 1, sc, newColor);
        }
        if (sc - 1 >= 0 && image[sr][sc - 1] == cur) {
            floodFill(image, sr, sc - 1, newColor);
        }
        if (sr + 1 < image.length && image[sr + 1][sc] == cur) {
            floodFill(image, sr + 1, sc, newColor);
        }
        if (sc + 1 < image[0].length && image[sr][sc + 1] == cur) {
            floodFill(image, sr, sc + 1, newColor);
        }
        return image;
    }
}