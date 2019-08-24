package Java;

import java.util.LinkedList;
import java.util.List;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 25/02/19
 * Description:
 */
public class Combinations {

    public static void main(String args[]) {

        List<String> combination = combination("abc");
        System.out.println("combination ->" + combination);


    }


    static List<String> combination(String str) {
        List<String> out = new LinkedList<>();
        StringBuilder comb = new StringBuilder();
        combination(str, out, comb, 0);
        return out;
    }

    static void combination(String str, List<String> out, StringBuilder comb, int start) {

        int n = str.length();

        for (int i = start; i < n; i++) {

            comb.append(str.charAt(i));

            out.add(comb.toString());

            combination(str, out, comb, i + 1);

            comb.deleteCharAt(comb.length() - 1);
        }
    }

}
