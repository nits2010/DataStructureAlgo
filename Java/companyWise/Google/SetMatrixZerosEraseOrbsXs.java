package Java.companyWise.Google;

import Java.nonleetcode.graph.questions.TopologicalSorts;
import javafx.util.Pair;

import java.util.*;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 12/04/19
 * Description: https://www.careercup.com/question?id=5763748409638912
 * <p>
 * There are orbs on an NxM board ('O' denotes orb. 'X' denotes empty slot).
 * <p>
 * Whenever two orbs are in the same column or the same row, you get to erase one of them.
 * <p>
 * Try to erase as many orbs as possible.
 * <p>
 * Find the minimum number of orbs remained on board eventually.
 * e.g.
 * <p>
 * OXOXXO
 * XXXXXO
 * XXXXOX
 * erase (0,0) and get
 * XXOXXO
 * XXXXXO
 * XXXXOX
 * erase (2,0) and get
 * XXXXXO
 * XXXXXO
 * XXXXOX
 * erase (5,0) and get
 * XXXXXX
 * XXXXXO
 * XXXXOX
 * no more moves available. Return 2 e.g.
 * <p>
 * OXOXXO
 * XXXXXO
 * XXOXOX
 * erase(4,2), (2,2), (0,0), (2,0), (5,0)
 * <p>
 * Return 1
 * e.g.
 * OXOXXX
 * XOXXXO
 * XXXOOX
 * <p>
 * erase(0,0), (1,1), (3,2)
 * <p>
 * Return 3
 * <p>
 * [Google]
 * <p>
 * {@link Java.LeetCode.SetMatrixZeroes}
 * *
 */

public class SetMatrixZerosEraseOrbsXs {

    static class DegreeNode {
        int i, j;
        int degree;

        public DegreeNode(int i, int j, int degree) {
            this.i = i;
            this.j = j;
            this.degree = degree;
        }
    }


    private static void print(char matrix[][]) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.println();
            for (int j = 0; j < matrix[0].length; j++)
                System.out.print(matrix[i][j] + " ");
        }

    }

    private static int count(char result[][]) {
        int count = 0;
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                if (result[i][j] == 'O')
                    count++;
            }
        }
        return count;
    }

    public static void test(char matrix[][]) {

        System.out.println("Testing...");
        print(matrix);
        System.out.println("\n\noutput");


        char result[][] = eraseOrbs(matrix);
        int count = count(result);
        print(result);

        System.out.println("\ncount :" + count);

    }

    public static void main(String []args) {
        char matrix[][] = {{'X', 'X', 'X', 'X', 'X', 'O'},
                {'O', 'X', 'X', 'X', 'X', 'X'},
                {'O', 'X', 'X', 'X', 'X', 'O'},
                {'X', 'X', 'O', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X', 'X', 'O'}};
        test(matrix);

        char matrix1[][] = {{'O', 'X', 'O', 'X', 'X'},
                {'X', 'O', 'X', 'X', 'X', 'O'},
                {'X', 'X', 'X', 'O', 'O', 'X'}};

        test(matrix1);

        char matrix2[][] = {{'O', 'X', 'O', 'X', 'O'},
                {'X', 'X', 'X', 'X', 'X', 'O'},
                {'X', 'X', 'O', 'X', 'O', 'X'}};

        test(matrix2);

        char matrix3[][] = {{'O', 'X', 'O', 'X', 'X', 'O'},
                {'X', 'X', 'X', 'X', 'X', 'O'},
                {'X', 'X', 'X', 'X', 'O', 'X'}};

        test(matrix3);


    }


    /**
     * Similar like khans topological sort;
     * we first build the graph from given matrix s.t. there is edge from a row to col where an orbs found (undirected graph)
     * then after we calculate degree of each orbs
     * then run topological sort till there is 1 or left whose degree is more than 0
     * <p>
     * {@link TopologicalSorts}
     *
     * @param matrix
     * @return
     */
    private static char[][] eraseOrbs(char[][] matrix) {


        Pair<Map<Integer, Set<Integer>>, Map<Integer, Set<Integer>>> mapMapPair = buildGraphAndEdges(matrix);
        Map<Integer, Set<Integer>> row = mapMapPair.getKey();
        Map<Integer, Set<Integer>> col = mapMapPair.getValue();


        //Count degree
        PriorityQueue<DegreeNode> priorityQueue = getDegreeInPQ(matrix.length, row, col);


        //Delete till every node has zero degree except last node
        while (!priorityQueue.isEmpty()) {

            DegreeNode node = priorityQueue.peek();

            //decrease the degree
            node.degree--;

            //if it has 0 degree, then pop it to not to process further
            if (node.degree == 0)
                priorityQueue.poll();
            else {
                //If all other node degree is not 0? in that case only one node has more than 0 degree
                if (priorityQueue.size() <= 1) {
                    break;
                }
            }

            //Find and remove the degree
            if (!row.isEmpty() && row.containsKey(node.i) && row.get(node.i).contains(node.j)) {
                row.get(node.i).remove(node.j);
                if (row.get(node.i).size() == 0)
                    row.remove(node.i);
            }

            if (!col.isEmpty() && col.containsKey(node.j) && col.get(node.j).contains(node.i)) {
                col.get(node.j).remove(node.i);
                if (col.get(node.j).size() == 0)
                    col.remove(node.j);
            }

            matrix[node.i][node.j] = 'X';

        }


        return matrix;
    }


    /**
     * This will count for any given row, how many orbs(o) are there, we call them connected components
     * similarly for columns
     * i->(j's) and
     * j -> (i's)
     *
     * @param matrix
     * @return
     */
    private static Pair<Map<Integer, Set<Integer>>, Map<Integer, Set<Integer>>> buildGraphAndEdges(char[][] matrix) {
        Map<Integer, Set<Integer>> row = new HashMap<>();
        Map<Integer, Set<Integer>> col = new HashMap<>();


        int rows = matrix.length;
        int cols = matrix[0].length;


        //Build graph
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 'O') {

                    Set<Integer> rowEdges = row.getOrDefault(i, new HashSet<>());
                    rowEdges.add(j);
                    row.put(i, rowEdges);

                    Set<Integer> colEdges = col.getOrDefault(j, new HashSet<>());
                    colEdges.add(i);
                    col.put(j, colEdges);
                }

            }
        }
        return new Pair<>(row, col);
    }

    /**
     * @param rows
     * @param row
     * @param col
     * @return
     */
    private static PriorityQueue<DegreeNode> getDegreeInPQ(int rows, Map<Integer, Set<Integer>> row, Map<Integer, Set<Integer>> col) {
        PriorityQueue<DegreeNode> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.degree));

        //Count degree
        for (int i = 0; i < rows; i++) {
            for (Integer r : row.getOrDefault(i, new HashSet<>())) {
                int degree = row.get(i).size() + col.getOrDefault(r, new HashSet<>()).size() - 2;
                if (degree > 0)
                    priorityQueue.offer(new DegreeNode(i, r, degree));
            }
        }

        return priorityQueue;
    }

}
