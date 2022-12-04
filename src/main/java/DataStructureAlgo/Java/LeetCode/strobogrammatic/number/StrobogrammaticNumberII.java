package DataStructureAlgo.Java.LeetCode.strobogrammatic.number;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 2019-09-09
 * Description: https://leetcode.com/problems/strobogrammatic-number-ii
 * 247.Strobogrammatic Number II
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 * <p>
 * Find all strobogrammatic numbers that are of length = n.
 * <p>
 * Example:
 * <p>
 * Input:  n = 2
 * Output: ["11","69","88","96"]
 * <p>
 * input : n=3
 * ['818', '111', '916', '619', '808', '101', '906', '609', '888', '181', '986', '689']
 * <p>
 * Input : n = 4
 * Output : 8008 1001 9006 6009 8888 1881 9886 6889 8118 1111
 * 9116 6119 8968 1961 9966 6969 8698 1691 9696 6699
 * <p>
 * {@link StrobogrammaticNumberI}
 * https://www.geeksforgeeks.org/strobogrammatic-number/
 * http://tiancao.me/Leetcode-Unlocked/LeetCode%20Locked/c1.5.html
 */
public class StrobogrammaticNumberII {

    public static void main(String[] args) {
        test(2, Arrays.asList("11", "69", "88", "96"));
        test(3, Arrays.asList("818", "111", "916", "619", "808", "101", "906", "609", "888", "181", "986", "689"));
        test(4, Arrays.asList("8008", "1001", "9006", "6009", "8888", "1881", "9886", "6889", "8118", "1111", "9116", "6119", "8968", "1961", "9966", "6969", "8698", "1691", "9696", "6699"));
        test(5, Arrays.asList("80108", "10101", "90106", "60109", "88188", "18181", "98186", "68189", "81118", "11111", "91116", "61119", "89168", "19161", "99166", "69169", "86198", "16191", "96196", "66199", "80008", "10001", "90006", "60009", "88088", "18081", "98086", "68089", "81018", "11011", "91016", "61019", "89068", "19061", "99066", "69069", "86098", "16091", "96096", "66099", "80808", "10801", "90806", "60809", "88888", "18881", "98886", "68889", "81818", "11811", "91816", "61819", "89868", "19861", "99866", "69869", "86898", "16891", "96896", "66899"));
    }

    private static void test(int n, List<String> expected) {
        Collections.sort(expected);
        System.out.println("\nExpected :" + expected);
        System.out.println("Obtained :" + findStrobogrammatic(n));
    }

    /**
     * We know that a number is Strobogrammatic Number if and only if both the char at ends are Strobogrammatic Number.
     *  Strobogrammatic Number
     *      * 6 <-> 9
     *      * 1 <-> 1
     *      * 0 <-> 0
     *      * 8 <-> 8
     *
     * i.e. means for each number we have only above choices to be placed at the end and recursively in between.
     *
     * Important point : if n is odd, then middle number can only be 0 or 1 or 8.
     *
     * Now, how we can generate n>2 from n=1 and n=2;
     *
     * lets take an example:
     * n= 1 ["0", "1", "8" ]
     * n = 2 ["00" , "11", "88", "69" , "96" ]
     *
     * n = 3
     *  => n=3-2 = 1 ["0", "1", "8" ]
     *  => append 1..1, 6..9, 9..6, 8..8 surround to obtain for n=3.
     *  => 0 -> 101, 609, 906,808,
     *  => 1 -> 111,619, 916, 818
     *  => 8 -> 181, 689, 986, 888
     *
     * n = 4
     * => n-2 = 2 ["00" , "11", "88", "69" , "96" ]
     * append 1..1, 6..9, 9..6, 8..8 surround to obtain for n=4.
     *
     * Complexity: O(n) / O (1) ; where n is length of output array
     *
     */
    public static List<String> findStrobogrammatic(int n) {
        if (n <= 0)
            return Arrays.asList("");

        if (n == 1)
            return Arrays.asList("0", "1", "8");

        if (n == 2)
            return Arrays.asList("11", "88", "69", "96");




        final List<String> strobogrammaticNumbers = findStrobogrammatic(n, n);

        Collections.sort(strobogrammaticNumbers);//for testing purpose

        return strobogrammaticNumbers;
    }

    private static List<String> findStrobogrammatic(int n, int total) {

        if (n == 1)
            return Arrays.asList("0", "1", "8");

        else if (n == 2)
            return Arrays.asList("00", "11", "88", "69", "96");

        else
            return append(findStrobogrammatic(n - 2, total), n, total);


    }

    private static List<String> append(List<String> strobogrammatic, int n, int total) {

        List<String> output = new ArrayList<>();

        for (String s : strobogrammatic) {

            if (n != total)
                output.add("0" + s + "0");
            output.add("1" + s + "1");
            output.add("6" + s + "9");
            output.add("9" + s + "6");
            output.add("8" + s + "8");

        }


        return output;
    }
}
