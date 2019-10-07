package Java.LeetCode;

import Java.nonleetcode.Combinations;
import Java.nonleetcode.Permutation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Author: Nitin Gupta
 * Date: 18/09/19
 * Description: https://leetcode.com/problems/letter-tile-possibilities/
 * 1079. Letter Tile Possibilities [Medium]
 * You have a set of tiles, where each tile has one letter tiles[i] printed on it.  Return the number of possible non-empty sequences of letters you can make.
 * Example 1:
 * Input: "AAB"
 * Output: 8
 * Explanation: The possible sequences are "A", "AA", "AAB", "AB", "B" , "BA",  "ABA", "BAA".
 * Example 2:
 * Input: "AAABBC"
 * Output: 188
 * Note:
 * <p>
 * 1 <= tiles.length <= 7
 * tiles consists of uppercase English letters.
 * <p>
 * Combination of  {@link Combinations} and {@link Permutation},
 * We need to avoid duplicate string though
 * <p>
 * Best solution {@link LetterTilePossibilitiesBackTrackingCounting}
 * https://leetcode.com/problems/letter-tile-possibilities/discuss/385144/3-solution-or-Optimisations-or-Java-or-Thought-Process
 */
public class LetterTilePossibilities {

    public static void main(String[] args) {
        test("AAB", 8);
        test("CDC", 8);
        test("AAABBC", 188);
    }

    private static void test(String tiles, int expected) {
        System.out.println("\nTiles:" + tiles);
        System.out.println("Expected                    :" + expected);
        System.out.println("Backtracking                :" + new LetterTilePossibilitiesBackTracking().numTilePossibilities(tiles));
        System.out.println("Backtracking optimized      :" + new LetterTilePossibilitiesBackTrackingOptimized().numTilePossibilities(tiles));
        System.out.println("Backtracking count          :" + new LetterTilePossibilitiesBackTrackingCounting().numTilePossibilities(tiles));


    }


}

/**
 * We can solve this problem using back-tracking.
 * <p>
 * Complexity: O(n*n!)
 * Runtime: 102 ms, faster than 7.99% of Java online submissions for Letter Tile Possibilities.
 * Memory Usage: 41 MB, less than 100.00% of Java online submissions for Letter Tile Possibilities.
 */
class LetterTilePossibilitiesBackTracking {
    public int numTilePossibilities(String tiles) {
        if (tiles == null || tiles.isEmpty())
            return 0;

        Set<String> set = new HashSet<>();
        boolean[] used = new boolean[tiles.length()];

        //O(n)
        for (int i = 0; i < tiles.length(); i++) {

            dfs(tiles, set, new StringBuilder(), used);
        }
        return set.size();

    }

    //O(n!)
    private void dfs(String tiles, Set<String> set, StringBuilder str, boolean[] used) {
        for (int i = 0; i < tiles.length(); i++) {

            if (used[i])
                continue;

            str.append(tiles.charAt(i));
            set.add(str.toString());

            used[i] = true;
            dfs(tiles, set, str, used);
            used[i] = false;
            str.setLength(str.length() - 1);

        }
    }

}


/**
 * WE apply same logic as above, but we try to find all string of L=1 to L=n length.
 * Algorithm:
 * 0.  sort string so that all duplicate come together
 * 1. Examine each length [1,n] where n is length of tiles
 * 2. Examine each character as potential start of a L length string
 * * a) if this character is used, skip it
 * * b) if this character is same as previous character and previous character is been used, then skip
 * * c) otherwise take this character, to make string of length L
 * * d) when we build a string of length L, increase the count
 * * e) backtrack to reuse i'th character
 * <p>
 * Complexity: O(n*Log(n)) + O(n*n!) = O(n*n!)
 * Runtime: 2 ms, faster than 83.17% of Java online submissions for Letter Tile Possibilities.
 * Memory Usage: 33.9 MB, less than 100.00% of Java online submissions for Letter Tile Possibilities.
 */
class LetterTilePossibilitiesBackTrackingOptimized {
    public int numTilePossibilities(String Tiles) {
        if (Tiles == null || Tiles.isEmpty())
            return 0;

        boolean[] used = new boolean[Tiles.length()];

        int[] result = {0};

        char[] tiles = Tiles.toCharArray();

        //sort string so that all duplicate come together
        //O(n*Log(n))
        Arrays.sort(tiles);

        //1. Examine each length [1,n] where n is length of tiles
        //O(n)
        for (int length = 1; length <= Tiles.length(); length++) {

            dfs(tiles, length, used, result);
        }
        return result[0];

    }

    //O(n!)
    private void dfs(char[] tiles, int len, boolean[] used, int[] result) {

        //d) when we build a string of length L, increase the count
        if (len == 0) {
            result[0]++;
            return;
        }

        //2. Examine each character as potential start of a L length string
        for (int i = 0; i < tiles.length; i++) {
            //b) if this character is same as previous character and previous character is been used, then skip
            if (i > 0 && !used[i - 1] && tiles[i] == tiles[i - 1])
                continue;

            //a) if this character is used, skip it
            if (used[i])
                continue;

            //c) otherwise take this character, to make string of length L
            used[i] = true;

            dfs(tiles, len - 1, used, result);

            //e) backtrack to reuse i'th character
            used[i] = false;

        }

    }


}

/**
 * In above solution, we keep counting the string of length L. We'll use same logic but important fact was duplicate string of same length we need to avoid.
 * To avoid that we used 'used' boolean array above.
 * <p>
 * In this solution, we'll use count of that character. Because a character can be used only that many number of times it occurred in string.
 * Another advantage is the constraint 'tiles consists of uppercase English letters.' which means there can be only 26 states possible.
 * <p>
 * Example:
 * AAB -> A:2, B:1
 * <p>
 * L = 1; we can choose either A or B
 * L = 2: We can choose either 2 A's or combination of A & B =>  AB or BA
 * L = 3: We can choose either all A's and B => AAB, BAA
 * <p>
 * Algorithm:
 * 1. Count the occurrence of Character in String. count
 * 2. Run through each character [0,26]
 * * a) use this character if available count[i]!=0
 * * b) Count how many string of L=1, L=2, L=3 with this character
 * * c) Backtrack and unused it for next iteration
 * <p>
 * Complexity: O(n) + O(26*n!) => O(n!)
 * * Runtime: 2 ms, faster than 83.17% of Java online submissions for Letter Tile Possibilities.
 * * Memory Usage: 33.9 MB, less than 100.00% of Java online submissions for Letter Tile Possibilities.
 */
class LetterTilePossibilitiesBackTrackingCounting {
    public int numTilePossibilities(String tiles) {
        if (tiles == null || tiles.isEmpty())
            return 0;

        final int[] count = new int[26];
        int[] result = {0};
        //1. Count the occurrence of Character in String. count
        for (char c : tiles.toCharArray())
            count[c - 'A']++;

        dfs(count, result);
        return result[0];

    }

    private void dfs(int[] count, int[] result) {

        for (int i = 0; i < 26; i++) {

            if (count[i] > 0) {

                result[0]++; //increase the count of string of this length

                //a) use this character if available count[i]!=0
                count[i]--;

                //b) Count how many string of this length with this character; L=1, L=2, L=3
                dfs(count, result);

                //c) Backtrack and unused it for next iteration
                count[i]++;
            }

        }
    }


    public int numTilePossibilities2(String tiles) {
        if (tiles == null || tiles.isEmpty())
            return 0;

        final int[] count = new int[26];
        //1. Count the occurrence of Character in String. count
        for (char c : tiles.toCharArray())
            count[c - 'A']++;

        return dfs2(count);

    }

    private int dfs2(int[] count) {

        int result = 0;
        for (int i = 0; i < 26; i++) {

            if (count[i] > 0) {

                result++; //increase the count of string of this length

                //a) use this character if available count[i]!=0
                count[i]--;

                //b) Count how many string of this length with this character; L=1, L=2, L=3
                result += dfs2(count);

                //c) Backtrack and unused it for next iteration
                count[i]++;
            }

        }
        return result;
    }


}