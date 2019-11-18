package Java.companyWise.Amazon;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-04
 * Description: https://aonecode.com/amazon-online-assessment-oa2-move-obstacle
 *
 * You are in charge of preparing a recently purchased lot for Amazon’s building. The lot is covered with trenches and has a single obstacle that needs to be taken down before the foundation is prepared for the building.
 * The demolition robot must remove the obstacle before the progress can be made on the building.
 *
 * Write an algorithm to determine the minimum distance required for the demolition robot to remove the obstacle.
 *
 * Assumptions:
 * • The lot is flat, except the trenches and can be represented by a 2D grid.
 * • The demolition robot must start at the top left corner of the lot, which is always flat, and can move on block up, down, right, left
 * • The demolition robot cannot enter trenches and cannot leave the lot.
 * • The flat areas are indicated by 1, areas with trenches are indicated by 0, and the obstacle is indicated by 9
 *
 * Input
 * The input of the function has 3 arguments: numRows – number of rows
 * numColumns – number of columns
 * lot – 2d grid of integers
 *
 * Output
 * Return an integer that indicated the minimum distance traversed to remove the obstacle else return -1
 *
 * Constraints
 * 1<= numRows, numColumns <= 1000
 *
 * Examples
 * Input:
 * numRows = 3
 * numColumns = 3
 * lot = [
 * [1, 0, 0],
 * [1, 0, 0],
 * [1, 9, 1]]
 *
 * Output:
 * 3
 *
 * Explanation:
 * Starting from the top-left corner, the demolition robot traversed the cells (0,0) -> (1,0)-> (2,0)->(2,1)
 * The robot moves 3 times to remove the obstacle “9”
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
