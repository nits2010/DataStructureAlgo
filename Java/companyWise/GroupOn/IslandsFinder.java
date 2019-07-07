package Java.companyWise.GroupOn;

/**
 * Author: Nitin Gupta
 * Date: 26/04/19
 * Description:
 */
public class IslandsFinder {


    static int row[] = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int col[] = {-1, 0, 1, -1, 1, -1, 0, 1};

    static class Islands {
        public int count;
        public int size;
        public int currentSize = 0;

        @Override
        public String toString() {
            return "IslandsFinder{" +
                    "count=" + count +
                    ", size=" + size +

                    '}';
        }
    }

    public static void main(String args[]) {
        int mat[][] = {{1, 0, 0, 0, 1, 0}, {0, 1, 0, 0, 1, 0}, {0, 0, 1, 1, 0, 0}, {0, 0, 0, 0, 0, 1}, {0, 0, 0, 0, 0, 1}, {0, 1, 1, 0, 1, 0}};
        Islands islands = islands(mat);
        System.out.println(islands);
    }

    private static Islands islands(int mat[][]) {

        if (mat == null || mat.length == 0)
            return null;

        int n = mat.length;
        int m = mat[0].length;

        return countIslands(mat, n, m);
    }

    private static Islands countIslands(int[][] mat, int n, int m) {

        Islands islands = new Islands();

        boolean marked[][] = new boolean[n][m];
        //Arrays.fill(marked, false);

        //int size = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (!marked[i][j] && mat[i][j] == 1) {

                    islands.currentSize = 1;
                    countIslands(mat, n, m, i, j, islands, marked);
                    islands.count++;
                    int max = Math.max(islands.currentSize, islands.size);
                    islands.size = max;
                }
            }
        }


        return islands;
    }


    private static void countIslands(int[][] mat, int n, int m, int r, int c, Islands islands, boolean marked[][]) {


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

        if (r >= 0 && c >= 0 && r < n && c < m && !marked[r][c] && mat[r][c] == 1)
            return true;
        return false;

    }

}
