package Java.nonleetcode;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-06-23
 * Description: https://www.geeksforgeeks.org/word-wrap-problem-dp-19/
 * <p>
 * Given a sequence of words, and a limit on the number of characters that can be put
 * in one line (line width). Put line breaks in the given sequence such that the
 * lines are printed neatly. Assume that the length of each word is smaller than the line width.
 * <p>
 * For example, consider the following string and line width M = 15
 * "Geeks for Geeks presents word wrap problem"
 * <p>
 * Following is the optimized arrangement of words in 3 lines
 * Geeks for Geeks
 * presents word
 * wrap problem
 * <p>
 * The total extra spaces in line 1, line 2 and line 3 are 0, 2 and 3 respectively.
 * So optimal value of total cost is 0 + 2*2 + 3*3 = 13
 *
 * Similar problem {@link Java.LeetCode.TextJustification}
 */
public class WordWrapProblem {

    public static void main(String[] args) {
        int[] l = {3, 2, 2, 5};
        int maxWidth = 6;

        int cost = wordWrap(l, maxWidth);
        System.out.println(cost);

        int[] l2 = {4, 2, 2, 7,2,4,1,14};
        int maxWidth2 = 16;

        int cost2 = wordWrap(l2, maxWidth2);
        System.out.println(cost2);
    }

    private static int wordWrap(int[] l, int maxWidth) {

        if (null == l || l.length == 0)
            return 0;

        final int MAX = Integer.MAX_VALUE;
        int n = l.length;

        // extra[i][j] denotes total number of extra spaces would there if we put word from i to j in a line
        // Negative value represent not possible to put it, where as 0 denotes no cost

        // extra[i][j] : W - line[i]; ( i == j ); if this is a single word, try to put this word. If possible it will be positive value otherwise a negative value denotes not possible
        // extra[i][j] : extra[i][j-1]-line[j-1] - 1; 1 for space between word

        int[][] extra = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            extra[i][i] = maxWidth - l[i - 1]; //fill diagonally

            for (int j = i + 1; j <= n; j++) { //fill column wise
                extra[i][j] = extra[i][j - 1] - l[j - 1] - 1; //how many spaces left on putting last word 'extra[i][j - 1]', see if we can put this word or not.
            }
        }


        // lineCost[i][j] denotes total cost  if we put word from i to j in a line
        // Negative value represent not possible to put it, where as 0 denotes no cost

        // lineCost[i][j] : Integer.Max if extra[i][j] < 0 since we could not put this word at this line(as it require more space)
        // lineCost[i][j] : 0 if j == n && extra[i][j] >=0 (means all are placed)
        // lineCost[i][j] : extra[i][j]^3

        int[][] lineCost = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {

            for (int j = i; j <= n; j++) {

                if (extra[i][j] < 0)
                    lineCost[i][j] = MAX;

                else if (j == n && extra[i][j] >= 0)
                    lineCost[i][j] = 0;

                else
                    lineCost[i][j] = (int) Math.pow(extra[i][j], 3);
            }
        }

        // cost[j] denotes total cost of putting word from 0 to j in a line
        // cost[j] = 0 if j=0
        //           min ( cost[i-1] + line[i][j] ) if cost[i-1]!=max & line[i][j] != max

        int[] cost = new int[n + 1];
        int[] p = new int[n + 1];

        cost[0] = 0; //there is no cost of putting empty word on empty line

        //put a word 'j' on each line and find the minimum cost
        for (int j = 1; j <= n; j++) {

            cost[j] = MAX; //assume we can't put this word

            //Find the minimum cost of putting this word w.r.t previous words
            for (int i = 1; i <= j; i++) {

                if (cost[i - 1] != MAX && lineCost[i][j] != MAX && cost[j] > cost[i - 1] + lineCost[i][j]) {
                    cost[j] = cost[i - 1] + lineCost[i][j];
                    p[j] = i; //note which word we choose for this place
                }

            }

        }

        print(p, n);

        for ( int i = 0 ; i<=n ; i++)
            System.out.print(cost[i] + " ");

        System.out.println();
        return cost[n];
    }

    private static int print(int[] p, int n) {
        int k ;
        if (p[n] == 1)
            k = 1;
        else
            k = print(p, p[n] - 1) + 1;

        System.out.println(" in line " + k + " put word from " + p[n] + " to " + n);

        return k;

    }
}
