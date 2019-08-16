package Java.LeetCode;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-16
 * Description: https://leetcode.com/problems/jewels-and-stones/
 * <p>
 * You're given strings J representing the types of stones that are jewels, and S representing the stones you have.  Each character in S is a type of stone you have.  You want to know how many of the stones you have are also jewels.
 * <p>
 * The letters in J are guaranteed distinct, and all characters in J and S are letters. Letters are case sensitive, so "a" is considered a different type of stone from "A".
 * <p>
 * Example 1:
 * <p>
 * Input: J = "aA", S = "aAAbbbb"
 * Output: 3
 * Example 2:
 * <p>
 * Input: J = "z", S = "ZZ"
 * Output: 0
 * Note:
 * <p>
 * S and J will consist of letters and have length at most 50.
 * The characters in J are distinct.
 */
public class JewelsStones {

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Jewels and Stones.
     * Memory Usage: 34.7 MB, less than 100.00% of Java online submissions for Jewels and Stones.
     *
     * @param J
     * @param S
     * @return
     */
    public static int numJewelsInStones(String J, String S) {

        if (J == null || J.isEmpty() || S.isEmpty())
            return 0;

        boolean types[] = new boolean['z' - 'A' + 1];

        for (char c : J.toCharArray())
            types[c - 'A'] = true;

        int count = 0;
        for (char c : S.toCharArray())
            if (types[c - 'A'])
                count++;

        return count;
    }


    public static void main(String[] args) {
        System.out.println(numJewelsInStones("aA", "aAAbbbb"));
        System.out.println(numJewelsInStones("z", "ZZ"));
    }
}
