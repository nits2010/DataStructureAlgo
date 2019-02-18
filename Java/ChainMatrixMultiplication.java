package Java;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 11/12/18
 * Description:
 * <p>
 * Matrix chain multiplication without memorization;
 * M[i][j] is the minimum operation required to multiply the matrix defined by <p> from i to j
 * <p>
 * M[i][j] = 0 ; if i == j => No operation required to multiply the same matrix
 * *       = minimum ( M[i][k] + M[k+1][j] + p[i-1]*p[k]+p[j] ) for any k from i to j.
 */


public class ChainMatrixMultiplication {


    static int minimumOperationRec(final int p[], int i, int j) {

        if (i == j) {
            return 0;
        }

        int k; // range from i to j
        int minimumOperation = Integer.MAX_VALUE;

        //for all such k
        // dimension of A[i] is p[i-1] x p[i]
        for (k = i; k < j; k++) {

            minimumOperation = Math.min
                    (minimumOperation,
                            minimumOperationRec(p, i, k)
                                    + minimumOperationRec(p, k + 1, j)
                                    + p[i - 1] * p[k] * p[j]);


        }


        return minimumOperation;
    }


    static int minimumOperationDP(final int p[]) {

        int length = p.length;
        int minimumOpeation[][] = new int[length][length];

        //0 ; if i == j => No operation required to multiply the same matrix
        for (int i = 1; i < length; i++)
            minimumOpeation[i][i] = 0;

        int i, j, k;

        //For all the multiplication of length >=2
        for (int len = 2; len < length; len++) {


            //For any length, in distance of "len" there will be length-len + 1 matrix
            for (i = 1; i < length - len + 1; i++) {

                // J should point the last element of len; means len apart from i
                j = i + len - 1;

                minimumOpeation[i][j] = Integer.MAX_VALUE;

                // M[i][j] = minimum ( M[i][k] + M[k+1][j] + p[i-1]*p[k]+p[j] ) for any k from i to j.
                for (k = i; k < j; k++) {

                    minimumOpeation[i][j] = Math.min(minimumOpeation[i][j],
                            minimumOpeation[i][k] + minimumOpeation[k + 1][j] + p[i - 1] * p[k] * p[j]);
                }
            }
        }

        return minimumOpeation[1][length - 1];
    }


    public static void main(String[] args) {
        int dimention1[] = {40, 20, 30, 10, 30};
        int dimention2[] = {10, 20, 30, 40, 30};
        int dimention3[] = {10, 20, 30};


        //Using Recursion; Without Dp
        System.out.print("Running\n");
        System.out.println(minimumOperationRec(dimention1, 1, dimention1.length - 1) == 26000);
        System.out.println(minimumOperationRec(dimention2, 1, dimention2.length - 1) == 30000);
        System.out.println(minimumOperationRec(dimention3, 1, dimention3.length - 1) == 6000);

        //Using MEMORIZATION; WITH Dp
        System.out.print("Running\n");
        System.out.println(minimumOperationDP(dimention1) == 26000);
        System.out.println(minimumOperationDP(dimention2) == 30000);
        System.out.println(minimumOperationDP(dimention3) == 6000);


    }

}
