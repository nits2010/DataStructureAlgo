package Java.nonleetcode;

import java.util.Arrays;

/**
 * Author: Nitin Gupta
 * Date: 2019-06-24
 * Description:https://www.geeksforgeeks.org/maximum-weight-transformation-of-a-given-string/
 * <p>
 * Given a string consisting of only A’s and B’s. We can transform the given string to another string by toggling any character. Thus many transformations of the given string are possible. The task is to find Weight of the maximum weight transformation.
 * <p>
 * Weight of a sting is calculated using below formula.
 * <p>
 * <p>
 * Weight of string = Weight of total pairs +weight of single characters -Total number of toggles.
 * <p>
 * Two consecutive characters are considered as pair only if they are different.
 * Weight of a single pair (both character are different) = 4
 * Weight of a single character = 1
 * Examples :
 * <p>
 * <p>
 * <p>
 * Input: str = "AA"
 * Output: 3
 * Transformations of given string are "AA", "AB", "BA" and "BB".
 * Maximum weight transformation is "AB" or "BA".  And weight
 * is "One Pair - One Toggle" = 4-1 = 3.
 * <p>
 * Input: str = "ABB"
 * Output: 5
 * Transformations are "ABB", "ABA", "AAB", "AAA", "BBB",
 * "BBA", "BAB" and "BAA"
 * Maximum weight is of original string 4+1 (One Pair + 1
 * character)
 */
public class MaximumWeightTransformation {

    public static void main(String []args) {
        int w = maximumWeightTransformation("ABA");
        System.out.println(w);
    }

    private static int maximumWeightTransformation(String str) {
        int w[] = new int[str.length()];
        Arrays.fill(w, -1);
        return maximumWeightTransformation(str, 0,w);
    }

    /**
     * W[i] = represent the max weight of transformation of string 0 to i.
     * <p>
     * W[i] = if (str[i] != str[i+1] ) then
     * *         either we check the current character separately -> 1+ W[i+1]
     * *         or we make a pair and then repeat it  -> 4 + W[i+2]
     * <p>
     * W[i] = if (str[i] == str[i+1] ) then
     * *         either we check the current character separately -> 1+ W[i+1]
     * *         or we toggle a char and make a pair and then repeat it  -> (4-1) + W[i+2] 
     *
     * @param str
     * @param i
     * @return
     */
    //O(n)
    private static int maximumWeightTransformation(String str, int i, int w[]) {

        if (i >= str.length())
            return 0;


        //we have solved this problem already
        if (w[i] != -1)
            return w[i];


        int maxWeightConsiderCurrentSep = 1 + maximumWeightTransformation(str, i + 1, w);
        int max = maxWeightConsiderCurrentSep;

        if (i + 1 < str.length()) {
            if (str.charAt(i) != str.charAt(i + 1))
                max = Math.max(max, 4 + maximumWeightTransformation(str, i + 2, w));
            else
                max = Math.max(max, (4 - 1) + maximumWeightTransformation(str, i + 2, w));
        }

        return w[i] = max;
    }

}
