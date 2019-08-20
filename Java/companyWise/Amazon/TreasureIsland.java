package Java.companyWise.Amazon;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-20
 * Description: https://leetcode.com/discuss/interview-question/347457/Amazon-or-OA-2019-or-Treasure-Island
 * ou have a map that marks the location of a treasure island. Some of the map area has jagged rocks and dangerous reefs.
 * Other areas are safe to sail in. There are other explorers trying to find the treasure. So you must figure out a shortest
 * route to the treasure island.
 * <p>
 * Assume the map area is a two dimensional grid, represented by a matrix of characters. You must start from the top-left
 * corner of the map and can move one block up, down, left or right at a time. The treasure island is marked as X in a block
 * of the matrix. X will not be at the top-left corner. Any block with dangerous rocks or reefs will be marked as D.
 * You must not enter dangerous blocks. You cannot leave the map area. Other areas O are safe to sail in.
 * The top-left corner is always safe. Output the minimum number of steps to get to the treasure.
 * <p>
 * Example:
 * <p>
 * Input:
 * [['O', 'O', 'O', 'O'],
 * ['D', 'O', 'D', 'O'],
 * ['O', 'O', 'O', 'O'],
 * ['X', 'D', 'D', 'O']]
 * <p>
 * Output: 5
 * Explanation: Route is (0, 0), (0, 1), (1, 1), (2, 1), (2, 0), (3, 0) The minimum route takes 5 steps.
 * <p>
 * Pre-requisite {@link Java.LeetCode.island.NumberIslands}
 */
public class TreasureIsland {
}
