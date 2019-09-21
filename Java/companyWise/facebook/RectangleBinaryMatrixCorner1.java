package Java.companyWise.facebook;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-16
 * Description: https://www.geeksforgeeks.org/find-rectangle-binary-matrix-corners-1/
 * Find if there is a rectangle in binary matrix with corners as 1
 * <p>
 * There is a given binary matrix, we need to find if there exists any rectangle or square in the given matrix
 * whose all four corners are equal to 1.
 * <p>
 * Examples:
 * <p>
 * Input :
 * mat[][] = {   1 0 0 1 0
 * *             0 0 1 0 1
 * *             0 0 0 1 0
 * *             1 0 1 0 1}
 * Output : Yes
 * as there exists-
 * 1 0 1
 * 0 1 0
 * 1 0 1
 */
public class RectangleBinaryMatrixCorner1 {

    public static void main(String []args) {
        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
        test7();
    }

    private static void test7() {

        int mat[][] = {
                {0, 0, 0, 1, 0},
                {0, 0, 1, 0, 1},
                {0, 0, 0, 1, 0},
                {1, 0, 0, 0, 1}
        };

        System.out.println("Brute:" + rectangleAll1BruteForce(mat));
        System.out.println("Optimized:" + rectangleAll1Optimized(mat));

    }

    private static void test6() {
        int mat[][] = {
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}

        };

        System.out.println("Brute:" + rectangleAll1BruteForce(mat));
        System.out.println("Optimized:" + rectangleAll1Optimized(mat));

    }


    private static void test5() {
        int mat[][] = {
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1}

        };

        System.out.println("Brute:" + rectangleAll1BruteForce(mat));
        System.out.println("Optimized:" + rectangleAll1Optimized(mat));

    }


    private static void test4() {
        int mat[][] = {
                {1, 0, 0, 0, 1},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {1, 0, 0, 0, 1}
        };

        System.out.println("Brute:" + rectangleAll1BruteForce(mat));
        System.out.println("Optimized:" + rectangleAll1Optimized(mat));
    }

    private static void test3() {
        int mat[][] = {
                {1, 0, 1, 1, 0},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 1, 0},
                {1, 0, 1, 0, 1}
        };
        System.out.println("Brute:" + rectangleAll1BruteForce(mat));
        System.out.println("Optimized:" + rectangleAll1Optimized(mat));

    }

    private static void test2() {

        int mat[][] = {
                {0, 0, 0, 1, 0},
                {0, 0, 1, 0, 1},
                {0, 0, 0, 1, 0},
                {1, 0, 1, 0, 1}
        };

        System.out.println("Brute:" + rectangleAll1BruteForce(mat));
        System.out.println("Optimized:" + rectangleAll1Optimized(mat));

    }

    private static void test1() {

        int mat[][] = {
                {1, 0, 0, 1, 0},
                {0, 0, 1, 0, 1},
                {0, 0, 0, 1, 0},
                {1, 0, 1, 0, 1}
        };

        System.out.println("Brute:" + rectangleAll1BruteForce(mat));
        System.out.println("Optimized:" + rectangleAll1Optimized(mat));

    }


    /**
     * O(n^4)
     *
     * @param mat
     * @return
     */
    private static boolean rectangleAll1BruteForce(int mat[][]) {

        for (int top = 0; top < mat.length; top++) {

            for (int left = 0; left < mat[0].length; left++) {

                if (mat[top][left] == 1) { // top left  has 1

                    for (int bottom = top + 1; bottom < mat.length; bottom++) {

                        if (mat[bottom][left] == 1) { // bottom left has 1

                            for (int right = left + 1; right < mat[0].length; right++) {

                                if (mat[top][right] == 1 && mat[bottom][right] == 1) // top right has 1 and bottom right has 1
                                    return true;
                            }
                        }
                    }

                }
            }
        }

        return false;

    }

    private static boolean rectangleAll1Optimized(int mat[][]) {

        int n = mat.length;
        int m = mat[0].length;

        /**
         * for this left, how many right we have seen has 1
         */
        Map<Integer, Set<Integer>> leftRight = new HashMap<>();


        for (int bottom = 0; bottom < n; bottom++) {

            for (int left = 0; left < m; left++) {

                if (mat[bottom][left] == 1) { // bottom left  has 1
                    //Then find all the bottom left and bottom right as well as top right

                    for (int right = left + 1; right < m; right++) {

                        if (mat[bottom][right] == 1) { // bottom right  has 1

                            //Find top left/right

                            /**
                             * Has we seen any top left and top right which has 1;
                             *
                             * for the given left, has we seen right too that has 1
                             */
                            if (leftRight.containsKey(left) && leftRight.get(left).contains(right)) {
                                return true;

                            }

                            /**
                             * Has we seen any top left and top right which has 1;
                             *
                             * for the given right, has we seen left too that has 1
                             */
                            if (leftRight.containsKey(right) && leftRight.get(right).contains(left)) {
                                return true;

                            }


                            /**
                             * if we have not seen any top left and right, then store this as a potential left right
                             * for future bottom left and right
                             */

                            if (leftRight.containsKey(left))
                                leftRight.get(left).add(right);
                            else {
                                Set<Integer> rights = new HashSet<>();
                                rights.add(right);

                                leftRight.put(left, rights);
                            }


                            if (leftRight.containsKey(right))
                                leftRight.get(right).add(left);
                            else {
                                Set<Integer> lefts = new HashSet<>();
                                lefts.add(left);

                                leftRight.put(right, lefts);
                            }
                        }
                    }

                }
            }
        }
        return false;

    }
}
