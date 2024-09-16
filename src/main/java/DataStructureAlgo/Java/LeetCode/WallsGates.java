package DataStructureAlgo.Java.LeetCode;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-15
 * Description: https://leetcode.ca/all/286.html
 * 286.Walls and Gates
 * <p>
 * You are given a m x n 2D grid initialized with these three possible values.
 * -1 - A wall or an obstacle.
 * 0 - A gate.
 * INF - Infinity means an empty room. We use the value 2^31 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
 * <p>
 * Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
 * For example, given the 2D grid:
 * INF  -1  0  INF
 * INF INF INF  -1
 * INF  -1 INF  -1
 * 0  -1 INF INF
 * After running your function, the 2D grid should be:
 * 3  -1   0   1
 * 2   2   1  -1
 * 1  -1   2  -1
 * 0  -1   3   4
 * <p>
 * http://buttercola.blogspot.com/2015/09/leetcode-walls-and-gates.html
 */


public class WallsGates {

    final static int INF = Integer.MAX_VALUE;

    public static void main(String []args) {
        testBFS(new int[][]{{INF, -1, 0, INF,}, {INF, INF, INF, -1}, {INF, -1, INF, -1}, {0, -1, INF, INF}});
        testDFS(new int[][]{{INF, -1, 0, INF,}, {INF, INF, INF, -1}, {INF, -1, INF, -1}, {0, -1, INF, INF}});

    }

    private static void testDFS(int room[][]) {

        System.out.println("\nGiven room");
        System.out.println(CommonMethods.toString(room));

        WallsGatesDFS dfs = new WallsGatesDFS();
        dfs.wallsAndGates(room);

        System.out.println("\nOutput room");
        System.out.println(CommonMethods.toString(room));


    }


    private static void testBFS(int[][] room) {

        System.out.println("\nGiven room");
        System.out.println(CommonMethods.toString(room));

        WallsGatesBFS bfs = new WallsGatesBFS();
        bfs.wallsAndGates(room);

        System.out.println("\nOutput room");
        System.out.println(CommonMethods.toString(room));


    }


}

class WallsGatesDFS {


    static int[] row = {0, 0, 1, -1};
    static int[] col = {1, -1, 0, 0};

    public void wallsAndGates(int[][] rooms) {

        if (null == rooms || rooms.length == 0 || rooms[0].length == 0)
            return;

        int n = rooms.length;
        int m = rooms[0].length;

        boolean[][] visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (!visited[i][j] && rooms[i][j] == 0) //if this is a gate
                    dfs(rooms, visited, i, j, 0);
            }
        }
    }

    private void dfs(int[][] rooms, boolean[][] visited, int i, int j, int distance) {

        if (!isSafe(rooms, visited, i, j, distance))
            return;

        if (distance < rooms[i][j]) //if current INF gives less distance, then use it. Keep in mind,
            // this will never become true if this cell is a gate as 0 < 0 is false
            rooms[i][j] = distance;

        visited[i][j] = true; //visit this cell

        for (int k = 0; k < row.length; k++) {

            int r = row[k] + i;
            int c = col[k] + j;

            if (isSafe(rooms, visited, r, c, distance + 1)) {
                dfs(rooms, visited, r, c, distance + 1);
            }
        }
        visited[i][j] = false;
    }

    private boolean isSafe(int[][] rooms, boolean[][] visited, int i, int j, int distance) {
        return i >= 0 && i < rooms.length && j >= 0 && j < rooms[0].length && !visited[i][j] && rooms[i][j] != -1 && distance <= rooms[i][j];
    }

}


class WallsGatesBFS {


    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0) {
            return;
        }

        int m = rooms.length;
        int n = rooms[0].length;

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) {
                    wallsAndGatesHelper(i, j, 0, rooms, queue);
                }
            }
        }
    }

    private void wallsAndGatesHelper(int row, int col, int distance, int[][] rooms, Queue<Integer> queue) {
        fill(row, col, distance, rooms, queue);

        int n = rooms[0].length;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cord = queue.poll();
                int x = cord / n;
                int y = cord % n;

                fill(x - 1, y, distance + 1, rooms, queue);
                fill(x + 1, y, distance + 1, rooms, queue);
                fill(x, y - 1, distance + 1, rooms, queue);
                fill(x, y + 1, distance + 1, rooms, queue);

            }
            distance++;
        }
    }

    private void fill(int row, int col, int distance, int[][] rooms, Queue<Integer> queue) {
        int m = rooms.length;
        int n = rooms[0].length;

        if (row < 0 || row >= m || col < 0 || col >= n) {
            return;
        }

        if (rooms[row][col] == -1) {
            return;
        }

        if (distance > rooms[row][col]) {
            return;
        }

        if (distance < rooms[row][col]) {
            rooms[row][col] = distance;
        }

        int cord = n * row + col;
        queue.offer(cord);
    }

}