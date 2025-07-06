package DataStructureAlgo.Java.nonleetcode.Tree;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 26/12/18
 * Description:
 */
public class BinarySearchTree implements IBinarySearchTree {

    List<TreeNode<Integer>> nodes = new LinkedList<>();

    public List<TreeNode<Integer>> getNodes() {
        return nodes;
    }

    public void clear() {
        nodes.clear();
    }


    @Override
    public TreeNode<Integer> insert(Integer data, TreeNode<Integer> root) {

        if (null == root) {
            TreeNode node = new BinaryTreeNode<>(data);
            nodes.add(node);
            return node;
        }

        if (root.getData() > data) {
            root.setLeft(insert(data, root.getLeft()));
        } else if (data > root.getData()) {
            root.setRight(insert(data, root.getRight()));
        }  //Duplicate key not allowed
        return root;

    }


    @Override
    public TreeNode<Integer> delete(Integer data, TreeNode<Integer> root) {

        if (null == root || null == data)
            return null;


        //If lies in left
        if (data.compareTo(root.getData()) < 0) {
            root.setLeft(delete(data, root.getLeft()));
            return root;
        }

        //If lies in right
        else if (data.compareTo(root.getData()) > 0) {
            root.setRight(delete(data, root.getRight()));

            return root;
        } else {

            //if this is to delete

            /**
             * Case 1: if its a leaf node
             */
            if (null == root.getLeft() && null == root.getRight())
                //delete it by resetting the parent pointer to null (left or right)
                return null;


            /**
             * Case 2: if this is a single child node
             */

            //If left child null, then carry the right child to parent
            if (null == root.getLeft()) {
                return root.getRight();
            }

            //If right child null, then carry the left child to parent
            if (null == root.getRight())
                return root.getLeft();


            /**
             * Case 3: if this is a double child node
             */

            TreeNode<Integer> successor = root.getRight();

            //find successor of root, that lies in right subtree, left most child
            while (null != successor.getLeft()) {
                successor = successor.getLeft();
            }

            //update the value of root from successor
            root.setData(successor.getData());

            root.setRight(delete(successor.getData(), root.getRight()));

            //carry root to its parent
            return root;
        }


    }


    @Override
    public boolean search(TreeNode<Integer> root, Integer data) {

        if (null == root)
            return false;

        if (root.getData() == data)
            return true;

        if (data.compareTo(root.getData()) < 0)
            return search(root.getLeft(), data);
        else
            return search(root.getRight(), data);

    }

    public Integer inOrderSuccessorIterative(TreeNode<Integer> root, TreeNode<Integer> node) {

        if (null == root || null == node)
            return null;
        Integer successor = null;

        while (root != null) {
            if (root.getData().compareTo(node.getData()) > 0) {
                successor = root.getData();
                root = root.getLeft();
            } else {
                root = root.getRight();
            }
        }
        return successor;

    }

    public Integer inOrderPredecessorIterative(TreeNode<Integer> root, TreeNode<Integer> node) {


        if (null == root || null == node)
            return null;
        Integer predecessor = null;

        while (root != null) {
            if (root.getData().compareTo(node.getData()) < 0) {
                predecessor = root.getData();
                root = root.getRight();
            } else {
                root = root.getLeft();
            }
        }
        return predecessor;

    }



    @Override
    public int largestBSTSize(TreeNode<Integer> root) {
        return size(root);
    }


    @Override
    public TreeNode<Integer> lowestCommonAncestor(TreeNode<Integer> root, TreeNode<Integer> alpha, TreeNode<Integer> beta) {
        return LowestCommonAncestor.lowestCommonAncestorBST(root, alpha, beta);
    }


    @Override
    public TreeNode postOrderToBst(List<Integer> postOrder) {

        if (null == postOrder || 0 == postOrder.size())
            return null;

        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;

        Wrapper wrapper = new Wrapper();
        wrapper.givenOrder = postOrder;
        wrapper.index = postOrder.size() - 1;


        return postOrderToBst(wrapper, min, max);

    }


    private TreeNode postOrderToBst(Wrapper wrapper, int min, int max) {
        if (wrapper.index < 0)
            return null;


        //The current node is in range, create a node with the value
        if (isInRange(wrapper, min, max)) {
            TreeNode<Integer> root = new BinaryTreeNode<>(wrapper.givenOrder.get(wrapper.index));
            wrapper.index--;


            root.setRight(postOrderToBst(wrapper, root.getData(), max));
            root.setLeft(postOrderToBst(wrapper, min, root.getData()));
            return root;

        }


        return null;
    }


    @Override
    public TreeNode preOrderToBst(List<Integer> preOrder) {
        if (null == preOrder || 0 == preOrder.size())
            return null;

        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;

        Wrapper wrapper = new Wrapper();
        wrapper.givenOrder = preOrder;
        wrapper.index = 0;


        return preOrderToBst(wrapper, min, max);

    }


    private TreeNode preOrderToBst(Wrapper wrapper, int min, int max) {

        if (wrapper.index >= wrapper.givenOrder.size())
            return null;

        //  System.out.println("Current element in check : " + wrapper.givenOrder.get(wrapper.index));


        //The current node is in range, create a node with the value
        if (isInRange(wrapper, min, max)) {

            //   System.out.println("is in range " + wrapper.givenOrder.get(wrapper.index) + " Range min " + min + " max : " + max);

            TreeNode<Integer> root = new BinaryTreeNode<>(wrapper.givenOrder.get(wrapper.index));
            wrapper.index++;

            //  System.out.println("Node created , going left min : " + min + " max " + root.getData());

            root.setLeft(preOrderToBst(wrapper, min, root.getData()));

            //  System.out.println("Node created , going right min : " + root.getData() + " max " + max);
            root.setRight(preOrderToBst(wrapper, root.getData(), max));

            return root;
        }

        return null;
    }


    @Override
    public TreeNode inOrderToBst(List<Integer> inOrder) {
        if (null == inOrder || 0 == inOrder.size())
            return null;

        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;

        Wrapper wrapper = new Wrapper();
        wrapper.givenOrder = inOrder;
        wrapper.index = 0;


        return inOrderToBst(wrapper, min, max);


    }


    private TreeNode inOrderToBst(Wrapper wrapper, int min, int max) {


        return null;
    }


    @Override
    public TreeNode levelOrderToBst(List<Integer> levelOrder) {

        if (null == levelOrder || 0 == levelOrder.size())
            return null;


        Queue<Wrapper> queue = new LinkedList<>();


        Wrapper rootWrapper = new Wrapper();

        rootWrapper.root = new BinaryTreeNode(levelOrder.get(0));
        rootWrapper.min.updateData(Integer.MIN_VALUE);
        rootWrapper.max.updateData(Integer.MAX_VALUE);

        queue.add(rootWrapper);
        int i = 1;

        while (i < levelOrder.size()) {
            Wrapper temp = queue.poll();

            int ele = levelOrder.get(i);

            //If this is left
            if (i < levelOrder.size() && ele < temp.root.getData() && temp.min.data.compareTo(ele) < 0) {
                temp.root.setLeft(new BinaryTreeNode(ele));
                Wrapper left = new Wrapper();
                left.root = temp.root.getLeft();
                left.min.updateData(temp.min.data);
                left.max.updateData(temp.root.getData());

                queue.add(left);
                i++;
            }


            if (i < levelOrder.size() && ele > temp.root.getData() && temp.max.data.compareTo(ele) > 0) {
                temp.root.setRight(new BinaryTreeNode(ele));
                Wrapper right = new Wrapper();
                right.root = temp.root.getRight();
                right.min.updateData(temp.root.getData());
                right.max.updateData(temp.max.data);

                queue.add(right);
                i++;
            }

        }

        return rootWrapper.root;
    }


    @Override
    public List<Integer> preOrderToPostOrder(List<Integer> preOrder) {
        if (null == preOrder || 0 == preOrder.size())
            return new ArrayList<>();

        Wrapper wrapper = new Wrapper();

        wrapper.givenOrder = preOrder;
        wrapper.index = 0;
        wrapper.output = new ArrayList<>(preOrder.size());
        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;

        preOrderToPostOrder(wrapper, min, max);
        return wrapper.output;


    }

    private void preOrderToPostOrder(Wrapper wrapper, int min, int max) {
        if (wrapper.index >= wrapper.givenOrder.size())
            return;

        //Check if the said tree( drive from below recursion ) is on that side
        if (!isInRange(wrapper, min, max))
            return;

        //Cache this value to process later
        Integer val = wrapper.givenOrder.get(wrapper.index);
        wrapper.index++;


        preOrderToPostOrder(wrapper, min, val);
        preOrderToPostOrder(wrapper, val, max);

        //Postorder process root as last
        wrapper.output.add(val);


    }

    private boolean isInRange(Wrapper wrapper, int min, int max) {
        int data = wrapper.givenOrder.get(wrapper.index);

        if (data > min && data < max)
            return true;
        return false;
    }


    @Override
    public List<Integer> postOrderToPreOrder(List<Integer> preOrder) {
        return null;
    }

    /**
     * //https://www.geeksforgeeks.org/check-for-identical-bsts-without-building-the-trees/
     *
     * @param input1
     * @param input2
     * @return
     */
    @Override
    public boolean checkForIdenticalBST(List<Integer> input1, List<Integer> input2) {

        //They both should have same length at least
        if ((null == input1 && null != input2)
                || (null == input2 && null != input1)
                || (input1.size() != input2.size()))
            return false;

        int index1 = 0, index2 = 0, min = Integer.MIN_VALUE, max = Integer.MAX_VALUE;
        return checkForIdenticalBST(input1, input2, index1, index2, min, max);

    }

    //O(n^2)
    private boolean checkForIdenticalBST(List<Integer> input1, List<Integer> input2, int index1, int index2, int min, int max) {

        //Out of bound check
        if (index1 >= input1.size() && index2 >= input2.size())
            return false;

        //find the left and right element that satisfy the min max condition
        int j = findInRange(input1, index1, min, max); //O(n)
        int k = findInRange(input2, index2, min, max); //O(n)


        //if both of them are leaf node, then they have same identical bst
        if (j == input1.size() && k == input2.size())
            return true;


        //If either of them is not a leaf node but one of them is  Or both are not same element then they don't form identical bst
        if (((j == input1.size() && k != input2.size())
                || (j != input1.size() && k == input2.size()))
                || (input1.get(j) != input2.get(k)))

            return false;

        // otherwise check for both left and right
        int element = input1.get(j); // since both element are same, this is does not matter
        return checkForIdenticalBST(input1, input2, index1 + 1, index2 + 1, min, element) && // Move left O(n)
                checkForIdenticalBST(input1, input2, index1 + 1, index2 + 1, element, max); //Move right O(n)
    }

    private int findInRange(List<Integer> input, int startIndex, int min, int max) {
        int i = startIndex;
        for (; i < input.size(); i++) {
            int ele = input.get(i);
            if (ele > min && ele < max)
                break;
        }
        return i;

    }


    @Override
    public double medianO1(TreeNode<Integer> root) {

        if (null == root)
            return 0.0;

        int size = size(root);

        int limit = size / 2;

        TreeNode<Integer> current = root, prev = null;
        int count = 0;

        double median = 0.0;
        boolean found = false; //We don't break the loop once median find, so that we can correct the tree back ; Mories traversal


        while (current != null) {

            if (current.getLeft() == null) {
                count++;

                if (count > limit && !found) {

                    if (size % 2 == 0)
                        median = (prev.getData() + current.getData()) / 2;
                    else
                        median = current.getData();

                    found = true;
                }

                prev = current;
                current = current.getRight();

            } else {
                TreeNode<Integer> temp = current.getLeft();

                while (temp.getRight() != null && temp.getRight() != current)
                    temp = temp.getRight();

                if (temp.getRight() == null) {
                    temp.setRight(current);
                    current = current.getLeft();

                } else {

                    temp.setRight(null);
                    count++;

                    if (count > limit && !found) {

                        if (size % 2 == 0)
                            median = (prev.getData() + current.getData()) / 2;
                        else
                            median = current.getData();

                        found = true;
                    }
                    prev = current;
                    current = current.getRight();
                }
            }
        }


        return median;
    }

    @Override
    public List<Integer> mergeInSorted(TreeNode<Integer> root1, TreeNode<Integer> root2) {

        if (root1 == null)
            return inOrder(root2);

        if (root2 == null)
            return inOrder(root1);


        TreeNode<Integer> current1 = root1;
        TreeNode<Integer> current2 = root2;
        Stack<TreeNode<Integer>> stack1 = new Stack<>();
        Stack<TreeNode<Integer>> stack2 = new Stack<>();

        List<Integer> values = new LinkedList<>();
        while (current1 != null || current2 != null) {

            if (current1 != null || current2 != null) {

                while (current1 != null) {
                    stack1.push(current1);
                    current1 = current1.getLeft();

                }

                while (current2 != null) {
                    stack2.push(current2);
                    current2 = current2.getLeft();

                }
            } else {

                if (stack1.isEmpty()) {

                    while (!stack2.isEmpty()) {

                        current2 = stack2.pop();
                        current2.setLeft(null);
                        values.addAll(inOrder(current2));
                        return values;
                    }
                }
                if (stack2.isEmpty()) {

                    while (!stack1.isEmpty()) {

                        current1 = stack1.pop();
                        current1.setLeft(null);
                        values.addAll(inOrder(current1));
                        return values;
                    }
                }

                if (stack1.peek().getData() < stack2.peek().getData()) {
                    current1 = stack1.pop();
                    values.add(current1.getData());
                    current2 = null;
                } else {
                    current2 = stack2.pop();
                    values.add(current2.getData());
                    current1 = null;
                }
            }
        }


        return values;
    }


    class Wrapper {

        List<Integer> givenOrder;
        int index;
        List<Integer> output;

        MinMaxObject min = new MinMaxObject();
        MinMaxObject max = new MinMaxObject();

        TreeNode<Integer> root;


    }
}
