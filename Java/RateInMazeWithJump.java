package Java;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 20/12/18
 * Description: https://www.geeksforgeeks.org/rat-in-a-maze-with-multiple-steps-jump-allowed/
 */
public class RateInMazeWithJump {

    public static void main(String args[]) {

        int maze[][] = {{2, 1, 0, 0},
                {3, 0, 0, 1},
                {0, 1, 0, 1},
                {0, 0, 0, 1}};

        int solution[][] = {{1, 0, 0, 0},
                {1, 0, 0, 1},
                {0, 0, 0, 1},
                {0, 0, 0, 1}};

        int n = maze.length, m = maze[0].length;

        int result[][] = rateInMazeWithJump(maze, 0, 0, n - 1, m - 1);

        System.out.println(isCorrect(result, solution));


    }


    private static boolean isCorrect(int result[][], int solution[][]) {
        for (int i = 0; i < result.length; i++) {

            for (int j = 0; j < result[0].length; j++)
                if (result[i][j] != solution[i][j])
                    return false;
        }

        return true;
    }

    private static int[][] rateInMazeWithJump(int[][] maze, int sx, int sy, int dx, int dy) {


        int result[][] = new int[maze.length][maze[0].length];

        rateInMazeWithJump(maze, sx, sy, dx, dy, result);
        return result;


    }


    private static boolean rateInMazeWithJump(int[][] maze, int sx, int sy, int dx, int dy, int[][] result) {

        //We reached the destination
        if (sx == dx && sy == dy) {
            result[sx][sy] = 1;
            return true;
        }

        //no more element to process
        if (sx >= maze.length || sy >= maze[0].length) {
            return false;
        }


        //Check if its safe to reach this cell
        if (isSafe(maze, sx, sy, result)) {

            //try this cell to destination
            result[sx][sy] = 1;

            //try all cells from this cell to destination

            // try from 1 jump to number of jump defined by maze[sx][sy]
            for (int i = 1; i <= maze[sx][sy]; i++) {


                //try right cells;
                if (rateInMazeWithJump(maze, sx + i, sy, dx, dy, result))
                    return true;

                //if not success then try down

                if (rateInMazeWithJump(maze, sx, sy + i, dx, dy, result))
                    return true;

            }

            //backtrack to other cells if not reached destination
            result[sx][sy] = 0;
            return false;


        }
        return false;

    }

    private static boolean isSafe(int[][] maze, int sx, int sy, int result[][]) {

        if (sx <= maze.length && sy <= maze[0].length && result[sx][sy] == 0)
            return true;
        return false;
    }


}
