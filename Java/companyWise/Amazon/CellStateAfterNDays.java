package Java.companyWise.Amazon;

import Java.HelpersToPrint.GenericPrinter;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-05
 * Description: https://aonecode.com/amazon-online-assessment-oa2-cell-state-after-n-days
 * <p>
 * Eight houses, represented as cells, are arranged in a straight line.
 * Each day every cell competes with its adjacent cells (neighbors).
 * An integer value 1 represents an active cell and a value of 0 represents an inactive cell.
 * <p>
 * If the neighbors on both the sides of a cell are either active or inactive, the cell becomes inactive on the next day;
 * otherwise the cell becomes active.
 * <p>
 * The two cells on each end have a single adjacent cell,so assume that the unoccupied space on the opposite side is an inactive cell.
 * Even after updating the cell state,
 * consider its previous state when updating the state of other cells. The state information of all cells should be updated simultaneously.
 * Write an algorithm to output the state of the cells after the given number of days.
 * <p>
 * Input
 * The input to the function/method consists of two arguments:
 * states, a list of integers representing the current state of cells;
 * days, an integer representing the number of days.
 * <p>
 * Output
 * Return a list of integers representing the state of the cells after the given number of days.
 * <p>
 * Examples 1
 * Input:
 * [1, 0, 0, 0, 0, 1, 0, 0], 1
 * <p>
 * Output:
 * 0 1 0 0 1 0 1 0
 * <p>
 * Examples 2
 * Input:
 * [1, 1, 1, 0, 1, 1, 1, 1], 2
 * <p>
 * Output:
 * 0 1 0 0 0 1 0 0
 * <p>
 * Exact match to this {@link Java.LeetCode.PrisonCellsAfterNDays}, but how cell change values gets change in this question.
 * <p>
 * In PrisonCellsAfterNDays:
 * If a cell has two adjacent neighbors that are both occupied[active] or both vacant [in-active], then the cell becomes occupied [active].
 * Otherwise, it becomes vacant[inactive].
 * <p>
 * Here
 * <p>
 * If the neighbors on both the sides of a cell are either active or inactive, the cell becomes inactive on the next day;
 * otherwise the cell becomes active.
 * <p>
 * It just shortestPath of above.
 */
public class CellStateAfterNDays {
    public static void main(String[] args) {
        GenericPrinter.print(cellAfterNDays(new int[]{1, 0, 0, 0, 0, 1, 0, 0}, 1));
        GenericPrinter.print(cellAfterNDays(new int[]{1, 1, 1, 0, 1, 1, 1, 1}, 2));
    }


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
    static int[] cellAfterNDays(int[] houses, int N) {
        System.out.println("Given N: " + N);
        GenericPrinter.print(houses);
        N = (N - 1) % 14 + 1;
        final int houseSize = 8;

        for (int i = N; i > 0; i--) {

            int temp[] = new int[houseSize];

            for (int c = 1; c < houseSize - 1; c++) { // leaving first and last as it is
                temp[c] = (houses[c - 1] == houses[c + 1]) //If the neighbors on both the sides of a cell are either active or inactive
                        ? 0 //the cell becomes inactive on the next day
                        : 1;//otherwise the cell becomes active.
            }

            houses = temp;
        }

        return houses;
    }
}
