package Java.LeetCode;

import Java.helpers.GenericPrinter;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Author: Nitin Gupta
 * Date: 23/09/19
 * Description: https://leetcode.com/problems/longest-absolute-file-path/
 * 388. Longest Absolute File Path [Medium]
 * <p>
 * Suppose we abstract our file system by a string in the following manner:
 * <p>
 * The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:
 * <p>
 * * dir
 * *     subdir1
 * *     subdir2
 * *         file.ext
 * The directory dir contains an empty sub-directory subdir1 and a sub-directory subdir2 containing a file file.ext.
 * <p>
 * The string "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext" represents:
 * <p>
 * dir
 * *     subdir1
 * *         file1.ext
 * *         subsubdir1
 * *     subdir2
 * *         subsubdir2
 * *             file2.ext
 * The directory dir contains two sub-directories subdir1 and subdir2. subdir1 contains a file file1.ext and an empty second-level sub-directory subsubdir1. subdir2 contains a second-level sub-directory subsubdir2 containing a file file2.ext.
 * <p>
 * We are interested in finding the longest (number of characters) absolute path to a file within our file system. For example, in the second example above, the longest absolute path is "dir/subdir2/subsubdir2/file2.ext", and its length is 32 (not including the double quotes).
 * <p>
 * Given a string representing the file system in the above format, return the length of the longest absolute path to file in the abstracted file system. If there is no file in the system, return 0.
 * <p>
 * Note:
 * The name of a file contains at least a . and an extension.
 * The name of a directory or sub-directory will not contain a ..
 * Time complexity required: O(n) where n is the size of the input string.
 * <p>
 * Notice that a/aa/aaa/file1.txt is not the longest file path, if there is another path aaaaaaaaaaaaaaaaaaaaa/sth.png.
 */
public class LongestAbsoluteFilePath {

    public static void main(String[] args) {
        boolean test = true;
        test &= test("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext", 20);
        test &= test("dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext", 32);
        System.out.println("\n Tests : " + (test ? "Passed" : "Failed"));
    }

    private static boolean test(String s, int expected) {
        System.out.println("\nS:" + s);
        System.out.println("Expected:   " + expected);

        int mapObtained = new LongestAbsoluteFilePathUsingMap().lengthLongestPath(s);
        int stackObtained = new LongestAbsoluteFilePathUsingStack().lengthLongestPath(s);
        int stack2Obtained = new LongestAbsoluteFilePathUsingStackExplicit().lengthLongestPath(s);
        System.out.println("Map:        " + mapObtained);
        System.out.println("Stack:      " + stackObtained);
        System.out.println("Stack2:     " + stack2Obtained);

        return GenericPrinter.equalsValues(mapObtained, stack2Obtained, stackObtained, expected);
    }
}

/**
 * Lets taken an example to find how to get the maximum length
 * The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"
 * represents:
 * * dir
 * *     subdir1
 * *     subdir2
 * *         file.ext
 * <p>
 * '\n' -> is being just a new line{to represent} and should not be counted in final string.
 * '\t' -> is being used to indicate the file/subfolder is part of a directory. In above example '\tsubdir1' is folder in 'dir' and '\t\tfile.ext' is file under subdir2. Which mean we need to use
 * '\t' for identify the level of directory.
 * <p>
 * Now, in order to find deepest file, we need to find the deepest '\t' such that it attached to a file. Two cases
 * Case 1: \t is attached to a sub-dir
 * * case 1.1: This sub-dir could be at the same level as the other sub-dir. In above example, subdire2 is on same level as subdir1. In this case, we just need to count length same for both {here its 3(dir) + 6(subdir2)}
 * * case 1.2: This sub-dir could be at different level in its sub-dir of another sub-dir. In that case, we need to add length from top to bottom example { its 3(dir) + 6(subdir1) + 6(subdir2)} where sub-dir2 is sub-dir of sub-dir1
 * <p>
 * Case 2: \t is attached to a file, to identify it we can check does it contain a '.' or not. In this case, we need to attach the length to highest correct sub-dir.
 * <p>
 * Now, Solving for Case 1 is trivial. But how to solve for case 2?
 * One should notice that the string will be always forming 'order' in the form of sub-dir contains the file. Example
 * The string "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext" represents:
 * * dir
 * *     subdir1
 * *         file1.ext <- This always comes first in string then file2.ext
 * *         subsubdir1
 * *     subdir2
 * *         subsubdir2
 * *             file2.ext <- This always comes later in string then file1.ext
 * <p>
 * Which means we can simply scan the string to find correct level of file.
 * <p>
 * Algorithm: Keeping above things in mind
 * 1. Split the string by '\n'
 * 2. Find the last index of '\t' { last because a sub-string may contain multiple '\t' but as above question they effectively a single sub-dir or file}
 * 3. Compute the remaining length of string without '\t'
 * 4. Check does this is a file or not
 * * a) if  a sub-dir, than append its length to just higher level sub-dir
 * * b) if its a file, compute the length of string for this file. To compute, get the level at this file is and add the length of the file
 * <p>
 * Time Complexity : O(n) where n is length of given string { lastIndexOf takes O(L) time and contains take O(L) time. In worst case, there is no \n and we run both in O(n) time}
 * Space: O(h) where h represent levels of string h~=n
 * <p>
 * Runtime: 1 ms, faster than 97.28% of Java online submissions for Longest Absolute File Path.
 * Memory Usage: 34.4 MB, less than 100.00% of Java online submissions for Longest Absolute File Path.
 */
class LongestAbsoluteFilePathUsingMap {

    public int lengthLongestPath(String input) {

        if (input == null || input.isEmpty())
            return 0;

        int maxLength = 0; //if may possible the string does not contain any file at all hence 0

        //to keep the length of every sub-dir at same level. It stores length of string at each level
        Map<Integer, Integer> levelVsLength = new HashMap<>();

        levelVsLength.putIfAbsent(0, 0); //empty level will have 0 length string

        //1. Split the string by '\n'
        String[] string = input.split("\n");

        for (String s : string) {

            //2. Find the last index of '\t' { last because a sub-string may contain multiple '\t' but as above question they effectively a single sub-dir or file}
            int tabIndexLevel = s.lastIndexOf('\t') + 1; //this will return the index of '\' if '\t' present, to make index of '\t' we add 1. It also play well when there is no '\t' then it makes level 0

            // 3. Compute the remaining length of string without '\t'
            int len = s.length() - tabIndexLevel;


            //4. Check does this is a file or not
            if (!s.contains(".")) {
                // a) if  a sub-dir, than append its length to just higher level sub-dir
                levelVsLength.put(tabIndexLevel + 1, levelVsLength.get(tabIndexLevel) + len + 1);
            } else {
                //b) if its a file, compute the length of string for this file. To compute, get the level at this file is and add the length of the file
                maxLength = Math.max(maxLength, levelVsLength.get(tabIndexLevel) + len);
            }
        }
        return maxLength;


    }

}

/**
 * We can use stack in place of map. As we need to know only the last level which is being the largest one to participate
 * <p>
 * Runtime: 1 ms, faster than 97.28% of Java online submissions for Longest Absolute File Path.
 * Memory Usage: 34.2 MB, less than 100.00% of Java online submissions for Longest Absolute File Path.
 */
class LongestAbsoluteFilePathUsingStack {


    public int lengthLongestPath(String input) {

        if (input == null || input.isEmpty())
            return 0;

        int maxLength = 0; //if may possible the string does not contain any file at all hence 0

        //to keep the length of every sub-dir at same level. It stores length of string at each level
        Stack<Integer> stack = new Stack<>();
        stack.push(0); //empty level will have 0 length string


        //1. Split the string by '\n'
        String[] string = input.split("\n");

        for (String s : string) {

            //2. Find the last index of '\t' { last because a sub-string may contain multiple '\t' but as above question they effectively a single sub-dir or file}
            int tabIndexLevel = s.lastIndexOf('\t') + 1; //this will return the index of '\' if '\t' present, to make index of '\t' we add 1. It also play well when there is no '\t' then it makes level 0

            // 3. Compute the remaining length of string without '\t'
            int len = s.length() - tabIndexLevel;

            //remove all the level which is bigger than this because we must have evaluate the sub-string size by them
            while (stack.size() > tabIndexLevel + 1)
                stack.pop();

            //4. Check does this is a file or not
            if (!s.contains(".")) {
                // a) if  a sub-dir, than append its length to just higher level sub-dir
                stack.push(stack.peek() + len + 1);
            } else {
                //b) if its a file, compute the length of string for this file. To compute, get the level at this file is and add the length of the file
                maxLength = Math.max(maxLength, stack.peek() + len);
            }
        }
        return maxLength;


    }
}

/**
 * Same as above
 */
class LongestAbsoluteFilePathUsingStackExplicit {
    public int lengthLongestPath(String input) {

        if (input == null || input.isEmpty())
            return 0;

        int maxLength = 0; //if may possible the string does not contain any file at all hence 0

        //to keep the length of every sub-dir at same level. It stores length of string at each level
        int[] stack = new int[input.length() + 1];
        int top = 0;
        stack[top++] = 0; //empty level will have 0 length string


        //1. Split the string by '\n'
        String[] string = input.split("\n");

        for (String s : string) {

            //2. Find the last index of '\t' { last because a sub-string may contain multiple '\t' but as above question they effectively a single sub-dir or file}
            int tabIndexLevel = s.lastIndexOf('\t') + 1; //this will return the index of '\' if '\t' present, to make index of '\t' we add 1. It also play well when there is no '\t' then it makes level 0

            // 3. Compute the remaining length of string without '\t'
            int len = s.length() - tabIndexLevel;

            //remove all the level which is bigger than this because we must have evaluate the sub-string size by them
            while (top > tabIndexLevel + 1)
                top--;

            //4. Check does this is a file or not
            if (!s.contains(".")) {
                // a) if  a sub-dir, than append its length to just higher level sub-dir
                int peek = stack[top - 1];
                stack[top++] = (peek + len + 1);
            } else {
                //b) if its a file, compute the length of string for this file. To compute, get the level at this file is and add the length of the file
                maxLength = Math.max(maxLength, stack[top - 1] + len);
            }
        }
        return maxLength;


    }
}