package DataStructureAlgo.Java.companyWise.LinkedIn;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 2026-03-14
 * Question Title: Generate Binary Tree Parent Child Relationship
 * Link: https://leetcode.com/problems/generate-binary-tree-parent-child-relationship/
 * Description:
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class GenerateBinaryTreeParentChildRelationship {

    static Node root = null;

    public static void main(String []args) {

        Node root = test1();
        inorder(root);
    }

    private static void inorder(Node root) {

        if (root == null)
            return;

        inorder(root.left);
        System.out.print(root.id + " ");
        inorder(root.right);

    }

    private static Node buildTree(List<Relation> data) {

        root = null;
        Map<Integer, Integer> childToParent = new HashMap<>();
        Map<Integer, Boolean> isLeft = new HashMap<>();


        buildChildParentHashMap(data, childToParent, isLeft);

        Map<Integer, Node> nodes = new HashMap<>();

        for (Relation r : data) {

            if (!nodes.containsKey(r.child)) {
                buildTree(childToParent, r.child, isLeft, nodes);
            }
        }


        return root;
    }

    private static void buildChildParentHashMap(List<Relation> data, Map<Integer, Integer> childToParent, Map<Integer, Boolean> isLeft) {

        for (Relation r : data) {
            childToParent.put(r.child, r.parent);
            isLeft.put(r.child, r.isLeft);
        }
    }

    private static Node buildTree(Map<Integer, Integer> childToParent, int current, Map<Integer, Boolean> isLeft, Map<Integer, Node> nodes) {

        if (nodes.containsKey(current))
            return nodes.get(current);

        if (childToParent.get(current) == -1) {
            //this is root;
            root = new Node();
            root.id = current;
            nodes.put(current, root);
            return root;
        }


        Node parent = buildTree(childToParent, childToParent.get(current), isLeft, nodes);

        Node child = new Node();
        child.id = current;

        if (isLeft.get(current))
            parent.left = child;
        else
            parent.right = child;

        nodes.put(current, child);

        return child;

    }


    private static Node test1() {

        List<Relation> data = new LinkedList<>();
        data.add(new Relation(15, 20, true));
        data.add(new Relation(19, 80, true));
        data.add(new Relation(17, 20, false));
        data.add(new Relation(16, 80, false));
        data.add(new Relation(80, 50, false));
        data.add(new Relation(50, -1, false));
        data.add(new Relation(20, 50, true));

        return buildTree(data);
    }
}


class Node {
    int id;
    Node left;
    Node right;
}

class Relation {
    int parent;
    int child;
    boolean isLeft;

    public Relation(int child, int parent, boolean left) {
        this.parent = parent;
        this.child = child;
        this.isLeft = left;

    }
}