package Java.companyWise.Amazon.amazonGermany;

import java.util.*;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-04
 * Description:
 */
public class RemoveObstacleRobotMinimumDistance {
    public static void main(String[] args) {

        int numRows = 3, numColumns = 3;

//        List<List<Integer>> lot = new ArrayList<>(numRows);
//
//        lot.add(Arrays.asList(1, 0, 0));
//        lot.add(Arrays.asList(1, 0, 0));
//        lot.add(Arrays.asList(1, 9, 1));

//
//        List<List<Integer>> lot = new ArrayList<>(numRows);
//
//        lot.add(Arrays.asList(1, 1, 1));
//        lot.add(Arrays.asList(1, 0, 1));
//        lot.add(Arrays.asList(1, 9, 1));
////
//        List<List<Integer>> lot = new ArrayList<>(numRows);
//
//        lot.add(Arrays.asList(1, 1, 1));
//        lot.add(Arrays.asList(0, 1, 1));
//        lot.add(Arrays.asList(1, 9, 1));


//        List<List<Integer>> lot = new ArrayList<>(numRows);
//
//        lot.add(Arrays.asList(1, 1, 1));
//        lot.add(Arrays.asList(1, 0, 1));
//        lot.add(Arrays.asList(1, 0, 9));


        List<List<Integer>> lot = new ArrayList<>(numRows);

        lot.add(Arrays.asList(1, 1, 9));
        lot.add(Arrays.asList(1, 1, 1));
        lot.add(Arrays.asList(1, 1, 1));


        System.out.println(removeObstacle(numRows, numColumns, lot));


    }


    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    static int removeObstacle(int numRows, int numColumns, List<List<Integer>> lot) {

        if (lot == null || lot.isEmpty())
            return 0;


        int dest[] = find(lot);

        if (dest == null)
            return -1;


        boolean visited[][] = new boolean[numRows][numColumns];


        visited[0][0] = true;

        int row[] = {0, 0, -1, 1};
        int col[] = {-1, 1, 0, 0};

        return removeObstacle(lot, numRows, numColumns, dest[0], dest[1], row, col, visited);


    }

    private static int removeObstacle(List<List<Integer>> lot, int R, int C, int di, int dj, int row[], int col[], boolean visited[][]) {

        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{0, 0, 0});

        while (!queue.isEmpty()) {

            int s[] = queue.poll();
            visited[s[0]][s[1]] = true;

            for (int k = 0; k < row.length; k++) {

                int r = s[0] + row[k];
                int c = s[1] + col[k];

                if (isSafe(r, c, R, C, lot, visited)) {

                    if (di == r && dj == c)
                        return s[2] + 1;


                    queue.offer(new int[]{r, c, s[2] + 1});

                }

            }
        }

        return -1;


    }


    static boolean isSafe(int r, int c, int n, int m, List<List<Integer>> lot, boolean visited[][]) {

        if (r >= 0 && r < n && c >= 0 && c < m && (lot.get(r).get(c) == 1 || lot.get(r).get(c) == 9) && !visited[r][c])
            return true;
        return false;
    }

    static int[] find(List<List<Integer>> lot) {

        for (int i = 0; i < lot.size(); i++) {

            for (int j = 0; j < lot.get(i).size(); j++) {

                if (lot.get(i).get(j) == 9)
                    return new int[]{i, j};
            }
        }

        return null;
    }

}
