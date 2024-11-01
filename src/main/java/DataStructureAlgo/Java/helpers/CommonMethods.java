package DataStructureAlgo.Java.helpers;

import DataStructureAlgo.Java.LeetCode2025.ProblemSet.Trees._100.SameTree_100;
import DataStructureAlgo.Java.helpers.templates.DoublyListNode;
import DataStructureAlgo.Java.helpers.templates.ListNode;
import DataStructureAlgo.Java.helpers.templates.TreeNode;
import DataStructureAlgo.Java.LeetCode.flatten.list.Node;
import DataStructureAlgo.Java.LeetCode.flatten.list.SinglyNode;
import DataStructureAlgo.Java.helpers.templates.NArrayTreeNode;
import DataStructureAlgo.Java.Pair;
import DataStructureAlgo.Java.nonleetcode.Tree.traversal.TreeTraversalRecursive;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-24
 * Description:
 */
public class CommonMethods {

    public static void print(final int[] a) {
        System.out.println(toString(a));
    }

    public static <T> void print(final T[] a) {
        System.out.println(toString(a));
    }

    public static <T> void print(final T[][] a) {

        for (T[] x : a) {
            System.out.print("[" + toString(x) + "],");
        }
    }

    public static void print(final int[][] a) {
        System.out.println(toString(a));
    }


    public static void print(final char[][] a) {
        System.out.println(toString(a));
    }

    public static void print(final List<int[]> list) {
        System.out.println(toString(list));
    }

    public static String toString(int[] nums) {
        if (null == nums || nums.length == 0)
            return null;

        StringBuilder result = new StringBuilder("[");

        for (int num : nums) {
            result.append(num);
            result.append(",");
        }
        result.setCharAt(result.length() - 1, ']');

        return result.toString();
    }

    public static <T> String toString(T[] nums) {
        if (null == nums || nums.length == 0)
            return null;

        StringBuilder result = new StringBuilder("[");

        for (T num : nums) {
            result.append(num == null ? null : num.toString());
            result.append(",");
        }
        result.setCharAt(result.length() - 1, ']');

        return result.toString();
    }


    public static <T> String toStringT2D(T[][] nums) {
        if (null == nums || nums.length == 0)
            return null;

        StringBuilder result = new StringBuilder("[\n");

        for (T[] num : nums)
            result.append(toString(num)).append("\n");
        result.append("]");


        return result.toString();
    }


    public static String toString(double[] nums) {
        if (null == nums || nums.length == 0)
            return null;

        StringBuilder result = new StringBuilder("[");

        for (double num : nums) {
            result.append(num);
            result.append(",");
        }
        result.setCharAt(result.length() - 1, ']');

        return result.toString();
    }

    public static String toString(char[] nums) {
        if (null == nums || nums.length == 0)
            return null;

        StringBuilder result = new StringBuilder("[");

        for (char num : nums) {
            result.append(num);
            result.append(",");
        }
        result.setCharAt(result.length() - 1, ']');

        return result.toString();
    }

    public static String toString(char[] nums, int from, int to) {
        if (null == nums || nums.length == 0 || from > to || from < 0)
            return null;

        to = to > nums.length - 1 ? nums.length : to;

        StringBuilder result = new StringBuilder("[");

        for (int i = from; i <= to; i++) {
            result.append(nums[i]);
            result.append(",");
        }
        result.setCharAt(result.length() - 1, ']');

        return result.toString();
    }

    public static String toString(String[] str) {
        if (null == str || str.length == 0)
            return null;

        StringBuilder result = new StringBuilder("[");

        for (String s : str) {
            result.append(s);
            result.append(",");
        }
        result.setCharAt(result.length() - 1, ']');

        return result.toString();
    }

    public static String toString(int[][] nums) {
        if (null == nums || nums.length == 0)
            return null;

        StringBuilder result = new StringBuilder();
        for (int[] num : nums) {
            result.append(toString(num));
            result.setCharAt(result.length() - 1, ']');
            result.append("\n");
        }


        return result.toString();

    }

    public static String toString2D(char[][] nums) {
        if (null == nums || nums.length == 0)
            return null;

        StringBuilder result = new StringBuilder();
        for (char[] num : nums) {
            result.append(toString(num));
            result.setCharAt(result.length() - 1, ']');
            result.append("\n");
        }


        return result.toString();

    }

    public static String toString2DForm(int[][] nums) {
        return toString(nums);
    }


    public static String toStringFlat(int[][] nums) {
        if (nums == null || nums.length == 0)
            return null;

        StringBuilder result = new StringBuilder();
        result.append("[");
        for (int i = 0; i < nums.length; i++) {
            result.append(toString(nums[i]));
            if (i < nums.length - 1) {
                result.append(',');
            }
        }
        result.append("]");

        return result.toString();
    }

    public static String toStringFlat(char[][] nums) {
        if (nums == null || nums.length == 0)
            return null;

        StringBuilder result = new StringBuilder();
        result.append("[");
        for (int i = 0; i < nums.length; i++) {
            result.append(toString(nums[i]));
            if (i < nums.length - 1) {
                result.append(',');
            }
        }
        result.append("]");

        return result.toString();
    }

    public static String toString(List<Integer>[] nums) {
        if (null == nums || nums.length == 0)
            return null;

        StringBuilder result = new StringBuilder();
        for (List<Integer> num : nums) {


            result.append(num);
            result.append("\n");
        }


        return result.toString();

    }

    public static String toStringInline(int[][] nums) {
        if (null == nums || nums.length == 0)
            return null;

        StringBuilder result = new StringBuilder();
        for (int[] num : nums) {


            result.append(toString(num));
            result.setCharAt(result.length() - 1, ']');
            result.append(",");
        }


        return result.toString();

    }

    public static String toStringInline(char[][] nums) {
        if (null == nums || nums.length == 0)
            return null;

        StringBuilder result = new StringBuilder();
        for (char[] num : nums) {


            result.append(toString(num));
            result.setCharAt(result.length() - 1, ']');
            result.append(",");
        }


        return result.toString();

    }


    public static String toString(char[][] nums) {
        if (null == nums || nums.length == 0)
            return null;

        StringBuilder result = new StringBuilder();
        for (char[] num : nums) {


            result.append(toString(num));
            result.setCharAt(result.length() - 1, ']');
        }


        return result.toString();

    }


    public static String toString(final List<int[]> list) {
        if (null == list || list.size() == 0)
            return null;

        StringBuilder result = new StringBuilder();
        for (int[] x : list) {
            result.append(toString(x)).append(",");
        }

        return result.toString().substring(0, result.length() - 1);
    }


    public static int[] copyOf(int[] input) {
        int[] copy = new int[input.length];

        System.arraycopy(input, 0, copy, 0, input.length);

        return copy;
    }


    public static int[][] copyOf(int[][] input) {
        int[][] copy = new int[input.length][input[0].length];

        for (int i = 0; i < input.length; i++)
            System.arraycopy(input[i], 0, copy[i], 0, input[0].length);

        return copy;
    }

    public static char[] copyOf(char[] input) {
        char[] copy = new char[input.length];

        System.arraycopy(input, 0, copy, 0, input.length);

        return copy;
    }


    public static char[][] copyOf(char[][] input) {
        char[][] copy = new char[input.length][input[0].length];

        for (int i = 0; i < input.length; i++)
            System.arraycopy(input[i], 0, copy[i], 0, input[0].length);

        return copy;

    }


    public static <T> T[] copyOf(T[] input) {

        if (input == null || input.length == 0)
            return input;
        int n = input.length;
        return Arrays.copyOf(input, input.length);
    }


    public static List<Integer> preOrder(TreeNode root) {
        final List<Integer> preOrder = new LinkedList<>();

        if (null == root)
            return preOrder;

        preOrderUtil(root, preOrder);

        return preOrder;
    }


    public static void preOrderUtil(TreeNode root, List<Integer> preorder) {

        if (null == root) {
            preorder.add(null);
            return;
        }

        preorder.add(root.val);
        preOrderUtil(root.left, preorder);
        preOrderUtil(root.right, preorder);

    }

    public static List<Integer> inOrder(TreeNode root) {
        final List<Integer> inOrder = new LinkedList<>();

        if (null == root)
            return inOrder;

        inOrderUtil(root, inOrder);

        return inOrder;
    }


    public static void inOrderUtil(TreeNode root, List<Integer> inOrder) {

        if (null == root)
            return;


        inOrderUtil(root.left, inOrder);
        inOrder.add(root.val);
        inOrderUtil(root.right, inOrder);

    }


    public static <T extends SinglyNode> List<T> print(T head) {

        List<T> nodes = new ArrayList<>();

        T temp = head;

        while (temp != null) {
            nodes.add(temp);
            temp = (T) temp.next;
        }

        return nodes;
    }

    public static <T extends Node> List<T> print(T head) {

        List<T> nodes = new ArrayList<>();

        T temp = head;

        while (temp != null) {
            nodes.add(temp);
            temp = (T) temp.next;
        }

        return nodes;
    }

    public static <T extends TreeNode> List<T> print(T head) {

        List<T> nodes = new ArrayList<>();

        T temp = head;

        while (temp != null) {
            nodes.add(temp);
            temp = (T) temp.right;
        }

        return nodes;
    }

    public static <T extends ListNode> List<T> print(T head) {

        List<T> nodes = new ArrayList<>();

        T temp = head;

        while (temp != null) {
            nodes.add(temp);
            temp = (T) temp.next;
        }

        return nodes;
    }

    public static <T extends DoublyListNode> List<T> print(T head) {

        List<T> nodes = new ArrayList<>();

        T temp = head;

        while (temp != null) {
            nodes.add(temp);
            temp = (T) temp.next;
        }

        return nodes;
    }


    public static <T extends ListNode> List<List<T>> print(T[] heads) {

        List<List<T>> nodes = new ArrayList<>();

        for (T t : heads)
            nodes.add(print(t));

        return nodes;

    }


    public static <T extends TreeNode> List<T> printCircular(T head) {

        List<T> nodes = new ArrayList<>();

        T temp = head;

        while (temp != null) {
            nodes.add(temp);
            temp = (T) temp.right;

            if (temp == head) {
                nodes.add(temp);//if it was circular, then state it for testing purpose
                break;
            }
        }


        return nodes;
    }


    public static List<Integer> reverse(Integer[] elements) {
        return Arrays.stream(elements)
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());
    }

    public static List<Integer> reverse(List<Integer> elements) {
        return elements.stream()
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());
    }


    public static <I, E> void inputPrint(I[] input, E expected, String inputString) {
        System.out.println("-----------");
        System.out.println(inputString + ": " + CommonMethods.toString(input));
        System.out.println("Expected :" + expected);

    }

    public static <I, E> void inputPrint(I[] input, E[] expected, String inputString) {
        System.out.println("-----------");
        System.out.println(inputString + ": " + CommonMethods.toString(input));
        System.out.println("Expected :" + CommonMethods.toString(expected));

    }

    public static <I, E> void inputPrint(I[][] input, E[] expected, String inputString) {
        System.out.println("-----------");
        System.out.println(inputString + ": " + CommonMethods.toString(input));
        System.out.println("Expected :" + CommonMethods.toString(expected));

    }


    public static <I, E> void inputPrint(I[][] input, E[][] expected, String inputString) {
        System.out.println("-----------");
        System.out.println(inputString + ": " + CommonMethods.toString(input));
        System.out.println("Expected :" + CommonMethods.toString(expected));

    }

    public static <I, I2, E> void inputPrint(I[] input1, I2[] input2, E[] expected, String inputString1, String inputString2) {
        System.out.println("-----------");
        System.out.println(inputString1 + ": " + CommonMethods.toString(input1));
        System.out.println(inputString2 + ": " + CommonMethods.toString(input2));
        System.out.println("Expected :" + CommonMethods.toString(expected));

    }

    public static <I, I2, E> void inputPrint(I[] input1, I2[][] input2, E[] expected, String inputString1, String inputString2) {
        System.out.println("-----------");
        System.out.println(inputString1 + ": " + CommonMethods.toString(input1));
        System.out.println(inputString2 + ": " + CommonMethods.toString(input2));
        System.out.println("Expected :" + CommonMethods.toString(expected));

    }

    public static <I, I2, E> void inputPrint(I[][] input1, I2[][] input2, E[] expected, String inputString1, String inputString2) {
        System.out.println("-----------");
        System.out.println(inputString1 + ": " + CommonMethods.toString(input1));
        System.out.println(inputString2 + ": " + CommonMethods.toString(input2));
        System.out.println("Expected :" + CommonMethods.toString(expected));

    }

    public static <I, I2, E> void inputPrint(I[][] input1, I2[][] input2, E[][] expected, String inputString1, String inputString2) {
        System.out.println("-----------");
        System.out.println(inputString1 + ": " + CommonMethods.toString(input1));
        System.out.println(inputString2 + ": " + CommonMethods.toString(input2));
        System.out.println("Expected :" + CommonMethods.toString(expected));

    }

    @SafeVarargs
    public static <T> boolean equalsValues(T... a) {

        if (a.length <= 1) // at least 2 length
            return true;
        boolean equal = true;
        T temp = a[0];
        int i = 1;
        while (i < a.length)
            equal &= temp == a[i++];
        return equal;
    }

    public static boolean equalsValues(int[]... a) {

        if (a.length <= 1) // at least 2 length
            return true;
        boolean equal = true;
        int[] temp = a[0];
        int i = 1;
        while (i < a.length)
            equal &= Arrays.equals(temp, a[i++]);
        return equal;
    }

    public static <T extends Comparable<T>> boolean equalsValuesWithoutOrder(T[]... arrays) {
        if (arrays.length <= 1) // At least 2 arrays needed
            return true;

        // Sort the first array
        T[] firstArray = arrays[0];
        Arrays.sort(firstArray);

        // Compare sorted arrays
        for (int i = 1; i < arrays.length; i++) {
            T[] currentArray = arrays[i];
            Arrays.sort(currentArray);

            if (!Arrays.equals(firstArray, currentArray)) {
                return false; // Not equal if any sorted array doesn't match
            }
        }
        return true; // All arrays are equal
    }

    public static boolean equalsValuesWithoutOrder(int[]... a) {
        if (a.length <= 1) // at least 2 length
            return true;

        // Sort the first array
        int[] firstArray = a[0];
        Arrays.sort(firstArray);

        // Compare sorted arrays
        for (int i = 1; i < a.length; i++) {
            int[] currentArray = a[i];
            Arrays.sort(currentArray);

            if (!Arrays.equals(firstArray, currentArray)) {
                return false; // Not equal if any sorted array doesn't match
            }
        }
        return true; // All arrays are equal
    }

    @SafeVarargs
    public static <T> boolean equalsValuesArray(T[]... a) {

        if (a.length <= 1) // at least 2 length
            return true;
        boolean equal = true;
        T[] temp = a[0];
        int i = 1;
        while (i < a.length)
            equal &= Arrays.deepEquals(temp, a[i++]);
        return equal;
    }

    @SafeVarargs
    public static <T> boolean equalsValues(List<T>... a) {

        if (a.length <= 1) // at least 2 length
            return true;
        boolean equal = true;
        List<T> temp = a[0];
        int i = 1;
        while (i < a.length)
            if (temp != null) {
                equal &= temp.equals(a[i++]);
            } else if (a[i] != null) {
                return false;
            }
        return equal;
    }


    public static <T> boolean equalsValues(List<List<T>> a, List<List<T>> b) {

        if (a == null && b == null)
            return true;
        if (a == null || b == null)
            return false;

        if (a.size() != b.size())
            return false;

        for (int i = 0; i < a.size(); i++) {
            List<T> x = a.get(i);
            List<T> y = b.get(i);

            if (x.size() != y.size())
                return false;

            for (int j = 0; j < x.size(); j++) {
                if (!x.get(j).equals(y.get(j)))
                    return false;
            }
        }

        return true;


    }


    public static boolean equalsValues(ListNode a, ListNode b) {

        if (a == null && b == null)
            return true;
        if (a == null || b == null)
            return false;
        while (a != null && b != null) {
            if (a.val != b.val)
                return false;
            a = a.next;
            b = b.next;
        }
        if (a != null || b != null)
            return false;
        return true;

    }

    public static String levelOrder(NArrayTreeNode root) {
        if (root == null)
            return null;

        StringBuilder levelOrder = new StringBuilder();
        Queue<NArrayTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        levelOrder.append(root.val).append(",");

        while (!queue.isEmpty()) {
            NArrayTreeNode node = queue.poll();

            if (node.children != null)
                for (NArrayTreeNode child : node.children) {
                    levelOrder.append(child.val).append(",");
                    queue.offer(child);
                }
        }

        levelOrder.setLength(levelOrder.length() - 1);
        return levelOrder.toString();

    }

    public static <I, E, R> void resultPrint(I input, E expected, E output, String msg, R result) {
        if (input instanceof int[]) {
            print((int[]) input);
            print((int[]) expected);
            print((int[]) output);
        } else {
            print((Object[]) input);
        }


        System.out.println(msg + ":" + result);

    }


    public static DataStructureAlgo.Java.Node listWithRandomNode(List<Pair<Integer, Integer>> input) {
        if (input == null || input.isEmpty())
            return null;

        Map<Integer, DataStructureAlgo.Java.Node> mapOfIndexVsNode = new HashMap<>();

        int i = 0;
        for (Pair<Integer, Integer> pair : input) {
            Integer value = pair.getKey();
            DataStructureAlgo.Java.Node node = new DataStructureAlgo.Java.Node(value, null, null);
            mapOfIndexVsNode.put(i, node);
            i++;

        }

        for (i = 0; i < input.size(); i++) {

            DataStructureAlgo.Java.Node node = mapOfIndexVsNode.get(i);

            if (i + 1 < input.size())
                node.next = mapOfIndexVsNode.get(i + 1);

            if (input.get(i).getValue() != null)
                node.random = mapOfIndexVsNode.get(input.get(i).getValue());
        }
        return mapOfIndexVsNode.get(0);

    }

    public static void print(DataStructureAlgo.Java.Node node) {
        DataStructureAlgo.Java.Node temp = node;

        while (temp != null) {
            System.out.print(temp.val + "-->");
            temp = temp.next;
        }
        System.out.println();
        temp = node;
        while (temp != null) {
            System.out.print(temp.val + "=>");
            if (temp.random != null)
                System.out.print(temp.random.val + " ");
            else
                System.out.print(temp.random + " ");
            temp = temp.next;
        }
    }

    public static void printWithRef(DataStructureAlgo.Java.Node node) {
        DataStructureAlgo.Java.Node temp = node;

        while (temp != null) {
            System.out.print(temp.val + "(" + temp + ")" + "-->");
            temp = temp.next;
        }
        System.out.println();
        temp = node;
        while (temp != null) {
            System.out.print(temp.val + "(" + temp + ")" + "=>");
            if (temp.random != null)
                System.out.print(temp.random.val + "(" + temp.random + ")" + " ");
            else
                System.out.print(temp.random + " ");
            temp = temp.next;
        }
    }

    public static void main(String[] args) {

//        int[] x = {1, 2, 3};
//        resultPrint(x,new int[]{1,1}, new int[]{1,1}, "hello", true);
//        System.out.println(toString(copyOf(x)));
//
//        int[][] x2 = {{1, 2, 3}, {4, 5, 6}};
//        int[][] x2d = copyOf(x2);
//        System.out.println(x2 == x2d);
//
//        System.out.println(toString(new int[]{1, 3, 4}));
//        System.out.println(toString(new int[][]{{1, 3, 4}, {7, 8, 9}, {10, 11, 12}}));
//        System.out.println(toString(new char[]{'a', 'b', 'c'}));
//        System.out.println(toString(new char[][]{{'a', 'b', 'c'}, {'x', 'y', 'z'}, {'t', 'y', 'p'}}));

        List<Pair<Integer, Integer>> input = new ArrayList<>(5);
//        [[7,null],[13,0],[11,4],[10,2],[1,0]]
        input.add(new Pair<>(7, null));
        input.add(new Pair<>(13, 0));
        input.add(new Pair<>(11, 4));
        input.add(new Pair<>(10, 2));
        input.add(new Pair<>(1, 0));

        DataStructureAlgo.Java.Node head = listWithRandomNode(input);
        printWithRef(head);

    }

    public static boolean equals(char[] in1, char[] in2) {
        if (in1 != null && in2 == null)
            return false;
        if (in1 == null && in2 != null)
            return false;
        if (in1.length != in2.length)
            return false;

        for (int i = 0; i < in1.length; i++) {
            if (in1[i] != in2[i])
                return false;
        }
        return true;

    }

    public static boolean equals(int[] in1, int[] in2) {
        if (in1 != null && in2 == null)
            return false;
        if (in1 == null && in2 != null)
            return false;
        if (in1.length != in2.length)
            return false;

        for (int i = 0; i < in1.length; i++) {
            if (in1[i] != in2[i])
                return false;
        }
        return true;

    }

    public static boolean equals(char[][] in1, char[][] in2) {
        if (in1 != null && in2 == null)
            return false;
        if (in1 == null && in2 != null)
            return false;

        if (in1.length != in2.length)
            return false;

        if (in1[0].length != in2[0].length)
            return false;

        for (int i = 0; i < in1.length; i++) {
            if (!equals(in1[i], in2[i]))
                return false;
        }
        return true;

    }

    public static boolean equals(int[][] in1, int[][] in2) {
        if (in1 != null && in2 == null)
            return false;
        if (in1 == null && in2 != null)
            return false;

        if (in1.length != in2.length)
            return false;

        if (in1[0].length != in2[0].length)
            return false;

        for (int i = 0; i < in1.length; i++) {
            if (!equals(in1[i], in2[i]))
                return false;
        }
        return true;

    }

    public static boolean equals(Integer[][] expected, List<List<Integer>> result) {
        boolean test = true;

        if (expected.length != result.size())
            return false;

        for (int i = 0; i < expected.length; i++) {
            if (expected[i].length != result.get(i).size())
                return false;
            for (int j = 0; j < expected[i].length; j++) {
                test &= (expected[i][j].equals(result.get(i).get(j)));
            }
        }
        return test;
    }

    public static boolean isSameTree(TreeNode root1, TreeNode root2) {
        SameTree_100.Solution solution = new SameTree_100.Solution();
        return solution.isSameTree(root1, root2);
    }

    public static TreeNode searchNodeByValue(TreeNode root, Integer value) {
        if (root == null || value == null)
            return null;

        if (root.val == value)
            return root;

        TreeNode left = searchNodeByValue(root.left, value);
        TreeNode right = searchNodeByValue(root.right, value);

        return left == null ? right : left;

    }


    public static TreeNode[] searchNodeByValues(TreeNode root, Integer[] values) {
        if (root == null || values == null)
            return null;

        TreeNode[] nodes = new TreeNode[values.length];
        int i = 0;
        for (Integer value : values) {
            nodes[i++] = searchNodeByValue(root, value);
        }

        return nodes;

    }


    public static TreeNode searchNodeByValueBST(TreeNode root, Integer value) {
        if (root == null || value == null)
            return null;

        if (root.val == value)
            return root;

        if (value < root.val)
            return searchNodeByValueBST(root.left, value);
        else
            return searchNodeByValueBST(root.right, value);


    }

    public static void printBinaryTree(TreeNode root) {
        printBinaryTree(root, 0);
    }

    // Recursive helper method to print the tree structure
    private static void printBinaryTree(TreeNode node, int level) {
        if (node == null) {
            return;
        }

        // Print right subtree
        printBinaryTree(node.right, level + 1);

        // Print current node with indentation
        if (level != 0) {
            for (int i = 0; i < level - 1; i++) {
                System.out.print("|\t");
            }
            System.out.println("|-------" + node.val);
        } else {
            System.out.println(node.val);
        }

        // Print left subtree
        printBinaryTree(node.left, level + 1);
    }

    public static void printAllTestOutCome(boolean test) {
        System.out.println("================================================================================");
        System.out.println(test ? "\nAll passed" : "\n Something Failed");
        System.out.println("================================================================================");
    }

    public static void printAllTestOutCome(boolean[] test) {
        System.out.println("================================================================================");
        int i = 1, count = 0;
        List<Integer> failedTests = new ArrayList<>();
        for (boolean t : test) {
            if (t) {
                count++;
            } else {
                failedTests.add(i);
            }
            i++;

        }
        if (failedTests.isEmpty()) {
            System.out.println("\nAll passed : " + count + "/" + count);
        } else {
            System.out.println("Total test :" + test.length + " Passed : " + count + "\nFailed Tests index : " + failedTests);
        }

        System.out.println("================================================================================");
    }

    @SafeVarargs
    public static <T> void printTestOutcome(String[] prefixConsoles, boolean isInput, T... inputs) {
        if (isInput)
            System.out.println("------------------------------------------------------------------------------");
        StringBuilder console = new StringBuilder();

        if (prefixConsoles.length != inputs.length)
            return;

        int i = 0;
        for (T input : inputs) {

            String output = "";
            if (input instanceof Integer[]) {
                output = Arrays.toString((Integer[]) input);
            } else if (input instanceof Integer[][]) {
                output = CommonMethods.toString((Integer[][]) input);
            } else if (input instanceof int[]) {
                output = Arrays.toString((int[]) input);
            } else if (input instanceof int[][]) {
                output = CommonMethods.toString((int[][]) input);
            } else if (input instanceof String[]) {
                output = Arrays.toString((String[]) input);
            } else if (input instanceof String) {
                output = (String) input;
            } else if (input instanceof List) {
                output = input.toString();
            } else if (input instanceof TreeNode) {
                output = TreeTraversalRecursive.levelOrderWithNull((TreeNode) input).toString();
            } else {
                output = input.toString();
            }

            console.append(prefixConsoles[i].trim()).append(": ").append(output);

            i++;
            if (i < prefixConsoles.length)
                console.append(" | ");
        }
        System.out.println(console);

    }


    public static String getResultStringSubTest(boolean test, String prefix) {
        return "\n" + " " + prefix + " " + (test ? " Passed" : "Failed");
    }

    public static boolean compareArrays(int[] array1, int[] array2) {
        // Step 1: Check if the lengths of the arrays are the same
        if (array1.length != array2.length) {
            return false;
        }

        // Step 2: Sort both arrays
        Arrays.sort(array1);
        Arrays.sort(array2);

        // Step 3: Compare the sorted arrays element by element
        for (int i = 0; i < array1.length; i++) {
            if (array1[i] != array2[i]) {
                return false;
            }
        }

        // If all elements match, return true
        return true;
    }

    public static boolean compareArrays(int[][] array1, int[][] array2) {
        if (array1.length != array2.length) {
            return false;
        }

        // Sort the rows of both arrays
        int[][] sortedArray1 = sortRows(array1);
        int[][] sortedArray2 = sortRows(array2);

        // Compare the sorted arrays
        for (int i = 0; i < sortedArray1.length; i++) {
            if (!Arrays.equals(sortedArray1[i], sortedArray2[i])) {
                return false;
            }
        }

        return true;
    }

    private static int[][] sortRows(int[][] array) {
        int[][] sortedArray = new int[array.length][];
        for (int i = 0; i < array.length; i++) {
            sortedArray[i] = array[i].clone();
            Arrays.sort(sortedArray[i]);
        }
        Arrays.sort(sortedArray, Arrays::compare);
        return sortedArray;
    }

    public static boolean equals(List<Integer[]> a, List<Integer[]> b) {
        if (a == null && b == null)
            return true;
        if (a == null || b == null)
            return false;

        if (a.size() != b.size())
            return false;

        for (int i = 0; i < a.size(); i++) {
            if (!Arrays.equals(a.get(i), b.get(i)))
                return false;
        }

        return true;

    }

    public static <T> boolean compareResultOutCome(T result, T expected, boolean strictCompare) {
        // Handle null cases
        if (result == null || expected == null) {
            return Objects.equals(result, expected);
        }

        // Handle array comparisons
        if (result.getClass().isArray() && expected.getClass().isArray()) {
            if (strictCompare) {
                return Arrays.deepEquals(new Object[]{result}, new Object[]{expected});
            } else {
                return compareArraysUnordered(result, expected);
            }
        }

        // Handle collection comparisons
        if (result instanceof Collection && expected instanceof Collection) {
            if (strictCompare) {
                return Objects.equals(result, expected);
            } else {
                return compareCollectionsUnordered((Collection<?>) result, (Collection<?>) expected);
            }
        }

        // Use Objects.equals for all other types (including primitive wrappers)
        return Objects.equals(result, expected);
    }

    private static boolean compareArraysUnordered(Object arr1, Object arr2) {
        Object[] array1 = Arrays.stream((Object[]) arr1).sorted().toArray();
        Object[] array2 = Arrays.stream((Object[]) arr2).sorted().toArray();
        return Arrays.equals(array1, array2);
    }

    private static boolean compareCollectionsUnordered(Collection<?> col1, Collection<?> col2) {
        if (col1.size() != col2.size()) {
            return false;
        }
        List<?> sortedCol1 = new ArrayList<>(col1);
        List<?> sortedCol2 = new ArrayList<>(col2);
        Collections.sort((List<Comparable>) sortedCol1);
        Collections.sort((List<Comparable>) sortedCol2);
        return sortedCol1.equals(sortedCol2);
    }

}
