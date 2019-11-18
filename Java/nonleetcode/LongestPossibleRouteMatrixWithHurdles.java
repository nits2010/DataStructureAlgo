package Java.nonleetcode;

/**
 * Author: Nitin Gupta
 * Date: 15/12/18
 * Description:
 * Question:  https://www.geeksforgeeks.org/longest-possible-route-in-a-matrix-with-hurdles/
 * <p>
 * input:
 * {
 * { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
 * { 1, 1, 0, 1, 1, 0, 1, 1, 0, 1 },
 * { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }
 * };
 * output: 24
 */
public class LongestPossibleRouteMatrixWithHurdles {


    static class Solution {
        public int cost = 0;
        public boolean isDestinationReached = false;

        public Solution(int cost, boolean reached) {
            this.cost = cost;
            this.isDestinationReached = reached;

        }
    }

    /**
     * Return the longest possible route (cost of it)
     *
     * @param input
     * @return : Cost of longest possible route
     * Integer.Min_Value use if no route found
     */
    public static int longestPossibleRouteMatrixWithHurdles(int input[][], int sourceX, int sourceY, int destX, int destY) {
        if (input == null || input.length == 0)
            return Integer.MIN_VALUE;

        int m = input.length;
        int n = input[0].length;

        Solution s = longestPossibleRouteMatrixWithHurdles(input, sourceX, sourceY, destX, destY, m, n, new boolean[m][n]);
        return s.cost;


    }

    private static Solution longestPossibleRouteMatrixWithHurdles(int[][] input, int sourceX, int sourceY, int destX, int destY, int m, int n, boolean visitied[][]) {

        int maxCost = Integer.MIN_VALUE;

        //if we reach to destination
        if (sourceX == destX && sourceY == destY) {
            return new Solution(0, true);
        }

        //if we reach end of the matrix Or beyond the matrix Or its a hurdle or we have already visited this row,col
        if (sourceX < 0 || sourceY < 0 || sourceX >= m || sourceY >= n || input[sourceX][sourceY] == 0 || visitied[sourceX][sourceY]) {
            return new Solution(Integer.MAX_VALUE, false);
        }

        //try other solution
        //visit this row,col
        visitied[sourceX][sourceY] = true;

        //recur in 4 direction

        //Down
        Solution down = longestPossibleRouteMatrixWithHurdles(input, sourceX + 1, sourceY, destX, destY, m, n, visitied);

        //Check have you reached to destination by moving down
        if (down.isDestinationReached) {
            if (down.cost > maxCost)
                maxCost = down.cost;
        }

        //Up
        Solution up = longestPossibleRouteMatrixWithHurdles(input, sourceX - 1, sourceY, destX, destY, m, n, visitied);

        //Check have you reached to destination by moving up
        if (up.isDestinationReached) {
            if (up.cost > maxCost)
                maxCost = up.cost;
        }


        //Right
        Solution right = longestPossibleRouteMatrixWithHurdles(input, sourceX, sourceY + 1, destX, destY, m, n, visitied);

        //Check have you reached to destination by moving right
        if (right.isDestinationReached) {
            if (right.cost > maxCost)
                maxCost = right.cost;
        }


        // Left
        Solution left = longestPossibleRouteMatrixWithHurdles(input, sourceX, sourceY - 1, destX, destY, m, n, visitied);

        //Check have you reached to destination by moving left
        if (left.isDestinationReached) {
            if (left.cost > maxCost)
                maxCost = left.cost;
        }


        //mark this cell completed and open it for next iteration -> Backtrack
        visitied[sourceX][sourceY] = false;

        //Check have we even reached to destination
        //Since max Cost only update when we reach destination by either of 4 ways
        if (maxCost != Integer.MIN_VALUE) {
            //We have reached destination
            return new Solution(1 + maxCost, true);
        } else {
            //We have not reached to destination
            return new Solution(maxCost, false);

        }


    }

    public static void main(String[] args) {

        int map[][] = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 0, 1, 1, 0, 1, 1, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };

        System.out.println(longestPossibleRouteMatrixWithHurdles(map, 0, 0, 1, 7) == 24);
    }


}
