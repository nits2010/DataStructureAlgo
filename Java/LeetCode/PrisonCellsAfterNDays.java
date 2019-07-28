package Java.LeetCode;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-12
 * Description:https://leetcode.com/problems/prison-cells-after-n-days/
 */
public class PrisonCellsAfterNDays {

    public static void main(String args[]) {
        int cells[] = {0, 1, 0, 1, 1, 0, 0, 1}, N = 7;
        SolutionPrisonCellsAfterNDays sol = new SolutionPrisonCellsAfterNDays();
        int[] ints = sol.prisonAfterNDays(cells, N);
        for (int i = 0; i < ints.length; i++)
            System.out.print(" " + ints[i]);


    }
}

class SolutionPrisonCellsAfterNDays {
    /**
     * why 14?
     * The intuition comes from the fact that the first and last cell never numberOfWays their values after the first day. In effect, this makes it 6 cells, each one could be occupied or not, that gives us 6C2 = 15. Since the first and last cell don't numberOfWays and will always be 0 regardless of their initial value, that means that the 2nd and 7th cell will flip their state if and only if the 3rd and 6th cells are zero i.e.
     * 0001000, or  so that's a combination that will never happen (2nd and 6th cell with value 1 and 2nd and 5th cell with value 1) which gives us in total 6C2 - 1 = 14 combination. After 14 days, the combination will just repeat over and over again, which leads to the following optimized constant time solution.
     */
    public int[] prisonAfterNDays(int[] cells, int N) {

        int cellSize = 8;

        for (N = (N - 1) % 14 + 1; N > 0; N--) {
            int temp[] = new int[cellSize];

            for (int i = 1; i < cellSize - 1; i++) {
                temp[i] = (cells[i - 1] == cells[i + 1]) ? 1 : 0;
            }
            cells = temp;
        }
        return cells;
    }
}