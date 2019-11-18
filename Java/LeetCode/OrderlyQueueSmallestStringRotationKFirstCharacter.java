package Java.LeetCode;

import java.util.Arrays;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-06
 * Description: https://leetcode.com/problems/orderly-queue/
 */
public class OrderlyQueueSmallestStringRotationKFirstCharacter {

    public static void main (String []args){
        SolutionOrderlyQueueSmallestStringRotationKFirstCharacter sol = new SolutionOrderlyQueueSmallestStringRotationKFirstCharacter();
        System.out.println(sol.orderlyQueue("cba", 1));
        System.out.println(sol.orderlyQueue("baaca", 3));
    }

}

class SolutionOrderlyQueueSmallestStringRotationKFirstCharacter {
    public String orderlyQueue(String S, int K) {
        return beat100Percent(S, K);

    }


    private String bitSlow(String S, int K) {
        if (K == 1) {
            String ans = S;
            for (int i = 0; i < S.length(); ++i) {
                String T = S.substring(i) + S.substring(0, i);
                if (T.compareTo(ans) < 0) ans = T;
            }
            return ans;
        } else {
            char[] ca = S.toCharArray();
            Arrays.sort(ca);
            return new String(ca);
        }
    }

    private String beat100Percent(String S, int K) {
        if (S == null || S.isEmpty())
            return S;

        if (K == 1)
            //This is nothing but finding the string after each rotation and comparing it with best so far
            return lexicographicallySmallestRotatedSequence(S);
        else {
            /**
             Lets understand this case when we can choose any of the first k letter;
             lets take a example to understand:
             bdac ; k=2
             bdac, select d (second letter)
             bacd, select a (second letter)
             bcda, select b (first letter)
             cdab, select c (first letter)
             dabc, select d (first letter)
             abcd

             So what we were essentially doing above? can you notice any pattern?
             Yes, we are somewhat sorting the letters, why? because we have capbability to push any
             first k character to end of the string.
             This makes generating all the combination/permutation of given string by choosing any of the                 first k letters.
             To understand better, please try k=3 in above example; you'll notice in somewhat it won't matter
             because at the end you'll reach sorted order of chars to make smallest string
             **/
            char c[] = S.toCharArray();
            Arrays.sort(c);
            return new String(c);
        }
    }

    /**
     * O(n^2 )
     *
     * @param str
     * @return
     */
    private String lexicographicallySmallestRotatedSequence(String str) {
        if (str == null || str.isEmpty())
            return str;

        int smallestStringIndex = 0;

        for (int i = 0; i < str.length(); i++) {

            if (isSmallest(str, smallestStringIndex, i)) smallestStringIndex = i;

        }
        if (smallestStringIndex == 0)
            return str;

        return str.substring(smallestStringIndex) + str.substring(0, smallestStringIndex);
    }

    /**
     * This guy makes things faster, notice it
     * if we use inbuilt string comparision, then it will iterate whole string regardless, but this will
     * throw answer as quick as possible
     * check string start from x and string start from y; find the smallest between them
     *
     * @param str
     * @param x
     * @param y
     * @return
     */
    private boolean isSmallest(String str, int x, int y) {

        for (int i = 0; i < str.length(); i++) {

            if (str.charAt(x) < str.charAt(y))
                return false;

            if (str.charAt(x) > str.charAt(y))
                return true;

            x = (x + 1) % str.length();
            y = (y + 1) % str.length();


        }
        return true;
    }
}