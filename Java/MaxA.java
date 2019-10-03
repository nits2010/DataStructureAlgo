package Java;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-09
 * Description:https://www.geeksforgeeks.org/how-to-print-maximum-number-of-a-using-given-four-keys/
 *
 * https://massivealgorithms.blogspot.com/2017/07/leetcode-651-4-keys-keyboard.html
 *
 * O(1) solution https://leetcode.com/articles/4-keys-keyboard/
 * <p>
 *
 * <p>
 * Below is the problem statement.
 * <p>
 * Imagine you have a special keyboard with the following keys:
 * Key 1:  Prints 'A' on screen
 * Key 2: (Ctrl-A): Select screen
 * Key 3: (Ctrl-C): Copy selection to buffer
 * Key 4: (Ctrl-V): Print buffer on screen appending it
 * after what has already been printed.
 * <p>
 * If you can only press the keyboard for N times (with the above four
 * keys), write a program to produce maximum numbers of A's. That is to
 * say, the input parameter is N (No. of keys that you can press), the
 * output is M (No. of As that you can produce).
 * Examples:
 * <p>
 * Input:  N = 3
 * Output: 3
 * We can at most get 3 A's on screen by pressing
 * following key sequence.
 * A, A, A
 * <p>
 * Input:  N = 7
 * Output: 9
 * We can at most get 9 A's on screen by pressing
 * following key sequence.
 * A, A, A, Ctrl A, Ctrl C, Ctrl V, Ctrl V
 * <p>
 * Input:  N = 11
 * Output: 27
 * We can at most get 27 A's on screen by pressing
 * following key sequence.
 * A, A, A, Ctrl A, Ctrl C, Ctrl V, Ctrl V, Ctrl A,
 * Ctrl C, Ctrl V, Ctrl V
 */
public class MaxA {

    public static void main(String []args) {

        // for the rest of the array we will rely on the previous
        // entries to compute new ones
        for (int N = 1; N <= 20; N++)
            System.out.println("Maximum Number of A's with keystrokes is N " + N + " -> " + findOptimalDP1(N));

        System.out.println("Optimized....");
        // for the rest of the array we will rely on the previous
        // entries to compute new ones
        for (int N = 1; N <= 20; N++)
            System.out.println("Maximum Number of A's with keystrokes is N " + N + " -> " +  findOptimalOptimized(N));
    }


    // this function returns the optimal
    // length string for N keystrokes
    //O(n^2)
    public static int findOptimalDP1(int N) {

        // The optimal string length is N
        // when N is smaller than 7
        if (N <= 6)
            return N;

        // An array to store result
        // of sub-problems
        int screen[] = new int[N + 1];

        int b; // To pick a breakpoint

        // Initializing the optimal lengths
        // array for until 6 input strokes
        int n;
        for (n = 1; n <= 6; n++)
            screen[n] = n;

        // Solve all sub-problems in bottom manner
        for (n = 7; n <= N; n++) {
            // Initialize length of optimal
            // string for n keystrokes
            screen[n] = 0;

            // For any keystroke n, we need
            // to loop from n-3 keystrokes
            // back to 1 keystroke to find
            // a breakpoint 'b' after which we
            // will have ctrl-a, ctrl-c and
            // then only ctrl-v all the way.
            for (b = n - 3; b >= 1; b--) {
                // if the breakpoint is
                // at b'th keystroke then
                // the optimal string would
                // have length
                // (n-b-1)*screen[b]
                int curr = (n - b - 1) * screen[b];
                if (curr > screen[n])
                    screen[n] = curr;
            }
        }

        return screen[N];
    }


    //O(n)
    public static int findOptimalOptimized(int N) {
        // The optimal string length is N
        // when N is smaller than 7
        if (N <= 6)
            return N;

        // An array to store result
        // of sub-problems
        int screen[] = new int[N + 1];

        // Initializing the optimal lengths
        // array for until 6 input strokes
        int n;
        for (n = 1; n <= 6; n++)
            screen[n] = n;

        /**
         * If you analyze the pattern of data, you find below information
         * N <=6
         * A(1), AA(2), AAA(3) , AAAA(4) , AAAAA(5), AAAAAA(6)
         *
         * for N=7
         *
         * the above method produce below things and take max of them
         *
         * { (7-4-1) * screen[4], (7-3-1) * screen[3],  (7-2-1) * screen[2], (7-1-1) * screen[1] }
         * **** => { 2 * 4 , 3 * 3, 4 * 2,  5 * 1 } => {8, 9, 8, 5 } = 9
         * Here
         * [second 8 produce by => (7-2-1) * screen[2] = 4 * screen[7-5]
         * and 9 produce by (7-3-1) * screen[3]] = 3 * screen [7-4] ]
         *
         * For N = 8
         *
         * { (8-5-1) * screen[5], (8-4-1) * screen[4], (8-3-1) * screen[3],  (8-2-1) * screen[2], (8-1-1) * screen[1] }
         * **** => { 2 * 5, 3 * 4, 4 * 3, 5 * 2, 6 * 1} =12
         *
         * Here
         *          * [first 12 produce by =>  (8-4-1) * screen[4] = 3 * screen[8-4]
         *          * and second 12 produce by (8-3-1) * screen[3]] = 4 * screen [8-5] ]
         *
         *          which implies max value are always from
         *          3 * screen[n-4] or 4 * screen [n-5]
         */

        for (n = 7; n <= N; n++) {
            screen[n] = Math.max(3 * screen[n - 4], 4 * screen[n - 5]);
        }

        return screen[N];
    }
}
