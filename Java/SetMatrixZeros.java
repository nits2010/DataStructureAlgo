package Java;

import java.util.*;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 12/04/19
 * Description: https://www.careercup.com/question?id=5763748409638912
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
 */

public class SetMatrixZeros {

    static class DegreeNode {
        int i, j;
        int degree;

        public DegreeNode(int i, int j, int degree) {
            this.i = i;
            this.j = j;
            this.degree = degree;
        }
    }


    public static void test(char matrix[][]) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.println();
            for (int j = 0; j < matrix[0].length; j++)
                System.out.print(matrix[i][j] + " ");
        }

        System.out.println("\n\noutput");
        int count = 0;
        char result[][] = setMatrixZero(matrix);
        for (int i = 0; i < result.length; i++) {
            System.out.println();
            for (int j = 0; j < result[0].length; j++) {
                if (result[i][j] == 'O')
                    count++;
                System.out.print(result[i][j] + " ");
            }
        }
        System.out.println("\ncount :" + count);

    }

    public static void main(String args[]) {
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

    private static char[][] setMatrixZero(char[][] matrix) {
        Map<Integer, Set<Integer>> row = new HashMap<>();
        Map<Integer, Set<Integer>> col = new HashMap<>();

        int m = matrix.length;
        int n = matrix[0].length;
        //Build graph
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 'O') {
                    Set<Integer> redges = row.getOrDefault(i, new HashSet<>());
                    redges.add(j);
                    row.put(i, redges);

                    Set<Integer> cedges = col.getOrDefault(j, new HashSet<>());
                    cedges.add(i);
                    col.put(j, cedges);
                }

            }
        }

        PriorityQueue<DegreeNode> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.degree));

        //Count degree
        for (int i = 0; i < m; i++) {
            for (Integer r : row.getOrDefault(i, new HashSet<>())) {
                int degree = row.get(i).size() + col.getOrDefault(r, new HashSet<>()).size() - 2;
                if (degree > 0)
                    priorityQueue.offer(new DegreeNode(i, r, degree));
            }
        }

        int index;
        //Delete till every node has zero degree except last node
        while (true) {

            DegreeNode node = priorityQueue.poll();

            //decrease the degree
            node.degree--;

            //if it has more degree, then push it to process further
            if (node.degree != 0)
                priorityQueue.offer(node);
            else {
                //If all other node degree is 0? in that case only one node has more than 0 degree
                if (priorityQueue.size() <= 1) {
                    break;
                }
            }

            //Find and remove the degree
            Set<Integer> rowsEdges = row.getOrDefault(node.i, new HashSet<>());
            Set<Integer> colEdges = col.getOrDefault(node.j, new HashSet<>());


            rowsEdges.remove(node.i);
            if (rowsEdges.size() == 0)
                row.remove(node.i);


            colEdges.remove(node.j);
            if (colEdges.size() == 0)
                col.remove(node.j);

            matrix[node.i][node.j] = 'X';

        }


        return matrix;
    }


}
