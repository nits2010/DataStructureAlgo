package Java.LeetCode;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-31
 * Description:https://leetcode.com/problems/divisor-game/
 * <p>
 * Alice and Bob take turns playing a game, with Alice starting first.
 * <p>
 * Initially, there is a number N on the chalkboard.  On each player's turn, that player makes a move consisting of:
 * <p>
 * Choosing any x with 0 < x < N and N % x == 0.
 * Replacing the number N on the chalkboard with N - x.
 * Also, if a player cannot make a move, they lose the game.
 * <p>
 * Return True if and only if Alice wins the game, assuming both players play optimally.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: 2
 * Output: true
 * Explanation: Alice chooses 1, and Bob has no more moves.
 * Example 2:
 * <p>
 * Input: 3
 * Output: false
 * Explanation: Alice chooses 1, Bob chooses 1, and Alice has no more moves.
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= N <= 1000
 */
public class DivisorGame {


    /**
     * https://leetcode.com/problems/divisor-game/discuss/296784/Come-on-in-different-explanation-from-others?currentPage=1&orderBy=most_relevant&query=explanation
     *
     *
     * <p>
     * 3 "definite" you need to know
     * <p>
     * Anyone who gets 1 definitely loses since not exist a x in range of (0, 1)
     * <p>
     * Anyone who gets 2 definitely wins since he always/or only can make 2 become 1. Because x can only be 1, 2 % 1 == 0 and 2 - 1 = 1
     * <p>
     * For any N >= 2, N will definitely be reduced to 2
     * <p>
     * Just illustrate some N below. Based on the "chain", you can see, no matter what N starts, all leads to 2 in the end:
     * <p>
     * //  2 -> 1
     * 3 -> 2
     * //  4 -> 2, 3
     * 5 -> 4
     * //  6 -> 3, 4, 5
     * 7 -> 6
     * //  8 -> 4, 6, 7
     * 9 -> 6, 8
     * //  10 -> 5, 8, 9
     * 11 -> 10
     * //  12 -> 8, 9, 10, 11
     * 13 -> 12
     * //  14 -> 7, 12, 13
     * 15 -> 10, 12
     * //  16 -> 8, 12, 14
     * 17 -> 16
     * //  18 -> 9, 12, 15, 16
     * 19 -> 18
     * //  20 -> 10, 15, 16, 18
     * ...
     * So Alice and Bob both say: Give me 2! They fight for 2 and it becomes the point of their "life". But how can they gurantee that they can get 2?
     * <p>
     * However after study, they find out:
     * <p>
     * For odd N, no matter what x is, x must be odd, then N - x must be even, i.e. odd =< even
     * For even N, we can always choose x as 1, then N - x = N - 1 must be odd, even => odd
     * They get the conclusion:
     * <p>
     * The one who gets even number `N` has the choice to get all the even numbers including 2(since 2 is even), so here comes win.
     * So if N is even initially,
     * 1. What is Alice's optimal strategy?
     * Choose x = 1, N = N - 1, send the odd back to Bob
     * 2. What is Bob's optimal strategy?
     * Choose x as large as possible to make N reduce as fast as possible such that the pain can end as early as possible, since nothing will change the fact that he will lose.
     * Just don't suffer.
     * <p>
     * Strategy
     * <p>
     * From above, we know that if Alice meets even N, she will win. So we just need to check if N is divisble by 2.
     * <p>
     * Final Code
     * <p>
     * class Solution {
     * public boolean divisorGame(int N) {
     * return (N & 1) == 0;
     * }
     * }
     *
     * @param n
     * @return
     */
    public boolean divisorGame(int n) {
        return (n % 2) == 0;
    }
}
