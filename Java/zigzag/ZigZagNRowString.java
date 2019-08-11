package Java.zigzag;


import java.util.ArrayList;
import java.util.List;


/**
 * https://www.geeksforgeeks.org/print-concatenation-of-zig-zag-string-form-in-n-rows/
 * Given a string and number of rows ‘n’. Print the string formed by concatenating n rows when input string is written in row-wise Zig-Zag fashion.
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
 */
public class ZigZagNRowString {

    public static void zigZagNRowString(String str, int n) {

        if (n == 0) {
            System.out.println("invalid");
            return;
        }

        if (n == 1 || str.length() <= n) {
            System.out.println(str);
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
            System.out.println(ss.toString());
        }
    }

    public static void main(String[] args) {
        zigZagNRowString("ABCDEFGH", 2);
        zigZagNRowString("GEEKSFORGEEKS", 3);
        zigZagNRowString("GEEKSFORGEEKS", 14);
        zigZagNRowString("GEEKSFORGEEKS", 0);


        System.out.println("");
    }


}
