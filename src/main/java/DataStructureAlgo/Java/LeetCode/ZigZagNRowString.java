package DataStructureAlgo.Java.LeetCode;


import java.util.ArrayList;
import java.util.List;


/**
 * https://leetcode.com/problems/zigzag-conversion/
 * 6. ZigZag Conversion
 * <p>
 * <p>
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
 * <p>
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 * <p>
 * Write the code that will take a string and make this conversion given a number of rows:
 * <p>
 * string convert(string s, int numRows);
 * Example 1:
 * <p>
 * Input: s = "PAYPALISHIRING", numRows = 3
 * Output: "PAHNAPLSIIGYIR"
 * Example 2:
 * <p>
 * Input: s = "PAYPALISHIRING", numRows = 4
 * Output: "PINALSIGYAHRPI"
 * Explanation:
 * <p>
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 * <p>
 * Examples:
 * <p>
 * Input: str = "ABCDEFGH"
 * n = 2
 * Output: "ACEGBDFH"
 * Explanation: Let us write input string in Zig-Zag fashion
 * in 2 rows.
 * *  A   C   E   G
 * *   B   D   F   H
 * Now concatenate the two rows and ignore spaces
 * in every row. We get "ACEGBDFH"
 * <p>
 * Input: str = "GEEKSFORGEEKS"
 * n = 3
 * Output: GSGSEKFREKEOE
 * Explanation: Let us write input string in Zig-Zag fashion
 * in 3 rows.
 * *  G       S       G       S
 * *   E   K   F   R   E   K
 * *     E       O       E
 * Now concatenate the two rows and ignore spaces
 * in every row. We get "GSGSEKFREKEOE"
 * <p>
 * https://www.geeksforgeeks.org/print-concatenation-of-zig-zag-string-form-in-n-rows/
 */
public class ZigZagNRowString {

    static class ZigZagConversion {

        public String zigZagNRowString(String str, int n) {


            if (n <= 1 || str.length() <= n) {
                return str;
            } else {

                List<StringBuffer> res = new ArrayList<>(n);
                int rr = 0;
                boolean up = false;

                for (int i = 0; i < n; i++)
                    res.add(new StringBuffer());

                for (int i = 0; i < str.length(); i++) {

                    res.get(rr).append(str.charAt(i));

                    if (!up)
                        rr++;
                    else
                        rr--;

                    if (rr == n) {
                        up = true;
                        rr -= 2;
                    }
                    if (rr == 0)
                        up = false;

                }

                StringBuffer ss = new StringBuffer();

                for (int i = 0; i < n; i++)
                    ss.append(res.get(i));
                return (ss.toString());
            }
        }
    }

    public static void main(String[] args) {
        ZigZagConversion conversion = new ZigZagConversion();
        System.out.println(conversion.zigZagNRowString("ABCDEFGH", 2));
        System.out.println(conversion.zigZagNRowString("GEEKSFORGEEKS", 3));
        System.out.println(conversion.zigZagNRowString("GEEKSFORGEEKS", 14));
        System.out.println(conversion.zigZagNRowString("GEEKSFORGEEKS", 0));


    }


}
