package Java.companyWise.facebook;

import java.util.*;
import java.util.Map;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 04/04/19
 * Description: https://www.geeksforgeeks.org/minimum-word-break/
 * <p>
 * Given a string s, break s such that every substring of the partition can be found in the dictionary. Return the minimum break needed.
 * <p>
 * Given a dictionary ["Cat", "Mat", "Ca",
 * "tM", "at", "C", "Dog", "og", "Do"]
 * <p>
 * Input :  Pattern "CatMat"
 * Output : 1
 * Explanation: we can break the sentences
 * in three ways, as follows:
 * CatMat = [ Cat Mat ]  break in 1
 * CatMat = [ Ca tM at ] break in 2
 * CatMat = [ C at Mat ] break in 2 so the
 * output is: 1
 * <p>
 * <p>
 * Approach 1:
 * a. We can assume the dictionary as hash map.
 * b. starting from 0 cut require to n cut require for (int cut =0; cut<n; cut++) where n is the size of pattern
 * c. for each cut we see the substring is in map or not, if its then this number of cut is required
 * <p>
 * Approach 2:
 * 1. Build the trie of given dic
 * 2. Start searching the pattern till it either hit the end of pattern or a leaf node in trie
 * 2.1 if it hit a leaf node in trie, and pattern still remaining that means we have to cut the pattern here at least, increment the count and
 * recursively apply this on rest of the pattern
 * 2.2 if it hit the leaf node in trie and pattern is exhaust then return the min cut so far.
 */
public class MinimumWordBreak {


    static class TrieNode {

        char value;
        Map<Character, TrieNode> childrens = new HashMap<>();
        boolean isLeaf = false;

        public TrieNode() {
        }

        public TrieNode(char value) {
            this.value = value;
        }
    }

    static class Trie {

        TrieNode root;

        public void insert(String toInsert) {

            if (root == null)
                root = new TrieNode();

            TrieNode pCrawl = root;

            for (int i = 0; i < toInsert.length(); i++) {
                char current = toInsert.charAt(i);

                if (!pCrawl.childrens.containsKey(current))
                    pCrawl.childrens.put(current, new TrieNode(current));

                pCrawl = pCrawl.childrens.get(current);
            }

            pCrawl.isLeaf = true;
        }

        public int search(String toSearch) {
            TrieNode pCrawl = root;
            int lastMatchPoint = -1;
            int i;
            for (i = 0; i < toSearch.length(); i++) {
                char current = toSearch.charAt(i);


                if (pCrawl.childrens.containsKey(current))
                    pCrawl = pCrawl.childrens.get(current);
                else
                    break;


                if (pCrawl.isLeaf)
                    lastMatchPoint = i;


            }

            if (pCrawl.isLeaf)
                return (lastMatchPoint == -1) ? i : lastMatchPoint;

            return lastMatchPoint;


        }


    }


    public static int minimumCut(String pattern, Trie trie) {

        String temp = pattern;
        int remaining = temp.length();
        int minCut = 0;
        int lastMatchPoint = 0;

        while (remaining > 0) {

            lastMatchPoint = trie.search(temp);

            if (lastMatchPoint == -1) { // If this is not found, then its impossible to break it because this string / character is not present in dic;
                /**
                 * Example:  dictionary[] = {"Cat", "Mat", "Ca", "tM", "at", "C", "Dog", "og", "Do"};
                 * Pattern= Dogcat ; Dog is found but cat or 'c' it self not found hence not possible
                 */

                return -1;
            } else if (lastMatchPoint + 1 < temp.length()) { //if Found, then increase the cut if require otherwise we are done with cut
                minCut++;
                temp = temp.substring(lastMatchPoint + 1);
                remaining -= lastMatchPoint + 1;
            } else return minCut;

        }


        return minCut;

    }

    public static int minimumCutWay2(String pattern, Trie trie) {

        TrieNode pCrawl = trie.root;
        String temp = "", breaked = "";
        int mincut = 0;

        for (int i = 0; i < pattern.length(); i++) {

            char c = pattern.charAt(i);

            if (pCrawl.childrens.containsKey(c)) {

                if (pCrawl.childrens.get(c).isLeaf)
                    breaked = temp + c;

                temp = temp + c;
                pCrawl = pCrawl.childrens.get(c);

            } else {
                if (breaked.isEmpty())
                    return -1;

                mincut++;
                i--;
                breaked = temp = "";
                pCrawl = trie.root;
            }
        }

        if (breaked.isEmpty())
            return -1;
        return mincut;


    }


    public static void main(String args[]) {
        String dictionary[] = {"Cat", "Mat", "Ca", "tM", "at", "C", "Dog", "og", "Do", "DogYat"};

        Trie trie = new Trie();
        Arrays.stream(dictionary).forEach(x -> trie.insert(x));

        System.out.println(minimumCut("Dogcat", trie));
        System.out.println(minimumCutWay2("Dogcat", trie));

        System.out.println(minimumCut("DogCat", trie));
        System.out.println(minimumCutWay2("DogCat", trie));

        System.out.println(minimumCut("CtMMat", trie));
        System.out.println(minimumCutWay2("CtMMat", trie));

        System.out.println(minimumCut("CatMat", trie));
        System.out.println(minimumCutWay2("CatMat", trie));

        System.out.println(minimumCut("DogYat", trie));
        System.out.println(minimumCutWay2("DogYat", trie));


    }


}
