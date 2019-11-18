package Java.nonleetcode.Tree;

import java.util.Arrays;

/**
 * Author: Nitin Gupta
 * Date: 2019-06-14
 * Description:
 * Binary Indexed Tree or Fenwick Tree
 */
public class BinaryIndexedTreeFenwickTree {


    private static int[] construct(int input[]) {

        int[] biTree = new int[input.length + 1];
        Arrays.fill(biTree, 0);

        for (int i = 0; i < input.length; i++)
            update(biTree, i, input[i]);

        return biTree;

    }

    //O(log n)
    private static int getSum(int[] biTree, int i, int j) {
        int sum = 0;

        if (i > j) {
            int temp = i;
            i = j;
            j = temp;
        }

        if (j > biTree.length - 2 || i < 0)
            return Integer.MIN_VALUE;

        if (i != 0) {
            sum = getSum(biTree, 0, j) - getSum(biTree, 0, i - 1);
        } else {

            int index = j + 1;
            while (index > 0) {

                sum += biTree[index];
                index = getParent(index);
            }

        }
        System.out.println("index i: " + i + " j: " + j + "   sum: " + sum);
        return sum;

    }

    //O(1)
    private static int getParent(int index) {
        return index - (index & -index);
    }

    //O(1)
    private static int getNext(int index) {
        return index + (index & -index);
    }

    //O(log n)
    private static void update(int[] biTree, int input[], int index, int value) {

        if (index >= input.length || index < 0)
            return;

        update(biTree, index, value - input[index]);

    }
    //O(log n)
    private static void update(int[] biTree, int index, int value) {

        index = index + 1; //Bi tree has one index ahead

        while (index < biTree.length) {

            biTree[index] += value;
            index = getNext(index); // index + (index & (2's complement of index );
        }
    }

    public static void main(String []args) {
        int input[] = {1, 2, 3, 4, 5, 6, 7};

        int[] biTree = construct(input);
        getSum(biTree, 0, 3); // 10
        getSum(biTree, 0, 4); // 15
        getSum(biTree, 1, 4); // 14

        System.out.println("update");
        update(biTree, input, 3, 8);  // {1, 2, 3, 8, 5, 6, 7};

        getSum(biTree, 0, 3); // 14
        getSum(biTree, 1, 4); // 18
    }
}
