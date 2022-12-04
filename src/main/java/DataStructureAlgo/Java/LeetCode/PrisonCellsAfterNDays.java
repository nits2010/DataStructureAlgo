package DataStructureAlgo.Java.LeetCode;

import  DataStructureAlgo.Java.helpers.GenericPrinter;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-12
 * Description:https://leetcode.com/problems/prison-cells-after-n-days/
 * <p>
 * There are 8 prison cells in a row, and each cell is either occupied or vacant.
 * <p>
 * Each day, whether the cell is occupied or vacant changes according to the following rules:
 * <p>
 * If a cell has two adjacent neighbors that are both occupied or both vacant, then the cell becomes occupied.
 * Otherwise, it becomes vacant.
 * (Note that because the prison is a row, the first and the last cells in the row can't have two adjacent neighbors.)
 * <p>
 * We describe the current state of the prison in the following way: cells[i] == 1 if the i-th cell is occupied, else cells[i] == 0.
 * <p>
 * Given the initial state of the prison, return the state of the prison after N days (and N such changes described above.)
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: cells = [0,1,0,1,1,0,0,1], N = 7
 * Output: [0,0,1,1,0,0,0,0]
 * Explanation:
 * The following table summarizes the state of the prison on each day:
 * Day 0: [0, 1, 0, 1, 1, 0, 0, 1]
 * Day 1: [0, 1, 1, 0, 0, 0, 0, 0]
 * Day 2: [0, 0, 0, 0, 1, 1, 1, 0]
 * Day 3: [0, 1, 1, 0, 0, 1, 0, 0]
 * Day 4: [0, 0, 0, 0, 0, 1, 0, 0]
 * Day 5: [0, 1, 1, 1, 0, 1, 0, 0]
 * Day 6: [0, 0, 1, 0, 1, 1, 0, 0]
 * Day 7: [0, 0, 1, 1, 0, 0, 0, 0]
 * <p>
 * Example 2:
 * <p>
 * Input: cells = [1,0,0,1,0,0,1,0], N = 1000000000
 * Output: [0,0,1,1,1,1,1,0]
 * <p>
 * <p>
 * Note:
 * <p>
 * cells.length == 8
 * cells[i] is in {0, 1}
 * 1 <= N <= 10^9
 * <p>
 * https://aonecode.com/amazon-online-assessment-oa2-cell-state-after-n-days
 */
public class PrisonCellsAfterNDays {

    public static void main(String []args) {
        SolutionPrisonCellsAfterNDays sol = new SolutionPrisonCellsAfterNDays();
        GenericPrinter.print(sol.prisonAfterNDays(new int[]{0, 1, 0, 1, 1, 0, 0, 1}, 7));
        GenericPrinter.print(sol.prisonAfterNDays(new int[]{1, 1, 1, 0, 1, 1, 1, 1}, 2));


    }
}

class SolutionPrisonCellsAfterNDays {
    /**
     * why 14?
     * The intuition comes from the fact that the first and last cell never change their values after the first day.
     * In effect, this makes it 6 cells, each one could be occupied or not, that gives us 6C2 = 15.
     * Since the first and last cell don't change and will always be 0 regardless of their initial value,
     * that means that the 2nd and 7th cell will flip their state if and only if the 3rd and 6th cells are zero i.e.
     * 0001000, or  so that's a combination that will never happen (2nd and 6th cell with value 1 and 2nd and 5th cell with value 1)
     * which gives us in total 6C2 - 1 = 14 combination. After 14 days, the combination will just repeat over and over again,
     * which leads to the following optimized constant time solution.
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