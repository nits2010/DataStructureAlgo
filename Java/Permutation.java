package Java;/* package whatever; // don't place package name! */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


class Permutation {
    public static void main(String[] args) throws java.lang.Exception {
        System.out.println("\n Recursion " + PermutationRecursion.generatePermutation("ABC"));
        System.out.println("\n Loops : " + PermutationUsingLoops.generatePermutation("ABC"));
    }
}

class PermutationRecursion {

    public static List<String> generatePermutation(String str) {
        List<String> out = new LinkedList<>();
        permutation(str, out, 0, str.length() - 1);
        return out;
    }

    private static void permutation(String str, List<String> out, int start, int end) {

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

    private static String swap(String str, int a, int b) {
        char temp[] = str.toCharArray();
        char tempV = temp[a];
        temp[a] = temp[b];
        temp[b] = tempV;
        return new String(temp);
    }
}

class PermutationUsingLoops {


    public static List<String> generatePermutation(String input) {
        return populatePermutation(input);
    }


    private static List<String> populatePermutation(String input) {
        final List<String> permutation = new ArrayList<String>();
        int j = 0;
        int N = input.length();
        int p[] = getCombinationSet(N);
        permutation.add(input);

        int i = 1;
        while (i < N) {
            p[i]--;
            j = getJ(p, i);
            String item = getNextPermutation(input, i, j);
            permutation.add(item);
            input = item;

            i = 1;
            while (p[i] == 0) {
                p[i] = i;
                i++;
            }

        }

        return permutation;

    }

    private static int[] getCombinationSet(int N) {
        int p[] = new int[N + 1];
        for (int i = 0; i <= N; i++)
            p[i] = i;

        return p;
    }

    private static int getJ(int[] p, int i) {
        if (i % 2 != 0)
            return p[i];
        else
            return 0;
    }

    private static String getNextPermutation(String input, int i, int j) {
        char[] temp = input.toCharArray();
        char t = temp[i];
        temp[i] = temp[j];
        temp[j] = t;
        return new String(temp);

    }

}
