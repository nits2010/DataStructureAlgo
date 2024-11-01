package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Strings._1233;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Author: Nitin Gupta
 * Date:25/10/24
 * Question Category: 1233. Remove Sub-Folders from the Filesystem
 * Description: https://leetcode.com/problems/remove-sub-folders-from-the-filesystem/description
 * Given a list of folders folder, return the folders after removing all sub-folders in those folders. You may return the answer in any order.
 * <p>
 * If a folder[i] is located within another folder[j], it is called a sub-folder of it. A sub-folder of folder[j] must start with folder[j], followed by a "/". For example, "/a/b" is a sub-folder of "/a", but "/b" is not a sub-folder of "/a/b/c".
 * <p>
 * The format of a path is one or more concatenated strings of the form: '/' followed by one or more lowercase English letters.
 * <p>
 * For example, "/leetcode" and "/leetcode/problems" are valid paths while an empty string and "/" are not.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: folder = ["/a","/a/b","/c/d","/c/d/e","/c/f"]
 * Output: ["/a","/c/d","/c/f"]
 * Explanation: Folders "/a/b" is a subfolder of "/a" and "/c/d/e" is inside of folder "/c/d" in our filesystem.
 * Example 2:
 * <p>
 * Input: folder = ["/a","/a/b/c","/a/b/d"]
 * Output: ["/a"]
 * Explanation: Folders "/a/b/c" and "/a/b/d" will be removed because they are subfolders of "/a".
 * Example 3:
 * <p>
 * Input: folder = ["/a/b/c","/a/b/ca","/a/b/d"]
 * Output: ["/a/b/c","/a/b/ca","/a/b/d"]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= folder.length <= 4 * 104
 * 2 <= folder[i].length <= 100
 * folder[i] contains only lowercase letters and '/'.
 * folder[i] always starts with the character '/'.
 * Each folder name is unique.
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 * @medium
 * @Array
 * @String
 * @Depth-FirstSearch
 * @Trie <p>
 * Company Tags
 * -----
 * @Amazon
 * @Google
 * @Editorial
 * @OptimalSoltuion {@link SolutionUsingList} {@link SolutionUsingTrie2}
 */

public class RemoveSubFoldersFromTheFilesystem_1233 {


    public static void main(String[] args) {
        boolean test = true;
        test &= test(new String[]{"/a", "/c/d/e", "/a/b", "/c/d", "/c/f"}, List.of("/a", "/c/d", "/c/f"));
        test &= test(new String[]{"/a", "/a/b", "/c/d", "/c/d/e", "/c/f"}, List.of("/a", "/c/d", "/c/f"));
        test &= test(new String[]{"/a", "/a/b/c", "/a/b/d"}, List.of("/a"));
        test &= test(new String[]{"/a/b/c", "/a/b/ca", "/a/b/d"}, List.of("/a/b/c", "/a/b/ca", "/a/b/d"));
        test &= test(new String[]{"/a/b/c", "/a/b/ca", "/a/b/d", "/a/b/ca/d", "/a/b/ca/d/e"}, List.of("/a/b/c", "/a/b/ca", "/a/b/d"));

        CommonMethods.printAllTestOutCome(test);
    }

    private static boolean test(String[] folders, List<String> expected) {
        CommonMethods.printTestOutcome(new String[]{"Folders", "Expected"}, true, folders, expected);

        List<String> output;
        boolean pass, finalPass = true;

        SolutionUsingSet solutionUsingSet = new SolutionUsingSet();
        output = solutionUsingSet.removeSubfolders(Arrays.copyOf(folders, folders.length));
        Collections.sort(output);
        pass = output.equals(expected);
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"UsingSet", "Pass"}, false, output, pass ? "Pass" : "Fail");

        SolutionUsingList solutionUsingList = new SolutionUsingList();
        output = solutionUsingList.removeSubfolders(Arrays.copyOf(folders, folders.length));
        pass = output.equals(expected);
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"UsingList", "Pass"}, false, output, pass ? "Pass" : "Fail");

        SolutionUsingTrie solutionUsingTrie = new SolutionUsingTrie();
        output = solutionUsingTrie.removeSubfolders(Arrays.copyOf(folders, folders.length));
        pass = output.equals(expected);
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"UsingTrie", "Pass"}, false, output, pass ? "Pass" : "Fail");

        SolutionUsingTrie2 solutionUsingTrie2 = new SolutionUsingTrie2();
        output = solutionUsingTrie2.removeSubfolders(Arrays.copyOf(folders, folders.length));
        Collections.sort(output);
        pass = output.equals(expected);
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"UsingTrie2", "Pass"}, false, output, pass ? "Pass" : "Fail");

        return finalPass;


    }

    static class SolutionUsingSet {
        public List<String> removeSubfolders(String[] folders) {
            Arrays.sort(folders); // O(n*log(n)) - n is the length of folder

            Set<String> set = new HashSet<>();
            set.add(folders[0]);

            for (int j = 1; j < folders.length; j++) {
                String folder = folders[j];

                if (!set.contains(folder)) {
                    StringBuilder s = new StringBuilder();
                    int i = 0;

                    // O(m)
                    for (; i < folder.length(); i++) {
                        s.append(folder.charAt(i));
                        if (i + 1 < folder.length() && folder.charAt(i + 1) == '/') {
                            if (set.contains(s.toString()))
                                break;
                        }

                    }

                    if (i == folder.length())
                        set.add(folder);

                }
            }
            return new ArrayList<>(set);
        }
    }

    static class SolutionUsingList {

        // O(n*m*log(n)) / O(1)
        public List<String> removeSubfolders(String[] folders) {
            Arrays.sort(folders); // O(n*log(n)) - n is the length of folder

            List<String> set = new ArrayList<>();
            set.add(folders[0]);

            for (int j = 1; j < folders.length; j++) {
                String folder = folders[j];

                String last = set.get(set.size() - 1) + "/";
                if (!folder.startsWith(last)) // O(m) - m is the length of folder
                    set.add(folder);

            }
            return set;
        }
    }

    // Using Trie
    static class SolutionUsingTrie {

        static class TrieNode {
            boolean isLeaf = false;
            String value;
            Map<String, TrieNode> childs;

            TrieNode() {
                childs = new HashMap<>();

            }

            TrieNode(String v) {
                this.value = v;
                childs = new HashMap<>();

            }


            private boolean search(String[] names) {
                TrieNode pCrawl = this;
                int i = 0;
                for (; i < names.length; i++) {
                    String s = names[i];

                    if (!s.isEmpty()) {
                        pCrawl = pCrawl.childs.get(s);
                        if (pCrawl.isLeaf)
                            break;

                    }
                }

                return i + 1 == names.length;
            }

            private void insert(String[] names) {
                TrieNode pCrawl = this;
                for (String s : names) {
                    if (!s.isEmpty()) {

                        if (!pCrawl.childs.containsKey(s))
                            pCrawl.childs.put(s, new TrieNode(s));

                        pCrawl = pCrawl.childs.get(s);

                    }
                }
                pCrawl.isLeaf = true;
            }
        }


        public List<String> removeSubfolders(String[] folders) {
            List<String> output = new ArrayList<>();

            TrieNode root = new TrieNode();

            // build trie
            for (String folder : folders) {
                root.insert(folder.split("/"));
            }

            // cross-Check Folders
            for (String folder : folders) {
                String[] names = folder.split("/");
                if (root.search(names))
                    output.add(folder);
            }

            return output;
        }

    }


    // Using Trie
    static class SolutionUsingTrie2 {

        static class TrieNode {
            boolean isLeaf = false;
            String value;
            Map<String, TrieNode> childs;

            TrieNode() {
                childs = new HashMap<>();

            }

            TrieNode(String v) {
                this.value = v;
                childs = new HashMap<>();

            }


            private boolean search(String[] names) {
                TrieNode pCrawl = this;
                int i = 0;
                for (; i < names.length; i++) {
                    String s = names[i];

                    if (!s.isEmpty()) {
                        pCrawl = pCrawl.childs.get(s);
                        if (pCrawl == null)
                            return true;

                        if (pCrawl.isLeaf)
                            break;

                    }
                }

                return i + 1 == names.length;
            }

            private void insert(String[] names) {
                TrieNode pCrawl = this;
                for (String s : names) {
                    if (!s.isEmpty()) {

                        if (!pCrawl.childs.containsKey(s))
                            pCrawl.childs.put(s, new TrieNode(s));

                        pCrawl = pCrawl.childs.get(s);

                    }
                }
                pCrawl.isLeaf = true;
            }
        }


        public List<String> removeSubfolders(String[] folders) {
            List<String> output = new ArrayList<>();
            Arrays.sort(folders, Comparator.comparing(String::length));
            TrieNode root = new TrieNode();

            // build trie
            for (String folder : folders) {
                String[] names = folder.split("/");
                if (root.search(names)) {
                    output.add(folder);
                    root.insert(names);
                }


            }

            return output;
        }

    }


}
