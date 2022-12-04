package DataStructureAlgo.Java.companyWise.LinkedIn;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-04
 * Description: https://leetcode.com/discuss/interview-question/algorithms/125080/generate-a-binary-tree-from-parent-child-relationship
 * <p>
 * This was asked in Java.companyWise.LinkedIn Interview
 * Given a list of child->parent relationships, build a binary tree out of it. All the element Ids inside the tree are unique.
 * <p>
 * Example:
 * <p>
 * Given the following relationships:
 * <p>
 * Child Parent IsLeft
 * 15 20 true
 * 19 80 true
 * 17 20 false
 * 16 80 false
 * 80 50 false
 * 50 null false
 * 20 50 true
 * <p>
 * You should return the following tree:
 * <p>
 * 50
 * /  \
 * 20   80
 * / \   / \
 * 15 17 19 16
 * Function Signature
 * <p>
 * <p>
 * Represents a pair relation between one parent node and one child node inside a binary tree
 * If the _parent is null, it represents the ROOT node
 * <p>
 * <p>
 * <p>
 * * class Relation {
 * *    int parent;
 * *    int child;
 * *    bool isLeft;
 * * };
 * <p>
 * Represents a single Node inside a binary tree
 * <p>
 * * class Node {
 * *     int id;
 * *     Node *left;
 * *     Node *right;
 * * }
 * Implement a method to build a tree from a list of parent-child relationships
 * And return the root Node of the tree
 * <p>
 * *     Node buildTree(List<Relation> data){}
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