package Java.LeetCode.serializeDeserialize.genrictree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 04/04/19
 * Description:
 */


public class Driver {
    static SerializeDeserializeNAryTree serializeDeserializeNAryTree = new SerializeDeserializeNAryTree();

    private static String levelOrder(NArrayTreeNode root) {
        if (root == null)
            return null;

        StringBuilder levelOrder = new StringBuilder();
        Queue<NArrayTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        levelOrder.append(root.val + ",");

        while (!queue.isEmpty()) {
            NArrayTreeNode node = queue.poll();

            if (node.children != null)
                for (NArrayTreeNode child : node.children) {
                    levelOrder.append(child.val + ",");
                    queue.offer(child);
                }
        }

        levelOrder.setLength(levelOrder.length() - 1);
        return levelOrder.toString();

    }

    public static void main(String []args) {

        test(getTree());
        test(getTree2());


    }

    private static void test(NArrayTreeNode root) {
        System.out.println("\n Running...\n");
        System.out.println(levelOrder(root));

        ISerializeDeserializeNArrayTree nArrayTree = new SerializeDeserializeNAryTree();
        String serialized = nArrayTree.serialize(root);
        System.out.println("serialized : " + serialized);
        NArrayTreeNode de = nArrayTree.deserialize(serialized);
        System.out.println(levelOrder(de));

    }

    private static NArrayTreeNode getTree() {
        NArrayTreeNode root = new NArrayTreeNode();
        root.val = 10;
        root.children = Arrays.asList(new NArrayTreeNode(2), new NArrayTreeNode(20), new NArrayTreeNode(12));
        NArrayTreeNode t = root.children.get(0);
        t.children = Arrays.asList(new NArrayTreeNode(1), new NArrayTreeNode(7));

        NArrayTreeNode tt = root.children.get(1);
        tt.children = Arrays.asList(new NArrayTreeNode(8), new NArrayTreeNode(9));

        NArrayTreeNode ttt = root.children.get(2);
        ttt.children = Arrays.asList(new NArrayTreeNode(21), new NArrayTreeNode(24), new NArrayTreeNode(25));


        return root;
    }

    private static NArrayTreeNode getTree2() {
        NArrayTreeNode root = new NArrayTreeNode();
        root.val = 10;
        root.children = Arrays.asList(new NArrayTreeNode(2), new NArrayTreeNode(20), new NArrayTreeNode(12));
        NArrayTreeNode t = root.children.get(0);
        t.children = Arrays.asList(new NArrayTreeNode(1));

        NArrayTreeNode tt = root.children.get(1);
        tt.children = Arrays.asList(new NArrayTreeNode(8));

        NArrayTreeNode ttt = root.children.get(2);
        ttt.children = Arrays.asList(new NArrayTreeNode(21));


        return root;
    }


}
