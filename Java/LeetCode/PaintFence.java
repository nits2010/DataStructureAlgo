package Java.LeetCode;

/**
 * Author: Nitin Gupta
 * Date: 24/09/19
 * Description: https://leetcode.com/problems/paint-fence
 * http://leetcode.liangjiateng.cn/leetcode/paint-fence/description
 * 276.Paint Fence [EASY]
 * There is a fence with n posts, each post can be painted with one of the k colors.
 * <p>
 * You have to paint all the posts such that no more than two adjacent fence posts have the same color.
 * <p>
 * Return the total number of ways you can paint the fence.
 * <p>
 * Note:
 * n and k are non-negative integers.
 * <p>
 * Example:
 * <p>
 * Input: n = 3, k = 2
 * Output: 6
 * Explanation: Take c1 as color 1, c2 as color 2. All possible ways are:
 * <p>
 * *           post1  post2  post3
 * -----      -----  -----  -----
 * 1         c1     c1     c2
 * 2         c1     c2     c1
 * 3         c1     c2     c2
 * 4         c2     c1     c1
 * 5         c2     c1     c2
 * 6         c2     c2     c1
 * <p>
 * https://www.youtube.com/watch?v=deh7UpSRaEY
 * https://www.geeksforgeeks.org/painting-fence-algorithm/
 */
public class PaintFence {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(3, 2, 6);
        test &= test(2, 4, 16);

        System.out.println("\n Tests:" + (test ? "Passed" : "Failed"));
    }

    private static boolean test(int n, int k, int expected) {
        System.out.println("\nN:" + n + " k:" + k + "\nexpected :" + expected);
        int obtained = numWays(n, k);
        System.out.println("Obtained :" + obtained);
        return obtained == expected;
    }

    /**
     * Lets examine the problem from small to bigger values.
     * <p>
     * Lets take we have only two fence. [1,2] with k colors{K1,K2,...Kk}
     * Now there are two cases;
     * Case 1: Both fence has same color.
     * *        If we paint the first fence with color = K1
     * *        then we can paint the second fence with only same color = K1 i.e. we left with only one possibility in this case.
     * * Hence Same = k {k choices for first fence} * 1 {one choice for second to be same as first}
     * <p>
     * Case 2: Both fence has different color.
     * *         If we paint the first fence with color = K1
     * *         then we can paint the second fence with different color = {K2,...Kk} i.e. we left with only k-1  possibility in this case.
     * * Hence different = k {k choices for first fence} * k-1 {k-1 choice for second to be different than  first}
     * <p>
     * Hence for 2 fence the answer is Same + different  = k*1 + k*(k-1) = k + k*(k-1)
     * <p>
     * <p>
     * Lets take we have only three fence. [1,2,3] with k colors{K1,K2,...Kk}
     * Case 1: Both last two fence has same color.
     * * Since we can't have same color for three different fence in a row i.e. we can't choose 'same' from two fence. But we can choose 'different' from two fence, because 'different' has different color of
     * last two fence. Since we need to paint last fence (3rd one) with same color as second last fence, then we left with only 1 choice.
     * <p>
     * Hence Same = different {choices when we paint last two fence with different color} * 1{for this fence, since we need same color as last fence}
     * <p>
     * Case 1: Both last two fence has different color.
     * * Since last two fence has different color i.e. we can put k-1 choices {since we need to make last fence has different color than second last fence}.
     * * Which means either last two fence has same color and this one has different color Or last two has different color and this one also have different color
     * <p>
     * Hence different = (same + different) * (k-1)
     * <p>
     * Hence for 3 fence the answer is Same + different  = (k*(k-1))*1  + (k*1 + k*(k-1)) * (k-1) =   2*k*(k-1)  + k*(k-1)^2
     *
     * @param n
     * @param k
     * @return
     */
    public static int numWays(int n, int k) {

        if (n == 0)
            return 0;

        if (n == 1)
            return k; //we have k choices to paint 1 fence

        int same = k; // Same = k {k choices for first fence} * 1 {one choice for second to be same as first}
        int different = k * (k - 1); // different = k {k choices for first fence} * k-1 {k-1 choice for second to be different than  first}

        for (int i = 3; i <= n; i++) {
            int prevDiff = different;

            different = (same + different) * (k - 1); //different = (same + different) * (k-1)
            same = prevDiff; //Same = different {choices when we paint last two fence with different color} * 1{for this fence, since we need same color as last fence}
        }


        return same + different;
    }
}
