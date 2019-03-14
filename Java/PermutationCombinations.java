package Java;

import java.util.LinkedList;
import java.util.List;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 25/02/19
 * Description:
 */
public class PermutationCombinations {

    public static void main(String args[]) {
        String input = "abc";

        List<String> permutation = permutation(input);
        List<String> combination = combination(input);

        System.out.println("permutation ->");

        System.out.println(permutation);

        System.out.println("combination ->");

        System.out.println(combination);


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

    static List<String> permutation(String str) {
        List<String> out = new LinkedList<>();
        permutation(str, out, 0, str.length() - 1);
        return out;
    }

    static void permutation(String str, List<String> out, int start, int end) {

        if (start == end) {
            out.add(str);
        } else {
            for (int i = start; i <= end; i++) {
                str = swap(str, start, i);
                permutation(str, out, start + 1, end);
                str = swap(str, start, i);

            }
        }
    }

    static String swap(String str, int a, int b) {
        char temp[] = str.toCharArray();
        char tempV = temp[a];
        temp[a] = temp[b];
        temp[b] = tempV;
        return new String(temp);
    }
}
