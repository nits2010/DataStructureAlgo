package Java.nonleetcode;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-10
 * Description:https://www.careercup.com/question?id=5664498449907712
 * <p>
 * there is a 2d array and gbikes are located in that location. there is a person and he wants to know the nearest
 * location of the bike which is available for him(there can be more than 1 nearest bike).
 * person can only move left ,right , up or down. output should be the distance in int.
 */
public class ClosestBike {

    // bikes are 1s in the parkingLot

    /**
     * search the possible bikes and take the minimum distance [ row - mansRow + col - mansCol ]
     *
     * @param parkingLot
     * @param manPosRow
     * @param manPosCol
     * @return
     */
    public static int calculateDistanceBrute(int[][] parkingLot, int manPosRow, int manPosCol) {
        int minDistance = Integer.MAX_VALUE;

        // the man is standing outside the parking lot. Can't reach any bike.
        if (manPosRow > parkingLot.length - 1 || manPosCol > parkingLot[0].length - 1)
            return minDistance;

        for (int row = 0; row < parkingLot.length; row++) {
            for (int col = 0; col < parkingLot[0].length; col++) {
                if (parkingLot[row][col] == 1) {
                    minDistance = Math.min(minDistance, (Math.abs(row - manPosRow) + Math.abs(col - manPosCol)));
                }
            }
        }

        return minDistance;
    }

    /**
     * DFS of given array
     *
     * @param parkingLot
     * @param manPosRow
     * @param manPosCol
     * @return
     */
    static int row[] = {-1, 1, 0, 0};
    static int col[] = {0, 0, -1, 1};

    public static int calculateDistanceBleed(int[][] parkingLot, int manPosRow, int manPosCol) {
        // the man is standing outside the parking lot. Can't reach any bike.
        int minDistance = Integer.MAX_VALUE - 1;
        if (manPosRow > parkingLot.length - 1 || manPosCol > parkingLot[0].length - 1)
            return minDistance;

        // The man is sitting on a bike
        if (parkingLot[manPosRow][manPosCol] == 1)
            return 0;

        // Mark the parkingLot location as visited
        parkingLot[manPosRow][manPosCol] = -1;


        for (int i = 0; i < row.length; i++) {
            int r = manPosRow + row[i];
            int c = manPosCol + col[i];

            if (r >= 0 && r < parkingLot.length && c >= 0 && c < parkingLot[0].length && parkingLot[r][c] != -1) {
                minDistance = Math.min(minDistance, 1 + calculateDistanceBleed(parkingLot, r, c));
            }


        }
        return minDistance;
    }


    /**
     * BFS; this can be done using queue also, try nearest spots first then not found, the increase the distance
     * max distance can be complete parking lot size.
     * <p>
     * This algorithm, build a circle of 'minDistance' radius and try to check all the points of this circle
     *
     * @param parkingLot
     * @param manPosRow
     * @param manPosCol
     * @return
     */
    public static int calculateDistanceBetter(int[][] parkingLot, int manPosRow, int manPosCol) {
        if (manPosRow > parkingLot.length - 1 || manPosCol > parkingLot[0].length - 1)
            return Integer.MAX_VALUE;

        // The man is already sitting on a bike. No need to check further.
        if (parkingLot[manPosRow][manPosCol] == 1)
            return 0;

        int minDistance = 1;

        while (minDistance < parkingLot.length + parkingLot[0].length) {
            for (int i = 0; i < minDistance; i++) { //find all the points in this circle with radius minDistance

                if (manPosRow + i < parkingLot.length && manPosCol + (minDistance - i) < parkingLot[0].length) {

                    // check SE
                    if (parkingLot[manPosRow + i][manPosCol + (minDistance - i)] == 1)
                        return minDistance;
                }

                if (manPosRow + i < parkingLot.length && manPosCol - (minDistance - i) > 0) {
                    // check SW
                    if (parkingLot[manPosRow + i][manPosCol - (minDistance - i)] == 1)
                        return minDistance;
                }

                if (manPosRow - i > 0 && manPosCol + (minDistance - i) < parkingLot[0].length) {
                    // check NE
                    if (parkingLot[manPosRow - i][manPosCol + (minDistance - i)] == 1)
                        return minDistance;
                }

                if (manPosRow - i > 0 && manPosCol - (minDistance - i) > 0) {
                    // check NW
                    if (parkingLot[manPosRow - i][manPosCol - (minDistance - i)] == 1)
                        return minDistance;
                }
            }
            minDistance++;
        }

        return Integer.MAX_VALUE;
    }

    public static void main(String[] args) {
        int[][] parkingLot = new int[][]{
                {0, 1, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 1}
        };

        System.out.println("Cal min distance: " + calculateDistanceBrute(parkingLot, 7, 0));
        System.out.println("Cal min distance: " + calculateDistanceBetter(parkingLot, 7, 0));
        System.out.println("Cal min distance: " + calculateDistanceBleed(parkingLot, 7, 0));

        System.out.println("Parking lot status: (-1 is where the man walked)");
        for (int i = 0; i < parkingLot.length; i++) {
            for (int j = 0; j < parkingLot[0].length; j++) {
                System.out.print(parkingLot[i][j] + " ");
            }
            System.out.println("");
        }
    }
}