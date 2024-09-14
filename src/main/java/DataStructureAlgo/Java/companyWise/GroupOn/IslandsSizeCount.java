package DataStructureAlgo.Java.companyWise.GroupOn;

/**
 * Author: Nitin Gupta
 * Date: 26/04/19
 * Description: Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume water all surrounds all four edges of the grid.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * 11110
 * 11010
 * 11000
 * 00000
 * <p>
 * Output: 1
 * Example 2:
 * <p>
 * Input:
 * 11000
 * 11000
 * 00100
 * 00011
 * <p>
 * Output: 3
 */
public class IslandsSizeCount {


    static int[] row = {-1, -1, -1, 0, 0, 1, 1, 1};//digonal,row,col
    static int[] col = {-1, 0, 1, -1, 1, -1, 0, 1};

    static class Islands {
        public int count;
        public int size;
        public int currentSize = 0;

        @Override
        public String toString() {
            return "IslandsSizeCount{" +
                    "count=" + count +
                    ", size=" + size +

                    '}';
        }
    }

    public static void main(String []args) {
        int[][] mat = { {1, 0, 0, 0, 1, 0},
                        {0, 1, 0, 0, 1, 0},
                        {0, 0, 1, 1, 0, 0},
                        {0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0, 1},
                        {0, 1, 1, 0, 1, 0}};

        Islands islands = islands(mat);
        System.out.println(islands);
    }

    private static Islands islands(int[][] mat) {

        if (mat == null || mat.length == 0)
            return null;

        int n = mat.length;
        int m = mat[0].length;

        return countIslands(mat, n, m);
    }

    private static Islands countIslands(int[][] mat, int n, int m) {

        Islands islands = new Islands();

        boolean[][] marked = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (!marked[i][j] && mat[i][j] == 1) {

                    islands.currentSize = 1;
                    countIslands(mat, n, m, i, j, islands, marked);

                    //count how many are there
                    islands.count++;

                    //find the max size
                    islands.size = Math.max(islands.currentSize, islands.size);
                }
            }
        }


        return islands;
    }


    /**
     * find all the connected island, and its size from given island point r and c
     *
     * @param mat
     * @param n
     * @param m
     * @param r
     * @param c
     * @param islands
     * @param marked
     */
    private static void countIslands(int[][] mat, int n, int m, int r, int c, Islands islands, boolean[][] marked) {


        marked[r][c] = true;

        for (int dir = 0; dir < row.length; dir++) {
            int newRow = row[dir] + r;
            int newCol = col[dir] + c;

            if (isSafe(mat, n, m, newRow, newCol, marked)) {
                islands.currentSize++;
                countIslands(mat, n, m, newRow, newCol, islands, marked);
            }
        }
    }

    private static boolean isSafe(int[][] mat, int n, int m, int r, int c, boolean[][] marked) {

        return r >= 0 && c >= 0 && r < n && c < m && !marked[r][c] && mat[r][c] == 1;

    }

}
