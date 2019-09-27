package Java.LeetCode;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-09-04
 * Description: https://leetcode.com/problems/reaching-points/
 * 780. Reaching Points [hard]
 * <p>
 * A move consists of taking a point (x, y) and transforming it to either (x, x+y) or (x+y, y).
 * <p>
 * Given a starting point (sx, sy) and a target point (tx, ty), return True if and only if a sequence of moves exists to transform the point (sx, sy) to (tx, ty). Otherwise, return False.
 * <p>
 * Examples:
 * Input: sx = 1, sy = 1, tx = 3, ty = 5
 * Output: True
 * Explanation:
 * One series of moves that transforms the starting point to the target is:
 * (1, 1) -> (1, 2)
 * (1, 2) -> (3, 2)
 * (3, 2) -> (3, 5)
 * <p>
 * Input: sx = 1, sy = 1, tx = 2, ty = 2
 * Output: False
 * <p>
 * Input: sx = 1, sy = 1, tx = 1, ty = 1
 * Output: True
 * <p>
 * Note:
 * <p>
 * sx, sy, tx, ty will all be integers in the range [1, 10^9].
 * <p>
 * Full explanation : https://leetcode.com/problems/reaching-points/discuss/375429/Detailed-explanation.-or-full-through-process-or-Java-100-beat
 */
public class ReachingPoints {

    public static void main(String[] args) {
        test(1, 1, 3, 5);
        test(1, 1, 4, 5);
        test(1, 1, 5, 5);

    }

    private static void test(int sx, int sy, int tx, int ty) {
        System.out.println("\n Sx : " + sx + " sy :" + sy + " tx :" + tx + " ty: " + ty);
        System.out.println("Top Down :" + ReachingPointsTopDown.reachingPoints(sx, sy, tx, ty));
        System.out.println("Bottom up :" + ReachingPointsBottomUp.reachingPoints(sx, sy, tx, ty));
    }


}


/**
 * If you observe that you have two actions to take at any moment for (x,y)
 * 1. (x , x+y)
 * 2. (x+y, y)
 * That means, we can have two choices at (x,y) which forms it like a binary tree. And one of the leaf node probably consist the (tx, ty)
 * <p>
 * Brute force:
 * 1. Do same as ask, run from top to bottom of binary tree. Find the leaf node which satisfy the condition.
 * sx=1, sy=1, tx=3, ty=5
 * *
 * *                            [1,1]
 * *                      [1,2]                  [2,1]
 * *             [1,3]           [3,2]
 * *                          [3,5] <- solution
 * *
 * *
 * *  Complexity: The height of binary tree depends on tx,ty. All the values which are either greater then tx or ty will be discarded as from that we can't reach the tx,ty.
 * * Hence, the height of tree would be Max(tx,ty)= N ..total complexity O(2^N)
 * *
 * *
 * TLE
 */
class ReachingPointsTopDown {

    public static boolean reachingPoints(int sx, int sy, int tx, int ty) {

        if (sx == tx && sy == ty)
            return true;

        if (sx > tx || sy > ty)
            return false;

        return (reachingPoints(sx + sy, sy, tx, ty) || reachingPoints(sx, sx + sy, tx, ty));
    }

}


/**
 * In above approach, we need to drill down till the tx or ty. If you see, for each child, there is only 1 way to reach parent (eventually root) in binary tree.
 * Which means, instead of starting from (sx,sy) and go down, we can start from (tx,ty) and go up till you hit one of the condition like sx >= tx or sy>= ty {revers of top down condition}
 * then you from that point you can simply check does it is possible to reach or not.
 * <p>
 * or sx=1, sy=1, tx=3, ty=5
 * *   [3,5] -> [3,2] = [1,2] here sx==tx
 * now we met the base condition, [1,2] can only be translate for [1,1] when ty >= sy {2>1} and (ty-sy)%sx == 0 { (2-1)%1 == 0}
 * <p>
 * why (ty-sy)%sx == 0?
 * since
 * sy will translate to ty only by (sx+sy), if they translate then (sx, sy+k*sx) = ty  for some k
 * sy+k*sx = ty => (ty-sy) / sx = k.
 * Since sx,sy,tx,ty are all integer, then k has to be a integer which means, there must be a integer k that help us to reach ty.
 * Hence
 * (ty-sy) % sx == 0
 * *
 * O(log(n)) ; n=Max(tx,ty)
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Reaching Points.
 * Memory Usage: 33 MB, less than 12.50% of Java online submissions for Reaching Points.
 */
class ReachingPointsBottomUp {
    public static boolean reachingPoints(int sx, int sy, int tx, int ty) {

        while (sx < tx && sy < ty)
            if (tx < ty)
                ty %= tx;
            else
                tx %= ty;

        if (sx == tx && sy <= ty && (ty - sy) % sx == 0)
            return true;

        return sy == ty && sx <= tx && (tx - sx) % sy == 0;


    }
}