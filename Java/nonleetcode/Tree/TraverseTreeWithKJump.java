package Java.nonleetcode.Tree;

import java.util.*;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-06-18
 * Description:
 * https://www.geeksforgeeks.org/traversal-tree-ability-jump-nodes-height/
 * There is a tree with N nodes and node 1 is the root node. Each node of the tree can contain fruit or not.
 * Initially, you are at the root node and start climbing on the tree.
 * You can jump from a node to any node at the same level(i.e. the height of nodes from the root are same),
 * During climbing from root node you can only make maximum K jumps. (K < 20)
 * Now you have to climb on the tree (from root node-> any leaf node) in such a way so that you can collect maximum no of fruits.
 * Example :
 * <p>
 * Input Tree :
 * Number of Nodes N = 12
 * Number of jumps allowed : 2
 * Edges:
 * 1 2
 * 1 3
 * 2 4
 * 2 5
 * 5 9
 * 9 10
 * 9 11
 * 11 12
 * 3 7
 * 7 6
 * 7 8
 * no of node having fruit(nf) : 8
 * Nodes Containing Fruits(lvn) : 2 4 5 7 8 9 11 12
 * Output: 7
 */
public class TraverseTreeWithKJump {


    private static void addEdge(List<List<Integer>> tree, int source, int child) {


        tree.get(source).add(child);
        tree.get(child).add(source);
    }

    public static void main(String []args) {

        List<List<Integer>> tree = new ArrayList<>();

        for (int i = 0; i <= 12; i++)
            tree.add(new ArrayList<>());

        addEdge(tree, 1, 2);
        addEdge(tree, 1, 3);
        addEdge(tree, 2, 5);
        addEdge(tree, 2, 4);
        addEdge(tree, 3, 7);
        addEdge(tree, 5, 9);
        addEdge(tree, 7, 6);
        addEdge(tree, 7, 8);
        addEdge(tree, 9, 10);
        addEdge(tree, 9, 11);
        addEdge(tree, 11, 12);

        int containFruits[] = {2, 5, 7, 9, 11, 12};// {2, 4, 5, 7, 8, 9, 11, 12};

        System.out.println(traverseTreeWithKJump(tree, containFruits, 2, 20));


    }

    private static int traverseTreeWithKJump(List<List<Integer>> tree, int[] containFruits, int jump, int maxJumps) {

        Map<Integer, List<Integer>> heights = new HashMap<>();
        Map<Integer, Integer> parents = new HashMap<>();

        fillHeightArray(tree, heights, parents);

        int dp[][] = new int[tree.size()][maxJumps];
        for (int i = 0; i < tree.size(); i++)
            Arrays.fill(dp[i], -1);

        int fruits[] = new int[tree.size()];
        Arrays.fill(fruits, 0);

        for (int i = 0; i < containFruits.length; i++)
            fruits[containFruits[i]] = 1;

        Set<Integer> fruitsObtained = new HashSet<>();
        return collectFruitsWithJump(heights, parents, tree, fruits, jump, dp, 1, 0, 1, fruitsObtained);

    }

    private static int collectFruitsWithJump(Map<Integer, List<Integer>> heights, Map<Integer, Integer> parents, List<List<Integer>> tree, int[] fruits,
                                             int jump, int dp[][], int root, int parent, int height, Set<Integer> fruitsObtained) {

        if (dp[root][jump] != -1)
            return dp[root][jump];


        int ans = 0;
        //Try all jumps and compute
        if (jump > 0) {

            for (Integer v : heights.get(height)) {

                if (v != parent) {

                    ans = Math.max(ans, collectFruitsWithJump(heights, parents, tree, fruits, jump - 1, dp, v, root, height, fruitsObtained));
                }
            }
        }

        //Try to climb
        for (Integer v : tree.get(root)) {

            if (v != parent) {
                ans = Math.max(ans, collectFruitsWithJump(heights, parents, tree, fruits, jump, dp, v, root, height + 1, fruitsObtained));
            }
        }

        if (fruits[root] == 1 && !fruitsObtained.contains(root)) {
            ans++;
            fruitsObtained.add(root);
        }
        dp[root][jump] = ans;

        return ans;
    }

    private static void fillHeightArray(List<List<Integer>> tree, Map<Integer, List<Integer>> heights, Map<Integer, Integer> parents) {

        fillHeightArray(tree, heights, parents, 1, 0, 1);
    }

    private static void fillHeightArray(List<List<Integer>> tree, Map<Integer, List<Integer>> heights, Map<Integer, Integer> parents, int root, int parent, int height) {

        if (heights.containsKey(height)) {
            heights.get(height).add(root);
        } else {
            List<Integer> nodes = new ArrayList<>();
            nodes.add(root);
            heights.put(height, nodes);
        }

        parents.put(root, parent);

        for (Integer v : tree.get(root)) {

            if (v != parent) {
                fillHeightArray(tree, heights, parents, v, root, height + 1);
            }

        }

    }
}
