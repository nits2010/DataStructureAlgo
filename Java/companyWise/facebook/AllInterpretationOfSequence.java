package Java.companyWise.facebook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 02/04/19
 * Description:
 * https://www.geeksforgeeks.org/find-all-possible-interpretations/
 */
public class AllInterpretationOfSequence {

    private static final String[] alphabet = {"", "a", "b", "c", "d", "e",
            "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
            "MinStepsInfiniteGrid", "t", "u", "v", "w", "x", "v", "z"};


    static class Node {
        public String s;
        public Node left, right;

        public Node(String s) {
            this.s = s;
        }
    }

    public static void main(String args[]) {

        List<String> out = null;
        int[] arr = {1, 1, 3, 4};
        out = allInterpretationOfSequence(arr);
        System.out.println("input: " + arr + " output: " + out);

        // aaa(1,1,1) ak(1,11) ka(11,1)
        int[] arr2 = {1, 1, 1};
        out = allInterpretationOfSequence(arr2);
        System.out.println("input: " + arr2 + " output: " + out);

        // bf(2,6) z(26)
        int[] arr3 = {2, 6};
        out = allInterpretationOfSequence(arr3);
        System.out.println("input: " + arr3 + " output: " + out);

        // ab(1,2), l(12)
        int[] arr4 = {1, 2};
        out = allInterpretationOfSequence(arr4);
        System.out.println("input: " + arr4 + " output: " + out);

        // a(1,0} j(10)
        int[] arr5 = {1, 0};
        out = allInterpretationOfSequence(arr5);
        System.out.println("input: " + arr5 + " output: " + out);

        // "" empty string output as array is empty
        int[] arr6 = {};
        out = allInterpretationOfSequence(arr6);
        System.out.println("input: " + arr6 + " output: " + out);

        // abba abu ava lba lu
        int[] arr7 = {1, 2, 2, 1};
        out = allInterpretationOfSequence(arr7);
        System.out.println("input: " + arr7 + " output: " + out);


    }

    private static List<String> allInterpretationOfSequence(int[] arr) {
        Node root = allInterpretationOfSequence(0, "", arr);

        return show(root);

    }

    private static List<String> show(Node root) {

        List<String> output = new ArrayList<>();

        show(root, output);
        return output;

    }

    private static void show(Node root, List<String> output) {

        if (null == root)
            return;

        if (root.left == null && root.right == null) {
            output.add(root.s);
            return;
        }

        show(root.left, output);
        show(root.right, output);
    }

    private static Node allInterpretationOfSequence(int index, String soFar, int[] arr) {

        if (index > 26)
            return null;


        String currentString = soFar + alphabet[index];

        Node root = new Node(currentString);

        if (arr.length > 0) {
            index = arr[0];

            root.left = allInterpretationOfSequence(index, currentString, Arrays.copyOfRange(arr, 1, arr.length));

            if (arr.length > 1) {
                index = arr[0] * 10 + arr[1];
                root.right = allInterpretationOfSequence(index, currentString, Arrays.copyOfRange(arr, 2, arr.length));

            }


        }
        return root;
    }
}
