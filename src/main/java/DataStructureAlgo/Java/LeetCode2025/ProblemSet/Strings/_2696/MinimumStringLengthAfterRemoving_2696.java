package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Strings._2696;

import DataStructureAlgo.Java.helpers.CommonMethods;

/**
 * Author: Nitin Gupta
 * Date: 10/7/2024
 * Question Category: 2696. Minimum String Length After Removing
 * Description: https://leetcode.com/problems/minimum-string-length-after-removing-substrings/description/?envType=daily-question&envId=2024-10-07
 * You are given a string s consisting only of uppercase English letters.
 *
 * You can apply some operations to this string where, in one operation, you can remove any occurrence of one of the substrings "AB" or "CD" from s.
 *
 * Return the minimum possible length of the resulting string that you can obtain.
 *
 * Note that the string concatenates after removing the substring and could produce new "AB" or "CD" substrings.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "ABFCACDB"
 * Output: 2
 * Explanation: We can do the following operations:
 * - Remove the substring "ABFCACDB", so s = "FCACDB".
 * - Remove the substring "FCACDB", so s = "FCAB".
 * - Remove the substring "FCAB", so s = "FC".
 * So the resulting length of the string is 2.
 * It can be shown that it is the minimum length that we can obtain.
 * Example 2:
 *
 * Input: s = "ACBBD"
 * Output: 5
 * Explanation: We cannot do any operations on the string so the length remains the same.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 100
 * s consists only of uppercase English letters.
 *
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.Strings._1047.RemoveAllAdjacentDuplicatesInString_1047}
 * <p><p>
 * Tags
 * -----
 * @easy
 * @String
 * @Stack
 * @Simulation
 *
 * <p><p>
 * Company Tags
 * -----
 * <p><p>
 *
 * @Editorial
 */
public class MinimumStringLengthAfterRemoving_2696 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test("ABFCACDB", 2);
        test &= test("ACBBD", 5);
        test &= test("ABABABAB", 0);
        test &= test("CCCCDDDD", 0);
        CommonMethods.printAllTestOutCome(test);
    }

    private static boolean test(String s, int expected) {
        System.out.println("----------------------------------");
        System.out.println("Sentence : "+s + " expected : "+expected);
        Solution solution = new Solution();
        int output = solution.minLength(s);
        boolean pass = output == expected;
        System.out.println("Output : "+output + " Pass : "+(pass?"Passed":"Failed"));
        return pass;
    }

    /**
     * {@link 2696_explaination.md}
     *
     */
    static class Solution {
        public int minLength(String s) {
            if(s == null || s.isEmpty())
                return 0;

            char [] st = s.toCharArray();
            int i = 0, j = 1, n = s.length();

            while(j<n){
                if(check(i, j, st)){
                    j++;
                    i--;
                }else{
                    st[++i] = st[j++];
                }
            }
            return i+1;


        }

        private boolean check(int i, int j, char []st){
            if(j >= st.length || i < 0 )
                return false;
            return (st[i] == 'A' && st[j] == 'B') || (st[i] == 'C' && st[j] == 'D');
        }
    }
}
