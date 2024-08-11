package DataStructureAlgo.Java.LeetCode2025.medium.stacks;

import java.util.Stack;

/**
 * Author: Nitin Gupta
 * Date:09/08/24
 * Question Category: 71. Simplify Path @medium
 * Description: https://leetcode.com/problems/simplify-path/description
 * <p>
 *
 * Given an absolute path for a Unix-style file system, which begins with a slash '/', transform this path into its simplified canonical path.
 *
 * In Unix-style file system context, a single period '.' signifies the current directory, a double period ".." denotes moving up one directory level, and multiple slashes such as "//" are interpreted as a single slash. In this problem, treat sequences of periods not covered by the previous rules (like "...") as valid names for files or directories.
 *
 * The simplified canonical path should adhere to the following rules:
 *
 * It must start with a single slash '/'.
 * Directories within the path should be separated by only one slash '/'.
 * It should not end with a slash '/', unless it's the root directory.
 * It should exclude any single or double periods used to denote current or parent directories.
 * Return the new path.
 *
 *
 *
 * Example 1:
 *
 * Input: path = "/home/"
 *
 * Output: "/home"
 *
 * Explanation:
 *
 * The trailing slash should be removed.
 *
 *
 * Example 2:
 *
 * Input: path = "/home//foo/"
 *
 * Output: "/home/foo"
 *
 * Explanation:
 *
 * Multiple consecutive slashes are replaced by a single one.
 *
 * Example 3:
 *
 * Input: path = "/home/user/Documents/../Pictures"
 *
 * Output: "/home/user/Pictures"
 *
 * Explanation:
 *
 * A double period ".." refers to the directory up a level.
 *
 * Example 4:
 *
 * Input: path = "/../"
 *
 * Output: "/"
 *
 * Explanation:
 *
 * Going one level up from the root directory is not possible.
 *
 * Example 5:
 *
 * Input: path = "/.../a/../b/c/../d/./"
 *
 * Output: "/.../b/d"
 *
 * Explanation:
 *
 * "..." is a valid name for a directory in this problem.
 *
 *
 *
 * Constraints:
 *
 * 1 <= path.length <= 3000
 * path consists of English letters, digits, period '.', slash '/' or '_'.
 * path is a valid absolute Unix path.
 *
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 * @medium
 * @String
 * @Stack
 *
 *
 *
 * <p>
 * Company Tags
 * -----
 * @Facebook
 * @Amazon
 * @Google
 * @Apple
 *
 * @Editorial
 */

public class SimplifyPath_71 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test("/home/", "/home");
        test &= test("/home//foo/", "/home/foo");
        test &= test("/home/user/Documents/../Pictures", "/home/user/Pictures");
        test &= test("/../", "/");
        test &= test("/.../a/../b/c/../d/./", "/.../b/d");
        test &= test("/", "/");
        test &= test("/..", "/");
        System.out.println((test ? "All passed" : "Failed"));

    }
    private static boolean test(String path, String expected) {
        System.out.println("\n\nTest:: path " + path + "\nexpected " + expected);
        SimplifyPath.SolutionUsingStacks solution = new SimplifyPath.SolutionUsingStacks();
        String output = solution.simplifyPath(path);
        if (!expected.equals(output)) {
            System.out.println("Failed for->" + path + "\nexpected->" + expected + "\nbut got -> " + output);
            return false;
        }else{
            System.out.println("Passed for->" + path + "\nexpected->" + expected + "\ngot -> " + output);
        }
        return true;
    }

    /**
     * Author: Nitin Gupta
     * Date:11/08/24
     * Question Category:
     * Description:
     * <p>
     * File reference
     * -----------
     * Duplicate {@link}
     * Similar {@link}
     * extension {@link }
     * <p>
     * Tags
     * -----
     *
     *
     * <p>
     * Company Tags
     * -----
     *
     * @Editorial
     */
    static class SimplifyPath {
        static class SolutionUsingStacks {
            public String simplifyPath(String path) {

                if (path == null || path.isEmpty() || path.length() == 1)
                    return path;

                final String[] stack = new String[path.length()];
                int top = -1;

                final String[] paths = path.split("/");

                for (String s : paths) {

                    if (s.equals("..")) {
                        if (top >= 0) {
                            top--;
                        }
                    } else if (!s.equals(".") && !s.isEmpty()) {
                        stack[++top] = s;
                    }
                }
                if (top == -1)
                    return "/";

                StringBuilder result = new StringBuilder();
                while (top >= 0) {
                    result.insert(0, "/" + stack[top--]);
                }
                return result.toString();


            }


            public String simplifyPath2(String path) {

                if (path == null || path.isEmpty() || path.length() == 1)
                    return path;

                final Stack<String> stack = new Stack<>();


                final String[] paths = path.split("/");

                for (String s : paths) {

                    if (s.equals("..")) {
                        if (!stack.isEmpty()) {
                            stack.pop();
                        }
                    } else if (!s.equals(".") && !s.isEmpty())
                        stack.push(s);
                }


                if (stack.isEmpty())
                    return "/";

                StringBuilder result = new StringBuilder();
                while (!stack.isEmpty()) {
                    result.insert(0, "/" + stack.pop());
                }
                return result.toString();


            }
        }
    }
}

